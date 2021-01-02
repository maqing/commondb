package com.commondb.app.web;

import com.commondb.app.common.SysConfig;
import com.commondb.app.common.meta.DescField;
import com.commondb.app.common.meta.FieldFactory;
import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.User;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.rits.cloning.Cloner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

public class AutoTipAction
  extends ActionSupport
  implements Preparable, ServletRequestAware
{
  private String entityName;
  private String q;
  private String descFieldName;
  private String newEntityUrl;
  private List tipList;
  private MetaService metaService;
  private EntityService entityService;
  private User user;
  HttpServletRequest request;
  
  public String autoTip()
  {
    ActionContext context = ActionContext.getContext();
    Map request = (Map)context.get("request");
    this.entityName = SysConfig.DefaultEntityName;
    
    Meta meta = this.metaService.findMetaByName(this.entityName);
    Integer metaId = meta.getId();
    List descAttrList = this.metaService.findDescAttrDef(metaId);
    DescField descField = null;
    for (int j = 0; j < descAttrList.size(); j++)
    {
      DescAttrDef descDef = (DescAttrDef)descAttrList.get(j);
      if (this.descFieldName.equals(descDef.getAttrName())) {
        descField = (DescField)FieldFactory.getInstance().createField(
          descDef);
      }
    }
    if (this.q == null) {
      this.q = "";
    }
    String columnsString = "distinct(t_entity_" + metaId + "." + descField.getColumnName() + ") " + descField.getColumnName();
    String fromString = "t_entity_" + metaId;
    String whereString = "t_entity_" + metaId + "." + descField.getColumnName() + " like '" + this.q + "%'";
    
    List result = this.entityService.dynSelect(columnsString, fromString, whereString);
    Cloner cloner = new Cloner();
    this.tipList = new ArrayList();
    for (int i = 0; i < result.size(); i++)
    {
      Map map = (Map)result.get(i);
      



      this.tipList.add(map.get(descField.getColumnName()).toString());
    }
    return "success";
  }
  
  public void prepare()
    throws Exception
  {}
  
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
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public void setServletRequest(HttpServletRequest request)
  {
    this.request = request;
  }
  
  public String getEntityName()
  {
    return this.entityName;
  }
  
  public void setEntityName(String entityName)
  {
    this.entityName = entityName;
  }
  
  public String getDescFieldName()
  {
    return this.descFieldName;
  }
  
  public void setDescFieldName(String descFieldName)
  {
    this.descFieldName = descFieldName;
  }
  
  public String getNewEntityUrl()
  {
    return this.newEntityUrl;
  }
  
  public void setNewEntityUrl(String newEntityUrl)
  {
    this.newEntityUrl = newEntityUrl;
  }
  
  public List getTipList()
  {
    return this.tipList;
  }
  
  public void setTipList(List tipList)
  {
    this.tipList = tipList;
  }
  
  public String getQ()
  {
    return this.q;
  }
  
  public void setQ(String q)
  {
    this.q = q;
  }
}
