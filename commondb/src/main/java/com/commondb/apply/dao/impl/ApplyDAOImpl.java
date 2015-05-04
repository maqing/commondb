package com.commondb.apply.dao.impl;

import com.commondb.apply.bo.Apply;
import com.commondb.apply.bo.ApplyCriteria;
import com.commondb.apply.dao.ApplyDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ApplyDAOImpl
  extends SqlMapClientDaoSupport
  implements ApplyDAO
{
  public int countByExample(ApplyCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_apply.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(ApplyCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_apply.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer applyId)
  {
    Apply key = new Apply();
    key.setApplyId(applyId);
    int rows = getSqlMapClientTemplate().delete("t_apply.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(Apply record)
  {
    getSqlMapClientTemplate().insert("t_apply.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(Apply record)
  {
    getSqlMapClientTemplate().insert("t_apply.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(ApplyCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_apply.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public Apply selectByPrimaryKey(Integer applyId)
  {
    Apply key = new Apply();
    key.setApplyId(applyId);
    Apply record = (Apply)getSqlMapClientTemplate().queryForObject("t_apply.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(Apply record, ApplyCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_apply.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(Apply record, ApplyCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_apply.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(Apply record)
  {
    int rows = getSqlMapClientTemplate().update("t_apply.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(Apply record)
  {
    int rows = getSqlMapClientTemplate().update("t_apply.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends ApplyCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, ApplyCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
}
