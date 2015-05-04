package com.commondb.apply.dao;

import com.commondb.apply.bo.ApplyMenu;
import com.commondb.apply.bo.ApplyMenuCriteria;
import java.util.List;

public abstract interface ApplyMenuDAO
{
  public abstract int countByExample(ApplyMenuCriteria paramApplyMenuCriteria);
  
  public abstract int deleteByExample(ApplyMenuCriteria paramApplyMenuCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(ApplyMenu paramApplyMenu);
  
  public abstract void insertSelective(ApplyMenu paramApplyMenu);
  
  public abstract List selectByExample(ApplyMenuCriteria paramApplyMenuCriteria);
  
  public abstract ApplyMenu selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(ApplyMenu paramApplyMenu, ApplyMenuCriteria paramApplyMenuCriteria);
  
  public abstract int updateByExample(ApplyMenu paramApplyMenu, ApplyMenuCriteria paramApplyMenuCriteria);
  
  public abstract int updateByPrimaryKeySelective(ApplyMenu paramApplyMenu);
  
  public abstract int updateByPrimaryKey(ApplyMenu paramApplyMenu);
}
