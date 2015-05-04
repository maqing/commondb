package com.commondb.db.service.impl;

import com.commondb.app.common.FileUpload;
import com.commondb.db.bo.CharacterData;
import com.commondb.db.bo.CharacterDataCriteria;
import com.commondb.db.bo.CharacterDef;
import com.commondb.db.bo.CharacterDefCriteria;
import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.bo.DescAttrDefCriteria;
import com.commondb.db.bo.HierarchyAttrDef;
import com.commondb.db.bo.HierarchyAttrDefCriteria;
import com.commondb.db.bo.HierarchyAttrDefCriteria.Criteria;
import com.commondb.db.bo.HierarchyAttrValue;
import com.commondb.db.bo.HierarchyAttrValueCriteria;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.MetaCriteria;
import com.commondb.db.bo.OperLog;
import com.commondb.db.bo.OperLogCriteria;
import com.commondb.db.bo.Pic;
import com.commondb.db.bo.PicAttrDef;
import com.commondb.db.bo.PicAttrDefCriteria;
import com.commondb.db.bo.PicCriteria;
import com.commondb.db.bo.REntity;
import com.commondb.db.bo.REntityCharaData;
import com.commondb.db.bo.REntityCharaDataCriteria;
import com.commondb.db.bo.REntityCriteria;
import com.commondb.db.bo.REntityHierarchyData;
import com.commondb.db.bo.REntityHierarchyDataCriteria;
import com.commondb.db.bo.RMetaChara;
import com.commondb.db.bo.RMetaCharaCriteria;
import com.commondb.db.bo.Reminder;
import com.commondb.db.bo.ReminderCriteria;
import com.commondb.db.bo.User;
import com.commondb.db.dao.CharacterDataDAO;
import com.commondb.db.dao.CharacterDefDAO;
import com.commondb.db.dao.DescAttrDefDAO;
import com.commondb.db.dao.DynamicEntityDAO;
import com.commondb.db.dao.HierarchyAttrDefDAO;
import com.commondb.db.dao.HierarchyAttrValueDAO;
import com.commondb.db.dao.MetaDAO;
import com.commondb.db.dao.OperLogDAO;
import com.commondb.db.dao.PicAttrDefDAO;
import com.commondb.db.dao.PicDAO;
import com.commondb.db.dao.REntityCharaDataDAO;
import com.commondb.db.dao.REntityDAO;
import com.commondb.db.dao.REntityHierarchyDataDAO;
import com.commondb.db.dao.RMetaCharaDAO;
import com.commondb.db.dao.ReminderDAO;
import com.commondb.db.service.SyncService;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

public class SyncServiceImpl
  implements SyncService
{
  private String ftpServer;
  private String ftpUser;
  private String ftpPassword;
  private DynamicEntityDAO localDynamicEntityDAO;
  private DynamicEntityDAO remoteDynamicEntityDAO;
  private OperLogDAO operLogDAO;
  private PicAttrDefDAO picAttrDefDAO;
  private DescAttrDefDAO descAttrDefDAO;
  private PicAttrDefDAO localPicAttrDefDAO;
  private DescAttrDefDAO localDescAttrDefDAO;
  private CharacterDefDAO characterDefDAO;
  private CharacterDataDAO characterDataDAO;
  private CharacterDefDAO localCharacterDefDAO;
  private CharacterDataDAO localCharacterDataDAO;
  private RMetaCharaDAO localRmetaCharaDAO;
  private HierarchyAttrDefDAO hierarchyAttrDefDAO;
  private HierarchyAttrDefDAO localHierarchyAttrDefDAO;
  private HierarchyAttrValueDAO hierarchyAttrValueDAO;
  private HierarchyAttrValueDAO localHierarchyAttrValueDAO;
  private REntityCharaDataDAO rentityCharaDataDAO;
  private REntityHierarchyDataDAO rentityHierarchyDataDAO;
  private REntityDAO rentityDAO;
  private PicDAO picDAO;
  private REntityCharaDataDAO localRentityCharaDataDAO;
  private REntityHierarchyDataDAO localRentityHierarchyDataDAO;
  private REntityDAO localRentityDAO;
  private PicDAO localPicDAO;
  private MetaDAO metaDAO;
  private MetaDAO localMetaDAO;
  private RMetaCharaDAO rmetaCharaDAO;
  private ReminderDAO reminderDAO;
  private ReminderDAO localReminderDAO;

  public ReminderDAO getReminderDAO()
  {
    return this.reminderDAO;
  }

  public void setReminderDAO(ReminderDAO reminderDAO)
  {
    this.reminderDAO = reminderDAO;
  }

  public ReminderDAO getLocalReminderDAO()
  {
    return this.localReminderDAO;
  }

  public void setLocalReminderDAO(ReminderDAO localReminderDAO)
  {
    this.localReminderDAO = localReminderDAO;
  }

  public void pullData()
  {
    this.localDynamicEntityDAO.dynDML("delete from r_role_meta");

    this.localDescAttrDefDAO.deleteByExample(new DescAttrDefCriteria());
    this.localPicAttrDefDAO.deleteByExample(new PicAttrDefCriteria());

    this.localRentityCharaDataDAO.deleteByExample(new REntityCharaDataCriteria());
    this.localCharacterDataDAO.deleteByExample(new CharacterDataCriteria());
    this.localRmetaCharaDAO.deleteByExample(new RMetaCharaCriteria());
    this.localCharacterDefDAO.deleteByExample(new CharacterDefCriteria());

    this.localRentityHierarchyDataDAO.deleteByExample(new REntityHierarchyDataCriteria());
    this.localHierarchyAttrValueDAO.deleteByExample(new HierarchyAttrValueCriteria());
    this.localHierarchyAttrDefDAO.deleteByExample(new HierarchyAttrDefCriteria());

    this.localRentityDAO.deleteByExample(new REntityCriteria());

    this.localMetaDAO.deleteByExample(new MetaCriteria());
    List<Meta> metaList = this.metaDAO.selectByExample(new MetaCriteria());
    for (Meta meta : metaList) {
      this.localMetaDAO.insert(meta);
    }
    List<HierarchyAttrDef> hierDefList = this.hierarchyAttrDefDAO.selectByExample(new HierarchyAttrDefCriteria());
    for (HierarchyAttrDef hierDef : hierDefList) {
      this.localHierarchyAttrDefDAO.insert(hierDef);
    }
    List<HierarchyAttrValue> hierValueList = this.hierarchyAttrValueDAO.selectByExample(new HierarchyAttrValueCriteria());
    for (HierarchyAttrValue hierValue : hierValueList) {
      this.localHierarchyAttrValueDAO.insert(hierValue);
    }
    List<REntityHierarchyData> rentityHierList = this.rentityHierarchyDataDAO.selectByExample(new REntityHierarchyDataCriteria());
    for (REntityHierarchyData rentityHier : rentityHierList) {
      this.localRentityHierarchyDataDAO.insert(rentityHier);
    }
    List<CharacterDef> charaDefList = this.characterDefDAO.selectByExample(new CharacterDefCriteria());
    for (CharacterDef charadef : charaDefList) {
      this.localCharacterDefDAO.insert(charadef);
    }
    List<RMetaChara> rmetaCharaList = this.rmetaCharaDAO.selectByExample(new RMetaCharaCriteria());
    for (RMetaChara rmChara : rmetaCharaList) {
      this.localRmetaCharaDAO.insert(rmChara);
    }
    List<CharacterData> charaDataList = this.characterDataDAO.selectByExample(new CharacterDataCriteria());
    for (CharacterData characterData : charaDataList) {
      this.localCharacterDataDAO.insert(characterData);
    }
    List<REntityCharaData> rentityCharaList = this.rentityCharaDataDAO.selectByExample(new REntityCharaDataCriteria());
    for (REntityCharaData rentityCharaData : rentityCharaList) {
      this.localRentityCharaDataDAO.insert(rentityCharaData);
    }
    List<DescAttrDef> descDefList = this.descAttrDefDAO.selectByExampleWithoutBLOBs(new DescAttrDefCriteria());
    for (DescAttrDef descAttrDef : descDefList) {
      this.localDescAttrDefDAO.insert(descAttrDef);
    }
    List<PicAttrDef> picDefList = this.picAttrDefDAO.selectByExample(new PicAttrDefCriteria());
    for (PicAttrDef picAttrDef : picDefList) {
      this.localPicAttrDefDAO.insert(picAttrDef);
    }
    List createRes;
    Map res;
    for (Meta meta : metaList)
    {
      this.localDynamicEntityDAO.dynDML("drop table t_entity_" + meta.getId());
      createRes = this.remoteDynamicEntityDAO.dynSelect("show create table t_entity_" + meta.getId());
      res = (Map)createRes.get(0);
      String sql = (String)res.get("Create Table");
      this.localDynamicEntityDAO.dynDML(sql);

      List entityList = this.remoteDynamicEntityDAO.selectByMetaId(meta.getId());
      for (int i = 0; i < entityList.size(); i++) {
        this.localDynamicEntityDAO.createEntity(meta.getId(), (Map)entityList.get(i));
      }
      this.localDynamicEntityDAO.createEntityTrigger(meta.getMetaId().intValue());
    }
    List<REntity> rentiyList = this.rentityDAO.selectByExample(new REntityCriteria());
    for (REntity re : rentiyList) {
      this.localRentityDAO.insert(re);
    }
    this.localReminderDAO.deleteByExample(new ReminderCriteria());
    List<Reminder> reminderList = this.reminderDAO.selectByExample(new ReminderCriteria());
    for (Reminder rm : reminderList) {
      this.reminderDAO.insert(rm);
    }
    List timeList = this.localDynamicEntityDAO.dynSelect("select last_sync_time from t_sync where id=2");
    PicCriteria picCriteria = new PicCriteria();
    if (timeList.size() > 0)
    {
      Map m = (Map)timeList.get(0);
      PicCriteria.Criteria c = picCriteria.createCriteria();
      c.andCreateTimeGreaterThan((Date)m.get("last_sync_time"));
    }
    String dstPath = ServletActionContext.getServletContext()
      .getRealPath("");

    String info = "";
    List<Pic> picList = this.picDAO.selectByExample(picCriteria);
    for (Pic p : picList) {
      if (p.getPicUrl() != null)
      {
        try
        {
          FileUpload.download(this.ftpServer, this.ftpUser, this.ftpPassword, p.getPicUrl(), new File(dstPath + "/" + p.getPicUrl()));
        }
        catch (Exception e)
        {
          info = info + "下载文件" + p.getPicUrl() + "失败，请尝试手工从服务器拷贝<br/>";
        }
        if ((p.getPicUrl().toLowerCase().endsWith(".jpg")) || (p.getPicUrl().toLowerCase().endsWith(".gif")) ||
          (p.getPicUrl().toLowerCase().endsWith(".bmp")) || (p.getPicUrl().toLowerCase().endsWith(".png")) ||
          (p.getPicUrl().toLowerCase().endsWith(".jpeg"))) {
          try
          {
            FileUpload.download(this.ftpServer, this.ftpUser, this.ftpPassword, p.getPreviewUrl(), new File(dstPath + "/" + p.getPreviewUrl()));
          }
          catch (MalformedURLException localMalformedURLException) {}catch (IOException localIOException) {}
        }
      }
    }
    this.localDynamicEntityDAO.dynDML("update t_sync set last_sync_time=now() where id=2");
    this.operLogDAO.deleteByExample(new OperLogCriteria());
  }

  public String syncData()
  {
    List<OperLog> operLogList = this.operLogDAO.selectByExample(new OperLogCriteria());
    String sqlRemote = "";
    String sqlLocale = "";
    int operCount = operLogList.size();
    Map rowMap;
    REntityCharaDataCriteria recc;
    for (OperLog op : operLogList) {
      if (op.getOperType().equals("new_hier"))
      {
        sqlLocale = "select * from t_hierarchy_attr_value where value_id='" + op.getObjId() + "'";
        List l = this.localDynamicEntityDAO.dynSelect(sqlLocale);
        if (l.size() > 0)
        {
          Map row = (Map)l.get(0);
          sqlRemote = "insert into t_hierarchy_attr_value(value_id, meta_id,attr_id,content,parent_id,level_num) values('" +
            row.get("value_id") + "'," + row.get("meta_id") + "," + row.get("attr_id") + ",'" +
            row.get("content") + "','" + row.get("parent_id") + "'," + row.get("level_num") + ")";
          this.remoteDynamicEntityDAO.dynDML(sqlRemote);
        }
      }
      else if (op.getOperType().equals("del_hier"))
      {
        sqlRemote = "delete from t_hierarchy_attr_value where value_id='" + op.getObjId() + "'";
        this.remoteDynamicEntityDAO.dynDML(sqlRemote);
      }
      else if (op.getOperType().equals("update_hier"))
      {
        sqlRemote = "delete from t_hierarchy_attr_value where value_id='" + op.getObjId() + "'";
        this.remoteDynamicEntityDAO.dynDML(sqlRemote);
        sqlLocale = "select * from t_hierarchy_attr_value where value_id='" + op.getObjId() + "'";
        List l = this.localDynamicEntityDAO.dynSelect(sqlLocale);

        Map row = (Map)l.get(0);
        sqlRemote = "insert into t_hierarchy_attr_value(value_id, meta_id,attr_id,content,parent_id,level_num) values('" +
          row.get("value_id") + "'," + row.get("meta_id") + ",'" +
          row.get("content") + "','" + row.get("parent_id") + "'," + row.get("level_num") + ")";
        this.remoteDynamicEntityDAO.dynDML(sqlRemote);
      }
      else if (op.getOperType().startsWith("del_entity"))
      {
        this.remoteDynamicEntityDAO.deleteEntityByPK(new Integer(op.getOperType().replaceFirst("new_entity_t_entity_", "")), op.getObjId());
      }
      else
      {
        String tableName = "";
        if (op.getOperType().startsWith("update_entity")) {
          tableName = op.getOperType().replaceFirst("update_entity_", "");
        } else if (op.getOperType().startsWith("new_entity")) {
          tableName = op.getOperType().replaceFirst("new_entity_", "");
        }
        sqlLocale = "select * from " + tableName + " where id='" + op.getObjId() + "'";

        List l = this.localDynamicEntityDAO.dynSelect(sqlLocale);
        if (l.size() > 0)
        {
          rowMap = (Map)l.get(0);
          Map valuesMap = rowMap;
          REntityHierarchyDataCriteria.Criteria rehc;
          REntityCharaDataCriteria criteria1;
          REntityCriteria.Criteria delREntity;
          if (op.getOperType().startsWith("update_entity"))
          {
            Integer metaId = new Integer(op.getOperType().replaceFirst("update_entity_", ""));
            this.remoteDynamicEntityDAO.updateEntity(metaId, op.getObjId(), rowMap);

            REntityHierarchyDataCriteria criteria = new REntityHierarchyDataCriteria();
            rehc = criteria.createCriteria();
            rehc.andEntityIdEqualTo(op.getObjId());
            this.rentityHierarchyDataDAO.deleteByExample(criteria);

            criteria1 = new REntityCharaDataCriteria();
            REntityCharaDataCriteria.Criteria delCriteria = criteria1.createCriteria();
            delCriteria.andEntityIdEqualTo(op.getObjId());
            this.rentityCharaDataDAO.deleteByExample(criteria1);

            REntityCriteria rentityCriter = new REntityCriteria();
            delREntity = rentityCriter.createCriteria();
            delREntity.andEntity1IdEqualTo(op.getObjId());
            this.rentityDAO.deleteByExample(rentityCriter);
          }
          else if (op.getOperType().startsWith("new_entity"))
          {
            Integer metaId = new Integer(op.getOperType().replaceFirst("new_entity_t_entity_", ""));
            this.remoteDynamicEntityDAO.createEntity(metaId, rowMap);
            Set<String> keys = valuesMap.keySet();
            for (Iterator<String> rcriteria1 = keys.iterator(); rcriteria1.hasNext(); rcriteria1.next()) {}
            recc = new REntityCharaDataCriteria();
            REntityCharaDataCriteria.Criteria criteria = recc.createCriteria();
            criteria.andEntityIdEqualTo(op.getObjId());
            List<REntityCharaData> recList = this.localRentityCharaDataDAO.selectByExample(recc);
            for (REntityCharaData rec : recList) {
              this.rentityCharaDataDAO.insert(rec);
            }
            REntityHierarchyDataCriteria rehd = new REntityHierarchyDataCriteria();
            REntityHierarchyDataCriteria.Criteria rehdc = rehd.createCriteria();
            rehdc.andEntityIdEqualTo(op.getObjId());
            List<REntityHierarchyData> rehdList = this.localRentityHierarchyDataDAO.selectByExample(rehd);
            for (REntityHierarchyData reh : rehdList) {
              this.rentityHierarchyDataDAO.insert(reh);
            }
            REntityCriteria rEnityCriteria = new REntityCriteria();
            REntityCriteria.Criteria c = rEnityCriteria.createCriteria();
            c.andEntity1IdEqualTo(op.getObjId());
            List<REntity> rentityList = this.localRentityDAO.selectByExample(rEnityCriteria);
            for (REntity re : rentityList) {
              this.rentityDAO.insert(re);
            }
            snapshotEntity(metaId, op.getObjId());
          }
        }
      }
    }
    ReminderCriteria reminderCriteria = new ReminderCriteria();

    List timeList = this.localDynamicEntityDAO.dynSelect("select last_sync_time from t_sync where id=1");
    if (timeList.size() > 0)
    {
      Map m = (Map)timeList.get(0);
      ReminderCriteria.Criteria remcCriteria = reminderCriteria.createCriteria();
      remcCriteria.andCreateTimeGreaterThan((Date)m.get("last_sync_time"));
    }
    List<Reminder> reminderList = this.localReminderDAO.selectByExample(reminderCriteria);
    for (Reminder rm : reminderList)
    {
      ReminderCriteria.Criteria remcCriteria = reminderCriteria.createCriteria();
      remcCriteria.andIdEqualTo(rm.getId());

      List l = this.reminderDAO.selectByExample(reminderCriteria);
      if (l.size() == 0) {
        this.reminderDAO.insert(rm);
      }
    }
    PicCriteria picCriteria = new PicCriteria();
    if (timeList.size() > 0)
    {
      Map m = (Map)timeList.get(0);
      PicCriteria.Criteria c = picCriteria.createCriteria();
      c.andCreateTimeGreaterThan((Date)m.get("opertime"));
    }
    String info = "";
    List<Pic> picList = this.localPicDAO.selectByExample(picCriteria);
    String dstPath = ServletActionContext.getServletContext()
      .getRealPath("");
    for (Pic p : picList) {
      if (p.getPicUrl() != null) {
        try
        {
          FileUpload.upload(this.ftpServer, this.ftpUser, this.ftpPassword, p.getPicUrl(), new File(dstPath + "/" + p.getPicUrl()));
          File previewFile = new File(dstPath + "/" + p.getPreviewUrl());
          if (previewFile.exists()) {
            FileUpload.upload(this.ftpServer, this.ftpUser, this.ftpPassword, p.getPreviewUrl(), new File(dstPath + "/" + p.getPreviewUrl()));
          }
        }
        catch (Exception e)
        {
          info = info + "上传文件" + p.getPicUrl() + "失败，请尝试手工上传到服务器<br/>";
        }
      }
    }
    this.localDynamicEntityDAO.dynDML("update t_sync set last_sync_time=now() where id=1");
    this.operLogDAO.deleteByExample(new OperLogCriteria());

    return "提交了" + operCount + "个操作 <br/>" + info;
  }

  private void snapshotEntity(Integer metaId, String entityId)
  {
    List l = this.remoteDynamicEntityDAO.dynSelect("select * from t_entity_" + metaId);
    if (l.size() == 0) {
      return;
    }
    Map valueMap = (Map)l.get(0);

    HierarchyAttrDefCriteria hierarchyCriteria = new HierarchyAttrDefCriteria();
    HierarchyAttrDefCriteria.Criteria c = hierarchyCriteria.createCriteria();
    c.andMetaIdEqualTo(metaId);
    List<HierarchyAttrDef> hdefList = this.hierarchyAttrDefDAO.selectByExample(hierarchyCriteria);
    List<REntityHierarchyData> hdataList;
    for (HierarchyAttrDef hefDef : hdefList)
    {
      REntityHierarchyDataCriteria erhCriteria = new REntityHierarchyDataCriteria();
      REntityHierarchyDataCriteria.Criteria erh = erhCriteria.createCriteria();
      erh.andAttrIdEqualTo(hefDef.getAttrId()).andEntityIdEqualTo(entityId);
      hdataList = this.rentityHierarchyDataDAO.selectByExample(erhCriteria);
      List hvalueList = new ArrayList();
      String hvalue = "";
      for (REntityHierarchyData reh : hdataList) {
        hvalue = hvalue + this.hierarchyAttrValueDAO.getHierPathString(hefDef.getAttrId(), reh.getValueId()) + "\n";
      }
      valueMap.put("h_" + hefDef.getAttrId(), hvalue);
    }
    RMetaCharaCriteria rmcCriteria = new RMetaCharaCriteria();
    RMetaCharaCriteria.Criteria rmc = rmcCriteria.createCriteria();
    rmc.andMetaIdEqualTo(metaId);

    List<RMetaChara> remList = this.rmetaCharaDAO.selectByExample(rmcCriteria);
    for (RMetaChara rmc1 : remList)
    {
      REntityCharaDataCriteria recDataCriteria = new REntityCharaDataCriteria();
      REntityCharaDataCriteria.Criteria recc = recDataCriteria.createCriteria();
      recc.andEntityIdEqualTo(entityId).andCharaIdEqualTo(rmc1.getCharaId());
      List<REntityCharaData> cvalueList = this.rentityCharaDataDAO.selectByExample(recDataCriteria);
      String cvalue = "";
      for (REntityCharaData rec : cvalueList) {
        cvalue = cvalue + " " + this.characterDataDAO.selectByPrimaryKey(rec.getDataId());
      }
      valueMap.put("c_" + rmc1.getCharaId(), cvalue);
    }
    REntityCriteria rnCriteria = new REntityCriteria();
    REntityCriteria.Criteria rcn = rnCriteria.createCriteria();
    rcn.andEntity1IdEqualTo(entityId);
    List<REntity> renList = this.rentityDAO.selectByExample(rnCriteria);
    String renValue = "";
    for (Object cvalue = renList.iterator(); ((Iterator)cvalue).hasNext();)
    {
      REntity ren = (REntity)((Iterator)cvalue).next();

      renValue = renValue + "," + ren.getLabel() + "_" + ren.getMeta2Id() + ren.getEntity2Id();
    }
    renValue.replaceFirst(",", "");
    User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  public DynamicEntityDAO getLocalDynamicEntityDAO()
  {
    return this.localDynamicEntityDAO;
  }

  public void setLocalDynamicEntityDAO(DynamicEntityDAO localDynamicEntityDAO)
  {
    this.localDynamicEntityDAO = localDynamicEntityDAO;
  }

  public DynamicEntityDAO getRemoteDynamicEntityDAO()
  {
    return this.remoteDynamicEntityDAO;
  }

  public void setRemoteDynamicEntityDAO(DynamicEntityDAO remoteDynamicEntityDAO)
  {
    this.remoteDynamicEntityDAO = remoteDynamicEntityDAO;
  }

  public OperLogDAO getOperLogDAO()
  {
    return this.operLogDAO;
  }

  public void setOperLogDAO(OperLogDAO operLogDAO)
  {
    this.operLogDAO = operLogDAO;
  }

  public PicAttrDefDAO getPicAttrDefDAO()
  {
    return this.picAttrDefDAO;
  }

  public void setPicAttrDefDAO(PicAttrDefDAO picAttrDefDAO)
  {
    this.picAttrDefDAO = picAttrDefDAO;
  }

  public DescAttrDefDAO getDescAttrDefDAO()
  {
    return this.descAttrDefDAO;
  }

  public void setDescAttrDefDAO(DescAttrDefDAO descAttrDefDAO)
  {
    this.descAttrDefDAO = descAttrDefDAO;
  }

  public PicAttrDefDAO getLocalPicAttrDefDAO()
  {
    return this.localPicAttrDefDAO;
  }

  public void setLocalPicAttrDefDAO(PicAttrDefDAO localPicAttrDefDAO)
  {
    this.localPicAttrDefDAO = localPicAttrDefDAO;
  }

  public DescAttrDefDAO getLocalDescAttrDefDAO()
  {
    return this.localDescAttrDefDAO;
  }

  public void setLocalDescAttrDefDAO(DescAttrDefDAO localDescAttrDefDAO)
  {
    this.localDescAttrDefDAO = localDescAttrDefDAO;
  }

  public CharacterDefDAO getCharacterDefDAO()
  {
    return this.characterDefDAO;
  }

  public void setCharacterDefDAO(CharacterDefDAO characterDefDAO)
  {
    this.characterDefDAO = characterDefDAO;
  }

  public CharacterDataDAO getCharacterDataDAO()
  {
    return this.characterDataDAO;
  }

  public void setCharacterDataDAO(CharacterDataDAO characterDataDAO)
  {
    this.characterDataDAO = characterDataDAO;
  }

  public CharacterDefDAO getLocalCharacterDefDAO()
  {
    return this.localCharacterDefDAO;
  }

  public void setLocalCharacterDefDAO(CharacterDefDAO localCharacterDefDAO)
  {
    this.localCharacterDefDAO = localCharacterDefDAO;
  }

  public CharacterDataDAO getLocalCharacterDataDAO()
  {
    return this.localCharacterDataDAO;
  }

  public void setLocalCharacterDataDAO(CharacterDataDAO localCharacterDataDAO)
  {
    this.localCharacterDataDAO = localCharacterDataDAO;
  }

  public RMetaCharaDAO getLocalRmetaCharaDAO()
  {
    return this.localRmetaCharaDAO;
  }

  public void setLocalRmetaCharaDAO(RMetaCharaDAO localRmetaCharaDAO)
  {
    this.localRmetaCharaDAO = localRmetaCharaDAO;
  }

  public HierarchyAttrDefDAO getHierarchyAttrDefDAO()
  {
    return this.hierarchyAttrDefDAO;
  }

  public void setHierarchyAttrDefDAO(HierarchyAttrDefDAO hierarchyAttrDefDAO)
  {
    this.hierarchyAttrDefDAO = hierarchyAttrDefDAO;
  }

  public HierarchyAttrDefDAO getLocalHierarchyAttrDefDAO()
  {
    return this.localHierarchyAttrDefDAO;
  }

  public void setLocalHierarchyAttrDefDAO(HierarchyAttrDefDAO localHierarchyAttrDefDAO)
  {
    this.localHierarchyAttrDefDAO = localHierarchyAttrDefDAO;
  }

  public HierarchyAttrValueDAO getHierarchyAttrValueDAO()
  {
    return this.hierarchyAttrValueDAO;
  }

  public void setHierarchyAttrValueDAO(HierarchyAttrValueDAO hierarchyAttrValueDAO)
  {
    this.hierarchyAttrValueDAO = hierarchyAttrValueDAO;
  }

  public HierarchyAttrValueDAO getLocalHierarchyAttrValueDAO()
  {
    return this.localHierarchyAttrValueDAO;
  }

  public void setLocalHierarchyAttrValueDAO(HierarchyAttrValueDAO localHierarchyAttrValueDAO)
  {
    this.localHierarchyAttrValueDAO = localHierarchyAttrValueDAO;
  }

  public REntityCharaDataDAO getRentityCharaDataDAO()
  {
    return this.rentityCharaDataDAO;
  }

  public void setRentityCharaDataDAO(REntityCharaDataDAO rentityCharaDataDAO)
  {
    this.rentityCharaDataDAO = rentityCharaDataDAO;
  }

  public REntityHierarchyDataDAO getRentityHierarchyDataDAO()
  {
    return this.rentityHierarchyDataDAO;
  }

  public void setRentityHierarchyDataDAO(REntityHierarchyDataDAO rentityHierarchyDataDAO)
  {
    this.rentityHierarchyDataDAO = rentityHierarchyDataDAO;
  }

  public REntityDAO getRentityDAO()
  {
    return this.rentityDAO;
  }

  public void setRentityDAO(REntityDAO rentityDAO)
  {
    this.rentityDAO = rentityDAO;
  }

  public PicDAO getPicDAO()
  {
    return this.picDAO;
  }

  public void setPicDAO(PicDAO picDAO)
  {
    this.picDAO = picDAO;
  }

  public REntityCharaDataDAO getLocalRentityCharaDataDAO()
  {
    return this.localRentityCharaDataDAO;
  }

  public void setLocalRentityCharaDataDAO(REntityCharaDataDAO localRentityCharaDataDAO)
  {
    this.localRentityCharaDataDAO = localRentityCharaDataDAO;
  }

  public REntityHierarchyDataDAO getLocalRentityHierarchyDataDAO()
  {
    return this.localRentityHierarchyDataDAO;
  }

  public void setLocalRentityHierarchyDataDAO(REntityHierarchyDataDAO localRentityHierarchyDataDAO)
  {
    this.localRentityHierarchyDataDAO = localRentityHierarchyDataDAO;
  }

  public REntityDAO getLocalRentityDAO()
  {
    return this.localRentityDAO;
  }

  public void setLocalRentityDAO(REntityDAO localRentityDAO)
  {
    this.localRentityDAO = localRentityDAO;
  }

  public PicDAO getLocalPicDAO()
  {
    return this.localPicDAO;
  }

  public void setLocalPicDAO(PicDAO localPicDAO)
  {
    this.localPicDAO = localPicDAO;
  }

  public MetaDAO getMetaDAO()
  {
    return this.metaDAO;
  }

  public void setMetaDAO(MetaDAO metaDAO)
  {
    this.metaDAO = metaDAO;
  }

  public MetaDAO getLocalMetaDAO()
  {
    return this.localMetaDAO;
  }

  public void setLocalMetaDAO(MetaDAO localMetaDAO)
  {
    this.localMetaDAO = localMetaDAO;
  }

  public RMetaCharaDAO getRmetaCharaDAO()
  {
    return this.rmetaCharaDAO;
  }

  public void setRmetaCharaDAO(RMetaCharaDAO rmetaCharaDAO)
  {
    this.rmetaCharaDAO = rmetaCharaDAO;
  }

  public String getFtpServer()
  {
    return this.ftpServer;
  }

  public void setFtpServer(String ftpServer)
  {
    this.ftpServer = ftpServer;
  }

  public String getFtpUser()
  {
    return this.ftpUser;
  }

  public void setFtpUser(String ftpUser)
  {
    this.ftpUser = ftpUser;
  }

  public String getFtpPassword()
  {
    return this.ftpPassword;
  }

  public void setFtpPassword(String ftpPassword)
  {
    this.ftpPassword = ftpPassword;
  }
}
