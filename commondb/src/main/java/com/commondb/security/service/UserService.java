package com.commondb.security.service;

import com.commondb.db.bo.User;

import java.util.List;

public abstract interface UserService
{
  public abstract List findUsers(Integer paramInteger);
  
  public abstract List findUsersByName(String userName);
  
  public abstract Integer createUser(String paramString1, String paramString2, String paramString3, Boolean paramBoolean, Integer[] paramArrayOfInteger);
  
  public abstract void updateUser(Integer paramInteger, String paramString1, String paramString2, String paramString3, Boolean paramBoolean, Integer[] paramArrayOfInteger);
  
  public abstract void delUser(Integer paramInteger);
  
  public abstract void stopUser(Integer paramInteger);
  
  public abstract int modifyUserInfo(String paramString1, String paramString2, String paramString3);
  
  public abstract int changePassword(String paramString1, String paramString2, String paramString3);
  
  public abstract int resetPassword(Integer paramInteger, String paramString);
  
  public abstract User findUserById(Integer paramInteger);
  
  public abstract List<User> getAllUser(User paramUser);
  
  public abstract void updateUser(User paramUser);
}
