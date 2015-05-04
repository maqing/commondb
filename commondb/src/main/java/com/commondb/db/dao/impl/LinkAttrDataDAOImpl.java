package com.commondb.db.dao.impl;

import com.commondb.db.bo.LinkAttrData;
import com.commondb.db.bo.LinkAttrDataCriteria;
import com.commondb.db.dao.LinkAttrDataDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class LinkAttrDataDAOImpl
  extends SqlMapClientDaoSupport
  implements LinkAttrDataDAO
{
  public int countByExample(LinkAttrDataCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_link_attr_data.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(LinkAttrDataCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_link_attr_data.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer dataId)
  {
    LinkAttrData key = new LinkAttrData();
    key.setDataId(dataId);
    int rows = getSqlMapClientTemplate().delete("t_link_attr_data.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(LinkAttrData record)
  {
    getSqlMapClientTemplate().insert("t_link_attr_data.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(LinkAttrData record)
  {
    getSqlMapClientTemplate().insert("t_link_attr_data.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(LinkAttrDataCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_link_attr_data.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public LinkAttrData selectByPrimaryKey(Integer dataId)
  {
    LinkAttrData key = new LinkAttrData();
    key.setDataId(dataId);
    LinkAttrData record = (LinkAttrData)getSqlMapClientTemplate().queryForObject("t_link_attr_data.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(LinkAttrData record, LinkAttrDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_link_attr_data.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(LinkAttrData record, LinkAttrDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_link_attr_data.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(LinkAttrData record)
  {
    int rows = getSqlMapClientTemplate().update("t_link_attr_data.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(LinkAttrData record)
  {
    int rows = getSqlMapClientTemplate().update("t_link_attr_data.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends LinkAttrDataCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, LinkAttrDataCriteria example)
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
