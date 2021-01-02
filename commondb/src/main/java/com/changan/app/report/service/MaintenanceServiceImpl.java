package com.changan.app.report.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import com.changan.app.datamodel.EntityDefine;
import com.commondb.db.service.EntityService;

public class MaintenanceServiceImpl implements MaintenanceService{
	
	private EntityService entityService;  
	
	public EntityService getEntityService() {
		return entityService;
	}

	public void setEntityService(EntityService entityService) {
		this.entityService = entityService;
	}


	@Override
	public List queryNeedMaint(String lineId, int from, int length) {
		String columnString = "remindid, devicename, workdesc, "
				+ " date_add(lastworktime,interval periodDays day) as needmaintday, "
				+ " datediff(sysdate(),date_add(lastworktime,interval periodDays day)) as delayday ";
		
		String fromStr = " deviceLastMaintenanceView ";
		
		String whereInitStr = " date_add(lastworktime,interval periodDays day)<sysdate() order by devicename " ;
		
		if ((from>=0) && (length>0)) {
			whereInitStr = whereInitStr + " limit " + length + " offset " + from;
		}
		
		return this.entityService.dynSelect(columnString, fromStr, whereInitStr);
	}

	@Override
	public int queryNeedMaintListSize(String lineId) {
		String columnString = " count(*) as recCount ";
		
		String fromStr = " deviceLastMaintenanceView ";
		
		String whereInitStr = " date_add(lastworktime,interval periodDays day)<sysdate() order by devicename " ;
		
		List recCountList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		return Integer.parseInt(((Map)recCountList.get(0)).get("recCount").toString());
	}

	@Override
	public List queryMaintRec(String lineId, String deviceId, String timeBegin, String timeEnd, int from, int length) {
		String columnString = "deviceid, devicename, worktime, workdesc,workername,note ";
		
		String fromStr = " deviceMaintenanceRecView ";
		
		String whereInitStr = " 1=1 " ;
		if ((timeBegin!=null) && (timeBegin.length()>0)) {
			whereInitStr = whereInitStr + " and worktime>='" + timeBegin + "' ";
		}
		if ((timeEnd!=null) && (timeEnd.length()>0)) {
			whereInitStr = whereInitStr + " and worktime<='" + timeEnd + "' ";
		}
		if ((deviceId!=null) && (deviceId.length()>0)) {
			whereInitStr = whereInitStr + " and deviceid like '%" + deviceId + "%' ";
		}
		whereInitStr = whereInitStr + " order by devicename,worktime desc ";
		
		if ((from>=0) && (length>0)) {
			whereInitStr = whereInitStr + " limit " + length + " offset " + from;
		}
		
		return this.entityService.dynSelect(columnString, fromStr, whereInitStr);
	}

	@Override
	public int queryMaintRecListSize(String lineId, String deviceId, String timeBegin, String timeEnd) {
		String columnString = " count(*) as recCount ";
		
		String fromStr = " deviceMaintenanceRecView ";
		
		String whereInitStr = " 1=1 " ;
		if ((timeBegin!=null) && (timeBegin.length()>0)) {
			whereInitStr = whereInitStr + " and worktime>='" + timeBegin + "' ";
		}
		if ((timeEnd!=null) && (timeEnd.length()>0)) {
			whereInitStr = whereInitStr + " and worktime<='" + timeEnd + "' ";
		}
		if ((deviceId!=null) && (deviceId.length()>0)) {
			whereInitStr = whereInitStr + " and deviceid like '%" + deviceId + "%' ";
		}
		
		List recCountList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		return Integer.parseInt(((Map)recCountList.get(0)).get("recCount").toString());
	}

	@Override
	public String addMaintRec(String remindId, String workTime, String note,
			String workerName) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> maintRecValuesMap = new HashMap<String, Object>();
		maintRecValuesMap.put(EntityDefine.MaintRec_WorkTime_CN, workTime);
		maintRecValuesMap.put(EntityDefine.MaintRec_Note_CN, note);
		maintRecValuesMap.put(EntityDefine.MaintRec_WorkerName_CN, workerName);
		//构造关联属性，关联到保养标准记录上
		String[][] maintRecEntityArr = {{String.valueOf(EntityDefine.MaintSTDMetaId), remindId}};
		maintRecValuesMap.put("entityArr", maintRecEntityArr);
		String[] maintRecEntityLabel = {"保养标准"};
		maintRecValuesMap.put("entityLabel",maintRecEntityLabel);
		entityService.createEntity(EntityDefine.MaintRecMetaId, maintRecValuesMap);
		return null;
	}
    	
	
}
