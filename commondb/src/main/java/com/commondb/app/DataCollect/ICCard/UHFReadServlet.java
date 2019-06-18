package com.commondb.app.DataCollect.ICCard;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Endpoint;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.changan.app.rotatedegassing.service.RDPLCDataService;
import com.commondb.app.DataCollect.ICCard.service.ICCardService;

/**
 * Servlet implementation class UHF
 */
public class UHFReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UHFReadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	 public void init() throws ServletException {
		System.out.println("准备启动WebService服务");
		//发布一个WebService
		ServletContext application;     
		WebApplicationContext wac;     
		application = getServletContext();     
		wac = WebApplicationContextUtils.getWebApplicationContext(application);//获取spring的context     
		ICCardService iCCardService = (ICCardService) wac.getBean("iCCardService");
		RDPLCDataService rDPLCDataService = (RDPLCDataService) wac.getBean("rDPLCDataService");
		//读取最后一条读卡记录到内存
		iCCardService.getCurrentCardRecID();
		/*
		InetAddress address = null;
		try { 
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) { 
			e.printStackTrace();
		} 
		String hostAddress = address.getHostAddress();
		Endpoint.publish("http://"+hostAddress+":8089/commondb/UHFReadWebService", new UHFReadWebService(iCCardService));
		*/
//		Endpoint.publish("http://10.0.41.199:8089/commondb/UHFReadWebService", new UHFReadWebService(iCCardService));
		Endpoint.publish("http://localhost:8089/commondb/UHFReadWebService", new UHFReadWebService(iCCardService,rDPLCDataService));
		System.out.println("已成功启动WebService服务");
 }
}
