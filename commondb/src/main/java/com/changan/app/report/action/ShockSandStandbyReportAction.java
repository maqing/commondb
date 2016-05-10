package com.changan.app.report.action;


import com.changan.app.datamodel.EntityDefine;
import com.commondb.db.service.EntityService;

public class ShockSandStandbyReportAction extends DeviceStandbyReportAction {  
  
	public void setEntityService(EntityService entityService) {  
		super.setEntityService(entityService);
	}
	
	@Override  
	public String execute() {
		setDeviceMetaId(String.valueOf(EntityDefine.ShockSandDeviceMetaId));
	    return refreshGridModel();  
	}
	
	public String ExportToExcel() {
		return super.ExportToExcelFile(String.valueOf(EntityDefine.ShockSandDeviceMetaId),
				EntityDefine.ShockSandDevice_ID_CN, EntityDefine.ShockSandDevice_Name_CN );
	}
	
}  
