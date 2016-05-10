package com.changan.app.deviceworkdata;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.web.TrackEntityAction;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;

public class WPCleanTrackEntityAction extends TrackEntityAction 
implements Preparable, ServletRequestAware{
	private String timeWPCleanBegin;
	private String timeWPCleanEnd;
	private MetaService metaService;
	private EntityService entityService;
	

	public String getTimeWPCleanBegin() {
		return timeWPCleanBegin;
	}

	public void setTimeWPCleanBegin(String timeWPCleanBegin) {
		this.timeWPCleanBegin = timeWPCleanBegin;
	}

	public String getTimeWPCleanEnd() {
		return timeWPCleanEnd;
	}

	public void setTimeWPCleanEnd(String timeWPCleanEnd) {
		this.timeWPCleanEnd = timeWPCleanEnd;
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
		if ((timeWPCleanBegin!=null)&&(timeWPCleanBegin.length()>0)) {
			appendQueryStr = appendQueryStr + " and t_entity_" + String.valueOf(EntityDefine.WorkpieceCleanMetaId) 
					+ "." + EntityDefine.WorkpieceClean_Date_CN + " >= '" + timeWPCleanBegin + "' ";
		}
		if ((timeWPCleanEnd!=null)&&(timeWPCleanEnd.length()>0)) {
			appendQueryStr = appendQueryStr  + " and t_entity_" + String.valueOf(EntityDefine.WorkpieceCleanMetaId) 
					+ "." + EntityDefine.WorkpieceClean_Date_CN + " <= '" + timeWPCleanEnd + "' ";
		}
		
		super.setAppendQuery(appendQueryStr);
	}

}