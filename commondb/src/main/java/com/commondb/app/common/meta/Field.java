package com.commondb.app.common.meta;

public abstract class Field
  implements IField
{
  FieldType fieldType;
  String value = "";
  String searchString = "";
  
  public String getSearchString()
  {
    return this.searchString;
  }
  
  public void setSearchString(String searchString)
  {
    this.searchString = searchString;
  }
  
  public FieldType getFieldType()
  {
    return this.fieldType;
  }
  
  public DataType getDataType()
  {
    return DataType.STRING;
  }
  
  public abstract String getFieldName();
  
  public abstract String getColumnName();
  
  public abstract String getFieldId();
  
  public abstract String getDisplayValue();
  
  public String getValue()
  {
    return this.value;
  }
  
  public void setValue(String value)
  {
    this.value = value;
  }
  
  public abstract String getCssClass();
  
  public String getHtml()
  {
    return this.value;
  }
  
  public String getInputHtml()
  {
    return 
    
      "<input id='" + getFieldId() + "' class='" + getCssClass() + "' name='" + getFieldName() + "' value=\"" + getValue() + "\"/> <span id='info_" + getFieldId() + "' ></span>";
  }
  
  public String getReadOnlyInputHtml()
  {
    return 
    
      "<input id='" + getFieldId() + "' class='" + getCssClass() + "' name='" + getFieldName() + "' value=\"" + getValue() + "\" readonly/> <span id='info_" + getFieldId() + "' ></span>";
  }
  
  public String getSearchHtml()
  {
    return 
    
      " <input style='width:100%' type='text' id='" + getFieldId() + "' class='" + getCssClass() + "' name='s_" + getFieldName() + "' value='" + getValue() + "'/>";
  }
  
  public String getSqlCondition()
  {
    if (getDataType().equals(DataType.STRING)) {
      return " " + getColumnName() + " like '%" + getValue() + "%'";
    }
    return getColumnName() + " " + getValue();
  }
}
