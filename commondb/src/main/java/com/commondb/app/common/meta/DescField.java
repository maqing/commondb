package com.commondb.app.common.meta;

import com.commondb.db.bo.DescAttrDef;

public class DescField
  extends Field
{
  DescAttrDef def;
  
  public DescField(DescAttrDef def)
  {
    this.fieldType = FieldType.DESC;
    this.def = def;
  }
  
  public String getFieldName()
  {
    return getColumnName();
  }
  
  public String getColumnName()
  {
    return "d_" + this.def.getAttrId();
  }
  
  public String getFieldId()
  {
    return this.def.getAttrName();
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
    return "DescDiv";
  }
  
  public String getDisplayValue()
  {
    return this.value;
  }
}
