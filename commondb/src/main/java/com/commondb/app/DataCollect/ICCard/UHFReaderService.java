package com.commondb.app.DataCollect.ICCard;

/*
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.misc.basicStructures.LONG;
*/
import com.sun.jna.Library;  
import com.sun.jna.Native;
import com.sun.jna.Platform;


public class UHFReaderService {
	/*
    static JNative ReaderApi = null;  
    
    public String openCommPort() throws NativeException, IllegalAccessException {  
        try {  
            if (ReaderApi == null) {  
                // 1. 利用org.xvolks.jnative.JNative来加载DLL：参数1.PegRoute为类名  
                // 2.HCTInitEx方法名  
            	ReaderApi = new JNative("ReaderApi", "OpenCommPort");  
  
                // 2.设置要调用方法中的参数：0 表示第一个以此类推  
                //LONG versionLong = new LONG(10);  
                //versionLong.setValue(0);  
  
                ReaderApi.setParameter(0, Type.STRING, "COM7");  
                ReaderApi.setParameter(1, Type.INT, "9600");  
  
                // 3.设置返回参数的类型  
                ReaderApi.setRetVal(Type.INT);  
                // 4.执行方法  
                ReaderApi.invoke();// 调用方法  
            }  
            System.out.println("调用的DLL文件名为：" + ReaderApi.getDLLName());  
            System.out.println("调用的方法名为：" + ReaderApi.getFunctionName());  
            // 5.返回值  
            return ReaderApi.getRetVal();  
        } finally {  
            if (ReaderApi != null) {  
                // 6.释放系统资源  
            	ReaderApi.dispose();  
            }  
        }  
    }  
    
	public static void main(String[] args) throws NativeException, IllegalAccessException{
		// TODO Auto-generated method stub
		String mm = new UHFReaderService().openCommPort();
        System.out.println(mm);

	}
	*/

	public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary)
            Native.loadLibrary("msvcrt", CLibrary.class);

        void printf(String format, Object... args);
    }	
	
    public interface ReaderApi extends Library {  
    	  
        // 2.ReaderApi.dll 中 openCommPort方法 
    	public int OpenCommPort(String strPort, int nBoud);
    	
    	public int TcpConnectReader(String ip, int port);
    	
    	public int TcpCloseConnect();
    } 	
	
	public static void main(String[] args){
		CLibrary temp = CLibrary.INSTANCE;
		CLibrary.INSTANCE.printf("Hello, World\n");
        for (int i=0;i < args.length;i++) {
            CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
        }		
		
		// TODO Auto-generated method stub
		ReaderApi epen = (ReaderApi) Native.loadLibrary("ReaderApi", ReaderApi.class);
        if (epen != null) {  
            System.out.println("DLL加载成功！");  
            //int success = epen.OpenCommPort("COM7",115200);  
            int success = epen.TcpConnectReader("192.168.0.10",80);  
            //int success = epen.TcpCloseConnect();  
            System.out.println("1.设备初始化信息！" + success);
        }
	}

}
