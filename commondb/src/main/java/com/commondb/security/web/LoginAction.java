package com.commondb.security.web;

import com.commondb.db.bo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class LoginAction
  extends ActionSupport
  implements Preparable
{
  private User user;
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public void prepare()
    throws Exception
  {}
  
  public String login()
  {
    return "success";
  }
}
