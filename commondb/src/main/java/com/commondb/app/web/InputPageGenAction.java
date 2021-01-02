package com.commondb.app.web;

import com.commondb.app.common.SysConfig;
import com.commondb.app.common.meta.CharacterField;
import com.commondb.app.common.meta.Field;
import com.commondb.app.common.meta.FieldFactory;
import com.commondb.app.common.meta.IField;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

public class InputPageGenAction
  extends BasicAction
  implements Preparable, ServletRequestAware
{
  private String appName;
  private String entityName;
  private String fieldsName;
  HttpServletRequest request;
  private User user;
  private Meta meta;
  private Integer metaId;
  private List fieldsList;
  private MetaService metaService;
  private EntityService entityService;
  private String rMetaId;
  private String rMetaName;
  private String rEntityId;
  private String rEntityLabel;
  private String rOutHtml;
  private Map fieldsMap = new HashMap();

  public String genForm()
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
    this.request.getParameterNames();
    List picAttrList = this.metaService.findPicAttrDef(this.metaId);
    List descAttrList = this.metaService.findDescAttrDef(this.metaId);
    List hierAttrList = this.metaService.findHierarchyAttrDef(this.metaId);
    List charaAttrList = this.metaService.listRMetaChara(this.metaId);

    this.fieldsList = new ArrayList();
    HierarchyAttrDef hierDef;
    if (this.fieldsName != null)
    {
      String[] fieldsNameArray = this.fieldsName.split("|");
      for (int i = 0; i < fieldsNameArray.length; i++)
      {
        boolean found = false;
        String fieldName = fieldsNameArray[i];
        if (!found) {
          for (int j = 0; j < picAttrList.size(); j++)
          {
            PicAttrDef picDef = (PicAttrDef)picAttrList.get(j);
            if (fieldName.equals(picDef.getAttrName()))
            {
              IField picField = FieldFactory.getInstance().createField(picDef);
              found = true;
              this.fieldsList.add(picField);
              break;
            }
          }
        }
        if (!found) {
          for (int j = 0; j < descAttrList.size(); j++)
          {
            DescAttrDef descDef = (DescAttrDef)descAttrList.get(j);
            if (fieldName.equals(descDef.getAttrName()))
            {
              IField descField = FieldFactory.getInstance().createField(descDef);
              found = true;
              this.fieldsList.add(descField);
              break;
            }
          }
        }
        if (!found) {
          for (int j = 0; j < charaAttrList.size(); j++)
          {
            RMetaChara rMetaChara = (RMetaChara)charaAttrList.get(j);
            CharacterDef charaDef = this.metaService.getCharaDefById(rMetaChara.getCharaId());
            if (fieldName.equals(charaDef.getCharaName()))
            {
              IField charaField = FieldFactory.getInstance().createField(charaDef);
              found = true;
              ((CharacterField)charaField).initInputHtml(this.metaService.listCharaData(charaDef.getCharaId()));
              this.fieldsList.add(charaField);
              break;
            }
          }
        }
        if (!found) {
          for (int j = 0; j < hierAttrList.size(); j++)
          {
            hierDef = (HierarchyAttrDef)hierAttrList.get(j);
            if (fieldName.equals(hierDef.getAttrName()))
            {
              IField hierField = FieldFactory.getInstance().createField(hierDef);
              found = true;
              this.fieldsList.add(hierField);
              break;
            }
          }
        }
      }
    }
    else
    {
      for (int j = 0; j < picAttrList.size(); j++)
      {
        PicAttrDef picDef = (PicAttrDef)picAttrList.get(j);

        IField picField = FieldFactory.getInstance()
          .createField(picDef);

        this.fieldsList.add(picField);
      }
      for (int j = 0; j < descAttrList.size(); j++)
      {
        DescAttrDef descDef = (DescAttrDef)descAttrList.get(j);

        IField descField = FieldFactory.getInstance().createField(
          descDef);
        this.fieldsList.add(descField);
      }
      for (int j = 0; j < charaAttrList.size(); j++)
      {
        RMetaChara rMetaChara = (RMetaChara)charaAttrList.get(j);
        CharacterDef charaDef = this.metaService.getCharaDefById(rMetaChara.getCharaId());
        IField charaField = FieldFactory.getInstance().createField(charaDef);
        ((CharacterField)charaField).initInputHtml(this.metaService.listCharaData(charaDef.getCharaId()));
        this.fieldsList.add(charaField);
      }
      for (int j = 0; j < hierAttrList.size(); j++)
      {
        hierDef =
          (HierarchyAttrDef)hierAttrList.get(j);

        IField hierField = FieldFactory.getInstance().createField(
          hierDef);
        this.fieldsList.add(hierField);
      }
    }
    /*
    if (this.rMetaName != null) {
      this.rOutHtml =
        ("<div id=\"关联属性_display\">  <a id=\"link_" + this.rMetaId + "_" + this.rEntityId +
        		"\" href=\"/app/tool/viewEntity.ac?metaId=" + this.rMetaId + "&entityId=" + this.rEntityId
        		+ "\">" + "<br>" + this.rMetaName + "：" + this.rEntityLabel + "</a>  "
        		+ "<img id=\"del_icon_" + this.rMetaId + "_" + this.rEntityId
        		+ "\" onclick=\"removeEntity('" + this.rMetaId + "','" + this.rEntityId
        		+ "','关联属性_display','relation')\" src=\"/app/img/del-icon.png\"></div>"
        		+ "<input value=\"" + this.rMetaId + "_" + this.rEntityId + "_" + this.rMetaName
        		+ "：" + this.rEntityLabel + "\" name=\"relation\" id=\"relation\" type=\"hidden\">"
        		+ "<img src=\"/app/img/add-icon.png\" alt=\"find\" onclick=\"selectEntity('通讯录','关联属性_display','relation')\" width=\"16\" height=\"16\">");
       */
    if (this.toRMetaName != null) {
    	this.rOutHtml =
        ("<div id=\"关联属性_display\">  <a id=\"link_" + this.toRMetaId + "_" + this.toREntityId
        	+ "\" href=\"/app/tool/viewEntity.ac?metaId=" + this.toRMetaId + "&entityId=" + this.toREntityId
			+ "\">" + "<br>" + this.toRMetaName + "：" + this.toRLabel + "</a>  "
			+ "<img id=\"del_icon_" + this.toRMetaId + "_" + this.toREntityId
			+ "\" onclick=\"removeEntity('" + this.toRMetaId + "','" + this.toREntityId
			+ "','关联属性_display','relation')\" src=\"/app/img/del-icon.png\"></div>"
			+ "<input value=\"" + this.toRMetaId + "_" + this.toREntityId + "_" + this.toRMetaName
			+ "：" + this.toRLabel + "\" name=\"relation\" id=\"relation\" type=\"hidden\">"
			+ "<img src=\"/app/img/add-icon.png\" alt=\"find\" onclick=\"selectEntity('"+ SysConfig.DefaultEntityName+"','关联属性_display','relation')\" width=\"16\" height=\"16\">");
    } else {
      this.rOutHtml = "<div id=\"关联属性_display\"> </div><input type=\"hidden\" value=\"\" name=\"relation\" id=\"relation\"/> <img width=\"16\" height=\"16\" src=\"/app/img/add-icon.png\" alt=\"find\" onclick=\"selectEntity('"+ SysConfig.DefaultEntityName+"','关联属性_display','relation')\">";
    }
    for (Object fieldObj : this.fieldsList)
    {
      Field field = (Field)fieldObj;
      this.fieldsMap.put(field.getFieldId(), field.getInputHtml());
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

  public Integer getMetaId()
  {
    return this.metaId;
  }

  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
  }

  public String getRMetaId()
  {
    return this.rMetaId;
  }

  public void setRMetaId(String metaId)
  {
    this.rMetaId = metaId;
  }

  public String getRMetaName()
  {
    return this.rMetaName;
  }

  public void setRMetaName(String metaName)
  {
    this.rMetaName = metaName;
  }

  public String getREntityId()
  {
    return this.rEntityId;
  }

  public void setREntityId(String entityId)
  {
    this.rEntityId = entityId;
  }

  public String getREntityLabel()
  {
    return this.rEntityLabel;
  }

  public void setREntityLabel(String entityLabel)
  {
    this.rEntityLabel = entityLabel;
  }

  public String getROutHtml()
  {
    return this.rOutHtml;
  }

  public void setROutHtml(String outHtml)
  {
    this.rOutHtml = outHtml;
  }

  public Map getFieldsMap()
  {
    return this.fieldsMap;
  }

  public void setFieldsMap(Map fieldsMap)
  {
    this.fieldsMap = fieldsMap;
  }
}
