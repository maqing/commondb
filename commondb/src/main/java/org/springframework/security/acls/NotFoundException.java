package org.springframework.security.acls;

import org.springframework.security.SpringSecurityException;

public class NotFoundException
  extends SpringSecurityException
{
  public NotFoundException(String msg)
  {
    super(msg);
  }
  
  public NotFoundException(String msg, Throwable t)
  {
    super(msg, t);
  }
}
