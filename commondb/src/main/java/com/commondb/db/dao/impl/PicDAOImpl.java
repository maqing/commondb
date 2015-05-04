package com.commondb.db.dao.impl;

import com.commondb.db.bo.Pic;
import com.commondb.db.bo.PicCriteria;
import com.commondb.db.dao.PicDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class PicDAOImpl
  extends SqlMapClientDaoSupport
  implements PicDAO
{
  public int countByExample(PicCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject(
      "t_pics.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(PicCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete(
      "t_pics.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer picId)
  {
    Pic key = new Pic();
    key.setPicId(picId);
    int rows = getSqlMapClientTemplate().delete(
      "t_pics.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(Pic record)
  {
    getSqlMapClientTemplate().insert("t_pics.ibatorgenerated_insert", 
      record);
  }
  
  public void insertSelective(Pic record)
  {
    getSqlMapClientTemplate().insert(
      "t_pics.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(PicCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList(
      "t_pics.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public Pic selectByPrimaryKey(Integer picId)
  {
    Pic key = new Pic();
    key.setPicId(picId);
    Pic record = (Pic)getSqlMapClientTemplate().queryForObject(
      "t_pics.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(Pic record, PicCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update(
      "t_pics.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(Pic record, PicCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update(
      "t_pics.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(Pic record)
  {
    int rows = getSqlMapClientTemplate().update(
      "t_pics.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(Pic record)
  {
    int rows = getSqlMapClientTemplate().update(
      "t_pics.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends PicCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, PicCriteria example)
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
