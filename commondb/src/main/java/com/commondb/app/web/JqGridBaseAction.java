package com.commondb.app.web;

import java.util.Collections;  
import java.util.List;  
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;  
   
@SuppressWarnings("serial")  
public abstract class JqGridBaseAction extends ActionSupport {  
    // 和jqGrid组件相关的参数属性  
    private List gridModel = Collections.emptyList();  
    private Integer rows = 0;  
    private Integer page = 0;  
    private Integer total = 0;  
    private Integer record = 0;  
    private String sord;  
    private String sidx;  
    private String search;  
    private Map<String, String> userdata;
   
    public abstract void initQueryStr();
    
    public abstract int getResultSize();  
   
    public abstract List listResults(int from, int length);  
   
    public String refreshGridModel() {  
        try {  
        	initQueryStr();
            List results = Collections.emptyList();  
            record = this.getResultSize();  
            int from = rows * (page - 1);  
            int length = rows;  
            results = this.listResults(from, length);  
            this.setGridModel(results);  
            total = (int) Math.ceil((double) record / (double) rows);  
            return SUCCESS;  
        } catch (Exception e) {  
            e.printStackTrace();  
            this.addActionError(e.getMessage());  
            return ERROR;  
        }  
    }  
    
	public String singleReport() {
		return "success";
	}
   
    public List getGridModel() {  
        return gridModel;  
    }  
    public void setGridModel(List gridModel) {  
        this.gridModel = gridModel;  
    }  
    public Integer getRows() {  
        return rows;  
    }  
    public void setRows(Integer rows) {  
        this.rows = rows;  
    }  
    public Integer getPage() {  
        return page;  
    }  
    public void setPage(Integer page) {  
        this.page = page;  
    }  
    public Integer getTotal() {  
        return total;  
    }  
    public void setTotal(Integer total) {  
        this.total = total;  
    }  
    public Integer getRecord() {  
        return record;  
    }  
    public void setRecord(Integer record) {  
        this.record = record;  
    }  
    public String getSord() {  
        return sord;  
    }  
    public void setSord(String sord) {  
        this.sord = sord;  
    }  
    public String getSidx() {  
        return sidx;  
    }  
    public void setSidx(String sidx) {  
        this.sidx = sidx;  
    }  
    public String getSearch() {  
        return search;  
    }  
    public void setSearch(String search) {  
        this.search = search;  
    }

	public Map<String, String> getUserdata() {
		return userdata;
	}

	public void setUserdata(Map userdata) {
		this.userdata = userdata;
	}  
}  