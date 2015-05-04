package org.springframework.security.afterinvocation;

import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.AuthorizationServiceException;
import org.springframework.security.ConfigAttribute;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.acls.AclService;
import org.springframework.security.acls.Permission;

public class AclEntryAfterInvocationCollectionFilteringProvider
  extends AbstractAclProvider
{
  protected static final Log logger = LogFactory.getLog(AclEntryAfterInvocationCollectionFilteringProvider.class);

  public AclEntryAfterInvocationCollectionFilteringProvider(AclService aclService, Permission[] requirePermission)
  {
    super(aclService, "AFTER_ACL_COLLECTION_READ", requirePermission);
  }

  public Object decide(Authentication authentication, Object object, ConfigAttributeDefinition config, Object returnedObject)
    throws AccessDeniedException
  {
    if (returnedObject == null)
    {
      if (logger.isDebugEnabled()) {
        logger.debug("Return object is null, skipping");
      }
      return null;
    }
    Iterator iter = config.getConfigAttributes().iterator();
    while (iter.hasNext())
    {
      ConfigAttribute attr = (ConfigAttribute)iter.next();
      if (supports(attr))
      {
        Filterer filterer;
        if ((returnedObject instanceof Collection))
        {
          filterer = new CollectionFilterer((Collection)returnedObject);
        }
        else
        {
          if (returnedObject.getClass().isArray()) {
            filterer = new ArrayFilterer((Object[])returnedObject);
          } else {
            throw new AuthorizationServiceException("A Collection or an array (or null) was required as the returnedObject, but the returnedObject was: " +
              returnedObject);
          }
        }
        Iterator collectionIter = filterer.iterator();
        while (collectionIter.hasNext())
        {
          Object domainObject = collectionIter.next();
          if ((domainObject != null) && (getProcessDomainObjectClass().isAssignableFrom(domainObject.getClass()))) {
            if (!hasPermission(authentication, domainObject))
            {
              filterer.remove(domainObject);
              if (logger.isDebugEnabled()) {
                logger.debug("Principal is NOT authorised for element: " + domainObject);
              }
            }
          }
        }
        return filterer.getFilteredObject();
      }
    }
    return returnedObject;
  }
}
