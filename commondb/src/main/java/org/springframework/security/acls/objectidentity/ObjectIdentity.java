package org.springframework.security.acls.objectidentity;

import java.io.Serializable;

public abstract interface ObjectIdentity
  extends Serializable
{
  public abstract boolean equals(Object paramObject);
  
  public abstract Serializable getIdentifier();
  
  public abstract Class getJavaType();
  
  public abstract int hashCode();
}
