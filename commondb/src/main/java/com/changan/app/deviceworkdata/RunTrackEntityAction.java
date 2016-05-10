package com.changan.app.deviceworkdata;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.web.TrackEntityAction;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;

public class RunTrackEntityAction extends TrackEntityAction 
implements Preparable, ServletRequestAware{
	private String timeRunBegin;
	private String timeRunEnd;
	private MetaService metaService;
	private EntityService entityService;
	

	public String getTimeRunBegin() {
		return timeRunBegin;
	}

	public void setTimeRunBegin(String timeRunBegin) {
		this.timeRunBegin = timeRunBegin;
	}

	public String getTimeRunEnd() {
		return timeRunEnd;
	}

	public void setTimeRunEnd(String timeRunEnd) {
		this.timeRunEnd = timeRunEnd;
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
		if ((timeRunBegin!=null)&&(timeRunBegin.length()>0)) {
			appendQueryStr = appendQueryStr + " and t_entity_" + String.valueOf(EntityDefine.RunMetaId) 
					+ "." + EntityDefine.Run_StartTime_CN + " >= '" + timeRunBegin + "' ";
		}
		if ((timeRunEnd!=null)&&(timeRunEnd.length()>0)) {
			appendQueryStr = appendQueryStr  + " and t_entity_" + String.valueOf(EntityDefine.RunMetaId) 
					+ "." + EntityDefine.Run_StartTime_CN + " <= '" + timeRunEnd + "' ";
		}
		
		super.setAppendQuery(appendQueryStr);
	}
}
