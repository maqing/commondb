package org.springframework.security.acls.domain;

import org.springframework.security.acls.AclFormattingUtils;
import org.springframework.security.acls.Permission;

public abstract class AbstractPermission
  implements Permission
{
  protected char code;
  protected int mask;
  
  protected AbstractPermission(int mask, char code)
  {
    this.mask = mask;
    this.code = code;
  }
  
  public final boolean equals(Object arg0)
  {
    if (arg0 == null) {
      return false;
    }
    if (!(arg0 instanceof Permission)) {
      return false;
    }
    Permission rhs = (Permission)arg0;
    
    return this.mask == rhs.getMask();
  }
  
  public final int getMask()
  {
    return this.mask;
  }
  
  public String getPattern()
  {
    return AclFormattingUtils.printBinary(this.mask, this.code);
  }
  
  public final String toString()
  {
    return getClass().getSimpleName() + "[" + getPattern() + "=" + this.mask + "]";
  }
  
  public final int hashCode()
  {
    return this.mask;
  }
}
