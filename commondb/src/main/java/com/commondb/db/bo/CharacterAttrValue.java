package com.commondb.db.bo;

import java.util.Date;

public class CharacterAttrValue
{
  private Integer valueId;
  private Integer attrId;
  private String attrValue;
  private String validated;
  private Date createTime;
  private Date lastUpdateTime;
  private Integer createUser;
  private Integer updateUser;
  
  public Integer getValueId()
  {
    return this.valueId;
  }
  
  public void setValueId(Integer valueId)
  {
    this.valueId = valueId;
  }
  
  public Integer getAttrId()
  {
    return this.attrId;
  }
  
  public void setAttrId(Integer attrId)
  {
    this.attrId = attrId;
  }
  
  public String getAttrValue()
  {
    return this.attrValue;
  }
  
  public void setAttrValue(String attrValue)
  {
    this.attrValue = attrValue;
  }
  
  public String getValidated()
  {
    return this.validated;
  }
  
  public void setValidated(String validated)
  {
    this.validated = validated;
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
