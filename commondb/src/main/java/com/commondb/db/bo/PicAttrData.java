package com.commondb.db.bo;

import java.util.Date;

public class PicAttrData
{
  private Integer dataId;
  private Integer entityId;
  private Integer attrId;
  private String filePath;
  private String previewFilePath;
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
  
  public String getFilePath()
  {
    return this.filePath;
  }
  
  public void setFilePath(String filePath)
  {
    this.filePath = filePath;
  }
  
  public String getPreviewFilePath()
  {
    return this.previewFilePath;
  }
  
  public void setPreviewFilePath(String previewFilePath)
  {
    this.previewFilePath = previewFilePath;
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
