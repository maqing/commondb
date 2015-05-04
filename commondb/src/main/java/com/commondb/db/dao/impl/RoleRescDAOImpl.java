package com.commondb.db.dao.impl;

import com.commondb.db.bo.RoleRescCriteria;
import com.commondb.db.bo.RoleRescKey;
import com.commondb.db.dao.RoleRescDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RoleRescDAOImpl
  extends SqlMapClientDaoSupport
  implements RoleRescDAO
{
  public int countByExample(RoleRescCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("r_role_resc.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(RoleRescCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("r_role_resc.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(RoleRescKey key)
  {
    int rows = getSqlMapClientTemplate().delete("r_role_resc.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(RoleRescKey record)
  {
    getSqlMapClientTemplate().insert("r_role_resc.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(RoleRescKey record)
  {
    getSqlMapClientTemplate().insert("r_role_resc.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(RoleRescCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("r_role_resc.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public int updateByExampleSelective(RoleRescKey record, RoleRescCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_role_resc.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(RoleRescKey record, RoleRescCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_role_resc.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends RoleRescCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, RoleRescCriteria example)
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
