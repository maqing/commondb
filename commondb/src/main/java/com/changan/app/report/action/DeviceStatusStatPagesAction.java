package com.changan.app.report.action;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.commondb.app.web.BasicAction;
import com.opensymphony.xwork2.Preparable;

public class DeviceStatusStatPagesAction extends BasicAction
implements Preparable, ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 设备故障统计
	 * @return
	 */
	public String genEquiMalfunctionForm()
	{
	
		return "success";
	}
    
	/**
	 * 刀具统计
	 * @return
	 */
	public String genCutterForm()
	{
	
		return "success";
	}
	
	/**
	 * 铸件加工信息查询
	 * @return
	 */
	public String genCastingForm()
	{
	
		return "success";
	}
	/**
	 * 设备维护修养提醒
	 * @return
	 */
	public String genEquiMataintanceForm()
	{
	
		return "success";
	}
}
