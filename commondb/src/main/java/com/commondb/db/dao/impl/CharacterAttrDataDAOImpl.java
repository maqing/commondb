package com.commondb.db.dao.impl;

import com.commondb.db.bo.CharacterAttrData;
import com.commondb.db.bo.CharacterAttrDataCriteria;
import com.commondb.db.dao.CharacterAttrDataDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class CharacterAttrDataDAOImpl
  extends SqlMapClientDaoSupport
  implements CharacterAttrDataDAO
{
  public int countByExample(CharacterAttrDataCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_character_attr_data.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(CharacterAttrDataCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_character_attr_data.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer dataId)
  {
    CharacterAttrData key = new CharacterAttrData();
    key.setDataId(dataId);
    int rows = getSqlMapClientTemplate().delete("t_character_attr_data.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(CharacterAttrData record)
  {
    getSqlMapClientTemplate().insert("t_character_attr_data.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(CharacterAttrData record)
  {
    getSqlMapClientTemplate().insert("t_character_attr_data.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(CharacterAttrDataCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_character_attr_data.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public CharacterAttrData selectByPrimaryKey(Integer dataId)
  {
    CharacterAttrData key = new CharacterAttrData();
    key.setDataId(dataId);
    CharacterAttrData record = (CharacterAttrData)getSqlMapClientTemplate().queryForObject("t_character_attr_data.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(CharacterAttrData record, CharacterAttrDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_character_attr_data.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(CharacterAttrData record, CharacterAttrDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_character_attr_data.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(CharacterAttrData record)
  {
    int rows = getSqlMapClientTemplate().update("t_character_attr_data.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(CharacterAttrData record)
  {
    int rows = getSqlMapClientTemplate().update("t_character_attr_data.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends CharacterAttrDataCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, CharacterAttrDataCriteria example)
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
