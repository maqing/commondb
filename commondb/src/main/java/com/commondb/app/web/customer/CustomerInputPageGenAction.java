package com.commondb.app.web.customer;

import com.commondb.app.web.InputPageGenAction;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.interceptor.ServletRequestAware;

public class CustomerInputPageGenAction
  extends InputPageGenAction
  implements Preparable, ServletRequestAware
{
  public String preRemind()
  {
    return "success";
  }
  
  public String preInitEntity()
  {
    return super.genForm();
  }
  
  public String genForm()
  {
    return super.genForm();
  }
}
