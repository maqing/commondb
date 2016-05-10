package com.changan.app.report.action;


import com.changan.app.datamodel.EntityDefine;
import com.commondb.db.service.EntityService;

public class TransferStandbyReportAction extends DeviceStandbyReportAction {  
  
	public void setEntityService(EntityService entityService) {  
		super.setEntityService(entityService);
	}
	
	@Override  
	public String execute() {
		setDeviceMetaId(String.valueOf(EntityDefine.TransferDeviceMetaId));
	    return refreshGridModel();  
	}
	
	public String ExportToExcel() {
		return super.ExportToExcelFile(String.valueOf(EntityDefine.TransferDeviceMetaId),
				EntityDefine.TransferDevice_ID_CN, EntityDefine.TransferDevice_Name_CN );
	}
	
}  
