package com.commondb.apply.dao.impl;

import com.commondb.apply.bo.MetaUser;
import com.commondb.apply.bo.MetaUserCriteria;
import com.commondb.apply.dao.MetaUserDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class MetaUserDAOImpl
  extends SqlMapClientDaoSupport
  implements MetaUserDAO
{
  public int countByExample(MetaUserCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("r_meta_user.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(MetaUserCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("r_meta_user.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer id)
  {
    MetaUser key = new MetaUser();
    key.setId(id);
    int rows = getSqlMapClientTemplate().delete("r_meta_user.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(MetaUser record)
  {
    getSqlMapClientTemplate().insert("r_meta_user.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(MetaUser record)
  {
    getSqlMapClientTemplate().insert("r_meta_user.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(MetaUserCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("r_meta_user.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public MetaUser selectByPrimaryKey(Integer id)
  {
    MetaUser key = new MetaUser();
    key.setId(id);
    MetaUser record = (MetaUser)getSqlMapClientTemplate().queryForObject("r_meta_user.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(MetaUser record, MetaUserCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_meta_user.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(MetaUser record, MetaUserCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_meta_user.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(MetaUser record)
  {
    int rows = getSqlMapClientTemplate().update("r_meta_user.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(MetaUser record)
  {
    int rows = getSqlMapClientTemplate().update("r_meta_user.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends MetaUserCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, MetaUserCriteria example)
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
