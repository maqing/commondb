package com.changan.app.report.service;

import java.util.List;

import com.changan.app.report.model.Device;
import com.changan.app.report.model.DeviceWorkTime;

public abstract interface DeviceStatusStatService
{
  /* 按条件查询设备的时间指标 */	
  public abstract DeviceWorkTime getDeviceWorkTimeStat(String lineId, String deviceType, String deviceId, String timeBegin, String timeEnd);

  /* 查询某类型设备的时间指标  设备类型参数必须存在*/
  public abstract List<DeviceWorkTime> getDeviceTypeWorkTimeStat(String lineId, String deviceType, String timeBegin, String timeEnd);

  
  /* 按条件查询设备的故障汇总指标 */	
  public abstract DeviceWorkTime getDeviceErrorStat(String lineId, String deviceType, String deviceId, String timeBegin, String timeEnd);
  
  /* 按条件查询设备的top10故障 */	
  public abstract List<DeviceWorkTime> getDeviceTopTenError(String lineId, String deviceType, String deviceId, String timeBegin, String timeEnd);
  
  /* 查询某类型设备的故障指标  设备类型参数必须存在*/
  public abstract List<DeviceWorkTime> getDeviceTypeErrorStat(String lineId, String deviceType, String timeBegin, String timeEnd);
}
