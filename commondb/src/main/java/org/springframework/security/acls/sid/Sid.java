package org.springframework.security.acls.sid;

import java.io.Serializable;

public abstract interface Sid
  extends Serializable
{
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}
