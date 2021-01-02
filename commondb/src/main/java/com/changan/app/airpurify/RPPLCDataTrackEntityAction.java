package com.changan.app.airpurify;

import java.util.ArrayList;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.web.TrackEntityAction;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;

public class RPPLCDataTrackEntityAction extends TrackEntityAction 
implements Preparable, ServletRequestAware{
	private String rpDeviceID;
	private String rpBeginTime;
	private String rpEndTime;
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
		if ((rpDeviceID!=null)&&(rpDeviceID.length()>0)) {
			appendQueryStr = appendQueryStr + " and t_entity_" + String.valueOf(EntityDefine.RPPLCDataMetaId) 
					+ "." + EntityDefine.RPPLCData_DeviceID_CN + " = '" + rpDeviceID + "' ";
		}
		if ((rpBeginTime!=null)&&(rpBeginTime.length()>0)) {
			appendQueryStr = appendQueryStr + " and t_entity_" + String.valueOf(EntityDefine.RPPLCDataMetaId) 
					+ "." + EntityDefine.RPPLCData_PLCTime_CN + " >= '" + rpBeginTime + "' ";
		}
		if ((rpEndTime!=null)&&(rpEndTime.length()>0)) {
			appendQueryStr = appendQueryStr  + " and t_entity_" + String.valueOf(EntityDefine.RPPLCDataMetaId) 
					+ "." + EntityDefine.RPPLCData_PLCTime_CN + " <= '" + rpEndTime + "' ";
		}
		
		super.setAppendQuery(appendQueryStr);
	}

}
