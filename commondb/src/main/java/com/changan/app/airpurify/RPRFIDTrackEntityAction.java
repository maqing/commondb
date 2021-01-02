package com.changan.app.airpurify;

import java.util.ArrayList;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.web.TrackEntityAction;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;

public class RPRFIDTrackEntityAction extends TrackEntityAction 
implements Preparable, ServletRequestAware{
	private String rpDeviceID;
	private String rpBeginTime;
	private String rpEndTime;
	private String rpRFIDNO;
	private MetaService metaService;
	private EntityService entityService;
	

	/**
	 * @return the rpDeviceID
	 */
	public String getRpDeviceID() {
		return rpDeviceID;
	}

	/**
	 * @param rpDeviceID the rpDeviceID to set
	 */
	public void setRpDeviceID(String rpDeviceID) {
		this.rpDeviceID = rpDeviceID;
	}

	public String getRpBeginTime() {
		return rpBeginTime;
	}

	public void setRpBeginTime(String rpBeginTime) {
		this.rpBeginTime = rpBeginTime;
	}

	public String getRpEndTime() {
		return rpEndTime;
	}

	public void setRpEndTime(String rpEndTime) {
		this.rpEndTime = rpEndTime;
	}

	/**
	 * @return the rpRFIDNO
	 */
	public String getRpRFIDNO() {
		return rpRFIDNO;
	}

	/**
	 * @param rpRFIDNO the rpRFIDNO to set
	 */
	public void setRpRFIDNO(String rpRFIDNO) {
		this.rpRFIDNO = rpRFIDNO;
	}

	public EntityService getEntityService()
	  {
	    return this.entityService;
	  }
	
	  public void setEntityService(EntityService entityService)
	  {
	    this.entityService = entityService;
	    super.setEntityService(entityService);
	  }
	
	  public MetaService getMetaService()
	  {
	    return this.metaService;
	  }
	
	  public void setMetaService(MetaService metaService)
	  {
	    this.metaService = metaService;
	    super.setMetaService(metaService);
	  }

	public String trackEntity(){
		super.setFavorMetaList(new ArrayList());

	    return super.trackEntity();
	}
	
	@Override
	public void customizeAppendQuery() {
		String appendQueryStr="";
		if ((rpRFIDNO!=null)&&(rpRFIDNO.length()>0)) {
			//RFID扫描时间早于除气开始时间，同时扫描时间在记录里最大
			/*
			appendQueryStr = appendQueryStr + " and t_entity_" + String.valueOf(EntityDefine.RPRFIDMetaId) 
					+ "." + EntityDefine.RPRFID_RFIDNO_CN + " = '" + rpRFIDNO 
					+ "' and t_entity_" + String.valueOf(EntityDefine.RPRFIDMetaId) 
					+ "." + EntityDefine.RPRFID_EnterTime_CN + " <= '" + rpBeginTime +"' "
					+ " and not exists (select * from  t_entity_" + String.valueOf(EntityDefine.RPRFIDMetaId) + " tb5 "
					+ " where tb5." + EntityDefine.RPRFID_RFIDNO_CN +  " = '" + rpRFIDNO + "' "
					+ " and tb5." + EntityDefine.RPRFID_EnterTime_CN + ">t_entity_" + String.valueOf(EntityDefine.RPRFIDMetaId) 
					+ "." + EntityDefine.RPRFID_EnterTime_CN 
					+ " and tb5." + EntityDefine.RPRFID_EnterTime_CN + " <= '" + rpBeginTime +"' "
					+ ")";
			*/
			
			//修改成 RFID扫描时间晚于除气开始时间，同时扫描时间在记录里最小
			appendQueryStr = appendQueryStr + " and t_entity_" + String.valueOf(EntityDefine.RPRFIDMetaId) 
					+ "." + EntityDefine.RPRFID_RFIDNO_CN + " = '" + rpRFIDNO 
					+ "' and t_entity_" + String.valueOf(EntityDefine.RPRFIDMetaId) 
					+ "." + EntityDefine.RPRFID_EnterTime_CN + " >= '" + rpBeginTime +"' "
					+ " and not exists (select * from  t_entity_" + String.valueOf(EntityDefine.RPRFIDMetaId) + " tb5 "
					+ " where tb5." + EntityDefine.RPRFID_RFIDNO_CN +  " = '" + rpRFIDNO + "' "
					+ " and tb5." + EntityDefine.RPRFID_EnterTime_CN + "<t_entity_" + String.valueOf(EntityDefine.RPRFIDMetaId) 
					+ "." + EntityDefine.RPRFID_EnterTime_CN 
					+ " and tb5." + EntityDefine.RPRFID_EnterTime_CN + " >= '" + rpBeginTime +"' "
					+ ")";
		}
		
		super.setAppendQuery(appendQueryStr);
	}

}
