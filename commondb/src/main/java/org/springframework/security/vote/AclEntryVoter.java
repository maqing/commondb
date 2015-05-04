package org.springframework.security.vote;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.Authentication;
import org.springframework.security.AuthorizationServiceException;
import org.springframework.security.ConfigAttribute;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.acls.Acl;
import org.springframework.security.acls.AclService;
import org.springframework.security.acls.NotFoundException;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.objectidentity.ObjectIdentityRetrievalStrategy;
import org.springframework.security.acls.objectidentity.ObjectIdentityRetrievalStrategyImpl;
import org.springframework.security.acls.sid.Sid;
import org.springframework.security.acls.sid.SidRetrievalStrategy;
import org.springframework.security.acls.sid.SidRetrievalStrategyImpl;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class AclEntryVoter
  extends AbstractAclVoter
{
  private static final Log logger = LogFactory.getLog(AclEntryVoter.class);
  private AclService aclService;
  private ObjectIdentityRetrievalStrategy objectIdentityRetrievalStrategy = new ObjectIdentityRetrievalStrategyImpl();
  private SidRetrievalStrategy sidRetrievalStrategy = new SidRetrievalStrategyImpl();
  private String internalMethod;
  private String processConfigAttribute;
  private Permission[] requirePermission;

  public AclEntryVoter(AclService aclService, String processConfigAttribute, Permission[] requirePermission)
  {
    Assert.notNull(processConfigAttribute, "A processConfigAttribute is mandatory");
    Assert.notNull(aclService, "An AclService is mandatory");
    if ((requirePermission == null) || (requirePermission.length == 0)) {
      throw new IllegalArgumentException("One or more requirePermission entries is mandatory");
    }
    this.aclService = aclService;
    this.processConfigAttribute = processConfigAttribute;
    this.requirePermission = requirePermission;
  }

  protected String getInternalMethod()
  {
    return this.internalMethod;
  }

  public void setInternalMethod(String internalMethod)
  {
    this.internalMethod = internalMethod;
  }

  protected String getProcessConfigAttribute()
  {
    return this.processConfigAttribute;
  }

  public void setObjectIdentityRetrievalStrategy(ObjectIdentityRetrievalStrategy objectIdentityRetrievalStrategy)
  {
    Assert.notNull(objectIdentityRetrievalStrategy, "ObjectIdentityRetrievalStrategy required");
    this.objectIdentityRetrievalStrategy = objectIdentityRetrievalStrategy;
  }

  public void setSidRetrievalStrategy(SidRetrievalStrategy sidRetrievalStrategy)
  {
    Assert.notNull(sidRetrievalStrategy, "SidRetrievalStrategy required");
    this.sidRetrievalStrategy = sidRetrievalStrategy;
  }

  public boolean supports(ConfigAttribute attribute)
  {
    if ((attribute.getAttribute() != null) && (attribute.getAttribute().equals(getProcessConfigAttribute()))) {
      return true;
    }
    return false;
  }

  public int vote(Authentication authentication, Object object, ConfigAttributeDefinition config)
  {
    Iterator iter = config.getConfigAttributes().iterator();
    while (iter.hasNext())
    {
      ConfigAttribute attr = (ConfigAttribute)iter.next();
      if (supports(attr))
      {
        Object domainObject = getDomainObjectInstance(object);
        if (domainObject == null)
        {
          if (logger.isDebugEnabled()) {
            logger.debug("Voting to abstain - domainObject is null");
          }
          return 0;
        }
        if (StringUtils.hasText(this.internalMethod)) {
          try
          {
            Class clazz = domainObject.getClass();
            Method method = clazz.getMethod(this.internalMethod, new Class[0]);
            domainObject = method.invoke(domainObject, new Object[0]);
          }
          catch (NoSuchMethodException nsme)
          {
            throw new AuthorizationServiceException("Object of class '" + domainObject.getClass() +
              "' does not provide the requested internalMethod: " + this.internalMethod);
          }
          catch (IllegalAccessException iae)
          {
            logger.debug("IllegalAccessException", iae);

            throw new AuthorizationServiceException("Problem invoking internalMethod: " + this.internalMethod +
              " for object: " + domainObject);
          }
          catch (InvocationTargetException ite)
          {
            logger.debug("InvocationTargetException", ite);

            throw new AuthorizationServiceException("Problem invoking internalMethod: " + this.internalMethod +
              " for object: " + domainObject);
          }
        }
        ObjectIdentity objectIdentity = this.objectIdentityRetrievalStrategy.getObjectIdentity(domainObject);


        Sid[] sids = this.sidRetrievalStrategy.getSids(authentication);
        try
        {
          Acl acl = this.aclService.readAclById(objectIdentity, sids);
          if (acl.isGranted(this.requirePermission, sids, false))
          {
            if (logger.isDebugEnabled()) {
              logger.debug("Voting to grant access");
            }
            return 1;
          }
          if (logger.isDebugEnabled()) {
            logger.debug(
              "Voting to deny access - ACLs returned, but insufficient permissions for this principal");
          }
          return -1;
        }
        catch (NotFoundException nfe)
        {
          if (logger.isDebugEnabled()) {
            logger.debug("Voting to deny access - no ACLs apply for this principal");
          }
          return -1;
        }
      }
    }
    return 0;
  }
}
