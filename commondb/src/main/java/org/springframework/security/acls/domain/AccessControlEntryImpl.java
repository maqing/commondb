package org.springframework.security.acls.domain;

import java.io.Serializable;
import org.springframework.security.acls.AccessControlEntry;
import org.springframework.security.acls.Acl;
import org.springframework.security.acls.AuditableAccessControlEntry;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.sid.Sid;
import org.springframework.util.Assert;

public class AccessControlEntryImpl
  implements AccessControlEntry, AuditableAccessControlEntry
{
  private Acl acl;
  private Permission permission;
  private Serializable id;
  private Sid sid;
  private boolean auditFailure = false;
  private boolean auditSuccess = false;
  private boolean granting;
  
  public AccessControlEntryImpl(Serializable id, Acl acl, Sid sid, Permission permission, boolean granting, boolean auditSuccess, boolean auditFailure)
  {
    Assert.notNull(acl, "Acl required");
    Assert.notNull(sid, "Sid required");
    Assert.notNull(permission, "Permission required");
    this.id = id;
    this.acl = acl;
    this.sid = sid;
    this.permission = permission;
    this.granting = granting;
    this.auditSuccess = auditSuccess;
    this.auditFailure = auditFailure;
  }
  
  public boolean equals(Object arg0)
  {
    if (!(arg0 instanceof AccessControlEntryImpl)) {
      return false;
    }
    AccessControlEntryImpl rhs = (AccessControlEntryImpl)arg0;
    if (this.acl == null)
    {
      if (rhs.getAcl() != null) {
        return false;
      }
    }
    else
    {
      if (rhs.getAcl() == null) {
        return false;
      }
      if (this.acl.getObjectIdentity() == null)
      {
        if (rhs.acl.getObjectIdentity() != null) {
          return false;
        }
      }
      else if (!this.acl.getObjectIdentity().equals(rhs.getAcl().getObjectIdentity())) {
        return false;
      }
    }
    if (this.id == null)
    {
      if (rhs.id != null) {
        return false;
      }
    }
    else
    {
      if (rhs.id == null) {
        return false;
      }
      if (!this.id.equals(rhs.id)) {
        return false;
      }
    }
    if ((this.auditFailure != rhs.isAuditFailure()) || (this.auditSuccess != rhs.isAuditSuccess()) || 
      (this.granting != rhs.isGranting()) || 
      (!this.permission.equals(rhs.getPermission())) || (!this.sid.equals(rhs.getSid()))) {
      return false;
    }
    return true;
  }
  
  public Acl getAcl()
  {
    return this.acl;
  }
  
  public Serializable getId()
  {
    return this.id;
  }
  
  public Permission getPermission()
  {
    return this.permission;
  }
  
  public Sid getSid()
  {
    return this.sid;
  }
  
  public boolean isAuditFailure()
  {
    return this.auditFailure;
  }
  
  public boolean isAuditSuccess()
  {
    return this.auditSuccess;
  }
  
  public boolean isGranting()
  {
    return this.granting;
  }
  
  void setAuditFailure(boolean auditFailure)
  {
    this.auditFailure = auditFailure;
  }
  
  void setAuditSuccess(boolean auditSuccess)
  {
    this.auditSuccess = auditSuccess;
  }
  
  void setPermission(Permission permission)
  {
    Assert.notNull(permission, "Permission required");
    this.permission = permission;
  }
  
  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("AccessControlEntryImpl[");
    sb.append("id: ").append(this.id).append("; ");
    sb.append("granting: ").append(this.granting).append("; ");
    sb.append("sid: ").append(this.sid).append("; ");
    sb.append("permission: ").append(this.permission).append("; ");
    sb.append("auditSuccess: ").append(this.auditSuccess).append("; ");
    sb.append("auditFailure: ").append(this.auditFailure);
    sb.append("]");
    
    return sb.toString();
  }
}
