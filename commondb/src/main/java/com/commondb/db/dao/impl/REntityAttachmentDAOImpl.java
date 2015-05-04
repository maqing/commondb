package com.commondb.db.dao.impl;

import com.commondb.db.bo.REntityAttachment;
import com.commondb.db.bo.REntityAttachmentCriteria;
import com.commondb.db.dao.REntityAttachmentDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class REntityAttachmentDAOImpl
  extends SqlMapClientDaoSupport
  implements REntityAttachmentDAO
{
  public void insert(REntityAttachment record)
  {
    getSqlMapClientTemplate().insert("r_entity_attachment.abatorgenerated_insert", record);
  }
  
  public int updateByPrimaryKey(REntityAttachment record)
  {
    int rows = getSqlMapClientTemplate().update("r_entity_attachment.abatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(REntityAttachment record)
  {
    int rows = getSqlMapClientTemplate().update("r_entity_attachment.abatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public List selectByExample(REntityAttachmentCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("r_entity_attachment.abatorgenerated_selectByExample", example);
    return list;
  }
  
  public REntityAttachment selectByPrimaryKey(String id)
  {
    REntityAttachment key = new REntityAttachment();
    key.setId(id);
    REntityAttachment record = (REntityAttachment)getSqlMapClientTemplate().queryForObject("r_entity_attachment.abatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int deleteByExample(REntityAttachmentCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("r_entity_attachment.abatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(String id)
  {
    REntityAttachment key = new REntityAttachment();
    key.setId(id);
    int rows = getSqlMapClientTemplate().delete("r_entity_attachment.abatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public int countByExample(REntityAttachmentCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("r_entity_attachment.abatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int updateByExampleSelective(REntityAttachment record, REntityAttachmentCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_entity_attachment.abatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(REntityAttachment record, REntityAttachmentCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("r_entity_attachment.abatorgenerated_updateByExample", parms);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends REntityAttachmentCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, REntityAttachmentCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
  
  public List getRAttachmentById(Integer metaId, String entityId)
  {
    Map map = new HashMap();
    map.put("metaId", metaId);
    map.put("entityId", entityId);
    List list = getSqlMapClientTemplate().queryForList("r_entity_attachment.getRAttachmentById", map);
    return list;
  }
}
