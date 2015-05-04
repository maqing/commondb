package com.commondb.db.service;

import com.commondb.app.common.meta.CharacterField;
import com.commondb.app.common.meta.HierarchyField;
import com.commondb.db.bo.CharacterAttrDef;
import com.commondb.db.bo.CharacterData;
import com.commondb.db.bo.CharacterDef;
import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.bo.HierarchyAttrDef;
import com.commondb.db.bo.HierarchyAttrValue;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.MetaProperty;
import com.commondb.db.bo.PicAttrDef;
import java.util.List;
import java.util.Map;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.sid.Sid;

public abstract interface MetaService
{
  public abstract Integer createMeta(String paramString1, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String[] paramArrayOfString4, Integer[] paramArrayOfInteger);
  
  public abstract CharacterDef getCharaDefById(Integer paramInteger);
  
  public abstract void delMeta(Integer paramInteger);
  
  public abstract void updateMeta(Meta paramMeta);
  
  public abstract List findAllDb();
  
  public abstract List findDb(List paramList);
  
  public abstract List findCharacterAttrDef(Integer paramInteger);
  
  public abstract List findHierarchyAttrDef(Integer paramInteger);
  
  public abstract List findDescAttrDef(Integer paramInteger);
  
  public abstract List findPicAttrDef(Integer paramInteger);
  
  public abstract void initHierFieldDisplayValue(HierarchyField paramHierarchyField);
  
  public abstract void initCharaDisplayvalue(CharacterField paramCharacterField);
  
  public abstract Meta findMetaByName(String paramString);
  
  public abstract HierarchyAttrValue saveNode(String paramString1, String paramString2);
  
  public abstract Integer createPicAttrDef(PicAttrDef paramPicAttrDef);
  
  public abstract void delPicAttrDef(Integer paramInteger);
  
  public abstract void updatePicAttrDef(PicAttrDef paramPicAttrDef);
  
  public abstract Integer createDescAttrDef(DescAttrDef paramDescAttrDef);
  
  public abstract void delDescAttrDef(Integer paramInteger);
  
  public abstract void updateDescAttrDef(DescAttrDef paramDescAttrDef);
  
  public abstract Integer createHierarchyAttrDef(HierarchyAttrDef paramHierarchyAttrDef);
  
  public abstract void delHierarchyAttrDef(Integer paramInteger);
  
  public abstract void updateHierarchyAttrDef(HierarchyAttrDef paramHierarchyAttrDef);
  
  public abstract Integer createCharacterAttrDef(CharacterAttrDef paramCharacterAttrDef);
  
  public abstract void delCharacterAttrDef(Integer paramInteger);
  
  public abstract void updateCharacterAttrDef(CharacterAttrDef paramCharacterAttrDef);
  
  public abstract List findMeta(Integer paramInteger);
  
  public abstract List findMetaPerm();
  
  public abstract void addPermission(Meta paramMeta, Sid paramSid, Permission paramPermission);
  
  public abstract void deletePermission(Meta paramMeta, Sid paramSid, Permission paramPermission);
  
  public abstract void delMeta(Meta paramMeta);
  
  public abstract HierarchyAttrValue editNode(String paramString1, String paramString2);
  
  public abstract void delNode(String paramString);
  
  public abstract Integer createCharaDef(CharacterDef paramCharacterDef);
  
  public abstract Integer createCharaDefWithMeta(CharacterDef paramCharacterDef, Integer paramInteger);
  
  public abstract void delCharaDef(CharacterDef paramCharacterDef);
  
  public abstract void updateCharaDef(CharacterDef paramCharacterDef);
  
  public abstract void updateShareCharaDef(Integer paramInteger);
  
  public abstract void updateEnableCharaDef(Integer paramInteger);
  
  public abstract void updateCheckMultipleCharaDef(Integer paramInteger);
  
  public abstract List listCharaDefById(List paramList);
  
  public abstract List listCharaDef();
  
  public abstract List listCharaDefByMeta(Integer paramInteger);
  
  public abstract void createRMetaChara(Integer paramInteger, Integer[] paramArrayOfInteger);
  
  public abstract void delRMetaChara(Integer paramInteger, Integer[] paramArrayOfInteger);
  
  public abstract void updateRMetaChara(Integer paramInteger, Integer[] paramArrayOfInteger);
  
  public abstract List listRMetaChara(Integer paramInteger);
  
  public abstract Integer createCharaData(CharacterData paramCharacterData);
  
  public abstract Integer createCharaDataWithEntity(CharacterData paramCharacterData, Integer paramInteger, String paramString);
  
  public abstract void delCharaData(CharacterData paramCharacterData);
  
  public abstract void updateCharaData(CharacterData paramCharacterData);
  
  public abstract void updateShareCharaData(Integer paramInteger);
  
  public abstract void updateEnableCharaData(Integer paramInteger);
  
  public abstract List listCharaDataById(List paramList);
  
  public abstract List listCharaData(Integer paramInteger);
  
  public abstract List listCharaData(Integer[] paramArrayOfInteger);
  
  public abstract List listCharaDataByEntity(Integer paramInteger, String paramString);
  
  public abstract void createREntityCharaData(Integer paramInteger1, String paramString, Integer paramInteger2, Integer[] paramArrayOfInteger);
  
  public abstract void updateREntityCharaDataArr(Integer paramInteger, String paramString, Integer[][] paramArrayOfInteger);
  
  public abstract void delREntityCharaData(Integer paramInteger, String paramString, Integer[] paramArrayOfInteger);
  
  public abstract void updateREntityCharaData(Integer paramInteger1, String paramString, Integer paramInteger2, Integer[] paramArrayOfInteger);
  
  public abstract List listREntityCharaData(Integer paramInteger, String paramString);
  
  public abstract void addPermissionCharacterDef(CharacterDef paramCharacterDef, Sid paramSid, Permission paramPermission);
  
  public abstract void updatePermissionCharacterDef(CharacterDef paramCharacterDef, List<Sid> paramList, Permission paramPermission);
  
  public abstract void deletePermissionCharacterDef(CharacterDef paramCharacterDef, Sid paramSid, Permission paramPermission);
  
  public abstract void addPermissionCharacterData(CharacterData paramCharacterData, Sid paramSid, Permission paramPermission);
  
  public abstract void updatePermissionCharacterData(CharacterData paramCharacterData, List<Sid> paramList, Permission paramPermission);
  
  public abstract void deletePermissionCharacterData(CharacterData paramCharacterData, Sid paramSid, Permission paramPermission);
  
  public abstract HierarchyAttrDef getHierarchyAttrDefById(Integer paramInteger);
  
  public abstract Map loadHieraAttrJsTreeByValueId(String paramString);
  
  public abstract Map loadHieraAttrJsTree(Integer paramInteger);
  
  public abstract Integer createMetaProperty(MetaProperty paramMetaProperty);
  
  public abstract void delMetaProperty(Integer paramInteger);
  
  public abstract void updateMetaProperty(MetaProperty paramMetaProperty);
  
  public abstract void updateEnableMetaProperty(Integer paramInteger);
  
  public abstract List listMetaProperty();
  
  public abstract String getHierPathString(Integer paramInteger, String paramString);
}
