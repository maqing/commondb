package com.commondb.db.bo;

import java.util.Date;

public class Pic
{
  private Integer picId;
  private String picName;
  private Integer picSize;
  private Date createTime;
  private String picUrl;
  private String previewUrl;
  private Integer refCount;
  
  public Integer getPicId()
  {
    return this.picId;
  }
  
  public void setPicId(Integer picId)
  {
    this.picId = picId;
  }
  
  public String getPicName()
  {
    return this.picName;
  }
  
  public void setPicName(String picName)
  {
    this.picName = picName;
  }
  
  public Integer getPicSize()
  {
    return this.picSize;
  }
  
  public void setPicSize(Integer picSize)
  {
    this.picSize = picSize;
  }
  
  public Date getCreateTime()
  {
    return this.createTime;
  }
  
  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }
  
  public String getPicUrl()
  {
    return this.picUrl;
  }
  
  public void setPicUrl(String picUrl)
  {
    this.picUrl = picUrl;
  }
  
  public String getPreviewUrl()
  {
    return this.previewUrl;
  }
  
  public void setPreviewUrl(String previewUrl)
  {
    this.previewUrl = previewUrl;
  }
  
  public Integer getRefCount()
  {
    return this.refCount;
  }
  
  public void setRefCount(Integer refCount)
  {
    this.refCount = refCount;
  }
}
