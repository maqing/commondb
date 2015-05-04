package com.commondb.app.common.meta;

import com.commondb.db.bo.DescAttrDef;

public class DatetimeField
  extends DescField
{
  public DatetimeField(DescAttrDef def)
  {
    super(def);
  }
  
  public String getInputHtml()
  {
    return 
    


      " <input id='" + getFieldId() + "' class='" + getCssClass() + "' name='" + getFieldName() + "' value=\"" + getValue() + "\"/> " + "<script type=\"text/javascript\">  $('#" + getFieldId() + "').datepicker({});</script>" + "<span id='info_" + getFieldId() + "' ></span>";
  }
}
