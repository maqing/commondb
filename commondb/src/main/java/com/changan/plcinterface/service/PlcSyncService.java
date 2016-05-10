package com.changan.plcinterface.service;

public abstract interface PlcSyncService
{
  public abstract String syncData();
  
  public abstract void pullData();
  
  public abstract String processPlcData();
}
