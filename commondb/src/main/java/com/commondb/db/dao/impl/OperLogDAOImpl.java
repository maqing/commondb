package com.commondb.db.dao.impl;

import com.commondb.db.bo.OperLog;
import com.commondb.db.bo.OperLogCriteria;
import com.commondb.db.dao.OperLogDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class OperLogDAOImpl
  extends SqlMapClientDaoSupport
  implements OperLogDAO
{
  public int countByExample(OperLogCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_oper_log.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(OperLogCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_oper_log.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer id)
  {
    OperLog key = new OperLog();
    key.setId(id);
    int rows = getSqlMapClientTemplate().delete("t_oper_log.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(OperLog record)
  {
    getSqlMapClientTemplate().insert("t_oper_log.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(OperLog record)
  {
    getSqlMapClientTemplate().insert("t_oper_log.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(OperLogCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_oper_log.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public OperLog selectByPrimaryKey(Integer id)
  {
    OperLog key = new OperLog();
    key.setId(id);
    OperLog record = (OperLog)getSqlMapClientTemplate().queryForObject("t_oper_log.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(OperLog record, OperLogCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_oper_log.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(OperLog record, OperLogCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_oper_log.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(OperLog record)
  {
    int rows = getSqlMapClientTemplate().update("t_oper_log.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(OperLog record)
  {
    int rows = getSqlMapClientTemplate().update("t_oper_log.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends OperLogCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, OperLogCriteria example)
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
