package com.commondb.db.service.impl;

import com.commondb.app.common.meta.CharacterField;
import com.commondb.app.common.meta.HierarchyField;
import com.commondb.common.NameExistException;
import com.commondb.db.bo.CharacterAttrDef;
import com.commondb.db.bo.CharacterAttrDefCriteria;
import com.commondb.db.bo.CharacterAttrDefCriteria.Criteria;
import com.commondb.db.bo.CharacterData;
import com.commondb.db.bo.CharacterDataCriteria;
import com.commondb.db.bo.CharacterDef;
import com.commondb.db.bo.CharacterDefCriteria;
import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.bo.DescAttrDefCriteria;
import com.commondb.db.bo.HierarchyAttrDef;
import com.commondb.db.bo.HierarchyAttrDefCriteria;
import com.commondb.db.bo.HierarchyAttrValue;
import com.commondb.db.bo.HierarchyAttrValueCriteria;
import com.commondb.db.bo.HierarchyAttrValueNode;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.MetaCriteria;
import com.commondb.db.bo.MetaProperty;
import com.commondb.db.bo.MetaPropertyCriteria;
import com.commondb.db.bo.PicAttrDef;
import com.commondb.db.bo.PicAttrDefCriteria;
import com.commondb.db.bo.REntityCharaData;
import com.commondb.db.bo.REntityCharaDataCriteria;
import com.commondb.db.bo.REntityHierarchyDataCriteria;
import com.commondb.db.bo.RMetaChara;
import com.commondb.db.bo.RMetaCharaCriteria;
import com.commondb.db.bo.RoleMetaCriteria;
import com.commondb.db.bo.User;
import com.commondb.db.dao.CharacterAttrDefDAO;
import com.commondb.db.dao.CharacterDataDAO;
import com.commondb.db.dao.CharacterDefDAO;
import com.commondb.db.dao.DescAttrDefDAO;
import com.commondb.db.dao.DynamicEntityDAO;
import com.commondb.db.dao.HierarchyAttrDefDAO;
import com.commondb.db.dao.HierarchyAttrValueDAO;
import com.commondb.db.dao.MetaDAO;
import com.commondb.db.dao.MetaPropertyDAO;
import com.commondb.db.dao.PicAttrDefDAO;
import com.commondb.db.dao.REntityCharaDataDAO;
import com.commondb.db.dao.REntityHierarchyDataDAO;
import com.commondb.db.dao.RMetaCharaDAO;
import com.commondb.db.dao.RoleMetaDAO;
import com.commondb.db.dao.UserDAO;
import com.commondb.db.service.MetaService;
import edu.emory.mathcs.backport.java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.acls.AccessControlEntry;
import org.springframework.security.acls.MutableAcl;
import org.springframework.security.acls.MutableAclService;
import org.springframework.security.acls.NotFoundException;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.objectidentity.ObjectIdentityImpl;
import org.springframework.security.acls.sid.GrantedAuthoritySid;
import org.springframework.security.acls.sid.PrincipalSid;
import org.springframework.security.acls.sid.Sid;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

public class MetaServiceImpl
  implements MetaService
{
  private MetaDAO metaDAO;
  private CharacterAttrDefDAO characterAttrDefDAO;
  private HierarchyAttrDefDAO hierarchyAttrDefDAO;
  private PicAttrDefDAO picAttrDefDAO;
  private DescAttrDefDAO descAttrDefDAO;
  private DynamicEntityDAO dynEntityDAO;
  HierarchyAttrValueDAO hierarchyAttrValueDAO;
  private MutableAclService mutableAclService;
  private RoleMetaDAO roleMetaDAO;
  private CharacterDefDAO characterDefDAO;
  private CharacterDataDAO characterDataDAO;
  private REntityCharaDataDAO rentityCharaDataDAO;
  private RMetaCharaDAO rmetaCharaDAO;
  private UserDAO userDAO;
  private REntityHierarchyDataDAO rentityHierarchyDataDAO;
  private MetaPropertyDAO metaPropertyDAO;
  static Logger logger = Logger.getLogger(MetaServiceImpl.class);

  public REntityHierarchyDataDAO getRentityHierarchyDataDAO()
  {
    return this.rentityHierarchyDataDAO;
  }

  public void setRentityHierarchyDataDAO(REntityHierarchyDataDAO rentityHierarchyDataDAO)
  {
    this.rentityHierarchyDataDAO = rentityHierarchyDataDAO;
  }

  public UserDAO getUserDAO()
  {
    return this.userDAO;
  }

  public void setUserDAO(UserDAO userDAO)
  {
    this.userDAO = userDAO;
  }

  public MetaPropertyDAO getMetaPropertyDAO()
  {
    return this.metaPropertyDAO;
  }

  public void setMetaPropertyDAO(MetaPropertyDAO metaPropertyDAO)
  {
    this.metaPropertyDAO = metaPropertyDAO;
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

  public MutableAclService getMutableAclService()
  {
    return this.mutableAclService;
  }

  public RoleMetaDAO getRoleMetaDAO()
  {
    return this.roleMetaDAO;
  }

  public void setRoleMetaDAO(RoleMetaDAO roleMetaDAO)
  {
    this.roleMetaDAO = roleMetaDAO;
  }

  public HierarchyAttrValueDAO getHierarchyAttrValueDAO()
  {
    return this.hierarchyAttrValueDAO;
  }

  public void setHierarchyAttrValueDAO(HierarchyAttrValueDAO hierarchyAttrValueDAO)
  {
    this.hierarchyAttrValueDAO = hierarchyAttrValueDAO;
  }

  public DynamicEntityDAO getDynEntityDAO()
  {
    return this.dynEntityDAO;
  }

  public void setDynEntityDAO(DynamicEntityDAO dynEntityDAO)
  {
    this.dynEntityDAO = dynEntityDAO;
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

  public List listCharaDef()
  {
    return this.characterDefDAO.selectByExample(new CharacterDefCriteria());
  }

  public List listCharaDefByMeta(Integer metaId)
  {
    return this.characterDefDAO.getByMetaId(metaId);
  }

  public List listCharaDefById(List charaIdL)
  {
    CharacterDefCriteria characterDefCriteria = new CharacterDefCriteria();
    CharacterDefCriteria.Criteria cdc = characterDefCriteria.createCriteria();
    cdc.andCharaIdIn(charaIdL);
    return this.characterDefDAO.selectByExample(characterDefCriteria);
  }

  public CharacterDef getCharaDefById(Integer charaId)
  {
    return this.characterDefDAO.selectByPrimaryKey(charaId);
  }

  public Integer createCharaDef(CharacterDef record)
  {
    Integer id = this.characterDefDAO.insert(record);

    record.setCharaId(id);
    if (record.getIsshared().equals(Integer.valueOf(1)))
    {
      Sid sidm = new PrincipalSid(getUsername());
      addPermissionCharacterDef(record, sidm, BasePermission.ADMINISTRATION);
      Sid sida = new GrantedAuthoritySid("ROLE_ADMIN");
      addPermissionCharacterDef(record, sida, BasePermission.ADMINISTRATION);
      Sid sid = new GrantedAuthoritySid("ROLE_AUTHENTICATEDCOMMON");
      addPermissionCharacterDef(record, sid, BasePermission.READ);
    }
    else
    {
      Sid sidm = new PrincipalSid(getUsername());
      addPermissionCharacterDef(record, sidm, BasePermission.ADMINISTRATION);
      Sid sida = new GrantedAuthoritySid("ROLE_ADMIN");
      addPermissionCharacterDef(record, sida, BasePermission.ADMINISTRATION);
      Sid sid = new GrantedAuthoritySid("ROLE_AUTHENTICATEDCOMMON");
      addPermissionCharacterDef(record, sid, BasePermission.WRITE);
    }
    return id;
  }

  public Integer createCharaDefWithMeta(CharacterDef record, Integer metaId)
  {
    Integer id = this.characterDefDAO.insert(record);
    record.setCharaId(id);

    RMetaChara rMetaChara = new RMetaChara();
    rMetaChara.setMetaId(metaId);
    rMetaChara.setCharaId(id);
    this.rmetaCharaDAO.insert(rMetaChara);
    this.dynEntityDAO.addColumn("t_his_entity_" + metaId, "c_" + id);
    if (record.getIsshared().equals(Integer.valueOf(1)))
    {
      Sid sidm = new PrincipalSid(getUsername());
      addPermissionCharacterDef(record, sidm, BasePermission.ADMINISTRATION);
      Sid sida = new GrantedAuthoritySid("ROLE_ADMIN");
      addPermissionCharacterDef(record, sida, BasePermission.ADMINISTRATION);
      Sid sid = new GrantedAuthoritySid("ROLE_AUTHENTICATEDCOMMON");
      addPermissionCharacterDef(record, sid, BasePermission.READ);
    }
    else
    {
      Sid sidm = new PrincipalSid(getUsername());
      addPermissionCharacterDef(record, sidm, BasePermission.ADMINISTRATION);
      Sid sida = new GrantedAuthoritySid("ROLE_ADMIN");
      addPermissionCharacterDef(record, sida, BasePermission.ADMINISTRATION);
      Sid sid = new GrantedAuthoritySid("ROLE_AUTHENTICATEDCOMMON");
      addPermissionCharacterDef(record, sid, BasePermission.WRITE);
    }
    return id;
  }

  public void delCharaDef(CharacterDef record)
  {
    Integer charaId = record.getCharaId();

    CharacterDataCriteria characterDataCriteria = new CharacterDataCriteria();
    CharacterDataCriteria.Criteria cdc = characterDataCriteria.createCriteria();
    cdc.andCharaIdEqualTo(charaId);
    List<CharacterData> characterDataList = this.characterDataDAO.selectByExample(characterDataCriteria);
    if (characterDataList != null) {
      for (CharacterData characterData : characterDataList)
      {
        REntityCharaDataCriteria rEntityCharaDataCriteria = new REntityCharaDataCriteria();
        REntityCharaDataCriteria.Criteria recc = rEntityCharaDataCriteria.createCriteria();
        recc.andDataIdEqualTo(characterData.getDataId());
        this.rentityCharaDataDAO.deleteByExample(rEntityCharaDataCriteria);


        delCharaData(characterData);
      }
    }
    RMetaCharaCriteria rMetaCharaCriteria = new RMetaCharaCriteria();
    RMetaCharaCriteria.Criteria c = rMetaCharaCriteria.createCriteria();
    c.andCharaIdEqualTo(charaId);
    List<RMetaChara> l = this.rmetaCharaDAO.selectByExample(rMetaCharaCriteria);
    for (RMetaChara r : l) {
      this.dynEntityDAO.dropColumn("t_his_entity_" + r.getMetaId(), "c_" + r.getCharaId());
    }
    this.rmetaCharaDAO.deleteByExample(rMetaCharaCriteria);


    ObjectIdentity oid = new ObjectIdentityImpl(CharacterDef.class, charaId);
    this.mutableAclService.deleteAcl(oid, false);


    this.characterDefDAO.deleteByPrimaryKey(charaId);
  }

  public void updateCharaDef(CharacterDef record)
  {
    if (record.getIsshared().equals(Integer.valueOf(1)))
    {
      Sid sid = new GrantedAuthoritySid("ROLE_AUTHENTICATEDCOMMON");
      updatePermissionCharacterDef(record, sid, BasePermission.READ);
    }
    else
    {
      Sid sid = new GrantedAuthoritySid("ROLE_AUTHENTICATEDCOMMON");
      updatePermissionCharacterDef(record, sid, BasePermission.WRITE);
    }
    this.characterDefDAO.updateByPrimaryKey(record);
  }

  public void updateShareCharaDef(Integer charaId)
  {
    CharacterDef characterDef = this.characterDefDAO.selectByPrimaryKey(charaId);
    if (characterDef != null) {
      this.characterDefDAO.updateShare(characterDef);
    }
  }

  public void updateEnableCharaDef(Integer charaId)
  {
    CharacterDef characterDef = this.characterDefDAO.selectByPrimaryKey(charaId);
    if (characterDef != null) {
      this.characterDefDAO.updateEnable(characterDef);
    }
  }

  public void updateCheckMultipleCharaDef(Integer charaId)
  {
    CharacterDef characterDef = this.characterDefDAO.selectByPrimaryKey(charaId);
    if (characterDef != null) {
      this.characterDefDAO.updateCheckMultiple(characterDef);
    }
  }

  public void createRMetaChara(Integer metaId, Integer[] charaIdArr)
  {
    if ((metaId != null) && (charaIdArr != null)) {
      for (Integer charaId : charaIdArr)
      {
        RMetaChara record = new RMetaChara();
        record.setMetaId(metaId);
        record.setCharaId(charaId);
        this.rmetaCharaDAO.insert(record);
        this.dynEntityDAO.addColumn("t_his_entity_" + metaId, "c_" + charaId);
      }
    }
  }

  private void newRMetaCharaWithoutHis(Integer metaId, Integer[] charaIdArr)
  {
    if ((metaId != null) && (charaIdArr != null)) {
      for (Integer charaId : charaIdArr)
      {
        RMetaChara record = new RMetaChara();
        record.setMetaId(metaId);
        record.setCharaId(charaId);
        this.rmetaCharaDAO.insert(record);
      }
    }
  }

  public void delRMetaChara(Integer metaId, Integer[] charaIdArr)
  {
    if ((metaId != null) && (charaIdArr != null))
    {
      RMetaCharaCriteria rMetaCharaCriteria = new RMetaCharaCriteria();
      RMetaCharaCriteria.Criteria c = rMetaCharaCriteria.createCriteria();
      c.andMetaIdEqualTo(metaId);
      c.andCharaIdIn(Arrays.asList(charaIdArr));
      List<RMetaChara> l = this.rmetaCharaDAO.selectByExample(rMetaCharaCriteria);
      for (RMetaChara r : l) {
        this.dynEntityDAO.dropColumn("t_his_entity_" + r.getMetaId(), "c_" + r.getCharaId());
      }
      this.rmetaCharaDAO.deleteByExample(rMetaCharaCriteria);
    }
  }

  public void updateRMetaChara(Integer metaId, Integer[] charaIdArr)
  {
    if ((metaId != null) && (charaIdArr == null))
    {
      List<CharacterDef> characterDefList = listCharaDefByMeta(metaId);
      if (characterDefList != null)
      {
        List<Integer> charaIdL = new ArrayList(characterDefList.size());
        for (CharacterDef cd : characterDefList) {
          charaIdL.add(cd.getCharaId());
        }
        RMetaCharaCriteria rMetaCharaCriteria = new RMetaCharaCriteria();
        RMetaCharaCriteria.Criteria c = rMetaCharaCriteria.createCriteria();
        c.andMetaIdEqualTo(metaId);
        if ((charaIdL != null) && (charaIdL.size() > 0)) 
        	c.andCharaIdIn(charaIdL);
        List<RMetaChara> l = this.rmetaCharaDAO.selectByExample(rMetaCharaCriteria);
        for (RMetaChara r : l) {
          this.dynEntityDAO.dropColumn("t_his_entity_" + r.getMetaId(), "c_" + r.getCharaId());
        }
        this.rmetaCharaDAO.deleteByExample(rMetaCharaCriteria);
      }
    }
    else if ((metaId != null) && (charaIdArr != null))
    {
      List<CharacterDef> characterDefList = listCharaDefByMeta(metaId);
      if (characterDefList == null)
      {
        for (int i = 0; i < charaIdArr.length; i++)
        {
          Integer charaId = charaIdArr[i];

          RMetaChara record = new RMetaChara();
          record.setMetaId(metaId);
          record.setCharaId(charaId);
          this.rmetaCharaDAO.insert(record);
          this.dynEntityDAO.addColumn("t_his_entity_" + record.getMetaId(), "c_" + record.getCharaId());
        }
      }
      else if (characterDefList != null)
      {
        for (int i = 0; i < charaIdArr.length; i++)
        {
          Integer charaId = charaIdArr[i];

          boolean voter = false;
          for (CharacterDef cd : characterDefList) {
            if (charaId.equals(cd.getCharaId()))
            {
              voter = true;
              characterDefList.remove(cd);
              break;
            }
          }
          if (!voter)
          {
            RMetaChara record = new RMetaChara();
            record.setMetaId(metaId);
            record.setCharaId(charaId);
            this.rmetaCharaDAO.insert(record);
            this.dynEntityDAO.addColumn("t_his_entity_" + record.getMetaId(), "c_" + record.getCharaId());
          }
        }
        if (characterDefList.size() > 0)
        {
          List<Integer> charaIdL = new ArrayList(characterDefList.size());
          for (CharacterDef cd : characterDefList) {
            charaIdL.add(cd.getCharaId());
          }
          RMetaCharaCriteria rMetaCharaCriteria = new RMetaCharaCriteria();
          RMetaCharaCriteria.Criteria c = rMetaCharaCriteria.createCriteria();
          c.andMetaIdEqualTo(metaId);
          c.andCharaIdIn(charaIdL);
          List<RMetaChara> l = this.rmetaCharaDAO.selectByExample(rMetaCharaCriteria);
          for (RMetaChara r : l) {
            this.dynEntityDAO.dropColumn("t_his_entity_" + r.getMetaId(), "c_" + r.getCharaId());
          }
          this.rmetaCharaDAO.deleteByExample(rMetaCharaCriteria);
        }
      }
    }
  }

  public List listRMetaChara(Integer metaId)
  {
    Integer userId = getUserId();

    return this.rmetaCharaDAO.selectByUser(metaId, userId);
  }

  public List listCharaData(Integer charaId)
  {
    CharacterDataCriteria characterDataCriteria = new CharacterDataCriteria();
    characterDataCriteria.createCriteria().andCharaIdEqualTo(charaId);
    return this.characterDataDAO.selectByExample(characterDataCriteria);
  }

  public List listCharaData(Integer[] charaIdArr)
  {
    if (charaIdArr != null)
    {
      CharacterDataCriteria characterDataCriteria = new CharacterDataCriteria();
      characterDataCriteria.createCriteria().andCharaIdIn(Arrays.asList(charaIdArr));
      return this.characterDataDAO.selectByExample(characterDataCriteria);
    }
    return null;
  }

  public List listCharaDataById(List dataIdL)
  {
    CharacterDataCriteria characterDataCriteria = new CharacterDataCriteria();
    characterDataCriteria.createCriteria().andDataIdIn(dataIdL);
    return this.characterDataDAO.selectByExample(characterDataCriteria);
  }

  public List listCharaDataByEntity(Integer metaId, String entityId)
  {
    return this.characterDataDAO.getByEntity(metaId, entityId);
  }

  public Integer createCharaData(CharacterData record)
  {
    Integer id = this.characterDataDAO.insert(record);
    record.setDataId(id);
    if (record.getIsshared().equals(Integer.valueOf(1)))
    {
      Sid sidm = new PrincipalSid(getUsername());
      addPermissionCharacterData(record, sidm, BasePermission.ADMINISTRATION);
      Sid sida = new GrantedAuthoritySid("ROLE_ADMIN");
      addPermissionCharacterData(record, sida, BasePermission.ADMINISTRATION);
      Sid sid = new GrantedAuthoritySid("ROLE_AUTHENTICATEDCOMMON");
      addPermissionCharacterData(record, sid, BasePermission.READ);
    }
    else
    {
      Sid sidm = new PrincipalSid(getUsername());
      addPermissionCharacterData(record, sidm, BasePermission.ADMINISTRATION);
      Sid sida = new GrantedAuthoritySid("ROLE_ADMIN");
      addPermissionCharacterData(record, sida, BasePermission.ADMINISTRATION);
      Sid sid = new GrantedAuthoritySid("ROLE_AUTHENTICATEDCOMMON");
      addPermissionCharacterData(record, sid, BasePermission.WRITE);
    }
    return id;
  }

  public Integer createCharaDataWithEntity(CharacterData record, Integer metaId, String entityId)
  {
    Integer id = this.characterDataDAO.insert(record);
    record.setDataId(id);

    REntityCharaData rEntityCharaData = new REntityCharaData();
    rEntityCharaData.setMetaId(metaId);
    rEntityCharaData.setEntityId(entityId);
    rEntityCharaData.setCharaId(record.getCharaId());
    rEntityCharaData.setDataId(id);
    this.rentityCharaDataDAO.insert(rEntityCharaData);
    if (record.getIsshared().equals(Integer.valueOf(1)))
    {
      Sid sidm = new PrincipalSid(getUsername());
      addPermissionCharacterData(record, sidm, BasePermission.ADMINISTRATION);
      Sid sida = new GrantedAuthoritySid("ROLE_ADMIN");
      addPermissionCharacterData(record, sida, BasePermission.ADMINISTRATION);
      Sid sid = new GrantedAuthoritySid("ROLE_AUTHENTICATEDCOMMON");
      addPermissionCharacterData(record, sid, BasePermission.READ);
    }
    else
    {
      Sid sidm = new PrincipalSid(getUsername());
      addPermissionCharacterData(record, sidm, BasePermission.ADMINISTRATION);
      Sid sida = new GrantedAuthoritySid("ROLE_ADMIN");
      addPermissionCharacterData(record, sida, BasePermission.ADMINISTRATION);
      Sid sid = new GrantedAuthoritySid("ROLE_AUTHENTICATEDCOMMON");
      addPermissionCharacterData(record, sid, BasePermission.WRITE);
    }
    return id;
  }

  public void delCharaData(CharacterData record)
  {
    Integer dataId = record.getDataId();


    REntityCharaDataCriteria rEntityCharaDataCriteria = new REntityCharaDataCriteria();
    REntityCharaDataCriteria.Criteria recc = rEntityCharaDataCriteria.createCriteria();

    recc.andDataIdEqualTo(dataId);
    this.rentityCharaDataDAO.deleteByExample(rEntityCharaDataCriteria);


    ObjectIdentity oid = new ObjectIdentityImpl(CharacterData.class, dataId);
    this.mutableAclService.deleteAcl(oid, false);


    this.characterDataDAO.deleteByPrimaryKey(dataId);
  }

  public void updateCharaData(CharacterData record)
  {
    if (record.getIsshared().equals(Integer.valueOf(1)))
    {
      Sid sid = new GrantedAuthoritySid("ROLE_AUTHENTICATEDCOMMON");
      updatePermissionCharacterData(record, sid, BasePermission.READ);
    }
    else
    {
      Sid sid = new GrantedAuthoritySid("ROLE_AUTHENTICATEDCOMMON");
      updatePermissionCharacterData(record, sid, BasePermission.WRITE);
    }
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

  public void createREntityCharaData(Integer metaId, String entityId, Integer charaId, Integer[] dataIdArr)
  {
    if ((entityId != null) && (dataIdArr != null)) {
      for (Integer dataId : dataIdArr)
      {
        REntityCharaData record = new REntityCharaData();
        record.setMetaId(metaId);
        record.setEntityId(entityId);
        record.setCharaId(charaId);
        record.setDataId(dataId);
        this.rentityCharaDataDAO.insert(record);
      }
    }
  }

  public void createREntityCharaData(Integer metaId, String entityId, Integer[][] charaArr)
  {
    if ((entityId != null) && (charaArr != null)) {
      for (Integer[] chara : charaArr)
      {
        REntityCharaData record = new REntityCharaData();
        record.setMetaId(metaId);
        record.setEntityId(entityId);
        record.setCharaId(chara[0]);
        record.setDataId(chara[1]);
        this.rentityCharaDataDAO.insert(record);
      }
    }
  }

  public void delREntityCharaData(Integer metaId, String entityId, Integer[] dataIdArr)
  {
    if ((entityId != null) && (dataIdArr != null))
    {
      REntityCharaDataCriteria rEntityCharaDataCriteria = new REntityCharaDataCriteria();
      REntityCharaDataCriteria.Criteria c = rEntityCharaDataCriteria.createCriteria();
      c.andMetaIdEqualTo(metaId);
      c.andEntityIdEqualTo(entityId);
      c.andDataIdIn(Arrays.asList(dataIdArr));

      this.rentityCharaDataDAO.deleteByExample(rEntityCharaDataCriteria);
    }
  }

  public void updateREntityCharaDataArr(Integer metaId, String entityId, Integer[][] charaArr)
  {
    if ((metaId != null) && (entityId != null) && (charaArr == null))
    {
      List<CharacterData> characterDataList = listCharaDataByEntity(metaId, entityId);
      if (characterDataList != null)
      {
        List<Integer> dataIdL = new ArrayList(characterDataList.size());
        for (CharacterData cd : characterDataList) {
          dataIdL.add(cd.getDataId());
        }
        REntityCharaDataCriteria rEntityCharaDataCriteria = new REntityCharaDataCriteria();
        REntityCharaDataCriteria.Criteria c = rEntityCharaDataCriteria.createCriteria();
        c.andMetaIdEqualTo(metaId);
        c.andEntityIdEqualTo(entityId);
        c.andDataIdIn(dataIdL);
        this.rentityCharaDataDAO.deleteByExample(rEntityCharaDataCriteria);
      }
    }
    else if ((metaId != null) && (entityId != null) && (charaArr != null))
    {
      List<CharacterData> characterDataList = listCharaDataByEntity(metaId, entityId);
      if (characterDataList == null)
      {
        Integer[][] arrayOfInteger;
        for (int i = 0; i < charaArr.length; i++)
        {
          Integer[] chara = charaArr[i];

          REntityCharaData record = new REntityCharaData();
          record.setMetaId(metaId);
          record.setEntityId(entityId);
          record.setCharaId(chara[0]);
          record.setDataId(chara[1]);
          this.rentityCharaDataDAO.insert(record);
        }
      }
      else if (characterDataList != null)
      {
        for (Integer[] chara : charaArr)
        {
          boolean voter = false;
          for (CharacterData cd : characterDataList) {
            if (chara[1].equals(cd.getDataId()))
            {
              voter = true;
              characterDataList.remove(cd);
              break;
            }
          }
          if (!voter)
          {
            REntityCharaData record = new REntityCharaData();
            record.setMetaId(metaId);
            record.setEntityId(entityId);
            record.setCharaId(chara[0]);
            record.setDataId(chara[1]);
            this.rentityCharaDataDAO.insert(record);
          }
        }
        if (characterDataList.size() > 0)
        {
          List<Integer> dataIdL = new ArrayList(characterDataList.size());
          for (CharacterData cd : characterDataList) {
            dataIdL.add(cd.getDataId());
          }
          REntityCharaDataCriteria rEntityCharaDataCriteria = new REntityCharaDataCriteria();
          REntityCharaDataCriteria.Criteria c = rEntityCharaDataCriteria.createCriteria();
          c.andMetaIdEqualTo(metaId);
          c.andEntityIdEqualTo(entityId);
          c.andDataIdIn(dataIdL);
          this.rentityCharaDataDAO.deleteByExample(rEntityCharaDataCriteria);
        }
      }
    }
  }

  public void updateREntityCharaData(Integer metaId, String entityId, Integer charaId, Integer[] dataIdArr)
  {
    if ((metaId != null) && (entityId != null) && (charaId != null) && (dataIdArr == null))
    {
      List<CharacterData> characterDataList = listCharaDataByEntity(metaId, entityId);
      if (characterDataList != null)
      {
        List<Integer> dataIdL = new ArrayList(characterDataList.size());
        for (CharacterData cd : characterDataList) {
          dataIdL.add(cd.getDataId());
        }
        REntityCharaDataCriteria rEntityCharaDataCriteria = new REntityCharaDataCriteria();
        REntityCharaDataCriteria.Criteria c = rEntityCharaDataCriteria.createCriteria();
        c.andMetaIdEqualTo(metaId);
        c.andEntityIdEqualTo(entityId);
        c.andDataIdIn(dataIdL);
        this.rentityCharaDataDAO.deleteByExample(rEntityCharaDataCriteria);
      }
    }
    else if ((metaId != null) && (entityId != null) && (charaId != null) && (dataIdArr != null))
    {
      List<CharacterData> characterDataList = listCharaDataByEntity(metaId, entityId);
      if (characterDataList == null)
      {
        Integer[] arrayOfInteger;
        for (int i = 0; i < dataIdArr.length; i++)
        {
          Integer dataId = dataIdArr[i];

          REntityCharaData record = new REntityCharaData();
          record.setMetaId(metaId);
          record.setEntityId(entityId);
          record.setCharaId(charaId);
          record.setDataId(dataId);
          this.rentityCharaDataDAO.insert(record);
        }
      }
      else if (characterDataList != null)
      {
        for (Integer dataId : dataIdArr)
        {
          boolean voter = false;
          for (CharacterData cd : characterDataList) {
            if (dataId.equals(cd.getDataId()))
            {
              voter = true;
              characterDataList.remove(cd);
              break;
            }
          }
          if (!voter)
          {
            REntityCharaData record = new REntityCharaData();
            record.setMetaId(metaId);
            record.setEntityId(entityId);
            record.setCharaId(charaId);
            record.setDataId(dataId);
            this.rentityCharaDataDAO.insert(record);
          }
        }
        if (characterDataList.size() > 0)
        {
          List<Integer> dataIdL = new ArrayList(characterDataList.size());
          for (CharacterData cd : characterDataList) {
            dataIdL.add(cd.getDataId());
          }
          REntityCharaDataCriteria rEntityCharaDataCriteria = new REntityCharaDataCriteria();
          REntityCharaDataCriteria.Criteria c = rEntityCharaDataCriteria.createCriteria();
          c.andMetaIdEqualTo(metaId);
          c.andEntityIdEqualTo(entityId);
          c.andDataIdIn(dataIdL);
          this.rentityCharaDataDAO.deleteByExample(rEntityCharaDataCriteria);
        }
      }
    }
  }

  public List listREntityCharaData(Integer metaId, String entityId)
  {
    Integer userId = getUserId();

    return this.rentityCharaDataDAO.selectByUser(metaId, entityId, userId);
  }

  public void updateREntityCharaData(Integer metaId, String entityId, Integer[][] charaArr)
  {
    if (charaArr == null)
    {
      List<REntityCharaData> recdList = listREntityCharaData(metaId, entityId);
      for (REntityCharaData recd : recdList) {
        this.rentityCharaDataDAO.deleteByPrimaryKey(recd.getId());
      }
    }
    else if (charaArr != null)
    {
      List<REntityCharaData> recdList = listREntityCharaData(metaId, entityId);
      for (REntityCharaData recd : recdList) {
        this.rentityCharaDataDAO.deleteByPrimaryKey(recd.getId());
      }
      if (recdList == null)
      {
        for (Integer[] chara : charaArr)
        {
          REntityCharaData record = new REntityCharaData();
          record.setMetaId(metaId);
          record.setEntityId(entityId);
          record.setCharaId(chara[0]);
          record.setDataId(chara[1]);
          this.rentityCharaDataDAO.insert(record);
        }
      }
      else if (recdList != null)
      {
        for (Integer[] chara : charaArr)
        {
          boolean voter = false;
          for (REntityCharaData recd : recdList) {
            if ((chara[0].equals(recd.getCharaId())) && (chara[1].equals(recd.getDataId())))
            {
              voter = true;
              recdList.remove(recd);
              break;
            }
          }
          if (!voter)
          {
            REntityCharaData record = new REntityCharaData();
            record.setMetaId(metaId);
            record.setEntityId(entityId);
            record.setCharaId(chara[0]);
            record.setDataId(chara[1]);
            this.rentityCharaDataDAO.insert(record);
          }
        }
        for (REntityCharaData recd : recdList) {
          this.rentityCharaDataDAO.deleteByPrimaryKey(recd.getId());
        }
      }
    }
  }

  public List findAllDb()
  {
    return this.metaDAO.selectByExample(new MetaCriteria());
  }

  public List findDb(List metaIdlist)
  {
    MetaCriteria metaCriteria = new MetaCriteria();
    MetaCriteria.Criteria c = metaCriteria.createCriteria();
    c.andMetaIdIn(metaIdlist);
    return this.metaDAO.selectByExample(metaCriteria);
  }

  public List findMeta(Integer metaId)
  {
    return this.metaDAO.getMetaByIdWithCashAttrDef(metaId);
  }

  public Meta findMetaByName(String entityName)
  {
    MetaCriteria criteria = new MetaCriteria();
    MetaCriteria.Criteria nameCriteria = criteria.createCriteria();
    nameCriteria.andEntityNameEqualTo(entityName);
    List l = this.metaDAO.selectByExample(criteria);
    if (l.size() == 0) {
      return null;
    }
    return (Meta)l.get(0);
  }

  public List findMetaPerm()
  {
    return this.metaDAO.getMetaByIdWithCashAttrDefArr();
  }

  public List findPicAttrDef(Integer metaId)
  {
    PicAttrDefCriteria picCriteria = new PicAttrDefCriteria();
    PicAttrDefCriteria.Criteria c = picCriteria.createCriteria();
    c.andMetaIdEqualTo(metaId);
    return this.picAttrDefDAO.selectByExample(picCriteria);
  }

  public Integer createPicAttrDef(PicAttrDef record)
  {
    Integer id = this.picAttrDefDAO.insert(record);
    this.dynEntityDAO.addColumn(record.getMetaId(), id, "p_");
    this.dynEntityDAO.addColumn("t_his_entity_" + record.getMetaId(), "p_" + id);
    return id;
  }

  public void delPicAttrDef(Integer attrId)
  {
    PicAttrDef picDef = this.picAttrDefDAO.selectByPrimaryKey(attrId);
    this.picAttrDefDAO.deleteByPrimaryKey(attrId);
    this.dynEntityDAO.dropColumn(picDef.getMetaId(), attrId, "p_");
    this.dynEntityDAO.dropColumn("t_his_entity_" + picDef.getMetaId(), "p_" + attrId);
  }

  public void updatePicAttrDef(PicAttrDef record)
  {
    this.picAttrDefDAO.updateByPrimaryKey(record);
  }

  public List findDescAttrDef(Integer metaId)
  {
    DescAttrDefCriteria descCriteria = new DescAttrDefCriteria();
    DescAttrDefCriteria.Criteria c = descCriteria.createCriteria();
    c.andMetaIdEqualTo(metaId);
    return this.descAttrDefDAO.selectByExampleWithoutBLOBs(descCriteria);
  }

  public Integer createDescAttrDef(DescAttrDef record)
  {
    Integer id = this.descAttrDefDAO.insert(record);
    this.dynEntityDAO.addColumn(record.getMetaId(), id, "d_");
    this.dynEntityDAO.addColumn("t_his_entity_" + record.getMetaId(), "d_" + id);
    return id;
  }

  public void delDescAttrDef(Integer attrId)
  {
    DescAttrDef descAttrDef = this.descAttrDefDAO.selectByPrimaryKey(attrId);
    this.descAttrDefDAO.deleteByPrimaryKey(attrId);
    this.dynEntityDAO.dropColumn(descAttrDef.getMetaId(), attrId, "d_");
    this.dynEntityDAO.dropColumn("t_his_entity_" + descAttrDef.getMetaId(), "d_" + attrId);
  }

  public void updateDescAttrDef(DescAttrDef record)
  {
    this.descAttrDefDAO.updateByPrimaryKeyWithoutBLOBs(record);
  }

  public List findHierarchyAttrDef(Integer metaId)
  {
    HierarchyAttrDefCriteria hierarchyCriteria = new HierarchyAttrDefCriteria();
    HierarchyAttrDefCriteria.Criteria c = hierarchyCriteria.createCriteria();
    c.andMetaIdEqualTo(metaId);
    List<HierarchyAttrDef> hdefList = this.hierarchyAttrDefDAO.selectByExample(hierarchyCriteria);
    for (HierarchyAttrDef hefDef : hdefList) {
      hefDef.setTreeValue(loadHieraAttrValue(hefDef.getAttrId()));
    }
    return hdefList;
  }

  public HierarchyAttrDef getHierarchyAttrDefById(Integer attrId)
  {
    HierarchyAttrDefCriteria hierarchyCriteria = new HierarchyAttrDefCriteria();
    HierarchyAttrDefCriteria.Criteria c = hierarchyCriteria.createCriteria();
    c.andAttrIdEqualTo(attrId);
    List<HierarchyAttrDef> hdefList = this.hierarchyAttrDefDAO.selectByExample(hierarchyCriteria);
    for (HierarchyAttrDef hefDef : hdefList) {
      hefDef.setTreeValue(loadHieraAttrValue(hefDef.getAttrId()));
    }
    if (hdefList != null) {
      return (HierarchyAttrDef)hdefList.get(0);
    }
    return null;
  }

  public void initHierFieldDisplayValue(HierarchyField hField)
  {
    if ((hField.getValue() == null) || (hField.getValue().equals(""))) {
      return;
    }
    HierarchyAttrDefCriteria hierarchyCriteria = new HierarchyAttrDefCriteria();
    HierarchyAttrDefCriteria.Criteria c = hierarchyCriteria.createCriteria();

    c.andAttrIdEqualTo(new Integer(hField.getColumnName().replaceFirst("h_", "")));
    List<HierarchyAttrDef> hdefList = this.hierarchyAttrDefDAO.selectByExample(hierarchyCriteria);
    for (HierarchyAttrDef hefDef : hdefList)
    {
      ArrayList valueList = new ArrayList();
      String[] sarr = hField.getValue().split(",");
      for (String s : sarr) {
        valueList.add(s);
      }
      HierarchyAttrValueCriteria criteria = new HierarchyAttrValueCriteria();
      criteria.createCriteria()
        .andAttrIdEqualTo(hefDef.getAttrId())
        .andValueIdIn(valueList);

      String displayStr = "";
      List l = this.hierarchyAttrValueDAO.selectByExample(criteria);
      for (int j = 0; j < l.size(); j++)
      {
        HierarchyAttrValue value = (HierarchyAttrValue)l.get(j);
        displayStr = displayStr + getHierPathString(hefDef.getAttrId(), value.getValueId());
        if (j != l.size() - 1) {
          displayStr = displayStr + "<br/>";
        }
      }
      hField.setDisplayVaule(displayStr);
    }
  }

  public String getHierPathString(Integer attrId, String valueid)
  {
    HierarchyAttrValueCriteria criteria = new HierarchyAttrValueCriteria();
    criteria.createCriteria()
      .andAttrIdEqualTo(attrId)
      .andValueIdEqualTo(valueid);

    String displayStr = "";
    List l = this.hierarchyAttrValueDAO.selectByExample(criteria);
    HierarchyAttrValue hValue = (HierarchyAttrValue)l.get(0);
    while (hValue.getParentId() != null)
    {
      if (displayStr.equals("")) {
        displayStr = hValue.getContent();
      } else {
        displayStr = hValue.getContent() + "\\" + displayStr;
      }
      criteria = new HierarchyAttrValueCriteria();
      criteria.createCriteria()
        .andAttrIdEqualTo(attrId)
        .andValueIdEqualTo(hValue.getParentId());
      l = this.hierarchyAttrValueDAO.selectByExample(criteria);
      hValue = (HierarchyAttrValue)l.get(0);
    }
    return displayStr;
  }

  public void initCharaDisplayvalue(CharacterField cField)
  {
    if ((cField.getValue() == null) || (cField.getValue().equals(""))) {
      return;
    }
    ArrayList<Integer> valueList = new ArrayList();
    String[] sarr = cField.getValue().split(",");
    for (String s : sarr) {
      valueList.add(new Integer(s));
    }
    CharacterDataCriteria characterDataCriteria = new CharacterDataCriteria();
    CharacterDataCriteria.Criteria cdc = characterDataCriteria.createCriteria();
    cdc.andCharaIdEqualTo(new Integer(cField.getColumnName().replaceFirst("c_", "")))
      .andDataIdIn(valueList);

    Object l = this.characterDataDAO.selectByExample(characterDataCriteria);
    String displayStr = "";
    for (int j = 0; j < ((List)l).size(); j++)
    {
      CharacterData value = (CharacterData)((List)l).get(j);
      displayStr = displayStr + value + " ";
      if (j % 5 == 0) {
        displayStr = displayStr + "<br/>";
      }
    }
    cField.setDisplayValue(displayStr);
  }

  public Integer createHierarchyAttrDef(HierarchyAttrDef record)
  {
    Integer id = this.hierarchyAttrDefDAO.insert(record);
    this.dynEntityDAO.addColumn(record.getMetaId(), id, "h_");
    this.dynEntityDAO.addColumn("t_his_entity_" + record.getMetaId(), "h_" + id);
    String hierarchyAttrName = record.getAttrName();
    if ((hierarchyAttrName != null) && (!"".equals(hierarchyAttrName)))
    {
      HierarchyAttrValue hierValue = new HierarchyAttrValue();

      hierValue.setAttrId(id);
      hierValue.setContent(hierarchyAttrName + "根");
      hierValue.setLevelNum(Integer.valueOf(0));
      hierValue.setMetaId(record.getMetaId());
      hierValue.setParentId(null);
      hierValue.setValueId(UUID.randomUUID().toString());
      this.hierarchyAttrValueDAO.insert(hierValue);
    }
    return id;
  }

  public void delHierarchyAttrDef(Integer attrId)
  {
    HierarchyAttrDef hierAttrDef = this.hierarchyAttrDefDAO.selectByPrimaryKey(attrId);

    HierarchyAttrValueCriteria hCriteria = new HierarchyAttrValueCriteria();
    hCriteria.createCriteria().andAttrIdEqualTo(attrId);
    this.hierarchyAttrValueDAO.deleteByExample(hCriteria);
    this.hierarchyAttrDefDAO.deleteByPrimaryKey(attrId);
    this.dynEntityDAO.dropColumn(hierAttrDef.getMetaId(), attrId, "h_");
    this.dynEntityDAO.dropColumn("t_his_entity_" + hierAttrDef.getMetaId(), "h_" + attrId);
  }

  public void updateHierarchyAttrDef(HierarchyAttrDef record)
  {
    this.hierarchyAttrDefDAO.updateByPrimaryKey(record);
  }

  public List findCharacterAttrDef(Integer metaId)
  {
    CharacterAttrDefCriteria characterCriteria = new CharacterAttrDefCriteria();
    CharacterAttrDefCriteria.Criteria c = characterCriteria.createCriteria();
    c.andMetaIdEqualTo(metaId);
    return this.characterAttrDefDAO.selectByExample(characterCriteria);
  }

  public Integer createCharacterAttrDef(CharacterAttrDef record)
  {
    Integer id = this.characterAttrDefDAO.insert(record);
    this.dynEntityDAO.addColumn(record.getMetaId(), id, "c_");
    return id;
  }

  public void delCharacterAttrDef(Integer attrId)
  {
    CharacterAttrDef attrDef = this.characterAttrDefDAO.selectByPrimaryKey(attrId);
    this.characterAttrDefDAO.deleteByPrimaryKey(attrId);
    this.dynEntityDAO.dropColumn(attrDef.getMetaId(), attrId, "c_");
  }

  public void updateCharacterAttrDef(CharacterAttrDef record)
  {
    this.characterAttrDefDAO.updateByPrimaryKey(record);
  }

  public void delMeta(Integer metaId)
  {
    DescAttrDefCriteria defCriteria = new DescAttrDefCriteria();
    defCriteria.createCriteria().andMetaIdEqualTo(metaId);
    this.descAttrDefDAO.deleteByExample(defCriteria);

    PicAttrDefCriteria picCriteria = new PicAttrDefCriteria();
    picCriteria.createCriteria().andMetaIdEqualTo(metaId);
    this.picAttrDefDAO.deleteByExample(picCriteria);

    HierarchyAttrDefCriteria hierCriteria = new HierarchyAttrDefCriteria();
    hierCriteria.createCriteria().andMetaIdEqualTo(metaId);
    this.hierarchyAttrDefDAO.deleteByExample(hierCriteria);

    CharacterAttrDefCriteria charaCriteria = new CharacterAttrDefCriteria();
    charaCriteria.createCriteria().andMetaIdEqualTo(metaId);
    this.characterAttrDefDAO.deleteByExample(charaCriteria);

    this.metaDAO.deleteByPrimaryKey(metaId);

    this.dynEntityDAO.dropTable(metaId);
  }

  public void delMeta(Meta meta)
  {
    DescAttrDefCriteria defCriteria = new DescAttrDefCriteria();
    defCriteria.createCriteria().andMetaIdEqualTo(meta.getMetaId());
    this.descAttrDefDAO.deleteByExample(defCriteria);


    PicAttrDefCriteria picCriteria = new PicAttrDefCriteria();
    picCriteria.createCriteria().andMetaIdEqualTo(meta.getMetaId());
    this.picAttrDefDAO.deleteByExample(picCriteria);


    HierarchyAttrDefCriteria hierCriteria = new HierarchyAttrDefCriteria();
    hierCriteria.createCriteria().andMetaIdEqualTo(meta.getMetaId());
    List<HierarchyAttrDef> hierDefs = this.hierarchyAttrDefDAO.selectByExample(hierCriteria);
    if (!hierDefs.isEmpty()) {
      for (HierarchyAttrDef hierDef : hierDefs)
      {
        HierarchyAttrValueCriteria hiervalueCriteria = new HierarchyAttrValueCriteria();
        hiervalueCriteria.createCriteria().andAttrIdEqualTo(hierDef.getAttrId());
        this.hierarchyAttrValueDAO.deleteByExample(hiervalueCriteria);
      }
    }
    this.hierarchyAttrDefDAO.deleteByExample(hierCriteria);

    CharacterAttrDefCriteria charaCriteria = new CharacterAttrDefCriteria();
    charaCriteria.createCriteria().andMetaIdEqualTo(meta.getMetaId());
    this.characterAttrDefDAO.deleteByExample(charaCriteria);

    RMetaCharaCriteria rMetaCharaCriteria = new RMetaCharaCriteria();
    RMetaCharaCriteria.Criteria rmc = rMetaCharaCriteria.createCriteria();
    rmc.andMetaIdEqualTo(meta.getMetaId());
    this.rmetaCharaDAO.deleteByExample(rMetaCharaCriteria);

    REntityCharaDataCriteria rEntityCharaDataCriteria = new REntityCharaDataCriteria();
    REntityCharaDataCriteria.Criteria recc = rEntityCharaDataCriteria.createCriteria();
    recc.andMetaIdEqualTo(meta.getMetaId());
    this.rentityCharaDataDAO.deleteByExample(rEntityCharaDataCriteria);



    ObjectIdentity oid = new ObjectIdentityImpl(Meta.class, meta.getId());
    this.mutableAclService.deleteAcl(oid, false);

    RoleMetaCriteria roleMetaCriteria = new RoleMetaCriteria();
    RoleMetaCriteria.Criteria c = roleMetaCriteria.createCriteria();
    c.andMetaIdEqualTo(meta.getMetaId());
    this.roleMetaDAO.deleteByExample(roleMetaCriteria);

    this.metaDAO.deleteByPrimaryKey(meta.getMetaId());

    this.dynEntityDAO.dropTable(meta.getMetaId());
  }

  public void updateMeta(Meta record)
  {
    Meta m = this.metaDAO.selectByPrimaryKey(record.getMetaId());
    record.setLastUpdateTime(new Date());
    record.setCreateTime(m.getCreateTime());
    this.metaDAO.updateByPrimaryKey(record);
  }

  private Map getJsTree(HierarchyAttrValue parent)
  {
    HierarchyAttrValueCriteria criteria = new HierarchyAttrValueCriteria();
    criteria.createCriteria()
      .andParentIdEqualTo(parent.getValueId());
    criteria.setOrderByClause(" CONVERT(content USING GBK) ");
    List<HierarchyAttrValue> l = this.hierarchyAttrValueDAO.selectByExample(criteria);
    Map res = new HashMap();
    l.size();







    ArrayList childrenList = new ArrayList();
    for (HierarchyAttrValue h : l) {
      childrenList.add(getJsTree(h));
    }
    Map attr = new HashMap();


    attr.put("id", "node_" + parent.getValueId());
    attr.put("rel", "folder");
    res.put("attr", attr);
    res.put("data", parent.getContent());
    res.put("children", childrenList);
    return res;
  }

  private Map getTree(HierarchyAttrValue parent)
  {
    HierarchyAttrValueCriteria criteria = new HierarchyAttrValueCriteria();
    criteria.createCriteria()
      .andParentIdEqualTo(parent.getValueId());

    List<HierarchyAttrValue> l = this.hierarchyAttrValueDAO.selectByExample(criteria);
    Map res = new HashMap();
    if (l.size() > 0) {
      res.put("leaf", Boolean.valueOf(false));
    } else {
      res.put("leaf", Boolean.valueOf(false));
    }
    ArrayList childrenList = new ArrayList();
    for (HierarchyAttrValue h : l) {
      childrenList.add(getTree(h));
    }
    res.put("children", childrenList);
    res.put("text", parent.getContent());
    res.put("title", parent.getContent());
    res.put("checked", Boolean.valueOf(false));
    res.put("id", "node_" + parent.getValueId());

    return res;
  }

  public HierarchyAttrValue saveNode(String parNodeId, String nodeName)
  {
    HierarchyAttrValue pNode = this.hierarchyAttrValueDAO.selectByPrimaryKey(parNodeId);
    pNode.getLevelNum();
    HierarchyAttrValue newNode = new HierarchyAttrValue();
    newNode.setAttrId(pNode.getAttrId());
    newNode.setContent(nodeName);
    newNode.setLevelNum(Integer.valueOf(pNode.getLevelNum().intValue() + 1));
    newNode.setMetaId(pNode.getMetaId());
    newNode.setParentId(parNodeId);
    newNode.setValueId(UUID.randomUUID().toString());
    this.hierarchyAttrValueDAO.insert(newNode);

    return newNode;
  }

  public void delNode(String nodeId)
  {
    removeNode(nodeId);
  }

  private void removeNode(String nodeId)
  {
    HierarchyAttrValueCriteria hCriteria = new HierarchyAttrValueCriteria();
    hCriteria.createCriteria().andParentIdEqualTo(nodeId);
    List l = this.hierarchyAttrValueDAO.selectByExample(hCriteria);
    for (int i = 0; i < l.size(); i++) {
      removeNode(((HierarchyAttrValue)l.get(i)).getValueId());
    }
    REntityHierarchyDataCriteria criteria = new REntityHierarchyDataCriteria();
    REntityHierarchyDataCriteria.Criteria rehc = criteria.createCriteria();
    rehc.andValueIdEqualTo(nodeId);
    this.rentityHierarchyDataDAO.deleteByExample(criteria);

    this.hierarchyAttrValueDAO.deleteByPrimaryKey(nodeId);
  }

  public HierarchyAttrValue editNode(String nodeId, String nodeName)
  {
    HierarchyAttrValue node = this.hierarchyAttrValueDAO.selectByPrimaryKey(nodeId);
    node.setContent(nodeName);
    this.hierarchyAttrValueDAO.updateByPrimaryKey(node);
    return node;
  }

  public Map loadHieraAttrValue(Integer attrId)
  {
    HierarchyAttrValueCriteria criteria = new HierarchyAttrValueCriteria();
    criteria.createCriteria()
      .andAttrIdEqualTo(attrId);

    List<HierarchyAttrValue> l = this.hierarchyAttrValueDAO.selectByExample(criteria);
    HierarchyAttrValue root = new HierarchyAttrValue();
    HierarchyAttrValueNode rootNode = null;
    Map<String, HierarchyAttrValueNode> map = new HashMap();
    for (HierarchyAttrValue h : l)
    {
      if (h.getParentId() == null) {
        root.setValueId(h.getValueId());
      } else if (h.getParentId().equals("")) {
        root.setValueId(h.getValueId());
      }
      HierarchyAttrValueNode node = new HierarchyAttrValueNode();
      node.setHierarchyAttrValue(h);

      map.put(node.getHierarchyAttrValue().getValueId(), node);
    }
    for (HierarchyAttrValueNode n : map.values())
    {
      HierarchyAttrValueNode node = (HierarchyAttrValueNode)map.get(n.getHierarchyAttrValue().getParentId());
      if (node != null) {
        node.addChild(n);
      }
    }
    rootNode = (HierarchyAttrValueNode)map.get(root.getValueId());
    return getTree(rootNode);
  }

  private Map getTree(HierarchyAttrValueNode parent)
  {
    Map res = new HashMap();
    try
    {
      if (parent == null) {
        return res;
      }
      res.put("text", parent.getHierarchyAttrValue().getContent());
      res.put("title", parent.getHierarchyAttrValue().getContent());
      res.put("checked", Boolean.valueOf(false));
      res.put("id", "node_" + parent.getHierarchyAttrValue().getValueId());

      List<HierarchyAttrValueNode> cNodeList = parent.getCNodes();
      if (cNodeList == null) {
        cNodeList = new ArrayList();
      }
      if (cNodeList.size() > 0) {
        res.put("leaf", Boolean.valueOf(false));
      } else {
        res.put("leaf", Boolean.valueOf(false));
      }
      ArrayList childrenList = new ArrayList();
      for (HierarchyAttrValueNode h : cNodeList) {
        childrenList.add(getTree(h));
      }
      res.put("children", childrenList);
    }
    catch (Throwable t)
    {
      logger.error(t.toString());
      t.printStackTrace();
    }
    return res;
  }

  public Map loadHieraAttrJsTree(Integer attrId)
  {
    HierarchyAttrValueCriteria criteria = new HierarchyAttrValueCriteria();
    criteria.createCriteria()
      .andAttrIdEqualTo(attrId);

    criteria.setOrderByClause("level_num, CONVERT(content USING GBK) ");

    List<HierarchyAttrValue> l = this.hierarchyAttrValueDAO.selectByExample(criteria);
    HierarchyAttrValue root = new HierarchyAttrValue();
    HierarchyAttrValueNode rootNode = null;
    LinkedHashMap<String, HierarchyAttrValueNode> map = new LinkedHashMap();
    for (HierarchyAttrValue h : l)
    {
      if (h.getParentId() == null) {
        root.setValueId(h.getValueId());
      } else if (h.getParentId().equals("")) {
        root.setValueId(h.getValueId());
      }
      HierarchyAttrValueNode node = new HierarchyAttrValueNode();
      node.setHierarchyAttrValue(h);

      map.put(node.getHierarchyAttrValue().getValueId(), node);
    }
    for (HierarchyAttrValueNode n : map.values())
    {
      HierarchyAttrValueNode node = (HierarchyAttrValueNode)map.get(n.getHierarchyAttrValue().getParentId());
      if (node != null) {
        node.addChild(n);
      }
    }
    rootNode = (HierarchyAttrValueNode)map.get(root.getValueId());
    return getJsTree(rootNode);
  }

  private Map getJsTree(HierarchyAttrValueNode parent)
  {
    Map res = new HashMap();
    try
    {
      if (parent == null) {
        return res;
      }
      Map attr = new HashMap();
      attr.put("id", "node_" + parent.getHierarchyAttrValue().getValueId());
      attr.put("rel", "folder");
      res.put("attr", attr);
      res.put("data", parent.getHierarchyAttrValue().getContent());


      List<HierarchyAttrValueNode> cNodeList = parent.getCNodes();
      if (cNodeList == null) {
        cNodeList = new ArrayList();
      }
      cNodeList.size();







      ArrayList childrenList = new ArrayList();
      for (HierarchyAttrValueNode h : cNodeList) {
        childrenList.add(getJsTree(h));
      }
      res.put("children", childrenList);
    }
    catch (Throwable t)
    {
      logger.error(t.toString());
      t.printStackTrace();
    }
    return res;
  }

  public Map loadHieraAttrJsTreeByValueId(String valueId)
  {
    HierarchyAttrValueCriteria criteria = new HierarchyAttrValueCriteria();
    criteria.createCriteria()
      .andValueIdEqualTo(valueId);

    List l = this.hierarchyAttrValueDAO.selectByExample(criteria);
    assert (l.size() == 1);
    HierarchyAttrValue root = null;
    if (l.size() > 0)
    {
      root = (HierarchyAttrValue)l.get(0);
      return getJsTree(root);
    }
    return null;
  }

  public Integer createMeta(String entityName, String entityDesc, String[] picAttrs, String[] descAttrs, String[] hierarchyAttrs, String[] characterAttrs, Integer[] metaProperty)
  {
    List picAttrsList = new ArrayList();
    List descAttrsList = new ArrayList();
    List charaAttrsList = new ArrayList();
    List hieraAttrsList = new ArrayList();

    MetaCriteria metaCriteria = new MetaCriteria();
    metaCriteria.createCriteria().andEntityNameEqualTo(entityName);
    if (this.metaDAO.selectByExample(metaCriteria).size() > 0) {
      throw new NameExistException();
    }
    Meta meta = new Meta();
    meta.setEntityName(entityName);
    meta.setEntityDesc(entityDesc);

    meta.setCreateTime(new Date());
    meta.setLastUpdateTime(new Date());
    Integer metaId = this.metaDAO.insert(meta);
    meta.setMetaId(metaId);
    String str3;
    PicAttrDef picDef;
    if (picAttrs != null)
    {
      for (int i = 0; i < picAttrs.length; i++)
      {
        String picAttrName = picAttrs[i];
        if ((picAttrName != null) && (!"".equals(picAttrName)))
        {
          picDef = new PicAttrDef();
          picDef.setAttrName(picAttrName);
          picDef.setMetaId(metaId);
          picAttrsList.add(this.picAttrDefDAO.insert(picDef));
        }
      }
    }
    String descAttrName;
    if (descAttrs != null)
    {
      for (int i = 0; i < descAttrs.length; i++)
      {
        descAttrName = descAttrs[i];
        if ((descAttrName != null) && (!"".equals(descAttrName)))
        {
          DescAttrDef descDef = new DescAttrDef();
          descDef.setAttrName(descAttrName);
          descDef.setMetaId(metaId);
          descDef.setPropertyId(metaProperty[i]);
          descAttrsList.add(this.descAttrDefDAO.insert(descDef));
    //      i++;
        }
      }
    }
    HierarchyAttrDef hierarchyDef;
    Integer attrId;
    HierarchyAttrValue hierValue;
    if (hierarchyAttrs != null)
    {
      for (int i = 0; i < hierarchyAttrs.length; i++)
      {
        String hierarchyAttrName = hierarchyAttrs[i];
        if ((hierarchyAttrName != null) && (!"".equals(hierarchyAttrName)))
        {
          hierarchyDef = new HierarchyAttrDef();
          hierarchyDef.setAttrName(hierarchyAttrName);
          hierarchyDef.setMetaId(metaId);
          attrId = this.hierarchyAttrDefDAO.insert(hierarchyDef);
          hieraAttrsList.add(attrId);
          hierValue = new HierarchyAttrValue();

          hierValue.setAttrId(attrId);
          hierValue.setContent(hierarchyAttrName + "根");
          hierValue.setLevelNum(Integer.valueOf(0));
          hierValue.setMetaId(metaId);
          hierValue.setParentId(null);
          hierValue.setValueId(UUID.randomUUID().toString());
          this.hierarchyAttrValueDAO.insert(hierValue);
        }
      }
    }
    ArrayList charaList = new ArrayList();
    String charaIdstr;
    if (characterAttrs != null)
    {
      Integer[] charaIdArr = new Integer[characterAttrs.length];

      for (int i = 0; i < characterAttrs.length; i++)
      {
        charaIdstr = characterAttrs[i];

        charaList.add(charaIdstr);
        if ((charaIdstr != null) && (!"".equals(charaIdstr)))
        {
          charaIdArr[i] = Integer.valueOf(Integer.parseInt(charaIdstr));
        //  i++;
        }
      }
      newRMetaCharaWithoutHis(metaId, charaIdArr);
    }
    this.dynEntityDAO.createEntityTable(metaId, picAttrsList, descAttrsList, hieraAttrsList, charaAttrsList);
    this.dynEntityDAO.createHistoryTable(metaId, picAttrsList, descAttrsList, hieraAttrsList, charaList);
    for (GrantedAuthority authority : getRoles())
    {
      Sid sid = new GrantedAuthoritySid(authority.getAuthority());
      addPermission(meta, sid, BasePermission.ADMINISTRATION);
    }
    return metaId;
  }

  public void setMutableAclService(MutableAclService mutableAclService)
  {
    this.mutableAclService = mutableAclService;
  }

  public void addPermission(Meta meta, Sid recipient, Permission permission)
  {
    ObjectIdentity oid = new ObjectIdentityImpl(Meta.class, meta.getMetaId());
    MutableAcl acl;
    try
    {
      acl = (MutableAcl)this.mutableAclService.readAclById(oid);
    }
    catch (NotFoundException nfe)
    {
      acl = this.mutableAclService.createAcl(oid);
    }
    acl.insertAce(acl.getEntries().length, permission, recipient, true);
    this.mutableAclService.updateAcl(acl);
  }

  protected Integer getUserId()
  {
    User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return user.getUserId();
  }

  protected String getUsername()
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if ((auth.getPrincipal() instanceof UserDetails)) {
      return ((UserDetails)auth.getPrincipal()).getUsername();
    }
    return auth.getPrincipal().toString();
  }

  protected List getUsernameS()
  {
    return this.userDAO.getUserByIdWithCashRole(null);
  }

  protected GrantedAuthority[] getRoles()
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if ((auth.getPrincipal() instanceof UserDetails)) {
      return ((UserDetails)auth.getPrincipal()).getAuthorities();
    }
    return null;
  }

  public void deletePermission(Meta meta, Sid recipient, Permission permission)
  {
    ObjectIdentity oid = new ObjectIdentityImpl(Meta.class, meta.getMetaId());
    MutableAcl acl = (MutableAcl)this.mutableAclService.readAclById(oid);


    AccessControlEntry[] entries = acl.getEntries();
    for (int i = 0; i < entries.length; i++) {
      if ((entries[i].getSid().equals(recipient)) && (entries[i].getPermission().equals(permission))) {
        acl.deleteAce(i);
      }
    }
    this.mutableAclService.updateAcl(acl);
  }

  public void addPermissionCharacterDef(CharacterDef characterDef, Sid recipient, Permission permission)
  {
    ObjectIdentity oid = new ObjectIdentityImpl(CharacterDef.class, characterDef.getCharaId());
    MutableAcl acl;
    try
    {
      acl = (MutableAcl)this.mutableAclService.readAclById(oid);
    }
    catch (NotFoundException nfe)
    {
      acl = this.mutableAclService.createAcl(oid);
    }
    acl.insertAce(acl.getEntries().length, permission, recipient, true);
    this.mutableAclService.updateAcl(acl);
  }

  public void updatePermissionCharacterDef(CharacterDef characterDef, List<Sid> sidL, Permission permission)
  {
    ObjectIdentity oid = new ObjectIdentityImpl(CharacterDef.class, characterDef.getCharaId());
    MutableAcl acl = (MutableAcl)this.mutableAclService.readAclById(oid);


    AccessControlEntry[] entries = acl.getEntries();
    for (int i = 0; i < entries.length; i++) {
      if ((!sidL.contains(entries[i].getSid())) && (!entries[i].getPermission().equals(permission))) {
        acl.updateAce(i, permission);
      }
    }
    this.mutableAclService.updateAcl(acl);
  }

  public void updatePermissionCharacterDef(CharacterDef characterDef, Sid recipient, Permission permission)
  {
    ObjectIdentity oid = new ObjectIdentityImpl(CharacterDef.class, characterDef.getCharaId());
    MutableAcl acl = (MutableAcl)this.mutableAclService.readAclById(oid);


    AccessControlEntry[] entries = acl.getEntries();
    for (int i = 0; i < entries.length; i++) {
      if ((entries[i].getSid().equals(recipient)) && (!entries[i].getPermission().equals(permission))) {
        acl.updateAce(i, permission);
      }
    }
    this.mutableAclService.updateAcl(acl);
  }

  public void deletePermissionCharacterDef(CharacterDef characterDef, Sid recipient, Permission permission)
  {
    ObjectIdentity oid = new ObjectIdentityImpl(CharacterDef.class, characterDef.getCharaId());
    MutableAcl acl = (MutableAcl)this.mutableAclService.readAclById(oid);


    AccessControlEntry[] entries = acl.getEntries();
    for (int i = 0; i < entries.length; i++) {
      if ((entries[i].getSid().equals(recipient)) && (entries[i].getPermission().equals(permission))) {
        acl.deleteAce(i);
      }
    }
    this.mutableAclService.updateAcl(acl);
  }

  public void addPermissionCharacterData(CharacterData characterData, Sid recipient, Permission permission)
  {
    ObjectIdentity oid = new ObjectIdentityImpl(CharacterData.class, characterData.getDataId());
    MutableAcl acl;
    try
    {
      acl = (MutableAcl)this.mutableAclService.readAclById(oid);
    }
    catch (NotFoundException nfe)
    {
      acl = this.mutableAclService.createAcl(oid);
    }
    acl.insertAce(acl.getEntries().length, permission, recipient, true);
    this.mutableAclService.updateAcl(acl);
  }

  public void updatePermissionCharacterData(CharacterData characterData, List<Sid> sidL, Permission permission)
  {
    ObjectIdentity oid = new ObjectIdentityImpl(CharacterData.class, characterData.getDataId());
    MutableAcl acl = (MutableAcl)this.mutableAclService.readAclById(oid);


    AccessControlEntry[] entries = acl.getEntries();
    for (int i = 0; i < entries.length; i++) {
      if ((!sidL.contains(entries[i].getSid())) && (!entries[i].getPermission().equals(permission))) {
        acl.updateAce(i, permission);
      }
    }
    this.mutableAclService.updateAcl(acl);
  }

  public void updatePermissionCharacterData(CharacterData characterData, Sid recipient, Permission permission)
  {
    ObjectIdentity oid = new ObjectIdentityImpl(CharacterData.class, characterData.getDataId());
    MutableAcl acl = (MutableAcl)this.mutableAclService.readAclById(oid);


    AccessControlEntry[] entries = acl.getEntries();
    for (int i = 0; i < entries.length; i++) {
      if ((entries[i].getSid().equals(recipient)) && (!entries[i].getPermission().equals(permission))) {
        acl.updateAce(i, permission);
      }
    }
    this.mutableAclService.updateAcl(acl);
  }

  public void deletePermissionCharacterData(CharacterData characterData, Sid recipient, Permission permission)
  {
    ObjectIdentity oid = new ObjectIdentityImpl(CharacterData.class, characterData.getDataId());
    MutableAcl acl = (MutableAcl)this.mutableAclService.readAclById(oid);


    AccessControlEntry[] entries = acl.getEntries();
    for (int i = 0; i < entries.length; i++) {
      if ((entries[i].getSid().equals(recipient)) && (entries[i].getPermission().equals(permission))) {
        acl.deleteAce(i);
      }
    }
    this.mutableAclService.updateAcl(acl);
  }

  public List listMetaProperty()
  {
    return this.metaPropertyDAO.selectByExample(new MetaPropertyCriteria());
  }

  public Integer createMetaProperty(MetaProperty record)
  {
    Integer id = this.metaPropertyDAO.insert(record);
    record.setPropertyId(id);
    return id;
  }

  public void delMetaProperty(Integer metaPropertyId)
  {
    this.metaPropertyDAO.deleteByPrimaryKey(metaPropertyId);
  }

  public void updateMetaProperty(MetaProperty record)
  {
    this.metaPropertyDAO.updateByPrimaryKey(record);
  }

  public void updateEnableMetaProperty(Integer propertyId)
  {
    MetaProperty metaProperty = this.metaPropertyDAO.selectByPrimaryKey(propertyId);
    if (metaProperty != null) {
      this.metaPropertyDAO.updateEnable(metaProperty);
    }
  }
}
