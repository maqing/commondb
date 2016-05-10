package com.commondb.app.echarts;

import com.commondb.app.DataCollect.AirPurify.service.PLCDataService;
import com.commondb.common.JsonResult;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import org.json.JSONObject;

public class EchartsDataAction  extends ActionSupport
implements Preparable
{
	private JsonResult result = new JsonResult();
	private PLCDataService plcDataService;
	
	public void prepare()
	  throws Exception
	{}
	
	public String queryEchartsData()
	{
	  this.result = new JsonResult();
	  try
	  {
	    this.result.success = true;
	    this.result.setData(plcDataService.queryAPPLCData());
	  }
	  catch (Throwable t)
	  {
	    this.result.success = false;
	    this.result.errormsg = t.getMessage();
	    t.printStackTrace();
	  }
	  return "success";
	}
	
	
	
	public JsonResult getResult()
	{
	  return this.result;
	}
	
	public void setResult(JsonResult result)
	{
	  this.result = result;
	}

	/**
	 * @return the plcDataService
	 */
	public PLCDataService getPlcDataService() {
		return plcDataService;
	}

	/**
	 * @param plcDataService the plcDataService to set
	 */
	public void setPlcDataService(PLCDataService plcDataService) {
		this.plcDataService = plcDataService;
	}
}
