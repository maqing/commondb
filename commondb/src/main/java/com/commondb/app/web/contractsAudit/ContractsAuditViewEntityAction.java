package com.commondb.app.web.contractsAudit;

import com.commondb.app.common.meta.DescField;
import com.commondb.app.web.ViewEntityAction;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.interceptor.ServletRequestAware;

public class ContractsAuditViewEntityAction
  extends ViewEntityAction
  implements Preparable, ServletRequestAware
{
  private MetaService metaService;
  private EntityService entityService;
  private Map descFieldsMap = new HashMap();
  
  public String viewEntity()
  {
    super.viewEntity();
    for (Object field : super.getDescFields())
    {
      DescField dField = (DescField)field;
      this.descFieldsMap.put(dField.getFieldId(), dField.getInputHtml());
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
    super.setMetaService(metaService);
  }
  
  public EntityService getEntityService()
  {
    return this.entityService;
  }
  
  public void setEntityService(EntityService entityService)
  {
    this.entityService = entityService;
    super.setEntityService(entityService);
  }
  
  public Map getDescFieldsMap()
  {
    return this.descFieldsMap;
  }
  
  public void setDescFieldsMap(Map descFieldsMap)
  {
    this.descFieldsMap = descFieldsMap;
  }
}
