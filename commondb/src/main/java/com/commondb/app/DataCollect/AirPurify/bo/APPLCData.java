package com.commondb.app.DataCollect.AirPurify.bo;

import java.util.Date;

import com.googlecode.jsonplugin.annotations.JSON;

public class APPLCData {
	private Date plcTime;
	private String deviceId;
	private String deviceStartFlag;
	private String apStartFlag;
	private Double engineSpeed;
	private Double alTemperature;
	private String alarmFlag;
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getPlcTime() {
		return plcTime;
	}
	public void setPlcTime(Date plcTime) {
		this.plcTime = plcTime;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceStartFlag() {
		return deviceStartFlag;
	}
	public void setDeviceStartFlag(String deviceStartFlag) {
		this.deviceStartFlag = deviceStartFlag;
	}
	public String getApStartFlag() {
		return apStartFlag;
	}
	public void setApStartFlag(String apStartFlag) {
		this.apStartFlag = apStartFlag;
	}
	public Double getEngineSpeed() {
		return engineSpeed;
	}
	public void setEngineSpeed(Double engineSpeed) {
		this.engineSpeed = engineSpeed;
	}
	public Double getAlTemperature() {
		return alTemperature;
	}
	public void setAlTemperature(Double alTemperature) {
		this.alTemperature = alTemperature;
	}
	public String getAlarmFlag() {
		return alarmFlag;
	}
	public void setAlarmFlag(String alarmFlag) {
		this.alarmFlag = alarmFlag;
	}
	
	public String toString() {
		return getPlcTime().toString()+";"+getDeviceStartFlag()+";"+getApStartFlag()+";"
				+String.valueOf(getEngineSpeed())+";"+String.valueOf(getAlTemperature())+";"+getAlarmFlag();
	}

}
