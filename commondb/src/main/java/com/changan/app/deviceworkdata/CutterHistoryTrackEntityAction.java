package com.changan.app.deviceworkdata;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.web.TrackEntityAction;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;

public class CutterHistoryTrackEntityAction extends TrackEntityAction 
implements Preparable, ServletRequestAware{
	private String timeChangeBegin;
	private String timeChangeEnd;
	private MetaService metaService;
	private EntityService entityService;
	

	public String getTimeChangeBegin() {
		return timeChangeBegin;
	}

	public void setTimeChangeBegin(String timeChangeBegin) {
		this.timeChangeBegin = timeChangeBegin;
	}

	public String getTimeChangeEnd() {
		return timeChangeEnd;
	}

	public void setTimeChangeEnd(String timeChangeEnd) {
		this.timeChangeEnd = timeChangeEnd;
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
		if ((timeChangeBegin!=null)&&(timeChangeBegin.length()>0)) {
			appendQueryStr = appendQueryStr + " and t_entity_" + String.valueOf(EntityDefine.ChangeCutterMetaId) 
					+ "." + EntityDefine.ChangeCutter_ChangeTime_CN + " >= '" + timeChangeBegin + "' ";
		}
		if ((timeChangeEnd!=null)&&(timeChangeEnd.length()>0)) {
			appendQueryStr = appendQueryStr  + " and t_entity_" + String.valueOf(EntityDefine.ChangeCutterMetaId) 
					+ "." + EntityDefine.ChangeCutter_ChangeTime_CN + " <= '" + timeChangeEnd + "' ";
		}
		
		super.setAppendQuery(appendQueryStr);
	}

}
