package com.commondb.db.dao;

import com.commondb.db.bo.Resc;
import com.commondb.db.bo.RescCriteria;
import java.util.List;

public abstract interface RescDAO
{
  public abstract int countByExample(RescCriteria paramRescCriteria);
  
  public abstract int deleteByExample(RescCriteria paramRescCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(Resc paramResc);
  
  public abstract void insertSelective(Resc paramResc);
  
  public abstract List selectByExample(RescCriteria paramRescCriteria);
  
  public abstract Resc selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(Resc paramResc, RescCriteria paramRescCriteria);
  
  public abstract int updateByExample(Resc paramResc, RescCriteria paramRescCriteria);
  
  public abstract int updateByPrimaryKeySelective(Resc paramResc);
  
  public abstract int updateByPrimaryKey(Resc paramResc);
  
  public abstract List<Resc> getRescByIdWithCashRole(Integer paramInteger);
  
  public abstract List<Resc> getByRoleId(Integer paramInteger);
}
