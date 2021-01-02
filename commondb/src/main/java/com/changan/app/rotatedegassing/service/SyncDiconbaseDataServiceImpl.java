package com.changan.app.rotatedegassing.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.changan.app.datamodel.EntityDefine;
import com.changan.mesinterface.service.MESSyncServiceImpl;
import com.commondb.app.DataCollect.AirPurify.bo.APPLCData;
import com.commondb.app.DataCollect.AirPurify.service.PLCDataService;
import com.commondb.app.DataCollect.ICCard.service.ICCardService;
import com.commondb.app.DataCollect.tools.ReadCardCache;
import com.commondb.app.common.FileTool;
import com.commondb.common.DateUtil;
import com.commondb.db.service.EntityService;
import com.commondb.security.service.SecurityUserHolder;

public class SyncDiconbaseDataServiceImpl implements SyncDiconbaseDataService {
	
	static Logger logger = Logger.getLogger(SyncDiconbaseDataServiceImpl.class);
	
	private EntityService entityService;
	private String fileName;
	private String bakPath;

	/**
	 * @return the entityService
	 */
	public EntityService getEntityService() {
		return entityService;
	}

	/**
	 * @param entityService the entityService to set
	 */
	public void setEntityService(EntityService entityService) {
		this.entityService = entityService;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	/**
	 * @return the bakPath
	 */
	public String getBakPath() {
		return bakPath;
	}

	/**
	 * @param bakPath the bakPath to set
	 */
	public void setBakPath(String bakPath) {
		this.bakPath = bakPath;
	}

	@Override
	public String syncData() throws Exception {
		File dstFile = null;
		try {
			dstFile = new File(fileName);
		} catch (Exception e) {
			logger.error("读取diconbase备份文件失败，文件位置:"+fileName+",错误信息："+e.toString());
			return "0";
		}
		if (!dstFile.exists()) {
			logger.error("diconbase不存在，文件位置:"+fileName+"；");
			return "0";
		}
		try
		{
			Map valuesMap = new HashMap();
			int newCount = 0;
			int updateCount = 0;
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
						valuesMap.put("update_user", "admin");
						valuesMap.put("create_user", "admin");
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
							//update 不更新
							//entityService.updateEntity(EntityDefine.DensityIndexMetaId, ((Map)tempIDList.get(0)).get("id").toString(), valuesMap);
						    //updateCount++;
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
			logger.info("密度当量记录导入了" + (lineCount - titleLineCount) + "条数据！" + newCount + "条新建数据，" + updateCount + "条更新已有数据！" );
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "1";
	}

	@Override
	public String deleteDiconbaseData() throws Exception {
		File dstFile = null;
		try {
			dstFile = new File(fileName);
		} catch (Exception e) {
			logger.error("读取diconbase备份文件失败，文件位置:"+fileName+",错误信息："+e.toString());
			return "0";
		}
		if (!dstFile.exists()) {
			logger.error("diconbase不存在，文件位置:"+fileName+"；");
		} else {
			 if (dstFile.isFile()) 
				 try {
					 Path bakFile=Paths.get(bakPath + DateUtil.formatDate(new Date(),"yyyyMMdd") + ".dat");
					 Files.copy(dstFile.toPath(), bakFile, StandardCopyOption.REPLACE_EXISTING);
					 dstFile.delete();
					 logger.info("删除diconbase备份文件。");
				 } catch (Exception e) {
					 logger.error("删除diconbase备份文件失败，文件位置:"+fileName+",错误信息："+e.toString());
				 }
		}
		return "1";
	}
	
	
}
