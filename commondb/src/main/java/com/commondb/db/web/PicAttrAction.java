package com.commondb.db.web;

import com.commondb.common.JsonResult;
import com.commondb.db.bo.PicAttrDef;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.json.JSONObject;

public class PicAttrAction
  extends ActionSupport
  implements Preparable
{
  private JsonResult result = new JsonResult();
  private MetaService metaService;
  private String data;
  private Integer[] attrId;
  
  public String getData()
  {
    return this.data;
  }
  
  public void setData(String data)
  {
    this.data = data;
  }
  
  public Integer[] getAttrId()
  {
    return this.attrId;
  }
  
  public void setAttrId(Integer[] attrId)
  {
    this.attrId = attrId;
  }
  
  public void prepare()
    throws Exception
  {}
  
  public String createPicAttr()
  {
    this.result = new JsonResult();
    try
    {
      PicAttrDef picAttr = new PicAttrDef();
      JSONObject js = new JSONObject(this.data);
      picAttr.setAttrName(js.getString("attrName"));
      picAttr.setMetaId(Integer.valueOf(js.getInt("metaId")));
      picAttr.setAttrId(this.metaService.createPicAttrDef(picAttr));
      
      this.result.success = true;
      this.result.setData(picAttr);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String delPicAttr()
  {
    this.result = new JsonResult();
    try
    {
      this.metaService.delPicAttrDef(new Integer(this.data));
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String updatePicAttr()
  {
    this.result = new JsonResult();
    try
    {
      PicAttrDef picAttr = new PicAttrDef();
      JSONObject js = new JSONObject(this.data);
      picAttr.setAttrName(js.getString("attrName"));
      picAttr.setMetaId(Integer.valueOf(js.getInt("metaId")));
      picAttr.setAttrId(Integer.valueOf(js.getInt("attrId")));
      this.metaService.updatePicAttrDef(picAttr);
      this.result.success = true;
      this.result.setData(picAttr);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public MetaService getMetaService()
  {
    return this.metaService;
  }
  
  public void setMetaService(MetaService metaService)
  {
    this.metaService = metaService;
  }
  
  public JsonResult getResult()
  {
    return this.result;
  }
  
  public void setResult(JsonResult result)
  {
    this.result = result;
  }
}
