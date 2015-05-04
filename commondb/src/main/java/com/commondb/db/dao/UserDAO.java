package com.commondb.db.dao;

import com.commondb.db.bo.User;
import com.commondb.db.bo.UserCriteria;
import java.util.List;

public abstract interface UserDAO
{
  public abstract int countByExample(UserCriteria paramUserCriteria);
  
  public abstract int deleteByExample(UserCriteria paramUserCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract Integer insert(User paramUser);
  
  public abstract void insertSelective(User paramUser);
  
  public abstract List selectByExample(UserCriteria paramUserCriteria);
  
  public abstract User selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(User paramUser, UserCriteria paramUserCriteria);
  
  public abstract int updateByExample(User paramUser, UserCriteria paramUserCriteria);
  
  public abstract int updateByPrimaryKeySelective(User paramUser);
  
  public abstract int updateByPrimaryKey(User paramUser);
  
  public abstract List<User> getUserByIdWithCashRole(Integer paramInteger);
  
  public abstract List<User> getUserByNameWithCashRole(String paramString);
  
  public abstract List<User> getByRoleId(Integer paramInteger);
  
  public abstract int stopUser(User paramUser);
}
