package org.springframework.security.acls.sid;

import org.springframework.security.GrantedAuthority;
import org.springframework.util.Assert;

public class GrantedAuthoritySid
  implements Sid
{
  private String grantedAuthority;
  
  public GrantedAuthoritySid(String grantedAuthority)
  {
    Assert.hasText(grantedAuthority, "GrantedAuthority required");
    this.grantedAuthority = grantedAuthority;
  }
  
  public GrantedAuthoritySid(GrantedAuthority grantedAuthority)
  {
    Assert.notNull(grantedAuthority, "GrantedAuthority required");
    Assert.notNull(grantedAuthority.getAuthority(), 
      "This Sid is only compatible with GrantedAuthoritys that provide a non-null getAuthority()");
    this.grantedAuthority = grantedAuthority.getAuthority();
  }
  
  public boolean equals(Object object)
  {
    if ((object == null) || (!(object instanceof GrantedAuthoritySid))) {
      return false;
    }
    return ((GrantedAuthoritySid)object).getGrantedAuthority().equals(getGrantedAuthority());
  }
  
  public int hashCode()
  {
    return getGrantedAuthority().hashCode();
  }
  
  public String getGrantedAuthority()
  {
    return this.grantedAuthority;
  }
  
  public String toString()
  {
    return "GrantedAuthoritySid[" + this.grantedAuthority + "]";
  }
}
