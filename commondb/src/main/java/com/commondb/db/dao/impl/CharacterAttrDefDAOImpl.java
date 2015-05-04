package com.commondb.db.dao.impl;

import com.commondb.db.bo.CharacterAttrDef;
import com.commondb.db.bo.CharacterAttrDefCriteria;
import com.commondb.db.dao.CharacterAttrDefDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class CharacterAttrDefDAOImpl
  extends SqlMapClientDaoSupport
  implements CharacterAttrDefDAO
{
  public int countByExample(CharacterAttrDefCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_meta_character_attr_def.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(CharacterAttrDefCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_meta_character_attr_def.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer attrId)
  {
    CharacterAttrDef key = new CharacterAttrDef();
    key.setAttrId(attrId);
    int rows = getSqlMapClientTemplate().delete("t_meta_character_attr_def.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public Integer insert(CharacterAttrDef record)
  {
    return (Integer)getSqlMapClientTemplate().insert("t_meta_character_attr_def.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(CharacterAttrDef record)
  {
    getSqlMapClientTemplate().insert("t_meta_character_attr_def.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(CharacterAttrDefCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_meta_character_attr_def.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public CharacterAttrDef selectByPrimaryKey(Integer attrId)
  {
    CharacterAttrDef key = new CharacterAttrDef();
    key.setAttrId(attrId);
    CharacterAttrDef record = (CharacterAttrDef)getSqlMapClientTemplate().queryForObject("t_meta_character_attr_def.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(CharacterAttrDef record, CharacterAttrDefCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_character_attr_def.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(CharacterAttrDef record, CharacterAttrDefCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_character_attr_def.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(CharacterAttrDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_character_attr_def.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(CharacterAttrDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_character_attr_def.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends CharacterAttrDefCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, CharacterAttrDefCriteria example)
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
