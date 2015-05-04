package com.commondb.app.common.meta;

import com.commondb.db.bo.CharacterData;
import com.commondb.db.bo.CharacterDef;
import java.util.List;

public class CharacterField
  extends Field
{
  CharacterDef def;
  String displayValue;
  String inputHtml;
  
  public void initInputHtml(List<CharacterData> dataList)
  {
    this.inputHtml = "";
    int i = 0;
    this.displayValue = "";
    for (CharacterData data : dataList)
    {
      String checked = "";
      if ((this.value != null) && ((this.value.equals(data.getDataId().toString())) || (this.value.startsWith(data.getDataId() + ",")) || (this.value.endsWith("," + data.getDataId())) || (this.value.indexOf("," + data.getDataId() + ",") > 0)))
      {
        checked = checked + "checked";
        this.displayValue = (this.displayValue + " " + data.getDataName());
      }
      this.inputHtml = (this.inputHtml + "<input onclick=\"refreshCField('" + getColumnName() + "')\" type=checkbox " + checked + " name='checkbox_" + getColumnName() + "' " + " id=checkbox_" + getColumnName() + "_" + data.getDataId() + " value=" + data.getDataId() + " >" + data.getDataName() + "</input>");
      i++;
      if (i % 5 == 0) {
        this.inputHtml += "<br/>";
      }
    }
  }
  
  public String getSearchString()
  {
    return 
    
      "<td class=\"first\" width=\"177\" ><strong>" + getFieldName() + "</strong></td>" + "<td class=\"last\" style=\"text-align:left\">" + getSearchInputHtml() + "</td>";
  }
  
  public String getSearchInputHtml()
  {
    return 
      "<input type='hidden' name=s_" + getColumnName() + " id=" + getColumnName() + " value=\"" + getValue() + "\" />" + this.inputHtml;
  }
  
  public String getInputHtml()
  {
    return 
      "<input type='hidden' name=" + getColumnName() + " id=" + getColumnName() + " value=\"" + getValue() + "\" />" + this.inputHtml;
  }
  
  public String getDisplayValue()
  {
    return this.displayValue;
  }
  
  public void setDisplayValue(String displayValue)
  {
    this.displayValue = displayValue;
  }
  
  public CharacterField(CharacterDef def)
  {
    this.fieldType = FieldType.PIC;
    this.def = def;
  }
  
  public String getFieldName()
  {
    return this.def.getCharaName();
  }
  
  public String getColumnName()
  {
    return "c_" + this.def.getCharaId();
  }
  
  public String getFieldId()
  {
    return this.def.getCharaName();
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
    return "CharacterDiv";
  }
  
  public String getSqlCondition()
  {
    return "(c.chara_id=" + this.def.getCharaId() + " and c.data_id in (" + getValue() + "))";
  }
}
