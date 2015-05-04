package com.commondb.db.dao.impl;

import com.commondb.db.bo.HierarchyAttrValue;
import com.commondb.db.bo.HierarchyAttrValueCriteria;
import com.commondb.db.bo.HierarchyAttrValueCriteria.Criteria;
import com.commondb.db.dao.HierarchyAttrValueDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class HierarchyAttrValueDAOImpl
  extends SqlMapClientDaoSupport
  implements HierarchyAttrValueDAO
{
  public int countByExample(HierarchyAttrValueCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_hierarchy_attr_value.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(HierarchyAttrValueCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_hierarchy_attr_value.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(String valueId)
  {
    HierarchyAttrValue key = new HierarchyAttrValue();
    key.setValueId(valueId);
    int rows = getSqlMapClientTemplate().delete("t_hierarchy_attr_value.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(HierarchyAttrValue record)
  {
    getSqlMapClientTemplate().insert("t_hierarchy_attr_value.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(HierarchyAttrValue record)
  {
    getSqlMapClientTemplate().insert("t_hierarchy_attr_value.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(HierarchyAttrValueCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_hierarchy_attr_value.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public HierarchyAttrValue selectByPrimaryKey(String valueId)
  {
    HierarchyAttrValue key = new HierarchyAttrValue();
    key.setValueId(valueId);
    HierarchyAttrValue record = (HierarchyAttrValue)getSqlMapClientTemplate().queryForObject("t_hierarchy_attr_value.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(HierarchyAttrValue record, HierarchyAttrValueCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_hierarchy_attr_value.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(HierarchyAttrValue record, HierarchyAttrValueCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_hierarchy_attr_value.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(HierarchyAttrValue record)
  {
    int rows = getSqlMapClientTemplate().update("t_hierarchy_attr_value.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(HierarchyAttrValue record)
  {
    int rows = getSqlMapClientTemplate().update("t_hierarchy_attr_value.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends HierarchyAttrValueCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, HierarchyAttrValueCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
  
  public String getHierPathString(Integer attrId, String valueid)
  {
    HierarchyAttrValueCriteria criteria = new HierarchyAttrValueCriteria();
    criteria.createCriteria()
      .andAttrIdEqualTo(attrId)
      .andValueIdEqualTo(valueid);
    
    String displayStr = "";
    List l = selectByExample(criteria);
    HierarchyAttrValue hValue = (HierarchyAttrValue)l.get(0);
    
    displayStr = hValue.getContent();
    while (hValue.getParentId() != null)
    {
      criteria = new HierarchyAttrValueCriteria();
      criteria.createCriteria()
        .andAttrIdEqualTo(attrId)
        .andValueIdEqualTo(hValue.getParentId());
      l = selectByExample(criteria);
      hValue = (HierarchyAttrValue)l.get(0);
      
      displayStr = hValue.getContent() + "\\" + displayStr;
    }
    return displayStr;
  }
}
