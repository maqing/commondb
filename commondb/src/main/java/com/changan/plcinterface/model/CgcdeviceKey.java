package com.changan.plcinterface.model;

import java.util.Date;

public class CgcdeviceKey {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column robotdevice.Deviceid
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    private Integer deviceid;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column robotdevice.PLCtime
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    private Date plctime;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column robotdevice.Wprecordvalue
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    private Long wprecordvalue;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column robotdevice.Deviceid
     *
     * @return the value of robotdevice.Deviceid
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public Integer getDeviceid() {
        return deviceid;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column robotdevice.Deviceid
     *
     * @param deviceid the value for robotdevice.Deviceid
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column robotdevice.PLCtime
     *
     * @return the value of robotdevice.PLCtime
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public Date getPlctime() {
        return plctime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column robotdevice.PLCtime
     *
     * @param plctime the value for robotdevice.PLCtime
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public void setPlctime(Date plctime) {
        this.plctime = plctime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column robotdevice.Wprecordvalue
     *
     * @return the value of robotdevice.Wprecordvalue
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public Long getWprecordvalue() {
        return wprecordvalue;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column robotdevice.Wprecordvalue
     *
     * @param wprecordvalue the value for robotdevice.Wprecordvalue
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    public void setWprecordvalue(Long wprecordvalue) {
        this.wprecordvalue = wprecordvalue;
    }
}