package com.commondb.db.web;

import com.commondb.common.JsonResult;
import com.commondb.db.bo.MetaProperty;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class MetaPropertyAction
  extends ActionSupport
  implements Preparable
{
  private JsonResult result = new JsonResult();
  private MetaService metaService;
  private Integer propertyId;
  private Integer propertyLimit;
  private Integer isEnabled;
  private String propertyName;
  private String propertyDesc;
  private String propertyCode;
  
  public Integer getPropertyId()
  {
    return this.propertyId;
  }
  
  public void setPropertyId(Integer propertyId)
  {
    this.propertyId = propertyId;
  }
  
  public Integer getPropertyLimit()
  {
    return this.propertyLimit;
  }
  
  public void setPropertyLimit(Integer propertyLimit)
  {
    this.propertyLimit = propertyLimit;
  }
  
  public Integer getIsEnabled()
  {
    return this.isEnabled;
  }
  
  public void setIsEnabled(Integer isEnabled)
  {
    this.isEnabled = isEnabled;
  }
  
  public String getPropertyName()
  {
    return this.propertyName;
  }
  
  public void setPropertyName(String propertyName)
  {
    this.propertyName = propertyName;
  }
  
  public String getPropertyDesc()
  {
    return this.propertyDesc;
  }
  
  public void setPropertyDesc(String propertyDesc)
  {
    this.propertyDesc = propertyDesc;
  }
  
  public String getPropertyCode()
  {
    return this.propertyCode;
  }
  
  public void setPropertyCode(String propertyCode)
  {
    this.propertyCode = propertyCode;
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
  
  public String createMetaProperty()
  {
    this.result = new JsonResult();
    try
    {
      MetaProperty metaProperty = new MetaProperty();
      metaProperty.setPropertyLimit(this.propertyLimit);
      metaProperty.setPropertyName(this.propertyName);
      metaProperty.setPropertyDesc(this.propertyDesc);
      metaProperty.setPropertyCode(this.propertyCode);
      metaProperty.setPropertyId(this.metaService.createMetaProperty(metaProperty));
      
      this.result.success = true;
      this.result.setData(metaProperty);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String dropMetaProperty()
  {
    this.result = new JsonResult();
    try
    {
      this.metaService.delMetaProperty(this.propertyId);
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
  
  public String updateMetaProperty()
  {
    this.result = new JsonResult();
    try
    {
      MetaProperty metaProperty = new MetaProperty();
      metaProperty.setPropertyLimit(this.propertyLimit);
      metaProperty.setPropertyName(this.propertyName);
      metaProperty.setPropertyDesc(this.propertyDesc);
      metaProperty.setPropertyCode(this.propertyCode);
      metaProperty.setPropertyId(this.propertyId);
      
      this.metaService.updateMetaProperty(metaProperty);
      
      this.result.success = true;
      this.result.setData(metaProperty);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String listMetaProperty()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.listMetaProperty());
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
}
