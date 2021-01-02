package com.changan.app.report.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.httpclient.util.DateUtil;

import com.changan.app.datamodel.EntityDefine;
import com.changan.app.report.model.ManufactureInfo;
import com.commondb.db.service.EntityService;

public class ManufactureServiceImpl implements ManufactureService{
	
	private EntityService entityService;  
	
	public EntityService getEntityService() {
		return entityService;
	}

	public void setEntityService(EntityService entityService) {
		this.entityService = entityService;
	}

	@Override
	public List<ManufactureInfo> queryManufactureInfo(String workpieceCode) {
		// TODO Auto-generated method stub
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(2016, 6, 20);
		ArrayList<ManufactureInfo> manufactureInfoList = new ArrayList<ManufactureInfo>();
		int loopCount = 3;
		if ((workpieceCode!=null) && (workpieceCode.length()>0)) {
			loopCount = 1;
		}
		
		for (int i=0; i<loopCount; i++) {
			ManufactureInfo manufactureInfoItem = new ManufactureInfo();
			if ((workpieceCode!=null) && (workpieceCode.length()>0)) {
				manufactureInfoItem.setWorkpieceCode(workpieceCode);
			}
			else {
				manufactureInfoItem.setWorkpieceCode(getRandomString(10));
			}
			manufactureInfoItem.setProcessDate(DateUtil.formatDate(calendar.getTime(), "yyyy-MM-dd") );
			manufactureInfoItem.setWorkpieceStatus("完成");
			
			manufactureInfoItem.setShockSandDeviceCode("107");
			calendar.add(Calendar.MINUTE, 2);
			manufactureInfoItem.setShockSandFinishTime(DateUtil.formatDate(calendar.getTime(), "hh:mm:ss"));
			
			manufactureInfoItem.setMachineDeviceCode("101");
			calendar.add(Calendar.MINUTE, 2);
			manufactureInfoItem.setMachineFinishTime(DateUtil.formatDate(calendar.getTime(), "hh:mm:ss"));
			
			manufactureInfoItem.setBurrCleaningCode("105");
			calendar.add(Calendar.MINUTE, 2);
			manufactureInfoItem.setBurrCleaningFinishTime(DateUtil.formatDate(calendar.getTime(), "hh:mm:ss"));
			
			manufactureInfoItem.setIsArtificialOnline("否");
			manufactureInfoList.add(manufactureInfoItem);
		}
		return manufactureInfoList;
	}

	
	public String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";//含有字符和数字的字符串
        Random random = new Random();//随机类初始化
        StringBuffer sb = new StringBuffer();//StringBuffer类生成，为了拼接字符串
 
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);// [0,62)
 
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


	@Override
	public List queryManufactureInfo(String workpieceCode,
			String timeBegin, String timeEnd, int from, int length) {
		String columnString = " a.workpiecename as wpname,'完成' as wpstate,date_format(a.lastplctime,'%Y-%m-%d') as wpdate, "  
				+ " a.devicename as shockname, if(a.processcode=4,date_format(a.lastplctime,'%T'),'') as shocktime,  "
				+ " ifnull(b.devicename,'') as machinename,if(b.processcode=0,date_format(b.lastplctime,'%T'),'') as machinetime, "
				+ " ifnull(c.devicename,'') as cgcname,if(c.processcode=6,date_format(c.lastplctime,'%T'),'') as cgctime,'否' as isRemake ";
		
		
		String fromStr = " shockdeviceWPTimeView a "
				+ " left join machinedeviceWPTimeView b on (a.workpiecename=b.workpiecename) "
				+ " left join  cgcdeviceWPTimeView c on (a.workpiecename=c.workpiecename) ";
		
		String whereInitStr = " 1=1 " ;
		if ((timeBegin!=null) && (timeBegin.length()>0)) {
			whereInitStr = whereInitStr + " and a.lastplctime>='" + timeBegin + "' ";
		}
		if ((timeEnd!=null) && (timeEnd.length()>0)) {
			whereInitStr = whereInitStr + " and a.lastplctime<='" + timeEnd + "' ";
		}
		if ((workpieceCode!=null) && (workpieceCode.length()>0)) {
			whereInitStr = whereInitStr + " and a.workpiecename like '%" + workpieceCode + "%' ";
		}
		if ((from>=0) && (length>0)) {
			whereInitStr = whereInitStr + " limit " + length + " offset " + from;
		}
		
		return this.entityService.dynSelect(columnString, fromStr, whereInitStr);
	}


	@Override
	public int queryManufactureInfoListSize(String workpieceCode,
			String timeBegin, String timeEnd) {
		String columnString = " count(*) as recCount ";
		
		String fromStr = " shockdeviceWPTimeView a "
				+ " left join machinedeviceWPTimeView b on (a.workpiecename=b.workpiecename) "
				+ " left join  cgcdeviceWPTimeView c on (a.workpiecename=c.workpiecename) ";
		
		String whereInitStr = " 1=1 " ;
		if ((timeBegin!=null) && (timeBegin.length()>0)) {
			whereInitStr = whereInitStr + " and a.lastplctime>='" + timeBegin + "' ";
		}
		if ((timeEnd!=null) && (timeEnd.length()>0)) {
			whereInitStr = whereInitStr + " and a.lastplctime<='" + timeEnd + "' ";
		}
		if ((workpieceCode!=null) && (workpieceCode.length()>0)) {
			whereInitStr = whereInitStr + " and a.workpiecename like '%" + workpieceCode + "%' ";
		}
		
		List recCountList = this.entityService.dynSelect(columnString, fromStr, whereInitStr);
		return Integer.parseInt(((Map)recCountList.get(0)).get("recCount").toString());
	}
    	
}
