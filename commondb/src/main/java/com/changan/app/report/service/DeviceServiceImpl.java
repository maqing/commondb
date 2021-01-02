package com.changan.app.report.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.changan.app.datamodel.EntityDefine;
import com.changan.app.report.model.Device;
import com.commondb.db.service.EntityService;

public class DeviceServiceImpl implements DeviceService {
	private EntityService entityService;  
	
	public EntityService getEntityService() {
		return entityService;
	}

	public void setEntityService(EntityService entityService) {
		this.entityService = entityService;
	}
	
	@Override
	public List<Device> getDevice(String lineId) {
		// TODO Auto-generated method stub
		ArrayList<Device> deviceItemList =  new ArrayList<Device>();
		
		//查询 流水线设备
		List transferDeviceMapList = getSpecDevice(EntityDefine.TransferDeviceMetaId, EntityDefine.TransferDevice_ID_CN,
				EntityDefine.TransferDevice_Name_CN);
		for (int i=0; i<transferDeviceMapList.size(); i++) {
			Map transferDeviceMapItem = (Map) transferDeviceMapList.get(i);
			Device deviceItem = new Device();
			deviceItem.setDeviceId((String) transferDeviceMapItem.get("id"));
			deviceItem.setDeviceCode((String) transferDeviceMapItem.get("code"));
			deviceItem.setDeviceName((String) transferDeviceMapItem.get("name"));
			deviceItem.setDeviceType(String.valueOf(EntityDefine.TransferDeviceMetaId));
			deviceItemList.add(deviceItem);
		}
		
		//查询 机器手设备
		List robotDeviceMapList = getSpecDevice(EntityDefine.RobotDeviceMetaId, EntityDefine.RobotDevice_ID_CN,
				EntityDefine.RobotDevice_Name_CN);
		for (int i=0; i<robotDeviceMapList.size(); i++) {
			Map robotDeviceMapItem = (Map) robotDeviceMapList.get(i);
			Device deviceItem = new Device();
			deviceItem.setDeviceId((String) robotDeviceMapItem.get("id"));
			deviceItem.setDeviceCode((String) robotDeviceMapItem.get("code"));
			deviceItem.setDeviceName((String) robotDeviceMapItem.get("name"));
			deviceItem.setDeviceType(String.valueOf(EntityDefine.RobotDeviceMetaId));
			deviceItemList.add(deviceItem);
		}
		
		//查询 机器手设备
		List shockSandDeviceMapList = getSpecDevice(EntityDefine.ShockSandDeviceMetaId, EntityDefine.ShockSandDevice_ID_CN,
				EntityDefine.ShockSandDevice_Name_CN);
		for (int i=0; i<shockSandDeviceMapList.size(); i++) {
			Map shockSandDeviceMapItem = (Map) shockSandDeviceMapList.get(i);
			Device deviceItem = new Device();
			deviceItem.setDeviceId((String) shockSandDeviceMapItem.get("id"));
			deviceItem.setDeviceCode((String) shockSandDeviceMapItem.get("code"));
			deviceItem.setDeviceName((String) shockSandDeviceMapItem.get("name"));
			deviceItem.setDeviceType(String.valueOf(EntityDefine.ShockSandDeviceMetaId));
			deviceItemList.add(deviceItem);
		}
		
		//查询 机床设备
		List machineDeviceMapList = getSpecDevice(EntityDefine.MachineDeviceMetaId, EntityDefine.MachineDevice_ID_CN,
				EntityDefine.MachineDevice_Name_CN);
		for (int i=0; i<machineDeviceMapList.size(); i++) {
			Map machineDeviceMapItem = (Map) machineDeviceMapList.get(i);
			Device deviceItem = new Device();
			deviceItem.setDeviceId((String) machineDeviceMapItem.get("id"));
			deviceItem.setDeviceCode((String) machineDeviceMapItem.get("code"));
			deviceItem.setDeviceName((String) machineDeviceMapItem.get("name"));
			deviceItem.setDeviceType(String.valueOf(EntityDefine.MachineDeviceMetaId));
			deviceItemList.add(deviceItem);
		}
		
		//查询 CGC设备
		List cgcDeviceMapList = getSpecDevice(EntityDefine.CgcDeviceMetaId, EntityDefine.CgcDevice_ID_CN,
				EntityDefine.CgcDevice_Name_CN);
		for (int i=0; i<cgcDeviceMapList.size(); i++) {
			Map cgcDeviceMapItem = (Map) cgcDeviceMapList.get(i);
			Device deviceItem = new Device();
			deviceItem.setDeviceId((String) cgcDeviceMapItem.get("id"));
			deviceItem.setDeviceCode((String) cgcDeviceMapItem.get("code"));
			deviceItem.setDeviceName((String) cgcDeviceMapItem.get("name"));
			deviceItem.setDeviceType(String.valueOf(EntityDefine.CgcDeviceMetaId));
			deviceItemList.add(deviceItem);
		}
		
		return deviceItemList;
	}

	private List getSpecDevice(int deviceMetaId, String codeColName, String nameColName) {
		String columnString = "id," + codeColName + " as code," + nameColName + " as name ";
		String fromStr = " t_entity_" + deviceMetaId;
		String whereInitStr = " 1=1 order by " + codeColName;
		return this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		
	}
	
}
