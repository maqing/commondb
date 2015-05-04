package com.commondb.db.dao.impl;

import com.commondb.db.bo.User;
import com.commondb.db.bo.UserCriteria;
import com.commondb.db.dao.UserDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UserDAOImpl
  extends SqlMapClientDaoSupport
  implements UserDAO
{
  public int countByExample(UserCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_user.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(UserCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_user.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer userId)
  {
    User key = new User();
    key.setUserId(userId);
    int rows = getSqlMapClientTemplate().delete("t_user.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public Integer insert(User record)
  {
    return (Integer)getSqlMapClientTemplate().insert("t_user.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(User record)
  {
    getSqlMapClientTemplate().insert("t_user.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(UserCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_user.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public User selectByPrimaryKey(Integer userId)
  {
    User key = new User();
    key.setUserId(userId);
    User record = (User)getSqlMapClientTemplate().queryForObject("t_user.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(User record, UserCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_user.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(User record, UserCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_user.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(User record)
  {
    int rows = getSqlMapClientTemplate().update("t_user.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(User record)
  {
    int rows = getSqlMapClientTemplate().update("t_user.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends UserCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, UserCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
  
  public List<User> getUserByNameWithCashRole(String userName)
  {
    Map map = new HashMap();
    map.put("userName", userName);
    return getSqlMapClientTemplate().queryForList("t_user.getUserByNameWithCashRole", map);
  }
  
  public List<User> getUserByIdWithCashRole(Integer userId)
  {
    Map map = new HashMap();
    map.put("userId", userId);
    return getSqlMapClientTemplate().queryForList("t_user.getUserByIdWithCashRole", map);
  }
  
  public List<User> getByRoleId(Integer roleId)
  {
    return getSqlMapClientTemplate().queryForList("t_user.getByRoleId", roleId);
  }
  
  public int stopUser(User record)
  {
    int rows = getSqlMapClientTemplate().update("t_user.stopUser", record);
    return rows;
  }
}
