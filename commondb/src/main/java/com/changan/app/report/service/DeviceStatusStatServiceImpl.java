package com.changan.app.report.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.changan.app.datamodel.EntityDefine;
import com.changan.app.report.model.Device;
import com.changan.app.report.model.DeviceWorkTime;
import com.commondb.common.DateUtil;
import com.commondb.db.service.EntityService;

public class DeviceStatusStatServiceImpl implements DeviceStatusStatService
{
	private EntityService entityService;  
	
	public EntityService getEntityService() {
		return entityService;
	}

	public void setEntityService(EntityService entityService) {
		this.entityService = entityService;
	}
	
	public DeviceWorkTime getDeviceWorkTimeStat(String lineId, String deviceType, String deviceId, String timeBegin, String timeEnd) {
		DeviceWorkTime resultItem = new DeviceWorkTime();
		DeviceWorkTime errorWorkItem = new DeviceWorkTime();
		//resultItem.setDeviceOEE("70%");
		double totalRunTime = 0;
		double normalRunTime = 0;
		double standbyTime = 0;
		double totalErrorTime = 0;
		double deviceOEE = 0;
		
		if (lineId != null) {
			//构造生产线查询条件
		}
		if ((deviceType!=null)&&(deviceType.length()>0)) {
			//查询特定设备类型  deviceType与metaid相等
			normalRunTime = getDeviceRunTimeStat(lineId, deviceType, deviceId, timeBegin, timeEnd);
			standbyTime = getDeviceStandbyTimeStat(lineId, deviceType, deviceId, timeBegin, timeEnd);
			errorWorkItem = getDeviceErrorTimeStat(lineId, deviceType, deviceId, timeBegin, timeEnd);
			
		} else {
			//查询所有设备
			normalRunTime = getAllDeviceRunTimeStat(lineId, deviceId, timeBegin, timeEnd);
			standbyTime = getAllDeviceStandbyTimeStat(lineId, deviceId, timeBegin, timeEnd);
			errorWorkItem = getAllDeviceErrorTimeStat(lineId,  deviceId, timeBegin, timeEnd);
		}
		totalErrorTime = Double.parseDouble(errorWorkItem.getTotalErrorTime());
		totalRunTime = normalRunTime + standbyTime + totalErrorTime ;
		deviceOEE = normalRunTime / (totalErrorTime + normalRunTime);
		
		resultItem.setNormalRunTime(String.valueOf(normalRunTime));
		resultItem.setStandbyTime(String.valueOf(standbyTime));
		resultItem.setTotalErrorTime(String.valueOf(totalErrorTime));
		resultItem.setTotalRunTime(String.valueOf(totalRunTime));
		resultItem.setErrorCount(errorWorkItem.getErrorCount());
		resultItem.setDeviceOEE(String.valueOf(deviceOEE));
		return resultItem;
	}
	
	private double getDeviceStandbyTimeStat(String lineId, String deviceType,
			String deviceId, String timeBegin, String timeEnd) {
		// TODO Auto-generated method stub
		/*String columnString = "a.s_date ,sum( "
			  	+ " case when date(b." + EntityDefine.Standby_StartTime_CN + ")=a.s_date and date(b." + EntityDefine.Standby_EndTime_CN + ")=a.s_date  then TIMESTAMPDIFF(second,b." + EntityDefine.Standby_StartTime_CN + ",b." + EntityDefine.Standby_EndTime_CN + ") "
			  	+ "	when date(b." + EntityDefine.Standby_StartTime_CN + ")=a.s_date and date(b." + EntityDefine.Standby_EndTime_CN + ")>a.s_date then TIMESTAMPDIFF(second,b." + EntityDefine.Standby_StartTime_CN + ",DATE_ADD(a.s_date,INTERVAL 1 DAY)) "
			  	+ "	when date(b." + EntityDefine.Standby_StartTime_CN + ")<a.s_date and date(b." + EntityDefine.Standby_EndTime_CN + ")=a.s_date then TIMESTAMPDIFF(second,a.s_date,b." + EntityDefine.Standby_EndTime_CN + ") "
			  	+ " when (" + EntityDefine.Standby_EndTime_CN + " is null or " + EntityDefine.Standby_EndTime_CN +"='') and a.s_date=date(now()) and date(b." + EntityDefine.Standby_StartTime_CN + ")<>a.s_date then TIMESTAMPDIFF(second,a.s_date,now()) "
			  	+ " when (" + EntityDefine.Standby_EndTime_CN + " is null or " + EntityDefine.Standby_EndTime_CN +"='') and date(b." + EntityDefine.Standby_StartTime_CN + ")=date(now()) then TIMESTAMPDIFF(second,b." + EntityDefine.Standby_StartTime_CN + ",now()) "
			  	+ "	else 86400 end ) as standbySecond ";*/
		
		String columnString = "ifnull(sum(case when  b." + EntityDefine.Standby_EndTime_CN + " is not null  "
				+ " then TIMESTAMPDIFF(second,GREATEST(b." + EntityDefine.Standby_StartTime_CN + ", '" + timeBegin +"'), least(b." + EntityDefine.Standby_EndTime_CN + ",'" + timeEnd + "'))"
			    + " when b." + EntityDefine.Standby_EndTime_CN + " is null "
			    + " then TIMESTAMPDIFF(second, GREATEST(b." + EntityDefine.Standby_StartTime_CN + ", '" + timeBegin + "'), '" + timeEnd + "')" 
		        + " else 0 " 
		        + " end)/3600,0) as cactime ";
		
		//String fromStr = " t_date a,t_entity_" + EntityDefine.StandbyMetaId + " b, t_entity_" + deviceType + " c, r_entity d ";
		String fromStr = " t_entity_" + EntityDefine.StandbyMetaId + " b, t_entity_" + deviceType + " c, r_entity d ";
		/*String whereInitStr = " date(b." + EntityDefine.Standby_StartTime_CN + ")<=a.s_date " 
			+ " and (date(b." + EntityDefine.Standby_EndTime_CN + ")>=a.s_date or ((" + EntityDefine.Standby_EndTime_CN 
			+ " is null or " + EntityDefine.Standby_EndTime_CN +"='') and a.s_date<=date(now()))) "
			+ " and d.entity2_id=c.id and d.meta2_id=" + deviceType +" "
			+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.StandbyMetaId + " "
			;*/
		String whereInitStr = " (date(b." + EntityDefine.Standby_StartTime_CN + ")<='" + timeBegin +"' and (date(b." + EntityDefine.Standby_EndTime_CN + ")>'" 
				+ timeBegin +"' or b." + EntityDefine.Standby_EndTime_CN + " is null) " 
				+ " or (date(b." + EntityDefine.Standby_StartTime_CN + ")>'" + timeBegin +"' and date(b." + EntityDefine.Standby_StartTime_CN + ")<'" + timeEnd + "')) "
				+ " and d.entity2_id=c.id and d.meta2_id=" + deviceType +" "
				+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.StandbyMetaId + " "
				;
		if (deviceId!=null) {
			whereInitStr = whereInitStr + " and d.entity2_id='" + deviceId + "' ";
		}
		double cacTime = 0;
		List cacTimeList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		if (cacTimeList.size()>0) { 
			cacTime = Double.parseDouble(((Map)cacTimeList.get(0)).get("cactime").toString());
		}
	    
		return cacTime;
		
	}

	private double getAllDeviceStandbyTimeStat(String lineId, 
			String deviceId, String timeBegin, String timeEnd) {
		// TODO Auto-generated method stub
		String columnString = "ifnull(sum(case when  b." + EntityDefine.Standby_EndTime_CN + " is not null  "
				+ " then TIMESTAMPDIFF(second,GREATEST(b." + EntityDefine.Standby_StartTime_CN + ", '" + timeBegin +"'), least(b." + EntityDefine.Standby_EndTime_CN + ",'" + timeEnd + "'))"
			    + " when b." + EntityDefine.Standby_EndTime_CN + " is null "
			    + " then TIMESTAMPDIFF(second, GREATEST(b." + EntityDefine.Standby_StartTime_CN + ", '" + timeBegin + "'), '" + timeEnd + "')" 
		        + " else 0 " 
		        + " end)/3600,0) as cactime ";
		String fromStr = " t_entity_" + EntityDefine.StandbyMetaId + " b, r_entity d ";
		String whereInitStr = " (date(b." + EntityDefine.Standby_StartTime_CN + ")<='" + timeBegin +"' and (date(b." + EntityDefine.Standby_EndTime_CN + ")>'" 
				+ timeBegin +"' or b." + EntityDefine.Standby_EndTime_CN + " is null) " 
				+ " or (date(b." + EntityDefine.Standby_StartTime_CN + ")>'" + timeBegin +"' and date(b." + EntityDefine.Standby_StartTime_CN + ")<'" + timeEnd + "')) "
				+ " and d.meta2_id in (" + EntityDefine.CgcDeviceMetaId + "," + EntityDefine.MachineDeviceMetaId
				+ "," + EntityDefine.RobotDeviceMetaId + "," + EntityDefine.ShockSandDeviceMetaId 
				+ "," + EntityDefine.TransferDeviceMetaId + ") "
				+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.StandbyMetaId + " "
				;
		if (deviceId!=null) {
			whereInitStr = whereInitStr + " and d.entity2_id='" + deviceId + "' ";
		}
		double cacTime = 0;
		List cacTimeList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		if (cacTimeList.size()>0) { 
			cacTime = Double.parseDouble(((Map)cacTimeList.get(0)).get("cactime").toString());
		}
		return cacTime;
	}

	private double getDeviceRunTimeStat(String lineId, String deviceType,
			String deviceId, String timeBegin, String timeEnd) {
		String columnString = "ifnull(sum(case when  b." + EntityDefine.Run_EndTime_CN + " is not null  "
				+ " then TIMESTAMPDIFF(second,GREATEST(b." + EntityDefine.Run_StartTime_CN + ", '" + timeBegin +"'), least(b." + EntityDefine.Run_EndTime_CN + ",'" + timeEnd + "'))"
			    + " when b." + EntityDefine.Run_EndTime_CN + " is null "
			    + " then TIMESTAMPDIFF(second, GREATEST(b." + EntityDefine.Run_StartTime_CN + ", '" + timeBegin + "'), '" + timeEnd + "')" 
		        + " else 0 " 
		        + " end)/3600,0) as cactime ";
		String fromStr = " t_entity_" + EntityDefine.RunMetaId + " b, t_entity_" + deviceType + " c, r_entity d ";
		String whereInitStr = " (date(b." + EntityDefine.Run_StartTime_CN + ")<='" + timeBegin +"' and (date(b." + EntityDefine.Run_EndTime_CN + ")>'" 
				+ timeBegin +"' or b." + EntityDefine.Run_EndTime_CN + " is null) " 
				+ " or (date(b." + EntityDefine.Run_StartTime_CN + ")>'" + timeBegin +"' and date(b." + EntityDefine.Run_StartTime_CN + ")<'" + timeEnd + "')) "
				+ " and d.entity2_id=c.id and d.meta2_id=" + deviceType +" "
				+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.RunMetaId + " "
				;
		if (deviceId!=null) {
			whereInitStr = whereInitStr + " and d.entity2_id='" + deviceId + "' ";
		}
		double cacTime = 0;
		List cacTimeList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		if (cacTimeList.size()>0) { 
			cacTime = Double.parseDouble(((Map)cacTimeList.get(0)).get("cactime").toString());
		}
		return cacTime;
	}
  
	private double getAllDeviceRunTimeStat(String lineId, 
			String deviceId, String timeBegin, String timeEnd) {
		String columnString = "ifnull(sum(case when  b." + EntityDefine.Run_EndTime_CN + " is not null  "
				+ " then TIMESTAMPDIFF(second,GREATEST(b." + EntityDefine.Run_StartTime_CN + ", '" + timeBegin +"'), least(b." + EntityDefine.Run_EndTime_CN + ",'" + timeEnd + "'))"
			    + " when b." + EntityDefine.Run_EndTime_CN + " is null "
			    + " then TIMESTAMPDIFF(second, GREATEST(b." + EntityDefine.Run_StartTime_CN + ", '" + timeBegin + "'), '" + timeEnd + "')" 
		        + " else 0 " 
		        + " end)/3600,0) as cactime ";
		String fromStr = " t_entity_" + EntityDefine.RunMetaId + " b, r_entity d ";
		String whereInitStr = " (date(b." + EntityDefine.Run_StartTime_CN + ")<='" + timeBegin +"' and (date(b." + EntityDefine.Run_EndTime_CN + ")>'" 
				+ timeBegin +"' or b." + EntityDefine.Run_EndTime_CN + " is null) " 
				+ " or (date(b." + EntityDefine.Run_StartTime_CN + ")>'" + timeBegin +"' and date(b." + EntityDefine.Run_StartTime_CN + ")<'" + timeEnd + "')) "
				+ " and d.meta2_id in (" + EntityDefine.CgcDeviceMetaId + "," + EntityDefine.MachineDeviceMetaId
				+ "," + EntityDefine.RobotDeviceMetaId + "," + EntityDefine.ShockSandDeviceMetaId 
				+ "," + EntityDefine.TransferDeviceMetaId + ") "
				+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.RunMetaId + " "
				;
		if (deviceId!=null) {
			whereInitStr = whereInitStr + " and d.entity2_id='" + deviceId + "' ";
		}
		double cacTime = 0;
		List cacTimeList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		if (cacTimeList.size()>0) { 
			cacTime = Double.parseDouble(((Map)cacTimeList.get(0)).get("cactime").toString());
		}
		return cacTime;
	}
  
	private DeviceWorkTime getDeviceErrorTimeStat(String lineId, String deviceType,
			String deviceId, String timeBegin, String timeEnd) {
		String columnString = "ifnull(sum(case when  b." + EntityDefine.Error_EndTime_CN + " is not null  "
				+ " then TIMESTAMPDIFF(second,GREATEST(b." + EntityDefine.Error_StartTime_CN + ", '" + timeBegin +"'), least(b." + EntityDefine.Error_EndTime_CN + ",'" + timeEnd + "'))"
			    + " when b." + EntityDefine.Error_EndTime_CN + " is null "
			    + " then TIMESTAMPDIFF(second, GREATEST(b." + EntityDefine.Error_StartTime_CN + ", '" + timeBegin + "'), '" + timeEnd + "')" 
		        + " else 0 " 
		        + " end)/3600,0) as cactime, count(b.id) as errorCount ";
		String fromStr = " t_entity_" + EntityDefine.ErrorMetaId + " b, t_entity_" + deviceType + " c, r_entity d ";
		String whereInitStr = " (date(b." + EntityDefine.Error_StartTime_CN + ")<='" + timeBegin +"' and (date(b." + EntityDefine.Error_EndTime_CN + ")>'" 
				+ timeBegin +"' or b." + EntityDefine.Error_EndTime_CN + " is null) " 
				+ " or (date(b." + EntityDefine.Error_StartTime_CN + ")>'" + timeBegin +"' and date(b." + EntityDefine.Error_StartTime_CN + ")<'" + timeEnd + "')) "
				+ " and d.entity2_id=c.id and d.meta2_id=" + deviceType +" "
				+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.ErrorMetaId + " "
				;
		if (deviceId!=null) {
			whereInitStr = whereInitStr + " and d.entity2_id='" + deviceId + "' ";
		}
		DeviceWorkTime deviceErrorWorkTimeItem = new DeviceWorkTime();
		List cacTimeList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		if (cacTimeList.size()>0) { 
			deviceErrorWorkTimeItem.setTotalErrorTime(((Map)cacTimeList.get(0)).get("cactime").toString());
			deviceErrorWorkTimeItem.setErrorCount(Integer.parseInt(((Map)cacTimeList.get(0)).get("errorCount").toString()));
		}
		return deviceErrorWorkTimeItem;
	}
  
	private DeviceWorkTime getAllDeviceErrorTimeStat(String lineId,
			String deviceId, String timeBegin, String timeEnd) {
		String columnString = "ifnull(sum(case when  b." + EntityDefine.Error_EndTime_CN + " is not null  "
				+ " then TIMESTAMPDIFF(second,GREATEST(b." + EntityDefine.Error_StartTime_CN + ", '" + timeBegin +"'), least(b." + EntityDefine.Error_EndTime_CN + ",'" + timeEnd + "'))"
			    + " when b." + EntityDefine.Error_EndTime_CN + " is null "
			    + " then TIMESTAMPDIFF(second, GREATEST(b." + EntityDefine.Error_StartTime_CN + ", '" + timeBegin + "'), '" + timeEnd + "')" 
		        + " else 0 " 
		        + " end)/3600,0) as cactime, count(b.id) as errorCount  ";
		String fromStr = " t_entity_" + EntityDefine.ErrorMetaId + " b, r_entity d ";
		String whereInitStr = " (date(b." + EntityDefine.Error_StartTime_CN + ")<='" + timeBegin +"' and (date(b." + EntityDefine.Error_EndTime_CN + ")>'" 
				+ timeBegin +"' or b." + EntityDefine.Error_EndTime_CN + " is null) " 
				+ " or (date(b." + EntityDefine.Error_StartTime_CN + ")>'" + timeBegin +"' and date(b." + EntityDefine.Error_StartTime_CN + ")<'" + timeEnd + "')) "
				+ " and d.meta2_id in (" + EntityDefine.CgcDeviceMetaId + "," + EntityDefine.MachineDeviceMetaId
				+ "," + EntityDefine.RobotDeviceMetaId + "," + EntityDefine.ShockSandDeviceMetaId 
				+ "," + EntityDefine.TransferDeviceMetaId + ") "
				+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.ErrorMetaId + " "
				;
		if (deviceId!=null) {
			whereInitStr = whereInitStr + " and d.entity2_id='" + deviceId + "' ";
		}
		DeviceWorkTime deviceErrorWorkTimeItem = new DeviceWorkTime();
		List cacTimeList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		if (cacTimeList.size()>0) { 
			deviceErrorWorkTimeItem.setTotalErrorTime(((Map)cacTimeList.get(0)).get("cactime").toString());
			deviceErrorWorkTimeItem.setErrorCount(Integer.parseInt(((Map)cacTimeList.get(0)).get("errorCount").toString()));
		}
		return deviceErrorWorkTimeItem;
	}

	
	private String getDeviceNameColByDeviceType(String deviceType) {
		String deviceNameCol = "";
		if (deviceType.equals(String.valueOf(EntityDefine.CgcDeviceMetaId))) {
			deviceNameCol = EntityDefine.CgcDevice_Name_CN;
		} else if (deviceType.equals(String.valueOf(EntityDefine.MachineDeviceMetaId))) {
			deviceNameCol = EntityDefine.MachineDevice_Name_CN;
		} else if (deviceType.equals(String.valueOf(EntityDefine.RobotDeviceMetaId))) {
			deviceNameCol = EntityDefine.RobotDevice_Name_CN;
		} else if (deviceType.equals(String.valueOf(EntityDefine.ShockSandDeviceMetaId))) {
			deviceNameCol = EntityDefine.ShockSandDevice_Name_CN;
		} else if (deviceType.equals(String.valueOf(EntityDefine.TransferDeviceMetaId))) {
			deviceNameCol = EntityDefine.TransferDevice_Name_CN;
		}
		return deviceNameCol;
	}
	
	private List<DeviceWorkTime> getDeviceStandbyTimeStatForType(String lineId, String deviceType, String timeBegin, String timeEnd) {
		String deviceNameCol = getDeviceNameColByDeviceType(deviceType);
		String columnString = "c." + deviceNameCol + " as deviceName, (select ifnull(sum(case when  b." + EntityDefine.Standby_EndTime_CN + " is not null  "
				+ " then TIMESTAMPDIFF(second,GREATEST(b." + EntityDefine.Standby_StartTime_CN + ", '" + timeBegin +"'), least(b." + EntityDefine.Standby_EndTime_CN + ",'" + timeEnd + "'))"
			    + " when b." + EntityDefine.Standby_EndTime_CN + " is null "
			    + " then TIMESTAMPDIFF(second, GREATEST(b." + EntityDefine.Standby_StartTime_CN + ", '" + timeBegin + "'), '" + timeEnd + "')" 
		        + " else 0 " 
		        + " end)/3600,0)  from t_entity_" + EntityDefine.StandbyMetaId + " b, r_entity d " 
		        + " where  (date(b." + EntityDefine.Standby_StartTime_CN + ")<='" + timeBegin +"' and (date(b." + EntityDefine.Standby_EndTime_CN + ")>'" 
				+ timeBegin +"' or b." + EntityDefine.Standby_EndTime_CN + " is null) " 
				+ " or (date(b." + EntityDefine.Standby_StartTime_CN + ")>'" + timeBegin +"' and date(b." + EntityDefine.Standby_StartTime_CN + ")<'" + timeEnd + "')) "
				+ " and d.entity2_id=c.id and d.meta2_id=" + deviceType +" "
				+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.StandbyMetaId + ")  as cactime ";
		
		String fromStr = " t_entity_" + deviceType + " c " ;
		String whereInitStr = " 1=1 order by c." + deviceNameCol ;
		List standbyTimeStatList =  new ArrayList<DeviceWorkTime>();
		double cacTime = 0;
		List cacTimeList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		if (cacTimeList!=null) { 
			for (int i=0; i<cacTimeList.size(); i++) {
				DeviceWorkTime deviceWorkTimeItem = new DeviceWorkTime();
				deviceWorkTimeItem.setDeviceName(((Map)cacTimeList.get(i)).get("deviceName").toString());
				deviceWorkTimeItem.setStandbyTime(((Map)cacTimeList.get(i)).get("cactime").toString());
				standbyTimeStatList.add(deviceWorkTimeItem);
			}
		}
		return standbyTimeStatList;
	}
	
	private List<DeviceWorkTime> getDeviceRunTimeStatForType(String lineId, String deviceType, String timeBegin, String timeEnd) {
		String deviceNameCol = getDeviceNameColByDeviceType(deviceType);
		String columnString = "c." + deviceNameCol + " as deviceName, (select ifnull(sum(case when  b." + EntityDefine.Run_EndTime_CN + " is not null  "
				+ " then TIMESTAMPDIFF(second,GREATEST(b." + EntityDefine.Run_StartTime_CN + ", '" + timeBegin +"'), least(b." + EntityDefine.Run_EndTime_CN + ",'" + timeEnd + "'))"
			    + " when b." + EntityDefine.Run_EndTime_CN + " is null "
			    + " then TIMESTAMPDIFF(second, GREATEST(b." + EntityDefine.Run_StartTime_CN + ", '" + timeBegin + "'), '" + timeEnd + "')" 
		        + " else 0 " 
		        + " end)/3600,0)  from t_entity_" + EntityDefine.RunMetaId + " b, r_entity d " 
		        + " where  (date(b." + EntityDefine.Run_StartTime_CN + ")<='" + timeBegin +"' and (date(b." + EntityDefine.Run_EndTime_CN + ")>'" 
				+ timeBegin +"' or b." + EntityDefine.Run_EndTime_CN + " is null) " 
				+ " or (date(b." + EntityDefine.Run_StartTime_CN + ")>'" + timeBegin +"' and date(b." + EntityDefine.Run_StartTime_CN + ")<'" + timeEnd + "')) "
				+ " and d.entity2_id=c.id and d.meta2_id=" + deviceType +" "
				+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.RunMetaId + ")  as cactime ";
		
		String fromStr = " t_entity_" + deviceType + " c " ;
		String whereInitStr = " 1=1 order by c." + deviceNameCol ;
		List runTimeStatList =  new ArrayList<DeviceWorkTime>();
		double cacTime = 0;
		List cacTimeList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		if (cacTimeList!=null) { 
			for (int i=0; i<cacTimeList.size(); i++) {
				DeviceWorkTime deviceWorkTimeItem = new DeviceWorkTime();
				deviceWorkTimeItem.setDeviceName(((Map)cacTimeList.get(i)).get("deviceName").toString());
				deviceWorkTimeItem.setNormalRunTime(((Map)cacTimeList.get(i)).get("cactime").toString());
				runTimeStatList.add(deviceWorkTimeItem);
			}
		}
		return runTimeStatList;
	}
	
	private List<DeviceWorkTime> getDeviceErrorTimeStatForType(String lineId, String deviceType, String timeBegin, String timeEnd) {
		String deviceNameCol = getDeviceNameColByDeviceType(deviceType);
		String columnString = "c." + deviceNameCol + " as deviceName, (select ifnull(sum(case when  b." + EntityDefine.Error_EndTime_CN + " is not null  "
				+ " then TIMESTAMPDIFF(second,GREATEST(b." + EntityDefine.Error_StartTime_CN + ", '" + timeBegin +"'), least(b." + EntityDefine.Error_EndTime_CN + ",'" + timeEnd + "'))"
			    + " when b." + EntityDefine.Error_EndTime_CN + " is null "
			    + " then TIMESTAMPDIFF(second, GREATEST(b." + EntityDefine.Error_StartTime_CN + ", '" + timeBegin + "'), '" + timeEnd + "')" 
		        + " else 0 " 
		        + " end)/3600,0)  from t_entity_" + EntityDefine.ErrorMetaId + " b, r_entity d " 
		        + " where  (date(b." + EntityDefine.Error_StartTime_CN + ")<='" + timeBegin +"' and (date(b." + EntityDefine.Error_EndTime_CN + ")>'" 
				+ timeBegin +"' or b." + EntityDefine.Error_EndTime_CN + " is null) " 
				+ " or (date(b." + EntityDefine.Error_StartTime_CN + ")>'" + timeBegin +"' and date(b." + EntityDefine.Error_StartTime_CN + ")<'" + timeEnd + "')) "
				+ " and d.entity2_id=c.id and d.meta2_id=" + deviceType +" "
				+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.ErrorMetaId + ")  as cactime, "
				+ " (select count(e.id) from t_entity_" + EntityDefine.ErrorMetaId + " e, r_entity f " 
		        + " where  (date(e." + EntityDefine.Error_StartTime_CN + ")<='" + timeBegin +"' and (date(e." + EntityDefine.Error_EndTime_CN + ")>'" 
				+ timeBegin +"' or e." + EntityDefine.Error_EndTime_CN + " is null) " 
				+ " or (date(e." + EntityDefine.Error_StartTime_CN + ")>'" + timeBegin +"' and date(e." + EntityDefine.Error_StartTime_CN + ")<'" + timeEnd + "')) "
				+ " and f.entity2_id=c.id and f.meta2_id=" + deviceType +" "
				+ " and f.entity1_id=e.id and f.meta1_id=" + EntityDefine.ErrorMetaId + ")  as errorCount ";
		
		String fromStr = " t_entity_" + deviceType + " c " ;
		String whereInitStr = " 1=1 order by c." + deviceNameCol ;
		List errorTimeStatList =  new ArrayList<DeviceWorkTime>();
		List cacTimeList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		if (cacTimeList!=null) { 
			for (int i=0; i<cacTimeList.size(); i++) {
				DeviceWorkTime deviceWorkTimeItem = new DeviceWorkTime();
				deviceWorkTimeItem.setDeviceName(((Map)cacTimeList.get(i)).get("deviceName").toString());
				deviceWorkTimeItem.setTotalErrorTime(((Map)cacTimeList.get(i)).get("cactime").toString());
				deviceWorkTimeItem.setErrorCount(Integer.parseInt(((Map)cacTimeList.get(i)).get("errorCount").toString()));
				errorTimeStatList.add(deviceWorkTimeItem);
			}
		}
		return errorTimeStatList;
	}
	
	@Override
	public List<DeviceWorkTime> getDeviceTypeWorkTimeStat(String lineId,
			String deviceType, String timeBegin, String timeEnd) {
		// TODO Auto-generated method stub
		if (lineId != null) {
			//构造生产线查询条件
		}
		List workTimeStatList =  new ArrayList<List<DeviceWorkTime>>();
		List standbyTimeStatList =  new ArrayList<DeviceWorkTime>();
		List runTimeStatList =  new ArrayList<DeviceWorkTime>();
		List errorTimeStatList =  new ArrayList<DeviceWorkTime>();
		if ((deviceType!=null)&&(deviceType.length()>0)) {
			//查询特定设备类型  deviceType与metaid相等
			standbyTimeStatList = getDeviceStandbyTimeStatForType(lineId, deviceType,  timeBegin, timeEnd);
			runTimeStatList = getDeviceRunTimeStatForType(lineId, deviceType,  timeBegin, timeEnd);
			errorTimeStatList = getDeviceErrorTimeStatForType(lineId, deviceType,  timeBegin, timeEnd);
		}
		
		//合并赋值到一个数组里
		for (int i=0; i<standbyTimeStatList.size(); i++) {
			DeviceWorkTime deviceWorkTimeItem =(DeviceWorkTime) standbyTimeStatList.get(i);
			deviceWorkTimeItem.setNormalRunTime(((DeviceWorkTime)runTimeStatList.get(i)).getNormalRunTime());
			deviceWorkTimeItem.setTotalErrorTime(((DeviceWorkTime)errorTimeStatList.get(i)).getTotalErrorTime());

			double normalRunTime = Double.parseDouble(deviceWorkTimeItem.getNormalRunTime());
			double totalErrorTime = Double.parseDouble(deviceWorkTimeItem.getTotalErrorTime());
			
			deviceWorkTimeItem.setTotalRunTime(
					String.valueOf(normalRunTime
					+ Double.parseDouble(deviceWorkTimeItem.getStandbyTime()) 
					+ totalErrorTime)
					);
			double deviceOEE = normalRunTime / (totalErrorTime + normalRunTime);

			deviceWorkTimeItem.setDeviceOEE(String.valueOf(deviceOEE));
		}
		//workTimeStatList.add(standbyTimeStatList);
		//workTimeStatList.add(runTimeStatList);
		//workTimeStatList.add(errorTimeStatList);
		return standbyTimeStatList;
	}

	@Override
	public DeviceWorkTime getDeviceErrorStat(String lineId, String deviceType,
			String deviceId, String timeBegin, String timeEnd) {
		// TODO Auto-generated method stub
		DeviceWorkTime resultItem = new DeviceWorkTime();
		DeviceWorkTime errorWorkItem = new DeviceWorkTime();
		double errorRate = 0;
		double normalRunTime = 0;
		double totalErrorTime = 0;
		double mttr = 0;
		double mtbf = 0;
		
		if (lineId != null) {
			//构造生产线查询条件
		}
		if ((deviceType!=null)&&(deviceType.length()>0)) {
			//查询特定设备类型  deviceType与metaid相等
			normalRunTime = getDeviceRunTimeStat(lineId, deviceType, deviceId, timeBegin, timeEnd);
			errorWorkItem = getDeviceErrorTimeStat(lineId, deviceType, deviceId, timeBegin, timeEnd);
			
		} else {
			//查询所有设备
			normalRunTime = getAllDeviceRunTimeStat(lineId, deviceId, timeBegin, timeEnd);
			errorWorkItem = getAllDeviceErrorTimeStat(lineId,  deviceId, timeBegin, timeEnd);
		}
		totalErrorTime = Double.parseDouble(errorWorkItem.getTotalErrorTime());
		if ((normalRunTime + totalErrorTime)>0) 
			errorRate = totalErrorTime / (normalRunTime + totalErrorTime);
		if (errorWorkItem.getErrorCount()>0) {
			mttr = 60 * totalErrorTime/errorWorkItem.getErrorCount();
			mtbf = 60 * normalRunTime/errorWorkItem.getErrorCount();
		}
		
		resultItem.setNormalRunTime(String.valueOf(normalRunTime));
		resultItem.setTotalErrorTime(String.valueOf(totalErrorTime));
		resultItem.setErrorCount(errorWorkItem.getErrorCount());
		resultItem.setErrorRate(errorRate);
		resultItem.setMttr(mttr);
		resultItem.setMtbf(mtbf);
		return resultItem;
	}

	@Override
	public List<DeviceWorkTime> getDeviceTopTenError(String lineId,
			String deviceType, String deviceId, String timeBegin, String timeEnd) {
		// TODO Auto-generated method stub
		String columnString = "";
		String fromStr = "";
		String whereInitStr = "";
		if (lineId != null) {
			//构造生产线查询条件
		}
		columnString = " b." + EntityDefine.Error_Code_CN + " as errCode, count(b.id) as errCount ";
		if ((deviceType!=null)&&(deviceType.length()>0)) {
			//查询特定设备类型  deviceType与metaid相等
			fromStr = " t_entity_" + EntityDefine.ErrorMetaId + " b, t_entity_" + deviceType + " c, r_entity d ";
			whereInitStr = " (date(b." + EntityDefine.Error_StartTime_CN + ")<='" + timeBegin +"' and (date(b." + EntityDefine.Error_EndTime_CN + ")>'" 
					+ timeBegin +"' or b." + EntityDefine.Error_EndTime_CN + " is null) " 
					+ " or (date(b." + EntityDefine.Error_StartTime_CN + ")>'" + timeBegin +"' and date(b." + EntityDefine.Error_StartTime_CN + ")<'" + timeEnd + "')) "
					+ " and d.entity2_id=c.id and d.meta2_id=" + deviceType +" "
					+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.ErrorMetaId + " "
					;
		} else {
			//查询所有设备
			fromStr = " t_entity_" + EntityDefine.ErrorMetaId + " b, r_entity d ";
			whereInitStr = " (date(b." + EntityDefine.Error_StartTime_CN + ")<='" + timeBegin +"' and (date(b." + EntityDefine.Error_EndTime_CN + ")>'" 
					+ timeBegin +"' or b." + EntityDefine.Error_EndTime_CN + " is null) " 
					+ " or (date(b." + EntityDefine.Error_StartTime_CN + ")>'" + timeBegin +"' and date(b." + EntityDefine.Error_StartTime_CN + ")<'" + timeEnd + "')) "
					+ " and d.meta2_id in (" + EntityDefine.CgcDeviceMetaId + "," + EntityDefine.MachineDeviceMetaId
					+ "," + EntityDefine.RobotDeviceMetaId + "," + EntityDefine.ShockSandDeviceMetaId 
					+ "," + EntityDefine.TransferDeviceMetaId + ") "
					+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.ErrorMetaId + " "
					;
		}
		if (deviceId!=null) {
			whereInitStr = whereInitStr + " and d.entity2_id='" + deviceId + "' ";
		}
		whereInitStr = whereInitStr + " group by b." + EntityDefine.Error_Code_CN
				+ " order by errcount desc limit 10 ";
		List errorTopList =  new ArrayList<DeviceWorkTime>();
		List errorMapList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		if (errorMapList!=null) { 
			for (int i=0; i<errorMapList.size(); i++) {
				DeviceWorkTime deviceWorkTimeItem = new DeviceWorkTime();
				deviceWorkTimeItem.setErrorCode(((Map)errorMapList.get(i)).get("errCode").toString());
				deviceWorkTimeItem.setErrorCount(Integer.parseInt(((Map)errorMapList.get(i)).get("errCount").toString()));
				errorTopList.add(deviceWorkTimeItem);
			}
		}
		return errorTopList;
	}

	@Override
	public List<DeviceWorkTime> getDeviceTypeErrorStat(String lineId,
			String deviceType, String timeBegin, String timeEnd) {
		// TODO Auto-generated method stub
		if (lineId != null) {
			//构造生产线查询条件
		}
		List runTimeStatList =  new ArrayList<DeviceWorkTime>();
		List errorTimeStatList =  new ArrayList<DeviceWorkTime>();
		if ((deviceType!=null)&&(deviceType.length()>0)) {
			//查询特定设备类型  deviceType与metaid相等
			runTimeStatList = getDeviceRunTimeStatForType(lineId, deviceType,  timeBegin, timeEnd);
			errorTimeStatList = getDeviceErrorTimeStatForType(lineId, deviceType,  timeBegin, timeEnd);
		}
		double errorRate = 0;
		double normalRunTime = 0;
		double totalErrorTime = 0;
		double mttr = 0;
		double mtbf = 0;
		
		//合并赋值到一个数组里
		for (int i=0; i<errorTimeStatList.size(); i++) {
			DeviceWorkTime deviceWorkTimeItem =(DeviceWorkTime) errorTimeStatList.get(i);
			totalErrorTime = Double.parseDouble(deviceWorkTimeItem.getTotalErrorTime());
			normalRunTime = Double.parseDouble(((DeviceWorkTime)runTimeStatList.get(i)).getNormalRunTime());
			if ((normalRunTime + totalErrorTime)>0) 
				errorRate = totalErrorTime / (normalRunTime + totalErrorTime);
			if (deviceWorkTimeItem.getErrorCount()>0) {
				mttr = 60 * totalErrorTime/deviceWorkTimeItem.getErrorCount();
				mtbf = 60 * normalRunTime/deviceWorkTimeItem.getErrorCount();
			}
			
			deviceWorkTimeItem.setErrorRate(errorRate);
			deviceWorkTimeItem.setMtbf(mtbf);
			deviceWorkTimeItem.setMttr(mttr);
		}
		return errorTimeStatList;
	}

  
}
