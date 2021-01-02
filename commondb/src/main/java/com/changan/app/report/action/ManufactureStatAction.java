package com.changan.app.report.action;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.util.CollectionUtils;

import com.changan.app.datamodel.EntityDefine;
import com.changan.app.report.model.Device;
import com.changan.app.report.model.ManufactureInfo;
import com.changan.app.report.service.ManufactureService;
import com.commondb.app.web.BasicAction;
import com.commondb.app.web.JqGridBaseAction;
import com.opensymphony.xwork2.Preparable;

public class ManufactureStatAction   extends JqGridBaseAction {
	
	private String workpieceCode;
	private String timeBegin;
	private String timeEnd;
	private ManufactureService manufactureService;
	
	public String getWorkpieceCode() {
		return workpieceCode;
	}
	public void setWorkpieceCode(String workpieceCode) {
		this.workpieceCode = workpieceCode;
	}
	public String getTimeBegin() {
		return timeBegin;
	}
	public void setTimeBegin(String timeBegin) {
		this.timeBegin = timeBegin;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public ManufactureService getManufactureService() {
		return manufactureService;
	}
	public void setManufactureService(ManufactureService manufactureService) {
		this.manufactureService = manufactureService;
	}
	
	public String queryManufactureInfo() {
	    return refreshGridModel();  
	}
	@Override
	public void initQueryStr() {
		
	}
	@Override
	public int getResultSize() {
		return manufactureService.queryManufactureInfoListSize(workpieceCode, timeBegin, timeEnd);
	}
	@Override
	public List listResults(int from, int length) {
		return manufactureService.queryManufactureInfo(workpieceCode, timeBegin, timeEnd, from, length);
	}
	
	
	public String queryCastingExport() {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");
	    String fileName = "铸件加工信息.xls";
	    try {
			response.setHeader("Content-disposition","attachment; filename=" + new String(fileName.getBytes("GBK"),"ISO-8859-1"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	    java.io.OutputStream output = null;
	    
	    try {
	        WritableFont BlodFont ;             // 粗体
	        WritableFont NormalFont ;        // 普通字体
	    	WritableCellFormat wcf_header;
	    	WritableCellFormat wcf_center ; 
	    	WritableCellFormat wcf_center_number ; 
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
	    	
            // 用于正文数字
            wcf_center_number = new WritableCellFormat(new NumberFormat("#.##"));                // 实例化单元格格式对象（正文、居中）
            wcf_center_number.setBorder(Border.ALL, BorderLineStyle.THIN);         // 线条
            wcf_center_number.setVerticalAlignment(VerticalAlignment.CENTRE);     // 垂直对齐
            wcf_center_number.setAlignment(Alignment.CENTRE);
           // wcf_left.setLocked(false) ;                                    // 设置锁定，还得设置SheetSettings启用保护和设置密码
            wcf_center_number.setWrap(true);                                     // 是否换行
	    	
	    	
	    	output = response.getOutputStream();
	     	jxl.write.WritableWorkbook wwb = jxl.Workbook.createWorkbook(output);
	   		jxl.write.WritableSheet ws = wwb.createSheet("铸件加工信息", 0);
	   		ws.setColumnView(0, 25);
	   		ws.setColumnView(1, 25);
	   		ws.setColumnView(2, 25);
	   		ws.setColumnView(3, 25);
	   		ws.setColumnView(4, 25);
	   		
	   		int rowIdx = 0;
	   		
	   		
	   		
//			ws.addCell(new jxl.write.Label(0, rowIdx, "设备名称：", wcf_header));
//			ws.addCell(new jxl.write.Label(1, rowIdx, "", wcf_header));
			String dateQueryConditionStr = "";
			if (timeBegin!=null) 
				dateQueryConditionStr += "从" + timeBegin;
			if (timeEnd!=null) 
				dateQueryConditionStr += "至" + timeEnd;
			if ((dateQueryConditionStr!=null) && (dateQueryConditionStr.length()>0)) {
				rowIdx++;
				ws.addCell(new jxl.write.Label(0, rowIdx, dateQueryConditionStr, wcf_header));
				ws.mergeCells(0, rowIdx, 1, rowIdx);
			}
	   		
			//标题
			rowIdx++;
			ws.addCell(new jxl.write.Label(0, rowIdx, "工件代码", wcf_header)); 
   			ws.addCell(new jxl.write.Label(1, rowIdx, "加工日期", wcf_header));    
   			ws.addCell(new jxl.write.Label(2, rowIdx, "工件状态", wcf_header));     
   			ws.addCell(new jxl.write.Label(3, rowIdx, "震动落砂设备号", wcf_header));    
   			ws.addCell(new jxl.write.Label(4, rowIdx, "震动落砂时间", wcf_header));
   			ws.addCell(new jxl.write.Label(5, rowIdx, "加工中心设备号", wcf_header));
   			ws.addCell(new jxl.write.Label(6, rowIdx, "加工中心时间", wcf_header));
   			ws.addCell(new jxl.write.Label(7, rowIdx, "毛刺清理设备号", wcf_header));
   			ws.addCell(new jxl.write.Label(8, rowIdx, "毛刺清理时间", wcf_header));
   			ws.addCell(new jxl.write.Label(9, rowIdx, "是否人工上线", wcf_header));
   			//明细数据
   			List detailList = manufactureService.queryManufactureInfo(workpieceCode, timeBegin, timeEnd, 0, manufactureService.queryManufactureInfoListSize(workpieceCode, timeBegin, timeEnd));
   			for(Object object : detailList){
		    	Map jo = (Map)object;
		    	rowIdx++;
		    	ws.addCell(new jxl.write.Label(0, rowIdx, jo.get("wpname") != null ? jo.get("wpname").toString() : "", wcf_center));
	   			ws.addCell(new jxl.write.Label(1, rowIdx, jo.get("wpdate") != null ? jo.get("wpdate").toString() : "" , wcf_center));    
	   			ws.addCell(new jxl.write.Label(2, rowIdx, jo.get("wpstate") != null ? jo.get("wpstate").toString() : "", wcf_center));       
	   			ws.addCell(new jxl.write.Label(3, rowIdx, jo.get("shockname") != null ? jo.get("shockname").toString() : "", wcf_center)); 
	   			ws.addCell(new jxl.write.Label(4, rowIdx, jo.get("shocktime") != null ? jo.get("shocktime").toString() : "", wcf_center)); 
	   			ws.addCell(new jxl.write.Label(5, rowIdx, jo.get("machinename") != null ? jo.get("machinename").toString() : "", wcf_center)); 
	   			ws.addCell(new jxl.write.Label(6, rowIdx, jo.get("machinetime") != null ? jo.get("machinetime").toString() : "", wcf_center)); 
	   			ws.addCell(new jxl.write.Label(7, rowIdx, jo.get("cgcname") != null ? jo.get("cgcname").toString() : "", wcf_center)); 
	   			ws.addCell(new jxl.write.Label(8, rowIdx, jo.get("cgctime") != null ? jo.get("cgctime").toString() : "", wcf_center)); 
	   			ws.addCell(new jxl.write.Label(9, rowIdx, jo.get("isRemake") != null ? jo.get("isRemake").toString() : "", wcf_center)); 
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
