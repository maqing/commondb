package com.commondb.db.service;

public abstract interface SyncService
{
  public abstract String syncData();
  
  public abstract void pullData();
}
