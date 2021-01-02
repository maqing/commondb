package com.changan.app.rotatedegassing.service;

import java.util.List;
import java.util.Map;

import com.commondb.app.DataCollect.AirPurify.bo.APPLCData;

public interface TempDataService {
	public abstract String saveTempData(Double tempV)
			    throws Exception;

}
