package com.changan.app.report.action;

import java.io.UnsupportedEncodingException;
import java.util.Collections;  
import java.util.Date;
import java.util.HashMap;
import java.util.List;  
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import jxl.CellView;
import jxl.HeaderFooter;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


import com.changan.app.datamodel.EntityDefine;
import com.commondb.app.web.JqGridBaseAction;
import com.commondb.common.DateUtil;
import com.commondb.db.service.EntityService;

public class DeviceStandbyReportAction extends JqGridBaseAction {  
    
	private EntityService entityService;  
	private String entityName;
	private String entityId;
	private String timeStandbyReportBegin;
	private String timeStandbyReportEnd;
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
	
	public String getTimeStandbyReportBegin() {
		return timeStandbyReportBegin;
	}
	
	public void setTimeStandbyReportBegin(String timeStandbyReportBegin) {
		this.timeStandbyReportBegin = timeStandbyReportBegin;
	}
	
	public String getTimeStandbyReportEnd() {
		return timeStandbyReportEnd;
	}
	
	public void setTimeStandbyReportEnd(String timeStandbyReportEnd) {
		this.timeStandbyReportEnd = timeStandbyReportEnd;
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
		columnCountString = "count(distinct a.s_date) as totalCount ,sum( "
			  	+ " case when date(b." + EntityDefine.Standby_StartTime_CN + ")=a.s_date and date(b." + EntityDefine.Standby_EndTime_CN + ")=a.s_date  then TIMESTAMPDIFF(second,b." + EntityDefine.Standby_StartTime_CN + ",b." + EntityDefine.Standby_EndTime_CN + ") "
			  	+ "	when date(b." + EntityDefine.Standby_StartTime_CN + ")=a.s_date and date(b." + EntityDefine.Standby_EndTime_CN + ")>a.s_date then TIMESTAMPDIFF(second,b." + EntityDefine.Standby_StartTime_CN + ",DATE_ADD(a.s_date,INTERVAL 1 DAY)) "
			  	+ "	when date(b." + EntityDefine.Standby_StartTime_CN + ")<a.s_date and date(b." + EntityDefine.Standby_EndTime_CN + ")=a.s_date then TIMESTAMPDIFF(second,a.s_date,b." + EntityDefine.Standby_EndTime_CN + ") "
			  	+ " when (" + EntityDefine.Standby_EndTime_CN + " is null or " + EntityDefine.Standby_EndTime_CN +"='') and a.s_date=date(now()) and date(b." + EntityDefine.Standby_StartTime_CN + ")<>a.s_date then TIMESTAMPDIFF(second,a.s_date,now()) "
			  	+ " when (" + EntityDefine.Standby_EndTime_CN + " is null or " + EntityDefine.Standby_EndTime_CN +"='') and date(b." + EntityDefine.Standby_StartTime_CN + ")=date(now()) then TIMESTAMPDIFF(second,b." + EntityDefine.Standby_StartTime_CN + ",now()) "
			  	+ "	else 86400 end ) as standbySecond ";
			  
		columnString = "a.s_date ,sum( "
				  	+ " case when date(b." + EntityDefine.Standby_StartTime_CN + ")=a.s_date and date(b." + EntityDefine.Standby_EndTime_CN + ")=a.s_date  then TIMESTAMPDIFF(second,b." + EntityDefine.Standby_StartTime_CN + ",b." + EntityDefine.Standby_EndTime_CN + ") "
				  	+ "	when date(b." + EntityDefine.Standby_StartTime_CN + ")=a.s_date and date(b." + EntityDefine.Standby_EndTime_CN + ")>a.s_date then TIMESTAMPDIFF(second,b." + EntityDefine.Standby_StartTime_CN + ",DATE_ADD(a.s_date,INTERVAL 1 DAY)) "
				  	+ "	when date(b." + EntityDefine.Standby_StartTime_CN + ")<a.s_date and date(b." + EntityDefine.Standby_EndTime_CN + ")=a.s_date then TIMESTAMPDIFF(second,a.s_date,b." + EntityDefine.Standby_EndTime_CN + ") "
				  	+ " when (" + EntityDefine.Standby_EndTime_CN + " is null or " + EntityDefine.Standby_EndTime_CN +"='') and a.s_date=date(now()) and date(b." + EntityDefine.Standby_StartTime_CN + ")<>a.s_date then TIMESTAMPDIFF(second,a.s_date,now()) "
				  	+ " when (" + EntityDefine.Standby_EndTime_CN + " is null or " + EntityDefine.Standby_EndTime_CN +"='') and date(b." + EntityDefine.Standby_StartTime_CN + ")=date(now()) then TIMESTAMPDIFF(second,b." + EntityDefine.Standby_StartTime_CN + ",now()) "
				  	+ "	else 86400 end ) as standbySecond ";
				  
		fromStr = " t_date a,t_entity_" + EntityDefine.StandbyMetaId + " b, t_entity_" + deviceMetaId + " c, r_entity d ";
		whereCountInitStr = " date(b." + EntityDefine.Standby_StartTime_CN + ")<=a.s_date " 
				+ " and (date(b." + EntityDefine.Standby_EndTime_CN + ")>=a.s_date or ((" + EntityDefine.Standby_EndTime_CN 
				+ " is null or " + EntityDefine.Standby_EndTime_CN +"='') and a.s_date<=date(now()))) "
				+ " and d.entity2_id=c.id and d.meta2_id=" + deviceMetaId +" "
				+ " and d.entity1_id=b.id and d.meta1_id=" + EntityDefine.StandbyMetaId + " "
				;
		whereInitStr = whereCountInitStr;
		groupStr =  " group by a.s_date ";
	}
	
	@Override  
	public int getResultSize() {  
		List countResults = Collections.emptyList();  
		countResults = this.entityService.dynSelect(columnCountString, fromStr, appendWhereStr(whereCountInitStr));
		if (countResults.size()>0) {
			Map countMap = (Map) countResults.get(0);
			Map userData = new HashMap<String, String>();  
			userData.put("s_date", "合计");
			if (countMap.get("standbySecond")!=null) {
				userData.put("standbySecond", DateUtil.toDHMS(countMap.get("standbySecond").toString()));
			} else {
				userData.put("standbySecond", DateUtil.toDHMS("0"));
			}
			setUserdata(userData);
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
		
		//把秒转成特定格式
	    for(int i = 0 ; i < results.size() ; i ++) { 
	    	Map resultItemMap = (Map) results.get(i);
	    	resultItemMap.put("standbySecond", DateUtil.toHMS(resultItemMap.get("standbySecond").toString()));
	    }
		return results;
	} 
    
	
	private String appendWhereStr(String whereStr) {
		String resultStr = whereStr;
		if (entityId!=null) 
			resultStr = resultStr + " and c.id='" + entityId + "' ";
		if ((timeStandbyReportBegin!=null)&&(timeStandbyReportBegin.length()==10)) 
			resultStr = resultStr + " and a.s_date>='" + timeStandbyReportBegin + "' ";
		if ((timeStandbyReportEnd!=null)&&(timeStandbyReportEnd.length()==10)) 
			resultStr = resultStr + " and a.s_date<='" + timeStandbyReportEnd + "' ";
		return resultStr;
	}
	
	public String ExportToExcelFile(String metaId, String device_ID_CN, String device_Name_CN) {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");
	    String fileName = "设备待机时间统计表.xls";
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
	   		jxl.write.WritableSheet ws = wwb.createSheet("待机时间统计表", 0);
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
				String dateQueryConditionStr = "";
				if ((timeStandbyReportBegin!=null)&&(timeStandbyReportBegin.length()==10)) 
					dateQueryConditionStr += "从" + timeStandbyReportBegin;
				if ((timeStandbyReportEnd!=null)&&(timeStandbyReportEnd.length()==10)) 
					dateQueryConditionStr += "至" + timeStandbyReportEnd;
				if ((dateQueryConditionStr!=null) && (dateQueryConditionStr.length()>0)) {
					rowIdx++;
					ws.addCell(new jxl.write.Label(0, rowIdx, dateQueryConditionStr, wcf_header));
					ws.mergeCells(0, rowIdx, 1, rowIdx);
				}
			}
	   		
			//标题
			rowIdx++;
   			ws.addCell(new jxl.write.Label(0, rowIdx, "日期", wcf_header));    
   			ws.addCell(new jxl.write.Label(1, rowIdx, "待机时长", wcf_header));    
   			
   			//明细数据
   			List detailList = this.entityService.dynSelect(columnString, fromStr, appendWhereStr(whereInitStr) + groupStr);
		    for(int i = 0 ; i < detailList.size() ; i ++) { 
		    	Map detailItemMap = (Map) detailList.get(i);
		    	rowIdx++;
	   			ws.addCell(new jxl.write.Label(0, rowIdx, DateUtil.formatDate((Date)detailItemMap.get("s_date")), wcf_center));    
	   			ws.addCell(new jxl.write.Label(1, rowIdx, DateUtil.toHMS(detailItemMap.get("standbySecond").toString()), wcf_center));    
		    }
		    
		    //汇总数据
		    List sumList = this.entityService.dynSelect(columnCountString, fromStr, appendWhereStr(whereCountInitStr));
			if (sumList.size()>0) {
				Map sumItemMap = (Map) sumList.get(0);
				rowIdx++;
	   			ws.addCell(new jxl.write.Label(0, rowIdx, "合计", wcf_center));    
	   			ws.addCell(new jxl.write.Label(1, rowIdx, DateUtil.toDHMS(sumItemMap.get("standbySecond").toString()), wcf_center));    
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
