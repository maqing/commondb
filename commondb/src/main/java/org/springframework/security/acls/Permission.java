package org.springframework.security.acls;

import java.io.Serializable;

public abstract interface Permission
  extends Serializable
{
  public static final char RESERVED_ON = '~';
  public static final char RESERVED_OFF = '.';
  public static final String THIRTY_TWO_RESERVED_OFF = "................................";
  
  public abstract int getMask();
  
  public abstract String getPattern();
}
