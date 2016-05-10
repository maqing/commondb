package com.changan.app.deviceworkdata;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.web.TrackEntityAction;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.Preparable;

public class SparePartsTrackEntityAction extends TrackEntityAction 
implements Preparable, ServletRequestAware{
	private String sparePartsName;
	private String sparePartsStyle;
	private MetaService metaService;
	private EntityService entityService;
	

	public String getSparePartsName() {
		return sparePartsName;
	}

	public void setSparePartsName(String sparePartsName) {
		this.sparePartsName = sparePartsName;
	}

	public String getSparePartsStyle() {
		return sparePartsStyle;
	}

	public void setSparePartsStyle(String sparePartsStyle) {
		this.sparePartsStyle = sparePartsStyle;
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
		if ((sparePartsName!=null)&&(sparePartsName.length()>0)) {
			appendQueryStr = appendQueryStr + " and t_entity_" + String.valueOf(EntityDefine.SparePartsMetaId) 
					+ "." + EntityDefine.SpareParts_Name_CN + " like '%" + sparePartsName + "%' ";
		}
		if ((sparePartsStyle!=null)&&(sparePartsStyle.length()>0)) {
			appendQueryStr = appendQueryStr  + " and t_entity_" + String.valueOf(EntityDefine.SparePartsMetaId) 
					+ "." + EntityDefine.SpareParts_Style_CN + " like '%" + sparePartsStyle + "%' ";
		}
		
		super.setAppendQuery(appendQueryStr);
	}

}
