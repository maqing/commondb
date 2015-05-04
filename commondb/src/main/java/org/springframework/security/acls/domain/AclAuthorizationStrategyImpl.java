package org.springframework.security.acls.domain;

import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.acls.Acl;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.sid.PrincipalSid;
import org.springframework.security.acls.sid.Sid;
import org.springframework.security.acls.sid.SidRetrievalStrategy;
import org.springframework.security.acls.sid.SidRetrievalStrategyImpl;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.util.Assert;

public class AclAuthorizationStrategyImpl
  implements AclAuthorizationStrategy
{
  private GrantedAuthority gaGeneralChanges;
  private GrantedAuthority gaModifyAuditing;
  private GrantedAuthority gaTakeOwnership;
  private SidRetrievalStrategy sidRetrievalStrategy = new SidRetrievalStrategyImpl();
  
  public AclAuthorizationStrategyImpl(GrantedAuthority[] auths)
  {
    Assert.notEmpty(auths, "GrantedAuthority[] with three elements required");
    Assert.isTrue(auths.length == 3, "GrantedAuthority[] with three elements required");
    this.gaTakeOwnership = auths[0];
    this.gaModifyAuditing = auths[1];
    this.gaGeneralChanges = auths[2];
  }
  
  public void securityCheck(Acl acl, int changeType)
  {
    if ((SecurityContextHolder.getContext() == null) || 
      (SecurityContextHolder.getContext().getAuthentication() == null) || 
      (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated())) {
      throw new AccessDeniedException("Authenticated principal required to operate with ACLs");
    }
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    

    Sid currentUser = new PrincipalSid(authentication);
    if ((currentUser.equals(acl.getOwner())) && (
      (changeType == 2) || (changeType == 0))) {
      return;
    }
    GrantedAuthority requiredAuthority = null;
    if (changeType == 1) {
      requiredAuthority = this.gaModifyAuditing;
    } else if (changeType == 2) {
      requiredAuthority = this.gaGeneralChanges;
    } else if (changeType == 0) {
      requiredAuthority = this.gaTakeOwnership;
    } else {
      throw new IllegalArgumentException("Unknown change type");
    }
    GrantedAuthority[] auths = authentication.getAuthorities();
    for (int i = 0; i < auths.length; i++) {
      if (requiredAuthority.equals(auths[i])) {
        return;
      }
    }
    Sid[] sids = this.sidRetrievalStrategy.getSids(authentication);
    if (acl.isGranted(new Permission[] { BasePermission.ADMINISTRATION }, sids, false)) {
      return;
    }
    throw new AccessDeniedException(
      "Principal does not have required ACL permissions to perform requested operation");
  }
  
  public void setSidRetrievalStrategy(SidRetrievalStrategy sidRetrievalStrategy)
  {
    Assert.notNull(sidRetrievalStrategy, "SidRetrievalStrategy required");
    this.sidRetrievalStrategy = sidRetrievalStrategy;
  }
}
