package com.commondb.db.dao.impl;

import com.commondb.db.bo.RoleMenuCriteria;
import com.commondb.db.bo.RoleMenuKey;
import com.commondb.db.dao.RoleMenuDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RoleMenuDAOImpl
  extends SqlMapClientDaoSupport
  implements RoleMenuDAO
{
  public int countByExample(RoleMenuCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("r_role_menu.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(RoleMenuCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("r_role_menu.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(RoleMenuKey key)
  {
    int rows = getSqlMapClientTemplate().delete("r_role_menu.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(RoleMenuKey record)
  {
    getSqlMapClientTemplate().insert("r_role_menu.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(RoleMenuKey record)
  {
    getSqlMapClientTemplate().insert("r_role_menu.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(RoleMenuCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("r_role_menu.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public int updateByExampleSelective(RoleMenuKey record, RoleMenuCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_role_menu.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(RoleMenuKey record, RoleMenuCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_role_menu.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends RoleMenuCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, RoleMenuCriteria example)
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
