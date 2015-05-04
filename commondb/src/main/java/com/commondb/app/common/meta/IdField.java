package com.commondb.app.common.meta;

public class IdField
  extends Field
{
  String value;
  
  public String getFieldName()
  {
    return "id";
  }
  
  public String getColumnName()
  {
    return "id";
  }
  
  public String getFieldId()
  {
    return "id";
  }
  
  public String getFieldValue()
  {
    return this.value;
  }
  
  public void setFieldValue(String value)
  {
    this.value = value;
  }
  
  public String getCssClass()
  {
    return "IdDiv";
  }
  
  public String getDisplayValue()
  {
    return this.value;
  }
}
