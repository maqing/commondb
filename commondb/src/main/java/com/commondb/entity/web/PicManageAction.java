package com.commondb.entity.web;

import com.commondb.common.JsonResult;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PicManageAction
  extends ActionSupport
  implements Preparable
{
  private int metaId;
  private JsonResult result = new JsonResult();
  private MetaService metaService;
  private EntityService entityService;
  
  public String listPics()
  {
    List images = new ArrayList();
    
    Map image = new HashMap();
    image.put("name", "kids_hug2.jpg");
    image.put("size", "2476");
    image.put("lastmod", "1249410626000");
    image.put("url", "images/thumbs/kids_hug2.jpg");
    
    images.add(image);
    
    image = new HashMap();
    image.put("name", "sara_pink.jpg");
    image.put("size", "2476");
    image.put("lastmod", "1249410626000");
    image.put("url", "images/thumbs/sara_pink.jpg");
    
    images.add(image);
    
    Map resMap = new HashMap();
    resMap.put("images", images);
    
    this.result.setData(resMap);
    
    return "success";
  }
  
  public void prepare()
    throws Exception
  {}
  
  public int getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(int metaId)
  {
    this.metaId = metaId;
  }
  
  public EntityService getEntityService()
  {
    return this.entityService;
  }
  
  public void setEntityService(EntityService entityService)
  {
    this.entityService = entityService;
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
