package com.commondb.common;

import java.util.List;

public class PageInfo
{
  public static final int NUMBERS_PER_PAGE = 10;
  private int numPerPage = 10;
  private int totalRows;
  private int totalPages;
  private int currentPage = 1;
  private int startIndex;
  private int lastIndex;
  private List resultList;
  
  public int getCurrentPage()
  {
	if (this.currentPage<=0) {
		return this.totalPages>=1?1:this.totalPages;
	} else {
		return this.currentPage;
	}
  }
  
  public void setCurrentPage(int currentPage)
  {
    this.currentPage = currentPage;
  }
  
  public int getNumPerPage()
  {
    return this.numPerPage;
  }
  
  public void setNumPerPage(int numPerPage)
  {
    this.numPerPage = numPerPage;
  }
  
  public List getResultList()
  {
    return this.resultList;
  }
  
  public void setResultList(List resultList)
  {
    this.resultList = resultList;
  }
  
  public int getTotalPages()
  {
    setTotalPages();
    return this.totalPages;
  }
  
  public void setTotalPages()
  {
    if (this.totalRows % this.numPerPage == 0) {
      this.totalPages = (this.totalRows / this.numPerPage);
    } else {
      this.totalPages = (this.totalRows / this.numPerPage + 1);
    }
  }
  
  public int getTotalRows()
  {
    return this.totalRows;
  }
  
  public void setTotalRows(int totalRows)
  {
    this.totalRows = totalRows;
  }
  
  public int getStartIndex()
  {
    setStartIndex();
    return this.startIndex;
  }
  
  public void setStartIndex()
  {
    if (this.currentPage > getTotalPages()) {
      this.currentPage = this.totalPages;
    }
    if (this.currentPage >= 1) {
      this.startIndex = ((this.currentPage - 1) * this.numPerPage);
    } else {
      this.startIndex = 0;
    }
  }
  
  public int getLastIndex()
  {
    setLastIndex();
    return this.lastIndex;
  }
  
  public void setLastIndex()
  {
    if (this.totalRows < this.numPerPage) {
      this.lastIndex = this.totalRows;
    } else if ((this.totalRows % this.numPerPage == 0) || (
      (this.totalRows % this.numPerPage != 0) && (this.currentPage < this.totalPages))) {
      this.lastIndex = (this.currentPage * this.numPerPage);
    } else if ((this.totalRows % this.numPerPage != 0) && (this.currentPage == this.totalPages)) {
      this.lastIndex = this.totalRows;
    }
  }
}
