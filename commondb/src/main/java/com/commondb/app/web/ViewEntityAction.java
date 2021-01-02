package com.commondb.app.web;

import com.commondb.app.common.SysConfig;
import com.commondb.app.common.meta.CharacterField;
import com.commondb.app.common.meta.DescField;
import com.commondb.app.common.meta.FieldFactory;
import com.commondb.app.common.meta.HierarchyField;
import com.commondb.app.common.meta.IField;
import com.commondb.common.PageInfo;
import com.commondb.db.bo.CharacterData;
import com.commondb.db.bo.CharacterDef;
import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.bo.HierarchyAttrDef;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.PicAttrDef;
import com.commondb.db.bo.RMetaChara;
import com.commondb.db.bo.User;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

public class ViewEntityAction
  extends BasicAction
  implements Preparable, ServletRequestAware
{
  private String appName;
  private String entityName;
  private String fieldsName;
  private String cconditions;
  private String hconditions;
  HttpServletRequest request;
  String conditionConnector;
  private String displayDiv;
  private String inputFieldId;
  private String mergeEntityIds;
  List picAttrDef = new ArrayList();
  List descAttrDef = new ArrayList();
  List hierFields = new ArrayList();
  List charaFields = new ArrayList();
  List picFields = new ArrayList();
  public List descFields = new ArrayList();
  List rEntityList = new ArrayList();
  String entityId;
  Integer metaId;
  private User user;
  private Map m;
  private Meta meta;
  private List fieldsList;
  private List resList;
  private List metaList;
  private PageInfo pageInfo = new PageInfo();
  private String orderBy;
  private MetaService metaService;
  private EntityService entityService;
  private String rEntityNames;
  List rAttachmentList = new ArrayList();

  public String viewEntity()
  {
    if (this.metaId == null)
    {
      if ((this.entityName == null) || (this.entityName.equals(""))) {
        this.entityName = SysConfig.DefaultEntityName;
      }
      if ((this.entityName == null) || (this.entityName.equals(""))) {
        return "success";
      }
      this.meta = this.metaService.findMetaByName(this.entityName);
      this.metaId = this.meta.getId();
    }
    else
    {
      this.meta = ((Meta)this.metaService.findMeta(this.metaId).get(0));
      this.entityName = this.meta.getEntityName();
    }
    this.metaList = this.metaService.findAllDb();


    List picAttrList = this.metaService.findPicAttrDef(this.metaId);
    List descAttrList = this.metaService.findDescAttrDef(this.metaId);
    List hierAttrList = this.metaService.findHierarchyAttrDef(this.metaId);
    List metaCharaList = this.metaService.listRMetaChara(this.metaId);
    List charaAttrList = new ArrayList();
    for (Object rm : metaCharaList)
    {
      CharacterDef charaDef = this.metaService.getCharaDefById(((RMetaChara)rm).getCharaId());
      charaAttrList.add(charaDef);
    }
    for (int j = 0; j < hierAttrList.size(); j++)
    {
      HierarchyAttrDef hDef = (HierarchyAttrDef)hierAttrList.get(j);
      IField hField = FieldFactory.getInstance().createField(hDef);
      this.hierFields.add(hField);
    }
    for (int j = 0; j < charaAttrList.size(); j++)
    {
      CharacterDef cDef = (CharacterDef)charaAttrList.get(j);
      IField cField = FieldFactory.getInstance().createField(cDef);
      this.charaFields.add(cField);
    }
    for (int j = 0; j < picAttrList.size(); j++)
    {
      PicAttrDef picDef = (PicAttrDef)picAttrList.get(j);

      IField picField = FieldFactory.getInstance()
        .createField(picDef);
      this.picFields.add(picField);
    }
    for (int j = 0; j < descAttrList.size(); j++)
    {
      DescAttrDef descDef = (DescAttrDef)descAttrList.get(j);

      IField descField = FieldFactory.getInstance().createField(
        descDef);
      this.descFields.add(descField);
      this.descAttrDef.add(descField);
    }
    Enumeration<String> keys = this.request.getParameterNames();
    while (keys.hasMoreElements())
    {
      String key = (String)keys.nextElement();
      if (key.startsWith("s_"))
      {
        key = key.replaceFirst("s_", "");
        if (key.startsWith("c_")) {
          for (Object field : this.charaFields)
          {
            CharacterField cField = (CharacterField)field;
            if (key.equals(cField.getColumnName())) {
              cField.setValue(this.request.getParameter("s_" + key));
            }
          }
        }
        if (key.startsWith("h_")) {
          for (Object field : this.hierFields)
          {
            HierarchyField hField = (HierarchyField)field;
            if (key.equals(hField.getColumnName())) {
              hField.setValue(this.request.getParameter("s_" + key));
            }
          }
        }
        if (key.startsWith("d_")) {
          for (Object field : this.fieldsList)
          {
            DescField dField = (DescField)field;
            if (key.equals(dField.getColumnName())) {
              dField.setValue(this.request.getParameter("s_" + key));
            }
          }
        }
      }
    }
    String columnsString = "";
    columnsString = "t_entity_" + this.metaId + ".*";

    String fromStr = " t_entity_" + this.metaId + " ";

    StringBuffer whereStr = new StringBuffer(" (t_entity_" + this.metaId + ".id='" + this.entityId + "') ");


    List result = this.entityService.dynSelect(columnsString, fromStr, whereStr.toString());
    if (result.size() <= 0) {
      return "success";
    }
    Map map = (Map)result.get(0);
    for (int j = 0; j < this.picFields.size(); j++)
    {
      IField field = (IField)this.picFields.get(j);
      if (map.get(field.getColumnName()) != null) {
        field.setValue(map.get(field.getColumnName()).toString());
      }
    }
    for (int j = 0; j < this.descFields.size(); j++)
    {
      IField field = (IField)this.descFields.get(j);
      if (map.get(field.getColumnName()) != null) {
        field.setValue(map.get(field.getColumnName()).toString());
      }
    }
    result = this.entityService.findHierarchyByEntity(this.metaId, this.entityId);
    if (result.size() > 0) {
      for (int k = 0; k < result.size(); k++)
      {
        map = (Map)result.get(k);
        for (int m = 0; m < this.hierFields.size(); m++)
        {
          HierarchyField hField = (HierarchyField)this.hierFields.get(m);
          if (map.get("attr_id").equals(hField.getColumnName().replaceFirst("h_", "")))
          {
            if ((hField.getValue() == null) || (hField.getValue().equals("")))
            {
              hField.setValue(map.get("value_id").toString()); break;
            }
            hField.setValue(hField.getValue() + "," + map.get("value_id").toString());

            break;
          }
        }
      }
    }
    result = this.metaService.listCharaDataByEntity(this.metaId, this.entityId);
    CharacterData cData;
    if (result.size() > 0) {
      for (int k = 0; k < result.size(); k++)
      {
        cData = (CharacterData)result.get(k);
        for (int m = 0; m < this.charaFields.size(); m++)
        {
          CharacterField cField = (CharacterField)this.charaFields.get(m);
          if (cData.getCharaId().toString().equals(cField.getColumnName().replaceFirst("c_", "")))
          {
            if ((cField.getValue() == null) || (cField.getValue().equals("")))
            {
              cField.setValue(cData.getDataId().toString()); break;
            }
            cField.setValue(cField.getValue() + "," + cData.getDataId().toString());

            break;
          }
        }
      }
    }
    for (Object field : this.hierFields) {
      this.metaService.initHierFieldDisplayValue((HierarchyField)field);
    }
    for (Object field : this.charaFields)
    {
      CharacterField cField = (CharacterField)field;
      cField.initInputHtml(this.metaService.listCharaData(new Integer(cField.getColumnName().replaceFirst("c_", ""))));
    }
    this.rEntityList = this.entityService.listREntity(this.metaId, this.entityId);

    this.rAttachmentList = this.entityService.listRAttachment(this.metaId, this.entityId);

    return "success";
  }

  public String preMergeEntity()
  {
    if (this.metaId == null)
    {
      if ((this.entityName == null) || (this.entityName.equals(""))) {
        this.entityName = SysConfig.DefaultEntityName;
      }
      if ((this.entityName == null) || (this.entityName.equals(""))) {
        return "success";
      }
      this.meta = this.metaService.findMetaByName(this.entityName);
      this.metaId = this.meta.getId();
    }
    else
    {
      this.meta = ((Meta)this.metaService.findMeta(this.metaId).get(0));
      this.entityName = this.meta.getEntityName();
    }
    this.metaList = this.metaService.findAllDb();


    List picAttrList = this.metaService.findPicAttrDef(this.metaId);
    List descAttrList = this.metaService.findDescAttrDef(this.metaId);
    List hierAttrList = this.metaService.findHierarchyAttrDef(this.metaId);
    List metaCharaList = this.metaService.listRMetaChara(this.metaId);
    List charaAttrList = new ArrayList();
    for (Object rm : metaCharaList)
    {
      CharacterDef charaDef = this.metaService.getCharaDefById(((RMetaChara)rm).getCharaId());
      charaAttrList.add(charaDef);
    }
    for (int j = 0; j < hierAttrList.size(); j++)
    {
      HierarchyAttrDef hDef = (HierarchyAttrDef)hierAttrList.get(j);
      IField hField = FieldFactory.getInstance().createField(hDef);
      this.hierFields.add(hField);
    }
    for (int j = 0; j < charaAttrList.size(); j++)
    {
      CharacterDef cDef = (CharacterDef)charaAttrList.get(j);
      IField cField = FieldFactory.getInstance().createField(cDef);
      this.charaFields.add(cField);
    }
    for (int j = 0; j < picAttrList.size(); j++)
    {
      PicAttrDef picDef = (PicAttrDef)picAttrList.get(j);

      IField picField = FieldFactory.getInstance()
        .createField(picDef);
      this.picFields.add(picField);
    }
    for (int j = 0; j < descAttrList.size(); j++)
    {
      DescAttrDef descDef = (DescAttrDef)descAttrList.get(j);

      IField descField = FieldFactory.getInstance().createField(
        descDef);
      this.descFields.add(descField);
      this.descAttrDef.add(descField);
    }
    Enumeration<String> keys = this.request.getParameterNames();
    while (keys.hasMoreElements())
    {
      String key = (String)keys.nextElement();
      if (key.startsWith("s_"))
      {
        key = key.replaceFirst("s_", "");
        if (key.startsWith("c_")) {
          for (Object field : this.charaFields)
          {
            CharacterField cField = (CharacterField)field;
            if (key.equals(cField.getColumnName())) {
              cField.setValue(this.request.getParameter("s_" + key));
            }
          }
        }
        if (key.startsWith("h_")) {
          for (Object field : this.hierFields)
          {
            HierarchyField hField = (HierarchyField)field;
            if (key.equals(hField.getColumnName())) {
              hField.setValue(this.request.getParameter("s_" + key));
            }
          }
        }
        if (key.startsWith("d_")) {
          for (Object field : this.descFields)
          {
            DescField dField = (DescField)field;
            if (key.equals(dField.getColumnName())) {
              dField.setValue(this.request.getParameter("s_" + key));
            }
          }
        }
      }
    }
    String[] entityIds = this.mergeEntityIds.split(",");
    for (int idx = 0; idx < entityIds.length; idx++)
    {
      this.entityId = entityIds[idx];


      String columnsString = "";
      columnsString = "t_entity_" + this.metaId + ".*";

      String fromStr = " t_entity_" + this.metaId + " ";

      StringBuffer whereStr = new StringBuffer(" (t_entity_" + this.metaId + ".id='" + this.entityId + "') ");


      List result = this.entityService.dynSelect(columnsString, fromStr, whereStr.toString());
      if (result.size() <= 0) {
        return "success";
      }
      Map map = (Map)result.get(0);
      for (int j = 0; j < this.picFields.size(); j++)
      {
        IField field = (IField)this.picFields.get(j);
        if (map.get(field.getColumnName()) != null) {
          field.setValue(map.get(field.getColumnName()).toString());
        }
      }
      for (int j = 0; j < this.descFields.size(); j++)
      {
        IField field = (IField)this.descFields.get(j);
        if (map.get(field.getColumnName()) != null) {
          field.setValue(map.get(field.getColumnName()).toString());
        }
      }
      result = this.entityService.findHierarchyByEntity(this.metaId, this.entityId);
      if (result.size() > 0) {
        for (int k = 0; k < result.size(); k++)
        {
          map = (Map)result.get(k);
          for (int m = 0; m < this.hierFields.size(); m++)
          {
            HierarchyField hField = (HierarchyField)this.hierFields.get(m);
            if (map.get("attr_id").equals(hField.getColumnName().replaceFirst("h_", "")))
            {
              if ((hField.getValue() == null) || (hField.getValue().equals("")))
              {
                hField.setValue(map.get("value_id").toString()); break;
              }
              hField.setValue(hField.getValue() + "," + map.get("value_id").toString());

              break;
            }
          }
        }
      }
      result = this.metaService.listCharaDataByEntity(this.metaId, this.entityId);
      CharacterData cData;
      if (result.size() > 0) {
        for (int k = 0; k < result.size(); k++)
        {
          cData = (CharacterData)result.get(k);
          for (int m = 0; m < this.charaFields.size(); m++)
          {
            CharacterField cField = (CharacterField)this.charaFields.get(m);
            if (cData.getCharaId().equals(cField.getColumnName().replaceFirst("c_", "")))
            {
              if ((cField.getValue() == null) || (cField.getValue().equals("")))
              {
                cField.setValue(cData.getDataId().toString()); break;
              }
              cField.setValue(cField.getValue() + "," + cData.getDataId().toString());

              break;
            }
          }
        }
      }
      for (Object field : this.hierFields) {
        this.metaService.initHierFieldDisplayValue((HierarchyField)field);
      }
      for (Object field : this.charaFields)
      {
        CharacterField cField = (CharacterField)field;
        cField.initInputHtml(this.metaService.listCharaData(new Integer(cField.getColumnName().replaceFirst("c_", ""))));
      }
      result = this.entityService.listREntity(this.metaId, this.entityId);
      if (result.size() > 0) {
        for (int k = 0; k < result.size(); k++) {
          this.rEntityList.add(result.get(k));
        }
      }
      result = this.entityService.listRAttachment(this.metaId, this.entityId);
      if (result.size() > 0) {
        for (int k = 0; k < result.size(); k++) {
          this.rAttachmentList.add(result.get(k));
        }
      }
    }
    return "success";
  }

  public List getFieldsList()
  {
    return this.fieldsList;
  }

  public void setFieldsList(List fieldsList)
  {
    this.fieldsList = fieldsList;
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

  public String getFieldsName()
  {
    return this.fieldsName;
  }

  public void setFieldsName(String fieldsName)
  {
    this.fieldsName = fieldsName;
  }

  public Map getM()
  {
    return this.m;
  }

  public void setM(Map m)
  {
    this.m = m;
  }

  public Meta getMeta()
  {
    return this.meta;
  }

  public void setMeta(Meta meta)
  {
    this.meta = meta;
  }

  public void setServletRequest(HttpServletRequest request)
  {
    this.request = request;
  }

  public List getPicAttrDef()
  {
    return this.picAttrDef;
  }

  public void setPicAttrDef(List picAttrDef)
  {
    this.picAttrDef = picAttrDef;
  }

  public List getDescAttrDef()
  {
    return this.descAttrDef;
  }

  public void setDescAttrDef(List descAttrDef)
  {
    this.descAttrDef = descAttrDef;
  }

  public String getConditionConnector()
  {
    return this.conditionConnector;
  }

  public void setConditionConnector(String conditionConnector)
  {
    this.conditionConnector = conditionConnector;
  }

  public String getCconditions()
  {
    return this.cconditions;
  }

  public void setCconditions(String cconditions)
  {
    this.cconditions = cconditions;
  }

  public String getHconditions()
  {
    return this.hconditions;
  }

  public void setHconditions(String hconditions)
  {
    this.hconditions = hconditions;
  }

  public List getResList()
  {
    return this.resList;
  }

  public void setResList(List resList)
  {
    this.resList = resList;
  }

  public PageInfo getPageInfo()
  {
    return this.pageInfo;
  }

  public void setPageInfo(PageInfo pageInfo)
  {
    this.pageInfo = pageInfo;
  }

  public String getOrderBy()
  {
    return this.orderBy;
  }

  public void setOrderBy(String orderBy)
  {
    this.orderBy = orderBy;
  }

  public List getMetaList()
  {
    return this.metaList;
  }

  public void setMetaList(List metaList)
  {
    this.metaList = metaList;
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

  public List getHierFields()
  {
    return this.hierFields;
  }

  public void setHierFields(List hierFields)
  {
    this.hierFields = hierFields;
  }

  public List getCharaFields()
  {
    return this.charaFields;
  }

  public void setCharaFields(List charaFields)
  {
    this.charaFields = charaFields;
  }

  public List getPicFields()
  {
    return this.picFields;
  }

  public void setPicFields(List picFields)
  {
    this.picFields = picFields;
  }

  public List getDescFields()
  {
    return this.descFields;
  }

  public void setDescFields(List descFields)
  {
    this.descFields = descFields;
  }

  public List getREntityList()
  {
    return this.rEntityList;
  }

  public void setREntityList(List entityList)
  {
    this.rEntityList = entityList;
  }

  public String getEntityId()
  {
    return this.entityId;
  }

  public void setEntityId(String entityId)
  {
    this.entityId = entityId;
  }

  public Integer getMetaId()
  {
    return this.metaId;
  }

  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
  }

  public String getREntityNames()
  {
    return this.rEntityNames;
  }

  public void setREntityNames(String entityNames)
  {
    this.rEntityNames = entityNames;
  }

  public String getMergeEntityIds()
  {
    return this.mergeEntityIds;
  }

  public void setMergeEntityIds(String mergeEntityId)
  {
    this.mergeEntityIds = mergeEntityId;
  }

  public List getRAttachmentList()
  {
    return this.rAttachmentList;
  }

  public void setRAttachmentList(List attachmentList)
  {
    this.rAttachmentList = attachmentList;
  }
}
