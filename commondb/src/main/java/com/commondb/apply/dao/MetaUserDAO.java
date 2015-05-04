package com.commondb.apply.dao;

import com.commondb.apply.bo.MetaUser;
import com.commondb.apply.bo.MetaUserCriteria;
import java.util.List;

public abstract interface MetaUserDAO
{
  public abstract int countByExample(MetaUserCriteria paramMetaUserCriteria);
  
  public abstract int deleteByExample(MetaUserCriteria paramMetaUserCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(MetaUser paramMetaUser);
  
  public abstract void insertSelective(MetaUser paramMetaUser);
  
  public abstract List selectByExample(MetaUserCriteria paramMetaUserCriteria);
  
  public abstract MetaUser selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(MetaUser paramMetaUser, MetaUserCriteria paramMetaUserCriteria);
  
  public abstract int updateByExample(MetaUser paramMetaUser, MetaUserCriteria paramMetaUserCriteria);
  
  public abstract int updateByPrimaryKeySelective(MetaUser paramMetaUser);
  
  public abstract int updateByPrimaryKey(MetaUser paramMetaUser);
}
