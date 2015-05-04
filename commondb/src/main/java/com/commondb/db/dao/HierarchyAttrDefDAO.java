package com.commondb.db.dao;

import com.commondb.db.bo.HierarchyAttrDef;
import com.commondb.db.bo.HierarchyAttrDefCriteria;
import java.util.List;

public abstract interface HierarchyAttrDefDAO
{
  public abstract int countByExample(HierarchyAttrDefCriteria paramHierarchyAttrDefCriteria);
  
  public abstract int deleteByExample(HierarchyAttrDefCriteria paramHierarchyAttrDefCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract Integer insert(HierarchyAttrDef paramHierarchyAttrDef);
  
  public abstract void insertSelective(HierarchyAttrDef paramHierarchyAttrDef);
  
  public abstract List selectByExample(HierarchyAttrDefCriteria paramHierarchyAttrDefCriteria);
  
  public abstract HierarchyAttrDef selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(HierarchyAttrDef paramHierarchyAttrDef, HierarchyAttrDefCriteria paramHierarchyAttrDefCriteria);
  
  public abstract int updateByExample(HierarchyAttrDef paramHierarchyAttrDef, HierarchyAttrDefCriteria paramHierarchyAttrDefCriteria);
  
  public abstract int updateByPrimaryKeySelective(HierarchyAttrDef paramHierarchyAttrDef);
  
  public abstract int updateByPrimaryKey(HierarchyAttrDef paramHierarchyAttrDef);
}
