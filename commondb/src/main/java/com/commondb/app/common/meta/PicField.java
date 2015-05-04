package com.commondb.app.common.meta;

import com.commondb.db.bo.PicAttrDef;

public class PicField
  extends Field
{
  PicAttrDef def;
  
  public PicField(PicAttrDef def)
  {
    this.fieldType = FieldType.PIC;
    this.def = def;
  }
  
  public String getFieldName()
  {
    return this.def.getAttrName();
  }
  
  public String getColumnName()
  {
    return "p_" + this.def.getAttrId();
  }
  
  public String getFieldId()
  {
    return this.def.getAttrName();
  }
  
  public String getCssClass()
  {
    return "PicDiv";
  }
  
  public String getInputHtml()
  {
    return 
    



      "<input id='" + getFieldId() + "' name='" + getColumnName() + "' class='" + getCssClass() + "' style='height: 100%;' type=hidden />" + "<input id='myfile_" + getFieldId() + "' name='" + "myFile" + "' class='" + getCssClass() + "' style='height: 100%;' type=file onchange=\"$('#" + getFieldId() + "').val(this.value);\"/>";
  }
  
  public String getDisplayValue()
  {
    if ((this.value == null) || (this.value.equals(""))) {
      return "";
    }
    if ((this.value.endsWith(".doc")) || (this.value.endsWith(".pdf")) || (this.value.endsWith(".txt"))) {
      return "<a href='/" + this.value + "'>下载 </a>";
    }
    return "<a href='/" + this.value + "'>" + "<img alt='下载' src='/" + this.value + "_p.jpg'/>" + " </a>";
  }
}
