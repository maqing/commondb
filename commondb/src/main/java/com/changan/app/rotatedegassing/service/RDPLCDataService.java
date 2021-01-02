package com.changan.app.rotatedegassing.service;

import java.util.List;
import java.util.Map;

import com.commondb.app.DataCollect.AirPurify.bo.APPLCData;

public interface RDPLCDataService {

	public abstract String savePLCData(String furnaceNumber, String itemNumber, String deviceNumber,
			String programNumber, String treatmentTime1, String treatmentTime2, 
			String minOfTreatment, String secOfTreatment, String temperature,
			String status, String stateMessage, String treatmentRotorspeed, 
			String rotorspeed1, String rotorspeed2, String saltAddition,
			String pressureDevice, String volumeOfFlow, String healthy1,
			String healthy2, String healthy3, String healthy4,
			String dateTimeIn, String dateTimeout, String seconds, 
			String date, String timeOfDay)
		    throws Exception;

	String savePLCTime(String dateTimeIn, String dateTimeout, String pressureDevice) throws Exception;

	
	String savePLCTimeAndLAmount(String dateTimeIn, String dateTimeout, String pressureDevice, 
			String pLCRiqi, String lAmount, String position) throws Exception;

}
