package com.commondb.db.bo;

import java.util.Date;

public class Reminder
{
  private String id;
  private String message;
  private Integer metaId;
  private String entityId;
  private Date remindTime;
  private Date createTime;
  private String createUser;
  private String toUser;
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
  }
  
  public Integer getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
  }
  
  public String getEntityId()
  {
    return this.entityId;
  }
  
  public void setEntityId(String entityId)
  {
    this.entityId = entityId;
  }
  
  public Date getRemindTime()
  {
    return this.remindTime;
  }
  
  public void setRemindTime(Date remindTime)
  {
    this.remindTime = remindTime;
  }
  
  public Date getCreateTime()
  {
    return this.createTime;
  }
  
  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }
  
  public String getCreateUser()
  {
    return this.createUser;
  }
  
  public void setCreateUser(String createUser)
  {
    this.createUser = createUser;
  }
  
  public String getToUser()
  {
    return this.toUser;
  }
  
  public void setToUser(String toUser)
  {
    this.toUser = toUser;
  }
}
