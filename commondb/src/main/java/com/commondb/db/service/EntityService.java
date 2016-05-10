package com.commondb.db.service;

import com.commondb.db.bo.CharacterData;
import com.commondb.db.bo.OperationBox;

import java.util.List;
import java.util.Map;

public abstract interface EntityService
{
  public abstract String createJournal(String paramString1, String paramString2, List paramList);
  
  public abstract String remind(Integer paramInteger1, Integer paramInteger2, String paramString1, String paramString2, String paramString3);
  
  public abstract void delRemind(String paramString);
  
  public abstract List getUserRemind(Integer paramInteger);
  
  public abstract String createEntity(Integer paramInteger, Map paramMap)
    throws Exception;
  
  public abstract List findEntityBymetaId(Integer paramInteger);
  
  public abstract List selectColumnData(Integer paramInteger, String paramString);
  
  public abstract List getPicList();
  
  public abstract void delEntity(Integer paramInteger, String paramString)
    throws Exception;
  
  public abstract String importEntity(Integer paramInteger, String paramString, Map paramMap)
    throws Exception;
  
  public abstract String updateEntity(Integer paramInteger, String paramString, Map paramMap)
    throws Exception;
  
  public abstract void saveREntity(Integer paramInteger1, String paramString, Integer paramInteger2, String[] paramArrayOfString);
  
  public abstract void saveREntity(Integer paramInteger1, String paramString1, Integer paramInteger2, String paramString2, String paramString3);
  
  public abstract void updateREntity(Integer paramInteger1, String paramString, Integer paramInteger2, String[] paramArrayOfString);
  
  public abstract void delREntity(Integer paramInteger1, String paramString, Integer paramInteger2);
  
  public abstract List findEntityByChara(Integer paramInteger, Integer[][] paramArrayOfInteger);
  
  public abstract List findEntityByHierarchy(Integer paramInteger, String[][] paramArrayOfString);
  
  public abstract List findHierarchyByEntity(Integer paramInteger, String paramString);
  
  public abstract List listCharaData(Integer paramInteger);
  
  public abstract Integer createCharaData(CharacterData paramCharacterData);
  
  public abstract void delCharaData(Integer paramInteger);
  
  public abstract void updateCharaData(CharacterData paramCharacterData);
  
  public abstract void updateShareCharaData(Integer paramInteger);
  
  public abstract void updateEnableCharaData(Integer paramInteger);
  
  public abstract void createREntityCharaData(String paramString, Integer[] paramArrayOfInteger);
  
  public abstract void delREntityCharaData(String paramString, Integer[] paramArrayOfInteger);
  
  public abstract void updateREntityCharaData(String paramString, Integer[] paramArrayOfInteger);
  
  public abstract List listREntity(Integer paramInteger, String paramString);
  
  public abstract List dynSelect(String paramString1, String paramString2, String paramString3);
  
  public abstract String addOperationRec(OperationBox paramOperationBox);
  
  public abstract void delOperationRec(String paramString);
  
  public abstract List getUserOperationRec(Integer paramInteger, String paramString);
  
  public abstract List getUserOperationRec(Integer paramInteger);
  
  public abstract List listRAttachment(Integer paramInteger, String paramString);

}
