package com.changan.app.report.action;


import com.changan.app.datamodel.EntityDefine;
import com.commondb.db.service.EntityService;

public class MachineSparePartsLifeReportAction extends DeviceSparePartsLifeReportAction {  
  
	public void setEntityService(EntityService entityService) {  
		super.setEntityService(entityService);
	}
	
	@Override  
	public String execute() {
		setDeviceMetaId(String.valueOf(EntityDefine.MachineDeviceMetaId));
	    return refreshGridModel();  
	}
	
	public String ExportToExcel() {
		return super.ExportToExcelFile(String.valueOf(EntityDefine.MachineDeviceMetaId),
				EntityDefine.MachineDevice_ID_CN, EntityDefine.MachineDevice_Name_CN );
	}
	
}  
