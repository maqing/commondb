package com.commondb.db.dao.impl;

import com.commondb.db.bo.MetaDescAttrStat;
import com.commondb.db.bo.MetaDescAttrStatCriteria;
import com.commondb.db.dao.MetaDescAttrStatDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class MetaDescAttrStatDAOImpl
  extends SqlMapClientDaoSupport
  implements MetaDescAttrStatDAO
{
  public int countByExample(MetaDescAttrStatCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_meta_desc_attr_stat.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(MetaDescAttrStatCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_meta_desc_attr_stat.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer statId)
  {
    MetaDescAttrStat key = new MetaDescAttrStat();
    key.setStatId(statId);
    int rows = getSqlMapClientTemplate().delete("t_meta_desc_attr_stat.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(MetaDescAttrStat record)
  {
    getSqlMapClientTemplate().insert("t_meta_desc_attr_stat.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(MetaDescAttrStat record)
  {
    getSqlMapClientTemplate().insert("t_meta_desc_attr_stat.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(MetaDescAttrStatCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_meta_desc_attr_stat.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public MetaDescAttrStat selectByPrimaryKey(Integer statId)
  {
    MetaDescAttrStat key = new MetaDescAttrStat();
    key.setStatId(statId);
    MetaDescAttrStat record = (MetaDescAttrStat)getSqlMapClientTemplate().queryForObject("t_meta_desc_attr_stat.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(MetaDescAttrStat record, MetaDescAttrStatCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_desc_attr_stat.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(MetaDescAttrStat record, MetaDescAttrStatCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_desc_attr_stat.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(MetaDescAttrStat record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_desc_attr_stat.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(MetaDescAttrStat record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_desc_attr_stat.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends MetaDescAttrStatCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, MetaDescAttrStatCriteria example)
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
