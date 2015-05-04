package com.commondb.db.dao.impl;

import com.commondb.db.bo.Reminder;
import com.commondb.db.bo.ReminderCriteria;
import com.commondb.db.dao.ReminderDAO;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ReminderDAOImpl
  extends SqlMapClientDaoSupport
  implements ReminderDAO
{
  public int countByExample(ReminderCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_reminder.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(ReminderCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_reminder.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(String id)
  {
    Reminder key = new Reminder();
    key.setId(id);
    int rows = getSqlMapClientTemplate().delete("t_reminder.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(Reminder record)
  {
    getSqlMapClientTemplate().insert("t_reminder.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(Reminder record)
  {
    getSqlMapClientTemplate().insert("t_reminder.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(ReminderCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_reminder.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public Reminder selectByPrimaryKey(String id)
  {
    Reminder key = new Reminder();
    key.setId(id);
    Reminder record = (Reminder)getSqlMapClientTemplate().queryForObject("t_reminder.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(Reminder record, ReminderCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_reminder.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(Reminder record, ReminderCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_reminder.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(Reminder record)
  {
    int rows = getSqlMapClientTemplate().update("t_reminder.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(Reminder record)
  {
    int rows = getSqlMapClientTemplate().update("t_reminder.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends ReminderCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, ReminderCriteria example)
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
