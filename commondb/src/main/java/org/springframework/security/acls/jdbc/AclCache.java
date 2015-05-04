package org.springframework.security.acls.jdbc;

import java.io.Serializable;
import org.springframework.security.acls.MutableAcl;
import org.springframework.security.acls.objectidentity.ObjectIdentity;

public abstract interface AclCache
{
  public abstract void evictFromCache(Serializable paramSerializable);
  
  public abstract void evictFromCache(ObjectIdentity paramObjectIdentity);
  
  public abstract MutableAcl getFromCache(ObjectIdentity paramObjectIdentity);
  
  public abstract MutableAcl getFromCache(Serializable paramSerializable);
  
  public abstract void putInCache(MutableAcl paramMutableAcl);
}
