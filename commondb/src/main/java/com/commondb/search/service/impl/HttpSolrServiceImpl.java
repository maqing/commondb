package com.commondb.search.service.impl;

import com.commondb.search.service.HttpSolrService;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class HttpSolrServiceImpl
  implements HttpSolrService
{
  private String url;
  private Integer soTimeOut;
  private Integer connectionTimeOut;
  private Integer maxConnectionsPerHost;
  private Integer maxTotalConnections;
  private Integer maxRetries;
  private HttpSolrServer solrServer = null;
  private static final String ASC = "asc";
  
  public HttpSolrServer getServer()
  {
    return this.solrServer;
  }
  
  public void init()
    throws MalformedURLException
  {
    this.solrServer = new HttpSolrServer(this.url);
    

    this.solrServer.setDefaultMaxConnectionsPerHost(this.maxConnectionsPerHost.intValue());
    this.solrServer.setMaxTotalConnections(this.maxTotalConnections.intValue());
    this.solrServer.setFollowRedirects(false);
    this.solrServer.setAllowCompression(true);
    this.solrServer.setMaxRetries(this.maxRetries.intValue());
  }
  
  public SolrDocumentList query(Map<String, String> propertyMap, Map<String, String> compositorMap, Long startIndex, Long pageSize)
    throws Exception
  {
    SolrQuery query = new SolrQuery();
    if (propertyMap == null) {
      throw new Exception("搜索字段不可为空!");
    }
    for (Object o : propertyMap.keySet())
    {
      StringBuffer sb = new StringBuffer();
      sb.append(o.toString()).append(":");
      sb.append((String)propertyMap.get(o));
      String queryString = addBlank2Expression(sb.toString());
      query.setQuery(queryString);
    }
    if (compositorMap != null) {
      for (Object co : compositorMap.keySet()) {
        if (("asc" == compositorMap.get(co)) || 
          ("asc".equals(compositorMap.get(co)))) {
          query.addSortField(co.toString(), SolrQuery.ORDER.asc);
        } else {
          query.addSortField(co.toString(), SolrQuery.ORDER.desc);
        }
      }
    }
    if (startIndex != null) {
      query.setStart(Integer.valueOf(Integer.parseInt(String.valueOf(startIndex))));
    }
    if ((pageSize != null) && (0L != pageSize.longValue())) {
      query.setRows(Integer.valueOf(Integer.parseInt(String.valueOf(pageSize))));
    }
    try
    {
      QueryResponse qrsp = this.solrServer.query(query);
      return qrsp.getResults();
    }
    catch (Exception e)
    {
      throw new Exception(e);
    }
  }
  
  private String addBlank2Expression(String oldExpression)
  {
    String lastExpression = oldExpression.replace("AND", " AND ").replace("NOT", 
      " NOT ").replace("OR", " OR ");
    return lastExpression;
  }
  
  public void addDoc(String entityId, Map valuesMap)
    throws Exception
  {
    SolrInputDocument doc = new SolrInputDocument();
    
    doc.addField("id", entityId);
    
    Set<String> keys = valuesMap.keySet();
    for (String key : keys) {
      doc.addField(key, valuesMap.get(key));
    }
    try
    {
      UpdateResponse response = this.solrServer.add(doc);
      this.solrServer.commit();
    }
    catch (Exception e)
    {
      this.solrServer.rollback();
      throw new Exception(e);
    }
  }
  
  public void updateDoc(String entityId, Map valuesMap)
    throws Exception
  {
    SolrInputDocument doc = new SolrInputDocument();
    
    doc.addField("id", entityId);
    

    Set<String> keys = valuesMap.keySet();
    for (String key : keys)
    {
      Map<String, Object> ky = new HashMap();
      ky.put("set", valuesMap.get(key));
      doc.addField(key, ky);
    }
    try
    {
      UpdateResponse response = this.solrServer.add(doc);
      this.solrServer.commit();
    }
    catch (Exception e)
    {
      this.solrServer.rollback();
      throw new Exception(e);
    }
  }
  
  public void deleteDoc(String entityId)
    throws Exception
  {
    this.solrServer.deleteById(entityId);
    this.solrServer.commit();
  }
  
  public String getUrl()
  {
    return this.url;
  }
  
  public void setUrl(String url)
  {
    this.url = url;
  }
  
  public Integer getSoTimeOut()
  {
    return this.soTimeOut;
  }
  
  public void setSoTimeOut(Integer soTimeOut)
  {
    this.soTimeOut = soTimeOut;
  }
  
  public Integer getConnectionTimeOut()
  {
    return this.connectionTimeOut;
  }
  
  public void setConnectionTimeOut(Integer connectionTimeOut)
  {
    this.connectionTimeOut = connectionTimeOut;
  }
  
  public Integer getMaxConnectionsPerHost()
  {
    return this.maxConnectionsPerHost;
  }
  
  public void setMaxConnectionsPerHost(Integer maxConnectionsPerHost)
  {
    this.maxConnectionsPerHost = maxConnectionsPerHost;
  }
  
  public Integer getMaxTotalConnections()
  {
    return this.maxTotalConnections;
  }
  
  public void setMaxTotalConnections(Integer maxTotalConnections)
  {
    this.maxTotalConnections = maxTotalConnections;
  }
  
  public Integer getMaxRetries()
  {
    return this.maxRetries;
  }
  
  public void setMaxRetries(Integer maxRetries)
  {
    this.maxRetries = maxRetries;
  }
}
