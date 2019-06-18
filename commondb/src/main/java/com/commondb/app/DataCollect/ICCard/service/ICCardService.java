package com.commondb.app.DataCollect.ICCard.service;

public interface ICCardService {
	
	public abstract String readCard(String cardID);
	
	public abstract String getCurrentCardRecID();

	public abstract String finishReadCard(String cardID);

}
