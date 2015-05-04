package com.commondb.db.dao;

import com.commondb.db.bo.RoleMeta;
import com.commondb.db.bo.RoleMetaCriteria;
import java.util.List;

public abstract interface RoleMetaDAO
{
  public abstract int countByExample(RoleMetaCriteria paramRoleMetaCriteria);
  
  public abstract int deleteByExample(RoleMetaCriteria paramRoleMetaCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract Integer insert(RoleMeta paramRoleMeta);
  
  public abstract void insertSelective(RoleMeta paramRoleMeta);
  
  public abstract List selectByExample(RoleMetaCriteria paramRoleMetaCriteria);
  
  public abstract RoleMeta selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(RoleMeta paramRoleMeta, RoleMetaCriteria paramRoleMetaCriteria);
  
  public abstract int updateByExample(RoleMeta paramRoleMeta, RoleMetaCriteria paramRoleMetaCriteria);
  
  public abstract int updateByPrimaryKeySelective(RoleMeta paramRoleMeta);
  
  public abstract int updateByPrimaryKey(RoleMeta paramRoleMeta);
}
