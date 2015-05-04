package com.commondb.db.bo;

import java.util.Date;

public class DescAttrDef
{
  private Integer attrId;
  private Integer metaId;
  private String attrName;
  private Integer lengthLimit;
  private String content;
  private Date createTime;
  private Date lastUpdateTime;
  private Integer createUser;
  private Integer updateUser;
  private String largeContent;
  private Meta meta;
  private Integer propertyId;
  
  public Integer getPropertyId()
  {
    return this.propertyId;
  }
  
  public void setPropertyId(Integer propertyId)
  {
    this.propertyId = propertyId;
  }
  
  public Meta getMeta()
  {
    return this.meta;
  }
  
  public void setMeta(Meta meta)
  {
    this.meta = meta;
  }
  
  public Integer getAttrId()
  {
    return this.attrId;
  }
  
  public void setAttrId(Integer attrId)
  {
    this.attrId = attrId;
  }
  
  public Integer getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
  }
  
  public String getAttrName()
  {
    return this.attrName;
  }
  
  public void setAttrName(String attrName)
  {
    this.attrName = attrName;
  }
  
  public Integer getLengthLimit()
  {
    return this.lengthLimit;
  }
  
  public void setLengthLimit(Integer lengthLimit)
  {
    this.lengthLimit = lengthLimit;
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
  
  public String getLargeContent()
  {
    return this.largeContent;
  }
  
  public void setLargeContent(String largeContent)
  {
    this.largeContent = largeContent;
  }
}
