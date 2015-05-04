package org.springframework.security.afterinvocation;

import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttribute;
import org.springframework.security.acls.Acl;
import org.springframework.security.acls.AclService;
import org.springframework.security.acls.NotFoundException;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.objectidentity.ObjectIdentityRetrievalStrategy;
import org.springframework.security.acls.objectidentity.ObjectIdentityRetrievalStrategyImpl;
import org.springframework.security.acls.sid.Sid;
import org.springframework.security.acls.sid.SidRetrievalStrategy;
import org.springframework.security.acls.sid.SidRetrievalStrategyImpl;
import org.springframework.util.Assert;

public abstract class AbstractAclProvider
  implements AfterInvocationProvider
{
  protected AclService aclService;
  protected Class processDomainObjectClass = Object.class;
  protected ObjectIdentityRetrievalStrategy objectIdentityRetrievalStrategy = new ObjectIdentityRetrievalStrategyImpl();
  protected SidRetrievalStrategy sidRetrievalStrategy = new SidRetrievalStrategyImpl();
  protected String processConfigAttribute;
  protected Permission[] requirePermission = { BasePermission.READ };
  
  public AbstractAclProvider(AclService aclService, String processConfigAttribute, Permission[] requirePermission)
  {
    Assert.hasText(processConfigAttribute, "A processConfigAttribute is mandatory");
    Assert.notNull(aclService, "An AclService is mandatory");
    if ((requirePermission == null) || (requirePermission.length == 0)) {
      throw new IllegalArgumentException("One or more requirePermission entries is mandatory");
    }
    this.aclService = aclService;
    this.processConfigAttribute = processConfigAttribute;
    this.requirePermission = requirePermission;
  }
  
  protected Class getProcessDomainObjectClass()
  {
    return this.processDomainObjectClass;
  }
  
  protected boolean hasPermission(Authentication authentication, Object domainObject)
  {
    ObjectIdentity objectIdentity = this.objectIdentityRetrievalStrategy.getObjectIdentity(domainObject);
    

    Sid[] sids = this.sidRetrievalStrategy.getSids(authentication);
    
    Acl acl = null;
    try
    {
      acl = this.aclService.readAclById(objectIdentity, sids);
      
      return acl.isGranted(this.requirePermission, sids, false);
    }
    catch (NotFoundException ignore) {}
    return false;
  }
  
  public void setObjectIdentityRetrievalStrategy(ObjectIdentityRetrievalStrategy objectIdentityRetrievalStrategy)
  {
    Assert.notNull(objectIdentityRetrievalStrategy, "ObjectIdentityRetrievalStrategy required");
    this.objectIdentityRetrievalStrategy = objectIdentityRetrievalStrategy;
  }
  
  protected void setProcessConfigAttribute(String processConfigAttribute)
  {
    Assert.hasText(processConfigAttribute, "A processConfigAttribute is mandatory");
    this.processConfigAttribute = processConfigAttribute;
  }
  
  public void setProcessDomainObjectClass(Class processDomainObjectClass)
  {
    Assert.notNull(processDomainObjectClass, "processDomainObjectClass cannot be set to null");
    this.processDomainObjectClass = processDomainObjectClass;
  }
  
  public void setSidRetrievalStrategy(SidRetrievalStrategy sidRetrievalStrategy)
  {
    Assert.notNull(sidRetrievalStrategy, "SidRetrievalStrategy required");
    this.sidRetrievalStrategy = sidRetrievalStrategy;
  }
  
  public boolean supports(ConfigAttribute attribute)
  {
    return this.processConfigAttribute.equals(attribute.getAttribute());
  }
  
  public boolean supports(Class clazz)
  {
    return true;
  }
}
