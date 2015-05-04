package com.commondb.db.dao;

import com.commondb.db.bo.DescAttrData;
import com.commondb.db.bo.DescAttrDataCriteria;
import java.util.List;

public abstract interface DescAttrDataDAO
{
  public abstract int countByExample(DescAttrDataCriteria paramDescAttrDataCriteria);
  
  public abstract int deleteByExample(DescAttrDataCriteria paramDescAttrDataCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(DescAttrData paramDescAttrData);
  
  public abstract void insertSelective(DescAttrData paramDescAttrData);
  
  public abstract List selectByExampleWithBLOBs(DescAttrDataCriteria paramDescAttrDataCriteria);
  
  public abstract List selectByExampleWithoutBLOBs(DescAttrDataCriteria paramDescAttrDataCriteria);
  
  public abstract DescAttrData selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(DescAttrData paramDescAttrData, DescAttrDataCriteria paramDescAttrDataCriteria);
  
  public abstract int updateByExampleWithBLOBs(DescAttrData paramDescAttrData, DescAttrDataCriteria paramDescAttrDataCriteria);
  
  public abstract int updateByExampleWithoutBLOBs(DescAttrData paramDescAttrData, DescAttrDataCriteria paramDescAttrDataCriteria);
  
  public abstract int updateByPrimaryKeySelective(DescAttrData paramDescAttrData);
  
  public abstract int updateByPrimaryKeyWithBLOBs(DescAttrData paramDescAttrData);
  
  public abstract int updateByPrimaryKeyWithoutBLOBs(DescAttrData paramDescAttrData);
}
