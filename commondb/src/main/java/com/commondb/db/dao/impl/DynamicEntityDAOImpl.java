package com.commondb.db.dao.impl;

import com.commondb.db.dao.DynamicEntityDAO;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class DynamicEntityDAOImpl
  extends SqlMapClientDaoSupport
  implements DynamicEntityDAO
{
  public void dropTable(Integer metaId)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_entity_" + metaId);
    getSqlMapClientTemplate().update("dyn_entity.dynamicDrop", parameter);
    

    HashMap parameterHis = new HashMap();
    parameterHis.put("tableName", "t_his_entity_" + metaId);
    getSqlMapClientTemplate().update("dyn_entity.dynamicDrop", parameterHis);
  }
  
  public void addColumn(Integer metaId, Integer attrId, String attrPrefix)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_entity_" + metaId);
    parameter.put("columnName", attrPrefix + attrId);
    
    getSqlMapClientTemplate().update("dyn_entity.dynamicAddColumn", parameter);
    
    System.out.print("====add column====");
  }
  
  public void addColumn(String tableName, String columnName)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", tableName);
    parameter.put("columnName", columnName);
    
    getSqlMapClientTemplate().update("dyn_entity.dynamicAddColumn", parameter);
    
    System.out.print("====add column====");
  }
  
  public void dropColumn(String tableName, String columnName)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", tableName);
    parameter.put("columnName", columnName);
    

    getSqlMapClientTemplate().update("dyn_entity.dynamicDropColumn", parameter);
  }
  
  public void dropColumn(Integer metaId, Integer attrId, String attrPrefix)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_entity_" + metaId);
    parameter.put("columnName", attrPrefix + attrId);
    
    getSqlMapClientTemplate().update("dyn_entity.dynamicDropColumn", parameter);
  }
  
  public List dynSelect(String sql)
  {
    HashMap parameter = new HashMap();
    parameter.put("sql", sql);
    List<HashMap> list = getSqlMapClientTemplate().queryForList("dyn_entity.dynamicSelectSql", parameter);
    return list;
  }
  
  public void dynDML(String sql)
  {
    HashMap parameter = new HashMap();
    parameter.put("sql", sql);
    getSqlMapClientTemplate().update("dyn_entity.dynamicDML", parameter);
  }
  
  public List selectByMetaId(Integer metaId)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_entity_" + metaId);
    
    List<HashMap> list = getSqlMapClientTemplate().queryForList("dyn_entity.dynamicSelectAll", parameter);
    return list;
  }
  
  public List selectColumnData(Integer metaId, String columnName)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_entity_" + metaId);
    parameter.put("column", "distinct " + columnName);
    parameter.put("whereclause", columnName + " != ''");
    
    List list = getSqlMapClientTemplate().queryForList("dyn_entity.dynamicSelectSingleColumn", parameter);
    return list;
  }
  
  public Map selectEntityByPK(Integer metaId, String id)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_entity_" + metaId);
    parameter.put("id", id);
    
    return (Map)getSqlMapClientTemplate().queryForObject("dyn_entity.dynamicSelectByPK", parameter);
  }
  
  public Map deleteEntityByPK(Integer metaId, String id)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_entity_" + metaId);
    parameter.put("id", id);
    
    getSqlMapClientTemplate().delete("dyn_entity.dynamicDeleteByPK", parameter);
    return null;
  }
  
  public String updateEntity(Integer metaId, String id, Map valueMap)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_entity_" + metaId);
    
    String columns = "";
    String values = "";
    Set<String> keys = valueMap.keySet();
    for (String key : keys) {
      columns = columns + ", " + key + "='" + valueMap.get(key) + "' ";
    }
    if (valueMap.get("update_time") == null) {
      columns = columns + ", update_time=now() ";
    }
    columns = columns.replaceFirst(",", "");
    

    parameter.put("columns", columns);
    parameter.put("id", id);
    
    getSqlMapClientTemplate().update("dyn_entity.dynamicUpdate", parameter);
    
    return id;
  }
  
  public String createEntity(Integer metaId, Map valueMap)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_entity_" + metaId);
    
    String columns = "";
    
    String values = "";
    String uuid = "";
    if (valueMap.get("id") == null)
    {
      columns = ",id";
      uuid = UUID.randomUUID().toString();
      values = ",'" + uuid + "'";
    }
    else
    {
      uuid = valueMap.get("id").toString();
    }
    Set<String> keys = valueMap.keySet();
    for (String key : keys)
    {
      columns = columns + "," + key;
      if (valueMap.get(key) == null) {
        values = values + ", null ";
      } else {
        values = values + ",'" + new StringBuilder().append(valueMap.get(key)).toString().replaceAll("(\\'|\\\\)", "\\\\$1") + "'";
      }
    }
    if (valueMap.get("update_time") == null)
    {
      columns = columns + ", update_time";
      values = values + ",now()";
    }
    if (valueMap.get("create_time") == null)
    {
      columns = columns + ", create_time";
      values = values + ",now()";
    }
    columns = columns.replaceFirst(",", "");
    values = values.replaceFirst(",", "");
    
    parameter.put("columns", columns);
    parameter.put("values", values);
    
    getSqlMapClientTemplate().update("dyn_entity.dynamicInsert", parameter);
    
    return uuid;
  }
  
  public String createSnapshot(Integer metaId, Map valueMap)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_his_entity_" + metaId);
    


    String columns = "";
    String values = "";
    
    Set<String> keys = valueMap.keySet();
    for (String key : keys)
    {
      columns = columns + "," + key;
      if (valueMap.get(key) == null) {
        values = values + ",null";
      } else {
        values = values + ",'" + new StringBuilder().append(valueMap.get(key)).toString().replaceAll("(\\'|\\\\)", "\\\\$1") + "'";
      }
    }
    columns = columns.replaceFirst(",", "");
    values = values.replaceFirst(",", "");
    parameter.put("columns", columns);
    parameter.put("values", values);
    
    getSqlMapClientTemplate().update("dyn_entity.dynamicInsert", parameter);
    
    return "";
  }
  
  public void createEntityTable(Integer metaId, List picAttrsList, List descAttrsList, List hieraAttrsList, List charaAttrsList)
  {
    HashMap parameter = new HashMap();
    
    parameter.put("tableName", "t_entity_" + metaId);
    String columns = "";
    columns = columns + getColumnsString("p_", picAttrsList);
    columns = columns + getColumnsString("d_", descAttrsList);
    


    parameter.put("columns", columns);
    
    parameter.put("metaId", metaId.toString());
    getSqlMapClientTemplate().update("dyn_entity.dynamicCreateTable", parameter);
    getSqlMapClientTemplate().update("dyn_entity.dynamicCreateInsertTrigger", parameter);
    getSqlMapClientTemplate().update("dyn_entity.dynamicCreateUpdateTrigger", parameter);
    getSqlMapClientTemplate().update("dyn_entity.dynamicCreateDelTriggerNew", parameter);
  }
  
  public void createEntityTrigger(int metaId)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_entity_" + metaId);
    
    getSqlMapClientTemplate().update("dyn_entity.dynamicCreateInsertTrigger", parameter);
    getSqlMapClientTemplate().update("dyn_entity.dynamicCreateUpdateTrigger", parameter);
    getSqlMapClientTemplate().update("dyn_entity.dynamicCreateDelTrigger", parameter);
  }
  
  public void createHistoryTable(Integer metaId, List picAttrsList, List descAttrsList, List hieraAttrsList, List charaAttrsList)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_his_entity_" + metaId);
    String columns = "";
    columns = columns + getColumnsString("p_", picAttrsList);
    columns = columns + getColumnsString("d_", descAttrsList);
    columns = columns + getColumnsString("h_", hieraAttrsList);
    columns = columns + getColumnsString("c_", charaAttrsList);
    columns = columns + " relation varchar(3000), update_user varchar(300), update_time datetime, id varchar(36),  create_user varchar(300), create_time datetime,";
    
    parameter.put("columns", columns);
    getSqlMapClientTemplate().update("dyn_entity.dynamicCreateHisTable", 
      parameter);
  }
  
  private String getColumnsString(String prefix, List attrList)
  {
    String res = "";
    for (int i = 0; i < attrList.size(); i++) {
      res = res + prefix + attrList.get(i) + " varchar(500),";
    }
    return res;
  }
  
  public List selectByChara(Integer metaId, Integer[][] charaArr)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_entity_" + metaId);
    parameter.put("metaId", metaId);
    String sChara = "";
    if (charaArr != null)
    {
      for (Integer[] chara : charaArr) {
        sChara = sChara + " or (chara_id=" + chara[0] + " and data_id=" + chara[1] + ")";
      }
      sChara = "(chara_id=" + charaArr[0][0] + " and data_id=" + charaArr[0][1] + ")" + sChara;
    }
    parameter.put("chara", sChara);
    
    List<HashMap> list = getSqlMapClientTemplate().queryForList("dyn_entity.selectByChara", parameter);
    
    return list;
  }
  
  public List selectByHierarchy(Integer metaId, String[][] hierarchyArr)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_entity_" + metaId);
    parameter.put("metaId", metaId);
    String sHierarchy = "";
    if (hierarchyArr != null)
    {
      for (String[] hierarchy : hierarchyArr) {
        sHierarchy = sHierarchy + " or (attr_id=" + hierarchy[0] + " and value_id='" + hierarchy[1] + "')";
      }
      sHierarchy = "(attr_id=" + hierarchyArr[0][0] + " and value_id='" + hierarchyArr[0][1] + "')" + sHierarchy;
    }
    parameter.put("hierarchy", sHierarchy);
    
    List<HashMap> list = getSqlMapClientTemplate().queryForList("dyn_entity.selectByHierarchy", parameter);
    
    return list;
  }
  
  public List selectHierarchyByEntity(Integer metaId, String entityId)
  {
    HashMap parameter = new HashMap();
    parameter.put("entityId", entityId);
    parameter.put("metaId", metaId);
    List<HashMap> list = getSqlMapClientTemplate().queryForList("dyn_entity.selectHierarchyByEntity", parameter);
    
    return list;
  }
  
  public List dynSelect(String columns, String fromStr, String whereStr)
  {
    HashMap parameter = new HashMap();
    parameter.put("columns", columns);
    parameter.put("tableName", fromStr);
    parameter.put("whereclause", whereStr);
    
    List<HashMap> list = getSqlMapClientTemplate().queryForList("dyn_entity.dynamicSelectColumns", parameter);
    
    return list;
  }
  
  public String createJournal(String userName, String module, String message)
  {
    HashMap parameter = new HashMap();
    parameter.put("tableName", "t_entity_14");
    
    String journalID = UUID.randomUUID().toString();
    


    String columns = "id,d_82,d_83,update_time,update_user";
    String values = "'" + journalID + "',now(), '" + message + "', now(),'" + userName + "'";
    

    parameter.put("columns", columns);
    parameter.put("values", values);
    
    getSqlMapClientTemplate().update("dyn_entity.dynamicInsert", parameter);
    
    return journalID;
  }
}
