package com.commondb.common;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
  private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
  public static final String ACTIVITY_FORMAT = "yyyy.MM.dd";
  public static final String ACTIVITY_FORMAT_CH = "yyyy-MM-dd";
  
  public static String formatDate(Date date, String format)
  {
    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      return sdf.format(date);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return "";
  }
  
  public static String formatDate(Date date)
  {
    return formatDate(date, "yyyy-MM-dd");
  }
  
  public static Date parseDate(String dateStr)
  {
    return parseDate(dateStr, "yyyy-MM-dd");
  }
  
  public static Date parseDate(String dateStr, String format)
  {
    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      return sdf.parse(dateStr);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static String firstDayOfMonth(Date date)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(5, 
      calendar.getActualMinimum(5));
    return formatDate(calendar.getTime(), "yyyy-MM-dd");
  }
  
  public static void main(String[] args)
  {
    System.out.println(firstDayOfMonth(new Date()));
  }
}
