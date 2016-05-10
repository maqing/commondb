package com.changan.app.report.action;


import com.changan.app.datamodel.EntityDefine;
import com.commondb.db.service.EntityService;

public class RobotRunReportAction extends DeviceRunReportAction {  
  
	public void setEntityService(EntityService entityService) {  
		super.setEntityService(entityService);
	}
	
	@Override  
	public String execute() {
		setDeviceMetaId(String.valueOf(EntityDefine.RobotDeviceMetaId));
	    return refreshGridModel();  
	}
	
	public String ExportToExcel() {
		return super.ExportToExcelFile(String.valueOf(EntityDefine.RobotDeviceMetaId),
				EntityDefine.RobotDevice_ID_CN, EntityDefine.RobotDevice_Name_CN );
	}
	
}  
