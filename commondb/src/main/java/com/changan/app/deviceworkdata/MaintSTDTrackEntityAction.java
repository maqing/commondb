package com.changan.app.deviceworkdata;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.web.TrackEntityAction;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;

public class MaintSTDTrackEntityAction extends TrackEntityAction 
implements Preparable, ServletRequestAware{
	private String maintSTDContent;
	private String maintSTDDesc;
	private MetaService metaService;
	private EntityService entityService;
	

	public String getMaintSTDContent() {
		return maintSTDContent;
	}

	public void setMaintSTDContent(String maintSTDContent) {
		this.maintSTDContent = maintSTDContent;
	}

	public String getMaintSTDDesc() {
		return maintSTDDesc;
	}

	public void setMaintSTDDesc(String maintSTDDesc) {
		this.maintSTDDesc = maintSTDDesc;
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
		if ((maintSTDContent!=null)&&(maintSTDContent.length()>0)) {
			appendQueryStr = appendQueryStr + " and t_entity_" + String.valueOf(EntityDefine.MaintSTDMetaId) 
					+ "." + EntityDefine.MaintSTD_Content_CN + " like '%" + maintSTDContent + "%' ";
		}
		if ((maintSTDDesc!=null)&&(maintSTDDesc.length()>0)) {
			appendQueryStr = appendQueryStr  + " and t_entity_" + String.valueOf(EntityDefine.MaintSTDMetaId) 
					+ "." + EntityDefine.MaintSTD_Desc_CN + " like '%" + maintSTDDesc + "%' ";
		}
		
		super.setAppendQuery(appendQueryStr);
	}

}
