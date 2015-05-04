package com.commondb.db.dao.impl;

import com.commondb.db.bo.CharacterAttrValue;
import com.commondb.db.bo.CharacterAttrValueCriteria;
import com.commondb.db.dao.CharacterAttrValueDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class CharacterAttrValueDAOImpl
  extends SqlMapClientDaoSupport
  implements CharacterAttrValueDAO
{
  public int countByExample(CharacterAttrValueCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_meta_character_attr_value.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(CharacterAttrValueCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_meta_character_attr_value.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer valueId)
  {
    CharacterAttrValue key = new CharacterAttrValue();
    key.setValueId(valueId);
    int rows = getSqlMapClientTemplate().delete("t_meta_character_attr_value.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(CharacterAttrValue record)
  {
    getSqlMapClientTemplate().insert("t_meta_character_attr_value.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(CharacterAttrValue record)
  {
    getSqlMapClientTemplate().insert("t_meta_character_attr_value.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(CharacterAttrValueCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_meta_character_attr_value.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public CharacterAttrValue selectByPrimaryKey(Integer valueId)
  {
    CharacterAttrValue key = new CharacterAttrValue();
    key.setValueId(valueId);
    CharacterAttrValue record = (CharacterAttrValue)getSqlMapClientTemplate().queryForObject("t_meta_character_attr_value.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(CharacterAttrValue record, CharacterAttrValueCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_character_attr_value.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(CharacterAttrValue record, CharacterAttrValueCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_character_attr_value.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(CharacterAttrValue record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_character_attr_value.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(CharacterAttrValue record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_character_attr_value.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends CharacterAttrValueCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, CharacterAttrValueCriteria example)
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
