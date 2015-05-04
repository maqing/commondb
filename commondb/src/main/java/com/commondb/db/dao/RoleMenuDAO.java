package com.commondb.db.dao;

import com.commondb.db.bo.RoleMenuCriteria;
import com.commondb.db.bo.RoleMenuKey;
import java.util.List;

public abstract interface RoleMenuDAO
{
  public abstract int countByExample(RoleMenuCriteria paramRoleMenuCriteria);
  
  public abstract int deleteByExample(RoleMenuCriteria paramRoleMenuCriteria);
  
  public abstract int deleteByPrimaryKey(RoleMenuKey paramRoleMenuKey);
  
  public abstract void insert(RoleMenuKey paramRoleMenuKey);
  
  public abstract void insertSelective(RoleMenuKey paramRoleMenuKey);
  
  public abstract List selectByExample(RoleMenuCriteria paramRoleMenuCriteria);
  
  public abstract int updateByExampleSelective(RoleMenuKey paramRoleMenuKey, RoleMenuCriteria paramRoleMenuCriteria);
  
  public abstract int updateByExample(RoleMenuKey paramRoleMenuKey, RoleMenuCriteria paramRoleMenuCriteria);
}
