package com.commondb.db.web;

import com.commondb.common.JsonResult;
import com.commondb.common.NameExistException;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.User;
import com.commondb.db.service.MetaService;
import com.commondb.security.service.SecurityUserHolder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

public class AdminAction
  extends ActionSupport
  implements Preparable,ServletRequestAware
{
  private Integer userId;
  private Integer metaId;
  private String[] picAttr;
  private String[] descAttr;
  private String[] hierarchyAttr;
  private String[] characterAttr;
  private String entityDesc;
  private String entityName;
  private Integer[] metaProperty;
  private Integer sourceMetaId;
  private List metaList;


  private File uploadMeta;
  private String uploadMetaFileName;
  private boolean success;
  
  public Integer[] getMetaProperty()
  {
    return this.metaProperty;
  }
  
  public void setMetaProperty(Integer[] metaProperty)
  {
    this.metaProperty = metaProperty;
  }
  
  public Integer getSourceMetaId()
  {
    return this.sourceMetaId;
  }
  
  public void setSourceMetaId(Integer sourceMetaId)
  {
    this.sourceMetaId = sourceMetaId;
  }
  
  private JsonResult result = new JsonResult();
  private MetaService metaService;
  private String nodeId;
  private String nodeName;
  private User user;
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public String getNodeName()
  {
    return this.nodeName;
  }
  
  public void setNodeName(String nodeName)
  {
    this.nodeName = nodeName;
  }
  
  public String getNodeId()
  {
    return this.nodeId;
  }
  
  public void setNodeId(String nodeId)
  {
    this.nodeId = nodeId;
  }
  
  public void prepare()
    throws Exception
  {
    this.user = SecurityUserHolder.getCurrentUser();
    this.metaList = this.metaService.findMetaPerm();
    if (this.metaId == null)
    {
      this.metaList = this.metaService.findMetaPerm();
      if (this.metaList.size() > 0) {
        this.metaId = ((Meta)this.metaList.get(0)).getMetaId();
      }
    }
    if (this.sourceMetaId == null)
    {
      this.metaList = this.metaService.findMetaPerm();
      if (this.metaList.size() > 0) {
        this.sourceMetaId = ((Meta)this.metaList.get(0)).getMetaId();
      }
    }
  }
  
  public String listMeta()
  {
    this.metaList = this.metaService.findAllDb();
    
    this.user = SecurityUserHolder.getCurrentUser();
    return "success";
  }
  
  public String findMeta()
  {
    this.metaList = this.metaService.findMeta(null);
    this.user = SecurityUserHolder.getCurrentUser();
    
    return "success";
  }
  
  public String findMetaPerm()
  {
    this.metaList = this.metaService.findMetaPerm();
    this.user = SecurityUserHolder.getCurrentUser();
    if (this.metaList.size() == 0) {
      return "noMeta";
    }
    return "success";
  }
  
  public String preNewRelationFront()
  {
    this.metaList = this.metaService.findAllDb();
    
    return "success";
  }
  
  public String preNewEntityFront()
  {
    this.metaList = this.metaService.findMeta(null);
    
    return "success";
  }
  
  public String saveNode()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.saveNode(this.nodeId, this.nodeName));
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
  
  public String editNode()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.editNode(this.nodeId, this.nodeName));
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
  
  public String delNode()
  {
    this.result = new JsonResult();
    try
    {
      this.metaService.delNode(this.nodeId);
      this.result.setData("成功");
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
  
  public String getAllMeta()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.findAllDb());
      
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
  
  public String getAllMetaForAdmin()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.findAllDb());
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
  
  public String createMeta()
  {
    this.result = new JsonResult();
    try
    {
      this.metaService.createMeta(this.entityName, this.entityDesc, this.picAttr, this.descAttr, this.hierarchyAttr, this.characterAttr, this.metaProperty);
      this.result.success = true;
    }
    catch (NameExistException e)
    {
      this.result.success = false;
      this.result.errormsg = (this.entityName + "已经存在");
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  
  public void importMeta()
  {
    this.result = new JsonResult();
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html");        
    try
    {
      //this.metaService.createMeta(this.entityName, this.entityDesc, this.picAttr, this.descAttr, this.hierarchyAttr, this.characterAttr, this.metaProperty);
      this.result.success = true;
      this.success = true;
    }
    catch (NameExistException e)
    {
      this.result.success = false;
      this.result.errormsg = (this.entityName + "已经存在");
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    String content = this.result.toString();
    try {
    	response.getWriter().write(content);
    	//response.getWriter().write("{\"success\":true}");
    	response.getWriter().flush();}
    catch(Exception e) {}

   // return "success";
  }
  
  
  public String updateMeta()
  {
    this.result = new JsonResult();
    try
    {
      Meta meta = new Meta();
      meta.setMetaId(this.metaId);
      meta.setEntityName(this.entityName);
      meta.setEntityDesc(this.entityDesc);
      this.metaService.updateMeta(meta);
      this.result.success = true;
      this.result.setData(meta);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String delMeta()
  {
    this.result = new JsonResult();
    try
    {
      Meta meta = (Meta)this.metaService.findMeta(this.metaId).get(0);
      this.metaService.delMeta(meta);
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
  
  public String fetchMetaAttrs()
  {
    this.result = new JsonResult();
    try
    {
      Map hMap = new HashMap();
      List l = this.metaService.findMeta(this.metaId);
      if (l.size() > 0)
      {
        hMap.put("entityName", ((Meta)l.get(0)).getEntityName());
        hMap.put("meta", (Meta)l.get(0));
      }
      else
      {
        hMap.put("entityName", "");
      }
      hMap.put("picAttr", this.metaService.findPicAttrDef(this.metaId));
      hMap.put("descAttr", this.metaService.findDescAttrDef(this.metaId));
      hMap.put("hierAttr", this.metaService.findHierarchyAttrDef(this.metaId));
      hMap.put("charaAttr", this.metaService.findCharacterAttrDef(this.metaId));
      
      this.result.setData(hMap);
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
  
  public String listPicAttrs()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.findPicAttrDef(this.metaId));
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
  
  public String listDescAttrs()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.findDescAttrDef(this.metaId));
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
  
  public String listHierAttrs()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.findHierarchyAttrDef(this.metaId));
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
  
  public String listCharaAttrs()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.findCharacterAttrDef(this.metaId));
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
  
  public List getMetaList()
  {
    return this.metaList;
  }
  
  public void setMetaList(List metaList)
  {
    this.metaList = metaList;
  }
  
  public Integer getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
  }
  
  public String getEntityDesc()
  {
    return this.entityDesc;
  }
  
  public void setEntityDesc(String entityDesc)
  {
    this.entityDesc = entityDesc;
  }
  
  public String getEntityName()
  {
    return this.entityName;
  }
  
  public void setEntityName(String entityName)
  {
    this.entityName = entityName;
  }
  
  public Integer getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
  
  public String[] getPicAttr()
  {
    return this.picAttr;
  }
  
  public void setPicAttr(String[] picAttr)
  {
    this.picAttr = picAttr;
  }
  
  public String[] getDescAttr()
  {
    return this.descAttr;
  }
  
  public void setDescAttr(String[] descAttr)
  {
    this.descAttr = descAttr;
  }
  
  public String[] getHierarchyAttr()
  {
    return this.hierarchyAttr;
  }
  
  public void setHierarchyAttr(String[] hierarchyAttr)
  {
    this.hierarchyAttr = hierarchyAttr;
  }
  
  public String[] getCharacterAttr()
  {
    return this.characterAttr;
  }
  
  public void setCharacterAttr(String[] characterAttr)
  {
    this.characterAttr = characterAttr;
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

@Override
public void setServletRequest(HttpServletRequest arg0) {
	// TODO Auto-generated method stub
	
}

public File getUploadMeta() {
	return uploadMeta;
}

public void setUploadMeta(File upload) {
	this.uploadMeta = upload;
}

public String getUploadMetaFileName() {
	return uploadMetaFileName;
}

public void setUploadMetaFileName(String uploadFileName) {
	this.uploadMetaFileName = uploadFileName;
}

public boolean isSuccess() {
	return success;
}

public void setSuccess(boolean success) {
	this.success = success;
}

}
