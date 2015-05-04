package com.commondb.app.common.meta;

public abstract interface IField
{
  public abstract FieldType getFieldType();
  
  public abstract DataType getDataType();
  
  public abstract String getFieldName();
  
  public abstract String getColumnName();
  
  public abstract String getFieldId();
  
  public abstract String getValue();
  
  public abstract void setValue(String paramString);
  
  public abstract String getHtml();
}
