package com.commondb.db.dao;

import com.commondb.db.bo.MetaProperty;
import com.commondb.db.bo.MetaPropertyCriteria;
import java.util.List;

public abstract interface MetaPropertyDAO
{
  public abstract int countByExample(MetaPropertyCriteria paramMetaPropertyCriteria);
  
  public abstract int deleteByExample(MetaPropertyCriteria paramMetaPropertyCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract Integer insert(MetaProperty paramMetaProperty);
  
  public abstract void insertSelective(MetaProperty paramMetaProperty);
  
  public abstract List selectByExample(MetaPropertyCriteria paramMetaPropertyCriteria);
  
  public abstract MetaProperty selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(MetaProperty paramMetaProperty, MetaPropertyCriteria paramMetaPropertyCriteria);
  
  public abstract int updateByExample(MetaProperty paramMetaProperty, MetaPropertyCriteria paramMetaPropertyCriteria);
  
  public abstract int updateByPrimaryKeySelective(MetaProperty paramMetaProperty);
  
  public abstract int updateByPrimaryKey(MetaProperty paramMetaProperty);
  
  public abstract int updateEnable(MetaProperty paramMetaProperty);
}
