package com.commondb.db.bo;

import java.util.Date;

public class PicAttrDef
{
  private Integer attrId;
  private Integer metaId;
  private String attrName;
  private Integer sizeLimit;
  private Integer height;
  private Integer width;
  private Date createTime;
  private Date lastUpdateTime;
  private Integer createUser;
  private Integer updateUser;
  private Meta meta;
  
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
  
  public Integer getSizeLimit()
  {
    return this.sizeLimit;
  }
  
  public void setSizeLimit(Integer sizeLimit)
  {
    this.sizeLimit = sizeLimit;
  }
  
  public Integer getHeight()
  {
    return this.height;
  }
  
  public void setHeight(Integer height)
  {
    this.height = height;
  }
  
  public Integer getWidth()
  {
    return this.width;
  }
  
  public void setWidth(Integer width)
  {
    this.width = width;
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
