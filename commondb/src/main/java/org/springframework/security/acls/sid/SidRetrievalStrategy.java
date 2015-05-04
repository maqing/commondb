package org.springframework.security.acls.sid;

import org.springframework.security.Authentication;

public abstract interface SidRetrievalStrategy
{
  public abstract Sid[] getSids(Authentication paramAuthentication);
}
