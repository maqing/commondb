package com.commondb.apply.dao.impl;

import com.commondb.apply.bo.ApplyMenu;
import com.commondb.apply.bo.ApplyMenuCriteria;
import com.commondb.apply.dao.ApplyMenuDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ApplyMenuDAOImpl
  extends SqlMapClientDaoSupport
  implements ApplyMenuDAO
{
  public int countByExample(ApplyMenuCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_apply_menu.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(ApplyMenuCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_apply_menu.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer applyMenuId)
  {
    ApplyMenu key = new ApplyMenu();
    key.setApplyMenuId(applyMenuId);
    int rows = getSqlMapClientTemplate().delete("t_apply_menu.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(ApplyMenu record)
  {
    getSqlMapClientTemplate().insert("t_apply_menu.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(ApplyMenu record)
  {
    getSqlMapClientTemplate().insert("t_apply_menu.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(ApplyMenuCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_apply_menu.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public ApplyMenu selectByPrimaryKey(Integer applyMenuId)
  {
    ApplyMenu key = new ApplyMenu();
    key.setApplyMenuId(applyMenuId);
    ApplyMenu record = (ApplyMenu)getSqlMapClientTemplate().queryForObject("t_apply_menu.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(ApplyMenu record, ApplyMenuCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_apply_menu.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(ApplyMenu record, ApplyMenuCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_apply_menu.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(ApplyMenu record)
  {
    int rows = getSqlMapClientTemplate().update("t_apply_menu.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(ApplyMenu record)
  {
    int rows = getSqlMapClientTemplate().update("t_apply_menu.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends ApplyMenuCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, ApplyMenuCriteria example)
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
