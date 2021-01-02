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
import com.changan.app.report.model.Device;
import com.changan.app.report.model.DeviceWorkTime;
import com.changan.app.report.service.DeviceService;
import com.changan.app.report.service.DeviceStatusStatService;
import com.commondb.app.web.BasicAction;
import com.opensymphony.xwork2.Preparable;

public class DeviceStatusStatAction   extends BasicAction
implements Preparable, ServletRequestAware
{
	private String totalRunTime;
	private String normalRunTime;
	private String standbyTime;
	private String totalErrorTime;
	private String deviceOEE;

	private String lineId;
	private String deviceType;
	private String deviceId;
	private String timeBegin;
	private String timeEnd;	
	
	private String deviceName;
	
	private DeviceStatusStatService deviceStatusStatService;
	private DeviceService deviceService;
	private DeviceWorkTime deviceWorkTimeItem;
	private List<DeviceWorkTime> deviceWorkTimeList;
	private List<Device> deviceItemList;

	/**
	 * @return the deviceStatusStatService
	 */
	public DeviceStatusStatService getDeviceStatusStatService() {
		return deviceStatusStatService;
	}

	/**
	 * @param deviceStatusStatService the deviceStatusStatService to set
	 */
	public void setDeviceStatusStatService(DeviceStatusStatService deviceStatusStatService) {
		this.deviceStatusStatService = deviceStatusStatService;
	}

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	/**
	 * @return the deviceWorkTimeItem
	 */
	public DeviceWorkTime getDeviceWorkTimeItem() {
		return deviceWorkTimeItem;
	}

	/**
	 * @param deviceWorkTimeItem the deviceWorkTimeItem to set
	 */
	public void setDeviceWorkTimeItem(DeviceWorkTime deviceWorkTimeItem) {
		this.deviceWorkTimeItem = deviceWorkTimeItem;
	}

	/**
	 * @return the deviceWorkTimeItemList
	 */
	public List<DeviceWorkTime> getDeviceWorkTimeList() {
		return deviceWorkTimeList;
	}

	/**
	 * @param deviceWorkTimeItemList the deviceWorkTimeItemList to set
	 */
	public void setDeviceWorkTimeList(List<DeviceWorkTime> deviceWorkTimeList) {
		this.deviceWorkTimeList = deviceWorkTimeList;
	}

	public String getTotalRunTime() {
		return totalRunTime;
	}

	public void setTotalRunTime(String totalRunTime) {
		this.totalRunTime = totalRunTime;
	}

	public String getNormalRunTime() {
		return normalRunTime;
	}

	public void setNormalRunTime(String normalRunTime) {
		this.normalRunTime = normalRunTime;
	}

	public String getStandbyTime() {
		return standbyTime;
	}

	public void setStandbyTime(String standbyTime) {
		this.standbyTime = standbyTime;
	}

	public String getTotalErrorTime() {
		return totalErrorTime;
	}

	public void setTotalErrorTime(String totalErrorTime) {
		this.totalErrorTime = totalErrorTime;
	}

	public String getDeviceOEE() {
		return deviceOEE;
	}

	public void setDeviceOEE(String deviceOEE) {
		this.deviceOEE = deviceOEE;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	
	
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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

	/**
	 * @return the deviceItemList
	 */
	public List<Device> getDeviceItemList() {
		return deviceItemList;
	}

	/**
	 * @param deviceItemList the deviceItemList to set
	 */
	public void setDeviceItemList(List<Device> deviceItemList) {
		this.deviceItemList = deviceItemList;
	}

	public String genForm()
	{
	
		return "success";
	}
	
	@Override  
	public String execute() {
		/*totalRunTime = "7823h";
		normalRunTime = "5012h";
		standbyTime = "232h";
		totalErrorTime = "100h";
		deviceOEE = "70%"; */
		deviceWorkTimeItem = deviceStatusStatService.getDeviceWorkTimeStat(lineId, deviceType, deviceId, timeBegin, timeEnd);
		return "success";
	}

	public String queryDeviceTypeStat() {
		deviceWorkTimeList = deviceStatusStatService.getDeviceTypeWorkTimeStat(lineId, deviceType, timeBegin, timeEnd);
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
	
	public String queryDeviceTypeStatExport() {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");
	    String fileName = "设备状态对比结果.xls";
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
	   		jxl.write.WritableSheet ws = wwb.createSheet("设备状态对比结果", 0);
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
   			ws.addCell(new jxl.write.Label(0, rowIdx, "设备名称", wcf_header));    
   			ws.addCell(new jxl.write.Label(1, rowIdx, "正常运行时间(h)", wcf_header));    
   			ws.addCell(new jxl.write.Label(2, rowIdx, "待机时间(h)", wcf_header));    
   			ws.addCell(new jxl.write.Label(3, rowIdx, "故障总时间(h)", wcf_header));    
   			ws.addCell(new jxl.write.Label(4, rowIdx, "OEE", wcf_header));    
   			
   			java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.##");
   			//明细数据
   			List<DeviceWorkTime> detailList = deviceStatusStatService.getDeviceTypeWorkTimeStat(lineId, deviceType, timeBegin, timeEnd);
		    for(int i = 0 ; i < detailList.size() ; i ++) { 
		    	DeviceWorkTime detailItem =  detailList.get(i);
		    	rowIdx++;
	   			ws.addCell(new jxl.write.Label(0, rowIdx, detailItem.getDeviceName(), wcf_center));    
	   			ws.addCell(new jxl.write.Label(1, rowIdx, df.format(Double.valueOf(
	   					detailItem.getNormalRunTime()).doubleValue()) , wcf_center));    
	   			ws.addCell(new jxl.write.Label(2, rowIdx, df.format(Double.valueOf(
	   					detailItem.getStandbyTime()).doubleValue()), wcf_center));    
	   			ws.addCell(new jxl.write.Label(3, rowIdx, df.format(Double.valueOf(
	   					detailItem.getTotalErrorTime()).doubleValue()), wcf_center));    
	   			ws.addCell(new jxl.write.Label(4, rowIdx, df.format(Double.valueOf(
	   					detailItem.getDeviceOEE()).doubleValue()), wcf_center)); 
		    }
		    
            wwb.write();    
            wwb.close();
            output.close();
          } catch (Exception e) {   
             e.printStackTrace();   
          } 
		return "success";
	}
	
	public String queryDevice() {
		deviceItemList = deviceService.getDevice(lineId);
		return "success";
	}
	
	public String queryDeviceErrorStat() {
		deviceWorkTimeItem = deviceStatusStatService.getDeviceErrorStat(lineId, deviceType, deviceId, timeBegin, timeEnd);;
		return "success";
	}
	
	public String queryDeviceTopTenError() {
		deviceWorkTimeList = deviceStatusStatService.getDeviceTopTenError(lineId, deviceType, deviceId, timeBegin, timeEnd);
		List<Device> deviceList = deviceService.getDevice("");
   		if(!CollectionUtils.isEmpty(deviceList)){
   			for(Device device : deviceList){
   				if(device.getDeviceId().equals(deviceId)){
   					deviceName = device.getDeviceName();
   					break;
   				}
   			}
   		}
		return "success";
	}
	
	public String queryDeviceTopTenErrorExport() {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");
	    String fileName = "设备故障top10.xls";
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
	   		jxl.write.WritableSheet ws = wwb.createSheet("设备故障top10", 0);
	   		ws.setColumnView(0, 25);
	   		ws.setColumnView(1, 25);
	   		ws.setColumnView(2, 25);
	   		
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
   			ws.addCell(new jxl.write.Label(0, rowIdx, "设备名称", wcf_header));    
   			ws.addCell(new jxl.write.Label(1, rowIdx, "错误代码", wcf_header));    
   			ws.addCell(new jxl.write.Label(2, rowIdx, "错误次数", wcf_header));    
   			
   			java.text.DecimalFormat   df=new java.text.DecimalFormat("#");
   			//明细数据
   			List<DeviceWorkTime> detailList = deviceStatusStatService.getDeviceTopTenError(lineId, deviceType, deviceId, timeBegin, timeEnd);
		    for(int i = 0 ; i < detailList.size() ; i ++) { 
		    	DeviceWorkTime detailItem =  detailList.get(i);
		    	rowIdx++;
	   			ws.addCell(new jxl.write.Label(0, rowIdx, detailItem.getDeviceName(), wcf_center));    
	   			ws.addCell(new jxl.write.Label(1, rowIdx, detailItem.getErrorCode(), wcf_center));    
	   			ws.addCell(new jxl.write.Label(2, rowIdx, String.valueOf(detailItem.getErrorCount()), wcf_center));    
		    }
		    
            wwb.write();    
            wwb.close();
            output.close();
          } catch (Exception e) {   
             e.printStackTrace();   
          } 
		return "success";
		
	}
	
	public String queryDeviceTypeErrorStat() {
		deviceWorkTimeList = deviceStatusStatService.getDeviceTypeErrorStat(lineId, deviceType, timeBegin, timeEnd);
		return "success";
	}
	
	public String queryDeviceTypeErrorStatExport() {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");
	    String fileName = "设备状态对比.xls";
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
   			ws.addCell(new jxl.write.Label(0, rowIdx, "设备名称", wcf_header));    
   			ws.addCell(new jxl.write.Label(1, rowIdx, "故障总时间(h)", wcf_header));    
   			ws.addCell(new jxl.write.Label(2, rowIdx, "故障率", wcf_header));    
   			ws.addCell(new jxl.write.Label(3, rowIdx, "MTTR(h)", wcf_header));    
   			ws.addCell(new jxl.write.Label(4, rowIdx, "MTBF(h)", wcf_header));    
   			
   			java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.##");
   			//明细数据
   			List<DeviceWorkTime> detailList = deviceStatusStatService.getDeviceTypeErrorStat(lineId, deviceType, timeBegin, timeEnd);
		    for(int i = 0 ; i < detailList.size() ; i ++) { 
		    	DeviceWorkTime detailItem =  detailList.get(i);
		    	rowIdx++;
	   			ws.addCell(new jxl.write.Label(0, rowIdx, detailItem.getDeviceName(), wcf_center));    
	   			ws.addCell(new jxl.write.Label(1, rowIdx, df.format(Double.valueOf(
	   					detailItem.getTotalErrorTime()).doubleValue()) , wcf_center));    
	   			ws.addCell(new jxl.write.Label(2, rowIdx, df.format(Double.valueOf(
	   					detailItem.getErrorRate()).doubleValue()), wcf_center));    
	   			ws.addCell(new jxl.write.Label(3, rowIdx, df.format(Double.valueOf(
	   					detailItem.getMttr()).doubleValue()), wcf_center));    
	   			ws.addCell(new jxl.write.Label(4, rowIdx, df.format(Double.valueOf(
	   					detailItem.getMtbf()).doubleValue()), wcf_center)); 
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
