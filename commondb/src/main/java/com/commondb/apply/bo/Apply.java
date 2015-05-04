package com.commondb.apply.bo;

import java.util.List;

public class Apply
{
  private Integer applyId;
  private String applyName;
  private String applyUrl;
  private Integer num;
  public List amenuList;
  
  public Integer getApplyId()
  {
    return this.applyId;
  }
  
  public void setApplyId(Integer applyId)
  {
    this.applyId = applyId;
  }
  
  public String getApplyName()
  {
    return this.applyName;
  }
  
  public void setApplyName(String applyName)
  {
    this.applyName = applyName;
  }
  
  public String getApplyUrl()
  {
    return this.applyUrl;
  }
  
  public void setApplyUrl(String applyUrl)
  {
    this.applyUrl = applyUrl;
  }
  
  public Integer getNum()
  {
    return this.num;
  }
  
  public void setNum(Integer num)
  {
    this.num = num;
  }
  
  public List getAmenuList()
  {
    return this.amenuList;
  }
  
  public void setAmenuList(List amenuList)
  {
    this.amenuList = amenuList;
  }
}
