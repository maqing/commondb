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
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

import org.apache.struts2.ServletActionContext;

import com.changan.app.datamodel.EntityDefine;
import com.changan.plcinterface.dao.TransferdeviceDAO;
import com.changan.plcinterface.model.Transferdevice;
import com.changan.plcinterface.model.TransferdeviceExample;
import com.commondb.app.web.JqGridBaseAction;
import com.commondb.common.DateUtil;
import com.commondb.db.service.EntityService;

public class TransferPLCDataReportAction extends JqGridBaseAction {  
    
	private EntityService entityService;  
	private String entityName;
	private String entityId;
	private String timePLCDataReportBegin;
	private String timePLCDataReportEnd;
	private String deviceMetaId;
	private String deviceId;
	
	private TransferdeviceDAO localTransferdeviceDAO;
  
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
	
	public String getTimePLCDataReportBegin() {
		return timePLCDataReportBegin;
	}
	
	public void setTimePLCDataReportBegin(String timePLCDataReportBegin) {
		this.timePLCDataReportBegin = timePLCDataReportBegin;
	}
	
	public String getTimePLCDataReportEnd() {
		return timePLCDataReportEnd;
	}
	
	public void setTimePLCDataReportEnd(String timePLCDataReportEnd) {
		this.timePLCDataReportEnd = timePLCDataReportEnd;
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
	
	/**
	 * @return the localCgcdeviceDAO
	 */
	public TransferdeviceDAO getLocalTransferdeviceDAO() {
		return localTransferdeviceDAO;
	}

	/**
	 * @param localCgcdeviceDAO the localCgcdeviceDAO to set
	 */
	public void setLocalTransferdeviceDAO(TransferdeviceDAO localTransferdeviceDAO) {
		this.localTransferdeviceDAO = localTransferdeviceDAO;
	}

	@Override  
	public void initQueryStr() {
	}
	
	/**
	 * @return the deviceCode
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceCode the deviceCode to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override  
	public int getResultSize() {
		TransferdeviceExample transferDeviceExample = new TransferdeviceExample();
		com.changan.plcinterface.model.TransferdeviceExample.Criteria transferCriteria= transferDeviceExample.createCriteria();
		if ((timePLCDataReportBegin!=null)&& (timePLCDataReportBegin.length()>0)) {
			if ((timePLCDataReportEnd!=null)&& (timePLCDataReportEnd.length()>0)) {
				transferCriteria.andPlctimeBetween(DateUtil.parseDate(timePLCDataReportBegin, "yyyy-MM-dd HH:mm:ss"), 
						DateUtil.parseDate(timePLCDataReportEnd, "yyyy-MM-dd HH:mm:ss"));
			} else {
				transferCriteria.andPlctimeGreaterThanOrEqualTo(DateUtil.parseDate(timePLCDataReportBegin, "yyyy-MM-dd HH:mm:ss"));
			}
		} else {
			if ((timePLCDataReportEnd!=null)&& (timePLCDataReportEnd.length()>0)) {
				transferCriteria.andPlctimeLessThanOrEqualTo(DateUtil.parseDate(timePLCDataReportEnd, "yyyy-MM-dd HH:mm:ss"));
			}
		}
		if ((deviceId!=null)&& (deviceId.length()>0)) {
			transferCriteria.andDeviceidEqualTo(Integer.parseInt(deviceId));
		}
		return localTransferdeviceDAO.countByExample(transferDeviceExample);
	}  

	@Override  
	public List listResults(int from, int length) {  
		List results = Collections.emptyList();  
		TransferdeviceExample transferDeviceExample = new TransferdeviceExample();
		com.changan.plcinterface.model.TransferdeviceExample.Criteria transferCriteria= transferDeviceExample.createCriteria();
		if ((timePLCDataReportBegin!=null)&& (timePLCDataReportBegin.length()>0)) {
			if ((timePLCDataReportEnd!=null)&& (timePLCDataReportEnd.length()>0)) {
				transferCriteria.andPlctimeBetween(DateUtil.parseDate(timePLCDataReportBegin, "yyyy-MM-dd HH:mm:ss"), 
						DateUtil.parseDate(timePLCDataReportEnd, "yyyy-MM-dd HH:mm:ss"));
			} else {
				transferCriteria.andPlctimeGreaterThanOrEqualTo(DateUtil.parseDate(timePLCDataReportBegin, "yyyy-MM-dd HH:mm:ss"));
			}
		} else {
			if ((timePLCDataReportEnd!=null)&& (timePLCDataReportEnd.length()>0)) {
				transferCriteria.andPlctimeLessThanOrEqualTo(DateUtil.parseDate(timePLCDataReportEnd, "yyyy-MM-dd HH:mm:ss"));
			}
		}
		if ((deviceId!=null)&& (deviceId.length()>0)) {
			transferCriteria.andDeviceidEqualTo(Integer.parseInt(deviceId));
		}
		transferDeviceExample.setFrom(from);
		transferDeviceExample.setLength(length);
		transferDeviceExample.setOrderByClause(" plctime desc ");
		results = localTransferdeviceDAO.selectByExamplePage(transferDeviceExample);
		return results;
	} 
	
	
    
	@Override  
	public String execute() {
		setDeviceMetaId(String.valueOf(EntityDefine.TransferDeviceMetaId));
		
		List deviceIdResults = Collections.emptyList(); 
		deviceIdResults = this.entityService.dynSelect( EntityDefine.TransferDevice_ID_CN , 
				"t_entity_" + EntityDefine.TransferDeviceMetaId, "id='" + entityId + "'");
		if (deviceIdResults.size()>0) {
			Map deviceIdMap = (Map) deviceIdResults.get(0);
			deviceId = (String) deviceIdMap.get(EntityDefine.TransferDevice_ID_CN);
		}
		
	    return refreshGridModel();  
	}
	
	
	public String ExportToExcel() {
		return ExportToExcelFile(String.valueOf(EntityDefine.TransferDeviceMetaId),
				EntityDefine.TransferDevice_ID_CN, EntityDefine.TransferDevice_Name_CN );
	}
	
	
	public String ExportToExcelFile(String metaId, String device_ID_CN, String device_Name_CN) {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");
	    String fileName = "中央物流采集PLC数据表.xls";
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
	   		jxl.write.WritableSheet ws = wwb.createSheet("PLC采集数据表", 0);
	   		ws.setColumnView(0, 25);
	   		ws.setColumnView(1, 25);
	   		ws.setColumnView(2, 25);
	   		ws.setColumnView(3, 25);
	   		ws.setColumnView(4, 25);
	   		ws.setColumnView(5, 25);
	   		ws.setColumnView(6, 25);
	   		
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
				if ((timePLCDataReportBegin!=null)&&(timePLCDataReportBegin.length()==19)) 
					dateQueryConditionStr += "从" + timePLCDataReportBegin;
				if ((timePLCDataReportEnd!=null)&&(timePLCDataReportEnd.length()==19)) 
					dateQueryConditionStr += "至" + timePLCDataReportEnd;
				if ((dateQueryConditionStr!=null) && (dateQueryConditionStr.length()>0)) {
					rowIdx++;
					ws.addCell(new jxl.write.Label(0, rowIdx, dateQueryConditionStr, wcf_header));
					ws.mergeCells(0, rowIdx, 1, rowIdx);
				}
			}
	   		
			//标题
			rowIdx++;
   			ws.addCell(new jxl.write.Label(0, rowIdx, "设备编码", wcf_header));    
   			ws.addCell(new jxl.write.Label(1, rowIdx, "设备名称", wcf_header));    
   			ws.addCell(new jxl.write.Label(2, rowIdx, "PLC时间", wcf_header));    
   			ws.addCell(new jxl.write.Label(3, rowIdx, "状态", wcf_header));    
   			ws.addCell(new jxl.write.Label(4, rowIdx, "故障代码", wcf_header));    
   			ws.addCell(new jxl.write.Label(5, rowIdx, "工序", wcf_header));    
   			ws.addCell(new jxl.write.Label(6, rowIdx, "加工工件编码", wcf_header));    
   			
   			//明细数据
   			List detailList = Collections.emptyList();  
   			TransferdeviceExample transferDeviceExample = new TransferdeviceExample();
   			com.changan.plcinterface.model.TransferdeviceExample.Criteria transferCriteria= transferDeviceExample.createCriteria();
   			if ((timePLCDataReportBegin!=null)&& (timePLCDataReportBegin.length()>0)) {
   				if ((timePLCDataReportEnd!=null)&& (timePLCDataReportEnd.length()>0)) {
   					transferCriteria.andPlctimeBetween(DateUtil.parseDate(timePLCDataReportBegin, "yyyy-MM-dd HH:mm:ss"), 
   							DateUtil.parseDate(timePLCDataReportEnd, "yyyy-MM-dd HH:mm:ss"));
   				} else {
   					transferCriteria.andPlctimeGreaterThanOrEqualTo(DateUtil.parseDate(timePLCDataReportBegin, "yyyy-MM-dd HH:mm:ss"));
   				}
   			} else {
   				if ((timePLCDataReportEnd!=null)&& (timePLCDataReportEnd.length()>0)) {
   					transferCriteria.andPlctimeLessThanOrEqualTo(DateUtil.parseDate(timePLCDataReportEnd, "yyyy-MM-dd HH:mm:ss"));
   				}
   			}
   			if ((deviceId!=null)&& (deviceId.length()>0)) {
   				transferCriteria.andDeviceidEqualTo(Integer.parseInt(deviceId));
   			}
   			transferDeviceExample.setOrderByClause(" plctime desc ");
   			detailList = localTransferdeviceDAO.selectByExample(transferDeviceExample);
   			
		    for(int i = 0 ; i < detailList.size() ; i ++) { 
		    	Transferdevice detailItem = (Transferdevice) detailList.get(i);
		    	rowIdx++;
		    	if (rowIdx>65530) {
		    		ws.addCell(new jxl.write.Label(0, rowIdx, "数据过多，后续不再导出", wcf_center));
		    		break;
		    	}
	   			ws.addCell(new jxl.write.Label(0, rowIdx, detailItem.getDeviceid().toString(), wcf_center));    
	   			ws.addCell(new jxl.write.Label(1, rowIdx, detailItem.getDevicename(), wcf_center));    
	   			ws.addCell(new jxl.write.Label(2, rowIdx, DateUtil.formatDate(detailItem.getPlctime(),"yyyy-MM-dd HH:mm:ss"), wcf_center));    
	   			ws.addCell(new jxl.write.Label(3, rowIdx, detailItem.getDevicestatusName(), wcf_center));    
	   			ws.addCell(new jxl.write.Label(4, rowIdx, detailItem.getFaultcode().toString(), wcf_center));    
	   			ws.addCell(new jxl.write.Label(5, rowIdx, detailItem.getProcessName(), wcf_center));    
	   			ws.addCell(new jxl.write.Label(6, rowIdx, detailItem.getWorkpiecename(), wcf_center)); 
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
