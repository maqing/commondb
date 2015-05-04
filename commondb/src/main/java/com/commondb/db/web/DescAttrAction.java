package com.commondb.db.web;

import com.commondb.common.JsonResult;
import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.json.JSONObject;

public class DescAttrAction
  extends ActionSupport
  implements Preparable
{
  private JsonResult result = new JsonResult();
  private MetaService metaService;
  private String data;
  private Integer attrId;
  
  public String getData()
  {
    return this.data;
  }
  
  public void setData(String data)
  {
    this.data = data;
  }
  
  public Integer getAttrId()
  {
    return this.attrId;
  }
  
  public void setAttrId(Integer attrId)
  {
    this.attrId = attrId;
  }
  
  public void prepare()
    throws Exception
  {}
  
  public String createDescAttr()
  {
    this.result = new JsonResult();
    try
    {
      DescAttrDef descAttr = new DescAttrDef();
      JSONObject js = new JSONObject(this.data);
      descAttr.setAttrName(js.getString("attrName"));
      descAttr.setMetaId(Integer.valueOf(js.getInt("metaId")));
      descAttr.setPropertyId(Integer.valueOf(js.getInt("propertyId")));
      descAttr.setAttrId(this.metaService.createDescAttrDef(descAttr));
      
      this.result.success = true;
      this.result.setData(descAttr);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String delDescAttr()
  {
    this.result = new JsonResult();
    try
    {
      this.metaService.delDescAttrDef(new Integer(this.data));
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
  
  public String updateDescAttr()
  {
    this.result = new JsonResult();
    try
    {
      DescAttrDef descAttr = new DescAttrDef();
      JSONObject js = new JSONObject(this.data);
      descAttr.setAttrName(js.getString("attrName"));
      descAttr.setMetaId(Integer.valueOf(js.getInt("metaId")));
      descAttr.setAttrId(Integer.valueOf(js.getInt("attrId")));
      descAttr.setPropertyId(Integer.valueOf(js.getInt("propertyId")));
      this.metaService.updateDescAttrDef(descAttr);
      this.result.success = true;
      this.result.setData(descAttr);
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
