package com.commondb.db.dao.impl;

import com.commondb.db.bo.PicAttrDef;
import com.commondb.db.bo.PicAttrDefCriteria;
import com.commondb.db.dao.PicAttrDefDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class PicAttrDefDAOImpl
  extends SqlMapClientDaoSupport
  implements PicAttrDefDAO
{
  public int countByExample(PicAttrDefCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_meta_pic_attr_def.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(PicAttrDefCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_meta_pic_attr_def.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer attrId)
  {
    PicAttrDef key = new PicAttrDef();
    key.setAttrId(attrId);
    int rows = getSqlMapClientTemplate().delete("t_meta_pic_attr_def.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public Integer insert(PicAttrDef record)
  {
    return (Integer)getSqlMapClientTemplate().insert("t_meta_pic_attr_def.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(PicAttrDef record)
  {
    getSqlMapClientTemplate().insert("t_meta_pic_attr_def.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(PicAttrDefCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_meta_pic_attr_def.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public PicAttrDef selectByPrimaryKey(Integer attrId)
  {
    PicAttrDef key = new PicAttrDef();
    key.setAttrId(attrId);
    PicAttrDef record = (PicAttrDef)getSqlMapClientTemplate().queryForObject("t_meta_pic_attr_def.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(PicAttrDef record, PicAttrDefCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_pic_attr_def.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(PicAttrDef record, PicAttrDefCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_meta_pic_attr_def.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(PicAttrDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_pic_attr_def.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(PicAttrDef record)
  {
    int rows = getSqlMapClientTemplate().update("t_meta_pic_attr_def.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends PicAttrDefCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, PicAttrDefCriteria example)
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
