package com.commondb.db.dao.impl;

import com.commondb.db.bo.RoleMeta;
import com.commondb.db.bo.RoleMetaCriteria;
import com.commondb.db.dao.RoleMetaDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RoleMetaDAOImpl
  extends SqlMapClientDaoSupport
  implements RoleMetaDAO
{
  public int countByExample(RoleMetaCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("r_role_meta.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(RoleMetaCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("r_role_meta.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer id)
  {
    RoleMeta key = new RoleMeta();
    key.setId(id);
    int rows = getSqlMapClientTemplate().delete("r_role_meta.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public Integer insert(RoleMeta record)
  {
    return (Integer)getSqlMapClientTemplate().insert("r_role_meta.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(RoleMeta record)
  {
    getSqlMapClientTemplate().insert("r_role_meta.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(RoleMetaCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("r_role_meta.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public RoleMeta selectByPrimaryKey(Integer id)
  {
    RoleMeta key = new RoleMeta();
    key.setId(id);
    RoleMeta record = (RoleMeta)getSqlMapClientTemplate().queryForObject("r_role_meta.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(RoleMeta record, RoleMetaCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_role_meta.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(RoleMeta record, RoleMetaCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_role_meta.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(RoleMeta record)
  {
    int rows = getSqlMapClientTemplate().update("r_role_meta.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(RoleMeta record)
  {
    int rows = getSqlMapClientTemplate().update("r_role_meta.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends RoleMetaCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, RoleMetaCriteria example)
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
