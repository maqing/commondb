package org.springframework.security.acls;

import org.springframework.security.acls.sid.Sid;

public abstract interface OwnershipAcl
  extends MutableAcl
{
  public abstract void setOwner(Sid paramSid);
}
