package org.springframework.security.acls.domain;

import org.springframework.security.acls.AccessControlEntry;

public abstract interface AuditLogger
{
  public abstract void logIfNeeded(boolean paramBoolean, AccessControlEntry paramAccessControlEntry);
}
