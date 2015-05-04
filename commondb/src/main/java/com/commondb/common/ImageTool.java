package com.commondb.common;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import javax.imageio.ImageIO;

public class ImageTool
{
  public static void createPreviewImge(File imgFile)
  {
    try
    {
      File fi = imgFile;
      File fo = new File(imgFile + "_p.jpg");
      
      AffineTransform transform = new AffineTransform();
      BufferedImage bis = ImageIO.read(fi);
      
      int w = bis.getWidth();
      System.out.println(w);
      int h = bis.getHeight();
      System.out.println(h);
      double scale = w / h;
      
      int nw = 120;
      int nh = nw * h / w;
      if (nh > 120)
      {
        nh = 120;
        nw = nh * w / h;
      }
      double sx = nw / w;
      double sy = nh / h;
      
      transform.setToScale(sx, sy);
      
      AffineTransformOp ato = new AffineTransformOp(transform, null);
      BufferedImage bid = new BufferedImage(nw, nh, 
        5);
      ato.filter(bis, bid);
      ImageIO.write(bid, "jpeg", fo);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
