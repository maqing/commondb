package org.springframework.security.acls.domain;

import org.springframework.security.acls.AclFormattingUtils;
import org.springframework.security.acls.Permission;

public class CumulativePermission
  extends AbstractPermission
{
  private String pattern = "................................";
  
  public CumulativePermission()
  {
    super(0, ' ');
  }
  
  public CumulativePermission clear(Permission permission)
  {
    this.mask &= (permission.getMask() ^ 0xFFFFFFFF);
    this.pattern = AclFormattingUtils.demergePatterns(this.pattern, permission.getPattern());
    
    return this;
  }
  
  public CumulativePermission clear()
  {
    this.mask = 0;
    this.pattern = "................................";
    
    return this;
  }
  
  public CumulativePermission set(Permission permission)
  {
    this.mask |= permission.getMask();
    this.pattern = AclFormattingUtils.mergePatterns(this.pattern, permission.getPattern());
    
    return this;
  }
  
  public String getPattern()
  {
    return this.pattern;
  }
}
