package com.commondb.db.dao.impl;

import com.commondb.db.bo.HierarchyAttrDef;
import com.commondb.db.bo.HierarchyAttrDefCriteria;
import com.commondb.db.dao.HierarchyAttrDefDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class HierarchyAttrDefDAOImpl
  extends SqlMapClientDaoSupport
  implements HierarchyAttrDefDAO
{
  public int countByExample(HierarchyAttrDefCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_meta_hierarchy_attr_def.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(HierarchyAttrDefCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_meta_hierarchy_attr_def.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer attrId)
  {
    HierarchyAttrDef key = new HierarchyAttrDef();
    key.setAttrId(attrId);
    int rows = getSqlMapClientTemplate().delete("t_meta_hierarchy_attr_def.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public Integer insert(HierarchyAttrDef record)
  {
    return (Integer)getSqlMapClientTemplate().insert("t_meta_hierarchy_attr_def.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(HierarchyAttrDef record)
  {
    getSqlMapClientTemplate().insert("t_meta_hierarchy_attr_def.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(HierarchyAttrDefCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_meta_hierarchy_attr_def.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public HierarchyAttrDef selectByPrimaryKey(Integer attrId)
  {
    HierarchyAttrDef key = new HierarchyAttrDef();
    key.setAttrId(attrId);
    HierarchyAttrDef record = (HierarchyAttrDef)getSqlMapClientTemplate().queryForObject("t_meta_hierarchy_attr_def.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(HierarchyAttrDef record, HierarchyAttrDefCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_hierarchy_attr_def.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(HierarchyAttrDef record, HierarchyAttrDefCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_hierarchy_attr_def.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(HierarchyAttrDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_hierarchy_attr_def.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(HierarchyAttrDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_hierarchy_attr_def.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends HierarchyAttrDefCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, HierarchyAttrDefCriteria example)
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
