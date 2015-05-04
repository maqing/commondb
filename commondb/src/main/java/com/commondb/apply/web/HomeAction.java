package com.commondb.apply.web;

import com.commondb.app.common.meta.DescField;
import com.commondb.app.common.meta.FieldFactory;
import com.commondb.app.common.meta.IField;
import com.commondb.apply.bo.MetaUser;
import com.commondb.apply.service.ApplyService;
import com.commondb.db.bo.CharacterDef;
import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.bo.RMetaChara;
import com.commondb.db.bo.User;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.rits.cloning.Cloner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

public class HomeAction
{
  EntityService entityService;
  List remindList;
  private ApplyService applyService;
  User user;
  MetaService metaService;
  List favorMetaList = new ArrayList();
  String defaultMenu;
  
  public String home()
  {
    User user = (User)SecurityContextHolder.getContext()
      .getAuthentication().getPrincipal();
    this.remindList = this.entityService.getUserRemind(user.getUserId());
    List<MetaUser> metauList = this.applyService.getMetaUserByUserId(
      user.getUserId());
    for (MetaUser mu : metauList)
    {
      Integer metaId = mu.getMetaId();
      
      List picAttrList = this.metaService.findPicAttrDef(metaId);
      List descAttrList = this.metaService.findDescAttrDef(metaId);
      List hierAttrList = this.metaService.findHierarchyAttrDef(metaId);
      List metaCharaList = this.metaService.listRMetaChara(metaId);
      
      List charaAttrList = new ArrayList();
      for (Object rm : metaCharaList)
      {
        CharacterDef charaDef = this.metaService
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
            columnsString = columnsString + ", t_entity_" + metaId + "." + field.getColumnName() + " ";
          }
        }
      }
      columnsString = "t_entity_" + metaId + ".id " + columnsString;
      columnsString = "t_entity_" + metaId + ".update_user, " + columnsString;
      columnsString = "t_entity_" + metaId + ".update_time, " + columnsString;
      
      columnsString = " distinct " + columnsString;
      


      String fromStr = " t_entity_" + metaId + 
        " left join r_entity_hierarchy_data h on (t_entity_" + 
        metaId + ".id = h.entity_id) " + 
        "left join r_entity_chara_data c on ( t_entity_" + metaId + 
        ".id=c.entity_id) ";
      StringBuffer whereStr = new StringBuffer(" (3 > 1) ");
      whereStr.append(" limit 20 offset 0");
      

      List result = this.entityService.dynSelect(columnsString, fromStr, whereStr.toString());
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
        resList.add(row);
      }
      Map favorRow = new HashMap();
      favorRow.put("fieldsList", fieldsList);
      favorRow.put("resList", resList);
      
      this.favorMetaList.add(favorRow);
    }
    return "success";
  }
  
  public EntityService getEntityService()
  {
    return this.entityService;
  }
  
  public void setEntityService(EntityService entityService)
  {
    this.entityService = entityService;
  }
  
  public List getRemindList()
  {
    return this.remindList;
  }
  
  public void setRemindList(List remindList)
  {
    this.remindList = remindList;
  }
  
  public ApplyService getApplyService()
  {
    return this.applyService;
  }
  
  public void setApplyService(ApplyService applyService)
  {
    this.applyService = applyService;
  }
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public String getDefaultMenu()
  {
    return this.defaultMenu;
  }
  
  public void setDefaultMenu(String defaultMenu)
  {
    this.defaultMenu = defaultMenu;
  }
  
  public MetaService getMetaService()
  {
    return this.metaService;
  }
  
  public void setMetaService(MetaService metaService)
  {
    this.metaService = metaService;
  }
  
  public List getFavorMetaList()
  {
    return this.favorMetaList;
  }
  
  public void setFavorMetaList(List favorMetaList)
  {
    this.favorMetaList = favorMetaList;
  }
}
