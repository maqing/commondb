package com.changan.app.report.action;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Collections;  
import java.util.Date;
import java.util.HashMap;
import java.util.List;  
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

import org.apache.struts2.ServletActionContext;

import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.web.JqGridBaseAction;
import com.commondb.common.DateUtil;
import com.commondb.db.service.EntityService;

public class DeviceSparePartsLifeReportAction extends JqGridBaseAction {  
    
	private EntityService entityService;  
	private String entityName;
	private String entityId;
	private String sparePartsName;
	private String sparePartsStyle;
	private String deviceMetaId;

	private String columnCountString;
	private String columnString;
	private String fromStr;
	private String whereCountInitStr ;
	private String whereInitStr ;
	private String groupStr ;
  
	public void setEntityService(EntityService entityService) {  
		this.entityService = entityService;  
	}
	
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	public String getEntityId() {
		return entityId;
	}
	
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	
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

	/**
	 * @return the deviceMetaId
	 */
	public String getDeviceMetaId() {
		return deviceMetaId;
	}

	/**
	 * @param deviceMetaId the deviceMetaId to set
	 */
	public void setDeviceMetaId(String deviceMetaId) {
		this.deviceMetaId = deviceMetaId;
	}
	
	@Override  
	public void initQueryStr() {
		columnCountString = "count(distinct e.id) as totalCount ";
			  
		columnString = " e." + EntityDefine.SpareParts_Name_CN + " as sparePartsName, e." + EntityDefine.SpareParts_Style_CN + " as  sparePartsStyle, " 
				+ " e." + EntityDefine.SpareParts_AlarmPerent_CN + " as alarmPercent, e." + EntityDefine.SpareParts_LifeTime_CN + " as designLife, "
				+ " (case when e." + EntityDefine.SpareParts_RecentTime_CN + " is null or e." + EntityDefine.SpareParts_RecentTime_CN + "='' then TIMESTAMPDIFF(second,e." + EntityDefine.SpareParts_BeginTime_CN +",now()) "
				+ " else TIMESTAMPDIFF(second,e." + EntityDefine.SpareParts_RecentTime_CN + ", now()) end)  as useSecond, "
				+ " sum( " 
				+ " case when " + EntityDefine.Run_EndTime_CN + " is null or " + EntityDefine.Run_EndTime_CN +" =''  then TIMESTAMPDIFF(second,b." + EntityDefine.Run_StartTime_CN + ",now()) "
				+ " else TIMESTAMPDIFF(second,b." + EntityDefine.Run_StartTime_CN + ",b." + EntityDefine.Run_EndTime_CN + ") end ) as runSecond, "
				+ " (select count(*)  from r_entity h  " 
				+ " 		where h.entity2_id=c.id and h.meta2_id=" + deviceMetaId 
				+ " 		and h.meta1_id=" + EntityDefine.WorkpieceCleanMetaId +") as processCount,  "
				+ " 				 (select data_id from r_entity_chara_data j  "
				+ " where j.entity_id=e.id and j.meta_id=" + EntityDefine.SparePartsMetaId
 				+ " and j.chara_id=" + EntityDefine.Chara_LifeType + ") as lifeType, "
 				+ " (select data_id from r_entity_chara_data k  "
 				+ " where k.entity_id=e.id and k.meta_id=" + EntityDefine.SparePartsMetaId
 				+ " and k.chara_id=" + EntityDefine.Chara_LifeUnit + ") as lifeUnit " ; 
		
		
		/*columnString = "a.s_date ,sum( "
				  	+ " case when date(b." + EntityDefine.Run_StartTime_CN + ")=a.s_date and date(b." + EntityDefine.Run_EndTime_CN + ")=a.s_date  then TIMESTAMPDIFF(second,b." + EntityDefine.Run_StartTime_CN + ",b." + EntityDefine.Run_EndTime_CN + ") "
				  	+ "	when date(b." + EntityDefine.Run_StartTime_CN + ")=a.s_date and date(b." + EntityDefine.Run_EndTime_CN + ")>a.s_date then TIMESTAMPDIFF(second,b." + EntityDefine.Run_StartTime_CN + ",DATE_ADD(a.s_date,INTERVAL 1 DAY)) "
				  	+ "	when date(b." + EntityDefine.Run_StartTime_CN + ")<a.s_date and date(b." + EntityDefine.Run_EndTime_CN + ")=a.s_date then TIMESTAMPDIFF(second,a.s_date,b." + EntityDefine.Run_EndTime_CN + ") "
				  	+ " when (" + EntityDefine.Run_EndTime_CN + " is null or " + EntityDefine.Run_EndTime_CN +"='') and a.s_date=date(now()) then TIMESTAMPDIFF(second,a.s_date,now()) "
				  	+ "	else 86400 end ) as runSecond ";*/
				  
		fromStr = " t_entity_" + EntityDefine.RunMetaId +" b, t_entity_" + deviceMetaId + " c, r_entity d, t_entity_" + EntityDefine.SparePartsMetaId +" e, r_entity f ";
		//fromStr = " t_date a,t_entity_" + EntityDefine.RunMetaId + " b, t_entity_" + deviceMetaId + " c, r_entity d ";
		whereCountInitStr = " (( e." + EntityDefine.SpareParts_RecentTime_CN + " is null and UNIX_TIMESTAMP(e." + EntityDefine.SpareParts_BeginTime_CN +")<= UNIX_TIMESTAMP(b." + EntityDefine.Run_StartTime_CN +")) "
				+ " or (e." + EntityDefine.SpareParts_RecentTime_CN + "  is not null and UNIX_TIMESTAMP(e." + EntityDefine.SpareParts_RecentTime_CN + ")<= UNIX_TIMESTAMP(b." + EntityDefine.Run_StartTime_CN +"))) "
				+ " and d.entity2_id=c.id and d.meta2_id=" + deviceMetaId 
				+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.RunMetaId 
				+ " and f.entity2_id=c.id and f.meta2_id=" + deviceMetaId 
			 	+ " and f.entity1_id=e.id and f.meta1_id=" + EntityDefine.SparePartsMetaId;
			 	//+ " and c.id='2a99b825-b27a-4b0d-a0b4-4f5eadd5cf7f'  ";

		/*whereCountInitStr = " date(b." + EntityDefine.Run_StartTime_CN + ")<=a.s_date "
				+ " and (date(b." + EntityDefine.Run_EndTime_CN + ")>=a.s_date or ((" + EntityDefine.Run_EndTime_CN 
				+ " is null or " + EntityDefine.Run_EndTime_CN +"='') and a.s_date<=date(now()))) "
				+ " and d.entity2_id=c.id and d.meta2_id=" + deviceMetaId +" "
				+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.RunMetaId + " "
				;*/
		whereInitStr = whereCountInitStr;
		groupStr =  " group by e." + EntityDefine.SpareParts_Name_CN + ", e." + EntityDefine.SpareParts_Style_CN;
	}
	
	@Override  
	public int getResultSize() {  
		List countResults = Collections.emptyList();  
		countResults = this.entityService.dynSelect(columnCountString, fromStr, appendWhereStr(whereCountInitStr));
		if (countResults.size()>0) {
			Map countMap = (Map) countResults.get(0);
			/*Map userData = new HashMap<String, String>();  
			userData.put("s_date", "合计");
			if (countMap.get("runSecond")!=null) {
				userData.put("runSecond", DateUtil.toDHMS(countMap.get("runSecond").toString()));
			} else {
				userData.put("runSecond", DateUtil.toDHMS("0"));
			}
			setUserdata(userData);*/
			return Integer.parseInt(countMap.get("totalCount").toString());
		} else {
			return 0;
		}
	}  

	@Override  
	public List listResults(int from, int length) {  
		List results = Collections.emptyList();  
		String whereStr = appendWhereStr(whereInitStr) + groupStr + " limit " + length + " offset " + from;
		results = this.entityService.dynSelect(columnString, fromStr, whereStr);
		
	    for(int i = 0 ; i < results.size() ; i ++) { 
	    	Map resultItemMap = (Map) results.get(i);
	    	
    		DecimalFormat dfTime=new DecimalFormat("#0.00"); 
    		DecimalFormat dfCount=new DecimalFormat("#"); 
	    	//判断是否要预警
	    	double designLife = 0;
	    	try {
	    		designLife = Double.parseDouble(resultItemMap.get("designLife").toString());
	    	} catch (Exception e) {}
	    	double alarmPercent = 0;
	    	try {
	    		alarmPercent = Double.parseDouble(resultItemMap.get("alarmPercent").toString());
	    	} catch (Exception e) {
	    		alarmPercent = 1;
	    	}
	    	String lifeType = "";
	    	try {
	    		lifeType = resultItemMap.get("lifeType").toString();
	    	} catch (Exception e) {
	    		lifeType = EntityDefine.Chara_LifeType_ProcessCount;
	    	}
	    	String lifeUnit = "";
	    	try {
	    		lifeUnit = resultItemMap.get("lifeUnit").toString();
	    	} catch (Exception e) {
	    		lifeUnit = EntityDefine.Chara_LifeUnit_Item;
	    	}
	    	if (lifeType.length()>0) {
	    		double workLife = 0;
	    		//计算工作量或时间
	    		if (lifeType.equals(EntityDefine.Chara_LifeType_ProcessCount)) {
	    			workLife =  Double.parseDouble(resultItemMap.get("processCount").toString());
	    		} else if (lifeType.equals(EntityDefine.Chara_LifeType_RunTime)) {
	    			workLife =  Double.parseDouble(resultItemMap.get("runSecond").toString());
	    		} else {
	    			workLife =  Double.parseDouble(resultItemMap.get("useSecond").toString());
	    		}
	    		//归并计量单位
	    		if (lifeUnit.length()>0) {
	    			if (lifeUnit.equals(EntityDefine.Chara_LifeUnit_Day)) {
	    				workLife = workLife/(24*3600);
	    				resultItemMap.put("workLife", dfTime.format(workLife)+"天");
	    				resultItemMap.put("designLife", resultItemMap.get("designLife").toString()+"天");
	    			} else if (lifeUnit.equals(EntityDefine.Chara_LifeUnit_Hour)) {
	    				workLife = workLife/(3600);
	    				resultItemMap.put("workLife", dfTime.format(workLife)+"小时");
	    				resultItemMap.put("designLife", resultItemMap.get("designLife").toString()+"小时");
	    			} else {
	    				resultItemMap.put("workLife", dfCount.format(workLife)+"件");
	    				resultItemMap.put("designLife", resultItemMap.get("designLife").toString()+"件");
	    			}
	    		}
	    		
				resultItemMap.put("alarmPercent", dfCount.format(alarmPercent*100)+"%");
				if (designLife>0) {
					resultItemMap.put("workPercent", dfCount.format(workLife*100/designLife)+"%");
				}
	    		if ((designLife>0) && (workLife>=designLife*alarmPercent)) {
	    			//报警
	    			resultItemMap.put("alarmFlag","1");
	    		} else {
	    			resultItemMap.put("alarmFlag","0");
	    		}
	    	}
	    }
		return results;
	} 
    
	
	private String appendWhereStr(String whereStr) {
		String resultStr = whereStr;
		if (entityId!=null) 
			resultStr = resultStr + " and c.id='" + entityId + "' ";
		if ((sparePartsName!=null)&&(!sparePartsName.equals(""))) 
			resultStr = resultStr + " and e." + EntityDefine.SpareParts_Name_CN + " like '%" + sparePartsName + "%' ";
		if ((sparePartsStyle!=null)&&(!sparePartsStyle.equals(""))) 
			resultStr = resultStr + " and e." + EntityDefine.SpareParts_Style_CN + " like '%" + sparePartsStyle + "%' ";
		return resultStr;
	}
	
	public String ExportToExcelFile(String metaId, String device_ID_CN, String device_Name_CN) {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");
	    String fileName = "备件检修预警表.xls";
	    try {
			response.setHeader("Content-disposition","attachment; filename=" + new String(fileName.getBytes("GBK"),"ISO-8859-1"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    java.io.OutputStream output = null;
	    
	    try {
	        WritableFont BlodFont ;             // 粗体
	        WritableFont NormalFont ;        // 普通字体
	    	WritableCellFormat wcf_header;
	    	WritableCellFormat wcf_center ; 
	        BlodFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD ) ;
	        NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            // 初始化标题样式
            wcf_header = new WritableCellFormat(BlodFont, NumberFormats.TEXT) ; // 实例化单元格格式对象（标题、居中）
            wcf_header.setBorder(Border.ALL, BorderLineStyle.THIN) ;    // 线条
            wcf_header.setVerticalAlignment(VerticalAlignment.CENTRE);     // 垂直对齐
            wcf_header.setAlignment(Alignment.CENTRE);                     // 水平对齐
            wcf_header.setBackground(Colour.GRAY_25) ;                    // 背景颜色
            wcf_header.setWrap(true);                                     // 是否换行
            
            // 用于正文
            wcf_center = new WritableCellFormat(NormalFont);                // 实例化单元格格式对象（正文、居中）
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN);         // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE);     // 垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE);
           // wcf_left.setLocked(false) ;                                    // 设置锁定，还得设置SheetSettings启用保护和设置密码
            wcf_center.setWrap(true);                                     // 是否换行
	    	
	    	
	    	output = response.getOutputStream();
	     	jxl.write.WritableWorkbook wwb = jxl.Workbook.createWorkbook(output);
	   		jxl.write.WritableSheet ws = wwb.createSheet("检修预警表", 0);
	   		ws.setColumnView(0, 25);
	   		ws.setColumnView(1, 25);
	   		
	   		int rowIdx = 0;
	   		//设备基础信息
			List robotDeviceList = this.entityService.dynSelect(device_ID_CN + "," + device_Name_CN,
					" t_entity_" + metaId, " id='" + entityId + "'");
			if (robotDeviceList.size()>0) {
				Map robotItemMap = (Map) robotDeviceList.get(0);
				ws.addCell(new jxl.write.Label(0, rowIdx, "设备编码：", wcf_header));
				ws.addCell(new jxl.write.Label(1, rowIdx, (String)robotItemMap.get(device_ID_CN), wcf_header));
				rowIdx++;
				ws.addCell(new jxl.write.Label(0, rowIdx, "设备名称：", wcf_header));
				ws.addCell(new jxl.write.Label(1, rowIdx, (String)robotItemMap.get(device_Name_CN), wcf_header));
				/*
				String dateQueryConditionStr = "";
				if ((sparePartsName!=null)&&(sparePartsName.length()==10)) 
					dateQueryConditionStr += "从" + sparePartsName;
				if ((sparePartsStyle!=null)&&(sparePartsStyle.length()==10)) 
					dateQueryConditionStr += "至" + sparePartsStyle;
				if ((dateQueryConditionStr!=null) && (dateQueryConditionStr.length()>0)) {
					rowIdx++;
					ws.addCell(new jxl.write.Label(0, rowIdx, dateQueryConditionStr, wcf_header));
					ws.mergeCells(0, rowIdx, 1, rowIdx);
				} */
			}
	   		
			//标题
			rowIdx++;
   			ws.addCell(new jxl.write.Label(0, rowIdx, "名称", wcf_header));    
   			ws.addCell(new jxl.write.Label(1, rowIdx, "型号", wcf_header));    
   			ws.addCell(new jxl.write.Label(2, rowIdx, "设计寿命", wcf_header));    
   			ws.addCell(new jxl.write.Label(3, rowIdx, "报警阈值", wcf_header));    
   			ws.addCell(new jxl.write.Label(4, rowIdx, "实际使用", wcf_header));    
   			ws.addCell(new jxl.write.Label(5, rowIdx, "使用比例", wcf_header));    
   			
   			//明细数据
   			List detailList = this.entityService.dynSelect(columnString, fromStr, appendWhereStr(whereInitStr) + groupStr);
		    for(int i = 0 ; i < detailList.size() ; i ++) { 
		    	Map detailItemMap = (Map) detailList.get(i);
		    	rowIdx++;
	   			ws.addCell(new jxl.write.Label(0, rowIdx, detailItemMap.get("sparePartsName").toString(), wcf_center));    
	   			ws.addCell(new jxl.write.Label(1, rowIdx, detailItemMap.get("sparePartsStyle").toString(), wcf_center));    
	   			
	    		DecimalFormat dfTime=new DecimalFormat("#0.00"); 
	    		DecimalFormat dfCount=new DecimalFormat("#"); 
		    	//判断是否要预警
		    	double designLife = 0;
		    	try {
		    		designLife = Double.parseDouble(detailItemMap.get("designLife").toString());
		    	} catch (Exception e) {}
		    	double alarmPercent = 0;
		    	try {
		    		alarmPercent = Double.parseDouble(detailItemMap.get("alarmPercent").toString());
		    	} catch (Exception e) {
		    		alarmPercent = 1;
		    	}
		    	String lifeType = "";
		    	try {
		    		lifeType = detailItemMap.get("lifeType").toString();
		    	} catch (Exception e) {
		    		lifeType = EntityDefine.Chara_LifeType_ProcessCount;
		    	}
		    	String lifeUnit = "";
		    	try {
		    		lifeUnit = detailItemMap.get("lifeUnit").toString();
		    	} catch (Exception e) {
		    		lifeUnit = EntityDefine.Chara_LifeUnit_Item;
		    	}
		    	if (lifeType.length()>0) {
		    		double workLife = 0;
		    		//计算工作量或时间
		    		if (lifeType.equals(EntityDefine.Chara_LifeType_ProcessCount)) {
		    			workLife =  Double.parseDouble(detailItemMap.get("processCount").toString());
		    		} else if (lifeType.equals(EntityDefine.Chara_LifeType_RunTime)) {
		    			workLife =  Double.parseDouble(detailItemMap.get("runSecond").toString());
		    		} else {
		    			workLife =  Double.parseDouble(detailItemMap.get("useSecond").toString());
		    		}
		    		//归并计量单位
		    		if (lifeUnit.length()>0) {
		    			if (lifeUnit.equals(EntityDefine.Chara_LifeUnit_Day)) {
		    				workLife = workLife/(24*3600);
		    	   			ws.addCell(new jxl.write.Label(4, rowIdx, dfTime.format(workLife)+"天", wcf_center));    
		    	   			ws.addCell(new jxl.write.Label(2, rowIdx, detailItemMap.get("designLife").toString()+"天", wcf_center));    
		    			} else if (lifeUnit.equals(EntityDefine.Chara_LifeUnit_Hour)) {
		    				workLife = workLife/(3600);
		    	   			ws.addCell(new jxl.write.Label(4, rowIdx, dfTime.format(workLife)+"小时", wcf_center));    
		    	   			ws.addCell(new jxl.write.Label(2, rowIdx, detailItemMap.get("designLife").toString()+"小时", wcf_center));    
		    			} else {
		    	   			ws.addCell(new jxl.write.Label(4, rowIdx, dfCount.format(workLife)+"件", wcf_center));    
		    	   			ws.addCell(new jxl.write.Label(2, rowIdx, detailItemMap.get("designLife").toString()+"件", wcf_center));    
		    			}
		    		}
		    		
    	   			ws.addCell(new jxl.write.Label(3, rowIdx, dfCount.format(alarmPercent*100)+"%", wcf_center));    
					if (designLife>0) {
	    	   			ws.addCell(new jxl.write.Label(5, rowIdx,  dfCount.format(workLife*100/designLife)+"%", wcf_center));    
					}
		    	}
		    	
		    }
		    
            wwb.write();    
            wwb.close();
            output.close();
          } catch (Exception e) {   
             e.printStackTrace();   
          } 
		return "success";
	}
	
}  
