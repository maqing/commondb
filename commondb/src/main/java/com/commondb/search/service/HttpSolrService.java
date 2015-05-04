package com.commondb.search.service;

import java.net.MalformedURLException;
import java.util.Map;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrDocumentList;

public abstract interface HttpSolrService
{
  public abstract SolrServer getServer();
  
  public abstract void init()
    throws MalformedURLException;
  
  public abstract SolrDocumentList query(Map<String, String> paramMap1, Map<String, String> paramMap2, Long paramLong1, Long paramLong2)
    throws Exception;
  
  public abstract void addDoc(String paramString, Map paramMap)
    throws Exception;
  
  public abstract void updateDoc(String paramString, Map paramMap)
    throws Exception;
  
  public abstract void deleteDoc(String paramString)
    throws Exception;
}
