package com.changan.app.rotatedegassing.datacollect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TempComConfig{

	//端口号
	private static String CommPortIdentifier="";
	//第二端口号
	private static String CommPortIdentifier2="";

	//数据位数
	private static int DATABITS=8;

	//奇偶校检
	private static int PARITY=0;

	//停止位
	private static int STOPBITS=1;

	//速率
	private static int SPEED=19200;

	//是否读取过文件
	private static boolean readfile=false;
	
	//定时读数间隔毫秒
	private static int readInterval=500;

	//命令开始标识字符串
	private static String startCmd="0104";

	//配置文件的定义
	private static String ComConfgFile="/TempComConfig.properties";


	public static void ReadFile(){
		if(readfile)return;
    	//System.out.println("读取COM配置文件相关信息:"+ComConfgFile);
    	Properties prop = new Properties();
    	try {
    		//FileInputStream fis=new FileInputStream(new File(ComConfgFile));
    		InputStream fis= TempComConfig.class.getResourceAsStream(ComConfgFile);
    		
			prop.load(fis);//载入配置文件
			try {
				String temp=prop.getProperty("CommPortIdentifier");//得以端口信息
				if(temp!=null&&!temp.trim().equals("")){
					setCommPortIdentifier(temp);
				}
				temp=prop.getProperty("DATABITS");//得到数据位数
				if(temp!=null&&!temp.trim().equals("")){
					setDATABITS(Integer.parseInt(temp));
				}
				temp=prop.getProperty("PARITY");//奇偶检验
				if(temp!=null&&!temp.trim().equals("")){
					setPARITY(Integer.parseInt(temp));
				}
				temp=prop.getProperty("SPEED");//速率
				if(temp!=null&&!temp.trim().equals("")){
					setSPEED(Integer.parseInt(temp));
				}
				temp=prop.getProperty("STOPBITS");//停止位
				if(temp!=null&&!temp.trim().equals("")){
					setSTOPBITS(Integer.parseInt(temp));
				}
				temp=prop.getProperty("Debug");//调试状态
				if(temp!=null&&!temp.trim().equals("")){
					//DBFactory.debug=true;
				}
				temp=prop.getProperty("CommPortIdentifier2");//得到第二端口信息
				if(temp!=null&&!temp.trim().equals("")){
					setCommPortIdentifier2(temp);
				}
				temp=prop.getProperty("ReadInterval");//得到读数间隔信息
				if(temp!=null&&!temp.trim().equals("")){
					setReadInterval(Integer.parseInt(temp));
				}
				temp=prop.getProperty("StartCmd");//得到读数间隔信息
				if(temp!=null&&!temp.trim().equals("")){
					setStartCmd(temp);
				}
				//System.out.println("完成读取COM信息:"+ComConfgFile);
				prop.clear();
				fis.close();
			} catch (NumberFormatException e) {
				e.printStackTrace();
				//System.out.println("读取COM信息出现数字格式错误:"+ComConfgFile);
				Reset();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			//System.out.println("COM配置文件未找到:"+ComConfgFile);
			Reset();
		} catch (IOException e) {
			e.printStackTrace();
			//System.out.println("读取COM配置文件信息出现IO错误:"+ComConfgFile);
			Reset();
		}
	}

	public static boolean getReadfile() {
		return readfile;
	}
	public static void setReadfile(boolean rf) {
		readfile = rf;
	}
	public static void Reset(){
		CommPortIdentifier="COM4";
		DATABITS=8;
		PARITY=0;
		STOPBITS=1;
		SPEED=19200;
	}


	public static String getCommPortIdentifier() {
		return CommPortIdentifier;
	}

	public static void setCommPortIdentifier(String commPortIdentifier) {
		CommPortIdentifier = commPortIdentifier;
	}

	public static int getDATABITS() {
		return DATABITS;
	}

	public static void setDATABITS(int databits) {
		DATABITS = databits;
	}

	public static int getPARITY() {
		return PARITY;
	}

	public static void setPARITY(int parity) {
		PARITY = parity;
	}

	public static int getSPEED() {
		return SPEED;
	}

	public static void setSPEED(int speed) {
		SPEED = speed;
	}

	public static int getSTOPBITS() {
		return STOPBITS;
	}

	public static void setSTOPBITS(int stopbits) {
		STOPBITS = stopbits;
	}
	/**
	 *  public static final int DATABITS_5 = 5;
	    public static final int DATABITS_6 = 6;
	    public static final int DATABITS_7 = 7;
	    public static final int DATABITS_8 = 8;
	    public static final int PARITY_NONE = 0;
	    public static final int PARITY_ODD = 1;
	    public static final int PARITY_EVEN = 2;
	    public static final int PARITY_MARK = 3;
	    public static final int PARITY_SPACE = 4;
	    public static final int STOPBITS_1 = 1;
	    public static final int STOPBITS_2 = 2;
	    public static final int STOPBITS_1_5 = 3;
	    public static final int FLOWCONTROL_NONE = 0;
	    public static final int FLOWCONTROL_RTSCTS_IN = 1;
	    public static final int FLOWCONTROL_RTSCTS_OUT = 2;
	    public static final int FLOWCONTROL_XONXOFF_IN = 4;
	    public static final int FLOWCONTROL_XONXOFF_OUT = 8;

	 */

	public static String getCommPortIdentifier2() {
		return CommPortIdentifier2;
	}

	public static void setCommPortIdentifier2(String commPortIdentifier2) {
		CommPortIdentifier2 = commPortIdentifier2;
	}

	/**
	 * @return the readInterval
	 */
	public static int getReadInterval() {
		return readInterval;
	}

	/**
	 * @param readInterval the readInterval to set
	 */
	public static void setReadInterval(int readInterval) {
		TempComConfig.readInterval = readInterval;
	}

	/**
	 * @return the startCmd
	 */
	public static String getStartCmd() {
		return startCmd;
	}

	/**
	 * @param startCmd the startCmd to set
	 */
	public static void setStartCmd(String startCmd) {
		TempComConfig.startCmd = startCmd;
	}
}
