package com.commondb.apply.web;

import com.commondb.db.bo.User;
import com.commondb.db.service.SyncService;

public class SyncAction
{
  private SyncService syncService;
  User user;
  Integer applyId;
  String info = "";
  String defaultMenu;
  
  public String getInfo()
  {
    return this.info;
  }
  
  public void setInfo(String info)
  {
    this.info = info;
  }
  
  public String index()
  {
    return "success";
  }
  
  public String checkin()
  {
    this.info = this.syncService.syncData();
    return "success";
  }
  
  public String checkout()
  {
    this.syncService.pullData();
    this.info = "数据更新完毕！";
    return "success";
  }
  
  public SyncService getSyncService()
  {
    return this.syncService;
  }
  
  public void setSyncService(SyncService syncService)
  {
    this.syncService = syncService;
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
}
