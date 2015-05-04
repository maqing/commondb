package com.commondb.entity.web;

import com.commondb.common.JsonResult;
import com.commondb.db.bo.User;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.commondb.security.service.SecurityUserHolder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.Map;

public class EntityAction
  extends ActionSupport
  implements Preparable
{
  private int metaId;
  private JsonResult result = new JsonResult();
  private MetaService metaService;
  private EntityService entityService;
  private User user;
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public String saveEntity()
  {
    ActionContext context = ActionContext.getContext();
    Map request = (Map)context.get("request");
    


    return "success";
  }
  
  public void prepare()
    throws Exception
  {
    this.user = SecurityUserHolder.getCurrentUser();
  }
  
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
