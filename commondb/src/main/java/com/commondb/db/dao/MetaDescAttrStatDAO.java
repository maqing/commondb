package com.commondb.db.dao;

import com.commondb.db.bo.MetaDescAttrStat;
import com.commondb.db.bo.MetaDescAttrStatCriteria;
import java.util.List;

public abstract interface MetaDescAttrStatDAO
{
  public abstract int countByExample(MetaDescAttrStatCriteria paramMetaDescAttrStatCriteria);
  
  public abstract int deleteByExample(MetaDescAttrStatCriteria paramMetaDescAttrStatCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(MetaDescAttrStat paramMetaDescAttrStat);
  
  public abstract void insertSelective(MetaDescAttrStat paramMetaDescAttrStat);
  
  public abstract List selectByExample(MetaDescAttrStatCriteria paramMetaDescAttrStatCriteria);
  
  public abstract MetaDescAttrStat selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(MetaDescAttrStat paramMetaDescAttrStat, MetaDescAttrStatCriteria paramMetaDescAttrStatCriteria);
  
  public abstract int updateByExample(MetaDescAttrStat paramMetaDescAttrStat, MetaDescAttrStatCriteria paramMetaDescAttrStatCriteria);
  
  public abstract int updateByPrimaryKeySelective(MetaDescAttrStat paramMetaDescAttrStat);
  
  public abstract int updateByPrimaryKey(MetaDescAttrStat paramMetaDescAttrStat);
}
