package com.commondb.apply.dao;

import com.commondb.apply.bo.ApplyUser;
import com.commondb.apply.bo.ApplyUserCriteria;
import java.util.List;

public abstract interface ApplyUserDAO
{
  public abstract int countByExample(ApplyUserCriteria paramApplyUserCriteria);
  
  public abstract int deleteByExample(ApplyUserCriteria paramApplyUserCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(ApplyUser paramApplyUser);
  
  public abstract void insertSelective(ApplyUser paramApplyUser);
  
  public abstract List selectByExample(ApplyUserCriteria paramApplyUserCriteria);
  
  public abstract ApplyUser selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(ApplyUser paramApplyUser, ApplyUserCriteria paramApplyUserCriteria);
  
  public abstract int updateByExample(ApplyUser paramApplyUser, ApplyUserCriteria paramApplyUserCriteria);
  
  public abstract int updateByPrimaryKeySelective(ApplyUser paramApplyUser);
  
  public abstract int updateByPrimaryKey(ApplyUser paramApplyUser);
}
