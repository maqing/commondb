package com.commondb.db.dao.impl;

import com.commondb.db.bo.PicAttrData;
import com.commondb.db.bo.PicAttrDataCriteria;
import com.commondb.db.dao.PicAttrDataDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class PicAttrDataDAOImpl
  extends SqlMapClientDaoSupport
  implements PicAttrDataDAO
{
  public int countByExample(PicAttrDataCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_pic_attr_data.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(PicAttrDataCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_pic_attr_data.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer dataId)
  {
    PicAttrData key = new PicAttrData();
    key.setDataId(dataId);
    int rows = getSqlMapClientTemplate().delete("t_pic_attr_data.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(PicAttrData record)
  {
    getSqlMapClientTemplate().insert("t_pic_attr_data.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(PicAttrData record)
  {
    getSqlMapClientTemplate().insert("t_pic_attr_data.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(PicAttrDataCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_pic_attr_data.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public PicAttrData selectByPrimaryKey(Integer dataId)
  {
    PicAttrData key = new PicAttrData();
    key.setDataId(dataId);
    PicAttrData record = (PicAttrData)getSqlMapClientTemplate().queryForObject("t_pic_attr_data.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(PicAttrData record, PicAttrDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_pic_attr_data.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(PicAttrData record, PicAttrDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_pic_attr_data.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(PicAttrData record)
  {
    int rows = getSqlMapClientTemplate().update("t_pic_attr_data.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(PicAttrData record)
  {
    int rows = getSqlMapClientTemplate().update("t_pic_attr_data.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends PicAttrDataCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, PicAttrDataCriteria example)
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
