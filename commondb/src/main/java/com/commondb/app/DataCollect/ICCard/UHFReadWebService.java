package com.commondb.app.DataCollect.ICCard;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;

import com.commondb.app.DataCollect.ICCard.service.ICCardService;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
@BindingType("http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class UHFReadWebService {
	private ICCardService iCCardService;
	
	public UHFReadWebService(ICCardService iCCardService) {
		this.iCCardService = iCCardService;
	}

	public String readCard(String cardID) {
		return iCCardService.readCard(cardID);
	}
}
