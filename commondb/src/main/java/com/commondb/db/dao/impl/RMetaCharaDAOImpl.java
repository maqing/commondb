package com.commondb.db.dao.impl;

import com.commondb.db.bo.RMetaChara;
import com.commondb.db.bo.RMetaCharaCriteria;
import com.commondb.db.dao.RMetaCharaDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RMetaCharaDAOImpl
  extends SqlMapClientDaoSupport
  implements RMetaCharaDAO
{
  public int countByExample(RMetaCharaCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("r_meta_chara.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(RMetaCharaCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("r_meta_chara.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer id)
  {
    RMetaChara key = new RMetaChara();
    key.setId(id);
    int rows = getSqlMapClientTemplate().delete("r_meta_chara.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(RMetaChara record)
  {
    getSqlMapClientTemplate().insert("r_meta_chara.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(RMetaChara record)
  {
    getSqlMapClientTemplate().insert("r_meta_chara.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(RMetaCharaCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("r_meta_chara.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public RMetaChara selectByPrimaryKey(Integer id)
  {
    RMetaChara key = new RMetaChara();
    key.setId(id);
    RMetaChara record = (RMetaChara)getSqlMapClientTemplate().queryForObject("r_meta_chara.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(RMetaChara record, RMetaCharaCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_meta_chara.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(RMetaChara record, RMetaCharaCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_meta_chara.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(RMetaChara record)
  {
    int rows = getSqlMapClientTemplate().update("r_meta_chara.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(RMetaChara record)
  {
    int rows = getSqlMapClientTemplate().update("r_meta_chara.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends RMetaCharaCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, RMetaCharaCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
  
  public List selectByUser(Integer metaId, Integer userId)
  {
    Map map = new HashMap();
    map.put("metaId", metaId);
    map.put("userId", userId);
    List list = getSqlMapClientTemplate().queryForList("r_meta_chara.selectByUser", map);
    return list;
  }
}
