package org.springframework.security.acls.jdbc;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.acls.AccessControlEntry;
import org.springframework.security.acls.Acl;
import org.springframework.security.acls.MutableAcl;
import org.springframework.security.acls.NotFoundException;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.UnloadedSidException;
import org.springframework.security.acls.domain.AccessControlEntryImpl;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AclImpl;
import org.springframework.security.acls.domain.AuditLogger;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.objectidentity.ObjectIdentityImpl;
import org.springframework.security.acls.sid.GrantedAuthoritySid;
import org.springframework.security.acls.sid.PrincipalSid;
import org.springframework.security.acls.sid.Sid;
import org.springframework.security.util.FieldUtils;
import org.springframework.util.Assert;

public final class BasicLookupStrategy
  implements LookupStrategy
{
  private AclAuthorizationStrategy aclAuthorizationStrategy;
  private AclCache aclCache;
  private AuditLogger auditLogger;
  private JdbcTemplate jdbcTemplate;
  private int batchSize = 50;

  public BasicLookupStrategy(DataSource dataSource, AclCache aclCache, AclAuthorizationStrategy aclAuthorizationStrategy, AuditLogger auditLogger)
  {
    Assert.notNull(dataSource, "DataSource required");
    Assert.notNull(aclCache, "AclCache required");
    Assert.notNull(aclAuthorizationStrategy, "AclAuthorizationStrategy required");
    Assert.notNull(auditLogger, "AuditLogger required");
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.aclCache = aclCache;
    this.aclAuthorizationStrategy = aclAuthorizationStrategy;
    this.auditLogger = auditLogger;
  }

  private static String computeRepeatingSql(String repeatingSql, int requiredRepetitions)
  {
    Assert.isTrue(requiredRepetitions >= 1, "Must be => 1");

    String startSql = "select acl_object_identity.object_id_identity, acl_entry.ace_order,  acl_object_identity.id as acl_id, acl_object_identity.parent_object, acl_object_identity.entries_inheriting, acl_entry.id as ace_id, acl_entry.mask,  acl_entry.granting,  acl_entry.audit_success, acl_entry.audit_failure,  acl_sid.principal as ace_principal, acl_sid.sid as ace_sid,  acli_sid.principal as acl_principal, acli_sid.sid as acl_sid, acl_class.class from acl_object_identity left join acl_sid acli_sid on  acli_sid.id = acl_object_identity.owner_sid left join acl_class on acl_class.id = acl_object_identity.object_id_class   left join acl_entry on acl_object_identity.id = acl_entry.acl_object_identity left join acl_sid on acl_entry.sid = acl_sid.id  where ( ";


    String endSql = ") order by acl_object_identity.object_id_identity asc, acl_entry.ace_order asc";


    StringBuffer sqlStringBuffer = new StringBuffer();
    sqlStringBuffer.append(startSql);
    for (int i = 1; i <= requiredRepetitions; i++)
    {
      sqlStringBuffer.append(repeatingSql);
      if (i != requiredRepetitions) {
        sqlStringBuffer.append(" or ");
      }
    }
    sqlStringBuffer.append(endSql);

    return sqlStringBuffer.toString();
  }

  private AclImpl convert(Map inputMap, Long currentIdentity)
  {
    Assert.notEmpty(inputMap, "InputMap required");
    Assert.notNull(currentIdentity, "CurrentIdentity required");


    Acl uncastAcl = (Acl)inputMap.get(currentIdentity);
    Assert.isInstanceOf(AclImpl.class, uncastAcl, "The inputMap contained a non-AclImpl");

    AclImpl inputAcl = (AclImpl)uncastAcl;

    Acl parent = inputAcl.getParentAcl();
    if ((parent != null) && ((parent instanceof StubAclParent)))
    {
      StubAclParent stubAclParent = (StubAclParent)parent;
      parent = convert(inputMap, stubAclParent.getId());
    }
    AclImpl result = new AclImpl(inputAcl.getObjectIdentity(), (Long)inputAcl.getId(), this.aclAuthorizationStrategy,
      this.auditLogger, parent, null, inputAcl.isEntriesInheriting(), inputAcl.getOwner());


    Field fieldAces = FieldUtils.getField(AclImpl.class, "aces");
    Field fieldAcl = FieldUtils.getField(AccessControlEntryImpl.class, "acl");
    try
    {
      fieldAces.setAccessible(true);
      fieldAcl.setAccessible(true);


      Iterator i = ((List)fieldAces.get(inputAcl)).iterator();


      List acesNew = new ArrayList();
      while (i.hasNext())
      {
        AccessControlEntryImpl ace = (AccessControlEntryImpl)i.next();
        fieldAcl.set(ace, result);
        acesNew.add(ace);
      }
      fieldAces.set(result, acesNew);
    }
    catch (IllegalAccessException ex)
    {
      throw new IllegalStateException("Could not obtain or set AclImpl or AccessControlEntryImpl fields");
    }
    return result;
  }

  private void convertCurrentResultIntoObject(Map acls, ResultSet rs)
    throws SQLException
  {
    Long id = new Long(rs.getLong("acl_id"));


    AclImpl acl = (AclImpl)acls.get(id);
    if (acl == null)
    {
      ObjectIdentity objectIdentity = new ObjectIdentityImpl(rs.getString("class"),
        new Long(rs.getLong("object_id_identity")));

      Acl parentAcl = null;
      long parentAclId = rs.getLong("parent_object");
      if (parentAclId != 0L) {
        parentAcl = new StubAclParent(new Long(parentAclId));
      }
      boolean entriesInheriting = rs.getBoolean("entries_inheriting");
      Sid owner;
      if (rs.getBoolean("acl_principal")) {
        owner = new PrincipalSid(rs.getString("acl_sid"));
      } else {
        owner = new GrantedAuthoritySid(rs.getString("acl_sid"));
      }
      acl = new AclImpl(objectIdentity, id, this.aclAuthorizationStrategy, this.auditLogger, parentAcl, null,
        entriesInheriting, owner);
      acls.put(id, acl);
    }
    if (rs.getString("ace_sid") != null)
    {
      Long aceId = new Long(rs.getLong("ace_id"));
      Sid recipient;
      if (rs.getBoolean("ace_principal")) {
        recipient = new PrincipalSid(rs.getString("ace_sid"));
      } else {
        recipient = new GrantedAuthoritySid(rs.getString("ace_sid"));
      }
      int mask = rs.getInt("mask");
      Permission permission = convertMaskIntoPermission(mask);
      boolean granting = rs.getBoolean("granting");
      boolean auditSuccess = rs.getBoolean("audit_success");
      boolean auditFailure = rs.getBoolean("audit_failure");

      AccessControlEntryImpl ace = new AccessControlEntryImpl(aceId, acl, recipient, permission, granting,
        auditSuccess, auditFailure);

      Field acesField = FieldUtils.getField(AclImpl.class, "aces");
      List aces;
      try
      {
        acesField.setAccessible(true);
        aces = (List)acesField.get(acl);
      }
      catch (IllegalAccessException ex)
      {
        throw new IllegalStateException("Could not obtain AclImpl.ace field: cause[" + ex.getMessage() + "]");
      }
      if (!aces.contains(ace)) {
        aces.add(ace);
      }
    }
  }

  protected Permission convertMaskIntoPermission(int mask)
  {
    return BasePermission.buildFromMask(mask);
  }

  private Map lookupObjectIdentities(final ObjectIdentity[] objectIdentities, Sid[] sids)
  {
    Assert.notEmpty(objectIdentities, "Must provide identities to lookup");

    Map acls = new HashMap();



    String sql = computeRepeatingSql("(acl_object_identity.object_id_identity = ? and acl_class.class = ?)",
      objectIdentities.length);

    Set parentsToLookup = (Set)this.jdbcTemplate.query(sql,
      new PreparedStatementSetter(){
        public void setValues(PreparedStatement ps)
          throws SQLException
        {
          for (int i = 0; i < objectIdentities.length; i++)
          {
            String javaType = objectIdentities[i].getJavaType().getName();


            String identifier = objectIdentities[i].getIdentifier().toString();
            long id = Long.valueOf(identifier).longValue();


            ps.setLong(2 * i + 1, id);
            ps.setString(2 * i + 2, javaType);
          }
        }
      }, new ProcessResultSet(acls, sids));
    if (parentsToLookup.size() > 0) {
      lookupPrimaryKeys(acls, parentsToLookup, sids);
    }
    Map resultMap = new HashMap();
    Iterator iter = acls.values().iterator();
    while (iter.hasNext())
    {
      Acl inputAcl = (Acl)iter.next();
      Assert.isInstanceOf(AclImpl.class, inputAcl, "Map should have contained an AclImpl");
      Assert.isInstanceOf(Long.class, ((AclImpl)inputAcl).getId(), "Acl.getId() must be Long");

      Acl result = convert(acls, (Long)((AclImpl)inputAcl).getId());
      resultMap.put(result.getObjectIdentity(), result);
    }
    return resultMap;
  }

  private void lookupPrimaryKeys(Map acls, final Set findNow, Sid[] sids)
  {
    Assert.notNull(acls, "ACLs are required");
    Assert.notEmpty(findNow, "Items to find now required");

    String sql = computeRepeatingSql("(acl_object_identity.id = ?)", findNow.size());

    Set parentsToLookup = (Set)this.jdbcTemplate.query(sql,
      new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps)
          throws SQLException
        {
          Iterator iter = findNow.iterator();
          int i = 0;
          while (iter.hasNext())
          {
            i++;
            ps.setLong(i, ((Long)iter.next()).longValue());
          }
        }
      }, new ProcessResultSet(acls, sids));
    if (parentsToLookup.size() > 0) {
      lookupPrimaryKeys(acls, parentsToLookup, sids);
    }
  }

  public Map readAclsById(ObjectIdentity[] objects, Sid[] sids)
  {
    Assert.isTrue(this.batchSize >= 1, "BatchSize must be >= 1");
    Assert.notEmpty(objects, "Objects to lookup required");


    Map result = new HashMap();

    Set currentBatchToLoad = new HashSet();
    for (int i = 0; i < objects.length; i++)
    {
      boolean aclFound = false;
      if (result.containsKey(objects[i])) {
        aclFound = true;
      }
      if (!aclFound)
      {
        Acl acl = this.aclCache.getFromCache(objects[i]);
        if (acl != null) {
          if (acl.isSidLoaded(sids))
          {
            result.put(acl.getObjectIdentity(), acl);
            aclFound = true;
          }
          else
          {
            throw new IllegalStateException(
              "Error: SID-filtered element detected when implementation does not perform SID filtering - have you added something to the cache manually?");
          }
        }
      }
      if (!aclFound) {
        currentBatchToLoad.add(objects[i]);
      }
      if (((currentBatchToLoad.size() == this.batchSize) || (i + 1 == objects.length)) &&
        (currentBatchToLoad.size() > 0))
      {
        Map loadedBatch = lookupObjectIdentities((ObjectIdentity[])currentBatchToLoad.toArray(new ObjectIdentity[0]), sids);


        result.putAll(loadedBatch);


        Iterator loadedAclIterator = loadedBatch.values().iterator();
        while (loadedAclIterator.hasNext()) {
          this.aclCache.putInCache((AclImpl)loadedAclIterator.next());
        }
        currentBatchToLoad.clear();
      }
    }
    return result;
  }

  public void setBatchSize(int batchSize)
  {
    this.batchSize = batchSize;
  }

  private class ProcessResultSet
    implements ResultSetExtractor
  {
    private Map acls;
    private Sid[] sids;

    public ProcessResultSet(Map acls, Sid[] sids)
    {
      Assert.notNull(acls, "ACLs cannot be null");
      this.acls = acls;
      this.sids = sids;
    }

    public Object extractData(ResultSet rs)
      throws SQLException, DataAccessException
    {
      Set parentIdsToLookup = new HashSet();
      while (rs.next())
      {
        BasicLookupStrategy.this.convertCurrentResultIntoObject(this.acls, rs);


        long parentId = rs.getLong("parent_object");
        if (parentId != 0L) {
          if (!this.acls.containsKey(new Long(parentId)))
          {
            MutableAcl cached = BasicLookupStrategy.this.aclCache.getFromCache(new Long(parentId));
            if ((cached == null) || (!cached.isSidLoaded(this.sids))) {
              parentIdsToLookup.add(new Long(parentId));
            } else {
              this.acls.put(cached.getId(), cached);
            }
          }
        }
      }
      return parentIdsToLookup;
    }
  }

  private class StubAclParent
    implements Acl
  {
    private Long id;

    public StubAclParent(Long id)
    {
      this.id = id;
    }

    public AccessControlEntry[] getEntries()
    {
      throw new UnsupportedOperationException("Stub only");
    }

    public Long getId()
    {
      return this.id;
    }

    public ObjectIdentity getObjectIdentity()
    {
      throw new UnsupportedOperationException("Stub only");
    }

    public Sid getOwner()
    {
      throw new UnsupportedOperationException("Stub only");
    }

    public Acl getParentAcl()
    {
      throw new UnsupportedOperationException("Stub only");
    }

    public boolean isEntriesInheriting()
    {
      throw new UnsupportedOperationException("Stub only");
    }

    public boolean isGranted(Permission[] permission, Sid[] sids, boolean administrativeMode)
      throws NotFoundException, UnloadedSidException
    {
      throw new UnsupportedOperationException("Stub only");
    }

    public boolean isSidLoaded(Sid[] sids)
    {
      throw new UnsupportedOperationException("Stub only");
    }
  }
}
