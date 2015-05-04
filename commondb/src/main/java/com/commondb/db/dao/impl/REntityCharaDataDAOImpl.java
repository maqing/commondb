package com.commondb.db.dao.impl;

import com.commondb.db.bo.REntityCharaData;
import com.commondb.db.bo.REntityCharaDataCriteria;
import com.commondb.db.dao.REntityCharaDataDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class REntityCharaDataDAOImpl
  extends SqlMapClientDaoSupport
  implements REntityCharaDataDAO
{
  public int countByExample(REntityCharaDataCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("r_entity_chara_data.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(REntityCharaDataCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("r_entity_chara_data.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(String id)
  {
    REntityCharaData key = new REntityCharaData();
    key.setId(id);
    int rows = getSqlMapClientTemplate().delete("r_entity_chara_data.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(REntityCharaData record)
  {
    if (record.getId() == null) {
      record.setId(UUID.randomUUID().toString());
    }
    getSqlMapClientTemplate().insert("r_entity_chara_data.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(REntityCharaData record)
  {
    getSqlMapClientTemplate().insert("r_entity_chara_data.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(REntityCharaDataCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("r_entity_chara_data.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public REntityCharaData selectByPrimaryKey(String id)
  {
    REntityCharaData key = new REntityCharaData();
    key.setId(id);
    REntityCharaData record = (REntityCharaData)getSqlMapClientTemplate().queryForObject("r_entity_chara_data.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(REntityCharaData record, REntityCharaDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_entity_chara_data.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(REntityCharaData record, REntityCharaDataCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_entity_chara_data.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(REntityCharaData record)
  {
    int rows = getSqlMapClientTemplate().update("r_entity_chara_data.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(REntityCharaData record)
  {
    int rows = getSqlMapClientTemplate().update("r_entity_chara_data.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends REntityCharaDataCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, REntityCharaDataCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
  
  public List selectByUser(Integer metaId, String entityId, Integer userId)
  {
    Map map = new HashMap();
    map.put("metaId", metaId);
    map.put("entityId", entityId);
    map.put("userId", userId);
    List list = getSqlMapClientTemplate().queryForList("r_entity_chara_data.selectByUser", map);
    return list;
  }
}
