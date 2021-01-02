package com.changan.app.report.action;

import java.io.UnsupportedEncodingException;
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
import org.springframework.util.CollectionUtils;

import com.changan.app.report.model.Device;
import com.changan.app.report.service.DeviceService;
import com.changan.app.report.service.MaintenanceService;
import com.commondb.app.web.JqGridBaseAction;
import com.commondb.common.MD5;
import com.commondb.db.bo.User;
import com.commondb.security.service.SecurityUserHolder;
import com.commondb.security.service.UserService;

public class MaintenanceRecordAction extends JqGridBaseAction {
	
	private String lineId;
	private String deviceId;
	private String timeBegin;
	private String timeEnd;
	private String remindId; 
	private String workTime;
	private String note;
	private String workerName;
	private String maintResult;
	private String maintMsg;
	private String userName;
	private String userPwd;
	private String userDesc;
	
	private MaintenanceService maintenanceService;
	
	private UserService userService;
	
	private DeviceService deviceService;
	
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
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
	public String getRemindId() {
		return remindId;
	}
	public void setRemindId(String remindId) {
		this.remindId = remindId;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getMaintResult() {
		return maintResult;
	}
	public void setMaintResult(String maintResult) {
		this.maintResult = maintResult;
	}
	public String getMaintMsg() {
		return maintMsg;
	}
	public void setMaintMsg(String maintMsg) {
		this.maintMsg = maintMsg;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userPwd
	 */
	public String getUserPwd() {
		return userPwd;
	}
	/**
	 * @param userPwd the userPwd to set
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public MaintenanceService getMaintenanceService() {
		return maintenanceService;
	}
	public void setMaintenanceService(MaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}
	
	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	
	
	
	public DeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	public String queryMaintRec() {
	    return refreshGridModel();  
	}
	@Override
	public void initQueryStr() {
		
	}
	@Override
	public int getResultSize() {
		return maintenanceService.queryMaintRecListSize(lineId, deviceId, timeBegin, timeEnd);
	}
	@Override
	public List listResults(int from, int length) {
		return maintenanceService.queryMaintRec(lineId, deviceId, timeBegin, timeEnd,  from, length);
	}
	
	/*新增维护记录*/
	public String addMaintRec() {
	    try {  
	    	String remindIds = remindId;
	    	if(null != remindIds && !remindIds.equals("")){
	    		String[] remindIdArray = remindIds.split(",");
	    		int len = remindIdArray.length;
	    		for(int i = 0 ; i < len ;i++){
	    			maintenanceService.addMaintRec(remindIdArray[i], workTime, note, workerName);
	    		}
	    	}
	    	maintResult = "success";
	    	return SUCCESS;  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	        this.addActionError(e.getMessage());  
	        maintResult = "error";
	        maintMsg = e.getMessage();
	        return ERROR;  
	    }  
	}

	
	public String queryManitExport() {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");
	    String fileName = "设备维护保养信息.xls";
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
	   		jxl.write.WritableSheet ws = wwb.createSheet("设备维护保养信息", 0);
	   		ws.setColumnView(0, 25);
	   		ws.setColumnView(1, 25);
	   		ws.setColumnView(2, 25);
	   		ws.setColumnView(3, 25);
	   		ws.setColumnView(4, 25);
	   		
	   		int rowIdx = 0;
	   		
	   		List<Device> deviceList = deviceService.getDevice("");
	   		if(!CollectionUtils.isEmpty(deviceList)){
	   			for(Device device : deviceList){
	   				if(device.getDeviceId().equals(deviceId)){
	   					ws.addCell(new jxl.write.Label(1, rowIdx, device.getDeviceName(), wcf_header));
	   					break;
	   				}
	   			}
	   		}
	   		
			ws.addCell(new jxl.write.Label(0, rowIdx, "设备名称：", wcf_header));
//			ws.addCell(new jxl.write.Label(1, rowIdx, deviceId, wcf_header));
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
			ws.addCell(new jxl.write.Label(0, rowIdx, "维修人", wcf_header)); 
   			ws.addCell(new jxl.write.Label(1, rowIdx, "维修时间", wcf_header));    
   			ws.addCell(new jxl.write.Label(2, rowIdx, "维修内容", wcf_header));     
   			ws.addCell(new jxl.write.Label(3, rowIdx, "备注", wcf_header));    
   			
   			//明细数据
   			List detailList = maintenanceService.queryMaintRec(lineId, deviceId, timeBegin, timeEnd,  0, maintenanceService.queryMaintRecListSize(lineId, deviceId, timeBegin, timeEnd));
   			for(Object object : detailList){
		    	Map jo = (Map)object;
		    	rowIdx++;
		    	ws.addCell(new jxl.write.Label(0, rowIdx, jo.get("workername") != null ? jo.get("workername").toString() : "", wcf_center));
	   			ws.addCell(new jxl.write.Label(1, rowIdx, jo.get("worktime") != null ? jo.get("worktime").toString() : "" , wcf_center));    
	   			ws.addCell(new jxl.write.Label(2, rowIdx, jo.get("workdesc") != null ? jo.get("workdesc").toString() : "", wcf_center));       
	   			ws.addCell(new jxl.write.Label(3, rowIdx, jo.get("note") != null ? jo.get("note").toString() : "", wcf_center)); 
		    }
            wwb.write();    
            wwb.close();
            output.close();
          } catch (Exception e) {   
             e.printStackTrace();   
          } 
		return "success";
		
	}
	
	
	/*校验录入用户*/
	public String verifyMaintUser() {
	    try {  
	    	List<User> userList = userService.findUsersByName(userName);
	    	if ((userList!=null) &&(userList.size()>0)) {
	    		User maintUser = userList.get(0);
	    		if (!(MD5.getMD5(userPwd.getBytes()).equals(maintUser.getPwd()))) {
	    			//密码错误
			        maintResult = "error";
			        maintMsg = "密码错误";
			        return ERROR;  
	    		}
	    		
	    		if (maintUser.getUsername().equals(SecurityUserHolder.getCurrentUser().getUsername())) {
			        maintResult = "error";
			        maintMsg = "不能与当前登录用户相同";
			        return ERROR;  
	    		}
	    		userDesc = maintUser.getUserDesc();
	    	} else {
		        maintMsg = "无此用户";
		        maintResult = "error";
		        return ERROR;  
	    	}
	    	maintResult = "success";
	    	return SUCCESS;  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	        this.addActionError(e.getMessage());  
	        maintResult = "error";
	        maintMsg = e.getMessage();
	        return ERROR;  
	    }  
	}

}
