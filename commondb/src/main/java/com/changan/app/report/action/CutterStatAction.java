package com.changan.app.report.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
import com.changan.app.report.model.Cutter;
import com.changan.app.report.model.CutterStat;
import com.changan.app.report.model.Device;
import com.changan.app.report.model.DeviceWorkTime;
import com.changan.app.report.service.CutterStatService;
import com.changan.app.report.service.DeviceService;
import com.commondb.app.web.BasicAction;
import com.opensymphony.xwork2.Preparable;

public class CutterStatAction   extends BasicAction
implements Preparable, ServletRequestAware {
	
	private String lineId;
	private String deviceType;
	private String deviceId;
	private String cutterType;
	private String cutterId;
	private String timeBegin;
	private String timeEnd;	
	private List<Cutter> cutterItemList;
	private CutterStat cutterStatItem;
	private List<CutterStat> cutterStatItemList;
	
	private CutterStatService cutterStatService;

	private DeviceService deviceService;
	
	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the cutterType
	 */
	public String getCutterType() {
		return cutterType;
	}

	/**
	 * @param cutterType the cutterType to set
	 */
	public void setCutterType(String cutterType) {
		this.cutterType = cutterType;
	}

	public String getCutterId() {
		return cutterId;
	}

	public void setCutterId(String cutterId) {
		this.cutterId = cutterId;
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

	public CutterStatService getCutterStatService() {
		return cutterStatService;
	}

	public void setCutterStatService(CutterStatService cutterStatService) {
		this.cutterStatService = cutterStatService;
	}

	
	
	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	/**
	 * @return the cutterItemList
	 */
	public List<Cutter> getCutterItemList() {
		return cutterItemList;
	}

	/**
	 * @param cutterItemList the cutterItemList to set
	 */
	public void setCutterItemList(List<Cutter> cutterItemList) {
		this.cutterItemList = cutterItemList;
	}
	
	public CutterStat getCutterStatItem() {
		return cutterStatItem;
	}

	public void setCutterStatItem(CutterStat cutterStatItem) {
		this.cutterStatItem = cutterStatItem;
	}

	public List<CutterStat> getCutterStatItemList() {
		return cutterStatItemList;
	}

	public void setCutterStatItemList(List<CutterStat> cutterStatItemList) {
		this.cutterStatItemList = cutterStatItemList;
	}

	public String queryCutterList() {
		cutterItemList = cutterStatService.getCutterList(lineId, deviceType, deviceId);
		return "success";
	}
	
	public String getSingleCutterStat() {
		cutterStatItem = cutterStatService.getSingleCutterStat(lineId, deviceId, cutterId, timeBegin, timeEnd);
		return "success";
	}
	
	public String getCutterCompareStat() {
		cutterStatItemList = cutterStatService.getCutterCompareStat(lineId, deviceType, cutterType, timeBegin, timeEnd);
		return "success";
	}
	
	
	public String queryCutterDetailExport() {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");
	    String fileName = "刀具使用情况统计.xls";
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
	   		jxl.write.WritableSheet ws = wwb.createSheet("刀具使用情况统计", 0);
	   		ws.setColumnView(0, 25);
	   		ws.setColumnView(1, 25);
	   		ws.setColumnView(2, 25);
	   		ws.setColumnView(3, 25);
	   		ws.setColumnView(4, 25);
	   		
	   		int rowIdx = 0;
			ws.addCell(new jxl.write.Label(0, rowIdx, "设备名称：", wcf_header));
//			ws.addCell(new jxl.write.Label(1, rowIdx, "12", wcf_header));
			List<Device> deviceList = deviceService.getDevice("");
	   		if(!CollectionUtils.isEmpty(deviceList)){
	   			for(Device device : deviceList){
	   				if(device.getDeviceId().equals(deviceId)){
	   					ws.addCell(new jxl.write.Label(1, rowIdx, device.getDeviceName(), wcf_header));
	   					break;
	   				}
	   			}
	   		}
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
   			ws.addCell(new jxl.write.Label(0, rowIdx, "更换刀具次数", wcf_header));    
   			ws.addCell(new jxl.write.Label(1, rowIdx, "平均加工次数", wcf_header));    
   			ws.addCell(new jxl.write.Label(2, rowIdx, "本刀加工数量", wcf_header));     
   			
   			//明细数据
   			CutterStat cutterStatItem = cutterStatService.getSingleCutterStat(lineId, deviceId, cutterId, timeBegin, timeEnd);
   			
		    rowIdx++;
	   		ws.addCell(new jxl.write.Label(0, rowIdx, null != cutterStatItem ? Integer.toString(cutterStatItem.getChangeCounter())  : "0", wcf_center));    
	   		ws.addCell(new jxl.write.Label(1, rowIdx, null != cutterStatItem ? Integer.toString(cutterStatItem.getAverageProcessNum()) : "0" , wcf_center));    
	   		ws.addCell(new jxl.write.Label(2, rowIdx, null != cutterStatItem ? Integer.toString(cutterStatItem.getOwnProcessNum()) : "0" , wcf_center));    
            wwb.write();    
            wwb.close();
            output.close();
          } catch (Exception e) {   
             e.printStackTrace();   
          } 
		return "success";
	}
	
	private String getDeviceTypeName(String deviceType) {
		String deviceNameCol = "";
		if (deviceType == null ) {
			deviceNameCol = "";
		} else if (deviceType.equals(String.valueOf(EntityDefine.CgcDeviceMetaId))) {
			deviceNameCol = "CGC";
		} else if (deviceType.equals(String.valueOf(EntityDefine.MachineDeviceMetaId))) {
			deviceNameCol = "机床";
		} else if (deviceType.equals(String.valueOf(EntityDefine.RobotDeviceMetaId))) {
			deviceNameCol = "机器手";
		} else if (deviceType.equals(String.valueOf(EntityDefine.ShockSandDeviceMetaId))) {
			deviceNameCol = "震砂机";
		} else if (deviceType.equals(String.valueOf(EntityDefine.TransferDeviceMetaId))) {
			deviceNameCol = "物流线";
		}
		return deviceNameCol;
	}
	
	public String queryCutterStatusCompExport() {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");
	    String fileName = "设备状态对比.xls";
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
	   		jxl.write.WritableSheet ws = wwb.createSheet("设备状态对比", 0);
	   		ws.setColumnView(0, 25);
	   		ws.setColumnView(1, 25);
	   		ws.setColumnView(2, 25);
	   		ws.setColumnView(3, 25);
	   		ws.setColumnView(4, 25);
	   		
	   		int rowIdx = 0;
			ws.addCell(new jxl.write.Label(0, rowIdx, "设备种类：", wcf_header));
			ws.addCell(new jxl.write.Label(1, rowIdx, getDeviceTypeName(deviceType), wcf_header));
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
   			ws.addCell(new jxl.write.Label(0, rowIdx, "刀具编号", wcf_header)); 
   			ws.addCell(new jxl.write.Label(1, rowIdx, "刀具名称", wcf_header));
   			ws.addCell(new jxl.write.Label(2, rowIdx, "更换刀具次数", wcf_header));    
   			ws.addCell(new jxl.write.Label(3, rowIdx, "平均加工次数", wcf_header));      
   			
   			//明细数据
   			List<CutterStat> cutterStatItemList = cutterStatService.getCutterCompareStat(lineId, deviceType, cutterType, timeBegin, timeEnd);
		    for(int i = 0 ; i < cutterStatItemList.size() ; i ++) { 
		    	CutterStat cutterStat =  cutterStatItemList.get(i);
		    	rowIdx++;
	   			ws.addCell(new jxl.write.Label(0, rowIdx, cutterStat.getCutterCode(), wcf_center));    
	   			ws.addCell(new jxl.write.Label(1, rowIdx, cutterStat.getCutterName(), wcf_center));    
	   			ws.addCell(new jxl.write.Label(2, rowIdx, Integer.toString(cutterStat.getChangeCounter())  , wcf_center));    
	   			ws.addCell(new jxl.write.Label(3, rowIdx, Integer.toString(cutterStat.getAverageProcessNum()), wcf_center));    
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
