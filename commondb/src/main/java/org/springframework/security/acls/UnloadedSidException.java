package org.springframework.security.acls;

import org.springframework.security.SpringSecurityException;

public class UnloadedSidException
  extends SpringSecurityException
{
  public UnloadedSidException(String msg)
  {
    super(msg);
  }
  
  public UnloadedSidException(String msg, Throwable t)
  {
    super(msg, t);
  }
}
