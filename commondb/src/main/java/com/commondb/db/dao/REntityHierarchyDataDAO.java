package com.commondb.db.dao;

import com.commondb.db.bo.REntityHierarchyData;
import com.commondb.db.bo.REntityHierarchyDataCriteria;
import java.util.List;

public abstract interface REntityHierarchyDataDAO
{
  public abstract int countByExample(REntityHierarchyDataCriteria paramREntityHierarchyDataCriteria);
  
  public abstract int deleteByExample(REntityHierarchyDataCriteria paramREntityHierarchyDataCriteria);
  
  public abstract int deleteByPrimaryKey(String paramString);
  
  public abstract void insert(REntityHierarchyData paramREntityHierarchyData);
  
  public abstract void insertSelective(REntityHierarchyData paramREntityHierarchyData);
  
  public abstract List selectByExample(REntityHierarchyDataCriteria paramREntityHierarchyDataCriteria);
  
  public abstract REntityHierarchyData selectByPrimaryKey(String paramString);
  
  public abstract int updateByExampleSelective(REntityHierarchyData paramREntityHierarchyData, REntityHierarchyDataCriteria paramREntityHierarchyDataCriteria);
  
  public abstract int updateByExample(REntityHierarchyData paramREntityHierarchyData, REntityHierarchyDataCriteria paramREntityHierarchyDataCriteria);
  
  public abstract int updateByPrimaryKeySelective(REntityHierarchyData paramREntityHierarchyData);
  
  public abstract int updateByPrimaryKey(REntityHierarchyData paramREntityHierarchyData);
}
