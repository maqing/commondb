package com.commondb.db.dao;

import com.commondb.db.bo.Entity;
import com.commondb.db.bo.EntityCriteria;
import java.util.List;

public abstract interface EntityDAO
{
  public abstract int countByExample(EntityCriteria paramEntityCriteria);
  
  public abstract int deleteByExample(EntityCriteria paramEntityCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract Integer insert(Entity paramEntity);
  
  public abstract void insertSelective(Entity paramEntity);
  
  public abstract List selectByExample(EntityCriteria paramEntityCriteria);
  
  public abstract Entity selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(Entity paramEntity, EntityCriteria paramEntityCriteria);
  
  public abstract int updateByExample(Entity paramEntity, EntityCriteria paramEntityCriteria);
  
  public abstract int updateByPrimaryKeySelective(Entity paramEntity);
  
  public abstract int updateByPrimaryKey(Entity paramEntity);
}
