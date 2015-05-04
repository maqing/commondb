package com.commondb.app.common.meta;

public abstract interface IData
{
  public abstract DataType getDataType();
  
  public abstract Object getValue();
  
  public abstract String getStringValue();
}
