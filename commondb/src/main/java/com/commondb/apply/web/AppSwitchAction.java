package com.commondb.apply.web;

import com.commondb.apply.bo.ApplyMenu;
import com.commondb.apply.bo.ApplyUser;
import com.commondb.apply.service.ApplyService;
import com.commondb.db.bo.User;
import com.commondb.security.service.SecurityUserHolder;
import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppSwitchAction
{
  private ApplyService applyService;
  User user;
  Integer applyId;
  String defaultMenu;
  
  public ApplyService getApplyService()
  {
    return this.applyService;
  }
  
  public void setApplyService(ApplyService applyService)
  {
    this.applyService = applyService;
  }
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public Integer getApplyId()
  {
    return this.applyId;
  }
  
  public void setApplyId(Integer applyId)
  {
    this.applyId = applyId;
  }
  
  public String getDefaultMenu()
  {
    return this.defaultMenu;
  }
  
  public void setDefaultMenu(String defaultMenu)
  {
    this.defaultMenu = defaultMenu;
  }
  
  public String switchApp()
  {
    this.user = SecurityUserHolder.getCurrentUser();
    List<ApplyUser> menuUserList = this.applyService.getApplyUserByUserIdAndApplyId(this.user.getUserId(), this.applyId);
    Map session = ActionContext.getContext().getSession();
    List currentMenuList = new ArrayList();
    String goToUrl = "";
    for (ApplyUser au : menuUserList)
    {
      ApplyMenu am = this.applyService.getApplyMenuById(au.getApplyMenuId());
      currentMenuList.add(am);
      if (am.getMenuName().trim().equals(this.defaultMenu)) {
        goToUrl = am.getMenuUrl();
      }
    }
    session.put("currentMenuList", currentMenuList);
    session.put("GOING_TO", goToUrl);
    
    return "success";
  }
}
