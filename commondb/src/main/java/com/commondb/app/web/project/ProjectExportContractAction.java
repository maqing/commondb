package com.commondb.app.web.project;

import com.commondb.app.common.meta.DescField;
import com.commondb.app.common.meta.Field;
import com.commondb.app.web.TrackEntityAction;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.struts2.ServletActionContext;

public class ProjectExportContractAction
  extends TrackEntityAction
{
  private String info;
  private String projectCodeColumn = "d_158";
  private String projectCode = "";
  
  public void exportContractEntity()
  {
    trackEntityBasic();
    calcProjectCode();
    exportProject();
    
    this.info = "导出成功";
  }
  
  public void calcProjectCode()
  {
    for (int i = 0; i < getDescFields().size(); i++)
    {
      DescField dField = (DescField)getDescFields().get(i);
      if (dField.getColumnName().equals(this.projectCodeColumn)) {
        this.projectCode = dField.getValue();
      }
    }
  }
  
  public void exportProject()
  {
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-disposition", "attachment;  filename=export.xls");
    OutputStream output = null;
    List fieldsList = getDescFields();
    try
    {
      output = response.getOutputStream();
      WritableWorkbook wwb = Workbook.createWorkbook(output);
      

      WritableSheet ws = wwb.createSheet("项目基本信息表", 0);
      Label cell = new Label(0, 0, "项目基本信息表");
      ws.addCell(cell);
      














      cell = new Label(0, 1, "id");
      ws.addCell(cell);
      cell = new Label(1, 1, getEntityId());
      ws.addCell(cell);
      ws.setRowView(1, 0);
      for (int i = 0; i < fieldsList.size(); i++)
      {
        cell = new Label(0, i + 2, ((Field)fieldsList.get(i)).getFieldId());
        ws.addCell(cell);
        cell = new Label(1, i + 2, ((Field)fieldsList.get(i)).getDisplayValue());
        ws.addCell(cell);
      }
      ws.setColumnView(0, 20);
      ws.setColumnView(1, 30);
      
      createAuditSheet(wwb, 0);
      
      createTrackSheet(wwb, 1, "合同财务计划表");
      createTrackSheet(wwb, 2, "合同供订货运输计划表");
      createTrackSheet(wwb, 3, "合同商务计划表");
      createTrackSheet(wwb, 4, "合同技术联络计划表");
      createTrackSheet(wwb, 5, "合同服务计划");
      

      wwb.write();
      wwb.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private void createTrackSheet(WritableWorkbook wwb, int sheetIndex, String sheetName)
  {
    try
    {
      Map cMap = (HashMap)super.getFavorMetaList().get(sheetIndex);
      WritableSheet cws = wwb.createSheet(sheetName, sheetIndex + 1);
      
      Label cell = new Label(0, 0, sheetName);
      cws.addCell(cell);
      cell = new Label(0, 1, "业务编号");
      cws.addCell(cell);
      
      cell = new Label(0, 2, "id");
      cws.addCell(cell);
      
      cws.setRowView(2, 0);
      
      List cSPFieldList = (List)cMap.get("fieldsList");
      for (int i = 0; i < cSPFieldList.size(); i++)
      {
        cell = new Label(0, i + 3, ((Field)cSPFieldList.get(i)).getFieldId());
        cws.addCell(cell);
      }
      cws.setColumnView(0, 20);
      
      List cSPValueList = (List)cMap.get("resList");
      for (int i = 0; i < cSPValueList.size(); i++)
      {
        cell = new Label(i + 1, 1, this.projectCode);
        cws.addCell(cell);
        

        List cValueRow = (List)cSPValueList.get(i);
        for (int j = 2; j < cValueRow.size(); j++)
        {
          cell = new Label(i + 1, j, ((Field)cValueRow.get(j)).getValue());
          cws.addCell(cell);
        }
        cws.setColumnView(i + 1, 30);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private void createAuditSheet(WritableWorkbook wwb, int sheetIndex)
  {
    try
    {
      Map cellPositionMap = new HashMap();
      int rowIndex = 4;
      cellPositionMap.put("合同号", new int[] { rowIndex++, 1 });
      cellPositionMap.put("项目名称", new int[] { rowIndex++, 1 });
      cellPositionMap.put("项目背景", new int[] { rowIndex++, 1 });
      cellPositionMap.put("合同买方风险评估", new int[] { rowIndex, 2 });
      cellPositionMap.put("合同买方", new int[] { rowIndex++, 1 });
      cellPositionMap.put("合同最终用户风险评估", new int[] { rowIndex, 2 });
      cellPositionMap.put("合同最终用户", new int[] { rowIndex++, 1 });
      cellPositionMap.put("合同卖方风险评估", new int[] { rowIndex, 2 });
      cellPositionMap.put("合同卖方", new int[] { rowIndex++, 1 });
      cellPositionMap.put("合同供货方风险评估", new int[] { rowIndex, 2 });
      cellPositionMap.put("合同供货方", new int[] { rowIndex++, 1 });
      cellPositionMap.put("合同最终供货方风险评估", new int[] { rowIndex, 2 });
      cellPositionMap.put("合同最终供货方", new int[] { rowIndex++, 1 });
      cellPositionMap.put("合同价格条款风险评估", new int[] { rowIndex, 2 });
      cellPositionMap.put("合同价格条款", new int[] { rowIndex++, 1 });
      cellPositionMap.put("交货期限条款风险评估", new int[] { rowIndex, 2 });
      cellPositionMap.put("交货期限条款", new int[] { rowIndex++, 1 });
      cellPositionMap.put("技术条款风险评估", new int[] { rowIndex, 2 });
      cellPositionMap.put("技术条款", new int[] { rowIndex++, 1 });
      cellPositionMap.put("供货内容条款风险评估", new int[] { rowIndex, 2 });
      cellPositionMap.put("供货内容条款", new int[] { rowIndex++, 1 });
      cellPositionMap.put("技术服务条款风险评估", new int[] { rowIndex, 2 });
      cellPositionMap.put("技术服务条款", new int[] { rowIndex++, 1 });
      cellPositionMap.put("售后服务条款风险评估", new int[] { rowIndex, 2 });
      cellPositionMap.put("售后服务条款", new int[] { rowIndex++, 1 });
      cellPositionMap.put("合同特别约定条款风险评估", new int[] { rowIndex, 2 });
      cellPositionMap.put("合同特别约定条款", new int[] { rowIndex++, 1 });
      cellPositionMap.put("项目总体风险评估", new int[] { rowIndex++, 1 });
      
      String sheetName = "合同审核表";
      Map cMap = (HashMap)super.getFavorMetaList().get(sheetIndex);
      WritableSheet cws = wwb.createSheet(sheetName, sheetIndex + 1);
      
      Label cell = new Label(0, 0, sheetName);
      cws.addCell(cell);
      cell = new Label(0, 1, "内容");
      cws.addCell(cell);
      
      cell = new Label(0, 2, "业务编号");
      cws.addCell(cell);
      
      cell = new Label(0, 3, "id");
      cws.addCell(cell);
      
      cws.setRowView(3, 0);
      







      cws.setColumnView(0, 20);
      
      List cSPValueList = (List)cMap.get("resList");
      for (int i = 0; i < cSPValueList.size(); i++)
      {
        cell = new Label(i * 2 + 1, 2, this.projectCode);
        cws.addCell(cell);
        

        cell = new Label(i * 2 + 2, 1, "分项风险评估");
        cws.addCell(cell);
        

        List cValueRow = (List)cSPValueList.get(i);
        for (int j = 2; j < cValueRow.size(); j++)
        {
          Field cValueCellField = (Field)cValueRow.get(j);
          if (j == 2)
          {
            cell = new Label(i * 2 + 1, j + 1, cValueCellField.getValue());
            cws.addCell(cell);
          }
          else
          {
            int[] cellPosition = (int[])cellPositionMap.get(cValueCellField.getFieldId());
            
            cell = new Label(i * 2 + cellPosition[1], cellPosition[0], cValueCellField.getValue());
            cws.addCell(cell);
            if (cellPosition[1] == 1)
            {
              cell = new Label(0, cellPosition[0], cValueCellField.getFieldId());
              cws.addCell(cell);
            }
          }
        }
        cws.setColumnView(i * 2 + 1, 30);
        cws.setColumnView(i * 2 + 2, 30);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public String getInfo()
  {
    return this.info;
  }
  
  public void setInfo(String info)
  {
    this.info = info;
  }
}
