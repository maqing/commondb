package com.changan.plcinterface.model;

public class Robotdevice  extends Basicdevice {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column robotdevice.Devicename
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    private String devicename;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column robotdevice.Devicestatus
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    private Long devicestatus;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column robotdevice.GrapAname
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    private String grapaname;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column robotdevice.GrapBname
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    private String grapbname;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column robotdevice.Faultcode
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    private Long faultcode;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column robotdevice.Processcode
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    private Integer processcode;
	
	private String processName;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column robotdevice.Devicename
     *
     * @return the value of robotdevice.Devicename
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public String getDevicename() {
        return devicename;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column robotdevice.Devicename
     *
     * @param devicename the value for robotdevice.Devicename
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column robotdevice.Devicestatus
     *
     * @return the value of robotdevice.Devicestatus
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public Long getDevicestatus() {
        return devicestatus;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column robotdevice.Devicestatus
     *
     * @param devicestatus the value for robotdevice.Devicestatus
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public void setDevicestatus(Long devicestatus) {
        this.devicestatus = devicestatus;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column robotdevice.GrapAname
     *
     * @return the value of robotdevice.GrapAname
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public String getGrapaname() {
        return grapaname;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column robotdevice.GrapAname
     *
     * @param grapaname the value for robotdevice.GrapAname
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public void setGrapaname(String grapaname) {
        this.grapaname = grapaname;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column robotdevice.GrapBname
     *
     * @return the value of robotdevice.GrapBname
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public String getGrapbname() {
        return grapbname;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column robotdevice.GrapBname
     *
     * @param grapbname the value for robotdevice.GrapBname
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public void setGrapbname(String grapbname) {
        this.grapbname = grapbname;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column robotdevice.Faultcode
     *
     * @return the value of robotdevice.Faultcode
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public Long getFaultcode() {
        return faultcode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column robotdevice.Faultcode
     *
     * @param faultcode the value for robotdevice.Faultcode
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public void setFaultcode(Long faultcode) {
        this.faultcode = faultcode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column robotdevice.Processcode
     *
     * @return the value of robotdevice.Processcode
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public Integer getProcesscode() {
        return processcode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column robotdevice.Processcode
     *
     * @param processcode the value for robotdevice.Processcode
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public void setProcesscode(Integer processcode) {
        this.processcode = processcode;
    }

	/**
	 * @return the processName
	 */
	public String getProcessName() {
		return processName;
	}

	/**
	 * @param processName the processName to set
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}
}