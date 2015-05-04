package com.commondb.db.bo;

import java.util.Date;

public class CharacterAttrData
{
  private Integer dataId;
  private Integer entityId;
  private Integer attrId;
  private String content;
  private Date createTime;
  private Date lastUpdateTime;
  private Integer createUser;
  private Integer updateUser;
  
  public Integer getDataId()
  {
    return this.dataId;
  }
  
  public void setDataId(Integer dataId)
  {
    this.dataId = dataId;
  }
  
  public Integer getEntityId()
  {
    return this.entityId;
  }
  
  public void setEntityId(Integer entityId)
  {
    this.entityId = entityId;
  }
  
  public Integer getAttrId()
  {
    return this.attrId;
  }
  
  public void setAttrId(Integer attrId)
  {
    this.attrId = attrId;
  }
  
  public String getContent()
  {
    return this.content;
  }
  
  public void setContent(String content)
  {
    this.content = content;
  }
  
  public Date getCreateTime()
  {
    return this.createTime;
  }
  
  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }
  
  public Date getLastUpdateTime()
  {
    return this.lastUpdateTime;
  }
  
  public void setLastUpdateTime(Date lastUpdateTime)
  {
    this.lastUpdateTime = lastUpdateTime;
  }
  
  public Integer getCreateUser()
  {
    return this.createUser;
  }
  
  public void setCreateUser(Integer createUser)
  {
    this.createUser = createUser;
  }
  
  public Integer getUpdateUser()
  {
    return this.updateUser;
  }
  
  public void setUpdateUser(Integer updateUser)
  {
    this.updateUser = updateUser;
  }
}
