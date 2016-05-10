package com.commondb.app.DataCollect.AirPurify;

import java.io.IOException;  

import javax.servlet.ServletContext;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.commondb.app.DataCollect.AirPurify.service.PLCDataService;

public class APServlet extends HttpServlet{  
  
    private static final long serialVersionUID = 1L;  
    private APThread myThread;  
      
    public APServlet(){  
    }  
  
    public void init(){  
        String str = null;  
        if (str == null && myThread == null) {
        	ServletContext application;     
            WebApplicationContext wac;     
            application = getServletContext();     
            wac = WebApplicationContextUtils.getWebApplicationContext(application);//获取spring的context     
            PLCDataService plcDataService = (PLCDataService) wac.getBean("plcDataService");     
        	ComConfig.ReadFile();
            myThread = new APThread(plcDataService);  
            myThread.Connection();
            myThread.start(); // servlet 上下文初始化时启动 socket
        }  
    }  
  
    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)  
        throws ServletException, IOException{  
    }  
  
    public void destory(){  
        if (myThread != null && myThread.isInterrupted()) {  
            myThread.interrupt(); 
        }  
    }  
}  
