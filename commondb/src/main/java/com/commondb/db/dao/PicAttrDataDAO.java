package com.commondb.db.dao;

import com.commondb.db.bo.PicAttrData;
import com.commondb.db.bo.PicAttrDataCriteria;
import java.util.List;

public abstract interface PicAttrDataDAO
{
  public abstract int countByExample(PicAttrDataCriteria paramPicAttrDataCriteria);
  
  public abstract int deleteByExample(PicAttrDataCriteria paramPicAttrDataCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(PicAttrData paramPicAttrData);
  
  public abstract void insertSelective(PicAttrData paramPicAttrData);
  
  public abstract List selectByExample(PicAttrDataCriteria paramPicAttrDataCriteria);
  
  public abstract PicAttrData selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(PicAttrData paramPicAttrData, PicAttrDataCriteria paramPicAttrDataCriteria);
  
  public abstract int updateByExample(PicAttrData paramPicAttrData, PicAttrDataCriteria paramPicAttrDataCriteria);
  
  public abstract int updateByPrimaryKeySelective(PicAttrData paramPicAttrData);
  
  public abstract int updateByPrimaryKey(PicAttrData paramPicAttrData);
}
