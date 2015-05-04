package com.commondb.db.dao;

import com.commondb.db.bo.LinkAttrData;
import com.commondb.db.bo.LinkAttrDataCriteria;
import java.util.List;

public abstract interface LinkAttrDataDAO
{
  public abstract int countByExample(LinkAttrDataCriteria paramLinkAttrDataCriteria);
  
  public abstract int deleteByExample(LinkAttrDataCriteria paramLinkAttrDataCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(LinkAttrData paramLinkAttrData);
  
  public abstract void insertSelective(LinkAttrData paramLinkAttrData);
  
  public abstract List selectByExample(LinkAttrDataCriteria paramLinkAttrDataCriteria);
  
  public abstract LinkAttrData selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(LinkAttrData paramLinkAttrData, LinkAttrDataCriteria paramLinkAttrDataCriteria);
  
  public abstract int updateByExample(LinkAttrData paramLinkAttrData, LinkAttrDataCriteria paramLinkAttrDataCriteria);
  
  public abstract int updateByPrimaryKeySelective(LinkAttrData paramLinkAttrData);
  
  public abstract int updateByPrimaryKey(LinkAttrData paramLinkAttrData);
}
