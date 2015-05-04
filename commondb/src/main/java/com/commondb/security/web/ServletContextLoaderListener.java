package com.commondb.security.web;

import com.commondb.security.service.SecurityManager;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServletContextLoaderListener
  implements ServletContextListener
{
  public void contextInitialized(ServletContextEvent servletContextEvent)
  {
    ServletContext servletContext = servletContextEvent.getServletContext();
    SecurityManager securityManager = getSecurityManager(servletContext);
    
    Map<String, String> urlAuthorities = securityManager.loadUrlAuthorities();
    servletContext.setAttribute("urlAuthorities", urlAuthorities);
  }
  
  public void contextDestroyed(ServletContextEvent servletContextEvent)
  {
    servletContextEvent.getServletContext().removeAttribute("urlAuthorities");
  }
  
  protected SecurityManager getSecurityManager(ServletContext servletContext)
  {
    return (SecurityManager)WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("securityManager");
  }
}
