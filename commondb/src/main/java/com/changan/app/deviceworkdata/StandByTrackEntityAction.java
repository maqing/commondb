package com.changan.app.deviceworkdata;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.web.TrackEntityAction;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;

public class StandByTrackEntityAction extends TrackEntityAction 
implements Preparable, ServletRequestAware{
	private String timeStandByBegin;
	private String timeStandByEnd;
	private MetaService metaService;
	private EntityService entityService;
	
	public String getTimeStandByBegin() {
		return timeStandByBegin;
	}

	public void setTimeStandByBegin(String timeStandByBegin) {
		this.timeStandByBegin = timeStandByBegin;
	}

	public String getTimeStandByEnd() {
		return timeStandByEnd;
	}

	public void setTimeStandByEnd(String timeStandByEnd) {
		this.timeStandByEnd = timeStandByEnd;
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
		if ((timeStandByBegin!=null)&&(timeStandByBegin.length()>0)) {
			appendQueryStr = appendQueryStr + " and t_entity_" + String.valueOf(EntityDefine.StandbyMetaId) 
					+ "." + EntityDefine.Standby_StartTime_CN + " >= '" + timeStandByBegin + "' ";
		}
		if ((timeStandByEnd!=null)&&(timeStandByEnd.length()>0)) {
			appendQueryStr = appendQueryStr + " and t_entity_" + String.valueOf(EntityDefine.StandbyMetaId) 
					+ "." + EntityDefine.Standby_StartTime_CN + " <= '" + timeStandByEnd + "' ";
		}
		
		super.setAppendQuery(appendQueryStr);
	}

/*	 public void addActionError(String anErrorMessage){
		   String s=anErrorMessage;
		   System.out.println(s);
		  }
		  public void addActionMessage(String aMessage){
		   String s=aMessage;
		   System.out.println(s);
		 
		  }
		  public void addFieldError(String fieldName, String errorMessage){
		   String s=errorMessage;
		   String f=fieldName;
		   System.out.println(s);
		   System.out.println(f);
		 
		  }	*/
}
