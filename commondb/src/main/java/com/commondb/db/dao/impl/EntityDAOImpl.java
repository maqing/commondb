package com.commondb.db.dao.impl;

import com.commondb.db.bo.Entity;
import com.commondb.db.bo.EntityCriteria;
import com.commondb.db.dao.EntityDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class EntityDAOImpl
  extends SqlMapClientDaoSupport
  implements EntityDAO
{
  public int countByExample(EntityCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_entity_data.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(EntityCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_entity_data.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer entityId)
  {
    Entity key = new Entity();
    key.setEntityId(entityId);
    int rows = getSqlMapClientTemplate().delete("t_entity_data.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public Integer insert(Entity record)
  {
    return (Integer)getSqlMapClientTemplate().insert("t_entity_data.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(Entity record)
  {
    getSqlMapClientTemplate().insert("t_entity_data.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(EntityCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_entity_data.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public Entity selectByPrimaryKey(Integer entityId)
  {
    Entity key = new Entity();
    key.setEntityId(entityId);
    Entity record = (Entity)getSqlMapClientTemplate().queryForObject("t_entity_data.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(Entity record, EntityCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_entity_data.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(Entity record, EntityCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_entity_data.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(Entity record)
  {
    int rows = getSqlMapClientTemplate().update("t_entity_data.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(Entity record)
  {
    int rows = getSqlMapClientTemplate().update("t_entity_data.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends EntityCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, EntityCriteria example)
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
