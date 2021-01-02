package com.changan.app.report.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.changan.app.datamodel.EntityDefine;
import com.changan.app.report.model.Cutter;
import com.changan.app.report.model.CutterStat;
import com.commondb.db.service.EntityService;

public class CutterStatServiceImpl implements CutterStatService {
	private EntityService entityService;  
	
	public EntityService getEntityService() {
		return entityService;
	}

	public void setEntityService(EntityService entityService) {
		this.entityService = entityService;
	}
	

	@Override
	public CutterStat getSingleCutterStat(String lineId, String deviceId,
			String cutterId, String timeBegin, String timeEnd) {
		
	/*select sum(d_38) as nowProcessNum,  
		count(b.d_43) as changeCount, ifnull(sum(b.d_43),0) as beforeProcessNum,
		round((ifnull(sum(b.d_43),0)+sum(d_38))/(1+count(b.d_43))) as averageProcessNum
		from t_entity_12  a  
		left join r_entity c  on a.id=c.entity2_id and c.meta2_id=12
		left join t_entity_13 b on c.entity1_id and c.meta1_id=13
		where a.d_34=0 ; */
		
		String columnString = "ifnull(sum(" + EntityDefine.Cutter_ProcessCount_CN + "),0) as nowProcessNum, "  
				+ " count(b." + EntityDefine.ChangeCutter_ProcessCount_CN +") as changeCount, ifnull(sum(b." 
				+ EntityDefine.ChangeCutter_ProcessCount_CN + "),0) as beforeProcessNum, "
				+ " round((ifnull(sum(b." + EntityDefine.ChangeCutter_ProcessCount_CN + "),0)+ifnull(sum(" 
				+ EntityDefine.Cutter_ProcessCount_CN + "),0))/(1+count(b." 
				+ EntityDefine.ChangeCutter_ProcessCount_CN + "))) as averageProcessNum ";
		
		String fromStr = "t_entity_" + EntityDefine.CutterMetaId + " a "
				+ " left join r_entity c  on a.id=c.entity2_id and c.meta2_id=" + EntityDefine.CutterMetaId 
				+ " left join t_entity_" + EntityDefine.ChangeCutterMetaId + " b on c.entity1_id and c.meta1_id=" + EntityDefine.ChangeCutterMetaId ;
		String whereInitStr = " 1=1 ";
		if (cutterId!=null) {
			whereInitStr = whereInitStr + " and a." + EntityDefine.Cutter_Code_CN + "='" + cutterId + "' ";
		}
		List cutterStatDataList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		CutterStat cutterStatItem = new CutterStat();
		if (cutterStatDataList.size()>0) { 
			Map cutterStatItemMap = (Map)cutterStatDataList.get(0);
			cutterStatItem.setCutterId(cutterId);
			cutterStatItem.setCutterCode(cutterId);
			cutterStatItem.setChangeCounter(Integer.parseInt(cutterStatItemMap.get("changeCount").toString()));
			cutterStatItem.setAverageProcessNum(((Double)(cutterStatItemMap.get("averageProcessNum"))).intValue());
			cutterStatItem.setOwnProcessNum(((Double)(cutterStatItemMap.get("nowProcessNum"))).intValue());
		}
		return cutterStatItem;
	}

	@Override
	public List<CutterStat> getCutterCompareStat(String lineId,
			String deviceType, String cutterType, String timeBegin,
			String timeEnd) {
		String columnString = "a." + EntityDefine.Cutter_Code_CN + " as cutterCode, sum(" 
				+ EntityDefine.Cutter_ProcessCount_CN + ") as nowProcessNum, "  
				+ " count(b." + EntityDefine.ChangeCutter_ProcessCount_CN +") as changeCount,ifnull(sum(b." 
				+ EntityDefine.ChangeCutter_ProcessCount_CN + "),0) as beforeProcessNum, "
				+ " round((ifnull(sum(b." + EntityDefine.ChangeCutter_ProcessCount_CN + "),0)+sum(" 
				+ EntityDefine.Cutter_ProcessCount_CN + "))/(1+count(b." 
				+ EntityDefine.ChangeCutter_ProcessCount_CN + "))) as averageProcessNum ";
		
		String fromStr = "t_entity_" + EntityDefine.CutterMetaId + " a "
				+ " left join r_entity c  on a.id=c.entity2_id and c.meta2_id=" + EntityDefine.CutterMetaId 
				+ " left join t_entity_" + EntityDefine.ChangeCutterMetaId + " b on c.entity1_id and c.meta1_id=" + EntityDefine.ChangeCutterMetaId ;
		String whereInitStr = " 1=1 ";
		whereInitStr = whereInitStr + " group by a." + EntityDefine.Cutter_Code_CN ;
		List cutterStatDataList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		ArrayList<CutterStat> cutterStatList = new ArrayList<CutterStat>();
		for (int i=0; i<cutterStatDataList.size(); i++) { 
			CutterStat cutterStatItem = new CutterStat();
			Map cutterStatItemMap = (Map)cutterStatDataList.get(i);
			cutterStatItem.setCutterId(cutterStatItemMap.get("cutterCode").toString());
			cutterStatItem.setCutterCode(cutterStatItemMap.get("cutterCode").toString());
			cutterStatItem.setChangeCounter(Integer.parseInt(cutterStatItemMap.get("changeCount").toString()));
			cutterStatItem.setAverageProcessNum(((Double)(cutterStatItemMap.get("averageProcessNum"))).intValue());
			cutterStatItem.setOwnProcessNum(((Double)(cutterStatItemMap.get("nowProcessNum"))).intValue());
			cutterStatList.add(cutterStatItem);
		}
		return cutterStatList;
	}

	@Override
	public List<Cutter> getCutterList(String lineId, String deviceType, String deviceId) {
		// TODO Auto-generated method stub
		String columnString = "";
		String fromStr ="";
		String whereStr = "";
		ArrayList<Cutter> cutterList = new ArrayList<Cutter>();

		
		columnString = " distinct a.toolCode , c." + EntityDefine.CgcDevice_ID_CN 
				+ " as deviceCode, c.id as deviceId " + 
				" from cgcDevice a, t_entity_" + EntityDefine.CgcDeviceMetaId + " c "
				+ " where a.deviceId = c." + EntityDefine.CgcDevice_ID_CN ;
		if (deviceId!=null) 
			columnString = columnString + " and c.id = '" + deviceId + "' ";
		columnString = columnString + " union "
				+ " select distinct a.toolCode , c." + EntityDefine.MachineDevice_ID_CN 
				+ " as deviceCode, c.id as deviceId ";
		fromStr = " machineDevice a, t_entity_" + EntityDefine.MachineDeviceMetaId + " c ";
		whereStr = " a.deviceId = c." + EntityDefine.MachineDevice_ID_CN ;
		if (deviceId!=null) 
			whereStr = whereStr	+ " and c.id = '" + deviceId + "' ";
		
		/*
		if ((deviceType!=null) && (deviceType.equals(String.valueOf(EntityDefine.CgcDeviceMetaId)))) {
			columnString = " distinct a.toolCode , c." + EntityDefine.CgcDevice_ID_CN 
					+ " as deviceCode, c.id as deviceId ";
			fromStr = " cgcDevice a, t_entity_" + deviceType + " c ";
			whereStr = " a.deviceId = c." + EntityDefine.CgcDevice_ID_CN ;
		}
		
		if ((deviceType!=null) && (deviceType.equals(String.valueOf(EntityDefine.MachineDeviceMetaId)))) {
			columnString = " distinct a.toolCode , c." + EntityDefine.MachineDevice_ID_CN 
					+ " as deviceCode, c.id as deviceId ";
			fromStr = " machineDevice a, t_entity_" + deviceType + " c ";
			whereStr = " a.deviceId = c." + EntityDefine.MachineDevice_ID_CN ;
		} */
		
		if (columnString.length()>0) {
			List cutterMapList = this.entityService.dynSelect(columnString, fromStr, whereStr);
			for (int i=0; i<cutterMapList.size(); i++) {
				Map cutterMapItem = (Map) cutterMapList.get(i);
				Cutter cutterItem =  new Cutter();
				cutterItem.setCutterId(cutterMapItem.get("toolCode").toString());
				cutterItem.setCutterCode(cutterMapItem.get("toolCode").toString());
				cutterItem.setDeviceCode(cutterMapItem.get("deviceCode").toString());
				cutterItem.setDeviceId(cutterMapItem.get("deviceId").toString());
				cutterList.add(cutterItem);
			}
		}
		return cutterList;
	}

}
