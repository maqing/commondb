package com.commondb.db.dao;

import com.commondb.db.bo.OperLog;
import com.commondb.db.bo.OperLogCriteria;
import java.util.List;

public abstract interface OperLogDAO
{
  public abstract int countByExample(OperLogCriteria paramOperLogCriteria);
  
  public abstract int deleteByExample(OperLogCriteria paramOperLogCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(OperLog paramOperLog);
  
  public abstract void insertSelective(OperLog paramOperLog);
  
  public abstract List selectByExample(OperLogCriteria paramOperLogCriteria);
  
  public abstract OperLog selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(OperLog paramOperLog, OperLogCriteria paramOperLogCriteria);
  
  public abstract int updateByExample(OperLog paramOperLog, OperLogCriteria paramOperLogCriteria);
  
  public abstract int updateByPrimaryKeySelective(OperLog paramOperLog);
  
  public abstract int updateByPrimaryKey(OperLog paramOperLog);
}
