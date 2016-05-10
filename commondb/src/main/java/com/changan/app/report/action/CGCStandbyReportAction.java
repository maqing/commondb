package com.changan.app.report.action;


import com.changan.app.datamodel.EntityDefine;
import com.commondb.db.service.EntityService;

public class CGCStandbyReportAction extends DeviceStandbyReportAction {  
  
	public void setEntityService(EntityService entityService) {  
		super.setEntityService(entityService);
	}
	
	@Override  
	public String execute() {
		setDeviceMetaId(String.valueOf(EntityDefine.CgcDeviceMetaId));
	    return refreshGridModel();  
	}
	
	public String ExportToExcel() {
		return super.ExportToExcelFile(String.valueOf(EntityDefine.CgcDeviceMetaId),
				EntityDefine.CgcDevice_ID_CN, EntityDefine.CgcDevice_Name_CN );
	}
	
}  
