package com.commondb.app.common.meta;

import com.commondb.db.bo.DescAttrDef;

public class BooleanField
  extends DescField
{
  public BooleanField(DescAttrDef def)
  {
    super(def);
  }
  
  public String getInputHtml()
  {
    String yesSelect = "";
    String noSelect = "";
    if (getValue().equals("是")) {
      yesSelect = "selected";
    } else {
      noSelect = "selected";
    }
    return 
    



      " <select id='" + getFieldId() + "' name='" + getFieldName() + "' value=\"" + getValue() + "\"> " + "<option value='是' " + yesSelect + " >是</option>" + "<option value='否' " + noSelect + " >否</option>" + "</select>" + "<span id='info_" + getFieldId() + "' ></span>";
  }
}
