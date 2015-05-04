package org.springframework.security.acls.domain;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.acls.Permission;
import org.springframework.util.Assert;

public class DefaultPermissionFactory
  implements PermissionFactory
{
  private Map registeredPermissionsByInteger = new HashMap();
  private Map registeredPermissionsByName = new HashMap();
  
  public void registerPublicPermissions(Class clazz)
  {
    Assert.notNull(clazz, "Class required");
    Assert.isAssignable(Permission.class, clazz);
    
    Field[] fields = clazz.getFields();
    for (int i = 0; i < fields.length; i++) {
      try
      {
        Object fieldValue = fields[i].get(null);
        if (Permission.class.isAssignableFrom(fieldValue.getClass()))
        {
          Permission perm = (Permission)fieldValue;
          String permissionName = fields[i].getName();
          
          registerPermission(perm, permissionName);
        }
      }
      catch (Exception localException) {}
    }
  }
  
  public void registerPermission(Permission perm, String permissionName)
  {
    Assert.notNull(perm, "Permission required");
    Assert.hasText(permissionName, "Permission name required");
    
    Integer mask = new Integer(perm.getMask());
    

    Assert.isTrue(!this.registeredPermissionsByInteger.containsKey(mask), "An existing Permission already provides mask " + mask);
    Assert.isTrue(!this.registeredPermissionsByName.containsKey(permissionName), "An existing Permission already provides name '" + permissionName + "'");
    

    this.registeredPermissionsByInteger.put(mask, perm);
    this.registeredPermissionsByName.put(permissionName, perm);
  }
  
  public Permission buildFromMask(int mask)
  {
    if (this.registeredPermissionsByInteger.containsKey(new Integer(mask))) {
      return (Permission)this.registeredPermissionsByInteger.get(new Integer(mask));
    }
    CumulativePermission permission = new CumulativePermission();
    for (int i = 0; i < 32; i++)
    {
      int permissionToCheck = 1 << i;
      if ((mask & permissionToCheck) == permissionToCheck)
      {
        Permission p = (Permission)this.registeredPermissionsByInteger.get(new Integer(permissionToCheck));
        Assert.state(p != null, "Mask " + permissionToCheck + " does not have a corresponding static Permission");
        permission.set(p);
      }
    }
    return permission;
  }
  
  public Permission[] buildFromMask(int[] masks)
  {
    if ((masks == null) || (masks.length == 0)) {
      return new Permission[0];
    }
    Permission[] permissions = new Permission[masks.length];
    for (int i = 0; i < masks.length; i++) {
      permissions[i] = buildFromMask(masks[i]);
    }
    return permissions;
  }
  
  public Permission buildFromName(String name)
  {
    Assert.isTrue(this.registeredPermissionsByName.containsKey(name), "Unknown permission '" + name + "'");
    
    return (Permission)this.registeredPermissionsByName.get(name);
  }
  
  public Permission[] buildFromName(String[] names)
  {
    if ((names == null) || (names.length == 0)) {
      return new Permission[0];
    }
    Permission[] permissions = new Permission[names.length];
    for (int i = 0; i < names.length; i++) {
      permissions[i] = buildFromName(names[i]);
    }
    return permissions;
  }
}
