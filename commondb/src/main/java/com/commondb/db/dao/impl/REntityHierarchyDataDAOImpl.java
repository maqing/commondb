package com.commondb.db.dao.impl;

import com.commondb.db.bo.REntityHierarchyData;
import com.commondb.db.bo.REntityHierarchyDataCriteria;
import com.commondb.db.dao.REntityHierarchyDataDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class REntityHierarchyDataDAOImpl
  extends SqlMapClientDaoSupport
  implements REntityHierarchyDataDAO
{
  public int countByExample(REntityHierarchyDataCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("r_entity_hierarchy_data.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(REntityHierarchyDataCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("r_entity_hierarchy_data.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(String id)
  {
    REntityHierarchyData key = new REntityHierarchyData();
    key.setId(id);
    int rows = getSqlMapClientTemplate().delete("r_entity_hierarchy_data.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(REntityHierarchyData record)
  {
    getSqlMapClientTemplate().insert("r_entity_hierarchy_data.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(REntityHierarchyData record)
  {
    getSqlMapClientTemplate().insert("r_entity_hierarchy_data.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(REntityHierarchyDataCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("r_entity_hierarchy_data.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public REntityHierarchyData selectByPrimaryKey(String id)
  {
    REntityHierarchyData key = new REntityHierarchyData();
    key.setId(id);
    REntityHierarchyData record = (REntityHierarchyData)getSqlMapClientTemplate().queryForObject("r_entity_hierarchy_data.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(REntityHierarchyData record, REntityHierarchyDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_entity_hierarchy_data.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(REntityHierarchyData record, REntityHierarchyDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_entity_hierarchy_data.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(REntityHierarchyData record)
  {
    int rows = getSqlMapClientTemplate().update("r_entity_hierarchy_data.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(REntityHierarchyData record)
  {
    int rows = getSqlMapClientTemplate().update("r_entity_hierarchy_data.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends REntityHierarchyDataCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, REntityHierarchyDataCriteria example)
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
