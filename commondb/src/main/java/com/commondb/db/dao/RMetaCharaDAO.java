package com.commondb.db.dao;

import com.commondb.db.bo.RMetaChara;
import com.commondb.db.bo.RMetaCharaCriteria;
import java.util.List;

public abstract interface RMetaCharaDAO
{
  public abstract int countByExample(RMetaCharaCriteria paramRMetaCharaCriteria);
  
  public abstract int deleteByExample(RMetaCharaCriteria paramRMetaCharaCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(RMetaChara paramRMetaChara);
  
  public abstract void insertSelective(RMetaChara paramRMetaChara);
  
  public abstract List selectByExample(RMetaCharaCriteria paramRMetaCharaCriteria);
  
  public abstract RMetaChara selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(RMetaChara paramRMetaChara, RMetaCharaCriteria paramRMetaCharaCriteria);
  
  public abstract int updateByExample(RMetaChara paramRMetaChara, RMetaCharaCriteria paramRMetaCharaCriteria);
  
  public abstract int updateByPrimaryKeySelective(RMetaChara paramRMetaChara);
  
  public abstract int updateByPrimaryKey(RMetaChara paramRMetaChara);
  
  public abstract List selectByUser(Integer paramInteger1, Integer paramInteger2);
}
