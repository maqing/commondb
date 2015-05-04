package com.commondb.app.web.file;

import com.commondb.app.web.SaveEntityAction;
import com.commondb.db.bo.REntity;
import com.commondb.db.bo.User;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class FileSaveEntityAction
  extends SaveEntityAction
  implements Preparable, ServletRequestAware
{
  private String appName;
  private String metaName;
  private int metaId;
  private String info;
  private MetaService metaService;
  private EntityService entityService;
  private User user;
  private Integer update;
  private String entityId;
  private String mergeEntityIds;
  HttpServletRequest request;
  private List<File> myFile;
  private List<String> myFileFileName;
  
  public String saveEntity()
  {
    Map valuesMap = super.makeValueMap();
    try
    {
      String newEntityID = "";
      String message = "";
      String note = "";
      if ((this.update != null) && (this.update.intValue() == 1))
      {
        this.entityService.updateEntity(Integer.valueOf(this.metaId), this.entityId, valuesMap);
        newEntityID = this.entityId;
        note = (String)valuesMap.get("d_91");
        message = "文件库修改记录，名称为：" + note;
      }
      else
      {
        newEntityID = this.entityService.createEntity(Integer.valueOf(this.metaId), valuesMap);
        note = (String)valuesMap.get("d_91");
        
        message = "文件库创建记录，名称为：" + note;
      }
      ArrayList rEntityList = new ArrayList();
      REntity rEntity1 = new REntity();
      rEntity1.setMeta2Id(Integer.valueOf(this.metaId));
      rEntity1.setEntity2Id(newEntityID);
      rEntity1.setLabel("文件库：" + note);
      rEntityList.add(rEntity1);
      this.entityService.createJournal("文件模块", message, rEntityList);
    }
    catch (Throwable t)
    {
      this.info = "保存失败";
      t.printStackTrace();
    }
    this.info = "保存成功";
    return "success";
  }
  
  public String getAppName()
  {
    return this.appName;
  }
  
  public void setAppName(String appName)
  {
    this.appName = appName;
  }
  
  public String getMetaName()
  {
    return this.metaName;
  }
  
  public void setMetaName(String metaName)
  {
    this.metaName = metaName;
  }
  
  public List<File> getMyFile()
  {
    return this.myFile;
  }
  
  public void setMyFile(List<File> myFile)
  {
    this.myFile = myFile;
  }
  
  public List<String> getMyFileFileName()
  {
    return this.myFileFileName;
  }
  
  public void setMyFileFileName(List<String> myFileFileName)
  {
    this.myFileFileName = myFileFileName;
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
    super.setEntityService(entityService);
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
  
  public Integer getMetaId()
  {
    return Integer.valueOf(this.metaId);
  }
  
  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId.intValue();
    super.setMetaId(metaId);
  }
  
  public String getInfo()
  {
    return this.info;
  }
  
  public void setInfo(String info)
  {
    this.info = info;
  }
  
  public void setMetaId(int metaId)
  {
    this.metaId = metaId;
  }
  
  public Integer getUpdate()
  {
    return this.update;
  }
  
  public void setUpdate(Integer update)
  {
    this.update = update;
  }
  
  public String getEntityId()
  {
    return this.entityId;
  }
  
  public void setEntityId(String entityId)
  {
    this.entityId = entityId;
  }
  
  public String getMergeEntityIds()
  {
    return this.mergeEntityIds;
  }
  
  public void setMergeEntityIds(String mergeEntityIds)
  {
    this.mergeEntityIds = mergeEntityIds;
  }
}
