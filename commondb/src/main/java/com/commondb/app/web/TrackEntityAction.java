package com.commondb.app.web;

import com.commondb.app.common.meta.DescField;
import com.commondb.app.common.meta.FieldFactory;
import com.commondb.app.common.meta.IField;
import com.commondb.db.bo.CharacterDef;
import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.RMetaChara;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;
import com.rits.cloning.Cloner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.ServletRequestAware;

public class TrackEntityAction
  extends ViewEntityAction
  implements Preparable, ServletRequestAware
{
  List favorMetaList = new ArrayList();
  String updateFlag;

  public List getFavorMetaList()
  {
    return this.favorMetaList;
  }

  public void setFavorMetaList(List favorMetaList)
  {
    this.favorMetaList = favorMetaList;
  }

  public String getUpdateFlag()
  {
    return this.updateFlag;
  }

  public void setUpdateFlag(String updateFlag)
  {
    this.updateFlag = updateFlag;
  }

  public String trackEntity()
  {
    trackEntityBasic();

    return "success";
  }

  public String relationEntity()
  {
    super.viewEntity();

    String[] rEntityName = super.getREntityNames().split("-");
    List metaNameList = new ArrayList();
    for (int i = 0; i < rEntityName.length; i++) {
      metaNameList.add(rEntityName[i]);
    }
    getRelationTrackRecord(metaNameList, "2");


    return "success";
  }

  public void trackEntityBasic()
  {
    super.viewEntity();
    if ((super.getREntityNames() != null) && (super.getREntityNames() != ""))
    {
      String[] rEntityName = super.getREntityNames().split("-");
      List metaNameList = new ArrayList();
      for (int i = 0; i < rEntityName.length; i++) {
        metaNameList.add(rEntityName[i]);
      }
      getRelationTrackRecord(metaNameList, "1");
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
public void getRelationTrackRecord(List rMetaNameList, String queryType)
  {
    for (Object moName : rMetaNameList)
    {
      Integer rMetaId = super.getMetaService().findMetaByName(moName.toString()).getId();


      List descAttrList = super.getMetaService().findDescAttrDef(rMetaId);

      List metaCharaList = super.getMetaService().listRMetaChara(rMetaId);


      List charaAttrList = new ArrayList();
      for (Object rm : metaCharaList)
      {
        CharacterDef charaDef = super.getMetaService()
          .getCharaDefById(((RMetaChara)rm).getCharaId());
        charaAttrList.add(charaDef);
      }
      List fieldsList = new ArrayList();
      IField descField;
      for (int j = 0; j < descAttrList.size(); j++)
      {
        DescAttrDef descDef = (DescAttrDef)descAttrList.get(j);

        descField = FieldFactory.getInstance().createField(
          descDef);
        fieldsList.add(descField);
      }
      String columnsString = "";
      if (fieldsList.size() > 0) {
        for (Object f : fieldsList)
        {
          IField field = (IField)f;
          if ((f instanceof DescField)) {
            columnsString = columnsString + ", t_entity_" + rMetaId + "." + field.getColumnName() + " ";
          }
        }
      }
      columnsString = "t_entity_" + rMetaId + ".id " + columnsString;
      columnsString = "t_entity_" + rMetaId + ".update_user, " + columnsString;
      columnsString = "t_entity_" + rMetaId + ".update_time, " + columnsString;

      columnsString = " distinct " + columnsString;


      String fromStr = "";
      StringBuffer whereStr = new StringBuffer("");
      if (queryType.equals("1"))
      {
        fromStr =





          " t_entity_" + rMetaId + " left join r_entity_hierarchy_data h on (t_entity_" + rMetaId + ".id = h.entity_id) " + "left join r_entity_chara_data c on ( t_entity_" + rMetaId + ".id=c.entity_id) " + "left join r_entity d on (t_entity_" + rMetaId + ".id = d.entity1_id) ";

        whereStr.append(" d.meta2_id=" + this.metaId +
          " and d.entity2_id='" + this.entityId + "'");
      }
      else
      {
        fromStr =





          " t_entity_" + rMetaId + " left join r_entity_hierarchy_data h on (t_entity_" + rMetaId + ".id = h.entity_id) " + "left join r_entity_chara_data c on ( t_entity_" + rMetaId + ".id=c.entity_id) " + "left join r_entity d on (t_entity_" + rMetaId + ".id = d.entity2_id) ";

        whereStr.append(" d.meta1_id=" + this.metaId +
          " and d.entity1_id='" + this.entityId + "'");
      }
      whereStr.append(" order by t_entity_" + rMetaId + ".create_time ");


      List result = super.getEntityService().dynSelect(columnsString, fromStr, whereStr.toString());
      List resList = new ArrayList(result.size());
      Cloner cloner = new Cloner();
      for (int i = 0; i < result.size(); i++)
      {
        List row = (List)cloner.deepClone(fieldsList);


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
        List rEntityList = super.getEntityService().listREntity(rMetaId, (String)((Map)result.get(i)).get("id"));
        row.add(2, rEntityList);
        resList.add(row);
      }
      Map favorRow = new HashMap();
      favorRow.put("fieldsList", fieldsList);
      favorRow.put("resList", resList);
      favorRow.put("metaName", moName.toString());

      this.favorMetaList.add(favorRow);
    }
  }
}
