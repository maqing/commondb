package com.commondb.apply.dao;

import com.commondb.apply.bo.Apply;
import com.commondb.apply.bo.ApplyCriteria;
import java.util.List;

public abstract interface ApplyDAO
{
  public abstract int countByExample(ApplyCriteria paramApplyCriteria);
  
  public abstract int deleteByExample(ApplyCriteria paramApplyCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(Apply paramApply);
  
  public abstract void insertSelective(Apply paramApply);
  
  public abstract List selectByExample(ApplyCriteria paramApplyCriteria);
  
  public abstract Apply selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(Apply paramApply, ApplyCriteria paramApplyCriteria);
  
  public abstract int updateByExample(Apply paramApply, ApplyCriteria paramApplyCriteria);
  
  public abstract int updateByPrimaryKeySelective(Apply paramApply);
  
  public abstract int updateByPrimaryKey(Apply paramApply);
}
