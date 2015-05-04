package org.springframework.security.acls.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.springframework.security.acls.AccessControlEntry;
import org.springframework.security.acls.Acl;
import org.springframework.security.acls.AuditableAcl;
import org.springframework.security.acls.MutableAcl;
import org.springframework.security.acls.NotFoundException;
import org.springframework.security.acls.OwnershipAcl;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.UnloadedSidException;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.sid.Sid;
import org.springframework.util.Assert;

public class AclImpl
  implements Acl, MutableAcl, AuditableAcl, OwnershipAcl
{
  private Acl parentAcl;
  private transient AclAuthorizationStrategy aclAuthorizationStrategy;
  private transient AuditLogger auditLogger;
  private List aces = new Vector();
  private ObjectIdentity objectIdentity;
  private Serializable id;
  private Sid owner;
  private Sid[] loadedSids = null;
  private boolean entriesInheriting = true;
  
  public AclImpl(ObjectIdentity objectIdentity, Serializable id, AclAuthorizationStrategy aclAuthorizationStrategy, AuditLogger auditLogger)
  {
    Assert.notNull(objectIdentity, "Object Identity required");
    Assert.notNull(id, "Id required");
    Assert.notNull(aclAuthorizationStrategy, "AclAuthorizationStrategy required");
    Assert.notNull(auditLogger, "AuditLogger required");
    this.objectIdentity = objectIdentity;
    this.id = id;
    this.aclAuthorizationStrategy = aclAuthorizationStrategy;
    this.auditLogger = auditLogger;
  }
  
  public AclImpl(ObjectIdentity objectIdentity, Serializable id, AclAuthorizationStrategy aclAuthorizationStrategy, AuditLogger auditLogger, Acl parentAcl, Sid[] loadedSids, boolean entriesInheriting, Sid owner)
  {
    Assert.notNull(objectIdentity, "Object Identity required");
    Assert.notNull(id, "Id required");
    Assert.notNull(aclAuthorizationStrategy, "AclAuthorizationStrategy required");
    Assert.notNull(owner, "Owner required");
    Assert.notNull(auditLogger, "AuditLogger required");
    this.objectIdentity = objectIdentity;
    this.id = id;
    this.aclAuthorizationStrategy = aclAuthorizationStrategy;
    this.auditLogger = auditLogger;
    this.parentAcl = parentAcl;
    this.loadedSids = loadedSids;
    this.entriesInheriting = entriesInheriting;
    this.owner = owner;
  }
  
  private AclImpl() {}
  
  private void verifyAceIndexExists(int aceIndex)
  {
    if (aceIndex < 0) {
      throw new NotFoundException("aceIndex must be greater than or equal to zero");
    }
    if (aceIndex > this.aces.size()) {
      throw new NotFoundException("aceIndex must correctly refer to an index of the AccessControlEntry collection");
    }
  }
  
  public void deleteAce(int aceIndex)
    throws NotFoundException
  {
    this.aclAuthorizationStrategy.securityCheck(this, 2);
    verifyAceIndexExists(aceIndex);
    synchronized (this.aces)
    {
      this.aces.remove(aceIndex);
    }
  }
  
  public AccessControlEntry[] getEntries()
  {
    return (AccessControlEntry[])this.aces.toArray(new AccessControlEntry[0]);
  }
  
  public Serializable getId()
  {
    return this.id;
  }
  
  public ObjectIdentity getObjectIdentity()
  {
    return this.objectIdentity;
  }
  
  public Sid getOwner()
  {
    return this.owner;
  }
  
  public Acl getParentAcl()
  {
    return this.parentAcl;
  }
  
  public void insertAce(int atIndexLocation, Permission permission, Sid sid, boolean granting)
    throws NotFoundException
  {
    this.aclAuthorizationStrategy.securityCheck(this, 2);
    Assert.notNull(permission, "Permission required");
    Assert.notNull(sid, "Sid required");
    if (atIndexLocation < 0) {
      throw new NotFoundException("atIndexLocation must be greater than or equal to zero");
    }
    if (atIndexLocation > this.aces.size()) {
      throw new NotFoundException("atIndexLocation must be less than or equal to the size of the AccessControlEntry collection");
    }
    AccessControlEntryImpl ace = new AccessControlEntryImpl(null, this, sid, permission, granting, false, false);
    synchronized (this.aces)
    {
      this.aces.add(atIndexLocation, ace);
    }
  }
  
  public boolean isEntriesInheriting()
  {
    return this.entriesInheriting;
  }
  
  public boolean isGranted(Permission[] permission, Sid[] sids, boolean administrativeMode)
    throws NotFoundException, UnloadedSidException
  {
    Assert.notEmpty(permission, "Permissions required");
    Assert.notEmpty(sids, "SIDs required");
    if (!isSidLoaded(sids)) {
      throw new UnloadedSidException("ACL was not loaded for one or more SID");
    }
    AccessControlEntry firstRejection = null;
    for (int i = 0; i < permission.length; i++) {
      for (int x = 0; x < sids.length; x++)
      {
        Iterator acesIterator = this.aces.iterator();
        boolean scanNextSid = true;
        while (acesIterator.hasNext())
        {
          AccessControlEntry ace = (AccessControlEntry)acesIterator.next();
          if ((ace.getPermission().getMask() == permission[i].getMask()) && (ace.getSid().equals(sids[x])))
          {
            if (ace.isGranting())
            {
              if (!administrativeMode) {
                this.auditLogger.logIfNeeded(true, ace);
              }
              return true;
            }
            if (firstRejection == null) {
              firstRejection = ace;
            }
            scanNextSid = false;
            
            break;
          }
        }
        if (!scanNextSid) {
          break;
        }
      }
    }
    if (firstRejection != null)
    {
      if (!administrativeMode) {
        this.auditLogger.logIfNeeded(false, firstRejection);
      }
      return false;
    }
    if ((isEntriesInheriting()) && (this.parentAcl != null)) {
      return this.parentAcl.isGranted(permission, sids, false);
    }
    throw new NotFoundException("Unable to locate a matching ACE for passed permissions and SIDs");
  }
  
  public boolean isSidLoaded(Sid[] sids)
  {
    if ((this.loadedSids == null) || (sids == null) || (sids.length == 0)) {
      return true;
    }
    for (int i = 0; i < sids.length; i++)
    {
      boolean found = false;
      for (int y = 0; y < this.loadedSids.length; y++) {
        if (sids[i].equals(this.loadedSids[y]))
        {
          found = true;
          
          break;
        }
      }
      if (!found) {
        return false;
      }
    }
    return true;
  }
  
  public void setEntriesInheriting(boolean entriesInheriting)
  {
    this.aclAuthorizationStrategy.securityCheck(this, 2);
    this.entriesInheriting = entriesInheriting;
  }
  
  public void setOwner(Sid newOwner)
  {
    this.aclAuthorizationStrategy.securityCheck(this, 0);
    Assert.notNull(newOwner, "Owner required");
    this.owner = newOwner;
  }
  
  public void setParent(Acl newParent)
  {
    this.aclAuthorizationStrategy.securityCheck(this, 2);
    Assert.isTrue((newParent == null) || (!newParent.equals(this)), "Cannot be the parent of yourself");
    this.parentAcl = newParent;
  }
  
  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("AclImpl[");
    sb.append("id: ").append(this.id).append("; ");
    sb.append("objectIdentity: ").append(this.objectIdentity).append("; ");
    sb.append("owner: ").append(this.owner).append("; ");
    
    Iterator iterator = this.aces.iterator();
    int count = 0;
    while (iterator.hasNext())
    {
      count++;
      if (count == 1) {
        sb.append("\r\n");
      }
      sb.append(iterator.next().toString()).append("\r\n");
    }
    if (count == 0) {
      sb.append("no ACEs; ");
    }
    sb.append("inheriting: ").append(this.entriesInheriting).append("; ");
    sb.append("parent: ").append(this.parentAcl == null ? "Null" : this.parentAcl.getObjectIdentity().toString());
    sb.append("aclAuthorizationStrategy: ").append(this.aclAuthorizationStrategy).append("; ");
    sb.append("auditLogger: ").append(this.auditLogger);
    sb.append("]");
    
    return sb.toString();
  }
  
  public void updateAce(int aceIndex, Permission permission)
    throws NotFoundException
  {
    this.aclAuthorizationStrategy.securityCheck(this, 2);
    verifyAceIndexExists(aceIndex);
    synchronized (this.aces)
    {
      AccessControlEntryImpl ace = (AccessControlEntryImpl)this.aces.get(aceIndex);
      ace.setPermission(permission);
    }
  }
  
  public void updateAuditing(int aceIndex, boolean auditSuccess, boolean auditFailure)
  {
    this.aclAuthorizationStrategy.securityCheck(this, 1);
    verifyAceIndexExists(aceIndex);
    synchronized (this.aces)
    {
      AccessControlEntryImpl ace = (AccessControlEntryImpl)this.aces.get(aceIndex);
      ace.setAuditSuccess(auditSuccess);
      ace.setAuditFailure(auditFailure);
    }
  }
  
  public boolean equals(Object obj)
  {
    if ((obj instanceof AclImpl))
    {
      AclImpl rhs = (AclImpl)obj;
      if ((this.aces.equals(rhs.aces)) && (
        ((this.parentAcl == null) && (rhs.parentAcl == null)) || ((this.parentAcl.equals(rhs.parentAcl)) && (
        ((this.objectIdentity == null) && (rhs.objectIdentity == null)) || ((this.objectIdentity.equals(rhs.objectIdentity)) && (
        ((this.id == null) && (rhs.id == null)) || ((this.id.equals(rhs.id)) && (
        ((this.owner == null) && (rhs.owner == null)) || ((this.owner.equals(rhs.owner)) && 
        (this.entriesInheriting == rhs.entriesInheriting))))))))))
      {
        if ((this.loadedSids == null) && (rhs.loadedSids == null)) {
          return true;
        }
        if (this.loadedSids.length == rhs.loadedSids.length)
        {
          for (int i = 0; i < this.loadedSids.length; i++) {
            if (!this.loadedSids[i].equals(rhs.loadedSids[i])) {
              return false;
            }
          }
          return true;
        }
      }
    }
    return false;
  }
}
