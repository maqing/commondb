package com.commondb.db.dao.impl;

import com.commondb.db.bo.OperationBox;
import com.commondb.db.bo.OperationBoxCriteria;
import com.commondb.db.dao.OperationBoxDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class OperationBoxDAOImpl
  extends SqlMapClientDaoSupport
  implements OperationBoxDAO
{
  public void insert(OperationBox record)
  {
    getSqlMapClientTemplate().insert("t_operation_box.abatorgenerated_insert", record);
  }
  
  public int updateByPrimaryKey(OperationBox record)
  {
    int rows = getSqlMapClientTemplate().update("t_operation_box.abatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(OperationBox record)
  {
    int rows = getSqlMapClientTemplate().update("t_operation_box.abatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public List selectByExample(OperationBoxCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_operation_box.abatorgenerated_selectByExample", example);
    return list;
  }
  
  public OperationBox selectByPrimaryKey(String id)
  {
    OperationBox key = new OperationBox();
    key.setId(id);
    OperationBox record = (OperationBox)getSqlMapClientTemplate().queryForObject("t_operation_box.abatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int deleteByExample(OperationBoxCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_operation_box.abatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(String id)
  {
    OperationBox key = new OperationBox();
    key.setId(id);
    int rows = getSqlMapClientTemplate().delete("t_operation_box.abatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public int countByExample(OperationBoxCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_operation_box.abatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int updateByExampleSelective(OperationBox record, OperationBoxCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_operation_box.abatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(OperationBox record, OperationBoxCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_operation_box.abatorgenerated_updateByExample", parms);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends OperationBoxCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, OperationBoxCriteria example)
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
