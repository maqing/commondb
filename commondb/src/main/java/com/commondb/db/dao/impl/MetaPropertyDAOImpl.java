package com.commondb.db.dao.impl;

import com.commondb.db.bo.MetaProperty;
import com.commondb.db.bo.MetaPropertyCriteria;
import com.commondb.db.dao.MetaPropertyDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class MetaPropertyDAOImpl
  extends SqlMapClientDaoSupport
  implements MetaPropertyDAO
{
  public int countByExample(MetaPropertyCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_meta_property.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(MetaPropertyCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_meta_property.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer propertyId)
  {
    MetaProperty key = new MetaProperty();
    key.setPropertyId(propertyId);
    int rows = getSqlMapClientTemplate().delete("t_meta_property.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public Integer insert(MetaProperty record)
  {
    return (Integer)getSqlMapClientTemplate().insert("t_meta_property.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(MetaProperty record)
  {
    getSqlMapClientTemplate().insert("t_meta_property.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(MetaPropertyCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_meta_property.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public MetaProperty selectByPrimaryKey(Integer propertyId)
  {
    MetaProperty key = new MetaProperty();
    key.setPropertyId(propertyId);
    MetaProperty record = (MetaProperty)getSqlMapClientTemplate().queryForObject("t_meta_property.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(MetaProperty record, MetaPropertyCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_property.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(MetaProperty record, MetaPropertyCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_property.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(MetaProperty record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_property.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(MetaProperty record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_property.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends MetaPropertyCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, MetaPropertyCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
  
  public int updateEnable(MetaProperty record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_property.updateEnable", record);
    return rows;
  }
}
