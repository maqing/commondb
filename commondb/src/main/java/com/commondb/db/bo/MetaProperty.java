package com.commondb.db.bo;

public class MetaProperty
{
  private Integer propertyId;
  private String propertyName;
  private String propertyDesc;
  private Integer propertyLimit;
  private String propertyCode;
  private Integer isenabled;
  
  public Integer getPropertyId()
  {
    return this.propertyId;
  }
  
  public void setPropertyId(Integer propertyId)
  {
    this.propertyId = propertyId;
  }
  
  public String getPropertyName()
  {
    return this.propertyName;
  }
  
  public void setPropertyName(String propertyName)
  {
    this.propertyName = propertyName;
  }
  
  public String getPropertyDesc()
  {
    return this.propertyDesc;
  }
  
  public void setPropertyDesc(String propertyDesc)
  {
    this.propertyDesc = propertyDesc;
  }
  
  public Integer getPropertyLimit()
  {
    return this.propertyLimit;
  }
  
  public void setPropertyLimit(Integer propertyLimit)
  {
    this.propertyLimit = propertyLimit;
  }
  
  public String getPropertyCode()
  {
    return this.propertyCode;
  }
  
  public void setPropertyCode(String propertyCode)
  {
    this.propertyCode = propertyCode;
  }
  
  public Integer getIsenabled()
  {
    return this.isenabled;
  }
  
  public void setIsenabled(Integer isenabled)
  {
    this.isenabled = isenabled;
  }
}
