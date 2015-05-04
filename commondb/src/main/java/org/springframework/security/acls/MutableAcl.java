package org.springframework.security.acls;

import java.io.Serializable;
import org.springframework.security.acls.sid.Sid;

public abstract interface MutableAcl
  extends Acl
{
  public abstract void deleteAce(int paramInt)
    throws NotFoundException;
  
  public abstract Serializable getId();
  
  public abstract void insertAce(int paramInt, Permission paramPermission, Sid paramSid, boolean paramBoolean)
    throws NotFoundException;
  
  public abstract void setOwner(Sid paramSid);
  
  public abstract void setEntriesInheriting(boolean paramBoolean);
  
  public abstract void setParent(Acl paramAcl);
  
  public abstract void updateAce(int paramInt, Permission paramPermission)
    throws NotFoundException;
}
