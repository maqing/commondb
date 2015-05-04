package com.commondb.db.dao;

import com.commondb.db.bo.PicAttrDef;
import com.commondb.db.bo.PicAttrDefCriteria;
import java.util.List;

public abstract interface PicAttrDefDAO
{
  public abstract int countByExample(PicAttrDefCriteria paramPicAttrDefCriteria);
  
  public abstract int deleteByExample(PicAttrDefCriteria paramPicAttrDefCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract Integer insert(PicAttrDef paramPicAttrDef);
  
  public abstract void insertSelective(PicAttrDef paramPicAttrDef);
  
  public abstract List selectByExample(PicAttrDefCriteria paramPicAttrDefCriteria);
  
  public abstract PicAttrDef selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(PicAttrDef paramPicAttrDef, PicAttrDefCriteria paramPicAttrDefCriteria);
  
  public abstract int updateByExample(PicAttrDef paramPicAttrDef, PicAttrDefCriteria paramPicAttrDefCriteria);
  
  public abstract int updateByPrimaryKeySelective(PicAttrDef paramPicAttrDef);
  
  public abstract int updateByPrimaryKey(PicAttrDef paramPicAttrDef);
}
