package com.commondb.app.web;

import com.commondb.db.bo.Role;
import com.commondb.db.bo.User;
import com.commondb.security.service.SecurityUserHolder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class SysConfigAction
  extends ActionSupport
  implements Preparable
{
  private User user;
  
  public String index()
  {
    this.user = SecurityUserHolder.getCurrentUser();
    HttpSession session = ServletActionContext.getRequest().getSession();
    for (Role role : this.user.getRoleList()) {
      session.setAttribute("rname", role.getRoleName());
    }
    return "success";
  }
  
  public void prepare()
    throws Exception
  {}
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
}
