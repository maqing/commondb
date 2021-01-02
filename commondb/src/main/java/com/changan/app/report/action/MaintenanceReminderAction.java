package com.changan.app.report.action;

import java.util.Collections;
import java.util.List;

import com.changan.app.report.service.MaintenanceService;
import com.commondb.app.web.JqGridBaseAction;

public class MaintenanceReminderAction extends JqGridBaseAction {
	
	private String lineId;
	private MaintenanceService maintenanceService;
	
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public MaintenanceService getMaintenanceService() {
		return maintenanceService;
	}
	public void setMaintenanceService(MaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}
	
	public String queryNeedMaint() {
	    return refreshGridModel();  
	}
	@Override
	public void initQueryStr() {
		
	}
	@Override
	public int getResultSize() {
		return maintenanceService.queryNeedMaintListSize(lineId);
	}
	@Override
	public List listResults(int from, int length) {
		return maintenanceService.queryNeedMaint(lineId,  from, length);
	}
	
}
