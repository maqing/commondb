package com.commondb.db.dao;

import com.commondb.db.bo.RoleRescCriteria;
import com.commondb.db.bo.RoleRescKey;
import java.util.List;

public abstract interface RoleRescDAO
{
  public abstract int countByExample(RoleRescCriteria paramRoleRescCriteria);
  
  public abstract int deleteByExample(RoleRescCriteria paramRoleRescCriteria);
  
  public abstract int deleteByPrimaryKey(RoleRescKey paramRoleRescKey);
  
  public abstract void insert(RoleRescKey paramRoleRescKey);
  
  public abstract void insertSelective(RoleRescKey paramRoleRescKey);
  
  public abstract List selectByExample(RoleRescCriteria paramRoleRescCriteria);
  
  public abstract int updateByExampleSelective(RoleRescKey paramRoleRescKey, RoleRescCriteria paramRoleRescCriteria);
  
  public abstract int updateByExample(RoleRescKey paramRoleRescKey, RoleRescCriteria paramRoleRescCriteria);
}
