package com.commondb.app.web;

import com.commondb.db.bo.HierarchyAttrValue;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class HierSelectAction
  extends ActionSupport
  implements Preparable
{
  private Integer attrId;
  Map treeValue;
  private String operation;
  private String id;
  private String position;
  private String title;
  private String type;
  private String initSelect;
  private String[] selectNodeArr;
  private String displayDiv;
  private String inputFieldId;
  private MetaService metaService;
  private EntityService entityService;
  private Map result = new HashMap();
  
  public String getHierData()
  {
    this.treeValue = this.metaService.loadHieraAttrJsTree(this.attrId);
    if ((this.initSelect != null) && (!this.initSelect.equals(""))) {
      this.selectNodeArr = this.initSelect.split(",");
    }
    return "success";
  }
  
  public String manageHier()
  {
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("text/xml;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    if (this.operation == null) {
      return "success";
    }
    if (this.operation.equals("create_node"))
    {
      HierarchyAttrValue nodeValue = this.metaService.saveNode(this.id, this.title);
      this.result.put("status", Integer.valueOf(1));
      this.result.put("id", nodeValue.getValueId());
    }
    if (this.operation.equals("rename_node"))
    {
      HierarchyAttrValue nodeValue = this.metaService.editNode(this.id, this.title);
      this.result.put("status", Integer.valueOf(1));
    }
    if (this.operation.equals("remove_node"))
    {
      this.metaService.delNode(this.id);
      this.result.put("status", Integer.valueOf(1));
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
  
  public Integer getAttrId()
  {
    return this.attrId;
  }
  
  public void setAttrId(Integer attrId)
  {
    this.attrId = attrId;
  }
  
  public String getOperation()
  {
    return this.operation;
  }
  
  public void setOperation(String operation)
  {
    this.operation = operation;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getPosition()
  {
    return this.position;
  }
  
  public void setPosition(String position)
  {
    this.position = position;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public Map getResult()
  {
    return this.result;
  }
  
  public void setResult(Map result)
  {
    this.result = result;
  }
  
  public String getInitSelect()
  {
    return this.initSelect;
  }
  
  public void setInitSelect(String m)
  {
    this.initSelect = m;
    if ((this.initSelect != null) && (!this.initSelect.equals(""))) {
      this.selectNodeArr = this.initSelect.split(",");
    }
  }
  
  public String[] getSelectNodeArr()
  {
    return this.selectNodeArr;
  }
  
  public void setSelectNodeArr(String[] selectNodeArr)
  {
    this.selectNodeArr = selectNodeArr;
  }
  
  public String selectHier()
  {
    return "success";
  }
  
  public Map getTreeValue()
  {
    return this.treeValue;
  }
  
  public void setTreeValue(Map treeValue)
  {
    this.treeValue = treeValue;
  }
  
  public String getDisplayDiv()
  {
    return this.displayDiv;
  }
  
  public void setDisplayDiv(String displayDiv)
  {
    this.displayDiv = displayDiv;
  }
  
  public String getInputFieldId()
  {
    return this.inputFieldId;
  }
  
  public void setInputFieldId(String inputFieldId)
  {
    this.inputFieldId = inputFieldId;
  }
}
