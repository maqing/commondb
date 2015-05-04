package com.commondb.app.common.meta;

import com.commondb.db.bo.DescAttrDef;

public class ContentField
  extends DescField
{
  public ContentField(DescAttrDef def)
  {
    super(def);
  }

  public String getInputHtml()
  {
    return



      " <textarea id='" + getFieldId() + "' rows='3' cols='80' style='width:500px;height:60px;' " + " class='" + getCssClass() + "' name='" + getFieldName() + "' value=\"" + toHtml(getValue()) + "\">" + toHtml(getValue()) + "</textarea> <span id='info_" + getFieldId() + "' ></span>";
  }

  public static String toHtml(String str)
  {
    String html = str;

    html = html.replace("& ", "&amp; ");
    html = html.replace(" < ", "&lt; ");
    html = html.replace("> ", "&gt; ");
    html = html.replace("\r\n ", "\n ");
    html = html.replace("\n ", " <br> \n ");
    html = html.replace("\t ", "         ");
    html = html.replace("     ", "   &nbsp; ");

    return html;
  }
}
