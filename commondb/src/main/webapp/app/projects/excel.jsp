<%@ page language="java" pageEncoding="UTF-8" contentType="application/msexcel" %><%
  response.setCharacterEncoding("UTF-8");
  response.setHeader("Content-disposition","inline; filename=export.xls");
  java.io.OutputStream output = null;
  java.io.InputStream fis = null;
  java.util.List fieldsList = (java.util.List )request.getAttribute("fieldsList");
  java.util.List resList = (java.util.List )request.getAttribute("resList");
  
  try {
  			output = response.getOutputStream();
   			jxl.write.WritableWorkbook wwb = jxl.Workbook.createWorkbook(output);
 			jxl.write.WritableSheet ws = wwb.createSheet("sheet1", 0);
 			jxl.write.Label cell = new jxl.write.Label(0,0,"id");
 			ws.addCell(cell);      
            for(int i = 0 ; i < fieldsList.size() ; i ++)
            {
				  cell = new jxl.write.Label(i+1,0,((com.commondb.app.common.meta.Field)fieldsList.get(i)).getFieldId());
				  ws.addCell(cell);      
            }
            
            for(int i = 0 ; i < resList.size() ; i ++)
            {
            	java.util.List row = (java.util.List)resList.get(i);
            	for(int j = 2; j < row.size(); j ++)
            	{
            		//j为 0,1 对应update_time，update_user, 2对应id
            		cell = new jxl.write.Label(j-2,i+1,((com.commondb.app.common.meta.Field)row.get(j)).getValue());
				  	ws.addCell(cell);
            	}
            }
                 wwb.write();    
                 wwb.close();   
             } catch (Exception e) {   
               e.printStackTrace();   
             } 
   
%>