package org.springframework.security.acls;

import java.io.Serializable;
import org.springframework.security.acls.sid.Sid;

public abstract interface AccessControlEntry
  extends Serializable
{
  public abstract Acl getAcl();
  
  public abstract Serializable getId();
  
  public abstract Permission getPermission();
  
  public abstract Sid getSid();
  
  public abstract boolean isGranting();
}
