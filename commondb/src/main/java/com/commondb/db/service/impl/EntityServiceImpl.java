package com.commondb.db.service.impl;

import com.commondb.app.common.FileTool;
import com.commondb.db.bo.CharacterAttrData;
import com.commondb.db.bo.CharacterAttrDataCriteria;
import com.commondb.db.bo.CharacterAttrDataCriteria.Criteria;
import com.commondb.db.bo.CharacterData;
import com.commondb.db.bo.CharacterDataCriteria;
import com.commondb.db.bo.CharacterDef;
import com.commondb.db.bo.DescAttrData;
import com.commondb.db.bo.DescAttrDataCriteria;
import com.commondb.db.bo.Entity;
import com.commondb.db.bo.HierarchyAttrData;
import com.commondb.db.bo.HierarchyAttrDataCriteria;
import com.commondb.db.bo.HierarchyAttrDef;
import com.commondb.db.bo.HierarchyAttrDefCriteria;
import com.commondb.db.bo.HierarchyAttrValue;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.OperationBox;
import com.commondb.db.bo.OperationBoxCriteria;
import com.commondb.db.bo.Pic;
import com.commondb.db.bo.PicAttrData;
import com.commondb.db.bo.PicAttrDataCriteria;
import com.commondb.db.bo.PicCriteria;
import com.commondb.db.bo.REntity;
import com.commondb.db.bo.REntityAttachment;
import com.commondb.db.bo.REntityAttachmentCriteria;
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
import com.commondb.db.dao.CharacterAttrDataDAO;
import com.commondb.db.dao.CharacterAttrDefDAO;
import com.commondb.db.dao.CharacterAttrValueDAO;
import com.commondb.db.dao.CharacterDataDAO;
import com.commondb.db.dao.CharacterDefDAO;
import com.commondb.db.dao.DescAttrDataDAO;
import com.commondb.db.dao.DescAttrDefDAO;
import com.commondb.db.dao.DynamicEntityDAO;
import com.commondb.db.dao.EntityDAO;
import com.commondb.db.dao.HierarchyAttrDataDAO;
import com.commondb.db.dao.HierarchyAttrDefDAO;
import com.commondb.db.dao.HierarchyAttrValueDAO;
import com.commondb.db.dao.MetaDAO;
import com.commondb.db.dao.OperationBoxDAO;
import com.commondb.db.dao.PicAttrDataDAO;
import com.commondb.db.dao.PicAttrDefDAO;
import com.commondb.db.dao.PicDAO;
import com.commondb.db.dao.REntityAttachmentDAO;
import com.commondb.db.dao.REntityCharaDataDAO;
import com.commondb.db.dao.REntityDAO;
import com.commondb.db.dao.REntityHierarchyDataDAO;
import com.commondb.db.dao.RMetaCharaDAO;
import com.commondb.db.dao.ReminderDAO;
import com.commondb.db.service.EntityService;
import com.commondb.search.service.HttpSolrService;
import edu.emory.mathcs.backport.java.util.Arrays;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

public class EntityServiceImpl
  implements EntityService
{
  private EntityDAO entityDAO;
  private MetaDAO metaDAO;
  private CharacterAttrDefDAO characterAttrDefDAO;
  private HierarchyAttrDefDAO hierarchyAttrDefDAO;
  private PicAttrDefDAO picAttrDefDAO;
  private DescAttrDefDAO descAttrDefDAO;
  private CharacterAttrDataDAO characterAttrDataDAO;
  private HierarchyAttrDataDAO hierarchyAttrDataDAO;
  private PicAttrDataDAO picAttrDataDAO;
  private DescAttrDataDAO descAttrDataDAO;
  private CharacterAttrValueDAO characterAttrValueDAO;
  private DynamicEntityDAO dynEntityDAO;
  private PicDAO picDAO;
  private REntityDAO rentityDAO;
  private CharacterDefDAO characterDefDAO;
  private CharacterDataDAO characterDataDAO;
  private REntityCharaDataDAO rentityCharaDataDAO;
  private RMetaCharaDAO rmetaCharaDAO;
  private REntityHierarchyDataDAO rentityHierarchyDataDAO;
  private HierarchyAttrValueDAO hierarchyAttrValueDAO;
  private ReminderDAO reminderDAO;
  private OperationBoxDAO operationBoxDAO;
  private REntityAttachmentDAO rentityAttachmentDAO;
  private HttpSolrService httpSolrService;

  public ReminderDAO getReminderDAO()
  {
    return this.reminderDAO;
  }

  public void setReminderDAO(ReminderDAO reminderDAO)
  {
    this.reminderDAO = reminderDAO;
  }

  public HierarchyAttrValueDAO getHierarchyAttrValueDAO()
  {
    return this.hierarchyAttrValueDAO;
  }

  public void setHierarchyAttrValueDAO(HierarchyAttrValueDAO hierarchyAttrValueDAO)
  {
    this.hierarchyAttrValueDAO = hierarchyAttrValueDAO;
  }

  public REntityHierarchyDataDAO getRentityHierarchyDataDAO()
  {
    return this.rentityHierarchyDataDAO;
  }

  public void setRentityHierarchyDataDAO(REntityHierarchyDataDAO rentityHierarchyDataDAO)
  {
    this.rentityHierarchyDataDAO = rentityHierarchyDataDAO;
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

  public REntityCharaDataDAO getRentityCharaDataDAO()
  {
    return this.rentityCharaDataDAO;
  }

  public void setRentityCharaDataDAO(REntityCharaDataDAO rentityCharaDataDAO)
  {
    this.rentityCharaDataDAO = rentityCharaDataDAO;
  }

  public RMetaCharaDAO getRmetaCharaDAO()
  {
    return this.rmetaCharaDAO;
  }

  public void setRmetaCharaDAO(RMetaCharaDAO rmetaCharaDAO)
  {
    this.rmetaCharaDAO = rmetaCharaDAO;
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

  public DynamicEntityDAO getDynEntityDAO()
  {
    return this.dynEntityDAO;
  }

  public void setDynEntityDAO(DynamicEntityDAO dynEntityDAO)
  {
    this.dynEntityDAO = dynEntityDAO;
  }

  public CharacterAttrValueDAO getCharacterAttrValueDAO()
  {
    return this.characterAttrValueDAO;
  }

  public void setCharacterAttrValueDAO(CharacterAttrValueDAO characterAttrValueDAO)
  {
    this.characterAttrValueDAO = characterAttrValueDAO;
  }

  public CharacterAttrDataDAO getCharacterAttrDataDAO()
  {
    return this.characterAttrDataDAO;
  }

  public void setCharacterAttrDataDAO(CharacterAttrDataDAO characterAttrDataDAO)
  {
    this.characterAttrDataDAO = characterAttrDataDAO;
  }

  public HierarchyAttrDataDAO getHierarchyAttrDataDAO()
  {
    return this.hierarchyAttrDataDAO;
  }

  public void setHierarchyAttrDataDAO(HierarchyAttrDataDAO hierarchyAttrDataDAO)
  {
    this.hierarchyAttrDataDAO = hierarchyAttrDataDAO;
  }

  public PicAttrDataDAO getPicAttrDataDAO()
  {
    return this.picAttrDataDAO;
  }

  public void setPicAttrDataDAO(PicAttrDataDAO picAttrDataDAO)
  {
    this.picAttrDataDAO = picAttrDataDAO;
  }

  public DescAttrDataDAO getDescAttrDataDAO()
  {
    return this.descAttrDataDAO;
  }

  public void setDescAttrDataDAO(DescAttrDataDAO descAttrDataDAO)
  {
    this.descAttrDataDAO = descAttrDataDAO;
  }

  public EntityDAO getEntityDAO()
  {
    return this.entityDAO;
  }

  public void setEntityDAO(EntityDAO entityDAO)
  {
    this.entityDAO = entityDAO;
  }

  public MetaDAO getMetaDAO()
  {
    return this.metaDAO;
  }

  public void setMetaDAO(MetaDAO metaDAO)
  {
    this.metaDAO = metaDAO;
  }

  public CharacterAttrDefDAO getCharacterAttrDefDAO()
  {
    return this.characterAttrDefDAO;
  }

  public void setCharacterAttrDefDAO(CharacterAttrDefDAO characterAttrDefDAO)
  {
    this.characterAttrDefDAO = characterAttrDefDAO;
  }

  public HierarchyAttrDefDAO getHierarchyAttrDefDAO()
  {
    return this.hierarchyAttrDefDAO;
  }

  public void setHierarchyAttrDefDAO(HierarchyAttrDefDAO hierarchyAttrDefDAO)
  {
    this.hierarchyAttrDefDAO = hierarchyAttrDefDAO;
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

  public String addOperationRec(OperationBox operationRec)
  {
    User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    operationRec.setCreateUser(user.getUserId().toString());

    String id = "0";
    OperationBoxCriteria opec = new OperationBoxCriteria();
    OperationBoxCriteria.Criteria criteria = opec.createCriteria();
    criteria.andCreateUserEqualTo(operationRec.getCreateUser());
    criteria.andMetaIdEqualTo(operationRec.getMetaId());
    criteria.andEntityIdEqualTo(operationRec.getEntityId());
    if (this.operationBoxDAO.selectByExample(opec).size() == 0)
    {
      operationRec.setCreateTime(new Date());
      id = UUID.randomUUID().toString();
      operationRec.setId(id);
      this.operationBoxDAO.insert(operationRec);
    }
    return id;
  }

  public void delOperationRec(String operationRecId)
  {
    this.operationBoxDAO.deleteByPrimaryKey(operationRecId);
  }

  public List getUserOperationRec(Integer userId, String operationType)
  {
    OperationBoxCriteria opec = new OperationBoxCriteria();
    OperationBoxCriteria.Criteria criteria = opec.createCriteria();
    criteria.andCreateUserEqualTo(userId.toString());
    criteria.andOperationTypeEqualTo(operationType);
    return this.operationBoxDAO.selectByExample(opec);
  }

  public List getUserOperationRec(Integer userId)
  {
    OperationBoxCriteria opec = new OperationBoxCriteria();
    OperationBoxCriteria.Criteria criteria = opec.createCriteria();
    criteria.andCreateUserEqualTo(userId.toString());
    List operaRecList = this.operationBoxDAO.selectByExample(opec);
    if (operaRecList != null) {
      for (int i = 0; i < operaRecList.size(); i++)
      {
        OperationBox record = (OperationBox)operaRecList.get(i);
        if (record.getOperationType().equals("2")) {
          record.setAttachmentPath(
            this.rentityAttachmentDAO.selectByPrimaryKey(record.getEntityId()).getAttachmentPath());
        }
      }
    }
    return operaRecList;
  }

  public String createJournal(String module, String message, List rEntityList)
  {
    User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String journalID = this.dynEntityDAO.createJournal(user.getUsername(), module, message);
    if (rEntityList != null) {
      for (int i = 0; i < rEntityList.size(); i++)
      {
        REntity record = (REntity)rEntityList.get(i);
        record.setId(UUID.randomUUID().toString());
        record.setMeta1Id(Integer.valueOf(14));
        record.setEntity1Id(journalID);
        this.rentityDAO.insert(record);
      }
    }
    return journalID;
  }

  public void delRemind(String remindId)
  {
    this.reminderDAO.deleteByPrimaryKey(remindId);
  }

  public String remind(Integer userId, Integer metaId, String entityId, String message, String date)
  {
    User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Reminder reminder = new Reminder();
    reminder.setToUser(userId.toString());
    reminder.setEntityId(entityId);
    reminder.setMessage(message);
    reminder.setMetaId(metaId);
    reminder.setCreateUser(user.getUserName());
    reminder.setCreateTime(new Date());
    try
    {
      reminder.setRemindTime(sdf.parse(date));
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    String id = UUID.randomUUID().toString();
    reminder.setId(id);
    this.reminderDAO.insert(reminder);
    return id;
  }

  public List getUserRemind(Integer userId)
  {
    ReminderCriteria remc = new ReminderCriteria();
    ReminderCriteria.Criteria criteria = remc.createCriteria();
    criteria.andToUserEqualTo(userId.toString());
    return this.reminderDAO.selectByExample(remc);
  }

  public List getPicList()
  {
    PicCriteria criteria = new PicCriteria();
    return this.picDAO.selectByExample(criteria);
  }

  public void delEntity(Integer metaId, String id)
    throws Exception
  {
    this.dynEntityDAO.deleteEntityByPK(metaId, id);

    REntityAttachmentCriteria rattachementCriter = new REntityAttachmentCriteria();
    REntityAttachmentCriteria.Criteria delRAttachment = rattachementCriter.createCriteria();
    delRAttachment.andMetaIdEqualTo(metaId).andEntityIdEqualTo(id);

    List attachmentList = this.rentityAttachmentDAO.selectByExample(rattachementCriter);
    if (attachmentList != null) {
      for (int i = 0; i < attachmentList.size(); i++) {
        FileTool.deleteFile(ServletActionContext.getServletContext().getRealPath("") +
          ((REntityAttachment)attachmentList.get(i)).getAttachmentPath());
      }
    }
    this.rentityAttachmentDAO.deleteByExample(rattachementCriter);




    this.httpSolrService.deleteDoc(id);
  }

  public String createEntity(Integer metaId, Map valuesMap)
    throws Exception
  {
    Integer[][] charaArr = (Integer[][])valuesMap.get("charaArr");
    String[][] hierarchyArr = (String[][])valuesMap.get("hierarchyArr");
    String[][] entityArr = (String[][])valuesMap.get("entityArr");
    String[] entityLabel = (String[])valuesMap.get("entityLabel");
    valuesMap.remove("charaArr");
    valuesMap.remove("hierarchyArr");
    valuesMap.remove("entityArr");
    valuesMap.remove("entityLabel");

    String[] attachNameList = (String[])valuesMap.get("attachNameList");
    valuesMap.remove("attachNameList");
    if (valuesMap.get("update_user") == null)
    {
      User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      valuesMap.put("update_user", user.getUsername());
    }
    if (valuesMap.get("create_user") == null)
    {
      User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      valuesMap.put("create_user", user.getUsername());
    }
    String entityId = this.dynEntityDAO.createEntity(metaId, valuesMap);



    Map solrValuesMap = new HashMap();


    Set<String> keys = valuesMap.keySet();
    PicCriteria criteria;
    List l;
    Pic pic;
    for (String key : keys)
    {
      if (key.startsWith("p_"))
      {
        String filePath = (String)valuesMap.get(key);
        criteria = new PicCriteria();
        criteria.createCriteria().andPicUrlEqualTo(filePath);
        l = this.picDAO.selectByExample(criteria);
        if (l.size() == 0)
        {
          pic = new Pic();
          pic.setCreateTime(new Date());
          File file = new File(ServletActionContext.getServletContext().getRealPath("upload") + "\\" + filePath);
          pic.setPicName(file.getName());
          pic.setPicSize(Integer.valueOf((int)file.length()));
          pic.setRefCount(Integer.valueOf(1));
          pic.setPreviewUrl(valuesMap.get(key) + "_p.jpg");
          pic.setPicUrl((String)valuesMap.get(key));
          this.picDAO.insert(pic);
        }
        else
        {
          pic = (Pic)l.get(0);
          pic.setRefCount(Integer.valueOf(pic.getRefCount().intValue() + 1));
          this.picDAO.updateByPrimaryKey(pic);
        }
      }
      solrValuesMap.put(key, valuesMap.get(key));
    }
    HashSet tempSet;
    if (charaArr != null)
    {
      ArrayList characterNameList = new ArrayList();
      ArrayList characterDataList = new ArrayList();


      for (int i = 0; i < charaArr.length; i++)
      {
        Integer[] chara = charaArr[i];

        REntityCharaData record = new REntityCharaData();
        record.setMetaId(metaId);
        record.setEntityId(entityId);
        record.setCharaId(chara[0]);
        record.setDataId(chara[1]);
        record.setId(UUID.randomUUID().toString());
        this.rentityCharaDataDAO.insert(record);


        CharacterData characterDataItem = this.characterDataDAO.selectByPrimaryKey(chara[1]);
        characterDataList.add(characterDataItem.getDataName());
        characterNameList.add(characterDataItem.getCharaDef().getCharaName());
      }
      tempSet = new HashSet(characterNameList);
      characterNameList.clear();
      characterNameList.addAll(tempSet);
      solrValuesMap.put("character_name", characterNameList);
      solrValuesMap.put("character_data", characterDataList);
    }
    if (hierarchyArr != null)
    {
      ArrayList hierarchyAttrList = new ArrayList();


     for (int i = 0; i < hierarchyArr.length; i++)
      {
        String[] hierarchy = hierarchyArr[i];

        REntityHierarchyData record = new REntityHierarchyData();
        record.setMetaId(metaId);
        record.setEntityId(entityId);
        record.setAttrId(new Integer(hierarchy[0]));
        record.setValueId(hierarchy[1]);
        record.setId(UUID.randomUUID().toString());
        this.rentityHierarchyDataDAO.insert(record);

        hierarchyAttrList.add(this.hierarchyAttrValueDAO.selectByPrimaryKey(hierarchy[1]).getContent());
      }
      solrValuesMap.put("hierarchy_attr", hierarchyAttrList);
    }
    if (entityArr != null) {
      for (int i = 0; i < entityArr.length; i++)
      {
        String[] entity = entityArr[i];
        REntity record = new REntity();
        record.setMeta1Id(metaId);
        record.setEntity1Id(entityId);
        record.setMeta2Id(new Integer(entity[0]));
        record.setEntity2Id(entity[1]);
        record.setId(UUID.randomUUID().toString());
        record.setLabel("");
        if (entityLabel != null) {
          record.setLabel(entityLabel[i]);
        }
        this.rentityDAO.insert(record);
      }
    }
    if (attachNameList != null) {
      for (int i = 0; i < attachNameList.length; i++)
      {
        REntityAttachment record = new REntityAttachment();
        record.setMetaId(metaId);
        record.setEntityId(entityId);
        record.setAttachmentPath(attachNameList[i]);
        int slashIndex = attachNameList[i].lastIndexOf('/');
        if (slashIndex != -1) {
          record.setAttachmentName(attachNameList[i].substring(slashIndex + 1));
        }
        record.setId(UUID.randomUUID().toString());
        this.rentityAttachmentDAO.insert(record);
      }
    }
    solrValuesMap.put("meta_id", metaId.toString());
    solrValuesMap.put("entity_name", this.metaDAO.selectByPrimaryKey(metaId).getEntityName());


    this.httpSolrService.addDoc(entityId, solrValuesMap);

    //maqing 2015-11-09 把属性恢复
    if (charaArr != null) {
    	valuesMap.put("charaArr",charaArr);
    }
    if (hierarchyArr != null) {
    	valuesMap.put("hierarchyArr",hierarchyArr);
    }
    if (entityArr != null) {
    	valuesMap.put("entityArr",entityArr);
    }
    if (entityLabel != null) {
    	valuesMap.put("entityLabel",entityLabel);
    }
    if (attachNameList != null) {
    	valuesMap.put("attachNameList",attachNameList);
    }

    return entityId;
  }

  private void snapshotEntity(Integer metaId, String entityId)
  {
    List l = this.dynEntityDAO.dynSelect("select * from t_entity_" + metaId + " where id='" + entityId + "'");
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
        cvalue = cvalue + " " + this.characterDataDAO.selectByPrimaryKey(rec.getDataId()).getDataName();
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
    renValue = renValue.replaceFirst(",", "");
    this.dynEntityDAO.createSnapshot(metaId, valueMap);
  }

  public void deleteEntity(Integer metaId, String id)
  {
    Map valuesMap = this.dynEntityDAO.selectEntityByPK(metaId, id);
    if (valuesMap == null) {
      return;
    }
    this.dynEntityDAO.deleteEntityByPK(metaId, id);


    Set<String> keys = valuesMap.keySet();
    for (String key : keys)
    {
      String value = (String)valuesMap.get(key);
      if ((key.startsWith("p_")) && (value != null) && (!value.equals("")))
      {
        String filePath = (String)valuesMap.get(key);
        PicCriteria criteria = new PicCriteria();
        criteria.createCriteria().andPicUrlEqualTo(filePath);
        List l = this.picDAO.selectByExample(criteria);
        if (l.size() != 0)
        {
          Pic pic = (Pic)l.get(0);
          pic.setRefCount(Integer.valueOf(pic.getRefCount().intValue() - 1));
          this.picDAO.updateByPrimaryKey(pic);
        }
      }
    }
    REntityAttachmentCriteria rattachementCriter = new REntityAttachmentCriteria();
    REntityAttachmentCriteria.Criteria delRAttachment = rattachementCriter.createCriteria();
    delRAttachment.andMetaIdEqualTo(metaId).andEntityIdEqualTo(id);

    List attachmentList = this.rentityAttachmentDAO.selectByExample(rattachementCriter);
    if (attachmentList != null) {
      for (int i = 0; i < attachmentList.size(); i++) {
        FileTool.deleteFile(ServletActionContext.getServletContext().getRealPath("") +
          ((REntityAttachment)attachmentList.get(i)).getAttachmentPath());
      }
    }
    this.rentityAttachmentDAO.deleteByExample(rattachementCriter);
  }

  public String importEntity(Integer metaId, String id, Map valuesMap)
    throws Exception
  {
    Map oldValue = this.dynEntityDAO.selectEntityByPK(metaId, id);

    Integer[][] charaArr = (Integer[][])valuesMap.get("charaArr");
    String[][] hierarchyArr = (String[][])valuesMap.get("hierarchyArr");
    String[][] entityArr = (String[][])valuesMap.get("entityArr");
    String[] entityLabel = (String[])valuesMap.get("entityLabel");
    valuesMap.remove("charaArr");
    valuesMap.remove("hierarchyArr");
    valuesMap.remove("entityArr");
    valuesMap.remove("entityLabel");

    String[] attachNameList = (String[])valuesMap.get("attachNameList");
    valuesMap.remove("attachNameList");


    snapshotEntity(metaId, id);

    String entityId = id;
    if (valuesMap.get("update_user") == null)
    {
      User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      valuesMap.put("update_user", user.getUsername());
    }
    valuesMap.remove("update_time");
    this.dynEntityDAO.updateEntity(metaId, id, valuesMap);



    valuesMap.put("meta_id", metaId.toString());
    valuesMap.put("entity_name", this.metaDAO.selectByPrimaryKey(metaId).getEntityName());

    this.httpSolrService.deleteDoc(id);

    this.httpSolrService.addDoc(id, valuesMap);


    return entityId;
  }

  public String updateEntity(Integer metaId, String id, Map valuesMap)
    throws Exception
  {
    Map oldValue = this.dynEntityDAO.selectEntityByPK(metaId, id);

    Integer[][] charaArr = (Integer[][])valuesMap.get("charaArr");
    String[][] hierarchyArr = (String[][])valuesMap.get("hierarchyArr");
    String[][] entityArr = (String[][])valuesMap.get("entityArr");
    String[] entityLabel = (String[])valuesMap.get("entityLabel");
    valuesMap.remove("charaArr");
    valuesMap.remove("hierarchyArr");
    valuesMap.remove("entityArr");
    valuesMap.remove("entityLabel");

    String[] attachNameList = (String[])valuesMap.get("attachNameList");
    valuesMap.remove("attachNameList");


    snapshotEntity(metaId, id);

    String entityId = id;
    if (valuesMap.get("update_user") == null)
    {
      User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      valuesMap.put("update_user", user.getUsername());
    }
    valuesMap.remove("update_time");
    this.dynEntityDAO.updateEntity(metaId, id, valuesMap);



    Map solrValuesMap = new HashMap();



    Set<String> keys = valuesMap.keySet();
    PicCriteria criteria;
    List l;
    Pic pic;
    for (String key : keys)
    {
      if (key.startsWith("p_"))
      {
        String filePath = (String)valuesMap.get(key);
        String oldFilePath = (String)oldValue.get(key);
        if ((oldFilePath == null) || (oldFilePath.equals("")))
        {
          if ((filePath != null) && (!filePath.equals("")))
          {
            criteria = new PicCriteria();
            criteria.createCriteria().andPicUrlEqualTo(filePath);
            l = this.picDAO.selectByExample(criteria);
            if (l.size() == 0)
            {
              pic = new Pic();
              pic.setCreateTime(new Date());
              File file = new File(ServletActionContext.getServletContext().getRealPath("upload") + "\\" + filePath);
              pic.setPicName(file.getName());
              pic.setPicSize(Integer.valueOf((int)file.length()));
              pic.setRefCount(Integer.valueOf(1));
              pic.setPreviewUrl(valuesMap.get(key) + "_p.jpg");
              pic.setPicUrl((String)valuesMap.get(key));
              this.picDAO.insert(pic);
            }
            else
            {
              pic = (Pic)l.get(0);
              pic.setRefCount(Integer.valueOf(pic.getRefCount().intValue() + 1));
              this.picDAO.updateByPrimaryKey(pic);
            }
          }
        }
        else if ((filePath == null) || (filePath.equals("")))
        {
          criteria = new PicCriteria();
          criteria.createCriteria().andPicUrlEqualTo(oldFilePath);
          l = this.picDAO.selectByExample(criteria);
          if (l.size() != 0)
          {
            pic = (Pic)l.get(0);
            pic.setRefCount(Integer.valueOf(pic.getRefCount().intValue() - 1));
            this.picDAO.updateByPrimaryKey(pic);
          }
        }
        else if (!oldValue.get(key).equals(filePath))
        {
          criteria = new PicCriteria();
          criteria.createCriteria().andPicUrlEqualTo(filePath);
          l = this.picDAO.selectByExample(criteria);
          if (l.size() == 0)
          {
            pic = new Pic();
            pic.setCreateTime(new Date());
            File file = new File(ServletActionContext.getServletContext().getRealPath("upload") + "\\" + filePath);
            pic.setPicName(file.getName());
            pic.setPicSize(Integer.valueOf((int)file.length()));
            pic.setRefCount(Integer.valueOf(1));
            pic.setPreviewUrl(valuesMap.get(key) + "_p.jpg");
            pic.setPicUrl((String)valuesMap.get(key));
            this.picDAO.insert(pic);
          }
          else
          {
            pic = (Pic)l.get(0);
            pic.setRefCount(Integer.valueOf(pic.getRefCount().intValue() + 1));
            this.picDAO.updateByPrimaryKey(pic);
          }
          criteria = new PicCriteria();
          criteria.createCriteria().andPicUrlEqualTo(oldFilePath);
          l = this.picDAO.selectByExample(criteria);
          if (l.size() != 0)
          {
            pic = (Pic)l.get(0);
            pic.setRefCount(Integer.valueOf(pic.getRefCount().intValue() - 1));
            this.picDAO.updateByPrimaryKey(pic);
          }
        }
      }
      solrValuesMap.put(key, valuesMap.get(key));
    }
    REntityHierarchyData record;
    if (hierarchyArr != null)
    {
      REntityHierarchyDataCriteria rhcriteria = new REntityHierarchyDataCriteria();
      REntityHierarchyDataCriteria.Criteria rehc = rhcriteria.createCriteria();
      rehc.andMetaIdEqualTo(metaId);
      rehc.andEntityIdEqualTo(id);
      this.rentityHierarchyDataDAO.deleteByExample(rhcriteria);


      ArrayList hierarchyAttrList = new ArrayList();


      for (int i = 0; i < hierarchyArr.length; i++)
      {
        String[] hierarchy = hierarchyArr[i];

        record = new REntityHierarchyData();
        record.setMetaId(metaId);
        record.setEntityId(id);
        record.setAttrId(new Integer(hierarchy[0]));
        record.setValueId(hierarchy[1]);
        record.setId(UUID.randomUUID().toString());
        this.rentityHierarchyDataDAO.insert(record);


        hierarchyAttrList.add(this.hierarchyAttrValueDAO.selectByPrimaryKey(hierarchy[1]).getContent());
      }
      solrValuesMap.put("hierarchy_attr", hierarchyAttrList);
    }
    else
    {
      REntityHierarchyDataCriteria rhcriteria = new REntityHierarchyDataCriteria();
      REntityHierarchyDataCriteria.Criteria rehc = rhcriteria.createCriteria();
      rehc.andMetaIdEqualTo(metaId);
      rehc.andEntityIdEqualTo(id);
      this.rentityHierarchyDataDAO.deleteByExample(rhcriteria);
    }
    REntityCharaDataCriteria rhcriteria = new REntityCharaDataCriteria();
    REntityCharaDataCriteria.Criteria delCriteria = rhcriteria.createCriteria();
    delCriteria.andMetaIdEqualTo(metaId).andEntityIdEqualTo(entityId);
    this.rentityCharaDataDAO.deleteByExample(rhcriteria);
    if (charaArr != null)
    {
      ArrayList characterNameList = new ArrayList();
      ArrayList characterDataList = new ArrayList();


      for (int i = 0; i < charaArr.length; i++)
      {
        Integer[] chara = charaArr[i];

        REntityCharaData rerecord = new REntityCharaData();
        rerecord.setMetaId(metaId);
        rerecord.setEntityId(entityId);
        rerecord.setCharaId(chara[0]);
        rerecord.setDataId(chara[1]);
        rerecord.setId(UUID.randomUUID().toString());
        this.rentityCharaDataDAO.insert(rerecord);


        CharacterData characterDataItem = this.characterDataDAO.selectByPrimaryKey(chara[1]);
        characterDataList.add(characterDataItem.getDataName());
        characterNameList.add(characterDataItem.getCharaDef().getCharaName());
      }
      HashSet tempSet = new HashSet(characterNameList);
      characterNameList.clear();
      characterNameList.addAll(tempSet);
      solrValuesMap.put("character_name", characterNameList);
      solrValuesMap.put("character_data", characterDataList);
    }
    REntityCriteria rentityCriter = new REntityCriteria();
    REntityCriteria.Criteria delREntity = rentityCriter.createCriteria();
    delREntity.andMeta1IdEqualTo(metaId).andEntity1IdEqualTo(entityId);
    this.rentityDAO.deleteByExample(rentityCriter);
    if (entityArr != null) {
      for (int i = 0; i < entityArr.length; i++)
      {
        String[] entity = entityArr[i];
        REntity rerecord = new REntity();
        rerecord.setMeta1Id(metaId);
        rerecord.setEntity1Id(entityId);
        rerecord.setMeta2Id(new Integer(entity[0]));
        rerecord.setEntity2Id(entity[1]);
        rerecord.setId(UUID.randomUUID().toString());
        rerecord.setLabel("");
        if (entityLabel != null) {
        	rerecord.setLabel(entityLabel[i]);
        }
        this.rentityDAO.insert(rerecord);
      }
    }
    REntityAttachmentCriteria rattachementCriter = new REntityAttachmentCriteria();
    REntityAttachmentCriteria.Criteria delRAttachment = rattachementCriter.createCriteria();
    delRAttachment.andMetaIdEqualTo(metaId).andEntityIdEqualTo(entityId);
    this.rentityAttachmentDAO.deleteByExample(rattachementCriter);
    if (attachNameList != null) {
      for (int i = 0; i < attachNameList.length; i++)
      {
        REntityAttachment rerecord = new REntityAttachment();
        rerecord.setMetaId(metaId);
        rerecord.setEntityId(entityId);
        rerecord.setAttachmentPath(attachNameList[i]);
        int slashIndex = attachNameList[i].lastIndexOf('/');
        if (slashIndex != -1) {
        	rerecord.setAttachmentName(attachNameList[i].substring(slashIndex + 1));
        }
        rerecord.setId(UUID.randomUUID().toString());
        this.rentityAttachmentDAO.insert(rerecord);
      }
    }
    solrValuesMap.put("meta_id", metaId.toString());
    solrValuesMap.put("entity_name", this.metaDAO.selectByPrimaryKey(metaId).getEntityName());

    this.httpSolrService.deleteDoc(entityId);

    this.httpSolrService.addDoc(entityId, solrValuesMap);

    //maqing 2015-11-09 把属性恢复
    if (charaArr != null) {
    	valuesMap.put("charaArr",charaArr);
    }
    if (hierarchyArr != null) {
    	valuesMap.put("hierarchyArr",hierarchyArr);
    }
    if (entityArr != null) {
    	valuesMap.put("entityArr",entityArr);
    }
    if (entityLabel != null) {
    	valuesMap.put("entityLabel",entityLabel);
    }
    if (attachNameList != null) {
    	valuesMap.put("attachNameList",attachNameList);
    }

    return entityId;
  }

  public Integer createEntity(Integer metaId, String[] picdata, String[] descdata, String[] hierdata, String[] charadata, int[] picid, int[] descid, int[] hierid, int[] charaid)
  {
    Entity entity = new Entity();
    entity.setMetaId(metaId);
    entity.setCreateTime(new Date());
    entity.setLastUpdateTime(new Date());

    Integer entityId = this.entityDAO.insert(entity);
    if (picdata != null) {
      for (int i = 0; i < picdata.length; i++)
      {
        PicAttrData picAData = new PicAttrData();
        picAData.setEntityId(entityId);
        picAData.setAttrId(Integer.valueOf(picid[i]));
        picAData.setFilePath(picdata[i]);
        picAData.setPreviewFilePath(picdata[i]);
        this.picAttrDataDAO.insert(picAData);
      }
    }
    if (descdata != null) {
      for (int i = 0; i < descdata.length; i++)
      {
        DescAttrData descAData = new DescAttrData();
        descAData.setEntityId(entityId);
        descAData.setAttrId(Integer.valueOf(descid[i]));
        descAData.setContent(descdata[i]);
        descAData.setLargeContent(descdata[i]);
        this.descAttrDataDAO.insert(descAData);
      }
    }
    if (hierdata != null) {
      for (int i = 0; i < hierdata.length; i++)
      {
        HierarchyAttrData hierAData = new HierarchyAttrData();
        hierAData.setEntityId(entityId);
        hierAData.setAttrId(Integer.valueOf(hierid[i]));
        hierAData.setValueId(Integer.valueOf(Integer.parseInt(hierdata[i])));
        this.hierarchyAttrDataDAO.insert(hierAData);
      }
    }
    if (charadata != null) {
      for (int i = 0; i < charadata.length; i++)
      {
        CharacterAttrData charaAData = new CharacterAttrData();
        charaAData.setEntityId(entityId);
        charaAData.setAttrId(Integer.valueOf(charaid[i]));
        charaAData.setContent(charadata[i]);
        this.characterAttrDataDAO.insert(charaAData);
      }
    }
    return entityId;
  }

  public List findEntityBymetaId(Integer metaId)
  {
    return this.dynEntityDAO.selectByMetaId(metaId);
  }

  public List findDescAttrData(Integer entityId)
  {
    DescAttrDataCriteria descCriteria = new DescAttrDataCriteria();
    DescAttrDataCriteria.Criteria c = descCriteria.createCriteria();
    c.andEntityIdEqualTo(entityId);
    return this.descAttrDataDAO.selectByExampleWithoutBLOBs(descCriteria);
  }

  public List findCharacterAttrData(Integer entityId)
  {
    CharacterAttrDataCriteria descCriteria = new CharacterAttrDataCriteria();
    CharacterAttrDataCriteria.Criteria c = descCriteria.createCriteria();
    c.andEntityIdEqualTo(entityId);
    return this.characterAttrDataDAO.selectByExample(descCriteria);
  }

  public List findPicAttrData(Integer entityId)
  {
    PicAttrDataCriteria descCriteria = new PicAttrDataCriteria();
    PicAttrDataCriteria.Criteria c = descCriteria.createCriteria();
    c.andEntityIdEqualTo(entityId);
    return this.picAttrDataDAO.selectByExample(descCriteria);
  }

  public List findHierarchyAttrData(Integer entityId)
  {
    HierarchyAttrDataCriteria descCriteria = new HierarchyAttrDataCriteria();
    HierarchyAttrDataCriteria.Criteria c = descCriteria.createCriteria();
    c.andEntityIdEqualTo(entityId);
    return this.hierarchyAttrDataDAO.selectByExample(descCriteria);
  }

  public List selectColumnData(Integer metaId, String columnName)
  {
    return this.dynEntityDAO.selectColumnData(metaId, columnName);
  }

  public void saveREntity(Integer rMetaId, String rEntityId, Integer tMetaId, String[] tEntityIdArr)
  {
    REntity rEntity = new REntity();
    if (tEntityIdArr != null) {
      for (String tEntityId : tEntityIdArr)
      {
        rEntity.setEntity1Id(rEntityId);
        rEntity.setEntity2Id(tEntityId);
        rEntity.setMeta1Id(rMetaId);
        rEntity.setMeta2Id(tMetaId);
        rEntity.setId(UUID.randomUUID().toString());
        this.rentityDAO.insert(rEntity);
      }
    }
  }

  public void saveREntity(Integer rMetaId, String rEntityId, Integer tMetaId, String tEntityId, String rLabel)
  {
    REntityCriteria reCriteria = new REntityCriteria();
    REntityCriteria.Criteria c = reCriteria.createCriteria();
    c.andEntity1IdEqualTo(rEntityId);
    c.andEntity2IdEqualTo(tEntityId);
    c.andMeta1IdEqualTo(rMetaId);
    c.andMeta2IdEqualTo(tMetaId);
    this.rentityDAO.deleteByExample(reCriteria);

    REntity rEntity = new REntity();
    rEntity.setEntity1Id(rEntityId);
    rEntity.setEntity2Id(tEntityId);
    rEntity.setMeta1Id(rMetaId);
    rEntity.setMeta2Id(tMetaId);
    rEntity.setId(UUID.randomUUID().toString());
    rEntity.setLabel(rLabel);
    this.rentityDAO.insert(rEntity);
  }

  public void updateREntity(Integer rMetaId, String rEntityId, Integer tMetaId, String[] tEntityIdArr)
  {
    REntity rEntity = new REntity();

    REntityCriteria reCriteria = new REntityCriteria();
    REntityCriteria.Criteria c = reCriteria.createCriteria();
    c.andEntity1IdEqualTo(rEntityId);
    c.andMeta1IdEqualTo(rMetaId);
    c.andMeta2IdEqualTo(tMetaId);
    this.rentityDAO.deleteByExample(reCriteria);
    if (tEntityIdArr != null) {
      for (String tEntityId : tEntityIdArr)
      {
        rEntity.setEntity1Id(rEntityId);
        rEntity.setEntity2Id(tEntityId);
        rEntity.setMeta1Id(rMetaId);
        rEntity.setMeta2Id(tMetaId);
        rEntity.setId(UUID.randomUUID().toString());
        this.rentityDAO.insert(rEntity);
      }
    }
  }

  public void delREntity(Integer rMetaId, String rEntityId, Integer tMetaId)
  {
    REntityCriteria reCriteria = new REntityCriteria();
    REntityCriteria.Criteria c = reCriteria.createCriteria();
    c.andEntity1IdEqualTo(rEntityId);
    c.andMeta1IdEqualTo(rMetaId);
    c.andMeta2IdEqualTo(tMetaId);
    this.rentityDAO.deleteByExample(reCriteria);
  }

  public List listREntity(Integer metaId, String entityId)
  {
    return this.rentityDAO.getREntityById(metaId, entityId);
  }

  public List listRAttachment(Integer metaId, String entityId)
  {
    return this.rentityAttachmentDAO.getRAttachmentById(metaId, entityId);
  }

  public List listCharaData(Integer charaId)
  {
    CharacterDataCriteria characterDataCriteria = new CharacterDataCriteria();
    characterDataCriteria.createCriteria().andCharaIdEqualTo(charaId);
    return this.characterDataDAO.selectByExample(characterDataCriteria);
  }

  public Integer createCharaData(CharacterData record)
  {
    Integer id = this.characterDataDAO.insert(record);
    return id;
  }

  public void delCharaData(Integer dataId)
  {
    this.characterDataDAO.deleteByPrimaryKey(dataId);
  }

  public void updateCharaData(CharacterData record)
  {
    this.characterDataDAO.updateByPrimaryKey(record);
  }

  public void updateShareCharaData(Integer dataId)
  {
    CharacterData characterData = this.characterDataDAO.selectByPrimaryKey(dataId);
    if (characterData != null) {
      this.characterDataDAO.updateShare(characterData);
    }
  }

  public void updateEnableCharaData(Integer dataId)
  {
    CharacterData characterData = this.characterDataDAO.selectByPrimaryKey(dataId);
    if (characterData != null) {
      this.characterDataDAO.updateEnable(characterData);
    }
  }

  public void createREntityCharaData(String entityId, Integer[] dataIdArr)
  {
    if ((entityId != null) && (dataIdArr != null)) {
      for (Integer dataId : dataIdArr)
      {
        REntityCharaData record = new REntityCharaData();
        record.setEntityId(entityId);
        record.setDataId(dataId);
        this.rentityCharaDataDAO.insert(record);
      }
    }
  }

  public void delREntityCharaData(String entityId, Integer[] dataIdArr)
  {
    if ((entityId != null) && (dataIdArr != null))
    {
      REntityCharaDataCriteria rEntityCharaDataCriteria = new REntityCharaDataCriteria();
      REntityCharaDataCriteria.Criteria c = rEntityCharaDataCriteria.createCriteria();
      c.andEntityIdEqualTo(entityId);
      c.andDataIdIn(Arrays.asList(dataIdArr));

      this.rentityCharaDataDAO.deleteByExample(rEntityCharaDataCriteria);
    }
  }

  public void updateREntityCharaData(String entityId, Integer[] dataIdArr)
  {
    if ((entityId != null) && (dataIdArr != null))
    {
      REntityCharaDataCriteria rEntityCharaDataCriteria = new REntityCharaDataCriteria();
      REntityCharaDataCriteria.Criteria c = rEntityCharaDataCriteria.createCriteria();
      c.andEntityIdEqualTo(entityId);

      this.rentityCharaDataDAO.deleteByExample(rEntityCharaDataCriteria);
      for (Integer dataId : dataIdArr)
      {
        REntityCharaData record = new REntityCharaData();
        record.setEntityId(entityId);
        record.setDataId(dataId);
        this.rentityCharaDataDAO.insert(record);
      }
    }
  }

  public List findEntityByChara(Integer metaId, Integer[][] charaArr)
  {
    return this.dynEntityDAO.selectByChara(metaId, charaArr);
  }

  public List findEntityByHierarchy(Integer metaId, String[][] hierarchyArr)
  {
    return this.dynEntityDAO.selectByHierarchy(metaId, hierarchyArr);
  }

  public List findHierarchyByEntity(Integer metaId, String entityId)
  {
    return this.dynEntityDAO.selectHierarchyByEntity(metaId, entityId);
  }

  public List dynSelect(String columns, String fromStr, String whereStr)
  {
    return this.dynEntityDAO.dynSelect(columns, fromStr, whereStr);
  }

  public OperationBoxDAO getOperationBoxDAO()
  {
    return this.operationBoxDAO;
  }

  public void setOperationBoxDAO(OperationBoxDAO operationBoxDAO)
  {
    this.operationBoxDAO = operationBoxDAO;
  }

  public REntityAttachmentDAO getRentityAttachmentDAO()
  {
    return this.rentityAttachmentDAO;
  }

  public void setRentityAttachmentDAO(REntityAttachmentDAO entityAttachmentDAO)
  {
    this.rentityAttachmentDAO = entityAttachmentDAO;
  }

  public HttpSolrService getHttpSolrService()
  {
    return this.httpSolrService;
  }

  public void setHttpSolrService(HttpSolrService httpSolrService)
  {
    this.httpSolrService = httpSolrService;
  }
  

}
