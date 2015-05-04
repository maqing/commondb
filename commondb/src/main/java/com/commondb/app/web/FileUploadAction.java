package com.commondb.app.web;

import com.commondb.app.common.FileTool;
import com.commondb.db.bo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

public class FileUploadAction
  extends ActionSupport
  implements Preparable, ServletRequestAware
{
  private File fileUpload;
  private String fileUploadFileName;
  private String fileSaveName;
  
  public String uploadFile()
    throws Exception
  {
    Map reqMap = ServletActionContext.getRequest().getParameterMap();
    String fileDiretory = ((String[])reqMap.get("fileDiretory"))[0];
    if ((fileDiretory.equals(null)) || (fileDiretory.equals(""))) {
      fileDiretory = "/";
    }
    String extName = "";
    String realName = this.fileUploadFileName;
    String newFileName = "";
    String nowTimeStr = "";
    
    Random r = new Random();
    
    String rootPath = ServletActionContext.getServletContext().getRealPath("");
    
    User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userPath = "/upload" + fileDiretory + user.getUsername() + "/";
    String savePath = rootPath + userPath;
    
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setCharacterEncoding("utf-8");
    

    int rannum = (int)(r.nextDouble() * 90000.0D) + 10000;
    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    nowTimeStr = sDateFormat.format(new Date());
    if (this.fileUploadFileName.lastIndexOf(".") >= 0)
    {
      extName = this.fileUploadFileName.substring(this.fileUploadFileName.lastIndexOf("."));
      realName = this.fileUploadFileName.substring(0, this.fileUploadFileName.lastIndexOf("."));
    }
    newFileName = realName + "_" + nowTimeStr + "_" + rannum + extName;
    if (!FileTool.copy(this.fileUpload, new File(savePath + newFileName))) {
      throw new Exception("文件上传失败");
    }
    setFileSaveName(userPath + newFileName);
    return "jsonback";
  }
  
  public void prepare()
    throws Exception
  {}
  
  public void setServletRequest(HttpServletRequest arg0) {}
  
  public File getFileUpload()
  {
    return this.fileUpload;
  }
  
  public void setFileUpload(File fileUpload)
  {
    this.fileUpload = fileUpload;
  }
  
  public String getFileUploadFileName()
  {
    return this.fileUploadFileName;
  }
  
  public void setFileUploadFileName(String fileUploadFileName)
  {
    this.fileUploadFileName = fileUploadFileName;
  }
  
  public String getFileSaveName()
  {
    return this.fileSaveName;
  }
  
  public void setFileSaveName(String fileSaveName)
  {
    this.fileSaveName = fileSaveName;
  }
}
