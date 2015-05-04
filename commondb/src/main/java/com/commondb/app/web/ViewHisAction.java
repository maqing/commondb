package com.commondb.app.web;

import com.commondb.app.common.meta.FieldFactory;
import com.commondb.app.common.meta.IField;
import com.commondb.db.bo.CharacterDef;
import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.bo.HierarchyAttrDef;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.PicAttrDef;
import com.commondb.db.bo.RMetaChara;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class ViewHisAction
  extends BasicAction
  implements Preparable, ServletRequestAware
{
  private String appName;
  private String entityName;
  private String entityId;
  HttpServletRequest request;
  
  public String getEntityId()
  {
    return this.entityId;
  }
  
  public void setEntityId(String entityId)
  {
    this.entityId = entityId;
  }
  
  List picAttrDef = new ArrayList();
  List descAttrDef = new ArrayList();
  List hierConditions = new ArrayList();
  List charaConditions = new ArrayList();
  private List fieldsList;
  private List resList;
  private MetaService metaService;
  private EntityService entityService;
  
  public String viewHis()
  {
    if ((this.entityName == null) || (this.entityName.equals(""))) {
      this.entityName = "通讯录";
    }
    if ((this.entityName == null) || (this.entityName.equals(""))) {
      return "success";
    }
    Meta meta = this.metaService.findMetaByName(this.entityName);
    Integer metaId = meta.getId();
    
    List picAttrList = this.metaService.findPicAttrDef(metaId);
    List descAttrList = this.metaService.findDescAttrDef(metaId);
    List hierAttrList = this.metaService.findHierarchyAttrDef(metaId);
    List metaCharaList = this.metaService.listRMetaChara(metaId);
    List charaAttrList = new ArrayList();
    for (Object rm : metaCharaList)
    {
      CharacterDef charaDef = this.metaService.getCharaDefById(((RMetaChara)rm).getCharaId());
      charaAttrList.add(charaDef);
    }
    this.fieldsList = new ArrayList();
    for (int j = 0; j < picAttrList.size(); j++)
    {
      PicAttrDef picDef = (PicAttrDef)picAttrList.get(j);
      
      IField picField = FieldFactory.getInstance().createField(picDef);
      this.fieldsList.add(picField);
    }
    for (int j = 0; j < descAttrList.size(); j++)
    {
      DescAttrDef descDef = (DescAttrDef)descAttrList.get(j);
      
      IField descField = FieldFactory.getInstance().createField(descDef);
      this.fieldsList.add(descField);
      this.descAttrDef.add(descField);
    }
    for (int j = 0; j < charaAttrList.size(); j++)
    {
      CharacterDef charaDef = (CharacterDef)charaAttrList.get(j);
      
      IField charaField = FieldFactory.getInstance()
        .createField(charaDef);
      this.fieldsList.add(charaField);
    }
    IField hierField;
    for (int j = 0; j < hierAttrList.size(); j++)
    {
      HierarchyAttrDef hierDef = (HierarchyAttrDef)hierAttrList.get(j);
      
      hierField = FieldFactory.getInstance().createField(hierDef);
      this.fieldsList.add(hierField);
    }
    String columnsString = "";
    if (this.fieldsList.size() > 0)
    {
      for (Object f : this.fieldsList)
      {
        IField field = (IField)f;
        
        columnsString = columnsString + ", t_his_entity_" + metaId + "." + field.getColumnName() + " ";
      }
      columnsString = columnsString + ",  t_his_entity_" + metaId + ".relation," + " t_his_entity_" + metaId + ".update_time, " + "t_his_entity_" + metaId + ".update_user ";
      columnsString = columnsString.replaceFirst(",", "");
    }
    else
    {
      columnsString = "t_entity_" + metaId + ".*";
    }
    String fromStr = " t_his_entity_" + metaId + " ";
    StringBuffer whereStr = new StringBuffer(" id='" + this.entityId + "'");
    


    this.resList = this.entityService.dynSelect(columnsString, fromStr, whereStr.toString());
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
  
  public String getEntityName()
  {
    return this.entityName;
  }
  
  public void setEntityName(String entityName)
  {
    this.entityName = entityName;
  }
  
  public List getFieldsList()
  {
    return this.fieldsList;
  }
  
  public void setFieldsList(List fieldsList)
  {
    this.fieldsList = fieldsList;
  }
  
  public List getResList()
  {
    return this.resList;
  }
  
  public void setResList(List resList)
  {
    this.resList = resList;
  }
  
  public MetaService getMetaService()
  {
    return this.metaService;
  }
  
  public void setMetaService(MetaService metaService)
  {
    this.metaService = metaService;
  }
  
  public EntityService getEntityService()
  {
    return this.entityService;
  }
  
  public void setEntityService(EntityService entityService)
  {
    this.entityService = entityService;
    this.entityService = entityService;
  }
  
  public void prepare()
    throws Exception
  {}
  
  public void setServletRequest(HttpServletRequest arg0) {}
}
