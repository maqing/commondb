package com.commondb.db.dao;

import com.commondb.db.bo.HierarchyAttrData;
import com.commondb.db.bo.HierarchyAttrDataCriteria;
import java.util.List;

public abstract interface HierarchyAttrDataDAO
{
  public abstract int countByExample(HierarchyAttrDataCriteria paramHierarchyAttrDataCriteria);
  
  public abstract int deleteByExample(HierarchyAttrDataCriteria paramHierarchyAttrDataCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(HierarchyAttrData paramHierarchyAttrData);
  
  public abstract void insertSelective(HierarchyAttrData paramHierarchyAttrData);
  
  public abstract List selectByExample(HierarchyAttrDataCriteria paramHierarchyAttrDataCriteria);
  
  public abstract HierarchyAttrData selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(HierarchyAttrData paramHierarchyAttrData, HierarchyAttrDataCriteria paramHierarchyAttrDataCriteria);
  
  public abstract int updateByExample(HierarchyAttrData paramHierarchyAttrData, HierarchyAttrDataCriteria paramHierarchyAttrDataCriteria);
  
  public abstract int updateByPrimaryKeySelective(HierarchyAttrData paramHierarchyAttrData);
  
  public abstract int updateByPrimaryKey(HierarchyAttrData paramHierarchyAttrData);
}
