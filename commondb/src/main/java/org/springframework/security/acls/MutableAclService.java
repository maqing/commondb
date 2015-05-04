package org.springframework.security.acls;

import org.springframework.security.acls.objectidentity.ObjectIdentity;

public abstract interface MutableAclService
  extends AclService
{
  public abstract MutableAcl createAcl(ObjectIdentity paramObjectIdentity)
    throws AlreadyExistsException;
  
  public abstract void deleteAcl(ObjectIdentity paramObjectIdentity, boolean paramBoolean)
    throws ChildrenExistException;
  
  public abstract MutableAcl updateAcl(MutableAcl paramMutableAcl)
    throws NotFoundException;
}
