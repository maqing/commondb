package org.springframework.security.acls;

public abstract interface AuditableAccessControlEntry
  extends AccessControlEntry
{
  public abstract boolean isAuditFailure();
  
  public abstract boolean isAuditSuccess();
}
