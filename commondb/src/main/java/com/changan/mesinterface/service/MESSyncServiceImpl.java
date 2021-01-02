package com.changan.mesinterface.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.changan.app.datamodel.EntityDefine;
import com.changan.mesinterface.dao.DegasserdensitydataDAO;
import com.changan.mesinterface.dao.DegasserprocdataDAO;
import com.changan.mesinterface.model.Degasserdensitydata;
import com.changan.mesinterface.model.DegasserdensitydataExample;
import com.changan.mesinterface.model.Degasserprocdata;
import com.changan.mesinterface.model.DegasserprocdataExample;
import com.commondb.app.DataCollect.tools.ReadCardCache;
import com.commondb.db.dao.DynamicEntityDAO;
import com.commondb.db.service.EntityService;

public class MESSyncServiceImpl implements MESSyncService {
	
	static Logger logger = Logger.getLogger(MESSyncServiceImpl.class);
	

	private DegasserprocdataDAO degasserprocdataDAO;
	private DegasserdensitydataDAO degasserdensitydataDAO;
	private DynamicEntityDAO dynEntityDAO;


	/**
	 * @return the degasserprocdataDAO
	 */
	public DegasserprocdataDAO getDegasserprocdataDAO() {
		return degasserprocdataDAO;
	}

	/**
	 * @param degasserprocdataDAO the degasserprocdataDAO to set
	 */
	public void setDegasserprocdataDAO(DegasserprocdataDAO degasserprocdataDAO) {
		this.degasserprocdataDAO = degasserprocdataDAO;
	}


	public DegasserdensitydataDAO getDegasserdensitydataDAO() {
		return degasserdensitydataDAO;
	}

	public void setDegasserdensitydataDAO(
			DegasserdensitydataDAO degasserdensitydataDAO) {
		this.degasserdensitydataDAO = degasserdensitydataDAO;
	}

	public DynamicEntityDAO getDynEntityDAO() {
		return dynEntityDAO;
	}

	public void setDynEntityDAO(DynamicEntityDAO dynEntityDAO) {
		this.dynEntityDAO = dynEntityDAO;
	}

	@Override
	public String insertData(Integer metaId, String entityId, Map valuesMap) {
		try {
			if (!checkNeedProc(metaId)) return null;
			if (metaId==EntityDefine.RPMetaId) {
				//除气机数据
				insertDPItem(metaId,entityId,valuesMap);
				//if (dgProcDataItem.getfIssync()==0) 
				//	degasserprocdataDAO.insert(dgProcDataItem);
			}
			if (metaId==EntityDefine.DensityIndexMetaId) {
				//密度当量仪数据
				Degasserdensitydata dgDensityDataItem = parseMapToDensityItem(metaId,entityId,valuesMap);
				degasserdensitydataDAO.insert(dgDensityDataItem);
			}
		} catch (Exception e) {
			logger.error("sync insert error: meteId-" + metaId + ",entityId-" + entityId + ", "+ e.toString());
		}
		return "1";
	}

	@Override
	public String updateData(Integer metaId, String entityId, Map valuesMap) {
		try {
			if (!checkNeedProc(metaId)) return null;
			if (metaId==EntityDefine.RPMetaId) {
				//除气机数据 
				DegasserprocdataExample procDataExample = new DegasserprocdataExample();
				DegasserprocdataExample.Criteria criterial = procDataExample.createCriteria();
				criterial.andEntityidEqualTo(entityId);
				List procDataList =  degasserprocdataDAO.selectByExample(procDataExample);
				if ((procDataList!=null) && (procDataList.size()==0))  {
					//没有数据, 插入记录
					insertDPItem(metaId,entityId,valuesMap);
				}
				return "1";
			}
			
			if (metaId==EntityDefine.DensityIndexMetaId) {
				//密度当量仪数据
				DegasserdensitydataExample densityExample = new DegasserdensitydataExample();
				DegasserdensitydataExample.Criteria criterial = densityExample.createCriteria();
				criterial.andEntityidEqualTo(entityId);
				List densityList =  degasserdensitydataDAO.selectByExample(densityExample);
				if ((densityList!=null)&& (densityList.size()>0))  {
					Degasserdensitydata densityOldItem = (Degasserdensitydata) densityList.get(0);
					if (densityOldItem.getfIssync().equals("0")) {
						Degasserdensitydata dgDensityDataItem = parseMapToDensityItem(metaId,entityId,valuesMap);
						dgDensityDataItem.setfId(densityOldItem.getfId());
						degasserdensitydataDAO.updateByPrimaryKey(dgDensityDataItem);
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("sync update error: meteId-" + metaId + ",entityId-" + entityId + ", "+ e.toString());
		}
		return "1";
	}

	@Override
	public String deleteData(Integer metaId, String entityId) {
		try {
			if (!checkNeedProc(metaId)) return null;
			if (metaId==EntityDefine.RPMetaId) {
				//除气机数据 不更新接口
				return "1";
			}
			
			if (metaId==EntityDefine.DensityIndexMetaId) {
				//密度当量仪数据
				DegasserdensitydataExample densityExample = new DegasserdensitydataExample();
				DegasserdensitydataExample.Criteria criterial = densityExample.createCriteria();
				criterial.andEntityidEqualTo(entityId);
				List densityList =  degasserdensitydataDAO.selectByExample(densityExample);
				if ((densityList!=null)&& (densityList.size()>0))  {
					Degasserdensitydata densityOldItem = (Degasserdensitydata) densityList.get(0);
					if (densityOldItem.getfIssync().equals("0")) {
						degasserdensitydataDAO.deleteByPrimaryKey(densityOldItem.getfId());
					}
				}
			}
		} catch (Exception e) {
			logger.error("sync delete error: meteId-" + metaId + ",entityId-" + entityId + ", "+ e.toString());
		}
		return "1";
	}
	
	private boolean checkNeedProc(Integer metaId) {
		if ((metaId.intValue()==EntityDefine.RPMetaId) || (metaId.intValue()==EntityDefine.DensityIndexMetaId)) {
			return true;
		} else {
			return false;
		}
	}

	private void insertDPItem(Integer metaId, String entityId, Map valuesMap) {
		//改成直接插入多条数据
		try {
			Object endTimeObj = valuesMap.get(EntityDefine.RP_EndTime_CN);
			String endTimeStr = "";
			if (endTimeObj!=null) {
				endTimeStr = endTimeObj.toString();
				if (endTimeStr.trim().length()==0) {
					//没有结束时间不发送数据
					return ;
				}
			}
		
			Object deviceIDObj = valuesMap.get(EntityDefine.RP_DeviceID_CN);
			String deviceIDStr = "";
			if (deviceIDObj!=null) {
				deviceIDStr = deviceIDObj.toString();
			}
			
			Object beginTimeObj = valuesMap.get(EntityDefine.RP_BeginTime_CN);
			String beginTimeStr = "";
			if (beginTimeObj!=null) {
				beginTimeStr = beginTimeObj.toString();
			}
			
			//计算除气时间
			double treatmentTime = 0;
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			DateFormat rfIDdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			try {
				//分钟单位
				treatmentTime = (df.parse(endTimeStr).getTime()-df.parse(beginTimeStr).getTime())/(1000*60);
			} catch (Exception e) {}
			
			Object rfidNOObj = valuesMap.get(EntityDefine.RP_RFIDNO_CN);
			String rfidNOStr = "";
			if (rfidNOObj!=null) {
				rfidNOStr= rfidNOObj.toString();
			}
			//计算静置时间
			double keepTime = 0;
			/*
			//查询RFID开始和离开时间,以除气结束时间后第一条RFID记录为准（RFID重复使用）
			List rfidDataList = dynEntityDAO.dynSelect("id," + EntityDefine.RPRFID_EnterTime_CN + ","
					+ EntityDefine.RPRFID_LeaveTime_CN , 
					"t_entity_"+String.valueOf(EntityDefine.RPRFIDMetaId), 
					"1=1 and " + EntityDefine.RPRFID_RFIDNO_CN + "='" + rfidNOStr + "' " 
					+ " and create_time>'" + endTimeStr 
					+ "' order by " + EntityDefine.RPRFID_EnterTime_CN );
			*/

			//查询RFID开始和离开时间,以除气开始时间后第一条RFID记录为准（RFID重复使用）
			List rfidDataList = dynEntityDAO.dynSelect("id," + EntityDefine.RPRFID_EnterTime_CN + ","
					+ EntityDefine.RPRFID_LeaveTime_CN , 
					"t_entity_"+String.valueOf(EntityDefine.RPRFIDMetaId), 
					"1=1 and " + EntityDefine.RPRFID_RFIDNO_CN + "='" + rfidNOStr + "' " 
					+ " and create_time>'" + beginTimeStr 
					+ "' order by " + EntityDefine.RPRFID_EnterTime_CN );			
			
			if ((rfidDataList!=null)&& (rfidDataList.size()>0)) {
				Map<String, String> rfidRecMap = (Map<String, String>) rfidDataList.get(0);
				Object enterTimeObj = rfidRecMap.get(EntityDefine.RPRFID_EnterTime_CN);
				Object leaveTimeObj = rfidRecMap.get(EntityDefine.RPRFID_LeaveTime_CN);
				try {
					// keepTime = (rfIDdf.parse(leaveTimeObj.toString()).getTime()-rfIDdf.parse(enterTimeObj.toString()).getTime())/(1000*60);
					// 除气结束时间 - 扫描离开时间
					keepTime = (df.parse(endTimeStr).getTime()-rfIDdf.parse(enterTimeObj.toString()).getTime())/(1000*60);
				}catch (Exception e) {
					logger.error("sync delete error: 计算静置时间错误, "+ e.toString());
				}
			}
			
			
			//查询时间段内所有工艺数据，计算转速、流量、压力、参数平均值
			//压力设为标准值2.5bar
			double sumSpeed=0;
			double avgSpeed=0;
			int countSpeed=0;
			double sumFlow=0;
			double avgFlow=0;
			int countFlow=0;
			double sumTemp=0;
			double avgTemp=0;
			int countTemp=0;
			
			double tempSpeed = 0;
			double tempFlow = 0;
			
			List plcDataList = dynEntityDAO.dynSelect("id," + EntityDefine.RPPLCData_RotateSpeed_CN + ","
					+ EntityDefine.RPPLCData_N2FlowRate_CN + "," + EntityDefine.RPPLCData_RefiningTemp_CN , 
					"t_entity_"+String.valueOf(EntityDefine.RPPLCDataMetaId), 
					"1=1 and " + EntityDefine.RPPLCData_PLCTime_CN + "<='" + endTimeStr 
					+"' and " + EntityDefine.RPPLCData_PLCTime_CN + ">='" + beginTimeStr 
					+"' and " + EntityDefine.RPPLCData_DeviceID_CN + "='" + deviceIDStr + "' " );
			if ((plcDataList!=null)&& (plcDataList.size()>0)) {
				for (int i=0;i<plcDataList.size();i++) {
					Map<String, String> plcDataMap =  (Map<String, String>) plcDataList.get(i);
					try {
						String speedStr =  plcDataMap.get(EntityDefine.RPPLCData_RotateSpeed_CN).trim();
						if (isNumber(speedStr)) {
							tempSpeed= Double.parseDouble(speedStr);
							//有异常数据，需要过滤
							if (tempSpeed<=6000) {
								sumSpeed = sumSpeed + tempSpeed;
								countSpeed++;
							}
						}
					} catch(Exception e)  {}
					try {
						String flowStr =  plcDataMap.get(EntityDefine.RPPLCData_N2FlowRate_CN).trim();
						if (isNumber(flowStr)) {
							tempFlow = Double.parseDouble(flowStr);
							//有异常数据，需要过滤
							if (tempFlow<150) {
								sumFlow = sumFlow + tempFlow;
								countFlow++;
							}
						}
					} catch(Exception e)  {}
					try {
						String tempStr =  plcDataMap.get(EntityDefine.RPPLCData_RefiningTemp_CN).trim();
						if (isNumber(tempStr)) {
							sumTemp = sumTemp + Double.parseDouble(tempStr);
							countTemp++;
						}
					} catch(Exception e)  {}
				}
			}
			if (countSpeed>0) avgSpeed=sumSpeed/countSpeed;
			if (countFlow>0) avgFlow=sumFlow/countFlow;
			if (countTemp>0) avgTemp=sumTemp/countTemp;
			
			
			//插入旋转转速记录
			Degasserprocdata dgProcDataSpeedItem = createDefaultItem();
			dgProcDataSpeedItem.setEntityid(entityId);
			dgProcDataSpeedItem.setfRfidid(rfidNOStr);
			dgProcDataSpeedItem.setfRealcyctime(String.format("%.1f", treatmentTime));
			dgProcDataSpeedItem.setfCheckparameter("旋转转速");
			dgProcDataSpeedItem.setfQualityvalue(String.format("%.0f", avgSpeed));
			dgProcDataSpeedItem.setfUnit("RPM");
			degasserprocdataDAO.insert(dgProcDataSpeedItem);

			//插入氮气流量记录
			Degasserprocdata dgProcDataFlowItem = createDefaultItem();
			dgProcDataFlowItem.setEntityid(entityId);
			dgProcDataFlowItem.setfRfidid(rfidNOStr);
			dgProcDataFlowItem.setfRealcyctime(String.format("%.1f", treatmentTime));
			dgProcDataFlowItem.setfCheckparameter("氮气流量");
			dgProcDataFlowItem.setfQualityvalue(String.format("%.0f", avgFlow));
			dgProcDataFlowItem.setfUnit("Nm3/h");
			degasserprocdataDAO.insert(dgProcDataFlowItem);
			
			//插入压力记录
			Degasserprocdata dgProcDataPressureItem = createDefaultItem();
			dgProcDataPressureItem.setEntityid(entityId);
			dgProcDataPressureItem.setfRfidid(rfidNOStr);
			dgProcDataPressureItem.setfRealcyctime(String.format("%.1f", treatmentTime));
			dgProcDataPressureItem.setfCheckparameter("氮气压力");
			dgProcDataPressureItem.setfQualityvalue("2.5");
			dgProcDataPressureItem.setfUnit("bar");
			degasserprocdataDAO.insert(dgProcDataPressureItem);
			
			//插入除气时间
			Degasserprocdata dgProcDataTreatTimeItem = createDefaultItem();
			dgProcDataTreatTimeItem.setEntityid(entityId);
			dgProcDataTreatTimeItem.setfRfidid(rfidNOStr);
			dgProcDataTreatTimeItem.setfRealcyctime(String.format("%.1f", treatmentTime));
			dgProcDataTreatTimeItem.setfCheckparameter("除气时间");
			dgProcDataTreatTimeItem.setfQualityvalue(String.format("%.1f", treatmentTime));
			dgProcDataTreatTimeItem.setfUnit("min");
			degasserprocdataDAO.insert(dgProcDataTreatTimeItem);
			
			//插入静置开始时间
			Degasserprocdata dgProcDatakeepStartTimeItem = createDefaultItem();
			dgProcDatakeepStartTimeItem.setEntityid(entityId);
			dgProcDatakeepStartTimeItem.setfRfidid(rfidNOStr);
			dgProcDatakeepStartTimeItem.setfRealcyctime(String.format("%.1f", treatmentTime));
			dgProcDatakeepStartTimeItem.setfCheckparameter("静置开始时间");
			dgProcDatakeepStartTimeItem.setfQualityvalue(endTimeStr);
			dgProcDatakeepStartTimeItem.setfUnit("");
			degasserprocdataDAO.insert(dgProcDatakeepStartTimeItem);
			
			//插入静置时间
			Degasserprocdata dgProcDataKeepTimeItem = createDefaultItem();
			dgProcDataKeepTimeItem.setEntityid(entityId);
			dgProcDataKeepTimeItem.setfRfidid(rfidNOStr);
			dgProcDataKeepTimeItem.setfRealcyctime(String.format("%.1f", treatmentTime));
			dgProcDataKeepTimeItem.setfCheckparameter("静置时间");
			dgProcDataKeepTimeItem.setfQualityvalue(String.format("%.1f", keepTime));
			dgProcDataKeepTimeItem.setfUnit("min");
			degasserprocdataDAO.insert(dgProcDataKeepTimeItem);
			
			//插入温度记录
			Degasserprocdata dgProcDataTempItem = createDefaultItem();
			dgProcDataTempItem.setEntityid(entityId);
			dgProcDataTempItem.setfRfidid(rfidNOStr);
			dgProcDataTempItem.setfRealcyctime(String.format("%.1f", treatmentTime));
			dgProcDataTempItem.setfCheckparameter("精炼温度");
			dgProcDataTempItem.setfQualityvalue(String.format("%.1f", avgTemp));
			dgProcDataTempItem.setfUnit("摄氏度");
			degasserprocdataDAO.insert(dgProcDataTempItem);
			
		} catch (Exception e) {
			logger.error("sync mes error: meteId-" + metaId + ",entityId-" + entityId + ", "+ e.toString());
		}
	}

	private Degasserdensitydata parseMapToDensityItem(Integer metaId, String entityId, Map valuesMap) {
		Degasserdensitydata dgDensityDataItem = new Degasserdensitydata();
		dgDensityDataItem.setEntityid(entityId);
		dgDensityDataItem.setfLineid(EntityDefine.LineCode);
		dgDensityDataItem.setfStationname(EntityDefine.StepName);
		dgDensityDataItem.setfMaschinenid(EntityDefine.DensityDeviceCode+"#");
		dgDensityDataItem.setfDevicename(EntityDefine.DensityDeviceName);
		try {
			double diDensityIndex = 0;
			double diDensityIndexMax = -1;
			double diDensityIndexMin = -1;
			Object diTimeObj = valuesMap.get(EntityDefine.DI_Time_CN);
			if (diTimeObj!=null) 
				try {
					dgDensityDataItem.setfTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(diTimeObj.toString()));

					//读取测量时间前最近的RFID卡号
					List icCardList = dynEntityDAO.dynSelect("id," + EntityDefine.RPRFID_RFIDNO_CN, 
							"t_entity_"+String.valueOf(EntityDefine.RPRFIDMetaId), 
							"1=1 and " + EntityDefine.RPRFID_EnterTime_CN + "<='" + diTimeObj.toString() 
							+ "' order by " + EntityDefine.RPRFID_EnterTime_CN + " desc ");
					if ((icCardList!=null)&& (icCardList.size()>0)) {
						Map<String, String> tempMap = (Map<String, String>) icCardList.get(0);
						dgDensityDataItem.setfRfidid(tempMap.get(EntityDefine.RPRFID_RFIDNO_CN));
					}
				
				} catch(Exception e) {}
			
			Object diFurnaceObj = valuesMap.get(EntityDefine.DI_Furnace_CN);
			if (diFurnaceObj!=null) 
				try {
					dgDensityDataItem.setfFurnace(diFurnaceObj.toString());
				} catch(Exception e) {}
				
			Object diArticleObj = valuesMap.get(EntityDefine.DI_Article_CN);
			if (diArticleObj!=null) 
				try {
					dgDensityDataItem.setfPersonnel(diArticleObj.toString());
				} catch(Exception e) {}
			
			Object diVDensityObj = valuesMap.get(EntityDefine.DI_VDensity_CN);
			if (diVDensityObj!=null) 
				try {
					dgDensityDataItem.setfVacuumsample(diVDensityObj.toString());
				} catch(Exception e) {}
			
			Object diADensityObj = valuesMap.get(EntityDefine.DI_ADensity_CN);
			if (diADensityObj!=null) 
				try {
					dgDensityDataItem.setfOrdinarysample(diADensityObj.toString());
				} catch(Exception e) {}
			
			Object diIDObj = valuesMap.get(EntityDefine.DI_ID_CN);
			if (diIDObj!=null) 
				try {
					dgDensityDataItem.setfSampleid(diIDObj.toString());
				} catch(Exception e) {}
			
			Object diDensityIndexObj = valuesMap.get(EntityDefine.DI_DensityIndex_CN);
			if (diDensityIndexObj!=null) 
				try {
					dgDensityDataItem.setfCheckvalue(diDensityIndexObj.toString());
					diDensityIndex = Double.parseDouble(diDensityIndexObj.toString());
				} catch(Exception e) {}
			
			//获取上下限值，判断结果是否合格
			Object diDensityIndexMaxObj = valuesMap.get(EntityDefine.DI_DensityIndexMax_CN);
			if (diDensityIndexMaxObj!=null) 
				try {
					diDensityIndexMax =Double.parseDouble(diDensityIndexMaxObj.toString());
				} catch(Exception e) {}
			
			Object diDensityIndexMinObj = valuesMap.get(EntityDefine.DI_DensityIndexMin_CN);
			if (diDensityIndexMinObj!=null) 
				try {
					diDensityIndexMin =Double.parseDouble(diDensityIndexMinObj.toString());
				} catch(Exception e) {}
			
			if ( ((diDensityIndexMax!=-1) && (diDensityIndex>diDensityIndexMax))
				|| ((diDensityIndexMin!=-1) && (diDensityIndex<diDensityIndexMin)) ) {
				dgDensityDataItem.setfState("不合格");
			} else {
				dgDensityDataItem.setfState("合格");
			}
		} catch (Exception e) {
			logger.error("sync parse error: meteId-" + metaId + ",entityId-" + entityId + ", "+ e.toString());
		}
		dgDensityDataItem.setfIssync(0);
		return dgDensityDataItem;
	}

	public boolean isNumber(String str){
		String reg = "^[0-9]+(.[0-9]+)?$";
		return str.matches(reg);
	}	
	
	public Degasserprocdata createDefaultItem() {
		Degasserprocdata defaultItem = new Degasserprocdata();
		defaultItem.setfLineid(EntityDefine.LineCode);
		defaultItem.setfStationname(EntityDefine.StepName);
		defaultItem.setfMaschinenid(EntityDefine.RPDeviceCode+"#");
		defaultItem.setfDevicename(EntityDefine.RPDeviceName);
		//设定除气时间
		defaultItem.setfObjectivecyctime("10");
		defaultItem.setfIssync(0);
		defaultItem.setfTime(new Date());
		return defaultItem;
	}
}
