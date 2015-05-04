package org.springframework.security.acls;

public abstract interface AuditableAcl
  extends MutableAcl
{
  public abstract void updateAuditing(int paramInt, boolean paramBoolean1, boolean paramBoolean2);
}
