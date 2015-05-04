package org.springframework.security.acls.domain;

import org.springframework.security.acls.Acl;

public abstract interface AclAuthorizationStrategy
{
  public static final int CHANGE_OWNERSHIP = 0;
  public static final int CHANGE_AUDITING = 1;
  public static final int CHANGE_GENERAL = 2;
  
  public abstract void securityCheck(Acl paramAcl, int paramInt);
}
