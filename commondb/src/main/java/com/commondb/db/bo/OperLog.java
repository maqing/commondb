package com.commondb.db.bo;

import java.util.Date;

public class OperLog
{
  private Integer id;
  private String objId;
  private String operType;
  private Date operTime;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getObjId()
  {
    return this.objId;
  }
  
  public void setObjId(String objId)
  {
    this.objId = objId;
  }
  
  public String getOperType()
  {
    return this.operType;
  }
  
  public void setOperType(String operType)
  {
    this.operType = operType;
  }
  
  public Date getOperTime()
  {
    return this.operTime;
  }
  
  public void setOperTime(Date operTime)
  {
    this.operTime = operTime;
  }
}
