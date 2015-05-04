package com.commondb.apply.bo;

import java.util.List;

public class ApplyUser
{
  private Integer id;
  private Integer userId;
  private Integer applyId;
  private Integer applyMenuId;
  private Integer type;
  public List applyList;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Integer getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
  
  public Integer getApplyId()
  {
    return this.applyId;
  }
  
  public void setApplyId(Integer applyId)
  {
    this.applyId = applyId;
  }
  
  public Integer getApplyMenuId()
  {
    return this.applyMenuId;
  }
  
  public void setApplyMenuId(Integer applyMenuId)
  {
    this.applyMenuId = applyMenuId;
  }
  
  public Integer getType()
  {
    return this.type;
  }
  
  public void setType(Integer type)
  {
    this.type = type;
  }
  
  public List getApplyList()
  {
    return this.applyList;
  }
  
  public void setApplyList(List applyList)
  {
    this.applyList = applyList;
  }
}
