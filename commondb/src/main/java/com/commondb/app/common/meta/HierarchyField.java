package com.commondb.app.common.meta;

import com.commondb.db.bo.HierarchyAttrDef;

public class HierarchyField
  extends Field
{
  HierarchyAttrDef def;
  String displayVaule = "";
  
  public String getDisplayVaule()
  {
    return this.displayVaule;
  }
  
  public void setDisplayVaule(String displayVaule)
  {
    this.displayVaule = displayVaule;
  }
  
  public HierarchyField(HierarchyAttrDef def)
  {
    this.fieldType = FieldType.HIERARCHY;
    this.def = def;
  }
  
  public String getFieldName()
  {
    return this.def.getAttrName();
  }
  
  public String getColumnName()
  {
    return "h_" + this.def.getAttrId();
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
    return "HierDiv";
  }
  
  public String getSearchString()
  {
    return 
    

      "<td class=\"first\" width=\"177\" ><strong>" + getFieldName() + "</strong></td>" + "<td class=\"last\" style=\"text-align:left\"><div id=\"" + getFieldName() + "_display\" >" + this.displayVaule + "</div><input type='hidden' id=\"s_h_" + getFieldId() + "\" " + "name=\"s_" + getColumnName() + "\" value=\"" + getValue() + "\" /><img width=\"16\" height=\"16\" onclick='selectHier(" + this.def.getAttrId() + ",\"" + getFieldName() + "_display\", \"s_h_" + getFieldId() + "\")' alt=\"find\" src=\"/app/img/edit-icon.gif\"></td>";
  }
  
  public String getInputHtml()
  {
    return 
    
      "<div id=\"" + getFieldName() + "_display\" >" + this.displayVaule + "</div><input type='hidden' id=\"" + getColumnName() + "\" " + "name=\"" + getColumnName() + "\" value=\"" + getValue() + "\" /><img width=\"16\" height=\"16\" onclick='selectHier(" + this.def.getAttrId() + ",\"" + getFieldName() + "_display\", \"" + getColumnName() + "\")' alt=\"find\" src=\"/app/img/edit-icon.gif\">";
  }
  
  public String getSqlCondition()
  {
    String[] ids = getValue().split(",");
    String idString = "";
    for (int i = 0; i < ids.length; i++)
    {
      idString = idString + "'" + ids[i] + "'";
      if (i != ids.length - 1) {
        idString = idString + ",";
      }
    }
    return "(h.attr_id=" + this.def.getAttrId() + " and h.value_id in (" + idString + "))";
  }
  
  public String getDisplayValue()
  {
    return this.displayVaule;
  }
}
