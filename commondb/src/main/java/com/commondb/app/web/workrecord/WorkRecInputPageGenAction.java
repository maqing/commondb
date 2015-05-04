package com.commondb.app.web.workrecord;

import com.commondb.app.web.InputPageGenAction;
import com.commondb.db.bo.User;
import com.commondb.security.service.UserService;
import com.opensymphony.xwork2.Preparable;
import java.util.List;
import org.apache.struts2.interceptor.ServletRequestAware;

public class WorkRecInputPageGenAction
  extends InputPageGenAction
  implements Preparable, ServletRequestAware
{
  String userName;
  List userList;
  UserService userService;
  
  public String preRemind()
  {
    return "success";
  }
  
  public String genForm()
  {
    User user = new User();
    user.setUserName(this.userName);
    this.userList = this.userService.getAllUser(user);
    
    return super.genForm();
  }
  
  public List getUserList()
  {
    return this.userList;
  }
  
  public void setUserList(List userList)
  {
    this.userList = userList;
  }
  
  public UserService getUserService()
  {
    return this.userService;
  }
  
  public void setUserService(UserService userService)
  {
    this.userService = userService;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
}
