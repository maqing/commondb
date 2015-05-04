package org.springframework.security.acls;

import org.springframework.security.SpringSecurityException;

public class ChildrenExistException
  extends SpringSecurityException
{
  public ChildrenExistException(String msg)
  {
    super(msg);
  }
  
  public ChildrenExistException(String msg, Throwable t)
  {
    super(msg, t);
  }
}
