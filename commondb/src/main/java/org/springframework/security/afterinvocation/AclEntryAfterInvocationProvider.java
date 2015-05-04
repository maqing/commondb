package org.springframework.security.afterinvocation;

import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttribute;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.SpringSecurityMessageSource;
import org.springframework.security.acls.AclService;
import org.springframework.security.acls.Permission;

public class AclEntryAfterInvocationProvider
  extends AbstractAclProvider
  implements MessageSourceAware
{
  protected static final Log logger = LogFactory.getLog(AclEntryAfterInvocationProvider.class);
  protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
  
  public AclEntryAfterInvocationProvider(AclService aclService, Permission[] requirePermission)
  {
    super(aclService, "AFTER_ACL_READ", requirePermission);
  }
  
  public Object decide(Authentication authentication, Object object, ConfigAttributeDefinition config, Object returnedObject)
    throws AccessDeniedException
  {
    Iterator iter = config.getConfigAttributes().iterator();
    if (returnedObject == null)
    {
      if (logger.isDebugEnabled()) {
        logger.debug("Return object is null, skipping");
      }
      return null;
    }
    if (!getProcessDomainObjectClass().isAssignableFrom(returnedObject.getClass()))
    {
      if (logger.isDebugEnabled()) {
        logger.debug("Return object is not applicable for this provider, skipping");
      }
      return returnedObject;
    }
    while (iter.hasNext())
    {
      ConfigAttribute attr = (ConfigAttribute)iter.next();
      if (supports(attr))
      {
        if (hasPermission(authentication, returnedObject)) {
          return returnedObject;
        }
        logger.debug("Denying access");
        
        throw new AccessDeniedException(this.messages
        
          .getMessage("BasicAclEntryAfterInvocationProvider.noPermission", new Object[] { authentication.getName(), returnedObject }, "Authentication {0} has NO permissions to the domain object {1}"));
      }
    }
    return returnedObject;
  }
  
  public void setMessageSource(MessageSource messageSource)
  {
    this.messages = new MessageSourceAccessor(messageSource);
  }
}
