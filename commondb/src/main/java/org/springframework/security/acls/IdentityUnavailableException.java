package org.springframework.security.acls;

import org.springframework.security.SpringSecurityException;

public class IdentityUnavailableException
  extends SpringSecurityException
{
  public IdentityUnavailableException(String msg)
  {
    super(msg);
  }
  
  public IdentityUnavailableException(String msg, Throwable t)
  {
    super(msg, t);
  }
}
