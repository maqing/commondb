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
  
  public static String toDHMS(String secStr) {
	  long sec = Long.parseLong(secStr);
	  int ss = 1;
	  int mi = ss * 60;
	  int hh = mi * 60;
	  int dd = hh * 24;

	  long day = sec / dd;
	  long hour = (sec - day * dd) / hh;
	  long minute = (sec - day * dd - hour * hh) / mi;
	  long second = (sec - day * dd - hour * hh - minute * mi) / ss;
	 // long milliSecond = sec - day * dd - hour * hh - minute * mi - second * ss;

	  //String strDay = day < 10 ? "0" + day : "" + day;
	  String strDay = "" + day;
	  String strHour = hour < 10 ? "0" + hour : "" + hour;
	  String strMinute = minute < 10 ? "0" + minute : "" + minute;
	  String strSecond = second < 10 ? "0" + second : "" + second;
	 // String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;
	 // strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
	  return strDay + "天" + strHour + "时" + strMinute + "分" + strSecond + "秒";	  
  }

  public static String toHMS(String secStr) {
	  long sec = Long.parseLong(secStr);
	  int ss = 1;
	  int mi = ss * 60;
	  int hh = mi * 60;
	  int dd = hh * 24;

	  long day = sec / dd;
	  day = 0;
	  long hour = (sec - day * dd) / hh;
	  long minute = (sec - day * dd - hour * hh) / mi;
	  long second = (sec - day * dd - hour * hh - minute * mi) / ss;
	 // long milliSecond = sec - day * dd - hour * hh - minute * mi - second * ss;

	  //String strDay = day < 10 ? "0" + day : "" + day;
	  //String strHour = hour < 10 ? "0" + hour : "" + hour;
	  String strHour = "" + hour;
	  String strMinute = minute < 10 ? "0" + minute : "" + minute;
	  String strSecond = second < 10 ? "0" + second : "" + second;
	 // String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;
	 // strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
	  return strHour + "时" + strMinute + "分" + strSecond + "秒";	  
  }
  
  
  public static void main(String[] args)
  {
    System.out.println(firstDayOfMonth(new Date()));
  }
  
  
  
}
