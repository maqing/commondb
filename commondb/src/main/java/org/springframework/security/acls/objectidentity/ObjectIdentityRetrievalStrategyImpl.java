package org.springframework.security.acls.objectidentity;

public class ObjectIdentityRetrievalStrategyImpl
  implements ObjectIdentityRetrievalStrategy
{
  public ObjectIdentity getObjectIdentity(Object domainObject)
  {
    return new ObjectIdentityImpl(domainObject);
  }
}
