package com.commondb.app.common.meta;

import com.commondb.db.bo.CharacterData;
import com.commondb.db.bo.CharacterDef;
import java.util.List;

public class SingleCharacterField
  extends CharacterField
{
  public SingleCharacterField(CharacterDef def)
  {
    super(def);
  }
  
  public void initInputHtml(List<CharacterData> dataList)
  {
    this.inputHtml = "";
    int i = 0;
    this.displayValue = "";
    for (CharacterData data : dataList)
    {
      String selected = "";
      if ((this.value != null) && ((this.value.equals(data.getDataId().toString())) || (this.value.startsWith(data.getDataId() + ",")) || (this.value.endsWith("," + data.getDataId())) || (this.value.indexOf("," + data.getDataId() + ",") > 0)))
      {
        selected = selected + "checked ";
        this.displayValue = (this.displayValue + " " + data.getDataName());
      }
      this.inputHtml = (this.inputHtml + "<input onclick=\"refreshCField('" + getColumnName() + "')\" type=radio " + selected + " name='checkbox_" + getColumnName() + "' " + " id=checkbox_" + getColumnName() + "_" + data.getDataId() + " value=" + data.getDataId() + " >" + data.getDataName() + "</input>");
      i++;
      if (i % 5 == 0) {
        this.inputHtml += "<br/>";
      }
    }
  }
}
