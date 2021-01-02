package com.changan.app.rotatedegassing.datacollect;

import java.io.IOException;  

import javax.servlet.ServletContext;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.changan.app.rotatedegassing.service.TempDataService;

public class RDTempServlet extends HttpServlet{  
  
    private static final long serialVersionUID = 1L;  
    private RDTempThread myThread;  
      
    public RDTempServlet(){  
    }  
  
    public void init(){  
        String str = null;  
        if (str == null && myThread == null) {
        	ServletContext application;     
            WebApplicationContext wac;     
            application = getServletContext();     
            wac = WebApplicationContextUtils.getWebApplicationContext(application);//获取spring的context     
            TempDataService tempDataService = (TempDataService) wac.getBean("tempDataService");     
        	TempComConfig.ReadFile();
            myThread = new RDTempThread(tempDataService);  
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
