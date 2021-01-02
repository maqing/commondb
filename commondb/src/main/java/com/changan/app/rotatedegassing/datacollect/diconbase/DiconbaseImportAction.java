package com.changan.app.rotatedegassing.datacollect.diconbase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.struts2.ServletActionContext;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.DataCollect.tools.ReadCardCache;
import com.commondb.app.common.FileTool;
import com.commondb.app.web.BasicAction;
import com.commondb.db.bo.User;
import com.commondb.db.service.EntityService;
import com.commondb.security.service.SecurityUserHolder;
import com.opensymphony.xwork2.Preparable;

public class DiconbaseImportAction  extends BasicAction
implements Preparable
{
	private User user;
	private File myFile;
	private String myFileFileName;
	private String entityName;
	private String fileName;
	private EntityService entityService;
	private String info;
	
	
	public String getInfo()
	{
	  return this.info;
	}
	
	public void setInfo(String info)
	{
	  this.info = info;
	}
	
	public String importDiconbaseEntity1()
	{
	  return "success";
	}
	
	public String importDiconbaseEntity2()
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
	    int titleLineCount = 0;
	    FileReader r = new FileReader(dstFile);
		BufferedReader b = new BufferedReader(r);	    
		String tempLineStr;
		int lineCount=0;
		while((tempLineStr = b.readLine()) != null){
			lineCount++;
			if (lineCount>titleLineCount) {
				String[] tempArray = tempLineStr.split(";",-1);
				if (tempArray.length>=32) {
					//有效数据项，构造记录
		    		String recordTime = (new SimpleDateFormat("yyyy-MM-dd")).format
							((new SimpleDateFormat("dd.MM.yyyy")).parse(tempArray[1].trim())) + " "
		    				+ tempArray[2].trim();
		    		valuesMap.put(EntityDefine.DI_Time_CN, recordTime);
		    		String deviceNo= tempArray[3].trim();
		    		valuesMap.put(EntityDefine.DI_DeviceNo_CN, deviceNo);
		    		valuesMap.put(EntityDefine.DI_ID_CN, tempArray[4].trim());
		    		valuesMap.put(EntityDefine.DI_Article_CN, tempArray[5].trim());
		    		valuesMap.put(EntityDefine.DI_Client_CN, tempArray[6].trim());
		    		valuesMap.put(EntityDefine.DI_Cast_CN, tempArray[7].trim());
		    		valuesMap.put(EntityDefine.DI_Furnace_CN, tempArray[8].trim());
		    		valuesMap.put(EntityDefine.DI_AlloyShort_CN, tempArray[14].trim());
		    		valuesMap.put(EntityDefine.DI_AlloyLong_CN, tempArray[15].trim());
		    		valuesMap.put(EntityDefine.DI_Treatment1_CN, tempArray[16].trim());
		    		valuesMap.put(EntityDefine.DI_Treatment2_CN, tempArray[17].trim());
		    		valuesMap.put(EntityDefine.DI_Treatment3_CN, tempArray[18].trim());
		    		valuesMap.put(EntityDefine.DI_Treatment4_CN, tempArray[19].trim());
		    		valuesMap.put(EntityDefine.DI_Temperature_CN, tempArray[20].trim());
		    		valuesMap.put(EntityDefine.DI_TemperatureMin_CN, tempArray[21].trim());
		    		valuesMap.put(EntityDefine.DI_TemperatureMax_CN, tempArray[22].trim());
		    		valuesMap.put(EntityDefine.DI_ADensity_CN, tempArray[23].trim());
		    		valuesMap.put(EntityDefine.DI_ADensityMin_CN, tempArray[24].trim());
		    		valuesMap.put(EntityDefine.DI_ADensityMax_CN, tempArray[25].trim());
		    		valuesMap.put(EntityDefine.DI_VDensity_CN, tempArray[26].trim());
		    		valuesMap.put(EntityDefine.DI_VDensityMin_CN, tempArray[27].trim());
		    		valuesMap.put(EntityDefine.DI_VDensityMax_CN, tempArray[28].trim());
		    		valuesMap.put(EntityDefine.DI_DensityIndex_CN, tempArray[29].trim());
		    		valuesMap.put(EntityDefine.DI_DensityIndexMin_CN, tempArray[30].trim());
		    		valuesMap.put(EntityDefine.DI_DensityIndexMax_CN, tempArray[31].trim());
		    		/*
		    		 //关联系统当前的rfid号没有意义，需要根据测量时间找最近的rfid记录。
		    		String cardReadRecID = ReadCardCache.getCurReadRecID();
		    		if (cardReadRecID!=null) {
		    			String[][] entityArr = {{String.valueOf(EntityDefine.RPRFIDMetaId),cardReadRecID}};
		    			valuesMap.put("entityArr",entityArr);
		    			valuesMap.put(EntityDefine.DI_RFIDNO_CN, ReadCardCache.getCurReadRecNo());
		    		}
		    		*/
		    		
			        List tempIDList = this.entityService.dynSelect("id", "t_entity_"+ String.valueOf(EntityDefine.DensityIndexMetaId) , 
			        		EntityDefine.DI_Time_CN + "='" + recordTime + "' and " + EntityDefine.DI_DeviceNo_CN + "='" + deviceNo+ "' ");
			  	    if (tempIDList.size() > 0) {
			  	    	//update
			  	    	entityService.updateEntity(EntityDefine.DensityIndexMetaId, ((Map)tempIDList.get(0)).get("id").toString(), valuesMap);
				        updateCount++;
			  	    } else {
			  	    	//create
			  	    	entityService.createEntity(EntityDefine.DensityIndexMetaId, valuesMap);
				        newCount++;
			  	    }
					
				}
			
			}
		}
		b.close();
		r.close();	    
	    this.info = 
	  	      (this.info + "<br>" + "密度当量记录导入了" + (lineCount - titleLineCount) + "条数据！" + newCount + "条新建数据，" + updateCount + "条更新已有数据！" + "</br>");
	  }
	  catch (Exception e)
	  {
	    e.printStackTrace();
	  }
	  FileTool.deleteFile(dstPath);
	  
	  return "success";
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
