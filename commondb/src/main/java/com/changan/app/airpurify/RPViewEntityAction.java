package com.changan.app.airpurify;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.common.meta.DescField;
import com.commondb.app.web.ViewEntityAction;

public class RPViewEntityAction extends ViewEntityAction {
	private String rpDeviceID;
	private String rpBeginTime;
	private String rpEndTime;
	private String rpRFIDNO;

	public String getRpDeviceID() {
		return rpDeviceID;
	}


	public void setRpDeviceID(String deviceId) {
		this.rpDeviceID = deviceId;
	}


	public String getRpBeginTime() {
		return rpBeginTime;
	}


	public void setRpBeginTime(String rpBeginTime) {
		this.rpBeginTime = rpBeginTime;
	}


	public String getRpEndTime() {
		return rpEndTime;
	}


	public void setRpEndTime(String rpEndTime) {
		this.rpEndTime = rpEndTime;
	}


	public String getRpRFIDNO() {
		return rpRFIDNO;
	}


	public void setRpRFIDNO(String rfIDNo) {
		this.rpRFIDNO = rfIDNo;
	}


	public String viewEntity() {
		String result = super.viewEntity();
	    for (int i = 0; i < descFields.size(); i++)
	    {
	    	DescField dField = (DescField) descFields.get(i);
	    	if (dField.getColumnName().equals(EntityDefine.RP_DeviceID_CN)) {
	    		rpDeviceID = dField.getValue();
	    	} else if (dField.getColumnName().equals(EntityDefine.RP_BeginTime_CN)) {
	    		rpBeginTime = dField.getValue();
	    	} else if (dField.getColumnName().equals(EntityDefine.RP_EndTime_CN)) {
	    		rpEndTime = dField.getValue();
	    	} else if (dField.getColumnName().equals(EntityDefine.RP_RFIDNO_CN)) {
	    		rpRFIDNO = dField.getValue();
	    	} 
	    }
		
		return result;
	}

}
