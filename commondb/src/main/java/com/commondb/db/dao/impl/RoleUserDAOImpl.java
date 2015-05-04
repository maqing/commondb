package com.commondb.db.dao.impl;

import com.commondb.db.bo.RoleUser;
import com.commondb.db.bo.RoleUserCriteria;
import com.commondb.db.dao.RoleUserDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RoleUserDAOImpl
  extends SqlMapClientDaoSupport
  implements RoleUserDAO
{
  public int countByExample(RoleUserCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("r_role_user.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(RoleUserCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("r_role_user.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer id)
  {
    RoleUser key = new RoleUser();
    key.setId(id);
    int rows = getSqlMapClientTemplate().delete("r_role_user.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public Integer insert(RoleUser record)
  {
    return (Integer)getSqlMapClientTemplate().insert("r_role_user.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(RoleUser record)
  {
    getSqlMapClientTemplate().insert("r_role_user.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(RoleUserCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("r_role_user.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public RoleUser selectByPrimaryKey(Integer id)
  {
    RoleUser key = new RoleUser();
    key.setId(id);
    RoleUser record = (RoleUser)getSqlMapClientTemplate().queryForObject("r_role_user.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(RoleUser record, RoleUserCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_role_user.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(RoleUser record, RoleUserCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_role_user.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(RoleUser record)
  {
    int rows = getSqlMapClientTemplate().update("r_role_user.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(RoleUser record)
  {
    int rows = getSqlMapClientTemplate().update("r_role_user.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends RoleUserCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, RoleUserCriteria example)
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
