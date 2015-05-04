package com.commondb.app.common.meta;

import com.commondb.db.bo.DescAttrDef;

public class NumberField
  extends DescField
{
  public NumberField(DescAttrDef def)
  {
    super(def);
  }

  public DataType getDataType()
  {
    return DataType.NUMBER;
  }

  public String getInputHtml()
  {
    return

      " <input id='" + getFieldId() + "' class='" + getCssClass() + "' name='" + getFieldName() + "' value=\"" + getValue() + "\"/>请输入数字<span id='info_" + getFieldId() + "' ></span>";
  }
}
