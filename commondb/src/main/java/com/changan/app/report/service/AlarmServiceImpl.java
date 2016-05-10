package com.changan.app.report.service;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.changan.app.datamodel.EntityDefine;
import com.changan.app.report.dao.TSparepartsAlarmDAO;
import com.changan.app.report.model.TSparepartsAlarm;
import com.changan.app.report.model.TSparepartsAlarmExample;
import com.changan.plcinterface.model.Transferdevice101Example;
import com.commondb.db.service.EntityService;

public class AlarmServiceImpl implements AlarmService {
	private EntityService entityService;  
	private TSparepartsAlarmDAO sparepartsAlarmDAO;

	public EntityService getEntityService() {
		return entityService;
	}

	public void setEntityService(EntityService entityService) {
		this.entityService = entityService;
	}

	/**
	 * @return the sparepartsAlarmDAO
	 */
	public TSparepartsAlarmDAO getSparepartsAlarmDAO() {
		return sparepartsAlarmDAO;
	}

	/**
	 * @param sparepartsAlarmDAO the sparepartsAlarmDAO to set
	 */
	public void setSparepartsAlarmDAO(TSparepartsAlarmDAO sparepartsAlarmDAO) {
		this.sparepartsAlarmDAO = sparepartsAlarmDAO;
	}

	@Override
	public void getSparePartsAlarm() {
		// TODO Auto-generated method stub
		getDeviceSparePartsAlarm(EntityDefine.RobotDeviceMetaId, EntityDefine.RobotDevice_ID_CN, EntityDefine.RobotDevice_Name_CN);
		//getDeviceSparePartsAlarm(EntityDefine.CgcDeviceMetaId, EntityDefine.CgcDevice_ID_CN, EntityDefine.CgcDevice_Name_CN);
		
	}
	
	private void getDeviceSparePartsAlarm(Integer deviceMetaId, String device_ID_CN, String device_Name_CN) {
		
		//先清除以前记录
		TSparepartsAlarmExample sparepartsAlarmExample = new TSparepartsAlarmExample();
		TSparepartsAlarmExample.Criteria sparepartsAlarmCriteria = sparepartsAlarmExample.createCriteria();
		sparepartsAlarmCriteria.andDevicemetaidEqualTo(deviceMetaId);
		getSparepartsAlarmDAO().deleteByExample(sparepartsAlarmExample);
		
		//查询现有报警记录
		String columnString = " c.id as deviceEntityId, c."+ device_ID_CN + " as deviceCode, c." + device_Name_CN + " as deviceName "
				+ " e.id as sparePartsId, e." + EntityDefine.SpareParts_Name_CN + " as sparePartsName, e." + EntityDefine.SpareParts_Style_CN + " as  sparePartsStyle, " 
				+ " e." + EntityDefine.SpareParts_AlarmPerent_CN + " as alarmPercent, e." + EntityDefine.SpareParts_LifeTime_CN + " as designLife, "
				+ " (case when e." + EntityDefine.SpareParts_RecentTime_CN + " is null or e." + EntityDefine.SpareParts_RecentTime_CN + "='' then TIMESTAMPDIFF(second,e." + EntityDefine.SpareParts_BeginTime_CN +",now()) "
				+ " else TIMESTAMPDIFF(second,e." + EntityDefine.SpareParts_RecentTime_CN + ", now()) end)  as useSecond, "
				+ " sum( " 
				+ " case when " + EntityDefine.Run_EndTime_CN + " is null or " + EntityDefine.Run_EndTime_CN +" =''  then TIMESTAMPDIFF(second,b." + EntityDefine.Run_StartTime_CN + ",now()) "
				+ " else TIMESTAMPDIFF(second,b." + EntityDefine.Run_StartTime_CN + ",b." + EntityDefine.Run_EndTime_CN + ") end ) as runSecond, "
				+ " (select count(*)  from r_entity h  " 
				+ " 		where h.entity2_id=c.id and h.meta2_id=" + deviceMetaId 
				+ " 		and h.meta1_id=" + EntityDefine.WorkpieceCleanMetaId +") as processCount,  "
				+ " 				 (select data_id from r_entity_chara_data j  "
				+ " where j.entity_id=e.id and j.meta_id=" + EntityDefine.SparePartsMetaId
 				+ " and j.chara_id=" + EntityDefine.Chara_LifeType + ") as lifeType, "
 				+ " (select data_id from r_entity_chara_data k  "
 				+ " where k.entity_id=e.id and k.meta_id=" + EntityDefine.SparePartsMetaId
 				+ " and k.chara_id=" + EntityDefine.Chara_LifeUnit + ") as lifeUnit " ; 
		
				  
		String fromStr = " t_entity_" + EntityDefine.RunMetaId +" b, t_entity_" + deviceMetaId + " c, r_entity d, t_entity_" + EntityDefine.SparePartsMetaId +" e, r_entity f ";
		String whereInitStr = " (( e." + EntityDefine.SpareParts_RecentTime_CN + " is null and UNIX_TIMESTAMP(e." + EntityDefine.SpareParts_BeginTime_CN +")<= UNIX_TIMESTAMP(b." + EntityDefine.Run_StartTime_CN +")) "
				+ " or (e." + EntityDefine.SpareParts_RecentTime_CN + "  is not null and UNIX_TIMESTAMP(e." + EntityDefine.SpareParts_RecentTime_CN + ")<= UNIX_TIMESTAMP(b." + EntityDefine.Run_StartTime_CN +"))) "
				+ " and d.entity2_id=c.id and d.meta2_id=" + deviceMetaId 
				+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.RunMetaId 
				+ " and f.entity2_id=c.id and f.meta2_id=" + deviceMetaId 
			 	+ " and f.entity1_id=e.id and f.meta1_id=" + EntityDefine.SparePartsMetaId;
		String groupStr =  " group by c.id, e.id, e." + EntityDefine.SpareParts_Name_CN + ", e." + EntityDefine.SpareParts_Style_CN;
		
		List results = Collections.emptyList();  
		String whereStr = whereInitStr + groupStr ;
		results = getEntityService().dynSelect(columnString, fromStr, whereStr);
		
	    for(int i = 0 ; i < results.size() ; i ++) { 
	    	Map resultItemMap = (Map) results.get(i);
	    	
    		DecimalFormat dfTime=new DecimalFormat("#0.00"); 
    		DecimalFormat dfCount=new DecimalFormat("#"); 
	    	//判断是否要预警
	    	double designLife = 0;
	    	try {
	    		designLife = Double.parseDouble(resultItemMap.get("designLife").toString());
	    	} catch (Exception e) {}
	    	double alarmPercent = 0;
	    	try {
	    		alarmPercent = Double.parseDouble(resultItemMap.get("alarmPercent").toString());
	    	} catch (Exception e) {
	    		alarmPercent = 1;
	    	}
	    	String lifeType = "";
	    	try {
	    		lifeType = resultItemMap.get("lifeType").toString();
	    	} catch (Exception e) {
	    		lifeType = EntityDefine.Chara_LifeType_ProcessCount;
	    	}
	    	String lifeUnit = "";
	    	try {
	    		lifeUnit = resultItemMap.get("lifeUnit").toString();
	    	} catch (Exception e) {
	    		lifeUnit = EntityDefine.Chara_LifeUnit_Item;
	    	}
	    	if (lifeType.length()>0) {
	    		double workLife = 0;
	    		//计算工作量或时间
	    		if (lifeType.equals(EntityDefine.Chara_LifeType_ProcessCount)) {
	    			workLife =  Double.parseDouble(resultItemMap.get("processCount").toString());
	    		} else if (lifeType.equals(EntityDefine.Chara_LifeType_RunTime)) {
	    			workLife =  Double.parseDouble(resultItemMap.get("runSecond").toString());
	    		} else {
	    			workLife =  Double.parseDouble(resultItemMap.get("useSecond").toString());
	    		}
	    		//归并计量单位
	    		String workLifeStr = "";
	    		String designLifeStr = "";
	    		if (lifeUnit.length()>0) {
	    			if (lifeUnit.equals(EntityDefine.Chara_LifeUnit_Day)) {
	    				workLife = workLife/(24*3600);
	    				workLifeStr = dfTime.format(workLife)+"天";
	    				designLifeStr = resultItemMap.get("designLife").toString()+"天";
	    			} else if (lifeUnit.equals(EntityDefine.Chara_LifeUnit_Hour)) {
	    				workLife = workLife/(3600);
	    				workLifeStr = dfTime.format(workLife)+"小时";
	    				designLifeStr = resultItemMap.get("designLife").toString()+"小时";
	    			} else {
	    				workLifeStr = dfCount.format(workLife)+"件";
	    				designLifeStr = resultItemMap.get("designLife").toString()+"件";
	    			}
	    		}
	    		
	    		if ((designLife>0) && (workLife>=designLife*alarmPercent)) {
	    			//报警
	    			resultItemMap.put("alarmFlag","1");
	    			TSparepartsAlarm sparePartsAlarmRecord = new TSparepartsAlarm();
	    			sparePartsAlarmRecord.setId(UUID.randomUUID().toString());
	    			sparePartsAlarmRecord.setDevicemetaid(deviceMetaId);
	    			sparePartsAlarmRecord.setDeviceentityid(resultItemMap.get("deviceEntityId").toString());
	    			sparePartsAlarmRecord.setDeviceentitycode(resultItemMap.get("deviceCode").toString());
	    			sparePartsAlarmRecord.setDeviceentityname(resultItemMap.get("deviceName").toString());
	    			sparePartsAlarmRecord.setSparepartsid(resultItemMap.get("sparePartsId").toString());
	    			sparePartsAlarmRecord.setSparepartscode(resultItemMap.get("sparePartsStyle").toString());
	    			sparePartsAlarmRecord.setSparepartsname(resultItemMap.get("sparePartsName").toString());
	    			sparePartsAlarmRecord.setDesignlife(designLifeStr);
	    			sparePartsAlarmRecord.setWorklife(workLifeStr);
	    			sparePartsAlarmRecord.setWorkpercent(dfCount.format(workLife*100/designLife)+"%");
	    			getSparepartsAlarmDAO().insert(sparePartsAlarmRecord);
	    		}
	    	}
	    }
		
		
	}

}
