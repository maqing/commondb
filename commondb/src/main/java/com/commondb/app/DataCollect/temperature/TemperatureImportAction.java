package com.commondb.app.DataCollect.temperature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts2.ServletActionContext;

import com.commondb.app.DataCollect.tools.ReadCardCache;
import com.commondb.app.common.FileTool;
import com.commondb.app.web.BasicAction;
import com.commondb.db.bo.User;
import com.commondb.db.service.EntityService;
import com.commondb.security.service.SecurityUserHolder;
import com.opensymphony.xwork2.Preparable;

public class TemperatureImportAction  extends BasicAction
implements Preparable
{
	private User user;
	private File myFile;
	private String myFileFileName;
	private String entityName;
	private String fileName;
	private EntityService entityService;
	private String info;
	private final int ICCardReadMetaId =3;
	private final int TemperatureMetaId = 4;
	private final String Tempe_TimeCN = "d_18";
	private final String Tempe_DeviceCodeCN = "d_19";
	private final String Tempe_ValueCN = "d_20";
	private final String Tempe_RFIDCodeCN = "d_21";
	
	
	public String getInfo()
	{
	  return this.info;
	}
	
	public void setInfo(String info)
	{
	  this.info = info;
	}
	
	public String importTemperatureEntity1()
	{
	  return "success";
	}
	
	public String importTemperatureEntity2()
	{
	  this.user = SecurityUserHolder.getCurrentUser();
	  this.fileName = (this.user.getUserName() + "_" + getMyFileFileName());
	  String dstPath = ServletActionContext.getServletContext().getRealPath("upload") + 
	    "\\" + this.fileName;
	  File dstFile = new File(dstPath);
	  FileTool.copy(this.myFile, dstFile);
	  try
	  {
	    Map valuesMap = new HashMap();
	    int newCount = 0;
	    int updateCount = 0;
	    this.info = "";
	    FileReader r = new FileReader(dstFile);
		BufferedReader b = new BufferedReader(r);	    
		String tempLineStr;
		int lineCount=0;
		while((tempLineStr = b.readLine()) != null){
			lineCount++;
			if (lineCount>12) {
				String[] tempArray = tempLineStr.split("\\u0009");
				if (tempArray.length>=7) {
					//有效数据项，构造记录
		    		String tempe_time = tempArray[1].trim() + " "
		    				+ tempArray[2].trim();
		    		String tempe_deviceCode = tempArray[3].trim();
		    		valuesMap.put(Tempe_TimeCN, tempe_time);
		    		valuesMap.put(Tempe_DeviceCodeCN, tempe_deviceCode);
		    		valuesMap.put(Tempe_ValueCN, tempArray[4].trim());
		    		String cardReadRecID = ReadCardCache.getCurReadRecID();
		    		if (cardReadRecID!=null) {
		    			String[][] entityArr = {{String.valueOf(ICCardReadMetaId),cardReadRecID}};
		    			valuesMap.put("entityArr",entityArr);
		    			valuesMap.put(Tempe_RFIDCodeCN, ReadCardCache.getCurReadRecNo());
		    		}
		    		
			        List tempIDList = this.entityService.dynSelect("id", "t_entity_"+ String.valueOf(TemperatureMetaId) , 
			        		Tempe_TimeCN + "='" + tempe_time + "' and " + Tempe_DeviceCodeCN + "='" + tempe_deviceCode+ "' ");
			  	    if (tempIDList.size() > 0) {
			  	    	//update
			  	    	entityService.updateEntity(TemperatureMetaId, ((Map)tempIDList.get(0)).get("id").toString(), valuesMap);
				        updateCount++;
			  	    } else {
			  	    	//create
			  	    	entityService.createEntity(TemperatureMetaId, valuesMap);
				        newCount++;
			  	    }
					
				}
			
			}
		}
		b.close();
		r.close();	    
	    this.info = 
	  	      (this.info + "<br>" + "测温记录导入了" + (lineCount - 12) + "条数据！" + newCount + "条新建数据，" + updateCount + "条更新已有数据！" + "</br>");
	  }
	  catch (Exception e)
	  {
	    e.printStackTrace();
	  }
	  FileTool.deleteFile(dstPath);
	  
	  return "success";
	}
	
	/*
	public String importTemperatureEntity2()
	{
	  this.user = SecurityUserHolder.getCurrentUser();
	  this.fileName = (this.user.getUserName() + "_" + getMyFileFileName());
	  String dstPath = ServletActionContext.getServletContext().getRealPath("upload") + 
	    "\\" + this.fileName;
	  File dstFile = new File(dstPath);
	  FileTool.copy(this.myFile, dstFile);
	  try
	  {
	    this.info = "";
	    
	    InputStream is = new FileInputStream(dstFile);
	    
	    Workbook rwb = Workbook.getWorkbook(is);
	    
	    importTrackSheet(rwb);
	    
	    is.close();
	  }
	  catch (Exception e)
	  {
	    e.printStackTrace();
	  }
	  FileTool.deleteFile(dstPath);
	  
	  return "success";
	}
	*/
	
	private void importTrackSheet(Workbook wwb)
	{
	  try
	  {
	    Sheet cws = wwb.getSheet(0);
	    

	    Map valuesMap = new HashMap();
	    int newCount = 0;
	    int updateCount = 0;
	    for (int rowIndex = 12; rowIndex < cws.getRows(); rowIndex++)
	    {
	    	Cell idCell = cws.getCell(0, rowIndex);
	    	
	    	if ((idCell!=null)&&(idCell.getContents()!=null)&&(idCell.getContents().trim().length()>0)) {
	    		//序号单元格不为空，开始构造记录
	    		String tempe_time = cws.getCell(1, rowIndex).getContents().trim() + " "
	    				+ cws.getCell(2, rowIndex).getContents().trim();
	    		String tempe_deviceCode = cws.getCell(3, rowIndex).getContents().trim();
	    		valuesMap.put(Tempe_TimeCN, tempe_time);
	    		valuesMap.put(Tempe_DeviceCodeCN, tempe_deviceCode);
	    		valuesMap.put(Tempe_ValueCN, cws.getCell(4, rowIndex).getContents().trim());
	    		String cardReadRecID = ReadCardCache.getCurReadRecID();
	    		if (cardReadRecID!=null) {
	    			String[][] entityArr = {{String.valueOf(ICCardReadMetaId),cardReadRecID}};
	    			valuesMap.put("entityArr",entityArr);
	    			valuesMap.put(Tempe_RFIDCodeCN, ReadCardCache.getCurReadRecNo());
	    		}
	    		
		        List tempIDList = this.entityService.dynSelect("id", "t_entity_"+ String.valueOf(TemperatureMetaId) , 
		        		Tempe_TimeCN + "='" + tempe_time + "' and " + Tempe_DeviceCodeCN + "='" + tempe_deviceCode+ "' ");
		  	    if (tempIDList.size() > 0) {
		  	    	//update
		  	    	entityService.updateEntity(TemperatureMetaId, ((Map)tempIDList.get(0)).get("id").toString(), valuesMap);
			        updateCount++;
		  	    } else {
		  	    	//create
		  	    	entityService.createEntity(TemperatureMetaId, valuesMap);
			        newCount++;
		  	    }
	    	}
	    }
	    this.info = 
	      (this.info + "<br>" + "测温记录导入了" + (cws.getColumns() - 1) + "条数据！" + newCount + "条新建数据，" + updateCount + "条更新已有数据！" + "</br>");
	  }
	  catch (Exception e)
	  {
	    e.printStackTrace();
	  }
	}
	
	
	public void prepare()
	  throws Exception
	{}
	
	public String getEntityName()
	{
	  return this.entityName;
	}
	
	public void setEntityName(String entityName)
	{
	  this.entityName = entityName;
	}
	
	public String getFileName()
	{
	  return this.fileName;
	}
	
	public void setFileName(String fileName)
	{
	  this.fileName = fileName;
	}
	

	
	public EntityService getEntityService()
	{
	  return this.entityService;
	}
	
	public void setEntityService(EntityService entityService)
	{
	  this.entityService = entityService;
	  super.setEntityService(entityService);
	}
	
	
	public User getUser()
	{
	  return this.user;
	}
	
	public void setUser(User user)
	{
	  this.user = user;
	}
	
	public File getMyFile()
	{
	  return this.myFile;
	}
	
	public void setMyFile(File myFile)
	{
	  this.myFile = myFile;
	}
	
	public String getMyFileFileName()
	{
	  return this.myFileFileName;
	}
	
	public void setMyFileFileName(String myFileFileName)
	{
	  this.myFileFileName = myFileFileName;
	}
}
