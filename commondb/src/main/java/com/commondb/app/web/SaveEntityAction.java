package com.commondb.app.web;

import com.commondb.app.common.FileTool;
import com.commondb.common.ImageTool;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.REntity;
import com.commondb.db.bo.User;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.ActionContext;
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

public class SaveEntityAction
  extends BasicAction
  implements Preparable, ServletRequestAware
{
  private String appName;
  private String metaName;
  private int metaId;
  private String info;
  private MetaService metaService;
  private EntityService entityService;
  private User user;
  private Integer update;
  private String entityId;
  HttpServletRequest request;
  private List<File> myFile;
  private List<String> myFileFileName;
  private String attachNameList;
  
  public Map makeValueMap()
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
      ArrayList<String[]> hierarchyArrI = new ArrayList();
      ArrayList<String[]> entityArrI = new ArrayList();
      ArrayList<String> entityLabel = new ArrayList();
      
      int iLabel = 2147483647;
      
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
              String[] pair = new String[2];
              pair[0] = cid.toString();
              pair[1] = s[i];
              hierarchyArrI.add(pair);
            }
          }
        }
        else if (key.equals("relation"))
        {
          String value = ((String[])reqMap.get(key))[0];
          value = value.trim();
          if ((value != null) && (!value.equals("")))
          {
            String[] s = value.split(",");
            for (int i = 0; i < s.length; i++)
            {
              String[] pair = new String[2];
              pair[0] = s[i].split("_")[0];
              pair[1] = s[i].split("_")[1];
              if (s[i].split("_").length > 2) {
                entityLabel.add(s[i].split("_")[2]);
              } else {
                entityLabel.add("");
              }
              entityArrI.add(pair);
            }
          }
        }
        else if (key.startsWith("d_"))
        {
          valuesMap.put(key, ((String[])reqMap.get(key))[0]);
          
          int cLabel = Integer.parseInt(key.substring(2));
          if (cLabel < iLabel)
          {
            iLabel = cLabel;
            this.masterRLabel = ((String[])reqMap.get(key))[0];
          }
        }
      }
      if (charaArrI.size() > 0) {
        valuesMap.put("charaArr", charaArrI.toArray(new Integer[charaArrI.size()][2]));
      }
      if (hierarchyArrI.size() > 0) {
        valuesMap.put("hierarchyArr", hierarchyArrI.toArray(new String[hierarchyArrI.size()][2]));
      }
      if (entityArrI.size() > 0)
      {
        valuesMap.put("entityArr", entityArrI.toArray(new String[entityArrI.size()][2]));
        valuesMap.put("entityLabel", entityLabel.toArray(new String[entityLabel.size()]));
      }
      ArrayList<String> attachName = new ArrayList();
      if ((this.attachNameList != null) && (!this.attachNameList.equals("")))
      {
        String[] s = this.attachNameList.split("\\*");
        for (int i = 0; i < s.length; i++) {
          attachName.add(s[i]);
        }
        valuesMap.put("attachNameList", attachName.toArray(new String[attachName.size()]));
      }
    }
    catch (Throwable t)
    {
      t.printStackTrace();
    }
    return valuesMap;
  }
  
  public String saveEntity()
  {
    Map valuesMap = makeValueMap();
    String resultEntityId = this.entityId;
    try
    {
      if ((this.update != null) && (this.update.intValue() == 1)) {
        this.entityService.updateEntity(Integer.valueOf(this.metaId), this.entityId, valuesMap);
      } else {
        resultEntityId = this.entityService.createEntity(Integer.valueOf(this.metaId), valuesMap);
      }
      this.info = "保存成功";
      if ((this.masterRMetaName != null) && (this.masterRMetaName.length() > 0))
      {
        this.metaName = ((Meta)this.metaService.findMeta(Integer.valueOf(this.metaId)).get(0)).getEntityName();
        this.entityService.saveREntity(Integer.valueOf(this.masterRMetaId), this.masterREntityId, 
          Integer.valueOf(this.metaId), resultEntityId, this.metaName + "：" + this.masterRLabel);
      }
      if ((this.toRMetaName != null) && (this.toRMetaName.length() > 0))
      {
        this.metaName = ((Meta)this.metaService.findMeta(Integer.valueOf(this.toRMetaId)).get(0)).getEntityName();
        this.entityService.saveREntity(Integer.valueOf(this.metaId), resultEntityId, 
          Integer.valueOf(this.toRMetaId), this.toREntityId, this.metaName + "：" + this.toRLabel);
      }
    }
    catch (Throwable t)
    {
      this.info = "保存失败";
      t.printStackTrace();
    }
    return "success";
  }
  
  public String initCustomerEntity(String deptID, String addressID, String label, boolean isSingle)
  {
    String info = "";
    String customerAddress = "";
    String customerZipCode = "";
    String customerMainPage = "";
    String customerPhoneNum = "";
    try
    {
      String customerName = this.metaService.getHierPathString(Integer.valueOf(28), deptID).replace("\\", "");
      


      List customerList = this.entityService.dynSelect(" * ", " t_entity_35 a ", " a.d_142='" + customerName + "' ");
      if (customerList.size() == 0)
      {
        Map customerValuesMap = new HashMap();
        ArrayList<String[]> entityArrI = new ArrayList();
        ArrayList<String> entityLabel = new ArrayList();
        

        List addressBookList = this.entityService.dynSelect(" distinct c.id, c.d_34 as name, c.d_40 as phone,  c.d_43 as zipcode, c.d_44 as mainpage, c.d_151 as address ", 
        
          " r_entity_hierarchy_data b, t_entity_9 c ", 
          " c.id=b.entity_id and b.value_id='" + deptID + "' ");
        for (int j = 0; j < addressBookList.size(); j++)
        {
          if (((Map)addressBookList.get(j)).get("zipcode") != null) {
            customerZipCode = ((Map)addressBookList.get(j)).get("zipcode").toString();
          }
          if (((Map)addressBookList.get(j)).get("mainpage") != null) {
            customerMainPage = ((Map)addressBookList.get(j)).get("mainpage").toString();
          }
          if (((Map)addressBookList.get(j)).get("phone") != null) {
            customerPhoneNum = ((Map)addressBookList.get(j)).get("phone").toString();
          }
          if (((Map)addressBookList.get(j)).get("address") != null) {
            customerAddress = ((Map)addressBookList.get(j)).get("address").toString();
          }
          String[] pair = new String[2];
          pair[0] = "9";
          pair[1] = ((Map)addressBookList.get(j)).get("id").toString();
          entityArrI.add(pair);
          if (((Map)addressBookList.get(j)).get("name") != null) {
            entityLabel.add("通讯录：" + ((Map)addressBookList.get(j)).get("name").toString());
          } else {
            entityLabel.add("通讯录：");
          }
        }
        customerValuesMap.put("d_142", customerName);
        customerValuesMap.put("d_149", customerAddress);
        customerValuesMap.put("d_145", customerZipCode);
        customerValuesMap.put("d_148", customerMainPage);
        customerValuesMap.put("d_146", customerPhoneNum);
        if (entityArrI.size() > 0)
        {
          customerValuesMap.put("entityArr", entityArrI.toArray(new String[entityArrI.size()][2]));
          customerValuesMap.put("entityLabel", entityLabel.toArray(new String[entityLabel.size()]));
        }
        String newEntityID = this.entityService.createEntity(Integer.valueOf(35), customerValuesMap);
        

        ArrayList rEntityList = new ArrayList();
        REntity rEntity1 = new REntity();
        rEntity1.setMeta2Id(Integer.valueOf(35));
        rEntity1.setEntity2Id(newEntityID);
        rEntity1.setLabel("客户库：" + customerName);
        rEntityList.add(rEntity1);
        this.entityService.createJournal("市场模块", "客户库创建记录，内容为：" + customerName, rEntityList);
        
        info = "同时根据通讯录自动创建了一条客户信息。";
      }
      else if (isSingle)
      {
        for (int i = 0; i < customerList.size(); i++) {
          this.entityService.saveREntity(Integer.valueOf(35), ((Map)customerList.get(i)).get("id").toString(), 
            Integer.valueOf(9), addressID, label);
        }
      }
    }
    catch (Throwable t)
    {
      info = "保存失败";
      t.printStackTrace();
    }
    return info;
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
  
  public Integer getUpdate()
  {
    return this.update;
  }
  
  public void setUpdate(Integer update)
  {
    this.update = update;
  }
  
  public String getEntityId()
  {
    return this.entityId;
  }
  
  public void setEntityId(String entityId)
  {
    this.entityId = entityId;
  }
  
  public String getAttachNameList()
  {
    return this.attachNameList;
  }
  
  public void setAttachNameList(String attachNameList)
  {
    this.attachNameList = attachNameList;
  }
}
