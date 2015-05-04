package com.commondb.db.web;

import com.commondb.common.JsonResult;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class PreCreateEntityAction
  extends ActionSupport
  implements Preparable
{
  private JsonResult result = new JsonResult();
  private MetaService metaService;
  private Integer metaId;
  
  public Integer getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
  }
  
  public String preCreatePage()
  {
    return "success";
  }
  
  public String preCreateJsPage()
  {
    return "success";
  }
  
  public void prepare()
    throws Exception
  {}
  
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
