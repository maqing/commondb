package org.springframework.security.acls.jdbc;

import java.util.Map;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.sid.Sid;

public abstract interface LookupStrategy
{
  public abstract Map readAclsById(ObjectIdentity[] paramArrayOfObjectIdentity, Sid[] paramArrayOfSid);
}
