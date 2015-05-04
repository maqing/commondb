package org.springframework.security.acls;

import org.springframework.security.SpringSecurityException;

public class AlreadyExistsException
  extends SpringSecurityException
{
  public AlreadyExistsException(String msg)
  {
    super(msg);
  }
  
  public AlreadyExistsException(String msg, Throwable t)
  {
    super(msg, t);
  }
}
