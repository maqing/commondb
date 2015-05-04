package com.commondb.db.dao;

import java.util.List;
import java.util.Map;

public abstract interface DynamicEntityDAO
{
  public abstract List selectByMetaId(Integer paramInteger);
  
  public abstract void createEntityTable(Integer paramInteger, List paramList1, List paramList2, List paramList3, List paramList4);
  
  public abstract String createEntity(Integer paramInteger, Map paramMap);
  
  public abstract List selectColumnData(Integer paramInteger, String paramString);
  
  public abstract String updateEntity(Integer paramInteger, String paramString, Map paramMap);
  
  public abstract Map selectEntityByPK(Integer paramInteger, String paramString);
  
  public abstract Map deleteEntityByPK(Integer paramInteger, String paramString);
  
  public abstract void addColumn(String paramString1, String paramString2);
  
  public abstract void dropColumn(String paramString1, String paramString2);
  
  public abstract void dropColumn(Integer paramInteger1, Integer paramInteger2, String paramString);
  
  public abstract void addColumn(Integer paramInteger1, Integer paramInteger2, String paramString);
  
  public abstract void dropTable(Integer paramInteger);
  
  public abstract List selectByChara(Integer paramInteger, Integer[][] paramArrayOfInteger);
  
  public abstract List selectByHierarchy(Integer paramInteger, String[][] paramArrayOfString);
  
  public abstract List selectHierarchyByEntity(Integer paramInteger, String paramString);
  
  public abstract List dynSelect(String paramString1, String paramString2, String paramString3);
  
  public abstract List dynSelect(String paramString);
  
  public abstract void createHistoryTable(Integer paramInteger, List paramList1, List paramList2, List paramList3, List paramList4);
  
  public abstract void dynDML(String paramString);
  
  public abstract String createSnapshot(Integer paramInteger, Map paramMap);
  
  public abstract void createEntityTrigger(int paramInt);
  
  public abstract String createJournal(String paramString1, String paramString2, String paramString3);
}
