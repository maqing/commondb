package com.commondb.app.common;

import com.commondb.app.common.meta.Meta;

public class MetaTool
{
  private static MetaTool instance = new MetaTool();
  
  public static MetaTool getInstance()
  {
    return instance;
  }
  
  public Integer getMetaId(String metaName)
  {
    return Integer.valueOf(0);
  }
  
  public Meta getMeta(String metaName)
  {
    return null;
  }
}
