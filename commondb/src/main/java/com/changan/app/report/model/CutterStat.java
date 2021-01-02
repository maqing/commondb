package com.changan.app.report.model;

public class CutterStat {
	private String cutterId;
	private String cutterCode;
	private String cutterName;
	private int changeCounter;
	private int averageProcessNum;
	private int ownProcessNum;
	
	public String getCutterId() {
		return cutterId;
	}
	public void setCutterId(String cutterId) {
		this.cutterId = cutterId;
	}
	public String getCutterCode() {
		return cutterCode;
	}
	public void setCutterCode(String cutterCode) {
		this.cutterCode = cutterCode;
	}
	public String getCutterName() {
		return cutterName;
	}
	public void setCutterName(String cutterName) {
		this.cutterName = cutterName;
	}
	public int getChangeCounter() {
		return changeCounter;
	}
	public void setChangeCounter(int changeCounter) {
		this.changeCounter = changeCounter;
	}
	public int getAverageProcessNum() {
		return averageProcessNum;
	}
	public void setAverageProcessNum(int averageProcessNum) {
		this.averageProcessNum = averageProcessNum;
	}
	public int getOwnProcessNum() {
		return ownProcessNum;
	}
	public void setOwnProcessNum(int ownProcessNum) {
		this.ownProcessNum = ownProcessNum;
	}
	

}
