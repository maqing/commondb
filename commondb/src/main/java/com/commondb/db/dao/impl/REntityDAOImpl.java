package com.commondb.db.dao.impl;

import com.commondb.db.bo.REntity;
import com.commondb.db.bo.REntityCriteria;
import com.commondb.db.dao.REntityDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class REntityDAOImpl
  extends SqlMapClientDaoSupport
  implements REntityDAO
{
  public int countByExample(REntityCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("r_entity.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(REntityCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("r_entity.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(String id)
  {
    REntity key = new REntity();
    key.setId(id);
    int rows = getSqlMapClientTemplate().delete("r_entity.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(REntity record)
  {
    if (record.getId() == null) {
      record.setId(UUID.randomUUID().toString());
    }
    getSqlMapClientTemplate().insert("r_entity.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(REntity record)
  {
    getSqlMapClientTemplate().insert("r_entity.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(REntityCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("r_entity.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public REntity selectByPrimaryKey(String id)
  {
    REntity key = new REntity();
    key.setId(id);
    REntity record = (REntity)getSqlMapClientTemplate().queryForObject("r_entity.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(REntity record, REntityCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_entity.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(REntity record, REntityCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_entity.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(REntity record)
  {
    int rows = getSqlMapClientTemplate().update("r_entity.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(REntity record)
  {
    int rows = getSqlMapClientTemplate().update("r_entity.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends REntityCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, REntityCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
  
  public List getREntityById(Integer metaId, String entityId)
  {
    Map map = new HashMap();
    map.put("metaId", metaId);
    map.put("entityId", entityId);
    List list = getSqlMapClientTemplate().queryForList("r_entity.getREntityById", map);
    return list;
  }
}
