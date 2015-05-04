package com.commondb.db.dao;

import com.commondb.db.bo.RoleUser;
import com.commondb.db.bo.RoleUserCriteria;
import java.util.List;

public abstract interface RoleUserDAO
{
  public abstract int countByExample(RoleUserCriteria paramRoleUserCriteria);
  
  public abstract int deleteByExample(RoleUserCriteria paramRoleUserCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract Integer insert(RoleUser paramRoleUser);
  
  public abstract void insertSelective(RoleUser paramRoleUser);
  
  public abstract List selectByExample(RoleUserCriteria paramRoleUserCriteria);
  
  public abstract RoleUser selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(RoleUser paramRoleUser, RoleUserCriteria paramRoleUserCriteria);
  
  public abstract int updateByExample(RoleUser paramRoleUser, RoleUserCriteria paramRoleUserCriteria);
  
  public abstract int updateByPrimaryKeySelective(RoleUser paramRoleUser);
  
  public abstract int updateByPrimaryKey(RoleUser paramRoleUser);
}
