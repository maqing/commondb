package org.springframework.security.acls;

import java.util.Map;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.sid.Sid;

public abstract interface AclService
{
  public abstract ObjectIdentity[] findChildren(ObjectIdentity paramObjectIdentity);
  
  public abstract Acl readAclById(ObjectIdentity paramObjectIdentity)
    throws NotFoundException;
  
  public abstract Acl readAclById(ObjectIdentity paramObjectIdentity, Sid[] paramArrayOfSid)
    throws NotFoundException;
  
  public abstract Map readAclsById(ObjectIdentity[] paramArrayOfObjectIdentity)
    throws NotFoundException;
  
  public abstract Map readAclsById(ObjectIdentity[] paramArrayOfObjectIdentity, Sid[] paramArrayOfSid)
    throws NotFoundException;
}
