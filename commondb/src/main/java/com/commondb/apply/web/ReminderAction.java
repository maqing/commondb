package com.commondb.apply.web;

import com.commondb.db.bo.User;
import com.commondb.db.service.EntityService;
import com.commondb.security.service.UserService;
import java.util.List;

public class ReminderAction
{
  String info;
  String entityId;
  String[] userId;
  String date;
  String metaId;
  String message;
  String remindId;
  String userName;
  List userList;
  EntityService entityService;
  UserService userService;
  Integer applyId;
  
  public String getInfo()
  {
    return this.info;
  }
  
  public void setInfo(String info)
  {
    this.info = info;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public String getRemindId()
  {
    return this.remindId;
  }
  
  public void setRemindId(String remindId)
  {
    this.remindId = remindId;
  }
  
  public String preRemind()
  {
    User user = new User();
    user.setUserName(this.userName);
    this.userList = this.userService.getAllUser(user);
    
    return "success";
  }
  
  public String remind()
  {
    if ((this.userId != null) && (this.userId.length > 0))
    {
      for (int i = 0; i < this.userId.length; i++) {
        this.entityService.remind(new Integer(this.userId[i]), new Integer(this.metaId), this.entityId, this.message, this.date);
      }
      this.info = "添加提醒成功！";
    }
    else
    {
      this.info = "未选择用户！";
    }
    return "success";
  }
  
  public String delRemind()
  {
    this.entityService.delRemind(this.remindId);
    this.info = "success";
    return "success";
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
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
  
  public String getEntityId()
  {
    return this.entityId;
  }
  
  public void setEntityId(String entityId)
  {
    this.entityId = entityId;
  }
  
  public String[] getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(String[] userId)
  {
    this.userId = userId;
  }
  
  public String getDate()
  {
    return this.date;
  }
  
  public void setDate(String date)
  {
    this.date = date;
  }
  
  public EntityService getEntityService()
  {
    return this.entityService;
  }
  
  public void setEntityService(EntityService entityService)
  {
    this.entityService = entityService;
  }
  
  public Integer getApplyId()
  {
    return this.applyId;
  }
  
  public void setApplyId(Integer applyId)
  {
    this.applyId = applyId;
  }
  
  public String getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(String metaId)
  {
    this.metaId = metaId;
  }
}
