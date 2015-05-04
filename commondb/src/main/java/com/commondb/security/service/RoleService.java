package com.commondb.security.service;

import java.util.List;

public abstract interface RoleService
{
  public abstract List findRolesWhithR(Integer paramInteger);
  
  public abstract Integer createRole(String paramString1, String paramString2, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2);
  
  public abstract void updateRole(Integer paramInteger, String paramString1, String paramString2, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2);
  
  public abstract void delRole(Integer paramInteger);
}
