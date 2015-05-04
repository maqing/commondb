package com.commondb.app.web.project;

import com.commondb.app.common.FileTool;
import com.commondb.app.web.BasicAction;
import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.User;
import com.commondb.db.service.EntityService;
import com.commondb.db.service.MetaService;
import com.commondb.security.service.SecurityUserHolder;
import com.opensymphony.xwork2.Preparable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.struts2.ServletActionContext;

public class ProjectImportContractAction
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
  private Meta meta;
  private String projectCodeColumn = "d_158";
  
  public String getInfo()
  {
    return this.info;
  }
  
  public void setInfo(String info)
  {
    this.info = info;
  }
  
  public String importContractEntity1()
  {
    return "success";
  }
  
  public String importContractEntity2()
  {
    this.user = SecurityUserHolder.getCurrentUser();
    this.fileName = (this.user.getUserName() + "_" + getMyFileFileName());
    String dstPath = ServletActionContext.getServletContext().getRealPath("upload") + 
      "\\" + this.fileName;
    File dstFile = new File(dstPath);
    FileTool.copy(this.myFile, dstFile);
    try
    {
      this.info = "";
      
      InputStream is = new FileInputStream(dstFile);
      Workbook rwb = Workbook.getWorkbook(is);
      
      importAuditSheet(rwb, "合同审核表");
      importTrackSheet(rwb, "合同财务计划表");
      importTrackSheet(rwb, "合同供订货运输计划表");
      importTrackSheet(rwb, "合同商务计划表");
      importTrackSheet(rwb, "合同技术联络计划表");
      importTrackSheet(rwb, "合同服务计划");
      
      is.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    FileTool.deleteFile(dstPath);
    
    return "success";
  }
  
  private void importTrackSheet(Workbook wwb, String entityName)
  {
    try
    {
      this.meta = this.metaService.findMetaByName(entityName);
      List descAttrList = this.metaService.findDescAttrDef(this.meta.getMetaId());
      Map<String, String> descAttrMap = new HashMap();
      for (int i = 0; i < descAttrList.size(); i++)
      {
        DescAttrDef descAttrDefItem = (DescAttrDef)descAttrList.get(i);
        descAttrMap.put(descAttrDefItem.getAttrName(), "d_" + descAttrDefItem.getAttrId().toString());
      }
      Sheet cws = wwb.getSheet(entityName);
      
      Map<Integer, String> rowDescAttrMap = new HashMap();
      for (int rowIndex = 3; rowIndex < cws.getRows(); rowIndex++) {
        rowDescAttrMap.put(Integer.valueOf(rowIndex), (String)descAttrMap.get(cws.getCell(0, rowIndex).getContents()));
      }
      Map valuesMap = new HashMap();
      int newCount = 0;
      int updateCount = 0;
      for (int colIndex = 1; colIndex < cws.getColumns(); colIndex++)
      {
        for (int rowIndex = 3; rowIndex < cws.getRows(); rowIndex++)
        {
          String cellContents = cws.getCell(colIndex, rowIndex).getContents();
          Object columnName = rowDescAttrMap.get(Integer.valueOf(rowIndex));
          if (columnName != null) {
            valuesMap.put(columnName.toString(), cellContents);
          }
        }
        if ((cws.getCell(0, 2).getContents().equalsIgnoreCase("id")) && (!cws.getCell(colIndex, 2).getContents().equals("")))
        {
          this.entityService.importEntity(this.meta.getMetaId(), cws.getCell(colIndex, 2).getContents(), valuesMap);
          updateCount++;
        }
        else
        {
          List projectIDList = this.entityService.dynSelect("id", "t_entity_13", 
            this.projectCodeColumn + "='" + cws.getCell(colIndex, 1).getContents() + "' ");
          if (projectIDList.size() > 0)
          {
            String projectID = ((Map)projectIDList.get(0)).get("id").toString();
            String[][] entityArr = new String[1][2];
            entityArr[0][0] = this.metaId.toString();
            entityArr[0][1] = projectID;
            
            String[] entityLabel = new String[1];
            entityLabel[0] = ("项目库: " + cws.getCell(colIndex, 1).getContents());
            
            valuesMap.put("entityArr", entityArr);
            valuesMap.put("entityLabel", entityLabel);
          }
          this.entityService.createEntity(this.meta.getMetaId(), valuesMap);
          newCount++;
        }
      }
      this.info = 
        (this.info + "<br>" + entityName + "导入了" + (cws.getColumns() - 1) + "条数据！" + newCount + "条新建数据，" + updateCount + "条更新已有数据！" + "</br>");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private void importAuditSheet(Workbook wwb, String entityName)
  {
    try
    {
      this.meta = this.metaService.findMetaByName(entityName);
      List descAttrList = this.metaService.findDescAttrDef(this.meta.getMetaId());
      Map<String, String> descAttrMap = new HashMap();
      for (int i = 0; i < descAttrList.size(); i++)
      {
        DescAttrDef descAttrDefItem = (DescAttrDef)descAttrList.get(i);
        descAttrMap.put(descAttrDefItem.getAttrName(), "d_" + descAttrDefItem.getAttrId().toString());
      }
      Sheet cws = wwb.getSheet(entityName);
      

      String[][] rowDescAttrMap = new String[2][16];
      rowDescAttrMap[0][0] = "合同号";
      rowDescAttrMap[0][1] = "项目名称";
      rowDescAttrMap[0][2] = "项目背景";
      rowDescAttrMap[1][3] = "合同买方风险评估";
      rowDescAttrMap[0][3] = "合同买方";
      rowDescAttrMap[1][4] = "合同最终用户风险评估";
      rowDescAttrMap[0][4] = "合同最终用户";
      rowDescAttrMap[1][5] = "合同卖方风险评估";
      rowDescAttrMap[0][5] = "合同卖方";
      rowDescAttrMap[1][6] = "合同供货方风险评估";
      rowDescAttrMap[0][6] = "合同供货方";
      rowDescAttrMap[1][7] = "合同最终供货方风险评估";
      rowDescAttrMap[0][7] = "合同最终供货方";
      rowDescAttrMap[1][8] = "合同价格条款风险评估";
      rowDescAttrMap[0][8] = "合同价格条款";
      rowDescAttrMap[1][9] = "交货期限条款风险评估";
      rowDescAttrMap[0][9] = "交货期限条款";
      rowDescAttrMap[1][10] = "技术条款风险评估";
      rowDescAttrMap[0][10] = "技术条款";
      rowDescAttrMap[1][11] = "供货内容条款风险评估";
      rowDescAttrMap[0][11] = "供货内容条款";
      rowDescAttrMap[1][12] = "技术服务条款风险评估";
      rowDescAttrMap[0][12] = "技术服务条款";
      rowDescAttrMap[1][13] = "售后服务条款风险评估";
      rowDescAttrMap[0][13] = "售后服务条款";
      rowDescAttrMap[1][14] = "合同特别约定条款风险评估";
      rowDescAttrMap[0][14] = "合同特别约定条款";
      rowDescAttrMap[1][15] = "项目总体风险评估";
      


      Map valuesMap = new HashMap();
      int newCount = 0;
      int updateCount = 0;
      for (int colIndex = 1; colIndex <= (cws.getColumns() - 1) / 2; colIndex++)
      {
        for (int i = 0; i < 2; i++) {
          for (int rowIndex = 4; rowIndex < cws.getRows(); rowIndex++)
          {
            String cellContents = cws.getCell(2 * colIndex + i - 1, rowIndex).getContents();
            Object columnName = descAttrMap.get(rowDescAttrMap[i][(rowIndex - 4)]);
            if (columnName != null) {
              valuesMap.put(columnName.toString(), cellContents);
            }
          }
        }
        if ((cws.getCell(0, 3).getContents().equalsIgnoreCase("id")) && (!cws.getCell(2 * colIndex - 1, 3).getContents().equals("")))
        {
          this.entityService.importEntity(this.meta.getMetaId(), cws.getCell(2 * colIndex - 1, 3).getContents(), valuesMap);
          updateCount++;
        }
        else
        {
          List projectIDList = this.entityService.dynSelect("id", "t_entity_13", 
            this.projectCodeColumn + "='" + cws.getCell(2 * colIndex - 1, 2).getContents() + "' ");
          if (projectIDList.size() > 0)
          {
            String projectID = ((Map)projectIDList.get(0)).get("id").toString();
            String[][] entityArr = new String[1][2];
            entityArr[0][0] = this.metaId.toString();
            entityArr[0][1] = projectID;
            
            String[] entityLabel = new String[1];
            entityLabel[0] = ("项目库: " + cws.getCell(2 * colIndex - 1, 2).getContents());
            
            valuesMap.put("entityArr", entityArr);
            valuesMap.put("entityLabel", entityLabel);
          }
          this.entityService.createEntity(this.meta.getMetaId(), valuesMap);
          newCount++;
        }
      }
      this.info = 
        (this.info + "<br>" + entityName + "导入了" + (cws.getColumns() - 1) / 2 + "条数据！" + newCount + "条新建数据，" + updateCount + "条更新已有数据！" + "</br>");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
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
    super.setEntityService(entityService);
  }
  
  public Integer getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
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
