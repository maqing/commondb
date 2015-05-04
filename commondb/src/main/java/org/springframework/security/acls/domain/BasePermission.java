package org.springframework.security.acls.domain;

import org.springframework.security.acls.Permission;

public class BasePermission
  extends AbstractPermission
{
  public static final Permission READ = new BasePermission(1, 'R');
  public static final Permission WRITE = new BasePermission(2, 'W');
  public static final Permission CREATE = new BasePermission(4, 'C');
  public static final Permission DELETE = new BasePermission(8, 'D');
  public static final Permission ADMINISTRATION = new BasePermission(16, 'A');
  protected static DefaultPermissionFactory defaultPermissionFactory = new DefaultPermissionFactory();
  
  static
  {
    registerPermissionsFor(BasePermission.class);
  }
  
  protected BasePermission(int mask, char code)
  {
    super(mask, code);
  }
  
  protected static final void registerPermissionsFor(Class subClass)
  {
    defaultPermissionFactory.registerPublicPermissions(subClass);
  }
  
  public static final Permission buildFromMask(int mask)
  {
    return defaultPermissionFactory.buildFromMask(mask);
  }
  
  public static final Permission[] buildFromMask(int[] masks)
  {
    return defaultPermissionFactory.buildFromMask(masks);
  }
  
  public static final Permission buildFromName(String name)
  {
    return defaultPermissionFactory.buildFromName(name);
  }
  
  public static final Permission[] buildFromName(String[] names)
  {
    return defaultPermissionFactory.buildFromName(names);
  }
}
