package com.changan.app.report.model;

public class Device {
	private String lineId;
	private String deviceType;
	private String deviceId;
	private String deviceCode;
	private String deviceName;
	
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * @return the deviceCode
	 */
	public String getDeviceCode() {
		return deviceCode;
	}
	/**
	 * @param deviceCode the deviceCode to set
	 */
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

}
