package com.commondb.db.web;

import com.commondb.common.JsonResult;
import com.commondb.db.bo.HierarchyAttrDef;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.json.JSONObject;

public class HieraAttrAction
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
  
  public String createHieraAttr()
  {
    this.result = new JsonResult();
    try
    {
      HierarchyAttrDef hieraAttrDef = new HierarchyAttrDef();
      JSONObject js = new JSONObject(this.data);
      hieraAttrDef.setAttrName(js.getString("attrName"));
      hieraAttrDef.setMetaId(Integer.valueOf(js.getInt("metaId")));
      hieraAttrDef.setAttrId(this.metaService.createHierarchyAttrDef(hieraAttrDef));
      
      this.result.success = true;
      this.result.setData(hieraAttrDef);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String delHieraAttr()
  {
    this.result = new JsonResult();
    try
    {
      this.metaService.delHierarchyAttrDef(new Integer(this.data));
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
  
  public String updateHieraAttr()
  {
    this.result = new JsonResult();
    try
    {
      HierarchyAttrDef hieraAttrDef = new HierarchyAttrDef();
      JSONObject js = new JSONObject(this.data);
      hieraAttrDef.setAttrName(js.getString("attrName"));
      hieraAttrDef.setMetaId(Integer.valueOf(js.getInt("metaId")));
      hieraAttrDef.setAttrId(Integer.valueOf(js.getInt("attrId")));
      this.metaService.updateHierarchyAttrDef(hieraAttrDef);
      this.result.success = true;
      this.result.setData(hieraAttrDef);
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
