package com.commondb.apply.dao.impl;

import com.commondb.apply.bo.ApplyUser;
import com.commondb.apply.bo.ApplyUserCriteria;
import com.commondb.apply.dao.ApplyUserDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ApplyUserDAOImpl
  extends SqlMapClientDaoSupport
  implements ApplyUserDAO
{
  public int countByExample(ApplyUserCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("r_apply_user.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(ApplyUserCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("r_apply_user.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer id)
  {
    ApplyUser key = new ApplyUser();
    key.setId(id);
    int rows = getSqlMapClientTemplate().delete("r_apply_user.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(ApplyUser record)
  {
    getSqlMapClientTemplate().insert("r_apply_user.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(ApplyUser record)
  {
    getSqlMapClientTemplate().insert("r_apply_user.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(ApplyUserCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("r_apply_user.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public ApplyUser selectByPrimaryKey(Integer id)
  {
    ApplyUser key = new ApplyUser();
    key.setId(id);
    ApplyUser record = (ApplyUser)getSqlMapClientTemplate().queryForObject("r_apply_user.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(ApplyUser record, ApplyUserCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_apply_user.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(ApplyUser record, ApplyUserCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_apply_user.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(ApplyUser record)
  {
    int rows = getSqlMapClientTemplate().update("r_apply_user.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(ApplyUser record)
  {
    int rows = getSqlMapClientTemplate().update("r_apply_user.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends ApplyUserCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, ApplyUserCriteria example)
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
