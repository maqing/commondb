package com.commondb.app.common.meta;

import com.commondb.db.bo.DescAttrDef;

public class UrlField
  extends DescField
{
  public UrlField(DescAttrDef def)
  {
    super(def);
  }
  
  public String getInputHtml()
  {
    String valueString = getValue();
    if ((valueString == null) || (valueString.equals(""))) {
      valueString = "http://";
    }
    return 
    
      " <input id='" + getFieldId() + "' class='" + getCssClass() + "' name='" + getFieldName() + "' value=\"" + valueString + "\"/> <span id='info_" + getFieldId() + "' ></span>";
  }
}
