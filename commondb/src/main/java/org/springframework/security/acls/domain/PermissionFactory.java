package org.springframework.security.acls.domain;

import org.springframework.security.acls.Permission;

public abstract interface PermissionFactory
{
  public abstract Permission buildFromMask(int paramInt);
}
