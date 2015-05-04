package com.commondb.db.dao;

import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.bo.DescAttrDefCriteria;
import java.util.List;

public abstract interface DescAttrDefDAO
{
  public abstract int countByExample(DescAttrDefCriteria paramDescAttrDefCriteria);
  
  public abstract int deleteByExample(DescAttrDefCriteria paramDescAttrDefCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract Integer insert(DescAttrDef paramDescAttrDef);
  
  public abstract void insertSelective(DescAttrDef paramDescAttrDef);
  
  public abstract List selectByExampleWithBLOBs(DescAttrDefCriteria paramDescAttrDefCriteria);
  
  public abstract List selectByExampleWithoutBLOBs(DescAttrDefCriteria paramDescAttrDefCriteria);
  
  public abstract DescAttrDef selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(DescAttrDef paramDescAttrDef, DescAttrDefCriteria paramDescAttrDefCriteria);
  
  public abstract int updateByExampleWithBLOBs(DescAttrDef paramDescAttrDef, DescAttrDefCriteria paramDescAttrDefCriteria);
  
  public abstract int updateByExampleWithoutBLOBs(DescAttrDef paramDescAttrDef, DescAttrDefCriteria paramDescAttrDefCriteria);
  
  public abstract int updateByPrimaryKeySelective(DescAttrDef paramDescAttrDef);
  
  public abstract int updateByPrimaryKeyWithBLOBs(DescAttrDef paramDescAttrDef);
  
  public abstract int updateByPrimaryKeyWithoutBLOBs(DescAttrDef paramDescAttrDef);
}
