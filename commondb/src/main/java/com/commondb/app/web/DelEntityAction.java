package com.commondb.app.web;

import com.commondb.app.common.FileTool;
import com.commondb.common.ImageTool;
import com.commondb.db.bo.User;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

public class DelEntityAction
  extends ActionSupport
  implements Preparable, ServletRequestAware
{
  private String appName;
  private String metaName;
  private int metaId;
  private String info;
  private MetaService metaService;
  private EntityService entityService;
  private User user;
  HttpServletRequest request;
  private List<File> myFile;
  private List<String> myFileFileName;
  
  public String saveEntity()
  {
    ActionContext context = ActionContext.getContext();
    Map request = (Map)context.get("request");
    
    List srcFiles = getMyFile();
    List filesArrayList = new ArrayList();
    if (srcFiles != null) {
      for (int i = 0; i < srcFiles.size(); i++)
      {
        String dstPath = ServletActionContext.getServletContext()
          .getRealPath("upload") + 
          "\\" + (String)getMyFileFileName().get(i);
        File dstFile = new File(dstPath);
        FileTool.copy((File)srcFiles.get(i), dstFile);
        ImageTool.createPreviewImge(dstFile);
        filesArrayList.add(dstFile);
      }
    }
    Map m = ActionContext.getContext().getParameters();
    

    HttpServletRequest req = ServletActionContext.getRequest();
    
    Map reqMap = req.getParameterMap();
    
    Map valuesMap = new HashMap();
    int fileIndex = 0;
    try
    {
      ArrayList<Integer[]> charaArrI = new ArrayList();
      ArrayList<Integer[]> hierarchyArrI = new ArrayList();
      ArrayList<Integer[]> entityArrI = new ArrayList();
      
      Set<String> keys = reqMap.keySet();
      for (String key : keys) {
        if (key.startsWith("p_"))
        {
          if ((((String[])reqMap.get(key))[0] != null) && (!((String[])reqMap.get(key))[0].equals("")))
          {
            File f = (File)filesArrayList.get(fileIndex++);
            
            valuesMap.put(key, "upload/" + f.getName());
          }
        }
        else if (key.startsWith("c_"))
        {
          String value = ((String[])reqMap.get(key))[0];
          if ((value != null) && (!value.equals("")))
          {
            String[] s = value.split(",");
            Integer cid = Integer.valueOf(Integer.parseInt(key.replaceFirst("c_", "")));
            for (int i = 0; i < s.length; i++)
            {
              Integer[] pair = new Integer[2];
              pair[0] = cid;
              pair[1] = Integer.valueOf(Integer.parseInt(s[i]));
              charaArrI.add(pair);
            }
          }
        }
        else if (key.startsWith("h_"))
        {
          String value = ((String[])reqMap.get(key))[0];
          if ((value != null) && (!value.equals("")))
          {
            String[] s = value.split(",");
            Integer cid = Integer.valueOf(Integer.parseInt(key.replaceFirst("h_", "")));
            for (int i = 0; i < s.length; i++)
            {
              Integer[] pair = new Integer[2];
              pair[0] = cid;
              pair[1] = Integer.valueOf(Integer.parseInt(s[i]));
              hierarchyArrI.add(pair);
            }
          }
        }
        else if (key.startsWith("r_"))
        {
          String value = ((String[])reqMap.get(key))[0];
          if ((value != null) && (!value.equals("")))
          {
            String[] s = value.split(",");
            Integer cid = Integer.valueOf(Integer.parseInt(key.replaceFirst("r_", "")));
            for (int i = 0; i < s.length; i++)
            {
              Integer[] pair = new Integer[2];
              pair[0] = cid;
              pair[1] = Integer.valueOf(Integer.parseInt(s[i]));
              entityArrI.add(pair);
            }
          }
        }
        else if (key.startsWith("d_"))
        {
          valuesMap.put(key, ((String[])reqMap.get(key))[0]);
        }
      }
      if (charaArrI.size() > 0) {
        valuesMap.put("charaArr", charaArrI.toArray());
      }
      if (hierarchyArrI.size() > 0) {
        valuesMap.put("hierarchyArr", hierarchyArrI.toArray());
      }
      if (entityArrI.size() > 0) {
        valuesMap.put("entityArr", entityArrI.toArray());
      }
      valuesMap.remove("metaId");
      valuesMap.remove("id");
      

      this.entityService.createEntity(Integer.valueOf(this.metaId), valuesMap);
    }
    catch (Throwable t)
    {
      t.printStackTrace();
    }
    return "success";
  }
  
  public String getAppName()
  {
    return this.appName;
  }
  
  public void setAppName(String appName)
  {
    this.appName = appName;
  }
  
  public String getMetaName()
  {
    return this.metaName;
  }
  
  public void setMetaName(String metaName)
  {
    this.metaName = metaName;
  }
  
  public List<File> getMyFile()
  {
    return this.myFile;
  }
  
  public void setMyFile(List<File> myFile)
  {
    this.myFile = myFile;
  }
  
  public List<String> getMyFileFileName()
  {
    return this.myFileFileName;
  }
  
  public void setMyFileFileName(List<String> myFileFileName)
  {
    this.myFileFileName = myFileFileName;
  }
  
  public void prepare()
    throws Exception
  {}
  
  public EntityService getEntityService()
  {
    return this.entityService;
  }
  
  public void setEntityService(EntityService entityService)
  {
    this.entityService = entityService;
  }
  
  public MetaService getMetaService()
  {
    return this.metaService;
  }
  
  public void setMetaService(MetaService metaService)
  {
    this.metaService = metaService;
  }
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public void setServletRequest(HttpServletRequest request)
  {
    this.request = request;
  }
  
  public Integer getMetaId()
  {
    return Integer.valueOf(this.metaId);
  }
  
  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId.intValue();
  }
  
  public String getInfo()
  {
    return this.info;
  }
  
  public void setInfo(String info)
  {
    this.info = info;
  }
  
  public void setMetaId(int metaId)
  {
    this.metaId = metaId;
  }
}
