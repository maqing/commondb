package com.commondb.db.dao.impl;

import com.commondb.db.bo.Role;
import com.commondb.db.bo.RoleCriteria;
import com.commondb.db.dao.RoleDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RoleDAOImpl
  extends SqlMapClientDaoSupport
  implements RoleDAO
{
  public int countByExample(RoleCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_role.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(RoleCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_role.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer roleId)
  {
    Role key = new Role();
    key.setRoleId(roleId);
    int rows = getSqlMapClientTemplate().delete("t_role.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public Integer insert(Role record)
  {
    return (Integer)getSqlMapClientTemplate().insert("t_role.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(Role record)
  {
    getSqlMapClientTemplate().insert("t_role.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(RoleCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_role.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public Role selectByPrimaryKey(Integer roleId)
  {
    Role key = new Role();
    key.setRoleId(roleId);
    Role record = (Role)getSqlMapClientTemplate().queryForObject("t_role.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(Role record, RoleCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_role.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(Role record, RoleCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_role.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(Role record)
  {
    int rows = getSqlMapClientTemplate().update("t_role.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(Role record)
  {
    int rows = getSqlMapClientTemplate().update("t_role.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends RoleCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, RoleCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
  
  public List<Role> getRoleByIdWithR(Integer roleId)
  {
    Map map = new HashMap();
    map.put("roleId", roleId);
    return getSqlMapClientTemplate().queryForList("t_role.getRoleByIdWithR", map);
  }
  
  public List<Role> getByMetaId(Integer metaId)
  {
    return getSqlMapClientTemplate().queryForList("t_role.getByMetaId", metaId);
  }
  
  public List<Role> getByUserId(Integer userId)
  {
    return getSqlMapClientTemplate().queryForList("t_role.getByUserId", userId);
  }
  
  public List<Role> getByRescId(Integer rescId)
  {
    return getSqlMapClientTemplate().queryForList("t_role.getByRescId", rescId);
  }
  
  public List<Role> getByMenuId(Integer menuId)
  {
    return getSqlMapClientTemplate().queryForList("t_role.getByMenuId", menuId);
  }
  
  public int getMaxId()
  {
    return ((Integer)getSqlMapClientTemplate().queryForObject("t_role.getMaxId", null)).intValue();
  }
}
