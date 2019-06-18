package com.commondb.app.DataCollect.tools;

import java.util.Hashtable;

import com.changan.app.datamodel.EntityDefine;

public class ReadCardCache {
	public static  Hashtable<String, String> curRFIDRecMap = new Hashtable<String, String>();
	
	public static String getCurReadRecID() {
		return curRFIDRecMap.get("id");
	}
	
	public static String getCurReadRecNo() {
		return curRFIDRecMap.get(EntityDefine.RPRFID_RFIDNO_CN);
	}
}
