package com.commondb.db.dao;

import com.commondb.db.bo.OperationBox;
import com.commondb.db.bo.OperationBoxCriteria;
import java.util.List;

public abstract interface OperationBoxDAO
{
  public abstract void insert(OperationBox paramOperationBox);
  
  public abstract int updateByPrimaryKey(OperationBox paramOperationBox);
  
  public abstract int updateByPrimaryKeySelective(OperationBox paramOperationBox);
  
  public abstract List selectByExample(OperationBoxCriteria paramOperationBoxCriteria);
  
  public abstract OperationBox selectByPrimaryKey(String paramString);
  
  public abstract int deleteByExample(OperationBoxCriteria paramOperationBoxCriteria);
  
  public abstract int deleteByPrimaryKey(String paramString);
  
  public abstract int countByExample(OperationBoxCriteria paramOperationBoxCriteria);
  
  public abstract int updateByExampleSelective(OperationBox paramOperationBox, OperationBoxCriteria paramOperationBoxCriteria);
  
  public abstract int updateByExample(OperationBox paramOperationBox, OperationBoxCriteria paramOperationBoxCriteria);
}
