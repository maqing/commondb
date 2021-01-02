package com.commondb.app.DataCollect.ICCard.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.DataCollect.ICCard.service.ICCardService;
import com.commondb.app.DataCollect.tools.ReadCardCache;
import com.commondb.db.service.EntityService;

public class ICCardServiceImpl implements ICCardService {

	private EntityService entityService;
//	private final int ICCardReadMetaId =3;
//	private final String EnterTimeColumnName = "d_15" ;
//	private final String LeaveTimeColumnName = "d_16" ;
//	private final String CardNoColumnName = "d_17" ;
	
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
	
	@Override
	public String readCard(String cardNo) {
		// TODO Auto-generated method stub
		try {
			if ((cardNo == null) || (cardNo.length() == 0)) return "empty";
			
			/*
			//找到最后一条记录，判断是否重复卡号
			List icCardList = entityService.dynSelect("id," + CardNoColumnName, 
					"t_entity_"+String.valueOf(ICCardReadMetaId), " 1=1 order by create_time desc ");
			if ((icCardList!=null)&& (icCardList.size()>0)) {
				Map<String, String> tempMap = (Map<String, String>) icCardList.get(0);
				if ( tempMap.get(CardNoColumnName).equals(cardID)) {
					return tempMap.get("id");
				}
			}
			*/
			//从内存读取上次读卡记录，不从数据库里查询
			String oldCardNo = ReadCardCache.getCurReadRecNo();
			if (oldCardNo!=null) {
				if (oldCardNo.equals(cardNo)) {
					return ReadCardCache.getCurReadRecID();
				} else {
					//更新上次读卡记录的离开时间
					//找到上次读卡记录
					List icCardList = entityService.dynSelect("* ", "t_entity_"+String.valueOf(EntityDefine.RPRFIDMetaId), 
							" 1=1 and " + EntityDefine.RPRFID_RFIDNO_CN + "='" + oldCardNo + "' order by create_time desc ");
					if ((icCardList!=null) && (icCardList.size()>0)) {
						Map<String, String> oldRecMap = (Map<String, String>) icCardList.get(0);
						Object leaveTimeObj = oldRecMap.get(EntityDefine.RPRFID_LeaveTime_CN);
						if ((leaveTimeObj!=null) && (leaveTimeObj.toString().trim()!=null))
						{
							//离开时间有值，不更新	
						} else {
							//更新离开时间
							String oldEntityId = oldRecMap.get("id");
							oldRecMap.remove("id");
							oldRecMap.put(EntityDefine.RPRFID_LeaveTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
							entityService.updateEntity(EntityDefine.RPRFIDMetaId, oldEntityId, oldRecMap );
						}
					}
				}
			}
			
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(EntityDefine.RPRFID_EnterTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
			valuesMap.put(EntityDefine.RPRFID_RFIDNO_CN, cardNo);
			
			valuesMap.put("update_user", "admin");
			valuesMap.put("create_user", "admin");
			String newCardRecID = entityService.createEntity(EntityDefine.RPRFIDMetaId, valuesMap);
			ReadCardCache.curRFIDRecMap.put("id", newCardRecID);
			ReadCardCache.curRFIDRecMap.put(EntityDefine.RPRFID_RFIDNO_CN, cardNo);
			return newCardRecID;
		} catch (Exception e) {
			return "error";
		}
	}

	@Override
	public String getCurrentCardRecID() {
		try {
			if (!ReadCardCache.curRFIDRecMap.containsKey("id")) {
				//找到最后一条记录
				List icCardList = entityService.dynSelect("id," + EntityDefine.RPRFID_RFIDNO_CN, 
						"t_entity_"+String.valueOf(EntityDefine.RPRFIDMetaId), "1=1 order by create_time desc ");
				if ((icCardList!=null)&& (icCardList.size()>0)) {
					Map<String, String> tempMap = (Map<String, String>) icCardList.get(0);
					ReadCardCache.curRFIDRecMap.put("id", tempMap.get("id"));
					ReadCardCache.curRFIDRecMap.put(EntityDefine.RPRFID_RFIDNO_CN, tempMap.get(EntityDefine.RPRFID_RFIDNO_CN));
					return tempMap.get("id");
				} else {
					return "";
				}
			} else {
				return ReadCardCache.curRFIDRecMap.get("id");
			}
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	public String finishReadCard(String cardID) {
		// TODO Auto-generated method stub
		try {
			
			List icCardList = entityService.dynSelect("* ", "t_entity_"+String.valueOf(EntityDefine.RPRFIDMetaId), 
					" 1=1 and " + EntityDefine.RPRFID_RFIDNO_CN + "='" + cardID + "' order by create_time desc ");
			if ((icCardList!=null)&& (icCardList.size()>0)) {
				Map<String, String> oldRecMap = (Map<String, String>) icCardList.get(0);
				Object leaveTimeObj = oldRecMap.get(EntityDefine.RPRFID_LeaveTime_CN);
				if ((leaveTimeObj==null) || (leaveTimeObj.toString().trim().length()==0))
				{
					//离开时间为空，更新	
					String oldEntityId = oldRecMap.get("id");
					oldRecMap.remove("id");
					oldRecMap.put(EntityDefine.RPRFID_LeaveTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
					entityService.updateEntity(EntityDefine.RPRFIDMetaId, oldEntityId, oldRecMap );
				}
			}
			
			return "success";
		} catch (Exception e) {
			return "error";
		}
	}

}
