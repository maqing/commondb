package com.commondb.security.service;

import java.util.Map;

public abstract interface SecurityManager
{
  public abstract Map<String, String> loadUrlAuthorities();
}
