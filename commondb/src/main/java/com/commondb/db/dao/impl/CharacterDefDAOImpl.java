package com.commondb.db.dao.impl;

import com.commondb.db.bo.CharacterDef;
import com.commondb.db.bo.CharacterDefCriteria;
import com.commondb.db.dao.CharacterDefDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class CharacterDefDAOImpl
  extends SqlMapClientDaoSupport
  implements CharacterDefDAO
{
  public int countByExample(CharacterDefCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_character_def.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(CharacterDefCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_character_def.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer charaId)
  {
    CharacterDef key = new CharacterDef();
    key.setCharaId(charaId);
    int rows = getSqlMapClientTemplate().delete("t_character_def.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public Integer insert(CharacterDef record)
  {
    return (Integer)getSqlMapClientTemplate().insert("t_character_def.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(CharacterDef record)
  {
    getSqlMapClientTemplate().insert("t_character_def.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(CharacterDefCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_character_def.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public CharacterDef selectByPrimaryKey(Integer charaId)
  {
    CharacterDef key = new CharacterDef();
    key.setCharaId(charaId);
    CharacterDef record = (CharacterDef)getSqlMapClientTemplate().queryForObject("t_character_def.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(CharacterDef record, CharacterDefCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_character_def.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(CharacterDef record, CharacterDefCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_character_def.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(CharacterDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_character_def.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(CharacterDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_character_def.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends CharacterDefCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, CharacterDefCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
  
  public int updateShare(CharacterDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_character_def.updateShare", record);
    return rows;
  }
  
  public int updateEnable(CharacterDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_character_def.updateEnable", record);
    return rows;
  }
  
  public int updateCheckMultiple(CharacterDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_character_def.updateCheckMultiple", record);
    return rows;
  }
  
  public List getByMetaId(Integer metaId)
  {
    List list = getSqlMapClientTemplate().queryForList("t_character_def.getByMetaId", metaId);
    return list;
  }
}
