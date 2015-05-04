package com.commondb.db.web;

import com.commondb.common.ImageTool;
import com.commondb.common.JsonResult;
import com.commondb.db.bo.Entity;
import com.commondb.db.bo.Meta;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class AdminEntityAction
  extends ActionSupport
  implements Preparable
{
  private static final int BUFFER_SIZE = 16384;
  private int userId;
  private int metaId;
  private String entityId;
  private JsonResult result = new JsonResult();
  private MetaService metaService;
  private EntityService entityService;
  private String entityDesc;
  private String entityName;
  private List entityList;
  private List picAttrDef;
  private List descAttrDef;
  private List hierAttrDef;
  private List charaAttrDef;
  private List<File> myFile;
  private List<String> myFileFileName;
  private Integer rMetaId;
  private String rEntityId;
  private Integer tMetaId;
  private String[] tEntityIdArr;
  private String[] charaArr;
  private String[] hierarchyArr;
  private String[] entityArr;
  private String columnName;

  public String[] getEntityArr()
  {
    return this.entityArr;
  }

  public void setEntityArr(String[] entityArr)
  {
    this.entityArr = entityArr;
  }

  public String[] getHierarchyArr()
  {
    return this.hierarchyArr;
  }

  public void setHierarchyArr(String[] hierarchyArr)
  {
    this.hierarchyArr = hierarchyArr;
  }

  public String[] getCharaArr()
  {
    return this.charaArr;
  }

  public void setCharaArr(String[] charaArr)
  {
    this.charaArr = charaArr;
  }

  public Integer getRMetaId()
  {
    return this.rMetaId;
  }

  public void setRMetaId(Integer metaId)
  {
    this.rMetaId = metaId;
  }

  public Integer getTMetaId()
  {
    return this.tMetaId;
  }

  public void setTMetaId(Integer metaId)
  {
    this.tMetaId = metaId;
  }

  public String getREntityId()
  {
    return this.rEntityId;
  }

  public void setREntityId(String entityId)
  {
    this.rEntityId = entityId;
  }

  public String[] getTEntityIdArr()
  {
    return this.tEntityIdArr;
  }

  public void setTEntityIdArr(String[] entityIdArr)
  {
    this.tEntityIdArr = entityIdArr;
  }

  public String getColumnName()
  {
    return this.columnName;
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

  public void setColumnName(String columnName)
  {
    this.columnName = columnName;
  }

  public String getEntityId()
  {
    return this.entityId;
  }

  public void setEntityId(String entityId)
  {
    this.entityId = entityId;
  }

  public List getEntityList()
  {
    return this.entityList;
  }

  public void setEntityList(List entityList)
  {
    this.entityList = entityList;
  }

  public EntityService getEntityService()
  {
    return this.entityService;
  }

  public void setEntityService(EntityService entityService)
  {
    this.entityService = entityService;
  }

  public void prepare()
    throws Exception
  {}

  public String listPics()
  {
    this.result.setData(this.entityService.getPicList());
    this.result.setSuccess(true);
    return "success";
  }

  public String preManageEntityJS()
  {
    this.picAttrDef = this.metaService.findPicAttrDef(Integer.valueOf(this.metaId));
    this.descAttrDef = this.metaService.findDescAttrDef(Integer.valueOf(this.metaId));
    this.hierAttrDef = this.metaService.findHierarchyAttrDef(Integer.valueOf(this.metaId));
    this.charaAttrDef = this.metaService.findCharacterAttrDef(Integer.valueOf(this.metaId));
    List l = this.metaService.findMeta(Integer.valueOf(this.metaId));
    if (l.size() > 0) {
      this.entityName = ((Meta)l.get(0)).getEntityName();
    } else {
      this.entityName = "";
    }
    return "success";
  }

  public List getPicAttrDef()
  {
    return this.picAttrDef;
  }

  public void setPicAttrDef(List picAttrDef)
  {
    this.picAttrDef = picAttrDef;
  }

  public List getDescAttrDef()
  {
    return this.descAttrDef;
  }

  public void setDescAttrDef(List descAttrDef)
  {
    this.descAttrDef = descAttrDef;
  }

  public List getHierAttrDef()
  {
    return this.hierAttrDef;
  }

  public void setHierAttrDef(List hierAttrDef)
  {
    this.hierAttrDef = hierAttrDef;
  }

  public List getCharaAttrDef()
  {
    return this.charaAttrDef;
  }

  public void setCharaAttrDef(List charaAttrDef)
  {
    this.charaAttrDef = charaAttrDef;
  }

  public String preManageEntity()
  {
    return "success";
  }

  public String preNewRelation()
  {
    return "success";
  }

  public String preManageRelation()
  {
    return "success";
  }

  public String listEntity()
  {
    this.entityList = this.entityService.findEntityBymetaId(Integer.valueOf(this.metaId));

    return "success";
  }

  public String jsonEntity()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.entityService.findEntityBymetaId(Integer.valueOf(this.metaId)));
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  private static void copy(File src, File dst)
  {
    InputStream in = null;
    OutputStream out = null;
    try
    {
      in = new BufferedInputStream(new FileInputStream(src), 16384);
      out = new BufferedOutputStream(new FileOutputStream(dst),
        16384);
      byte[] buffer = new byte[16384];
      int len = 0;
      while ((len = in.read(buffer)) > 0) {
        out.write(buffer, 0, len);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      if (in != null) {
        try
        {
          in.close();
        }
        catch (IOException e1)
        {
          e1.printStackTrace();
        }
      }
      if (out != null) {
        try
        {
          out.close();
        }
        catch (IOException e2)
        {
          e2.printStackTrace();
        }
      }
    }
    finally
    {
      if (in != null) {
        try
        {
          in.close();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
      if (out != null) {
        try
        {
          out.close();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
  }

  public String createEntity()
  {
    List srcFiles = getMyFile();
    List filesArrayList = new ArrayList();
    if (srcFiles != null) {
      for (int i = 0; i < srcFiles.size(); i++)
      {
        String dstPath = ServletActionContext.getServletContext()
          .getRealPath("upload") +
          "\\" + (String)getMyFileFileName().get(i);
        File dstFile = new File(dstPath);
        copy((File)srcFiles.get(i), dstFile);
        ImageTool.createPreviewImge(dstFile);
        filesArrayList.add(dstFile);
      }
    }
    Map m = ActionContext.getContext().getParameters();

    this.result = new JsonResult();
    HttpServletRequest req = ServletActionContext.getRequest();

    Map reqMap = req.getParameterMap();

    Map valuesMap = new HashMap();
    int fileIndex = 0;
    try
    {
      Set<String> keys = reqMap.keySet();
      for (String key : keys) {
        if (key.startsWith("p_server"))
        {
          String value = ((String[])reqMap.get(key.replaceFirst("p_server", "p_")))[0];
          if ((value == null) || (value.equals(""))) {
            valuesMap.put(key.replaceFirst("p_server", "p_"), ((String[])reqMap.get(key))[0]);
          }
        }
        else if (key.startsWith("p_"))
        {
          if ((((String[])reqMap.get(key))[0] != null) && (!((String[])reqMap.get(key))[0].equals("")))
          {
            File f = (File)filesArrayList.get(fileIndex++);

            valuesMap.put(key, "upload/" + f.getName());
          }
        }
        else
        {
          valuesMap.put(key, ((String[])reqMap.get(key))[0]);
        }
      }
      valuesMap.remove("metaId");
      valuesMap.remove("id");
      if (this.charaArr != null)
      {
        Integer[][] charaArrI = new Integer[this.charaArr.length][2];
        for (int i = 0; i < this.charaArr.length; i++)
        {
          String[] s = this.charaArr[i].split(",");
          charaArrI[i][0] = Integer.valueOf(Integer.parseInt(s[0]));
          charaArrI[i][1] = Integer.valueOf(Integer.parseInt(s[1]));
        }
        valuesMap.put("charaArr", charaArrI);
      }
      else
      {
        Integer[][] charaArrI = (Integer[][])null;
        valuesMap.put("charaArr", charaArrI);
      }
      if (this.hierarchyArr != null)
      {
        String[][] hierarchyArrI = new String[this.hierarchyArr.length][2];
        for (int i = 0; i < this.hierarchyArr.length; i++)
        {
          String[] s = this.hierarchyArr[i].split(",");
          hierarchyArrI[i][0] = s[0];
          hierarchyArrI[i][1] = s[1];
        }
        valuesMap.put("hierarchyArr", hierarchyArrI);
      }
      else
      {
        Integer[][] hierarchyArrI = (Integer[][])null;
        valuesMap.put("hierarchyArr", hierarchyArrI);
      }
      if (this.entityArr != null)
      {
        String[][] entityArrI = new String[this.entityArr.length][2];
        for (int i = 0; i < this.entityArr.length; i++)
        {
          String[] s = this.entityArr[i].split(",");
          entityArrI[i][0] = s[0];
          entityArrI[i][1] = s[1];
        }
        valuesMap.put("entityArr", entityArrI);
      }
      else
      {
        Integer[][] entityArrI = (Integer[][])null;
        valuesMap.put("entityArr", entityArrI);
      }
      this.entityService.createEntity(Integer.valueOf(this.metaId), valuesMap);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  public String delEntity()
  {
    this.result = new JsonResult();
    try
    {
      this.entityService.delEntity(Integer.valueOf(this.metaId), this.entityId);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  public String updateEntity()
  {
    List srcFiles = getMyFile();
    List filesArrayList = new ArrayList();
    if (srcFiles != null) {
      for (int i = 0; i < srcFiles.size(); i++)
      {
        String dstPath = ServletActionContext.getServletContext()
          .getRealPath("upload") +
          "\\" + (String)getMyFileFileName().get(i);
        File dstFile = new File(dstPath);
        copy((File)srcFiles.get(i), dstFile);
        ImageTool.createPreviewImge(dstFile);
        filesArrayList.add(dstFile);
      }
    }
    Map m = ActionContext.getContext().getParameters();

    this.result = new JsonResult();
    HttpServletRequest req = ServletActionContext.getRequest();

    Map reqMap = req.getParameterMap();

    Map valuesMap = new HashMap();
    int fileIndex = 0;
    try
    {
      Set<String> keys = reqMap.keySet();
      for (String key : keys) {
        if (key.startsWith("p_server"))
        {
          String value = ((String[])reqMap.get(key.replaceFirst("p_server", "p_")))[0];
          if ((value == null) || (value.equals(""))) {
            valuesMap.put(key.replaceFirst("p_server", "p_"), ((String[])reqMap.get(key))[0]);
          }
        }
        else if (key.startsWith("p_"))
        {
          if ((((String[])reqMap.get(key))[0] != null) && (!((String[])reqMap.get(key))[0].equals("")))
          {
            File f = (File)filesArrayList.get(fileIndex++);

            valuesMap.put(key, "upload/" + f.getName());
          }
        }
        else
        {
          valuesMap.put(key, ((String[])reqMap.get(key))[0]);
        }
      }
      if (this.hierarchyArr != null)
      {
        String[][] hierarchyArrI = new String[this.hierarchyArr.length][2];
        for (int i = 0; i < this.hierarchyArr.length; i++)
        {
          String[] s = this.hierarchyArr[i].split(",");
          hierarchyArrI[i][0] = s[0];
          hierarchyArrI[i][1] = s[1];
        }
        valuesMap.put("hierarchyArr", hierarchyArrI);
      }
      else
      {
        Integer[][] hierarchyArrI = (Integer[][])null;
        valuesMap.put("hierarchyArr", hierarchyArrI);
      }
      String id = (String)valuesMap.get("id");
      valuesMap.remove("metaId");
      valuesMap.remove("id");
      this.entityService.updateEntity(Integer.valueOf(this.metaId), id, valuesMap);

      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  public String updateEntity2()
  {
    this.result = new JsonResult();
    try
    {
      Entity entity = new Entity();
      entity.setMetaId(Integer.valueOf(this.metaId));


      this.result.success = true;
      this.result.setData(entity);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  public String listDescAttrData()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.entityService.selectColumnData(Integer.valueOf(this.metaId), this.columnName));
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  public String listCharaAttrData()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.findDescAttrDef(Integer.valueOf(this.metaId)));
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  public String fetchMetaAttrData()
  {
    this.result = new JsonResult();
    try
    {
      Map hMap = new HashMap();
      hMap.put("picAttrData", this.metaService.findPicAttrDef(Integer.valueOf(this.metaId)));
      hMap.put("descAttrData", this.metaService.findDescAttrDef(Integer.valueOf(this.metaId)));
      hMap.put("hierAttrData", this.metaService.findHierarchyAttrDef(Integer.valueOf(this.metaId)));
      hMap.put("charaAttrData", this.metaService.findCharacterAttrDef(Integer.valueOf(this.metaId)));

      this.result.setData(hMap);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  public int getMetaId()
  {
    return this.metaId;
  }

  public void setMetaId(int metaId)
  {
    this.metaId = metaId;
  }

  public String getEntityDesc()
  {
    return this.entityDesc;
  }

  public void setEntityDesc(String entityDesc)
  {
    this.entityDesc = entityDesc;
  }

  public String getEntityName()
  {
    return this.entityName;
  }

  public void setEntityName(String entityName)
  {
    this.entityName = entityName;
  }

  public int getUserId()
  {
    return this.userId;
  }

  public void setUserId(int userId)
  {
    this.userId = userId;
  }

  public MetaService getMetaService()
  {
    return this.metaService;
  }

  public void setMetaService(MetaService metaService)
  {
    this.metaService = metaService;
  }

  public JsonResult getResult()
  {
    return this.result;
  }

  public void setResult(JsonResult result)
  {
    this.result = result;
  }

  public String saveREntity()
  {
    this.result = new JsonResult();
    try
    {
      this.entityService.saveREntity(this.rMetaId, this.rEntityId, this.tMetaId, this.tEntityIdArr);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  public String updateREntity()
  {
    this.result = new JsonResult();
    try
    {
      this.entityService.updateREntity(this.rMetaId, this.rEntityId, this.tMetaId, this.tEntityIdArr);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  public String delREntity()
  {
    this.result = new JsonResult();
    try
    {
      this.entityService.delREntity(this.rMetaId, this.rEntityId, this.tMetaId);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  public String listREntity()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.entityService.listREntity(Integer.valueOf(this.metaId), this.entityId));
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  public String jsonEntityByChara()
  {
    this.result = new JsonResult();
    try
    {
      if (this.charaArr != null)
      {
        Integer[][] charaArrI = new Integer[this.charaArr.length][2];
        for (int i = 0; i < this.charaArr.length; i++)
        {
          String[] s = this.charaArr[i].split(",");
          charaArrI[i][0] = Integer.valueOf(Integer.parseInt(s[0]));
          charaArrI[i][1] = Integer.valueOf(Integer.parseInt(s[1]));
        }
        this.result.setData(this.entityService.findEntityByChara(Integer.valueOf(this.metaId), charaArrI));
      }
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  public String jsonEntityByHierarchy()
  {
    this.result = new JsonResult();
    try
    {
      if (this.hierarchyArr != null)
      {
        String[][] hierarchyArrI = new String[this.hierarchyArr.length][2];
        for (int i = 0; i < this.hierarchyArr.length; i++)
        {
          String[] s = this.hierarchyArr[i].split(",");
          hierarchyArrI[i][0] = s[0];
          hierarchyArrI[i][1] = s[1];
        }
        this.result.setData(this.entityService.findEntityByHierarchy(Integer.valueOf(this.metaId), hierarchyArrI));
      }
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }

  public String jsonHierarchyByEntity()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.entityService.findHierarchyByEntity(Integer.valueOf(this.metaId), this.entityId));
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
}
