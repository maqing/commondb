package com.changan.plcinterface.model;

import java.util.Date;

public class Basicdevice extends BasicdeviceKey {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column robotdevice102.Wprecordvalue
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	private Long wprecordvalue;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column robotdevice102.PLCtime
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	private Date plctime;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column robotdevice102.Deviceid
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	private Integer deviceid;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column robotdevice102.Devicename
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	private String devicename;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column robotdevice102.Devicestatus
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	private Long devicestatus;
	
	private String devicestatusName;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column robotdevice102.GrapAname
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */

	private Long faultcode;
	
	/* 工件名称  */
	private String workpiecename;

	/* 刀具编码  */
    private String toolcode;

    /* 工序代码 */
    private Integer processcode;
    
	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column robotdevice102.Wprecordvalue
	 * @return  the value of robotdevice102.Wprecordvalue
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	public Long getWprecordvalue() {
		return wprecordvalue;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column robotdevice102.Wprecordvalue
	 * @param wprecordvalue  the value for robotdevice102.Wprecordvalue
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	public void setWprecordvalue(Long wprecordvalue) {
		this.wprecordvalue = wprecordvalue;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column robotdevice102.PLCtime
	 * @return  the value of robotdevice102.PLCtime
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	public Date getPlctime() {
		return plctime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column robotdevice102.PLCtime
	 * @param plctime  the value for robotdevice102.PLCtime
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	public void setPlctime(Date plctime) {
		this.plctime = plctime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column robotdevice102.Deviceid
	 * @return  the value of robotdevice102.Deviceid
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	public Integer getDeviceid() {
		return deviceid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column robotdevice102.Deviceid
	 * @param deviceid  the value for robotdevice102.Deviceid
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	public void setDeviceid(Integer deviceid) {
		this.deviceid = deviceid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column robotdevice102.Devicename
	 * @return  the value of robotdevice102.Devicename
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	public String getDevicename() {
		return devicename;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column robotdevice102.Devicename
	 * @param devicename  the value for robotdevice102.Devicename
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column robotdevice102.Devicestatus
	 * @return  the value of robotdevice102.Devicestatus
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	public Long getDevicestatus() {
		return devicestatus;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column robotdevice102.Devicestatus
	 * @param devicestatus  the value for robotdevice102.Devicestatus
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	public void setDevicestatus(Long devicestatus) {
		this.devicestatus = devicestatus;
	}

	/**
	 * @return the devicestatusName
	 */
	public String getDevicestatusName() {
		return devicestatusName;
	}

	/**
	 * @param devicestatusName the devicestatusName to set
	 */
	public void setDevicestatusName(String devicestatusName) {
		this.devicestatusName = devicestatusName;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column robotdevice102.Faultcode
	 * @return  the value of robotdevice102.Faultcode
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	public Long getFaultcode() {
		return faultcode;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column robotdevice102.Faultcode
	 * @param faultcode  the value for robotdevice102.Faultcode
	 * @abatorgenerated  Thu Jan 21 21:19:27 CST 2016
	 */
	public void setFaultcode(Long faultcode) {
		this.faultcode = faultcode;
	}

	/**
	 * @return the workpiecename
	 */
	public String getWorkpiecename() {
		return workpiecename;
	}

	/**
	 * @param workpiecename the workpiecename to set
	 */
	public void setWorkpiecename(String workpiecename) {
		this.workpiecename = workpiecename;
	}

	/**
	 * @return the toolcode
	 */
	public String getToolcode() {
		return toolcode;
	}

	/**
	 * @param toolcode the toolcode to set
	 */
	public void setToolcode(String toolcode) {
		this.toolcode = toolcode;
	}

	/**
	 * @return the processcode
	 */
	public Integer getProcesscode() {
		return processcode;
	}

	/**
	 * @param processcode the processcode to set
	 */
	public void setProcesscode(Integer processcode) {
		this.processcode = processcode;
	}

}