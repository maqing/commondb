package org.springframework.security.acls;

import java.io.Serializable;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.sid.Sid;

public abstract interface Acl
  extends Serializable
{
  public abstract AccessControlEntry[] getEntries();
  
  public abstract ObjectIdentity getObjectIdentity();
  
  public abstract Sid getOwner();
  
  public abstract Acl getParentAcl();
  
  public abstract boolean isEntriesInheriting();
  
  public abstract boolean isGranted(Permission[] paramArrayOfPermission, Sid[] paramArrayOfSid, boolean paramBoolean)
    throws NotFoundException, UnloadedSidException;
  
  public abstract boolean isSidLoaded(Sid[] paramArrayOfSid);
}
