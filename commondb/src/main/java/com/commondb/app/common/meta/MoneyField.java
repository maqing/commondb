package com.commondb.app.common.meta;

import com.commondb.db.bo.DescAttrDef;

public class MoneyField
  extends DescField
{
  public MoneyField(DescAttrDef def)
  {
    super(def);
  }
  
  public String getInputHtml()
  {
    String rmbSelect = "";
    String jpSelect = "";
    String drSelect = "";
    String ouyuanSelect = "";
    String valueString = getValue();
    if (getValue() != null)
    {
      if (valueString.endsWith("人民币"))
      {
        rmbSelect = "selected";
        valueString = valueString.replaceAll("人民币", "");
      }
      if (valueString.endsWith("美元"))
      {
        drSelect = "selected";
        valueString = valueString.replaceAll("美元", "");
      }
      if (valueString.endsWith("日元"))
      {
        jpSelect = "selected";
        valueString = valueString.replaceAll("日元", "");
      }
      if (valueString.endsWith("欧元"))
      {
        ouyuanSelect = "selected";
        valueString = valueString.replaceAll("欧元", "");
      }
    }
    return 
    








      " <input type=hidden name='" + getFieldName() + "' id='" + getFieldId() + "' value='" + getValue() + "'/> " + "<input onchange=\"refreshMoney('" + getFieldId() + "');\" id='display_" + getFieldId() + "' class='" + getCssClass() + "' value=\"" + valueString + "\"/> " + "<select onchange=\"refreshMoney('" + getFieldId() + "');\" id='unit_" + getFieldId() + "'>" + "<option value='人民币' " + rmbSelect + " >人民币</option>" + "<option value='美元' " + drSelect + " >美元</option>" + "<option value='日元' " + jpSelect + " >日元</option>" + "<option value='欧元' " + ouyuanSelect + " >欧元</option>" + "</select>" + "<span id='info_" + getFieldId() + "' ></span>";
  }
}
