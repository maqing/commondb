package com.commondb.db.dao;

import com.commondb.db.bo.Meta;
import com.commondb.db.bo.MetaCriteria;
import java.util.List;

public abstract interface MetaDAO
{
  public abstract int countByExample(MetaCriteria paramMetaCriteria);
  
  public abstract int deleteByExample(MetaCriteria paramMetaCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract Integer insert(Meta paramMeta);
  
  public abstract void insertSelective(Meta paramMeta);
  
  public abstract List selectByExample(MetaCriteria paramMetaCriteria);
  
  public abstract Meta selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(Meta paramMeta, MetaCriteria paramMetaCriteria);
  
  public abstract int updateByExample(Meta paramMeta, MetaCriteria paramMetaCriteria);
  
  public abstract int updateByPrimaryKeySelective(Meta paramMeta);
  
  public abstract int updateByPrimaryKey(Meta paramMeta);
  
  public abstract Meta getMetaByIdWithCashRole(Integer paramInteger);
  
  public abstract List<Meta> getByRoleId(Integer paramInteger);
  
  public abstract List<Meta> getMetaByIdWithCashAttrDef(Integer paramInteger);
  
  public abstract List getMetaByIdWithCashAttrDefArr();
}
