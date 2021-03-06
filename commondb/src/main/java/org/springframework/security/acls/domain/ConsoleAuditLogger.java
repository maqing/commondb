package org.springframework.security.acls.domain;

import java.io.PrintStream;
import org.springframework.security.acls.AccessControlEntry;
import org.springframework.security.acls.AuditableAccessControlEntry;
import org.springframework.util.Assert;

public class ConsoleAuditLogger
  implements AuditLogger
{
  public void logIfNeeded(boolean granted, AccessControlEntry ace)
  {
    Assert.notNull(ace, "AccessControlEntry required");
    if ((ace instanceof AuditableAccessControlEntry))
    {
      AuditableAccessControlEntry auditableAce = (AuditableAccessControlEntry)ace;
      if ((granted) && (auditableAce.isAuditSuccess())) {
        System.out.println("GRANTED due to ACE: " + ace);
      } else if ((!granted) && (auditableAce.isAuditFailure())) {
        System.out.println("DENIED due to ACE: " + ace);
      }
    }
  }
}
