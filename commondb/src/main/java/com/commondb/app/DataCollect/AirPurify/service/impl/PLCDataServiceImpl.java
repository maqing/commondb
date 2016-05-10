package com.commondb.app.DataCollect.AirPurify.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.commondb.app.DataCollect.AirPurify.bo.APPLCData;
import com.commondb.app.DataCollect.AirPurify.service.PLCDataService;
import com.commondb.app.DataCollect.ICCard.service.ICCardService;
import com.commondb.app.DataCollect.tools.ReadCardCache;
import com.commondb.db.service.EntityService;

public class PLCDataServiceImpl implements PLCDataService {
	
	private EntityService entityService;
	private ICCardService iCCardService;
	
	private final int PLCDataMetaId =1;
	private final String PLC_TimeCN = "d_1";
	private final String PLC_DeviceIDCN = "d_2";
	private final String PLC_DeviceStartFlagCN = "d_3";
	private final String PLC_APStartFlagCN = "d_4";
	private final String PLC_EngineSpeedCN = "d_5"; 
	private final String PLC_AlTemperatureCN = "d_6";
	private final String PLC_AlarmFlagCN = "d_7";
	
	private final int APRecMetaId =2;
	private final String AP_StartTimeCN = "d_8";
	private final String AP_EndTimeCN = "d_9";
	private final String AP_DeviceIDCN = "d_10";
	private final String AP_AlarmFlagCN = "d_11";
	private final String AP_AlarmStartTimeCN = "d_12";
	private final String AP_AlarmEndTimeCN = "d_13";
	private final String AP_RFIDCodeCN = "d_14";
	
	private final int ICCardReadMetaId =3;

	/**
	 * @return the entityService
	 */
	public EntityService getEntityService() {
		return entityService;
	}

	/**
	 * @param entityService the entityService to set
	 */
	public void setEntityService(EntityService entityService) {
		this.entityService = entityService;
	}
	

	public ICCardService getiCCardService() {
		return iCCardService;
	}

	public void setiCCardService(ICCardService iCCardService) {
		this.iCCardService = iCCardService;
	}

	@Override
	public Map<String, Object> saveAPPLCData(APPLCData item,  Map<String, Object> apRecValueMap) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> valuesMap = new HashMap<String, Object>();
		if (item.getPlcTime()!=null) {
			valuesMap.put(PLC_TimeCN, (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(item.getPlcTime()));
		}
		if (item.getDeviceStartFlag()!=null) {
			valuesMap.put(PLC_DeviceStartFlagCN, item.getDeviceStartFlag());
		}
		if (item.getApStartFlag()!=null) {
			valuesMap.put(PLC_APStartFlagCN, item.getApStartFlag());
			if (item.getApStartFlag().equals("1")) {
				//开始除气，创建新加工记录，同时静态变量也被更新
				apRecValueMap = createAPRecord(item);
			}
			if (item.getApStartFlag().equals("0")) {
				//结束除气，更新加工记录
				if (apRecValueMap.containsKey("id")) {
					apRecValueMap.put(AP_EndTimeCN, (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(item.getPlcTime()));
					//id从map里移走，否则更新slor报错。
					String tempId = (String) apRecValueMap.get("id");
					apRecValueMap.remove("id");
					entityService.updateEntity(APRecMetaId, tempId, apRecValueMap);
					//放回id
					apRecValueMap.put("id", tempId);
				}
			}
		}
		if (item.getEngineSpeed()!=null) {
			valuesMap.put(PLC_EngineSpeedCN, item.getEngineSpeed().toString());
		}
		if (item.getAlTemperature()!=null) {
			valuesMap.put(PLC_AlTemperatureCN, item.getAlTemperature().toString());
		}
		if (item.getAlarmFlag()!=null) {
			valuesMap.put(PLC_AlarmFlagCN, item.getAlarmFlag());
			if (apRecValueMap.containsKey("id")) {
				if (item.getAlarmFlag().equals("1")) {
					//报警，更新加工记录
					apRecValueMap.put(AP_AlarmFlagCN, "1");
					apRecValueMap.put(AP_AlarmStartTimeCN, (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(item.getPlcTime()));
					String tempId = (String) apRecValueMap.get("id");
					apRecValueMap.remove("id");
					entityService.updateEntity(APRecMetaId, tempId, apRecValueMap);
					//放回id
					apRecValueMap.put("id", tempId);
				}
				if (item.getAlarmFlag().equals("0")) {
					//结束报警，更新加工记录
					apRecValueMap.put(AP_AlarmEndTimeCN, (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(item.getPlcTime()));
					String tempId = (String) apRecValueMap.get("id");
					apRecValueMap.remove("id");
					entityService.updateEntity(APRecMetaId, tempId, apRecValueMap);
					//放回id
					apRecValueMap.put("id", tempId);
				}
			}
		}
		valuesMap.put("update_user", "admin");
		valuesMap.put("create_user", "admin");
		
		if (apRecValueMap.containsKey("id")) {
			//构造关联属性，明细数据关联到加工记录上
			String[][] entityArr = {{String.valueOf(APRecMetaId),(String) apRecValueMap.get("id")}};
			valuesMap.put("entityArr",entityArr);
		}
		entityService.createEntity(PLCDataMetaId, valuesMap);
		return apRecValueMap;
	}

	private Map<String, Object> createAPRecord(APPLCData item) throws Exception{
		// TODO Auto-generated method stub
		Map<String, Object> valuesMap = new HashMap<String, Object>();
		valuesMap.put(AP_StartTimeCN, (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(item.getPlcTime()));
		
		valuesMap.put("update_user", "admin");
		valuesMap.put("create_user", "admin");
		
		//构造关联属性，加工记录关联到RFID读卡记录上
		/*
		String cardReadRecID = iCCardService.getCurrentCardRecID();
		if (cardReadRecID.length()>0) {
			String[][] entityArr = {{String.valueOf(ICCardReadMetaId),cardReadRecID}};
			valuesMap.put("entityArr",entityArr);
		}*/
		String cardReadRecID = ReadCardCache.getCurReadRecID();
		if (cardReadRecID!=null) {
			String[][] entityArr = {{String.valueOf(ICCardReadMetaId),cardReadRecID}};
			valuesMap.put("entityArr",entityArr);
			valuesMap.put(AP_RFIDCodeCN, ReadCardCache.getCurReadRecNo());
		}
		
		valuesMap.put("id", entityService.createEntity(APRecMetaId, valuesMap));
		return valuesMap;
	}

	@Override
	public Map<String, Object> getLastNotFinishAPRec() {
		// TODO Auto-generated method stub
		Map<String, Object> valuesMap = new HashMap<String, Object>();
		//判断结束时间是否有值作为是否完成标志
		List idList =  entityService.dynSelect("id", 
				"t_entity_"+String.valueOf(APRecMetaId), 
				AP_EndTimeCN + " is null or " + AP_EndTimeCN + "='' order by create_time desc ");
		if ((idList!=null)&& (idList.size()>0)) {
			valuesMap = (Map<String, Object>) idList.get(0);
			valuesMap.put("update_user", "admin");
			//查询关联属性
		    List rEntityList = entityService.listREntity(APRecMetaId, (String)valuesMap.get("id"));
			if ((rEntityList!=null)&& (rEntityList.size()>0)) {
				String[][] entityArr = new String[rEntityList.size()][2];
				for (int i=0; i<rEntityList.size(); i++) {
					entityArr[i][0] = (String) ((HashMap<String, Object>)rEntityList.get(i)).get("meta2Id");
					entityArr[i][1] = (String) ((HashMap<String, Object>)rEntityList.get(i)).get("entity2Id");
				}
				valuesMap.put("entityArr",entityArr);
			}
		}
		return valuesMap;
	}

	
	@Override
	public List<APPLCData> queryAPPLCData() {
		// TODO Auto-generated method stub
		ArrayList<APPLCData> apPLCDataList = new ArrayList<APPLCData>();
		//判断结束时间是否有值作为是否完成标志
		List plcDataList =  entityService.dynSelect(PLC_TimeCN + ", " +PLC_EngineSpeedCN + ", " + PLC_AlTemperatureCN , 
				"t_entity_"+String.valueOf(PLCDataMetaId), 
				PLC_EngineSpeedCN + " is not null and " + PLC_AlTemperatureCN  + " is not null order by " + PLC_TimeCN );
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		//System.out.println("----"+ data[0]+ "----"+data[0].equals("2015/8/28 14:49:04"));
		if ((plcDataList!=null)&& (plcDataList.size()>0)) {
			for (int i=0; i<plcDataList.size(); i++) {
				Map<String, Object> plcDataMap = (Map<String, Object>)plcDataList.get(i);
				APPLCData item= new APPLCData();
				try {
					item.setPlcTime(sdf.parse(plcDataMap.get(PLC_TimeCN).toString()));
				} catch(Exception e) 
				{}
				item.setEngineSpeed(Double.parseDouble(plcDataMap.get(PLC_EngineSpeedCN).toString()));
				item.setAlTemperature(Double.parseDouble(plcDataMap.get(PLC_AlTemperatureCN).toString()));
				apPLCDataList.add(item);
			}
		}
		return apPLCDataList;
	}
	
}
