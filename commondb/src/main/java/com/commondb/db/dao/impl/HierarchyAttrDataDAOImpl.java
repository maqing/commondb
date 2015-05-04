package com.commondb.db.dao.impl;

import com.commondb.db.bo.HierarchyAttrData;
import com.commondb.db.bo.HierarchyAttrDataCriteria;
import com.commondb.db.dao.HierarchyAttrDataDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class HierarchyAttrDataDAOImpl
  extends SqlMapClientDaoSupport
  implements HierarchyAttrDataDAO
{
  public int countByExample(HierarchyAttrDataCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_hierarchy_attr_data.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(HierarchyAttrDataCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_hierarchy_attr_data.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer dataId)
  {
    HierarchyAttrData key = new HierarchyAttrData();
    key.setDataId(dataId);
    int rows = getSqlMapClientTemplate().delete("t_hierarchy_attr_data.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(HierarchyAttrData record)
  {
    getSqlMapClientTemplate().insert("t_hierarchy_attr_data.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(HierarchyAttrData record)
  {
    getSqlMapClientTemplate().insert("t_hierarchy_attr_data.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(HierarchyAttrDataCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_hierarchy_attr_data.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public HierarchyAttrData selectByPrimaryKey(Integer dataId)
  {
    HierarchyAttrData key = new HierarchyAttrData();
    key.setDataId(dataId);
    HierarchyAttrData record = (HierarchyAttrData)getSqlMapClientTemplate().queryForObject("t_hierarchy_attr_data.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(HierarchyAttrData record, HierarchyAttrDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_hierarchy_attr_data.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(HierarchyAttrData record, HierarchyAttrDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_hierarchy_attr_data.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(HierarchyAttrData record)
  {
    int rows = getSqlMapClientTemplate().update("t_hierarchy_attr_data.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(HierarchyAttrData record)
  {
    int rows = getSqlMapClientTemplate().update("t_hierarchy_attr_data.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends HierarchyAttrDataCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, HierarchyAttrDataCriteria example)
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
