package com.changan.mesinterface.service;

import java.util.Map;

public abstract interface MESSyncService
{
  public abstract String insertData(Integer metaId, String entityId, Map valuesMap);
  
  public abstract String updateData(Integer metaId, String entityId, Map valuesMap);
  
  public abstract String deleteData(Integer metaId, String entityId);
  
}
