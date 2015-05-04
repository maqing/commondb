package com.commondb.app.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileTool
{
  private static final int BUFFER_SIZE = 16384;
  
  public static boolean copy(File src, File dst)
  {
    InputStream in = null;
    OutputStream out = null;
    try
    {
      if (dst.exists()) {
        dst.delete();
      }
      if (!dst.getParentFile().exists()) {
        if (!dst.getParentFile().mkdirs()) {
          throw new Exception("创建目标文件所在目录失败！");
        }
      }
      in = new BufferedInputStream(new FileInputStream(src), 16384);
      out = new BufferedOutputStream(new FileOutputStream(dst), 
        16384);
      byte[] buffer = new byte[16384];
      int len = 0;
      while ((len = in.read(buffer)) > 0) {
        out.write(buffer, 0, len);
      }
      return true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return false;
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
          return false;
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
          return false;
        }
      }
    }
  }
  
  public static boolean DeleteFolder(String sPath)
  {
    boolean flag = false;
    File file = new File(sPath);
    if (!file.exists()) {
      return flag;
    }
    if (file.isFile()) {
      return deleteFile(sPath);
    }
    return deleteDirectory(sPath);
  }
  
  public static boolean deleteFile(String sPath)
  {
    boolean flag = false;
    File file = new File(sPath);
    if ((file.isFile()) && (file.exists()))
    {
      file.delete();
      flag = true;
    }
    return flag;
  }
  
  public static boolean deleteDirectory(String sPath)
  {
    if (!sPath.endsWith(File.separator)) {
      sPath = sPath + File.separator;
    }
    File dirFile = new File(sPath);
    if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
      return false;
    }
    boolean flag = true;
    
    File[] files = dirFile.listFiles();
    for (int i = 0; i < files.length; i++) {
      if (files[i].isFile())
      {
        flag = deleteFile(files[i].getAbsolutePath());
        if (!flag) {
          break;
        }
      }
      else
      {
        flag = deleteDirectory(files[i].getAbsolutePath());
        if (!flag) {
          break;
        }
      }
    }
    if (!flag) {
      return false;
    }
    if (dirFile.delete()) {
      return true;
    }
    return false;
  }
}
