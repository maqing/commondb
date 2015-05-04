package org.springframework.security.acls.sid;

import org.springframework.security.Authentication;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.util.Assert;

public class PrincipalSid
  implements Sid
{
  private String principal;
  
  public PrincipalSid(String principal)
  {
    Assert.hasText(principal, "Principal required");
    this.principal = principal;
  }
  
  public PrincipalSid(Authentication authentication)
  {
    Assert.notNull(authentication, "Authentication required");
    Assert.notNull(authentication.getPrincipal(), "Principal required");
    if ((authentication.getPrincipal() instanceof UserDetails)) {
      this.principal = ((UserDetails)authentication.getPrincipal()).getUsername();
    } else {
      this.principal = authentication.getPrincipal().toString();
    }
  }
  
  public boolean equals(Object object)
  {
    if ((object == null) || (!(object instanceof PrincipalSid))) {
      return false;
    }
    return ((PrincipalSid)object).getPrincipal().equals(getPrincipal());
  }
  
  public int hashCode()
  {
    return getPrincipal().hashCode();
  }
  
  public String getPrincipal()
  {
    return this.principal;
  }
  
  public String toString()
  {
    return "PrincipalSid[" + this.principal + "]";
  }
}
