package com.commondb.db.dao;

import com.commondb.db.bo.Pic;
import com.commondb.db.bo.PicCriteria;
import java.util.List;

public abstract interface PicDAO
{
  public abstract int countByExample(PicCriteria paramPicCriteria);
  
  public abstract int deleteByExample(PicCriteria paramPicCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(Pic paramPic);
  
  public abstract void insertSelective(Pic paramPic);
  
  public abstract List selectByExample(PicCriteria paramPicCriteria);
  
  public abstract Pic selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(Pic paramPic, PicCriteria paramPicCriteria);
  
  public abstract int updateByExample(Pic paramPic, PicCriteria paramPicCriteria);
  
  public abstract int updateByPrimaryKeySelective(Pic paramPic);
  
  public abstract int updateByPrimaryKey(Pic paramPic);
}
