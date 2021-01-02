package com.changan.app.rotatedegassing.service;

import java.util.List;
import java.util.Map;

import com.commondb.app.DataCollect.AirPurify.bo.APPLCData;

public interface SyncDiconbaseDataService {
	public abstract String syncData()
			    throws Exception;

	public abstract String deleteDiconbaseData()
		    throws Exception;
}
