package com.commondb.app.web;

import com.commondb.app.common.FileTool;
import com.commondb.app.common.meta.FieldFactory;
import com.commondb.app.common.meta.IField;
import com.commondb.common.ImageTool;
import com.commondb.common.RandomTool;
import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.PicAttrDef;
import com.commondb.db.bo.User;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.commondb.security.service.SecurityUserHolder;
import com.opensymphony.xwork2.Preparable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import jxl.Cell;
import jxl.Image;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.struts2.ServletActionContext;

public class ImportAction
  extends BasicAction
  implements Preparable
{
  private User user;
  private File myFile;
  private String myFileFileName;
  private String entityName;
  private String fileName;
  private MetaService metaService;
  private EntityService entityService;
  private String info;
  private Integer metaId;
  private List colList;
  
  public String getInfo()
  {
    return this.info;
  }
  
  public void setInfo(String info)
  {
    this.info = info;
  }
  
  private List fieldsList = new ArrayList();
  
  public String step1()
  {
    Meta meta = this.metaService.findMetaByName(this.entityName);
    this.metaId = meta.getId();
    
    return "success";
  }
  
  public String step2()
  {
    this.user = SecurityUserHolder.getCurrentUser();
    this.fileName = (this.user.getUserName() + "_" + getMyFileFileName());
    String dstPath = ServletActionContext.getServletContext()
      .getRealPath("upload") + 
      "\\" + this.fileName;
    File dstFile = new File(dstPath);
    FileTool.copy(this.myFile, dstFile);
    



    List descAttrList = this.metaService.findDescAttrDef(this.metaId);
    for (int j = 0; j < descAttrList.size(); j++)
    {
      DescAttrDef descDef = (DescAttrDef)descAttrList.get(j);
      
      IField descField = FieldFactory.getInstance().createField(
        descDef);
      this.fieldsList.add(descField);
    }
    List picAttrList = this.metaService.findPicAttrDef(this.metaId);
    for (int j = 0; j < picAttrList.size(); j++)
    {
      PicAttrDef picDef = (PicAttrDef)picAttrList.get(j);
      
      IField picField = FieldFactory.getInstance().createField(
        picDef);
      this.fieldsList.add(picField);
    }
    this.colList = new ArrayList();
    try
    {
      InputStream is = new FileInputStream(dstFile);
      Workbook rwb = Workbook.getWorkbook(is);
      Sheet rs = rwb.getSheet(0);
      int colCount = rs.getColumns();
      for (int j = 0; j < colCount; j++)
      {
        Cell cell = rs.getCell(j, 0);
        this.colList.add(cell.getContents());
      }
      is.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return "success";
  }
  
  public String step3()
  {
    Map valuesMap = new HashMap();
    HttpServletRequest req = ServletActionContext.getRequest();
    Map reqMap = req.getParameterMap();
    Set<String> keys = reqMap.keySet();
    
    this.colList = new ArrayList();
    Map imageMap = new HashMap();
    try
    {
      InputStream is = new FileInputStream(ServletActionContext.getServletContext()
        .getRealPath("upload") + "\\" + this.fileName);
      Workbook rwb = Workbook.getWorkbook(is);
      Sheet rs = rwb.getSheet(0);
      for (int i = 0; i < rs.getNumberOfImages(); i++)
      {
        Image image = rs.getDrawing(i);
        if (image != null)
        {
          String position = "R" + (int)image.getRow() + "C" + (int)image.getColumn();
          byte[] imageData = image.getImageData();
          imageMap.put(position, imageData);
        }
      }
      int rowCount = rs.getRows();
      int newCount = 0;
      int updateCount = 0;
      for (int j = 1; j < rowCount; j++)
      {
        for (String key : keys)
        {
          if (key.startsWith("d_"))
          {
            Integer col = new Integer(((String[])reqMap.get(key))[0]);
            valuesMap.put(key, rs.getCell(col.intValue(), j).getContents());
          }
          if (key.startsWith("p_"))
          {
            Integer col = new Integer(((String[])reqMap.get(key))[0]);
            String picPosition = "R" + j + "C" + col;
            byte[] picImageData = (byte[])imageMap.get(picPosition);
            if (picImageData == null)
            {
              valuesMap.put(key, "");
            }
            else
            {
              String picFileName = new SimpleDateFormat("yyMMddhhmmss").format(new Date()) + 
                RandomTool.generateString(6) + picPosition + ".jpg";
              File picFile = new File(ServletActionContext.getServletContext()
                .getRealPath("upload") + "\\import\\" + picFileName);
              FileOutputStream outStream = new FileOutputStream(picFile);
              outStream.write(picImageData);
              outStream.close();
              
              ImageTool.createPreviewImge(picFile);
              valuesMap.put(key, "upload/import/" + picFileName);
            }
          }
        }
        if ((rs.getCell(0, 0).getContents().equalsIgnoreCase("id")) && (!rs.getCell(0, j).getContents().equals("")))
        {
          this.entityService.importEntity(this.metaId, rs.getCell(0, j).getContents(), valuesMap);
          updateCount++;
        }
        else
        {
          this.entityService.createEntity(this.metaId, valuesMap);
          newCount++;
        }
      }
      is.close();
      FileTool.deleteFile(ServletActionContext.getServletContext().getRealPath("upload") + 
        "\\" + this.fileName);
      this.info = ("导入了" + (rs.getRows() - 1) + "条数据！" + newCount + "条新建数据，" + updateCount + "条更新已有数据！");
    }
    catch (Exception e)
    {
      e.printStackTrace();
      this.info = e.getMessage();
    }
    return "success";
  }
  
  public void prepare()
    throws Exception
  {}
  
  public String getEntityName()
  {
    return this.entityName;
  }
  
  public void setEntityName(String entityName)
  {
    this.entityName = entityName;
  }
  
  public String getFileName()
  {
    return this.fileName;
  }
  
  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }
  
  public MetaService getMetaService()
  {
    return this.metaService;
  }
  
  public void setMetaService(MetaService metaService)
  {
    this.metaService = metaService;
  }
  
  public EntityService getEntityService()
  {
    return this.entityService;
  }
  
  public void setEntityService(EntityService entityService)
  {
    this.entityService = entityService;
    this.entityService = entityService;
  }
  
  public Integer getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
  }
  
  public List getColList()
  {
    return this.colList;
  }
  
  public void setColList(List colList)
  {
    this.colList = colList;
  }
  
  public List getFieldsList()
  {
    return this.fieldsList;
  }
  
  public void setFieldsList(List fieldsList)
  {
    this.fieldsList = fieldsList;
  }
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public File getMyFile()
  {
    return this.myFile;
  }
  
  public void setMyFile(File myFile)
  {
    this.myFile = myFile;
  }
  
  public String getMyFileFileName()
  {
    return this.myFileFileName;
  }
  
  public void setMyFileFileName(String myFileFileName)
  {
    this.myFileFileName = myFileFileName;
  }
}
