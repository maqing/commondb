package com.commondb.db.dao.impl;

import com.commondb.db.bo.Resc;
import com.commondb.db.bo.RescCriteria;
import com.commondb.db.dao.RescDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RescDAOImpl
  extends SqlMapClientDaoSupport
  implements RescDAO
{
  public int countByExample(RescCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_resc.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(RescCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_resc.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer rescId)
  {
    Resc key = new Resc();
    key.setRescId(rescId);
    int rows = getSqlMapClientTemplate().delete("t_resc.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(Resc record)
  {
    getSqlMapClientTemplate().insert("t_resc.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(Resc record)
  {
    getSqlMapClientTemplate().insert("t_resc.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(RescCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_resc.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public Resc selectByPrimaryKey(Integer rescId)
  {
    Resc key = new Resc();
    key.setRescId(rescId);
    Resc record = (Resc)getSqlMapClientTemplate().queryForObject("t_resc.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(Resc record, RescCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_resc.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(Resc record, RescCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_resc.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(Resc record)
  {
    int rows = getSqlMapClientTemplate().update("t_resc.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(Resc record)
  {
    int rows = getSqlMapClientTemplate().update("t_resc.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends RescCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, RescCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
  
  public List<Resc> getRescByIdWithCashRole(Integer rescId)
  {
    Map map = new HashMap();
    map.put("rescId", rescId);
    return getSqlMapClientTemplate().queryForList("t_resc.getRescByIdWithCashRole", map);
  }
  
  public List<Resc> getByRoleId(Integer roleId)
  {
    return getSqlMapClientTemplate().queryForList("t_resc.getByRoleId", roleId);
  }
}
