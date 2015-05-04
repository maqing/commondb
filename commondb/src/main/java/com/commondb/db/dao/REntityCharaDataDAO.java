package com.commondb.db.dao;

import com.commondb.db.bo.REntityCharaData;
import com.commondb.db.bo.REntityCharaDataCriteria;
import java.util.List;

public abstract interface REntityCharaDataDAO
{
  public abstract int countByExample(REntityCharaDataCriteria paramREntityCharaDataCriteria);
  
  public abstract int deleteByExample(REntityCharaDataCriteria paramREntityCharaDataCriteria);
  
  public abstract int deleteByPrimaryKey(String paramString);
  
  public abstract void insert(REntityCharaData paramREntityCharaData);
  
  public abstract void insertSelective(REntityCharaData paramREntityCharaData);
  
  public abstract List selectByExample(REntityCharaDataCriteria paramREntityCharaDataCriteria);
  
  public abstract REntityCharaData selectByPrimaryKey(String paramString);
  
  public abstract int updateByExampleSelective(REntityCharaData paramREntityCharaData, REntityCharaDataCriteria paramREntityCharaDataCriteria);
  
  public abstract int updateByExample(REntityCharaData paramREntityCharaData, REntityCharaDataCriteria paramREntityCharaDataCriteria);
  
  public abstract int updateByPrimaryKeySelective(REntityCharaData paramREntityCharaData);
  
  public abstract int updateByPrimaryKey(REntityCharaData paramREntityCharaData);
  
  public abstract List selectByUser(Integer paramInteger1, String paramString, Integer paramInteger2);
}
