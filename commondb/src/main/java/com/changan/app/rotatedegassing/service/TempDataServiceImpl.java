package com.changan.app.rotatedegassing.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.DataCollect.AirPurify.bo.APPLCData;
import com.commondb.app.DataCollect.AirPurify.service.PLCDataService;
import com.commondb.app.DataCollect.ICCard.service.ICCardService;
import com.commondb.app.DataCollect.tools.ReadCardCache;
import com.commondb.db.service.EntityService;

public class TempDataServiceImpl implements TempDataService {
	
	private EntityService entityService;
	private String deviceID;

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
	 * @return the deviceID
	 */
	public String getDeviceID() {
		return deviceID;
	}

	/**
	 * @param deviceID the deviceID to set
	 */
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	@Override
	public String saveTempData(Double tempV) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> rpPLCDataValuesMap = new HashMap<String, Object>();
		Map<String, Object> tempDataValuesMap = new HashMap<String, Object>();

		String plcTimeStr=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_DeviceID_CN, this.deviceID);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_PLCTime_CN, plcTimeStr);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_RefiningTemp_CN, tempV.toString());
		rpPLCDataValuesMap.put("update_user", "admin");
		rpPLCDataValuesMap.put("create_user", "admin");
		entityService.createEntity(EntityDefine.RPPLCDataMetaId, rpPLCDataValuesMap);
		
		tempDataValuesMap.put(EntityDefine.RPTemp_DeviceID_CN, this.deviceID);
		tempDataValuesMap.put(EntityDefine.RPTemp_Time_CN, plcTimeStr);
		tempDataValuesMap.put(EntityDefine.RPTemp_Value_CN,  tempV.toString());
		tempDataValuesMap.put("create_user", "admin");
		tempDataValuesMap.put("update_user", "admin");
		entityService.createEntity(EntityDefine.RPTempMetaId, tempDataValuesMap);

		return "1";
	}
	
	
}
