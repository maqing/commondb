package com.commondb.app.DataCollect.tools;

import java.util.Hashtable;

public class ReadCardCache {
	private final static String CardNoColumnName = "d_17" ;
	public static  Hashtable<String, String> curRFIDRecMap = new Hashtable<String, String>();
	
	public static String getCurReadRecID() {
		return curRFIDRecMap.get("id");
	}
	
	public static String getCurReadRecNo() {
		return curRFIDRecMap.get(CardNoColumnName);
	}
}
