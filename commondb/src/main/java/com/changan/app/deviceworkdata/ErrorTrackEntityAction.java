package com.changan.app.deviceworkdata;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.web.TrackEntityAction;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;

public class ErrorTrackEntityAction extends TrackEntityAction 
implements Preparable, ServletRequestAware{
	private String timeErrorBegin;
	private String timeErrorEnd;
	private MetaService metaService;
	private EntityService entityService;
	

	public String getTimeErrorBegin() {
		return timeErrorBegin;
	}

	public void setTimeErrorBegin(String timeRunBegin) {
		this.timeErrorBegin = timeRunBegin;
	}

	public String getTimeErrorEnd() {
		return timeErrorEnd;
	}

	public void setTimeErrorEnd(String timeRunEnd) {
		this.timeErrorEnd = timeRunEnd;
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
		if ((timeErrorBegin!=null)&&(timeErrorBegin.length()>0)) {
			appendQueryStr = appendQueryStr + " and t_entity_" + String.valueOf(EntityDefine.ErrorMetaId) 
					+ "." + EntityDefine.Error_StartTime_CN + " >= '" + timeErrorBegin + "' ";
		}
		if ((timeErrorEnd!=null)&&(timeErrorEnd.length()>0)) {
			appendQueryStr = appendQueryStr  + " and t_entity_" + String.valueOf(EntityDefine.ErrorMetaId) 
					+ "." + EntityDefine.Error_StartTime_CN + " <= '" + timeErrorEnd + "' ";
		}
		
		super.setAppendQuery(appendQueryStr);
	}

}
