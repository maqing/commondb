package com.commondb.app.web.customerQuoteDetail;

import com.commondb.app.web.TrackEntityAction;
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

public class CusQuoteDetailTrackEntityAction
  extends TrackEntityAction
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
	String inqueryMetaName = "客户询价单";
	String inqueryDetailMetaName = "客户询价明细";
	String inqueryIDColumnName = "d_278" ;

    for (Object moName : rMetaNameList)
    {
      Integer rMetaId = super.getMetaService().findMetaByName(moName.toString()).getId();
      Integer inqueryMetaId = super.getMetaService().findMetaByName(inqueryMetaName).getId();
      Integer inqueryDetailMetaId = super.getMetaService().findMetaByName(inqueryDetailMetaName).getId();

      List descAttrList = super.getMetaService().findDescAttrDef(rMetaId);
      List inQueryDetailDescAttrList = super.getMetaService().findDescAttrDef(inqueryDetailMetaId);

      //拼接两个表字段输出
      List allFieldsList = new ArrayList();
      List inqueryDetailFieldsList = new ArrayList();
      IField descField;
      for (int j = 0; j < inQueryDetailDescAttrList.size(); j++)
      {
        DescAttrDef descDef = (DescAttrDef)inQueryDetailDescAttrList.get(j);

        descField = FieldFactory.getInstance().createField(
          descDef);
        inqueryDetailFieldsList.add(descField);
        allFieldsList.add(descField);
      }

      List fieldsList = new ArrayList();
      for (int j = 0; j < descAttrList.size(); j++)
      {
        DescAttrDef descDef = (DescAttrDef)descAttrList.get(j);

        descField = FieldFactory.getInstance().createField(
          descDef);
        fieldsList.add(descField);
        allFieldsList.add(descField);
      }



      String columnsString = "";
      if (inqueryDetailFieldsList.size() > 0) {
          for (Object f : inqueryDetailFieldsList)
          {
            IField field = (IField)f;
            if ((f instanceof DescField)) {
              columnsString = columnsString + ", t_entity_" + inqueryDetailMetaId + "." + field.getColumnName() + " ";
            }
          }
        }

      if (fieldsList.size() > 0) {
        for (Object f : fieldsList)
        {
          IField field = (IField)f;
          if ((f instanceof DescField)) {
            columnsString = columnsString + ", quote." + field.getColumnName() + " ";
          }
        }
      }
      columnsString = "quote.id " + columnsString;
      columnsString = "quote.update_user, " + columnsString;
      columnsString = "quote.update_time, " + columnsString;

      columnsString = " distinct " + columnsString;


      String fromStr = "";
      StringBuffer whereStr = new StringBuffer("");
      if (queryType.equals("1"))
      {
          fromStr =
                  " t_entity_" + inqueryDetailMetaId + " join r_entity d1 on "
                    + "(d1.meta1_id=" + inqueryDetailMetaId + " and t_entity_" + inqueryDetailMetaId + ".id = d1.entity1_id) "
                    + " join r_entity d2 on (d2.meta1_id=" + this.getMetaId()
                    + " and d2.entity1_id='" + this.getEntityId() + "' and d2.entity2_id=d1.entity2_id and d2.meta2_id=" +inqueryMetaId +") "
                    + " left join (select t_entity_" + rMetaId
                  	+".* from  t_entity_" + rMetaId + " join r_entity d on (t_entity_" + rMetaId + ".id = d.entity1_id) "
                  	+ " where d.meta2_id=" + this.getMetaId()
                  	+ " and d.entity2_id='" + this.getEntityId() + "'"
                  	+ " ) quote on (t_entity_" + inqueryDetailMetaId + ".id = quote." + inqueryIDColumnName +") ";
      }
      else
      {
          fromStr =
                  " t_entity_" + inqueryDetailMetaId + " join r_entity d1 on "
                    + "(d1.meta1_id=" + inqueryDetailMetaId + " and t_entity_" + inqueryDetailMetaId + ".id = d1.entity1_id) "
                    + " join r_entity d2 on (d2.meta1_id=" + this.getMetaId()
                    + " and d2.entity1_id='" + this.getEntityId() + "' and d2.entity2_id=d1.entity2_id and d2.meta2_id=" +inqueryMetaId +") "
                    + " left join (select t_entity_" + rMetaId
                  	+".* from  t_entity_" + rMetaId + " join r_entity d on (t_entity_" + rMetaId + ".id = d.entity2_id) "
                  	+ " where d.meta1_id=" + this.getMetaId()
                  	+ " and d.entity1_id='" + this.getEntityId() + "'"
                  	+ " ) quote on (t_entity_" + inqueryDetailMetaId + ".id = quote." + inqueryIDColumnName +") ";

      }
      whereStr.append(" 1=1  order by t_entity_" + inqueryDetailMetaId + ".create_time ");


      List result = super.getEntityService().dynSelect(columnsString, fromStr, whereStr.toString());
      List resList = new ArrayList(result.size());



      Cloner cloner = new Cloner();
      for (int i = 0; i < result.size(); i++)
      {
        List row = (List)cloner.deepClone(allFieldsList);


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
      favorRow.put("fieldsList", allFieldsList);
      favorRow.put("resList", resList);
      favorRow.put("metaName", moName.toString());

      this.favorMetaList.add(favorRow);
    }
  }
}
