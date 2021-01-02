package com.commondb.app.web;

import com.commondb.app.common.SysConfig;
import com.commondb.app.common.meta.CharacterField;
import com.commondb.app.common.meta.DescField;
import com.commondb.app.common.meta.FieldFactory;
import com.commondb.app.common.meta.HierarchyField;
import com.commondb.app.common.meta.IField;
import com.commondb.common.PageInfo;
import com.commondb.db.bo.CharacterAttrDef;
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
import com.rits.cloning.Cloner;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

public class SearchPageGenAction
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
  private String export;
  private String restrict;
  private String disType;
  List picAttrDef = new ArrayList();
  List descAttrDef = new ArrayList();
  List hierConditions = new ArrayList();
  List charaConditions = new ArrayList();
  Integer metaId;
  private User user;
  private Map m;
  private Meta meta;
  private List fieldsList;
  private List resList;
  private List metaList;
  private PageInfo pageInfo = new PageInfo();
  private String orderBy;
  private String desc;
  private MetaService metaService;
  private EntityService entityService;
  private String entityIdExport;
  private String updateTime;
  private String updateUser;

  public String genSearchPage()
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
    this.hierConditions = new ArrayList();
    if (this.hconditions != null)
    {
      String[] fieldsNameArray = this.hconditions.split("|");
      for (int i = 0; i < fieldsNameArray.length; i++)
      {
        boolean found = false;
        String fieldName = fieldsNameArray[i];
        if (!found) {
          for (int j = 0; j < hierAttrList.size(); j++)
          {
            HierarchyAttrDef hDef = (HierarchyAttrDef)hierAttrList.get(j);
            if (fieldName.equals(hDef.getAttrName()))
            {
              IField hField = FieldFactory.getInstance().createField(hDef);
              found = true;
              this.hierConditions.add(hField);
              break;
            }
          }
        }
      }
    }
    else
    {
      for (int j = 0; j < hierAttrList.size(); j++)
      {
        HierarchyAttrDef hDef = (HierarchyAttrDef)hierAttrList.get(j);
        IField hField = FieldFactory.getInstance().createField(hDef);
        this.hierConditions.add(hField);
      }
    }
    this.charaConditions = new ArrayList();
    if (this.cconditions != null)
    {
      String[] fieldsNameArray = this.cconditions.split("|");
      for (int i = 0; i < fieldsNameArray.length; i++)
      {
        boolean found = false;
        String fieldName = fieldsNameArray[i];
        if (!found) {
          for (int j = 0; j < charaAttrList.size(); j++)
          {
            CharacterAttrDef cDef = (CharacterAttrDef)hierAttrList.get(j);
            if (fieldName.equals(cDef.getAttrName()))
            {
              IField cField = FieldFactory.getInstance().createField(cDef);
              found = true;
              this.charaConditions.add(cField);
              break;
            }
          }
        }
      }
    }
    else
    {
      for (int j = 0; j < charaAttrList.size(); j++)
      {
        CharacterDef cDef = (CharacterDef)charaAttrList.get(j);
        IField cField = FieldFactory.getInstance().createField(cDef);
        this.charaConditions.add(cField);
      }
    }
    this.fieldsList = new ArrayList();
    boolean found;
    String fieldName;
    HierarchyAttrDef hierDef;
    if (this.fieldsName != null)
    {
      String[] fieldsNameArray = this.fieldsName.split("|");
      for (int i = 0; i < fieldsNameArray.length; i++)
      {
        found = false;
        fieldName = fieldsNameArray[i];
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
            CharacterAttrDef charaDef = (CharacterAttrDef)charaAttrList.get(j);
            if (fieldName.equals(charaDef.getAttrName()))
            {
              IField charaField = FieldFactory.getInstance().createField(charaDef);
              found = true;
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

        //found = FieldFactory.getInstance().createField(picDef);
        FieldFactory.getInstance().createField(picDef);
      }
      IField descField;
      for (int j = 0; j < descAttrList.size(); j++)
      {
        DescAttrDef descDef = (DescAttrDef)descAttrList.get(j);

        descField = FieldFactory.getInstance().createField(
          descDef);
        this.fieldsList.add(descField);
        this.descAttrDef.add(descField);
      }
      for (int j = 0; j < charaAttrList.size(); j++)
      {
        CharacterDef charaDef =
          (CharacterDef)charaAttrList.get(j);

        descField = FieldFactory.getInstance().createField(
          charaDef);
      }
      for (int j = 0; j < hierAttrList.size(); j++)
      {
        hierDef =
          (HierarchyAttrDef)hierAttrList.get(j);

        descField = FieldFactory.getInstance().createField(
          hierDef);
      }
    }
    Enumeration<String> keys = this.request.getParameterNames();
    //Object field;
    while (keys.hasMoreElements())
    {
      String key = (String)keys.nextElement();
      if (key.startsWith("s_"))
      {
        key = key.replaceFirst("s_", "");
        if (key.startsWith("c_")) {
          for (Object field : this.charaConditions)
          {
            CharacterField cField = (CharacterField)field;
            if (key.equals(cField.getColumnName())) {
              cField.setValue(this.request.getParameter("s_" + key));
            }
          }
        }
        if (key.startsWith("h_")) {
          for (Object field : this.hierConditions)
          {
            HierarchyField hField = (HierarchyField)field;
            if (key.equals(hField.getColumnName())) {
              hField.setValue(this.request.getParameter("s_" + key));
            }
          }
        }
        if (key.startsWith("d_")) {
          for (Iterator fieldNameI = this.fieldsList.iterator(); fieldNameI.hasNext();)
          {
            Object field = fieldNameI.next();

            DescField dField = (DescField)field;
            if (key.equals(dField.getColumnName())) {
              dField.setValue(this.request.getParameter("s_" + key));
            }
          }
        }
      }
    }
    for (Object field : this.hierConditions) {
      this.metaService.initHierFieldDisplayValue((HierarchyField)field);
    }
    CharacterField cField;
    for (Object field : this.charaConditions)
    {
      cField = (CharacterField)field;
      cField.initInputHtml(this.metaService.listCharaData(new Integer(cField.getColumnName().replaceFirst("c_", ""))));
      this.metaService.initCharaDisplayvalue((CharacterField)field);
    }
    String columnsString = "";
    if (this.descAttrDef.size() > 0) {
      for (Object f : this.descAttrDef)
      {
        IField field = (IField)f;
        if ((f instanceof DescField)) {
          columnsString = columnsString + ", t_entity_" + this.metaId + "." + field.getColumnName() + " ";
        }
      }
    }
    columnsString = "t_entity_" + this.metaId + ".id " + columnsString;
    columnsString = "t_entity_" + this.metaId + ".update_user, " + columnsString;
    columnsString = "t_entity_" + this.metaId + ".update_time, " + columnsString;

    columnsString = " distinct " + columnsString;
    String fromStr = " t_entity_" + this.metaId + " left join r_entity_hierarchy_data h on (t_entity_" + this.metaId + ".id = h.entity_id) " +
      "left join r_entity_chara_data c on ( t_entity_" + this.metaId + ".id=c.entity_id) ";
    //StringBuffer whereStr = new StringBuffer(" (3 > 1) ");
    StringBuffer whereStr = new StringBuffer(" ");
    boolean findFirst = false;
    for (Object field : this.descAttrDef)
    {
      DescField df = (DescField)field;
      if ((df.getValue() != null) && (!df.getValue().equals(""))) {
		  //whereStr.append(" " + this.conditionConnector + df.getSqlCondition());
    	  if (findFirst) {
    		  whereStr.append(" " + this.conditionConnector );
    	  } else
    	  {
    		  findFirst = true;
    	  }
		  whereStr.append(df.getSqlCondition());

      }
    }
    for (Object field : this.charaConditions)
    {
      CharacterField cf = (CharacterField)field;
      if ((cf.getValue() != null) && (!cf.getValue().equals(""))) {
	      //whereStr.append(" " + this.conditionConnector + " (c.meta_id=" + this.metaId + " and " + cf.getSqlCondition() + ")");
    	  if (findFirst) {
    		  whereStr.append(" " + this.conditionConnector );
    	  } else
    	  {
    		  findFirst = true;
    	  }
	      whereStr.append( " (c.meta_id=" + this.metaId + " and " + cf.getSqlCondition() + ")");
      }
    }
    for (Object field : this.hierConditions)
    {
      HierarchyField hf = (HierarchyField)field;
      if ((hf.getValue() != null) && (!hf.getValue().equals(""))) {
	      //whereStr.append(" " + this.conditionConnector + " (h.meta_id=" + this.metaId + " and " + hf.getSqlCondition() + ")");
    	  if (findFirst) {
    		  whereStr.append(" " + this.conditionConnector );
    	  } else
    	  {
    		  findFirst = true;
    	  }
	      whereStr.append(" (h.meta_id=" + this.metaId + " and " + hf.getSqlCondition() + ")");
      }
    }
    if ((this.updateTime != null) && (!this.updateTime.equals(""))) {
	  //whereStr.append(" " + this.conditionConnector + " update_time like '%" + this.updateTime + "%'");
  	  if (findFirst) {
		  whereStr.append(" " + this.conditionConnector );
	  } else
	  {
		  findFirst = true;
	  }
	  whereStr.append(" update_time like '%" + this.updateTime + "%'");
    }
    if ((this.updateUser != null) && (!this.updateUser.equals(""))) {
      //whereStr.append(" " + this.conditionConnector + " update_user like '%" + this.updateUser + "%'");
  	  if (findFirst) {
		  whereStr.append(" " + this.conditionConnector );
	  } else
	  {
		  findFirst = true;
	  }
      whereStr.append(" update_user like '%" + this.updateUser + "%'");
    }
    if ((this.export != null) && (this.export.equals("1")) && (this.entityIdExport != null) && (!this.entityIdExport.equals("")))
    {
      String idString = "";
      String[] idArr = this.entityIdExport.split(",");
      for (int i = 0; i < idArr.length; i++) {
        idString = idString + ",'" + idArr[i].split("_")[1] + "'";
      }
      idString = idString.replaceFirst(",", "");

      //whereStr.append(" and t_entity_" + this.metaId + ".id in (" + idString + ")");
  	  if (findFirst) {
		  whereStr.append(" and " );
	  } else
	  {
		  findFirst = true;
	  }
      whereStr.append(" t_entity_" + this.metaId + ".id in (" + idString + ")");
    }

	  if (!findFirst) {
		  findFirst = true;
		  whereStr.append(" (3 > 1) ");
	  }

    List pageCountRes = this.entityService.dynSelect(" count( t_entity_" + this.metaId + ".id) as totalRows", fromStr, whereStr.toString());
    HashMap h = (HashMap)pageCountRes.get(0);


    this.pageInfo.setTotalRows(((Long)h.get("totalRows")).intValue());
    if ((this.orderBy != null) && (!this.orderBy.equals("")))
    {
      whereStr.append(" order by  CONVERT(t_entity_" + this.metaId + "." + this.orderBy + " USING GBK) ");
      if ((this.desc != null) && (this.desc.equals("1")))
      {
        whereStr.append(" asc");
      }
      else
      {
        this.desc = "0";
        whereStr.append(" desc");
      }
    }
    else
    {
      whereStr.append(" order by  CONVERT(t_entity_" + this.metaId + ".update_time USING GBK) desc ");
    }
    if ((this.export == null) || (this.export.equals("0"))) {
      whereStr.append(" limit " + this.pageInfo.getNumPerPage() + " offset " + this.pageInfo.getStartIndex());
    }
    List result = this.entityService.dynSelect(columnsString, fromStr, whereStr.toString());
    this.resList = new ArrayList(result.size());
    Cloner cloner = new Cloner();
    for (int i = 0; i < result.size(); i++)
    {
      List row = (List)cloner.deepClone(this.fieldsList);
      IField idField = FieldFactory.getInstance().createField(
        null);

      row.add(0, idField);
      for (int j = 0; j < row.size(); j++)
      {
        Map map = (Map)result.get(i);
        IField field = (IField)row.get(j);
        if (map.get(field.getColumnName()) != null) {
          field.setValue(map.get(field.getColumnName()).toString());
        }
      }
      row.add(0, ((Map)result.get(i)).get("update_time"));
      row.add(1, ((Map)result.get(i)).get("update_user"));
      this.resList.add(row);
    }
    return "success";
  }

  public String getDesc()
  {
    return this.desc;
  }

  public void setDesc(String desc)
  {
    this.desc = desc;
  }

  public List getHierConditions()
  {
    return this.hierConditions;
  }

  public void setHierConditions(List hierConditions)
  {
    this.hierConditions = hierConditions;
  }

  public List getCharaConditions()
  {
    return this.charaConditions;
  }

  public void setCharaConditions(List charaConditions)
  {
    this.charaConditions = charaConditions;
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

  public String getExport()
  {
    return this.export;
  }

  public void setExport(String export)
  {
    this.export = export;
  }

  public String getEntityIdExport()
  {
    return this.entityIdExport;
  }

  public void setEntityIdExport(String entityIdExport)
  {
    this.entityIdExport = entityIdExport;
  }

  public String getRestrict()
  {
    return this.restrict;
  }

  public void setRestrict(String restrict)
  {
    this.restrict = restrict;
  }

  public String getDisType()
  {
    return this.disType;
  }

  public void setDisType(String disType)
  {
    this.disType = disType;
  }

  public String getUpdateTime()
  {
    return this.updateTime;
  }

  public void setUpdateTime(String updateTime)
  {
    this.updateTime = updateTime;
  }

  public String getUpdateUser()
  {
    return this.updateUser;
  }

  public void setUpdateUser(String updateUser)
  {
    this.updateUser = updateUser;
  }

  public Integer getMetaId()
  {
    return this.metaId;
  }

  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
  }
}
