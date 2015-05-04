package com.commondb.security.web;

import com.commondb.common.JsonResult;
import com.commondb.db.bo.User;
import com.commondb.security.service.SecurityUserHolder;
import com.commondb.security.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class UserAction
  extends ActionSupport
  implements Preparable
{
  private JsonResult result = new JsonResult();
  private UserService userService;
  private Integer userId;
  private String userName;
  private String userDesc;
  private String pwd;
  private String curPwd;
  private Boolean disabled;
  private Integer[] roleIdArr;
  private User user;
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public String getCurPwd()
  {
    return this.curPwd;
  }
  
  public void setCurPwd(String curPwd)
  {
    this.curPwd = curPwd;
  }
  
  public Boolean getDisabled()
  {
    return this.disabled;
  }
  
  public void setDisabled(Boolean disabled)
  {
    this.disabled = disabled;
  }
  
  public UserService getUserService()
  {
    return this.userService;
  }
  
  public void setUserService(UserService userService)
  {
    this.userService = userService;
  }
  
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
  
  public String getUserDesc()
  {
    return this.userDesc;
  }
  
  public void setUserDesc(String userDesc)
  {
    this.userDesc = userDesc;
  }
  
  public String getPwd()
  {
    return this.pwd;
  }
  
  public void setPwd(String pwd)
  {
    this.pwd = pwd;
  }
  
  public Integer[] getRoleIdArr()
  {
    return this.roleIdArr;
  }
  
  public void setRoleIdArr(Integer[] roleIdArr)
  {
    this.roleIdArr = roleIdArr;
  }
  
  public void prepare()
    throws Exception
  {}
  
  public JsonResult getResult()
  {
    return this.result;
  }
  
  public void setResult(JsonResult result)
  {
    this.result = result;
  }
  
  public String findUser()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.userService.findUsers(this.userId));
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String createUser()
  {
    this.result = new JsonResult();
    try
    {
      this.userService.createUser(this.userName, this.pwd, this.userDesc, this.disabled, this.roleIdArr);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String updateUser()
  {
    this.result = new JsonResult();
    try
    {
      this.userService.updateUser(this.userId, this.userName, this.pwd, this.userDesc, this.disabled, this.roleIdArr);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String delUser()
  {
    this.result = new JsonResult();
    try
    {
      this.userService.delUser(this.userId);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String stopUser()
  {
    this.result = new JsonResult();
    try
    {
      this.userService.stopUser(this.userId);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String modifyUserInfo()
  {
    this.result = new JsonResult();
    try
    {
      int i = this.userService.modifyUserInfo(SecurityUserHolder.getCurrentUser().getUsername(), this.userName, this.userDesc);
      if (i == 1) {
        this.result.success = true;
      } else {
        this.result.success = false;
      }
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String changePassword()
  {
    this.result = new JsonResult();
    try
    {
      int i = this.userService.changePassword(SecurityUserHolder.getCurrentUser().getUsername(), this.curPwd, this.pwd);
      if (i == 1) {
        this.result.success = true;
      } else {
        this.result.success = false;
      }
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String resetPassword()
  {
    this.result = new JsonResult();
    try
    {
      int i = this.userService.resetPassword(this.userId, this.pwd);
      if (i == 1) {
        this.result.success = true;
      } else {
        this.result.success = false;
      }
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String userSet()
  {
    this.user = SecurityUserHolder.getCurrentUser();
    return "success";
  }
}
