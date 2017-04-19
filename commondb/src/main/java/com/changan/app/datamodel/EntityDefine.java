package com.changan.app.datamodel;

public class EntityDefine {

	public final static int TransferDeviceMetaId =1;
	public final static String TransferDevice_ID_CN = "d_1";
	public final static String TransferDevice_Name_CN = "d_2";
	public final static Long TransferDevice_StandBy = 1L;
	public final static Long TransferDevice_Run = 2L;
	public final static Long TransferDevice_Stop = 3L;
	public final static Long TransferDevice_Error = 4L;

	public final static int RobotDeviceMetaId =2;
	public final static String RobotDevice_ID_CN = "d_3";
	public final static String RobotDevice_Name_CN = "d_4";
	public final static Long RobotDevice_StandBy = 1L;
	public final static Long RobotDevice_Run = 2L;
	public final static Long RobotDevice_Stop = 3L;
	public final static Long RobotDevice_Error = 4L;
	
	public final static int ShockSandDeviceMetaId = 3;
	public final static String ShockSandDevice_ID_CN = "d_5";
	public final static String ShockSandDevice_Name_CN = "d_6";
	public final static Long ShockSandDevice_StandBy = 1L;
	public final static Long ShockSandDevice_Run = 2L;
	public final static Long ShockSandDevice_Stop = 3L;
	public final static Long ShockSandDevice_Error = 4L;

	public final static int MachineDeviceMetaId =4;
	public final static String MachineDevice_ID_CN = "d_7";
	public final static String MachineDevice_Name_CN = "d_8";
	public final static Long MachineDevice_StandBy = 1L;
	public final static Long MachineDevice_Run = 2L;
	public final static Long MachineDevice_Stop = 3L;
	public final static Long MachineDevice_Error = 4L;
	public final static Long MachineDevice_ChangeCutter = 6L;
	
	public final static int CgcDeviceMetaId = 5;
	public final static String CgcDevice_ID_CN = "d_9";
	public final static String CgcDevice_Name_CN = "d_10";
	public final static Long CgcDevice_StandBy = 1L;
	public final static Long CgcDevice_Run = 2L;
	public final static Long CgcDevice_Stop = 3L;
	public final static Long CgcDevice_Error = 4L;
	
	public final static int ErrorMetaId =8;
	public final static String Error_StartTime_CN = "d_24";
	public final static String Error_EndTime_CN = "d_25";
	public final static String Error_Code_CN = "d_26";

	public final static int StandbyMetaId =9;
	public final static String Standby_StartTime_CN = "d_27";
	public final static String Standby_EndTime_CN = "d_28";
	
	public final static int RunMetaId =10;
	public final static String Run_StartTime_CN = "d_29";
	public final static String Run_EndTime_CN = "d_30";

	public final static int SparePartsMetaId =7;
	public final static String SpareParts_Name_CN = "d_21";
	public final static String SpareParts_Style_CN = "d_22";
	public final static String SpareParts_Amount_CN = "d_23";
	public final static String SpareParts_BeginTime_CN = "d_44";
	public final static String SpareParts_RecentTime_CN = "d_45";
	public final static String SpareParts_AlarmPerent_CN = "d_46";
	public final static String SpareParts_LifeTime_CN = "d_47";
	
	public final static String Chara_LifeType = "1";
	public final static String Chara_LifeType_ProcessCount = "1";
	public final static String Chara_LifeType_UseTime = "3";
	public final static String Chara_LifeType_RunTime = "2";
	public final static String Chara_LifeUnit = "2";
	public final static String Chara_LifeUnit_Item = "5";
	public final static String Chara_LifeUnit_Day = "8";
	public final static String Chara_LifeUnit_Hour = "9";

	
	public final static int WorkpieceCleanMetaId = 11;
	public final static String WorkpieceClean_Date_CN = "d_31";
	public final static String WorkpieceClean_PieceName_CN = "d_32";
	public final static String WorkpieceClean_DeviceID_CN = "d_33";
	
	public final static int CutterMetaId = 12;
	public final static String Cutter_Code_CN = "d_34";
	public final static String Cutter_BeginTime_CN = "d_35";
	public final static String Cutter_DesignLife_CN = "d_37";
	public final static String Cutter_ProcessCount_CN = "d_38";
	
	public final static int ChangeCutterMetaId = 13;
	public final static String ChangeCutter_Code_CN = "d_39";
	public final static String ChangeCutter_BeginTime_CN = "d_40";
	public final static String ChangeCutter_ChangeTime_CN = "d_41";
	public final static String ChangeCutter_DesignLife_CN = "d_42";
	public final static String ChangeCutter_ProcessCount_CN = "d_43";

	public final static int MaintSTDMetaId = 14;
	public final static String MaintSTD_Content_CN = "d_48";
	public final static String MaintSTD_Desc_CN = "d_49";
	public final static String MaintSTD_Period_CN = "d_50";
	public final static String MaintSTD_BeginTime_CN = "d_55";

	public final static int MaintRecMetaId = 15;
	public final static String MaintRec_WorkTime_CN = "d_52";
	public final static String MaintRec_WorkerName_CN = "d_53";
	public final static String MaintRec_Note_CN = "d_54";
	
}
