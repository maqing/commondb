package com.commondb.search.web;

import com.commondb.common.PageInfo;
import com.commondb.search.service.HttpSolrService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.struts2.interceptor.ServletRequestAware;

public class SolrSearchAction
  extends ActionSupport
  implements Preparable, ServletRequestAware
{
  String search_q;
  HttpServletRequest request;
  private String info;
  SolrDocumentList docs;
  private PageInfo pageInfo = new PageInfo();
  private HttpSolrService httpSolrService;
  
  public String genSearchPage()
  {
    Map propertyMap = new HashMap();
    Map compositorMap = new HashMap();
    
    propertyMap.put("text", this.search_q);
    compositorMap.put("update_time", "desc");
    try
    {
      this.docs = this.httpSolrService.query(propertyMap, compositorMap, Long.valueOf(0L), Long.valueOf(30L));
      if (this.docs.getNumFound() > 0L) {
        this.info = ((SolrDocument)this.docs.get(0)).getFieldValue("text").toString();
      }
    }
    catch (Exception e)
    {
      this.info = "保存失败";
      e.printStackTrace();
    }
    return "success";
  }
  
  public void prepare()
    throws Exception
  {}
  
  public void setServletRequest(HttpServletRequest request)
  {
    this.request = request;
  }
  
  public PageInfo getPageInfo()
  {
    return this.pageInfo;
  }
  
  public void setPageInfo(PageInfo pageInfo)
  {
    this.pageInfo = pageInfo;
  }
  
  public HttpSolrService getHttpSolrService()
  {
    return this.httpSolrService;
  }
  
  public void setHttpSolrService(HttpSolrService httpSolrService)
  {
    this.httpSolrService = httpSolrService;
  }
  
  public String getSearch_q()
  {
    return this.search_q;
  }
  
  public void setSearch_q(String search_q)
  {
    this.search_q = search_q;
  }
  
  public String getInfo()
  {
    return this.info;
  }
  
  public void setInfo(String info)
  {
    this.info = info;
  }
  
  public SolrDocumentList getDocs()
  {
    return this.docs;
  }
  
  public void setDocs(SolrDocumentList docs)
  {
    this.docs = docs;
  }
}
