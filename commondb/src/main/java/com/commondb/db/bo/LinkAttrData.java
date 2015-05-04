package com.commondb.db.bo;

import java.util.Date;

public class LinkAttrData
{
  private Integer dataId;
  private Integer fromEntityId;
  private Integer attrId;
  private String label;
  private String url;
  private Integer toEntityId;
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
  
  public Integer getFromEntityId()
  {
    return this.fromEntityId;
  }
  
  public void setFromEntityId(Integer fromEntityId)
  {
    this.fromEntityId = fromEntityId;
  }
  
  public Integer getAttrId()
  {
    return this.attrId;
  }
  
  public void setAttrId(Integer attrId)
  {
    this.attrId = attrId;
  }
  
  public String getLabel()
  {
    return this.label;
  }
  
  public void setLabel(String label)
  {
    this.label = label;
  }
  
  public String getUrl()
  {
    return this.url;
  }
  
  public void setUrl(String url)
  {
    this.url = url;
  }
  
  public Integer getToEntityId()
  {
    return this.toEntityId;
  }
  
  public void setToEntityId(Integer toEntityId)
  {
    this.toEntityId = toEntityId;
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
