package org.springframework.security.acls.jdbc;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.Authentication;
import org.springframework.security.acls.AccessControlEntry;
import org.springframework.security.acls.Acl;
import org.springframework.security.acls.AlreadyExistsException;
import org.springframework.security.acls.ChildrenExistException;
import org.springframework.security.acls.MutableAcl;
import org.springframework.security.acls.MutableAclService;
import org.springframework.security.acls.NotFoundException;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.domain.AccessControlEntryImpl;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.objectidentity.ObjectIdentityImpl;
import org.springframework.security.acls.sid.GrantedAuthoritySid;
import org.springframework.security.acls.sid.PrincipalSid;
import org.springframework.security.acls.sid.Sid;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

public class JdbcMutableAclService
  extends JdbcAclService
  implements MutableAclService
{
  private boolean foreignKeysInDatabase = true;
  private AclCache aclCache;
  private String deleteEntryByObjectIdentityForeignKey = "delete from acl_entry where acl_object_identity=?";
  private String deleteObjectIdentityByPrimaryKey = "delete from acl_object_identity where id=?";
  private String classIdentityQuery = "select max(ID) from acl_class";
  private String sidIdentityQuery = "select max(ID) from acl_sid";
  private String insertClass = "insert into acl_class (class) values (?)";
  private String insertEntry = "insert into acl_entry (acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)values (?, ?, ?, ?, ?, ?, ?)";
  private String insertObjectIdentity = "insert into acl_object_identity (object_id_class, object_id_identity, owner_sid, entries_inheriting) values (?, ?, ?, ?)";
  private String insertSid = "insert into acl_sid (principal, sid) values (?, ?)";
  private String selectClassPrimaryKey = "select id from acl_class where class=?";
  private String selectObjectIdentityPrimaryKey = "select acl_object_identity.id from acl_object_identity, acl_class where acl_object_identity.object_id_class = acl_class.id and acl_class.class=? and acl_object_identity.object_id_identity = ?";
  private String selectSidPrimaryKey = "select id from acl_sid where principal=? and sid=?";
  private String updateObjectIdentity = "update acl_object_identity set parent_object = ?, owner_sid = ?, entries_inheriting = ? where id = ?";
  
  public JdbcMutableAclService(DataSource dataSource, LookupStrategy lookupStrategy, AclCache aclCache)
  {
    super(dataSource, lookupStrategy);
    Assert.notNull(aclCache, "AclCache required");
    this.aclCache = aclCache;
  }
  
  public MutableAcl createAcl(ObjectIdentity objectIdentity)
    throws AlreadyExistsException
  {
    Assert.notNull(objectIdentity, "Object Identity required");
    if (retrieveObjectIdentityPrimaryKey(objectIdentity) != null) {
      throw new AlreadyExistsException("Object identity '" + objectIdentity + "' already exists");
    }
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    PrincipalSid sid = new PrincipalSid(auth);
    

    createObjectIdentity(objectIdentity, sid);
    

    Acl acl = readAclById(objectIdentity);
    Assert.isInstanceOf(MutableAcl.class, acl, "MutableAcl should be been returned");
    
    return (MutableAcl)acl;
  }
  
  protected void createEntries(final MutableAcl acl)
  {
    this.jdbcTemplate.batchUpdate(this.insertEntry, 
      new BatchPreparedStatementSetter()
      {
        public int getBatchSize()
        {
          return acl.getEntries().length;
        }
        
        public void setValues(PreparedStatement stmt, int i)
          throws SQLException
        {
          AccessControlEntry entry_ = (AccessControlEntry)Array.get(acl.getEntries(), i);
          Assert.isTrue(entry_ instanceof AccessControlEntryImpl, "Unknown ACE class");
          
          AccessControlEntryImpl entry = (AccessControlEntryImpl)entry_;
          
          stmt.setLong(1, ((Long)acl.getId()).longValue());
          stmt.setInt(2, i);
          stmt.setLong(3, JdbcMutableAclService.this.createOrRetrieveSidPrimaryKey(entry.getSid(), true).longValue());
          stmt.setInt(4, entry.getPermission().getMask());
          stmt.setBoolean(5, entry.isGranting());
          stmt.setBoolean(6, entry.isAuditSuccess());
          stmt.setBoolean(7, entry.isAuditFailure());
        }
      });
  }
  
  protected void createObjectIdentity(ObjectIdentity object, Sid owner)
  {
    Long sidId = createOrRetrieveSidPrimaryKey(owner, true);
    Long classId = createOrRetrieveClassPrimaryKey(object.getJavaType(), true);
    this.jdbcTemplate.update(this.insertObjectIdentity, 
      new Object[] { classId, object.getIdentifier().toString(), sidId, new Boolean(true) });
  }
  
  protected Long createOrRetrieveClassPrimaryKey(Class clazz, boolean allowCreate)
  {
    List classIds = this.jdbcTemplate.queryForList(this.selectClassPrimaryKey, new Object[] { clazz.getName() }, Long.class);
    Long classId = null;
    if (classIds.isEmpty())
    {
      if (allowCreate)
      {
        classId = null;
        this.jdbcTemplate.update(this.insertClass, new Object[] { clazz.getName() });
        Assert.isTrue(TransactionSynchronizationManager.isSynchronizationActive(), 
          "Transaction must be running");
        classId = new Long(this.jdbcTemplate.queryForLong(this.classIdentityQuery));
      }
    }
    else {
      classId = (Long)classIds.iterator().next();
    }
    return classId;
  }
  
  protected Long createOrRetrieveSidPrimaryKey(Sid sid, boolean allowCreate)
  {
    Assert.notNull(sid, "Sid required");
    
    String sidName = null;
    boolean principal = true;
    if ((sid instanceof PrincipalSid))
    {
      sidName = ((PrincipalSid)sid).getPrincipal();
    }
    else if ((sid instanceof GrantedAuthoritySid))
    {
      sidName = ((GrantedAuthoritySid)sid).getGrantedAuthority();
      principal = false;
    }
    else
    {
      throw new IllegalArgumentException("Unsupported implementation of Sid");
    }
    List sidIds = this.jdbcTemplate.queryForList(this.selectSidPrimaryKey, new Object[] { new Boolean(principal), sidName }, 
      Long.class);
    Long sidId = null;
    if (sidIds.isEmpty())
    {
      if (allowCreate)
      {
        sidId = null;
        this.jdbcTemplate.update(this.insertSid, new Object[] { new Boolean(principal), sidName });
        Assert.isTrue(TransactionSynchronizationManager.isSynchronizationActive(), 
          "Transaction must be running");
        sidId = new Long(this.jdbcTemplate.queryForLong(this.sidIdentityQuery));
      }
    }
    else {
      sidId = (Long)sidIds.iterator().next();
    }
    return sidId;
  }
  
  public void deleteAcl(ObjectIdentity objectIdentity, boolean deleteChildren)
    throws ChildrenExistException
  {
    Assert.notNull(objectIdentity, "Object Identity required");
    Assert.notNull(objectIdentity.getIdentifier(), "Object Identity doesn't provide an identifier");
    if (deleteChildren)
    {
      ObjectIdentity[] children = findChildren(objectIdentity);
      if (children != null) {
        for (int i = 0; i < children.length; i++) {
          deleteAcl(children[i], true);
        }
      }
    }
    else if (!this.foreignKeysInDatabase)
    {
      ObjectIdentity[] children = findChildren(objectIdentity);
      if (children != null) {
        throw new ChildrenExistException("Cannot delete '" + objectIdentity + "' (has " + children.length + 
          " children)");
      }
    }
    Long oidPrimaryKey = retrieveObjectIdentityPrimaryKey(objectIdentity);
    

    deleteEntries(oidPrimaryKey);
    

    deleteObjectIdentity(oidPrimaryKey);
    

    this.aclCache.evictFromCache(objectIdentity);
  }
  
  protected void deleteEntries(Long oidPrimaryKey)
  {
    this.jdbcTemplate.update(this.deleteEntryByObjectIdentityForeignKey, 
      new Object[] { oidPrimaryKey });
  }
  
  protected void deleteObjectIdentity(Long oidPrimaryKey)
  {
    this.jdbcTemplate.update(this.deleteObjectIdentityByPrimaryKey, new Object[] { oidPrimaryKey });
  }
  
  protected Long retrieveObjectIdentityPrimaryKey(ObjectIdentity oid)
  {
    try
    {
      return new Long(this.jdbcTemplate
        .queryForLong(this.selectObjectIdentityPrimaryKey, new Object[] { oid.getJavaType().getName(), oid.getIdentifier() }));
    }
    catch (DataAccessException notFound) {}
    return null;
  }
  
  public MutableAcl updateAcl(MutableAcl acl)
    throws NotFoundException
  {
    Assert.notNull(acl.getId(), "Object Identity doesn't provide an identifier");
    

    deleteEntries(retrieveObjectIdentityPrimaryKey(acl.getObjectIdentity()));
    

    createEntries(acl);
    

    updateObjectIdentity(acl);
    

    clearCacheIncludingChildren(acl.getObjectIdentity());
    

    return (MutableAcl)super.readAclById(acl.getObjectIdentity());
  }
  
  private void clearCacheIncludingChildren(ObjectIdentity objectIdentity)
  {
    Assert.notNull(objectIdentity, "ObjectIdentity required");
    ObjectIdentity[] children = findChildren(objectIdentity);
    if (children != null) {
      for (int i = 0; i < children.length; i++) {
        clearCacheIncludingChildren(children[i]);
      }
    }
    this.aclCache.evictFromCache(objectIdentity);
  }
  
  protected void updateObjectIdentity(MutableAcl acl)
  {
    Long parentId = null;
    if (acl.getParentAcl() != null)
    {
      Assert.isInstanceOf(ObjectIdentityImpl.class, acl.getParentAcl().getObjectIdentity(), 
        "Implementation only supports ObjectIdentityImpl");
      
      ObjectIdentityImpl oii = (ObjectIdentityImpl)acl.getParentAcl().getObjectIdentity();
      parentId = retrieveObjectIdentityPrimaryKey(oii);
    }
    Assert.notNull(acl.getOwner(), "Owner is required in this implementation");
    
    Long ownerSid = createOrRetrieveSidPrimaryKey(acl.getOwner(), true);
    int count = this.jdbcTemplate.update(this.updateObjectIdentity, 
      new Object[] { parentId, ownerSid, new Boolean(acl.isEntriesInheriting()), acl.getId() });
    if (count != 1) {
      throw new NotFoundException("Unable to locate ACL to update");
    }
  }
  
  public void setClassIdentityQuery(String identityQuery)
  {
    Assert.hasText(identityQuery, "New identity query is required");
    this.classIdentityQuery = identityQuery;
  }
  
  public void setSidIdentityQuery(String identityQuery)
  {
    Assert.hasText(identityQuery, "New identity query is required");
    this.sidIdentityQuery = identityQuery;
  }
  
  public void setForeignKeysInDatabase(boolean foreignKeysInDatabase)
  {
    this.foreignKeysInDatabase = foreignKeysInDatabase;
  }
}
