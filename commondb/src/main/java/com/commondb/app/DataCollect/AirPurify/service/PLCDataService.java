package com.commondb.app.DataCollect.AirPurify.service;

import java.util.List;
import java.util.Map;

import com.commondb.app.DataCollect.AirPurify.bo.APPLCData;

public interface PLCDataService {
	public abstract Map<String, Object> saveAPPLCData(APPLCData item,  Map<String, Object> apRecValueMap)
			    throws Exception;

	public abstract Map<String, Object> getLastNotFinishAPRec();

	public List<APPLCData> queryAPPLCData();

}
