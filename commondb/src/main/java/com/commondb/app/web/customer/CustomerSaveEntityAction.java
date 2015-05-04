package com.commondb.app.web.customer;

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
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

public class CustomerSaveEntityAction
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
  static Logger logger = Logger.getLogger(CustomerSaveEntityAction.class);

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
        note = (String)valuesMap.get("d_142");
        message = "客户库修改记录，内容为：" + note;
        if ((this.mergeEntityIds != null) && (!this.mergeEntityIds.equals("")))
        {
          String[] mergeEntityId = this.mergeEntityIds.split(",");
          if (mergeEntityId.length > 1)
          {
            message = message + "。合并记录id为: ";
            for (int i = 0; i < mergeEntityId.length - 1; i++)
            {
              this.entityService.delEntity(Integer.valueOf(this.metaId), mergeEntityId[i]);
              message = message + mergeEntityId[i] + ",";
            }
            message = message.substring(0, message.length() - 1) + "。";
          }
        }
      }
      else
      {
        newEntityID = this.entityService.createEntity(Integer.valueOf(this.metaId), valuesMap);
        note = (String)valuesMap.get("d_142");

        message = "客户库创建记录，内容为：" + note;
      }
      ArrayList rEntityList = new ArrayList();
      REntity rEntity1 = new REntity();
      rEntity1.setMeta2Id(Integer.valueOf(this.metaId));
      rEntity1.setEntity2Id(newEntityID);
      rEntity1.setLabel("客户库：" + note);
      rEntityList.add(rEntity1);
      this.entityService.createJournal("市场模块", message, rEntityList);
    }
    catch (Throwable t)
    {
      this.info = "保存失败";
      t.printStackTrace();
    }
    this.info = "保存成功";
    return "success";
  }

  public String initEntity()
  {
    int processCount = 0;
    try
    {
      String columnsString = " distinct b.value_id ";
      String fromStr = " r_entity_hierarchy_data b, t_entity_9 c ";
      String whereStr = " b.attr_id=28 and c.id=b.entity_id ";


      List deptResult = this.entityService.dynSelect(columnsString, fromStr, whereStr.toString());
      for (int i = 0; i < deptResult.size(); i++)
      {
        String deptID = ((Map)deptResult.get(i)).get("value_id").toString();

        this.info = deptID;

        super.initCustomerEntity(deptID, null, null, false);
        processCount++;
      }
      this.info = "更新成功: 共" + processCount + "条记录.";
      logger.info(this.info);
    }
    catch (Throwable t)
    {
      this.info = ("更新失败" + this.info + t.getMessage());
      t.printStackTrace();
      logger.error(this.info + t.getMessage());
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
