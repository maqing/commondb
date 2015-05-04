package com.commondb.db.dao;

import com.commondb.db.bo.HierarchyAttrValue;
import com.commondb.db.bo.HierarchyAttrValueCriteria;
import java.util.List;

public abstract interface HierarchyAttrValueDAO
{
  public abstract String getHierPathString(Integer paramInteger, String paramString);
  
  public abstract int countByExample(HierarchyAttrValueCriteria paramHierarchyAttrValueCriteria);
  
  public abstract int deleteByExample(HierarchyAttrValueCriteria paramHierarchyAttrValueCriteria);
  
  public abstract int deleteByPrimaryKey(String paramString);
  
  public abstract void insert(HierarchyAttrValue paramHierarchyAttrValue);
  
  public abstract void insertSelective(HierarchyAttrValue paramHierarchyAttrValue);
  
  public abstract List selectByExample(HierarchyAttrValueCriteria paramHierarchyAttrValueCriteria);
  
  public abstract HierarchyAttrValue selectByPrimaryKey(String paramString);
  
  public abstract int updateByExampleSelective(HierarchyAttrValue paramHierarchyAttrValue, HierarchyAttrValueCriteria paramHierarchyAttrValueCriteria);
  
  public abstract int updateByExample(HierarchyAttrValue paramHierarchyAttrValue, HierarchyAttrValueCriteria paramHierarchyAttrValueCriteria);
  
  public abstract int updateByPrimaryKeySelective(HierarchyAttrValue paramHierarchyAttrValue);
  
  public abstract int updateByPrimaryKey(HierarchyAttrValue paramHierarchyAttrValue);
}
