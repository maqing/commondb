package com.changan.app.report.model;

public class DeviceWorkTime {
	private String lineId;
	private String deviceId;
	private String deviceName;
	private String totalRunTime;
	private String normalRunTime;
	private String standbyTime;
	private String totalErrorTime;
	private int errorCount;
	private String deviceOEE;
	private double errorRate;
	private String errorCode;
	private double mttr;
	private double mtbf;
	
	public String getTotalRunTime() {
		return totalRunTime;
	}
	public void setTotalRunTime(String totalRunTime) {
		this.totalRunTime = totalRunTime;
	}
	public String getNormalRunTime() {
		return normalRunTime;
	}
	public void setNormalRunTime(String normalRunTime) {
		this.normalRunTime = normalRunTime;
	}
	public String getStandbyTime() {
		return standbyTime;
	}
	public void setStandbyTime(String standbyTime) {
		this.standbyTime = standbyTime;
	}
	public String getTotalErrorTime() {
		return totalErrorTime;
	}
	public void setTotalErrorTime(String totalErrorTime) {
		this.totalErrorTime = totalErrorTime;
	}
	public String getDeviceOEE() {
		return deviceOEE;
	}
	public void setDeviceOEE(String deviceOEE) {
		this.deviceOEE = deviceOEE;
	}
	public double getErrorRate() {
		return errorRate;
	}
	public void setErrorRate(double errorRate) {
		this.errorRate = errorRate;
	}
	public double getMttr() {
		return mttr;
	}
	public void setMttr(double mttr) {
		this.mttr = mttr;
	}
	public double getMtbf() {
		return mtbf;
	}
	public void setMtbf(double mtbf) {
		this.mtbf = mtbf;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * @return the errorCount
	 */
	public int getErrorCount() {
		return errorCount;
	}
	/**
	 * @param errorCount the errorCount to set
	 */
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
