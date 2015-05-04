package com.commondb.app.web.project;

import com.commondb.app.web.SaveEntityAction;
import com.commondb.db.bo.CharacterData;
import com.commondb.db.bo.REntity;
import com.commondb.db.bo.User;
import com.commondb.db.dao.CharacterDataDAO;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class ProjectSaveEntityAction
  extends SaveEntityAction
  implements Preparable, ServletRequestAware
{
  private String appName;
  private String metaName;
  private int metaId;
  private String info;
  private MetaService metaService;
  private EntityService entityService;
  private CharacterDataDAO characterDataDAO;
  private User user;
  private Integer update;
  private String entityId;
  HttpServletRequest request;
  private String remindCheck;
  private String[] remindUserId;
  private String remindDate;
  private String remindMessage;
  private String codeTypeColumn = "10";
  private String bussinessTypeColumn = "11";
  private String bussinessDifColumn = "12";
  private String executorTypeColumn = "13";
  private String partnerTypeColumn = "14";
  private String projectCodeColumn = "d_158";
  private String noteColumn = "d_138";
  private HashMap codeTypeMap = new HashMap();
  private HashMap bussinessTypeMap = new HashMap();
  private HashMap bussinessDifMap = new HashMap();
  private HashMap executorTypeMap = new HashMap();
  private HashMap partnerTypeMap = new HashMap();
  
  public String saveEntity()
  {
    Map valuesMap = super.makeValueMap();
    try
    {
      String newEntityID = "";
      String message = "";
      String note = "";
      
      valuesMap.put(this.projectCodeColumn, getProjectCode(valuesMap));
      if ((this.update != null) && (this.update.intValue() == 1))
      {
        this.entityService.updateEntity(Integer.valueOf(this.metaId), this.entityId, valuesMap);
        newEntityID = this.entityId;
        note = (String)valuesMap.get(this.noteColumn);
        message = "项目库修改记录，内容为：" + note;
      }
      else
      {
        newEntityID = this.entityService.createEntity(Integer.valueOf(this.metaId), valuesMap);
        note = (String)valuesMap.get(this.noteColumn);
        
        message = "项目库创建记录，内容为：" + note;
        if (this.remindCheck.equals("yes"))
        {
          if ((this.remindUserId != null) && (this.remindUserId.length > 0)) {
            for (int i = 0; i < this.remindUserId.length; i++) {
              this.entityService.remind(new Integer(this.remindUserId[i]), new Integer(this.metaId), newEntityID, this.remindMessage, this.remindDate);
            }
          }
          message = message + "; 提醒信息为：" + this.remindMessage;
        }
      }
      ArrayList rEntityList = new ArrayList();
      REntity rEntity1 = new REntity();
      rEntity1.setMeta2Id(Integer.valueOf(this.metaId));
      rEntity1.setEntity2Id(newEntityID);
      rEntity1.setLabel("项目库：" + note);
      rEntityList.add(rEntity1);
      this.entityService.createJournal("项目库", message, rEntityList);
    }
    catch (Throwable t)
    {
      this.info = "保存失败";
      t.printStackTrace();
    }
    this.info = "保存成功";
    return "success";
  }
  
  public synchronized String getProjectCode(Map valuesMap)
  {
    String dayAndSeqCode = "";
    
    String oldProjectCode = (String)valuesMap.get(this.projectCodeColumn);
    if ((this.update != null) && (this.update.intValue() == 1) && (oldProjectCode != null) && (oldProjectCode.length() == 16)) {
      dayAndSeqCode = oldProjectCode.substring(5, 13);
    } else {
      dayAndSeqCode = getMaxSeqCode();
    }
    String codeTypePrefix = "0";
    String bussinessTypePrefix = "0";
    String bussinessDifPrefix = "0";
    String executorTypePrefix = "0";
    String partnerTypePrefix = "0";
    
    Integer[][] charaArr = (Integer[][])valuesMap.get("charaArr");
    if (charaArr != null) {
      for (Integer[] chara : charaArr)
      {
        String characterDataDesc = this.characterDataDAO.selectByPrimaryKey(chara[1]).getDataDesc();
        if (chara[0].equals(Integer.valueOf(this.codeTypeColumn))) {
          codeTypePrefix = (String)this.codeTypeMap.get(characterDataDesc);
        }
        if (chara[0].equals(Integer.valueOf(this.bussinessTypeColumn))) {
          bussinessTypePrefix = (String)this.bussinessTypeMap.get(characterDataDesc);
        }
        if (chara[0].equals(Integer.valueOf(this.bussinessDifColumn))) {
          bussinessDifPrefix = (String)this.bussinessDifMap.get(characterDataDesc);
        }
        if (chara[0].equals(Integer.valueOf(this.executorTypeColumn))) {
          executorTypePrefix = (String)this.executorTypeMap.get(characterDataDesc);
        }
        if (chara[0].equals(Integer.valueOf(this.partnerTypeColumn))) {
          partnerTypePrefix = (String)this.partnerTypeMap.get(characterDataDesc);
        }
      }
    }
    return 
      codeTypePrefix + bussinessTypePrefix + bussinessDifPrefix + executorTypePrefix + partnerTypePrefix + dayAndSeqCode + "000";
  }
  
  public synchronized String getMaxSeqCode()
  {
    String key = new SimpleDateFormat("yyMMdd").format(new Date());
    

    String columnsString = " max(SUBSTRING(" + this.projectCodeColumn + ",12,2)) as maxSeq ";
    String fromStr = " t_entity_13 ";
    String whereStr = " SUBSTRING(" + this.projectCodeColumn + ",6,6)='" + key + "' ";
    
    int maxSeq = 0;
    List seqResult = this.entityService.dynSelect(columnsString, fromStr, whereStr);
    if (seqResult.size() > 0)
    {
      Object maxObject = ((Map)seqResult.get(0)).get("maxSeq");
      if (maxObject != null) {
        maxSeq = Integer.parseInt(maxObject.toString());
      }
    }
    return key + String.format("%02d", new Object[] { Integer.valueOf(maxSeq + 1) });
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
  
  public void prepare()
    throws Exception
  {
    this.codeTypeMap.put("业务", "1");
    this.codeTypeMap.put("档案", "2");
    this.codeTypeMap.put("财务", "3");
    this.codeTypeMap.put("记录", "4");
    
    this.bussinessTypeMap.put("项目合同", "1");
    this.bussinessTypeMap.put("备件合同", "2");
    this.bussinessTypeMap.put("服务合同", "3");
    this.bussinessTypeMap.put("其他合同", "4");
    this.bussinessTypeMap.put("专项业务", "5");
    this.bussinessTypeMap.put("杂项业务", "6");
    this.bussinessTypeMap.put("公司特定", "0");
    
    this.bussinessDifMap.put("销售", "1");
    this.bussinessDifMap.put("采购", "2");
    this.bussinessDifMap.put("公司内", "0");
    
    this.executorTypeMap.put("章思遥", "0");
    this.executorTypeMap.put("郭懿", "1");
    this.executorTypeMap.put("张晋生", "2");
    
    this.partnerTypeMap.put("IDECO", "1");
    this.partnerTypeMap.put("F+K", "2");
    this.partnerTypeMap.put("MASSIN", "3");
    this.partnerTypeMap.put("VENT", "4");
    this.partnerTypeMap.put("本公司", "0");
  }
  
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
  
  public String getRemindCheck()
  {
    return this.remindCheck;
  }
  
  public void setRemindCheck(String remindCheck)
  {
    this.remindCheck = remindCheck;
  }
  
  public String[] getRemindUserId()
  {
    return this.remindUserId;
  }
  
  public void setRemindUserId(String[] userId)
  {
    this.remindUserId = userId;
  }
  
  public String getRemindDate()
  {
    return this.remindDate;
  }
  
  public void setRemindDate(String remindDate)
  {
    this.remindDate = remindDate;
  }
  
  public String getRemindMessage()
  {
    return this.remindMessage;
  }
  
  public void setRemindMessage(String remindMessage)
  {
    this.remindMessage = remindMessage;
  }
  
  public CharacterDataDAO getCharacterDataDAO()
  {
    return this.characterDataDAO;
  }
  
  public void setCharacterDataDAO(CharacterDataDAO characterDataDAO)
  {
    this.characterDataDAO = characterDataDAO;
  }
}
