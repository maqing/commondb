package com.commondb.apply.web;

import com.commondb.db.bo.OperationBox;
import com.commondb.db.service.EntityService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;

public class OperationAction
  extends ActionSupport
{
  String info;
  String entityId;
  String metaId;
  String label;
  String operationRecId;
  String operationType;
  EntityService entityService;
  private Map result = new HashMap();
  
  public String getOperationRecId()
  {
    return this.operationRecId;
  }
  
  public void setOperationRecId(String operationRecId)
  {
    this.operationRecId = operationRecId;
  }
  
  public String addOperationRec()
  {
    OperationBox operationRec = new OperationBox();
    operationRec.setEntityId(this.entityId);
    operationRec.setMetaId(Integer.valueOf(this.metaId));
    operationRec.setLabel(this.label);
    operationRec.setOperationType(this.operationType);
    if (this.entityService.addOperationRec(operationRec).equals("0"))
    {
      this.result.put("flag", "ERROR");
      this.result.put("info", "已有重复记录");
    }
    else
    {
      this.result.put("flag", "SUCCESS");
      this.result.put("id", operationRec.getId());
      this.result.put("tip", operationRec.getCreateTime());
    }
    return "success";
  }
  
  public String delOperationRec()
  {
    this.entityService.delOperationRec(this.operationRecId);
    this.info = "success";
    return "success";
  }
  
  public String getLable()
  {
    return this.label;
  }
  
  public void setLable(String label)
  {
    this.label = label;
  }
  
  public String getEntityId()
  {
    return this.entityId;
  }
  
  public void setEntityId(String entityId)
  {
    this.entityId = entityId;
  }
  
  public EntityService getEntityService()
  {
    return this.entityService;
  }
  
  public void setEntityService(EntityService entityService)
  {
    this.entityService = entityService;
  }
  
  public String getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(String metaId)
  {
    this.metaId = metaId;
  }
  
  public String getInfo()
  {
    return this.info;
  }
  
  public void setInfo(String info)
  {
    this.info = info;
  }
  
  public String getLabel()
  {
    return this.label;
  }
  
  public void setLabel(String label)
  {
    this.label = label;
  }
  
  public String getOperationType()
  {
    return this.operationType;
  }
  
  public void setOperationType(String operationType)
  {
    this.operationType = operationType;
  }
  
  public Map getResult()
  {
    return this.result;
  }
  
  public void setResult(Map result)
  {
    this.result = result;
  }
}
