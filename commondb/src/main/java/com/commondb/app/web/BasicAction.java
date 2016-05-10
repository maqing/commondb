package com.commondb.app.web;

import com.commondb.db.bo.User;
import com.commondb.db.service.EntityService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

public class BasicAction
  extends ActionSupport
  implements Preparable, ServletRequestAware
{
  List remindList;
  List operationRecList;
  EntityService entityService;
  String masterRMetaId;
  String masterRMetaName;
  String masterREntityId;
  String masterRLabel;
  String toRMetaId;
  String toRMetaName;
  String toREntityId;
  String toRLabel;
  
  public void prepare()
    throws Exception
  {}
  
  public void setServletRequest(HttpServletRequest arg0) {}
  
  public List getRemindList()
  {
    User user = (User)SecurityContextHolder.getContext()
      .getAuthentication().getPrincipal();
    this.remindList = this.getEntityService().getUserRemind(user.getUserId());
    
    return this.remindList;
  }
  
  public void setRemindList(List remindList)
  {
    this.remindList = remindList;
  }
  
  public List getOperationRecList()
  {
    User user = (User)SecurityContextHolder.getContext()
      .getAuthentication().getPrincipal();
    this.operationRecList = this.getEntityService().getUserOperationRec(user.getUserId());
    return this.operationRecList;
  }
  
  public void setOperationRecList(List operationRecList)
  {
    this.operationRecList = operationRecList;
  }
  
  public EntityService getEntityService()
  {
    return this.entityService;
  }
  
  public void setEntityService(EntityService entityService)
  {
    this.entityService = entityService;
  }
  
  public String getMasterRMetaId()
  {
    return this.masterRMetaId;
  }
  
  public void setMasterRMetaId(String masterRMetaId)
  {
    this.masterRMetaId = masterRMetaId;
  }
  
  public String getMasterRMetaName()
  {
    return this.masterRMetaName;
  }
  
  public void setMasterRMetaName(String masterRMetaName)
  {
    this.masterRMetaName = masterRMetaName;
  }
  
  public String getMasterREntityId()
  {
    return this.masterREntityId;
  }
  
  public void setMasterREntityId(String masterREntityId)
  {
    this.masterREntityId = masterREntityId;
  }
  
  public String getMasterRLabel()
  {
    return this.masterRLabel;
  }
  
  public void setMasterRLabel(String masterRLabel)
  {
    this.masterRLabel = masterRLabel;
  }
  
  public String getToRMetaId()
  {
    return this.toRMetaId;
  }
  
  public void setToRMetaId(String toRMetaId)
  {
    this.toRMetaId = toRMetaId;
  }
  
  public String getToRMetaName()
  {
    return this.toRMetaName;
  }
  
  public void setToRMetaName(String toRMetaName)
  {
    this.toRMetaName = toRMetaName;
  }
  
  public String getToREntityId()
  {
    return this.toREntityId;
  }
  
  public void setToREntityId(String toREntityId)
  {
    this.toREntityId = toREntityId;
  }
  
  public String getToRLabel()
  {
    return this.toRLabel;
  }
  
  public void setToRLabel(String toRLabel)
  {
    this.toRLabel = toRLabel;
  }
}
