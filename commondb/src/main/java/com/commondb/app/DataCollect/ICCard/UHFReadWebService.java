package com.commondb.app.DataCollect.ICCard;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;

import com.changan.app.rotatedegassing.service.RDPLCDataService;
import com.commondb.app.DataCollect.ICCard.service.ICCardService;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
@BindingType("http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class UHFReadWebService {
	private ICCardService iCCardService;
	private RDPLCDataService rDPLCDataService;
	
	public UHFReadWebService(ICCardService iCCardService, RDPLCDataService rDPLCDataService) {
		this.iCCardService = iCCardService;
		this.rDPLCDataService = rDPLCDataService;
	}

	public String readCard(String cardID) {
		return iCCardService.readCard(cardID);
	}

	public String finishReadCard(String cardID) {
		return iCCardService.finishReadCard(cardID);
	}
	
	public String readRPPLCData(String furnaceNumber, String itemNumber, String deviceNumber,
			String programNumber, String treatmentTime1, String treatmentTime2, 
			String minOfTreatment, String secOfTreatment, String temperature,
			String status, String stateMessage, String treatmentRotorspeed, 
			String rotorspeed1, String rotorspeed2, String saltAddition,
			String pressureDevice, String volumeOfFlow, String healthy1,
			String healthy2, String healthy3, String healthy4,
			String dateTimeIn, String dateTimeout, String seconds, 
			String date, String timeOfDay) {
		try {
			return rDPLCDataService.savePLCData(furnaceNumber,  itemNumber,  deviceNumber,
					 programNumber,  treatmentTime1,  treatmentTime2, 
					 minOfTreatment,  secOfTreatment,  temperature,
					 status,  stateMessage,  treatmentRotorspeed, 
					 rotorspeed1,  rotorspeed2,  saltAddition,
					 pressureDevice,  volumeOfFlow,  healthy1,
					 healthy2,  healthy3,  healthy4,
					 dateTimeIn,  dateTimeout,  seconds, 
					 date,  timeOfDay);
		} catch (Exception e) {
			return "0";	
		}
	}
	
	public String readRPPLCTime(String dateTimeIn, String dateTimeout,String pressureDevice) {
		try {
			return rDPLCDataService.savePLCTime(dateTimeIn, dateTimeout, pressureDevice);
		} catch (Exception e) {
			return "0";	
		}
	}
	
	
	public String readRPPLCTimeAndLAmount(String dateTimeIn, String dateTimeout,String pressureDevice, 
			String pLCRiqi, String lAmount, String position) {
		try {
			return rDPLCDataService.savePLCTimeAndLAmount(dateTimeIn, dateTimeout, pressureDevice, pLCRiqi, lAmount, position);
		} catch (Exception e) {
			return "0";	
		}
	}	
}
