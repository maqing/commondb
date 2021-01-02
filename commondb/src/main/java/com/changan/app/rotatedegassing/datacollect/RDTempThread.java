package com.changan.app.rotatedegassing.datacollect;

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

import com.changan.app.rotatedegassing.service.TempDataService;
import com.commondb.app.DataCollect.AirPurify.bo.APPLCData;
import com.commondb.app.DataCollect.AirPurify.service.PLCDataService;

public class RDTempThread extends Thread {  
	
	static Logger logger = Logger.getLogger(RDTempThread.class);

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
	public static int d=0;
	public final int PARITY=SerialPort.PARITY_NONE;
	
	//保存加工记录，避免每次从数据库中查询定位最新记录后再更新
	public static Map<String, Object> apRecValueMap = new HashMap<String, Object>();
	private TempDataService tempDataService;
	
    public RDTempThread(TempDataService tempDataService) {
    	this.tempDataService = tempDataService;
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
            	System.out.println("ComConfig.getCommPortIdentifier2()="+TempComConfig.getCommPortIdentifier2()+" portId.getName()=  "+portId.getName());
                if(portId.getName().equals(TempComConfig.getCommPortIdentifier2())){//name
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
					serialPort.setSerialPortParams(TempComConfig.getSPEED(),
							TempComConfig.getDATABITS(), TempComConfig.getSTOPBITS(),
							TempComConfig.getPARITY());
				} catch (UnsupportedCommOperationException e) {
                	logger.error("串口设置失败:"+ e.toString());
					e.printStackTrace();
				}

				InputStream in = serialPort.getInputStream();
				byte[] bs=new byte[60];
				//因为 会有3次重发，剔除重复
				String oldDataStr = "";
				long oldTime = System.currentTimeMillis();
				while(true){
					Thread.sleep(TempComConfig.getReadInterval());
					//读取接收的字符串
					int count=in.read(bs);
					//System.out.println(count);
					//System.out.println("count = "+ count );
					//将数据字符串存入sb
					for(int i=0;i<count;i++){
						sb.append(byteToHexString(bs[i]));
					}
					//System.out.println(sb);

					//约定开始字符串
					//找到字符串开始index
					int str=sb.indexOf(TempComConfig.getStartCmd());//
					if(str==0){
						//找数据长度
						//如果没有不够，继续读数据
						if (sb.length()<6) continue;
						int tempDataLen=getInt(sb.substring(4, 6))*2;
						//System.out.println(sb.substring(4, 6) + " " + getInt(sb.substring(4, 6)));
						//System.out.println("sb:" + sb + " datalen:" + tempDataLen);
						//6位+数据+4位CRC校验字符串,如果不够，继续
						if (sb.length()<(6+tempDataLen+4)) continue;
						Double tempValue=Double.parseDouble(String.valueOf(getInt(sb.substring(6, tempDataLen+6)))) / 10;
						String dataStr = sb.substring(0,tempDataLen+6);
						long currentTime = System.currentTimeMillis();
						String crcStr = sb.substring(tempDataLen+6,tempDataLen+10);
						System.out.println("dataStr:" + dataStr + " crcStr:" + crcStr);
						if (crcStr.equals(CRC16_Check(toBytes(dataStr)))) {
							//logger.debug("接收到数据: "+ dataStr + crcStr);
							if (!(oldDataStr.equals(dataStr)) || (currentTime-oldTime)>(6000+TempComConfig.getReadInterval())) {
								//不重复数据，才处理
								logger.debug("接收到有效数据 new: "+ dataStr + " old: " +  oldDataStr + " currentTime: " + currentTime + " oldTime: " + oldTime);
								tempDataService.saveTempData(tempValue);
							}
							oldDataStr = dataStr;
							oldTime = currentTime;
						} else {
							logger.debug("接收到数据错误: "+ dataStr + crcStr + " CRC校验失败");
						}
 						
						//删掉该段数据
						sb.delete(0,tempDataLen+9);
					} else if (str>0) {
						sb.delete(0,str);
					}

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
        return hexDigits[d1] + hexDigits[d2];
    }
	public static byte getByte(String s){
		return (byte)getInt(s);
	}
	
	 public static byte[] toBytes(String str) { 
		 if(str == null || str.trim().equals("")) { return new byte[0]; } 
		 byte[] bytes = new byte[str.length() / 2]; 
		 for(int i = 0; i < str.length() / 2; i++){ 
			String subStr = str.substring(i * 2, i * 2 + 2); 
		 	bytes[i] = (byte) Integer.parseInt(subStr, 16); 
		 	} 
		 return bytes; 
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
    

    public static String CRC16_Check(byte Pushdata[])
    {
        int Reg_CRC=0xffff;
        int temp;
        int i,j;


        for( i = 0; i<Pushdata.length; i ++)
        {
            temp = Pushdata[i];
            if(temp < 0) temp += 256;
            temp &= 0xff;
            Reg_CRC^= temp;


            for (j = 0; j<8; j++)
            {
                if ((Reg_CRC & 0x0001) == 0x0001)
                    Reg_CRC=(Reg_CRC>>1)^0xA001;
                else
                    Reg_CRC >>=1;
            }
        }
        //高低位转换，看情况使用
        Reg_CRC = ( (Reg_CRC & 0x0000FF00) >> 8) | ( (Reg_CRC & 0x000000FF ) << 8);
        return Integer.toHexString((Reg_CRC&0xffff)).toUpperCase();
    }
    
    
   public static void main(String args[]) {
        //字符串转16进制byte数组
        String str16 = "0104021FBC";
        byte[] bytes = toBytes(str16);
        System.out.println("str16:" + str16);

        System.out.println(CRC16_Check(bytes));
    }  
}

