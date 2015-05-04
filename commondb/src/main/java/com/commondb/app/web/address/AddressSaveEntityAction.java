package com.commondb.app.web.address;

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

public class AddressSaveEntityAction
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
  HttpServletRequest request;
  private List<File> myFile;
  private List<String> myFileFileName;
  
  public String saveEntity()
  {
    Map valuesMap = super.makeValueMap();
    try
    {
      String message = "";
      String note = "";
      note = (String)valuesMap.get("d_34");
      if ((this.update != null) && (this.update.intValue() == 1))
      {
        this.entityService.updateEntity(Integer.valueOf(this.metaId), this.entityId, valuesMap);
        message = "通讯录修改记录，内容为：" + note;
      }
      else
      {
        this.entityId = this.entityService.createEntity(Integer.valueOf(this.metaId), valuesMap);
        message = "通讯录创建记录，内容为：" + note;
      }
      ArrayList rEntityList = new ArrayList();
      REntity rEntity1 = new REntity();
      rEntity1.setMeta2Id(Integer.valueOf(this.metaId));
      rEntity1.setEntity2Id(this.entityId);
      rEntity1.setLabel("通讯录：" + note);
      rEntityList.add(rEntity1);
      this.entityService.createJournal("通讯录", message, rEntityList);
      


      String columnsString = " value_id ";
      String fromStr = " r_entity_hierarchy_data  ";
      String whereStr = " attr_id=1 and entity_id='" + this.entityId + "'";
      

      List deptResult = this.entityService.dynSelect(columnsString, fromStr, whereStr.toString());
      if (deptResult.size() > 0) {
        this.info = 
          super.initCustomerEntity(((Map)deptResult.get(0)).get("value_id").toString(), this.entityId, "通讯录：" + note, true);
      }
      this.info = ("保存成功。" + this.info);
    }
    catch (Throwable t)
    {
      this.info = "保存失败。";
      t.printStackTrace();
    }
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
}
