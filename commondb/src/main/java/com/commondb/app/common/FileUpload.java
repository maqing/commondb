package com.commondb.app.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileUpload
{
  public static void upload(String ftpServer, String user, String password, String fileName, File source)
    throws MalformedURLException, IOException
  {
    if ((ftpServer != null) && (fileName != null) && (source != null))
    {
      StringBuffer sb = new StringBuffer("ftp://");
      if ((user != null) && (password != null))
      {
        sb.append(user);
        sb.append(':');
        sb.append(password);
        sb.append('@');
      }
      sb.append(ftpServer);
      sb.append('/');
      sb.append(fileName);




      sb.append(";type=i");

      BufferedInputStream bis = null;
      BufferedOutputStream bos = null;
      try
      {
        URL url = new URL(sb.toString());
        URLConnection urlc = url.openConnection();

        bos = new BufferedOutputStream(urlc.getOutputStream());
        bis = new BufferedInputStream(new FileInputStream(source));
        int i;
        while ((i = bis.read()) != -1)
        {
          bos.write(i);
        }
      }
      finally
      {
        if (bis != null) {
          try
          {
            bis.close();
          }
          catch (IOException ioe)
          {
            ioe.printStackTrace();
          }
        }
        if (bos != null) {
          try
          {
            bos.close();
          }
          catch (IOException ioe)
          {
            ioe.printStackTrace();
          }
        }
      }
      try
      {
        bos.close();
      }
      catch (IOException ioe)
      {
        ioe.printStackTrace();
      }
    }
    else
    {
      System.out.println("Input not available.");
    }
  }

  public static void download(String ftpServer, String user, String password, String fileName, File destination)
    throws MalformedURLException, IOException
  {
    if ((ftpServer != null) && (fileName != null) && (destination != null))
    {
      StringBuffer sb = new StringBuffer("ftp://");
      if ((user != null) && (password != null))
      {
        sb.append(user);
        sb.append(':');
        sb.append(password);
        sb.append('@');
      }
      sb.append(ftpServer);
      sb.append('/');
      sb.append(fileName);




      sb.append(";type=i");
      BufferedInputStream bis = null;
      BufferedOutputStream bos = null;
      try
      {
        URL url = new URL(sb.toString());
        URLConnection urlc = url.openConnection();

        bis = new BufferedInputStream(urlc.getInputStream());


        FileOutputStream os = new FileOutputStream(destination);
        byte[] bytes = new byte[1024];
        int i;
        while ((i = bis.read(bytes)) != -1)
        {
          os.write(bytes, 0, i);
        }
      }
      finally
      {
        if (bis != null) {
          try
          {
            bis.close();
          }
          catch (IOException ioe)
          {
            ioe.printStackTrace();
          }
        }
        if (bos != null) {
          try
          {
            bos.close();
          }
          catch (IOException ioe)
          {
            ioe.printStackTrace();
          }
        }
      }
      try
      {
        bos.close();
      }
      catch (IOException ioe)
      {
        ioe.printStackTrace();
      }
    }
    else
    {
      System.out.println("Input not available");
    }
  }

  public static void main(String[] args)
  {
    try
    {
      download("localhost", "test", "test", "upload/fei.jpg", new File("D:/tools/apache-tomcat-6.0.18/webapps/ROOT/upload/mm.jpg"));
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
