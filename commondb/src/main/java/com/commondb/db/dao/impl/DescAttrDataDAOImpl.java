package com.commondb.db.dao.impl;

import com.commondb.db.bo.DescAttrData;
import com.commondb.db.bo.DescAttrDataCriteria;
import com.commondb.db.dao.DescAttrDataDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class DescAttrDataDAOImpl
  extends SqlMapClientDaoSupport
  implements DescAttrDataDAO
{
  public int countByExample(DescAttrDataCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_desc_attr_data.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(DescAttrDataCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_desc_attr_data.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer dataId)
  {
    DescAttrData key = new DescAttrData();
    key.setDataId(dataId);
    int rows = getSqlMapClientTemplate().delete("t_desc_attr_data.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(DescAttrData record)
  {
    getSqlMapClientTemplate().insert("t_desc_attr_data.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(DescAttrData record)
  {
    getSqlMapClientTemplate().insert("t_desc_attr_data.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExampleWithBLOBs(DescAttrDataCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_desc_attr_data.ibatorgenerated_selectByExampleWithBLOBs", example);
    return list;
  }
  
  public List selectByExampleWithoutBLOBs(DescAttrDataCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_desc_attr_data.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public DescAttrData selectByPrimaryKey(Integer dataId)
  {
    DescAttrData key = new DescAttrData();
    key.setDataId(dataId);
    DescAttrData record = (DescAttrData)getSqlMapClientTemplate().queryForObject("t_desc_attr_data.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(DescAttrData record, DescAttrDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_desc_attr_data.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExampleWithBLOBs(DescAttrData record, DescAttrDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_desc_attr_data.ibatorgenerated_updateByExampleWithBLOBs", parms);
    return rows;
  }
  
  public int updateByExampleWithoutBLOBs(DescAttrData record, DescAttrDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_desc_attr_data.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(DescAttrData record)
  {
    int rows = getSqlMapClientTemplate().update("t_desc_attr_data.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKeyWithBLOBs(DescAttrData record)
  {
    int rows = getSqlMapClientTemplate().update("t_desc_attr_data.ibatorgenerated_updateByPrimaryKeyWithBLOBs", record);
    return rows;
  }
  
  public int updateByPrimaryKeyWithoutBLOBs(DescAttrData record)
  {
    int rows = getSqlMapClientTemplate().update("t_desc_attr_data.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends DescAttrDataCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, DescAttrDataCriteria example)
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
