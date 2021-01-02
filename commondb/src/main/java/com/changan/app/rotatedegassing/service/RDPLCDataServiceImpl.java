package com.changan.app.rotatedegassing.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class RDPLCDataServiceImpl implements RDPLCDataService {
	
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
	public String savePLCData(String furnaceNumber, String itemNumber,
			String deviceNumber, String programNumber, String treatmentTime1,
			String treatmentTime2, String minOfTreatment,
			String secOfTreatment, String temperature, String status,
			String stateMessage, String treatmentRotorspeed,
			String rotorspeed1, String rotorspeed2, String saltAddition,
			String pressureDevice, String volumeOfFlow, String healthy1,
			String healthy2, String healthy3, String healthy4,
			String dateTimeIn, String dateTimeout, String seconds, String date,
			String timeOfDay) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> rpPLCDataValuesMap = new HashMap<String, Object>();

		//String plcTimeStr=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
		//rpPLCDataValuesMap.put(EntityDefine.RPPLCData_PLCTime_CN, plcTimeStr);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_DeviceID_CN, this.deviceID);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_PLCTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_RotateSpeed_CN, treatmentRotorspeed);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_N2FlowRate_CN, volumeOfFlow);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_N2Pressure_CN, pressureDevice);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_DegassingTime_CN, treatmentTime1);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_StandingTime_CN, treatmentTime2);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_RefiningTemp_CN, temperature);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_FurnaceNumber_CN, furnaceNumber);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_ItemNumber_CN, itemNumber);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_ProgramNumber_CN, programNumber);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_ExectedMinutes_CN, minOfTreatment);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_ExectedSeconds_CN, secOfTreatment);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_Status_CN, status);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_StateMessage_CN, stateMessage);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_RotorSpeed1_CN, rotorspeed1);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_RotorSpeed2_CN, rotorspeed2);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_SaltAddition_CN, saltAddition);
		rpPLCDataValuesMap.put(EntityDefine.RPPLCData_PressureDevice_CN, pressureDevice);
		rpPLCDataValuesMap.put("update_user", "admin");
		rpPLCDataValuesMap.put("create_user", "admin");
		String plcDataId=entityService.createEntity(EntityDefine.RPPLCDataMetaId, rpPLCDataValuesMap);
		
		/*
		Map<String, Object> rpPLCRecValuesMap = new HashMap<String, Object>();
		rpPLCRecValuesMap.put(EntityDefine.RP_DeviceID_CN, this.deviceID);
		rpPLCRecValuesMap.put(EntityDefine.RP_BeginTime_CN, dateTimeIn);
		rpPLCRecValuesMap.put(EntityDefine.RP_EndTime_CN, dateTimeout);
		rpPLCRecValuesMap.put(EntityDefine.RP_RFIDNO_CN, ReadCardCache.getCurReadRecNo());
		rpPLCRecValuesMap.put("create_user", "admin");
		rpPLCRecValuesMap.put("update_user", "admin");
		entityService.createEntity(EntityDefine.RPMetaId, rpPLCRecValuesMap);
		*/
		
		return plcDataId;
	}


	@Override
	public String savePLCTime(
			String dateTimeIn, String dateTimeout, String pressureDevice) throws Exception {
		// TODO Auto-generated method stub
		/*
		Map<String, Object> rpPLCRecValuesMap = new HashMap<String, Object>();
		rpPLCRecValuesMap.put(EntityDefine.RP_DeviceID_CN, this.deviceID);
		rpPLCRecValuesMap.put(EntityDefine.RP_BeginTime_CN, dateTimeIn);
		rpPLCRecValuesMap.put(EntityDefine.RP_EndTime_CN, dateTimeout);
		rpPLCRecValuesMap.put(EntityDefine.RP_RFIDNO_CN, ReadCardCache.getCurReadRecNo());
		rpPLCRecValuesMap.put("create_user", "admin");
		rpPLCRecValuesMap.put("update_user", "admin");
		String plcTimeId=entityService.createEntity(EntityDefine.RPMetaId, rpPLCRecValuesMap);
		return plcTimeId;
		*/
		return savePLCTimeAndLAmount(
				 dateTimeIn, dateTimeout, pressureDevice, "", "", "");
		
	}

	@Override
	public String savePLCTimeAndLAmount(
			String dateTimeIn, String dateTimeout, String pressureDevice, 
			String pLCRiqi, String lAmount, String position) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String, Object> rpPLCRecValuesMap = new HashMap<String, Object>();
		rpPLCRecValuesMap.put(EntityDefine.RP_DeviceID_CN, this.deviceID);
		rpPLCRecValuesMap.put(EntityDefine.RP_BeginTime_CN, dateTimeIn);
		rpPLCRecValuesMap.put(EntityDefine.RP_EndTime_CN, dateTimeout);
		rpPLCRecValuesMap.put(EntityDefine.RP_RFIDNO_CN, ReadCardCache.getCurReadRecNo());
		//构造记录编号
		if (lAmount.length()>0 && position.length()>0) {
			/*
			Calendar cal = Calendar.getInstance();
			int day = cal.get(Calendar.DATE);
		    int month = cal.get(Calendar.MONTH) + 1;
		    pLCRiqi = EntityDefine.RPDeviceNumber + position +  String.format("%01X", month) + String.format("%02d", day);   
		    */
			String day = dateTimeIn.substring(8, 10);
			String month = dateTimeIn.substring(5, 7);
			pLCRiqi = EntityDefine.RPDeviceNumber + position +  String.format("%01X",Integer.valueOf(month)) + day
					+ String.format("%03d", Integer.valueOf(lAmount));   
		}
		rpPLCRecValuesMap.put(EntityDefine.RP_PLCRiqi_CN, pLCRiqi);
		rpPLCRecValuesMap.put(EntityDefine.RP_LAmount_CN, lAmount);
		rpPLCRecValuesMap.put(EntityDefine.RP_Position_CN, position);
		rpPLCRecValuesMap.put("create_user", "admin");
		rpPLCRecValuesMap.put("update_user", "admin");
		String plcTimeId=entityService.createEntity(EntityDefine.RPMetaId, rpPLCRecValuesMap);
		
		return plcTimeId;
	}
	
	
	/*
	@Override
	public String savePLCData(Double tempV) throws Exception {
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
	*/
	
	public static void main(String[] args){
		/*
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
	    int month = cal.get(Calendar.MONTH) + 1;
	    String pLCRiqi = EntityDefine.RPDeviceNumber + "A" +  String.format("%01X", month) + String.format("%02d", day);   
		System.out.println(pLCRiqi);
		*/
		String dateTimeIn = "2020-12-09 13:18:27";
			String day = dateTimeIn.substring(8, 10);
			String month = dateTimeIn.substring(5, 7);
			String lAmount = "93";
			System.out.println(day);
			System.out.println(month);
			String pLCRiqi = EntityDefine.RPDeviceNumber + "A" +  String.format("%01X",Integer.valueOf(month)) + day
					+ String.format("%03d", Integer.valueOf(lAmount));   
			System.out.println(pLCRiqi);
	}
	
}
