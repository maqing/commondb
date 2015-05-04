package com.commondb.db.bo;

public class UserSimple
{
  private Integer userId;
  private String userName;
  private String pwd;
  private String userDesc;
  private Boolean disabled;
  
  public Integer getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public String getPwd()
  {
    return this.pwd;
  }
  
  public void setPwd(String pwd)
  {
    this.pwd = pwd;
  }
  
  public String getUserDesc()
  {
    return this.userDesc;
  }
  
  public void setUserDesc(String userDesc)
  {
    this.userDesc = userDesc;
  }
  
  public Boolean getDisabled()
  {
    return this.disabled;
  }
  
  public void setDisabled(Boolean disabled)
  {
    this.disabled = disabled;
  }
}
