package com.commondb.db.dao.impl;

import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.bo.DescAttrDefCriteria;
import com.commondb.db.dao.DescAttrDefDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class DescAttrDefDAOImpl
  extends SqlMapClientDaoSupport
  implements DescAttrDefDAO
{
  public int countByExample(DescAttrDefCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_meta_desc_attr_def.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(DescAttrDefCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_meta_desc_attr_def.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer attrId)
  {
    DescAttrDef key = new DescAttrDef();
    key.setAttrId(attrId);
    int rows = getSqlMapClientTemplate().delete("t_meta_desc_attr_def.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public Integer insert(DescAttrDef record)
  {
    return (Integer)getSqlMapClientTemplate().insert("t_meta_desc_attr_def.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(DescAttrDef record)
  {
    getSqlMapClientTemplate().insert("t_meta_desc_attr_def.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExampleWithBLOBs(DescAttrDefCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_meta_desc_attr_def.ibatorgenerated_selectByExampleWithBLOBs", example);
    return list;
  }
  
  public List selectByExampleWithoutBLOBs(DescAttrDefCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_meta_desc_attr_def.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public DescAttrDef selectByPrimaryKey(Integer attrId)
  {
    DescAttrDef key = new DescAttrDef();
    key.setAttrId(attrId);
    DescAttrDef record = (DescAttrDef)getSqlMapClientTemplate().queryForObject("t_meta_desc_attr_def.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(DescAttrDef record, DescAttrDefCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_desc_attr_def.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExampleWithBLOBs(DescAttrDef record, DescAttrDefCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_desc_attr_def.ibatorgenerated_updateByExampleWithBLOBs", parms);
    return rows;
  }
  
  public int updateByExampleWithoutBLOBs(DescAttrDef record, DescAttrDefCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_desc_attr_def.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(DescAttrDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_desc_attr_def.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKeyWithBLOBs(DescAttrDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_desc_attr_def.ibatorgenerated_updateByPrimaryKeyWithBLOBs", record);
    return rows;
  }
  
  public int updateByPrimaryKeyWithoutBLOBs(DescAttrDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_desc_attr_def.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends DescAttrDefCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, DescAttrDefCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
}
