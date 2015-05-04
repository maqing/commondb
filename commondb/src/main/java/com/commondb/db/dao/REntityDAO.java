package com.commondb.db.dao;

import com.commondb.db.bo.REntity;
import com.commondb.db.bo.REntityCriteria;
import java.util.List;

public abstract interface REntityDAO
{
  public abstract int countByExample(REntityCriteria paramREntityCriteria);
  
  public abstract int deleteByExample(REntityCriteria paramREntityCriteria);
  
  public abstract int deleteByPrimaryKey(String paramString);
  
  public abstract void insert(REntity paramREntity);
  
  public abstract void insertSelective(REntity paramREntity);
  
  public abstract List selectByExample(REntityCriteria paramREntityCriteria);
  
  public abstract REntity selectByPrimaryKey(String paramString);
  
  public abstract int updateByExampleSelective(REntity paramREntity, REntityCriteria paramREntityCriteria);
  
  public abstract int updateByExample(REntity paramREntity, REntityCriteria paramREntityCriteria);
  
  public abstract int updateByPrimaryKeySelective(REntity paramREntity);
  
  public abstract int updateByPrimaryKey(REntity paramREntity);
  
  public abstract List getREntityById(Integer paramInteger, String paramString);
}
