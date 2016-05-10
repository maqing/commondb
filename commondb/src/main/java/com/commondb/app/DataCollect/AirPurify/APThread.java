package com.commondb.app.DataCollect.AirPurify;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.commondb.app.DataCollect.AirPurify.bo.APPLCData;
import com.commondb.app.DataCollect.AirPurify.service.PLCDataService;

public class APThread extends Thread {  
	
	static Logger logger = Logger.getLogger(APThread.class);

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    private static StringBuffer sb=new StringBuffer();
	private  Enumeration portList;
	private  CommPortIdentifier portId;
	public  SerialPort serialPort;
	public final int SPEED=1200;
	public boolean run=true;
	public final int DATABITS=8;
	public final int STOPBITS=1;
	public static String idKard="";
	public static String idKard2="";
	public static double tizhong=0;
	public static int d=0;
	public static double tizhong2=0;
	public final int PARITY=SerialPort.PARITY_NONE;
	
	//保存加工记录，避免每次从数据库中查询定位最新记录后再更新
	public static Map<String, Object> apRecValueMap = new HashMap<String, Object>();
	private PLCDataService plcDataService;
	
    public APThread(PLCDataService plcDataService) {
    	this.plcDataService = plcDataService;
	}
	public void Connection(){

    	portList = CommPortIdentifier.getPortIdentifiers();
    	 //System.out.println(ComConfig.getCommPortIdentifier());
        while (portList.hasMoreElements())
        {
            portId = (CommPortIdentifier) portList.nextElement();
            //System.out.println("CommPortIdentifier.PORT_SERIAL="+ CommPortIdentifier.PORT_SERIAL +"               portId.getPortType()= "+portId.getPortType());
            //  System.out.println("\n\n检测到端口:"+portId.getName());
            /* 如果端口类型是串口，则打印出其端口信息 */
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL)//comm
            {
            	 //       	System.out.println("\n\n检测到端口:"+portId.getName());
            	System.out.println("ComConfig.getCommPortIdentifier2()="+ComConfig.getCommPortIdentifier2()+" portId.getName()=  "+portId.getName());
                if(portId.getName().equals(ComConfig.getCommPortIdentifier2())){//name
            	        	System.out.println("\n\n检测到端口:"+portId.getName());
                	break;
                }else{
                	portId=null;
                }
            }
        }
    }
    public void run(){
    	try {
			if(portId!=null){
				serialPort = (SerialPort)portId.open("Serial_Communication", 1000);
				try {
					serialPort.setSerialPortParams(ComConfig.getSPEED(),
							ComConfig.getDATABITS(), ComConfig.getSTOPBITS(),
							ComConfig.getPARITY());
				} catch (UnsupportedCommOperationException e) {
                	logger.error("串口设置失败:"+ e.toString());
					e.printStackTrace();
				}

				InputStream in = serialPort.getInputStream();
				byte[] bs=new byte[60];

				while(true){
					Thread.sleep(1000);
					//读取接收的字符串
					int count=in.read(bs);
					//System.out.println(count);
					//System.out.println("count = "+ count );
					//将数据字符串存入sb
					for(int i=0;i<count;i++){
						sb.append(byteToHexString(bs[i]));
					}
					//System.out.println(sb);

					//没有约定开始字符串
					//找到字符串开始index
					//int str=sb.indexOf("02");//
					//如果没有指定字符串
					//if(str==0){
						//如果字符串在首位 查找字符串结束符
						int end=sb.indexOf("0D 0A");
						if(end>0){
							//如果结束符位置正确，则把有效数据值取出
							logger.debug("接收到数据: "+ sb.substring(0,end+5));
							setPLCValue(sb.substring(0,end));
							//删掉该段数据
							sb.delete(0,end+5);
						} else if(end==0) {
							//接收到空结束符
							sb.delete(0,5);
						}
					//} else if (str>0) {
					//	sb.delete(0,str);
					//}

				}
			}
		} catch (PortInUseException e) {
        	logger.error("串口正在使用:"+ e.toString());
			//e.printStackTrace();
		} catch (IOException e) {
        	logger.error("IO失败:"+ e.toString());
			//e.printStackTrace();
		} catch(Exception e){
        	logger.error("失败:"+ e.toString());
			//e.printStackTrace();
		}finally{
			if(serialPort!=null){
				serialPort.close();
				sb.delete(0,sb.length());
				//APThread apThread1 =new APThread();
				//apThread1.Connection();
				//apThread1.start();
			}
		}
    }

    private static void setValue(String v){

    	//把字数传节成字符串段
    	String[] values=v.split("\\s+");

		if(values.length==12) {

			byte[] bs=new byte[12];
			for(int i=0;i<12;i++) {
				bs[i]=getByte(values[i]);
			}
			byte check=107;

			for(int i=0;i<12;i++) {
				//^=　按位异或赋值
				check^=bs[i];
			}

			byte[] vs= {bs[6],bs[7],bs[8],bs[9],bs[10]};

			//数据正确
			if(check==0) {
				double d=Double.parseDouble(new String(vs))/100;

				//体重值大于10
				if(d>=10) {

					String kard=v.substring(0,11);
					if(!idKard2.equals(kard)){
						idKard2=kard;
						idKard=kard;
						tizhong2=0;
					}

					tizhong=d;

					System.out.println("体重:"+d+" 号:"+v.substring(0,11));

				}else {
					idKard="";
					tizhong=0;
					tizhong2=0;
					System.out.println("不正确的体重："+d);
				}
			}else {
				System.out.println("连称数据验证不正确。");
			}
		}
	}

    /* maqing 2014-06-09 add
     * 解析 赛康体重接口数据
     * */
    private static void setSecaValue(String v){

    	//把字数传节成字符串段
    	String[] values=v.split("\\s+");

		if(values.length==18) {

			byte[] bs=new byte[values.length];
			for(int i=0;i<values.length;i++) {
				bs[i]=getByte(values[i]);
			}
			byte check=107;

			for(int i=0;i<values.length;i++) {
				//^=　按位异或赋值
				check^=bs[i];
			}
			/*
			int KK_0 = (bs[1]&0xf0)>>>4;
			int KK_1 = (bs[1]&0x0f);
			int BCD_0 = (bs[2]&0xf0)>>>4;
			int BCD_1 = (bs[2]&0x0f);
			int BCD_2 = (bs[3]&0xf0)>>>4;
			int BCD_3 = (bs[3]&0x0f);
			int BCD_4 = (bs[4]&0xf0)>>>4;
			int DISPTYPE = (bs[4]&0x0f);
			*/
			String KK_0 = String.valueOf((char)bs[1]);
			int KK_1 = Integer.parseInt(String.valueOf((char)bs[2]));
			int BCD_0 = Integer.parseInt(String.valueOf((char)bs[3]));
			int BCD_1 = Integer.parseInt(String.valueOf((char)bs[4]));
			int BCD_2 = Integer.parseInt(String.valueOf((char)bs[5]));
			int BCD_3 = Integer.parseInt(String.valueOf((char)bs[6]));
			int BCD_4 = Integer.parseInt(String.valueOf((char)bs[7]));
			int DISPTYPE = Integer.parseInt(String.valueOf((char)bs[8]));




			//数据正确
			//if(check==0) {
				double weight = 0;
				if (KK_1==1) {
					//稳定体重
					if (DISPTYPE==0) {
						//"0": adult weighing scale, kg.g kkk.gg
						weight =  BCD_0*100 + BCD_1*10 + BCD_2 + (double)BCD_3/10 + (double)BCD_4/100;
					} else if (DISPTYPE==1) {
						//"1": adult weighing scale, kg.g kkk.g
						weight =  BCD_1*100 + BCD_2*10 + BCD_3 + (double)BCD_4/10 ;
					} else if (DISPTYPE==2) {
						//"2": baby weighing scale, kg.g kk.ggg
						weight = BCD_0*10 + BCD_1* + (double)BCD_2/10 + (double)BCD_3/100 + (double)BCD_4/1000;
					} else {
						System.out.println("不支持该显示模式");
						logger.info("不支持该显示模式 : " + DISPTYPE);
					}

				}

				//体重值大于10
				if(weight>=10) {
					idKard=idKard2;
					tizhong=weight;
					System.out.println("体重:"+tizhong+" 号:" + idKard);
					logger.info("体重:"+tizhong+" 号:" + idKard);
				}else if (weight>=2){
					idKard="";
					tizhong=0;
					tizhong2=0;
					System.out.println("不正确的体重："+weight);
					logger.info("不正确的体重："+weight);
				}
			//}else {
			//	System.out.println("连称数据验证不正确。");
			//}
		}
	}

    /* maqing 2015-10-20 add
     * 解析PLC数据
     * */
    private void setPLCValue(String v){
     try {
    	//把字数传节成字符串段
    	String[] values=v.trim().split("\\s+");
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<values.length;i++) {
			sb.append((char) getByte(values[i]));
		}
		logger.info("解析成功："+sb.toString().trim());
		String[] data=sb.toString().split(";",-1);
		if (data.length==6) {
			APPLCData apPLCData = new APPLCData();
			try {
				if (!data[0].equals("")) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
					//System.out.println("----"+ data[0]+ "----"+data[0].equals("2015/8/28 14:49:04"));
					apPLCData.setPlcTime(sdf.parse(data[0]));
				}
			} catch (Exception e) { logger.info(e.toString()); }
			try {
				if (!data[1].equals("")) {
					apPLCData.setDeviceStartFlag(data[1]);
				}
			} catch (Exception e) {}
			try {
				if (!data[2].equals("")) {
					apPLCData.setApStartFlag(data[2]);
				}
			} catch (Exception e) {}
			try {
				if (!data[3].equals("")) {
					apPLCData.setEngineSpeed(Double.parseDouble(data[3]));
				}
			} catch (Exception e) {}
			try {
				if (!data[4].equals("")) {
					apPLCData.setAlTemperature(Double.parseDouble(data[4]));
				}
			} catch (Exception e) {}
			try {
				if (!data[5].equals("")) {
					apPLCData.setAlarmFlag(data[5]);
				}
			} catch (Exception e) {}
			logger.info("解析成功："+apPLCData.toString());
			//判断缓存是否有加工记录，否则读取最后一条未结束记录
			if (!apRecValueMap.containsKey("id")) {
				apRecValueMap = plcDataService.getLastNotFinishAPRec();
			}
			apRecValueMap = plcDataService.saveAPPLCData(apPLCData, apRecValueMap);
		}
     } catch(Exception e) {
    	 logger.error(e.toString());
     }
		
	}


	private static int getInt(String s){
		int i=-1;
		if(!s.equals("")){
			try {
				i=Integer.parseInt(s, 16);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2]+" ";
    }
	public static byte getByte(String s){
		return (byte)getInt(s);
	}

    public static byte[] getBooleanArray(byte b) {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte)(b & 1);
            b = (byte) (b >> 1);
        }
        return array;
    }

    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }

}

/*
    public void run() {  
        while (!this.isInterrupted()) {// 线程未中断执行循环  
            try {  
                Thread.sleep(2000); //每隔2000ms执行一次  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
              
//           ------------------ 开始执行 ---------------------------  
            System.out.println("TIME:" + System.currentTimeMillis());  
        }  
    }  
}  */