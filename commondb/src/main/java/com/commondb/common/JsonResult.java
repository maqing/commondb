package com.commondb.common;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class JsonResult
{
  public boolean success;
  public Object data;
  public Map errors = new HashMap();
  public String errormsg;
  
  public String getErrormsg()
  {
    return this.errormsg;
  }
  
  public void setErrormsg(String errormsg)
  {
    this.errormsg = errormsg;
  }
  
  public boolean isSuccess()
  {
    return this.success;
  }
  
  public void setSuccess(boolean success)
  {
    this.success = success;
  }
  
  public Object getData()
  {
    return this.data;
  }
  
  public void setData(Object data)
  {
    this.data = data;
  }
  
  public Map getErrors()
  {
    return this.errors;
  }
  
  public void setErrors(Map errors)
  {
    this.errors = errors;
  }
  
  public String toString()
  {
    JSONObject js = new JSONObject(this);
    return js.toString();
  }
}
