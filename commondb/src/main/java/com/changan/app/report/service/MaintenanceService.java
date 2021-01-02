package com.changan.app.report.service;

import java.util.List;


public abstract interface MaintenanceService {
	
	public abstract List queryNeedMaint(String lineId, int from, int length);

	public abstract int queryNeedMaintListSize(String lineId);

	public abstract List queryMaintRec(String lineId, String deviceId, String timeBegin, String timeEnd, int from, int length);
	
	public abstract int queryMaintRecListSize(String lineId, String deviceId, String timeBegin, String timeEnd);
	
	public abstract String addMaintRec(String remindId,  String workTime, String note, String workerName) throws Exception;

	
}
