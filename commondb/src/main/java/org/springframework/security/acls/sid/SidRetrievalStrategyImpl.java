package org.springframework.security.acls.sid;

import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;

public class SidRetrievalStrategyImpl
  implements SidRetrievalStrategy
{
  public Sid[] getSids(Authentication authentication)
  {
    GrantedAuthority[] authorities = authentication.getAuthorities();
    Sid[] sids = new Sid[authorities.length + 1];
    
    sids[0] = new PrincipalSid(authentication);
    for (int i = 1; i <= authorities.length; i++) {
      sids[i] = new GrantedAuthoritySid(authorities[(i - 1)]);
    }
    return sids;
  }
}
