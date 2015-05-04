package com.commondb.db.dao;

import com.commondb.db.bo.Role;
import com.commondb.db.bo.RoleCriteria;
import java.util.List;

public abstract interface RoleDAO
{
  public abstract int countByExample(RoleCriteria paramRoleCriteria);
  
  public abstract int deleteByExample(RoleCriteria paramRoleCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract Integer insert(Role paramRole);
  
  public abstract void insertSelective(Role paramRole);
  
  public abstract List selectByExample(RoleCriteria paramRoleCriteria);
  
  public abstract Role selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(Role paramRole, RoleCriteria paramRoleCriteria);
  
  public abstract int updateByExample(Role paramRole, RoleCriteria paramRoleCriteria);
  
  public abstract int updateByPrimaryKeySelective(Role paramRole);
  
  public abstract int updateByPrimaryKey(Role paramRole);
  
  public abstract List<Role> getRoleByIdWithR(Integer paramInteger);
  
  public abstract List<Role> getByUserId(Integer paramInteger);
  
  public abstract List<Role> getByRescId(Integer paramInteger);
  
  public abstract List<Role> getByMetaId(Integer paramInteger);
  
  public abstract List<Role> getByMenuId(Integer paramInteger);
  
  public abstract int getMaxId();
}
