package com.commondb.db.dao.impl;

import com.commondb.db.bo.CharacterData;
import com.commondb.db.bo.CharacterDataCriteria;
import com.commondb.db.dao.CharacterDataDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class CharacterDataDAOImpl
  extends SqlMapClientDaoSupport
  implements CharacterDataDAO
{
  public int countByExample(CharacterDataCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_character_data.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(CharacterDataCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_character_data.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer dataId)
  {
    CharacterData key = new CharacterData();
    key.setDataId(dataId);
    int rows = getSqlMapClientTemplate().delete("t_character_data.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public Integer insert(CharacterData record)
  {
    return (Integer)getSqlMapClientTemplate().insert("t_character_data.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(CharacterData record)
  {
    getSqlMapClientTemplate().insert("t_character_data.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(CharacterDataCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_character_data.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public CharacterData selectByPrimaryKey(Integer dataId)
  {
    CharacterData key = new CharacterData();
    key.setDataId(dataId);
    CharacterData record = (CharacterData)getSqlMapClientTemplate().queryForObject("t_character_data.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(CharacterData record, CharacterDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_character_data.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(CharacterData record, CharacterDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_character_data.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(CharacterData record)
  {
    int rows = getSqlMapClientTemplate().update("t_character_data.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(CharacterData record)
  {
    int rows = getSqlMapClientTemplate().update("t_character_data.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends CharacterDataCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, CharacterDataCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
  
  public int updateShare(CharacterData record)
  {
    int rows = getSqlMapClientTemplate().update("t_character_data.updateShare", record);
    return rows;
  }
  
  public int updateEnable(CharacterData record)
  {
    int rows = getSqlMapClientTemplate().update("t_character_data.updateEnable", record);
    return rows;
  }
  
  public List getByEntity(Integer metaId, String entityId)
  {
    Map map = new HashMap();
    map.put("metaId", metaId);
    map.put("entityId", entityId);
    List list = getSqlMapClientTemplate().queryForList("t_character_data.getByEntity", map);
    return list;
  }
}
