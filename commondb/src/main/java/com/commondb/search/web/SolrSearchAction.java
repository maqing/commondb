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
  
  //默认显示页数
  private final int DEFAULT_PAGES = 11;
  //每页显示多少条记录
  private final int PAGE_ROWS = 10;
  
  //当前页
  int curPage;
  //总页数
  long totalPages;
  //起始页
  int startPage;
  //实际分页
  int realPages;
  

  public String genSearchPage()
  {
    Map propertyMap = new HashMap();
    Map compositorMap = new HashMap();
    if (curPage<1) curPage=1;
    int startRow = 0;
    if (search_q!="") startRow = (curPage-1)*PAGE_ROWS;
    propertyMap.put("text", this.search_q);
    compositorMap.put("update_time", "desc");
    try
    {
      //this.docs = this.httpSolrService.query(propertyMap, compositorMap, Long.valueOf(0L), Long.valueOf(30L));
      this.docs = this.httpSolrService.query(propertyMap, compositorMap, (long) startRow, (long ) PAGE_ROWS);
      if (this.docs.getNumFound() > 0L) {
        this.info = ((SolrDocument)this.docs.get(0)).getFieldValue("text").toString();
      }
      
      totalPages = (long)(Math.ceil((double)this.docs.getNumFound()/(double)PAGE_ROWS));
      int[] pageParams = caluPages(curPage, totalPages);
      startPage = pageParams[0];
      realPages = pageParams[1];
      System.out.println("总页数："+ totalPages + "，当前页：" + curPage + "，起始页：" + startPage + "，实际分页：" + realPages + "，Solr开始记录：" + startRow);      
      
    }
    catch (Exception e)
    {
      this.info = "保存失败";
      e.printStackTrace();
    }
    return "success";
  }
  
  
		  
  private int[] caluPages(int curPage, long totalPages) {
	  int[] result = new int[]{curPage, DEFAULT_PAGES}; // 默认返回值
	  if(totalPages < DEFAULT_PAGES) {
		  // 如果总页数小于“默认分页”，则从1开始，结果就是所有页全部显示
		  result = new int[]{1, DEFAULT_PAGES};
	  }else if(curPage < DEFAULT_PAGES) {
		  // 当前页还没有超出“默认分页”，则从1开始；但是需要修改分页值
		  result = new int[]{1, DEFAULT_PAGES + curPage - 1};
	  }else {
		  // 此种是curPage >= DEFAULT_PAGES的情况，起始页从当前页值-每页页数的下一页算起
		  result = new int[]{curPage - DEFAULT_PAGES + 1, DEFAULT_PAGES*2};
	  }
	  return result;
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

public HttpServletRequest getRequest() {
	return request;
}

public void setRequest(HttpServletRequest request) {
	this.request = request;
}

public int getCurPage() {
	return curPage;
}

public void setCurPage(int curPage) {
	this.curPage = curPage;
}

public long getTotalPages() {
	return totalPages;
}

public void setTotalPages(long totalPages) {
	this.totalPages = totalPages;
}

public int getStartPage() {
	return startPage;
}

public void setStartPage(int startPage) {
	this.startPage = startPage;
}

public int getRealPages() {
	return realPages;
}

public void setRealPages(int realPages) {
	this.realPages = realPages;
}
}
