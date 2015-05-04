package com.commondb.db.dao;

import com.commondb.db.bo.Reminder;
import com.commondb.db.bo.ReminderCriteria;
import java.util.List;

public abstract interface ReminderDAO
{
  public abstract int countByExample(ReminderCriteria paramReminderCriteria);
  
  public abstract int deleteByExample(ReminderCriteria paramReminderCriteria);
  
  public abstract int deleteByPrimaryKey(String paramString);
  
  public abstract void insert(Reminder paramReminder);
  
  public abstract void insertSelective(Reminder paramReminder);
  
  public abstract List selectByExample(ReminderCriteria paramReminderCriteria);
  
  public abstract Reminder selectByPrimaryKey(String paramString);
  
  public abstract int updateByExampleSelective(Reminder paramReminder, ReminderCriteria paramReminderCriteria);
  
  public abstract int updateByExample(Reminder paramReminder, ReminderCriteria paramReminderCriteria);
  
  public abstract int updateByPrimaryKeySelective(Reminder paramReminder);
  
  public abstract int updateByPrimaryKey(Reminder paramReminder);
}
