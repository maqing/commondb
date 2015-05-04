package com.commondb.common;

import com.commondb.apply.service.ApplyService;
import javax.servlet.ServletContext;
import org.apache.velocity.VelocityContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MyVelocityContext
  extends VelocityContext
  implements ServletContextAware
{
  public static final String APPLYSERVICE = "applyService";
  private ServletContext context;
  private ApplyService applyService;
  
  public Object internalGet(String arg0)
  {
    if ("applyService".equals(arg0)) {
      return this.applyService;
    }
    return super.internalGet(arg0);
  }
  
  public boolean containsKey(Object key)
  {
    return ("applyService".equals(key)) || (super.containsKey(key));
  }
  
  public void setServletContext(ServletContext arg0)
  {
    this.context = arg0;
    ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.context);
    this.applyService = ((ApplyService)ac.getBean("applyService"));
  }
}
