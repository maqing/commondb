package com.commondb.db.dao.impl;

import com.commondb.db.bo.Meta;
import com.commondb.db.bo.MetaCriteria;
import com.commondb.db.dao.MetaDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class MetaDAOImpl
  extends SqlMapClientDaoSupport
  implements MetaDAO
{
  public int countByExample(MetaCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_meta.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(MetaCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_meta.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer metaId)
  {
    Meta key = new Meta();
    key.setMetaId(metaId);
    int rows = getSqlMapClientTemplate().delete("t_meta.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public Integer insert(Meta record)
  {
    if (record.getMetaId() != null)
    {
      getSqlMapClientTemplate().insert("t_meta.ibatorgenerated_insertSelective", record);
      return record.getMetaId();
    }
    return (Integer)getSqlMapClientTemplate().insert("t_meta.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(Meta record)
  {
    getSqlMapClientTemplate().insert("t_meta.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(MetaCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_meta.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public Meta selectByPrimaryKey(Integer metaId)
  {
    Meta key = new Meta();
    key.setMetaId(metaId);
    Meta record = (Meta)getSqlMapClientTemplate().queryForObject("t_meta.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(Meta record, MetaCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(Meta record, MetaCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(Meta record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(Meta record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends MetaCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, MetaCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
  
  public List<Meta> getByRoleId(Integer roleId)
  {
    return getSqlMapClientTemplate().queryForList("t_meta.getByRoleId", roleId);
  }
  
  public Meta getMetaByIdWithCashRole(Integer metaId)
  {
    Map map = new HashMap();
    map.put("metaId", metaId);
    return (Meta)getSqlMapClientTemplate().queryForList("t_meta.getMetaByIdWithCashRole", map);
  }
  
  public List<Meta> getMetaByIdWithCashAttrDef(Integer metaId)
  {
    Map map = new HashMap();
    map.put("metaId", metaId);
    return getSqlMapClientTemplate().queryForList("t_meta.getMetaByIdWithCashAttrDef", map);
  }
  
  public List getMetaByIdWithCashAttrDefArr()
  {
    return getSqlMapClientTemplate().queryForList("t_meta.getMetaByIdWithCashAttrDefArr", null);
  }
}
