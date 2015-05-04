package org.springframework.security.acls.jdbc;

import java.io.Serializable;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.security.acls.MutableAcl;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AclImpl;
import org.springframework.security.acls.domain.AuditLogger;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.util.FieldUtils;
import org.springframework.util.Assert;

public class EhCacheBasedAclCache
  implements AclCache
{
  private Ehcache cache;
  private AuditLogger auditLogger;
  private AclAuthorizationStrategy aclAuthorizationStrategy;
  
  public EhCacheBasedAclCache(Ehcache cache)
  {
    Assert.notNull(cache, "Cache required");
    this.cache = cache;
  }
  
  public void evictFromCache(Serializable pk)
  {
    Assert.notNull(pk, "Primary key (identifier) required");
    
    MutableAcl acl = getFromCache(pk);
    if (acl != null)
    {
      this.cache.remove(acl.getId());
      this.cache.remove(acl.getObjectIdentity());
    }
  }
  
  public void evictFromCache(ObjectIdentity objectIdentity)
  {
    Assert.notNull(objectIdentity, "ObjectIdentity required");
    
    MutableAcl acl = getFromCache(objectIdentity);
    if (acl != null)
    {
      this.cache.remove(acl.getId());
      this.cache.remove(acl.getObjectIdentity());
    }
  }
  
  public MutableAcl getFromCache(ObjectIdentity objectIdentity)
  {
    Assert.notNull(objectIdentity, "ObjectIdentity required");
    
    Element element = null;
    try
    {
      element = this.cache.get(objectIdentity);
    }
    catch (CacheException localCacheException) {}
    if (element == null) {
      return null;
    }
    return initializeTransientFields((MutableAcl)element.getValue());
  }
  
  public MutableAcl getFromCache(Serializable pk)
  {
    Assert.notNull(pk, "Primary key (identifier) required");
    
    Element element = null;
    try
    {
      element = this.cache.get(pk);
    }
    catch (CacheException localCacheException) {}
    if (element == null) {
      return null;
    }
    return initializeTransientFields((MutableAcl)element.getValue());
  }
  
  public void putInCache(MutableAcl acl)
  {
    Assert.notNull(acl, "Acl required");
    Assert.notNull(acl.getObjectIdentity(), "ObjectIdentity required");
    Assert.notNull(acl.getId(), "ID required");
    if ((this.aclAuthorizationStrategy == null) && 
      ((acl instanceof AclImpl)))
    {
      this.aclAuthorizationStrategy = ((AclAuthorizationStrategy)FieldUtils.getProtectedFieldValue("aclAuthorizationStrategy", acl));
      this.auditLogger = ((AuditLogger)FieldUtils.getProtectedFieldValue("auditLogger", acl));
    }
    if ((acl.getParentAcl() != null) && ((acl.getParentAcl() instanceof MutableAcl))) {
      putInCache((MutableAcl)acl.getParentAcl());
    }
    this.cache.put(new Element(acl.getObjectIdentity(), acl));
    this.cache.put(new Element(acl.getId(), acl));
  }
  
  private MutableAcl initializeTransientFields(MutableAcl value)
  {
    if ((value instanceof AclImpl))
    {
      FieldUtils.setProtectedFieldValue("aclAuthorizationStrategy", value, this.aclAuthorizationStrategy);
      FieldUtils.setProtectedFieldValue("auditLogger", value, this.auditLogger);
    }
    return value;
  }
}
