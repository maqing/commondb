package org.springframework.security.acls.objectidentity;

public abstract interface ObjectIdentityRetrievalStrategy
{
  public abstract ObjectIdentity getObjectIdentity(Object paramObject);
}
