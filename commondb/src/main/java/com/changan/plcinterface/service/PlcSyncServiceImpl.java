package com.changan.plcinterface.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.changan.app.datamodel.EntityDefine;
import com.changan.plcinterface.dao.Cgcdevice104DAO;
import com.changan.plcinterface.dao.Cgcdevice105DAO;
import com.changan.plcinterface.dao.Cgcdevice106DAO;
import com.changan.plcinterface.dao.Cgcdevice107DAO;
import com.changan.plcinterface.dao.CgcdeviceDAO;
import com.changan.plcinterface.dao.Machinedevice102DAO;
import com.changan.plcinterface.dao.Machinedevice103DAO;
import com.changan.plcinterface.dao.MachinedeviceDAO;
import com.changan.plcinterface.dao.Robotdevice102DAO;
import com.changan.plcinterface.dao.Robotdevice103DAO;
import com.changan.plcinterface.dao.RobotdeviceDAO;
import com.changan.plcinterface.dao.Shocksanddevice102DAO;
import com.changan.plcinterface.dao.Shocksanddevice103DAO;
import com.changan.plcinterface.dao.ShocksanddeviceDAO;
import com.changan.plcinterface.dao.Transferdevice101DAO;
import com.changan.plcinterface.dao.TransferdeviceDAO;
import com.changan.plcinterface.dao.WprecordinformationDAO;
import com.changan.plcinterface.model.Basicdevice;
import com.changan.plcinterface.model.Cgcdevice;
import com.changan.plcinterface.model.Cgcdevice104;
import com.changan.plcinterface.model.Cgcdevice104Example;
import com.changan.plcinterface.model.Cgcdevice105;
import com.changan.plcinterface.model.Cgcdevice105Example;
import com.changan.plcinterface.model.Cgcdevice106;
import com.changan.plcinterface.model.Cgcdevice106Example;
import com.changan.plcinterface.model.Cgcdevice107;
import com.changan.plcinterface.model.Cgcdevice107Example;
import com.changan.plcinterface.model.Machinedevice;
import com.changan.plcinterface.model.Machinedevice102;
import com.changan.plcinterface.model.Machinedevice102Example;
import com.changan.plcinterface.model.Machinedevice103;
import com.changan.plcinterface.model.Machinedevice103Example;
import com.changan.plcinterface.model.Robotdevice;
import com.changan.plcinterface.model.Robotdevice102;
import com.changan.plcinterface.model.Robotdevice102Example;
import com.changan.plcinterface.model.Robotdevice103;
import com.changan.plcinterface.model.Robotdevice103Example;
import com.changan.plcinterface.model.Shocksanddevice;
import com.changan.plcinterface.model.Shocksanddevice102;
import com.changan.plcinterface.model.Shocksanddevice102Example;
import com.changan.plcinterface.model.Shocksanddevice103;
import com.changan.plcinterface.model.Shocksanddevice103Example;
import com.changan.plcinterface.model.Transferdevice;
import com.changan.plcinterface.model.Transferdevice101;
import com.changan.plcinterface.model.Transferdevice101Example;
import com.changan.plcinterface.model.Wprecordinformation;
import com.changan.plcinterface.model.WprecordinformationExample;
import com.commondb.db.service.EntityService;

public class PlcSyncServiceImpl implements PlcSyncService {
	
	static Logger logger = Logger.getLogger(PlcSyncServiceImpl.class);

	private MachinedeviceDAO localMachinedeviceDAO;
	private Machinedevice102DAO localMachinedevice102DAO;
	private Machinedevice102DAO remoteMachinedevice102DAO;
	private Machinedevice103DAO localMachinedevice103DAO;
	private Machinedevice103DAO remoteMachinedevice103DAO;
	private RobotdeviceDAO localRobotdeviceDAO;
	private Robotdevice102DAO localRobotdevice102DAO;
	private Robotdevice102DAO remoteRobotdevice102DAO;
	private Robotdevice103DAO localRobotdevice103DAO;
	private Robotdevice103DAO remoteRobotdevice103DAO;
	private ShocksanddeviceDAO localShocksanddeviceDAO;
	private Shocksanddevice102DAO localShocksanddevice102DAO;
	private Shocksanddevice102DAO remoteShocksanddevice102DAO;
	private Shocksanddevice103DAO localShocksanddevice103DAO;
	private Shocksanddevice103DAO remoteShocksanddevice103DAO;
	private WprecordinformationDAO localWprecordinformationDAO;
	private WprecordinformationDAO remoteWprecordinformationDAO;
	private CgcdeviceDAO localCgcdeviceDAO;
	private Cgcdevice104DAO localCgcdevice104DAO;
	private Cgcdevice104DAO remoteCgcdevice104DAO;
	private Cgcdevice105DAO localCgcdevice105DAO;
	private Cgcdevice105DAO remoteCgcdevice105DAO;
	private Cgcdevice106DAO localCgcdevice106DAO;
	private Cgcdevice106DAO remoteCgcdevice106DAO;
	private Cgcdevice107DAO localCgcdevice107DAO;
	private Cgcdevice107DAO remoteCgcdevice107DAO;
	private TransferdeviceDAO localTransferdeviceDAO;
	private Transferdevice101DAO localTransferdevice101DAO;
	private Transferdevice101DAO remoteTransferdevice101DAO;

	private EntityService entityService;
	
	
	/**
	 * @return the localMachinedeviceDAO
	 */
	public MachinedeviceDAO getLocalMachinedeviceDAO() {
		return localMachinedeviceDAO;
	}

	/**
	 * @param localMachinedeviceDAO the localMachinedeviceDAO to set
	 */
	public void setLocalMachinedeviceDAO(MachinedeviceDAO localMachinedeviceDAO) {
		this.localMachinedeviceDAO = localMachinedeviceDAO;
	}

	public Machinedevice102DAO getLocalMachinedevice102DAO() {
		return localMachinedevice102DAO;
	}

	public void setLocalMachinedevice102DAO(
			Machinedevice102DAO localMachinedevice102DAO) {
		this.localMachinedevice102DAO = localMachinedevice102DAO;
	}

	public Machinedevice102DAO getRemoteMachinedevice102DAO() {
		return remoteMachinedevice102DAO;
	}

	public void setRemoteMachinedevice102DAO(
			Machinedevice102DAO remoteMachinedevice102DAO) {
		this.remoteMachinedevice102DAO = remoteMachinedevice102DAO;
	}

	public Machinedevice103DAO getLocalMachinedevice103DAO() {
		return localMachinedevice103DAO;
	}

	public void setLocalMachinedevice103DAO(
			Machinedevice103DAO localMachinedevice103DAO) {
		this.localMachinedevice103DAO = localMachinedevice103DAO;
	}

	public Machinedevice103DAO getRemoteMachinedevice103DAO() {
		return remoteMachinedevice103DAO;
	}

	public void setRemoteMachinedevice103DAO(
			Machinedevice103DAO remoteMachinedevice103DAO) {
		this.remoteMachinedevice103DAO = remoteMachinedevice103DAO;
	}

	public RobotdeviceDAO getLocalRobotdeviceDAO() {
		return localRobotdeviceDAO;
	}

	public void setLocalRobotdeviceDAO(RobotdeviceDAO localRobotdeviceDAO) {
		this.localRobotdeviceDAO = localRobotdeviceDAO;
	}

	public Robotdevice102DAO getLocalRobotdevice102DAO() {
		return localRobotdevice102DAO;
	}

	public void setLocalRobotdevice102DAO(Robotdevice102DAO localRobotdevice102DAO) {
		this.localRobotdevice102DAO = localRobotdevice102DAO;
	}

	public Robotdevice102DAO getRemoteRobotdevice102DAO() {
		return remoteRobotdevice102DAO;
	}

	public void setRemoteRobotdevice102DAO(Robotdevice102DAO remoteRobotdevice102DAO) {
		this.remoteRobotdevice102DAO = remoteRobotdevice102DAO;
	}

	public Robotdevice103DAO getLocalRobotdevice103DAO() {
		return localRobotdevice103DAO;
	}

	public void setLocalRobotdevice103DAO(Robotdevice103DAO localRobotdevice103DAO) {
		this.localRobotdevice103DAO = localRobotdevice103DAO;
	}

	public Robotdevice103DAO getRemoteRobotdevice103DAO() {
		return remoteRobotdevice103DAO;
	}

	public void setRemoteRobotdevice103DAO(Robotdevice103DAO remoteRobotdevice103DAO) {
		this.remoteRobotdevice103DAO = remoteRobotdevice103DAO;
	}

	/**
	 * @return the localShocksanddeviceDAO
	 */
	public ShocksanddeviceDAO getLocalShocksanddeviceDAO() {
		return localShocksanddeviceDAO;
	}

	/**
	 * @param localShocksanddeviceDAO the localShocksanddeviceDAO to set
	 */
	public void setLocalShocksanddeviceDAO(ShocksanddeviceDAO localShocksanddeviceDAO) {
		this.localShocksanddeviceDAO = localShocksanddeviceDAO;
	}

	public Shocksanddevice102DAO getLocalShocksanddevice102DAO() {
		return localShocksanddevice102DAO;
	}

	public void setLocalShocksanddevice102DAO(
			Shocksanddevice102DAO localShocksanddevice102DAO) {
		this.localShocksanddevice102DAO = localShocksanddevice102DAO;
	}

	public Shocksanddevice102DAO getRemoteShocksanddevice102DAO() {
		return remoteShocksanddevice102DAO;
	}

	public void setRemoteShocksanddevice102DAO(
			Shocksanddevice102DAO remoteShocksanddevice102DAO) {
		this.remoteShocksanddevice102DAO = remoteShocksanddevice102DAO;
	}

	public Shocksanddevice103DAO getLocalShocksanddevice103DAO() {
		return localShocksanddevice103DAO;
	}

	public void setLocalShocksanddevice103DAO(
			Shocksanddevice103DAO localShocksanddevice103DAO) {
		this.localShocksanddevice103DAO = localShocksanddevice103DAO;
	}

	public Shocksanddevice103DAO getRemoteShocksanddevice103DAO() {
		return remoteShocksanddevice103DAO;
	}

	public void setRemoteShocksanddevice103DAO(
			Shocksanddevice103DAO remoteShocksanddevice103DAO) {
		this.remoteShocksanddevice103DAO = remoteShocksanddevice103DAO;
	}

	public WprecordinformationDAO getLocalWprecordinformationDAO() {
		return localWprecordinformationDAO;
	}

	public void setLocalWprecordinformationDAO(
			WprecordinformationDAO localWprecordinformationDAO) {
		this.localWprecordinformationDAO = localWprecordinformationDAO;
	}

	public WprecordinformationDAO getRemoteWprecordinformationDAO() {
		return remoteWprecordinformationDAO;
	}

	public void setRemoteWprecordinformationDAO(
			WprecordinformationDAO remoteWprecordinformationDAO) {
		this.remoteWprecordinformationDAO = remoteWprecordinformationDAO;
	}

	/**
	 * @return the localCgcdeviceDAO
	 */
	public CgcdeviceDAO getLocalCgcdeviceDAO() {
		return localCgcdeviceDAO;
	}

	/**
	 * @param localCgcdeviceDAO the localCgcdeviceDAO to set
	 */
	public void setLocalCgcdeviceDAO(CgcdeviceDAO localCgcdeviceDAO) {
		this.localCgcdeviceDAO = localCgcdeviceDAO;
	}

	public Cgcdevice104DAO getLocalCgcdevice104DAO() {
		return localCgcdevice104DAO;
	}

	public void setLocalCgcdevice104DAO(Cgcdevice104DAO localCgcdevice104DAO) {
		this.localCgcdevice104DAO = localCgcdevice104DAO;
	}

	public Cgcdevice104DAO getRemoteCgcdevice104DAO() {
		return remoteCgcdevice104DAO;
	}

	public void setRemoteCgcdevice104DAO(Cgcdevice104DAO remoteCgcdevice104DAO) {
		this.remoteCgcdevice104DAO = remoteCgcdevice104DAO;
	}

	public Cgcdevice105DAO getLocalCgcdevice105DAO() {
		return localCgcdevice105DAO;
	}

	public void setLocalCgcdevice105DAO(Cgcdevice105DAO localCgcdevice105DAO) {
		this.localCgcdevice105DAO = localCgcdevice105DAO;
	}

	public Cgcdevice105DAO getRemoteCgcdevice105DAO() {
		return remoteCgcdevice105DAO;
	}

	public void setRemoteCgcdevice105DAO(Cgcdevice105DAO remoteCgcdevice105DAO) {
		this.remoteCgcdevice105DAO = remoteCgcdevice105DAO;
	}

	public Cgcdevice106DAO getLocalCgcdevice106DAO() {
		return localCgcdevice106DAO;
	}

	public void setLocalCgcdevice106DAO(Cgcdevice106DAO localCgcdevice106DAO) {
		this.localCgcdevice106DAO = localCgcdevice106DAO;
	}

	public Cgcdevice106DAO getRemoteCgcdevice106DAO() {
		return remoteCgcdevice106DAO;
	}

	public void setRemoteCgcdevice106DAO(Cgcdevice106DAO remoteCgcdevice106DAO) {
		this.remoteCgcdevice106DAO = remoteCgcdevice106DAO;
	}

	public Cgcdevice107DAO getLocalCgcdevice107DAO() {
		return localCgcdevice107DAO;
	}

	public void setLocalCgcdevice107DAO(Cgcdevice107DAO localCgcdevice107DAO) {
		this.localCgcdevice107DAO = localCgcdevice107DAO;
	}

	/**
	 * @return the localTransferdeviceDAO
	 */
	public TransferdeviceDAO getLocalTransferdeviceDAO() {
		return localTransferdeviceDAO;
	}

	/**
	 * @param localTransferdeviceDAO the localTransferdeviceDAO to set
	 */
	public void setLocalTransferdeviceDAO(TransferdeviceDAO localTransferdeviceDAO) {
		this.localTransferdeviceDAO = localTransferdeviceDAO;
	}

	public Cgcdevice107DAO getRemoteCgcdevice107DAO() {
		return remoteCgcdevice107DAO;
	}

	public void setRemoteCgcdevice107DAO(Cgcdevice107DAO remoteCgcdevice107DAO) {
		this.remoteCgcdevice107DAO = remoteCgcdevice107DAO;
	}

	public Transferdevice101DAO getLocalTransferdevice101DAO() {
		return localTransferdevice101DAO;
	}

	public void setLocalTransferdevice101DAO(
			Transferdevice101DAO localTransferdevice101DAO) {
		this.localTransferdevice101DAO = localTransferdevice101DAO;
	}

	public Transferdevice101DAO getRemoteTransferdevice101DAO() {
		return remoteTransferdevice101DAO;
	}

	public void setRemoteTransferdevice101DAO(
			Transferdevice101DAO remoteTransferdevice101DAO) {
		this.remoteTransferdevice101DAO = remoteTransferdevice101DAO;
	}

	public EntityService getEntityService() {
		return entityService;
	}

	public void setEntityService(EntityService entityService) {
		this.entityService = entityService;
	}

	@Override
	public String syncData() {
		try {
			//查询是否有未同步数据
			WprecordinformationExample wprecordinformationExample = new WprecordinformationExample();
			WprecordinformationExample.Criteria wprecordCriteria = wprecordinformationExample.createCriteria();
			wprecordCriteria.andRecordstatusEqualTo(true);
			wprecordinformationExample.setOrderByClause("wpdataname, datatime limit 100");
			
		    List<Wprecordinformation> remoteWprecordinformationList = this.remoteWprecordinformationDAO.selectByExample(wprecordinformationExample);
		    if (remoteWprecordinformationList.size()>0) {
				logger.info("begin sync data.");
		    }
		    int processCount = 0 ;
		    for (Wprecordinformation remoteWprecordinformation : remoteWprecordinformationList) {
		    	boolean processFlag = false;
		    	if (remoteWprecordinformation.getWpdataname().equalsIgnoreCase("machinedevice102")) {
		    		Machinedevice102Example machinedevice102Example = new Machinedevice102Example();
		    		Machinedevice102Example.Criteria machinedevice102Criteria = machinedevice102Example.createCriteria();
		    		machinedevice102Criteria.andWprecordvalueEqualTo(remoteWprecordinformation.getWprecordcode().longValue());
		    	    List<Machinedevice102> remoteMachinedevice102List = this.remoteMachinedevice102DAO.selectByExample(machinedevice102Example);
		    	    for (Machinedevice102 remoteMachinedevice102 : remoteMachinedevice102List) {
		    	      this.localMachinedevice102DAO.insert(remoteMachinedevice102);
		    	      localMachinedeviceDAO.insert(remoteMachinedevice102);
		    	      processMachineDeviceData(remoteMachinedevice102);
		    	    }
		    	    processFlag = true;
		    	}
	
		    	if (remoteWprecordinformation.getWpdataname().equalsIgnoreCase("machinedevice103")) {
		    		Machinedevice103Example machinedevice103Example = new Machinedevice103Example();
		    		Machinedevice103Example.Criteria machinedevice103Criteria = machinedevice103Example.createCriteria();
		    		machinedevice103Criteria.andWprecordvalueEqualTo(remoteWprecordinformation.getWprecordcode().longValue());
		    	    List<Machinedevice103> remoteMachinedevice103List = this.remoteMachinedevice103DAO.selectByExample(machinedevice103Example);
		    	    for (Machinedevice103 remoteMachinedevice103 : remoteMachinedevice103List) {
		    	      this.localMachinedevice103DAO.insert(remoteMachinedevice103);
		    	      localMachinedeviceDAO.insert(remoteMachinedevice103);
		    	      processMachineDeviceData(remoteMachinedevice103);
		    	    }
		    	    processFlag = true;
		    	}
		    	
		    	if (remoteWprecordinformation.getWpdataname().equalsIgnoreCase("robotdevice102")) {
		    		Robotdevice102Example robotdevice102Example = new Robotdevice102Example();
		    		Robotdevice102Example.Criteria robotdevice102Criteria = robotdevice102Example.createCriteria();
		    		robotdevice102Criteria.andWprecordvalueEqualTo(remoteWprecordinformation.getWprecordcode().longValue());
		    	    List<Robotdevice102> remoteRobotdevice102List = this.remoteRobotdevice102DAO.selectByExample(robotdevice102Example);
		    	    for (Robotdevice102 remoteRobotdevice102 : remoteRobotdevice102List) {
		    	      this.localRobotdevice102DAO.insert(remoteRobotdevice102);
		    	      localRobotdeviceDAO.insert(remoteRobotdevice102);
		    	      processRobotDeviceData(remoteRobotdevice102);
		    	    }
		    	    processFlag = true;
		    	}
		    	
		    	if (remoteWprecordinformation.getWpdataname().equalsIgnoreCase("robotdevice103")) {
		    		Robotdevice103Example robotdevice103Example = new Robotdevice103Example();
		    		Robotdevice103Example.Criteria robotdevice103Criteria = robotdevice103Example.createCriteria();
		    		robotdevice103Criteria.andWprecordvalueEqualTo(remoteWprecordinformation.getWprecordcode().longValue());
		    	    List<Robotdevice103> remoteRobotdevice103List = this.remoteRobotdevice103DAO.selectByExample(robotdevice103Example);
		    	    for (Robotdevice103 remoteRobotdevice103 : remoteRobotdevice103List) {
		    	      this.localRobotdevice103DAO.insert(remoteRobotdevice103);
		    	      localRobotdeviceDAO.insert(remoteRobotdevice103);
		    	      processRobotDeviceData(remoteRobotdevice103);
		    	    }
		    	    processFlag = true;
		    	}
		    	
		    	if (remoteWprecordinformation.getWpdataname().equalsIgnoreCase("shocksanddevice102")) {
		    		Shocksanddevice102Example Shocksanddevice102Example = new Shocksanddevice102Example();
		    		Shocksanddevice102Example.Criteria Shocksanddevice102Criteria = Shocksanddevice102Example.createCriteria();
		    		Shocksanddevice102Criteria.andWprecordvalueEqualTo(remoteWprecordinformation.getWprecordcode().longValue());
		    	    List<Shocksanddevice102> remoteShocksanddevice102List = this.remoteShocksanddevice102DAO.selectByExample(Shocksanddevice102Example);
		    	    for (Shocksanddevice102 remoteShocksanddevice102 : remoteShocksanddevice102List) {
		    	      this.localShocksanddevice102DAO.insert(remoteShocksanddevice102);
		    	      localShocksanddeviceDAO.insert(remoteShocksanddevice102);
		    	      processShockSandDeviceData(remoteShocksanddevice102);
		    	    }
		    	    processFlag = true;
		    	}
		    	
		    	if (remoteWprecordinformation.getWpdataname().equalsIgnoreCase("shocksanddevice103")) {
		    		Shocksanddevice103Example Shocksanddevice103Example = new Shocksanddevice103Example();
		    		Shocksanddevice103Example.Criteria Shocksanddevice103Criteria = Shocksanddevice103Example.createCriteria();
		    		Shocksanddevice103Criteria.andWprecordvalueEqualTo(remoteWprecordinformation.getWprecordcode().longValue());
		    	    List<Shocksanddevice103> remoteShocksanddevice103List = this.remoteShocksanddevice103DAO.selectByExample(Shocksanddevice103Example);
		    	    for (Shocksanddevice103 remoteShocksanddevice103 : remoteShocksanddevice103List) {
		    	      this.localShocksanddevice103DAO.insert(remoteShocksanddevice103);
		    	      localShocksanddeviceDAO.insert(remoteShocksanddevice103);
		    	      processShockSandDeviceData(remoteShocksanddevice103);
		    	    }
		    	    processFlag = true;
		    	}

		    	if (remoteWprecordinformation.getWpdataname().equalsIgnoreCase("cgcdevice104")) {
		    		Cgcdevice104Example cgcdevice104Example = new Cgcdevice104Example();
		    		Cgcdevice104Example.Criteria cgcdevice104Criteria = cgcdevice104Example.createCriteria();
		    		cgcdevice104Criteria.andWprecordvalueEqualTo(remoteWprecordinformation.getWprecordcode().longValue());
		    	    List<Cgcdevice104> remoteCgcdevice104List = this.remoteCgcdevice104DAO.selectByExample(cgcdevice104Example);
		    	    for (Cgcdevice104 remoteCgcdevice104 : remoteCgcdevice104List) {
		    	      this.localCgcdevice104DAO.insert(remoteCgcdevice104);
		    	      this.localCgcdeviceDAO.insert(remoteCgcdevice104);
		    	      processCGCDeviceData(remoteCgcdevice104);
		    	    }
		    	    processFlag = true;
		    	}
		    	
		    	if (remoteWprecordinformation.getWpdataname().equalsIgnoreCase("cgcdevice105")) {
		    		Cgcdevice105Example cgcdevice105Example = new Cgcdevice105Example();
		    		Cgcdevice105Example.Criteria cgcdevice105Criteria = cgcdevice105Example.createCriteria();
		    		cgcdevice105Criteria.andWprecordvalueEqualTo(remoteWprecordinformation.getWprecordcode().longValue());
		    	    List<Cgcdevice105> remoteCgcdevice105List = this.remoteCgcdevice105DAO.selectByExample(cgcdevice105Example);
		    	    for (Cgcdevice105 remoteCgcdevice105 : remoteCgcdevice105List) {
		    	      this.localCgcdevice105DAO.insert(remoteCgcdevice105);
		    	      this.localCgcdeviceDAO.insert(remoteCgcdevice105);
		    	      processCGCDeviceData(remoteCgcdevice105);
		    	    }
		    	    processFlag = true;
		    	}
		    	
		    	if (remoteWprecordinformation.getWpdataname().equalsIgnoreCase("cgcdevice106")) {
		    		Cgcdevice106Example cgcdevice106Example = new Cgcdevice106Example();
		    		Cgcdevice106Example.Criteria cgcdevice106Criteria = cgcdevice106Example.createCriteria();
		    		cgcdevice106Criteria.andWprecordvalueEqualTo(remoteWprecordinformation.getWprecordcode().longValue());
		    	    List<Cgcdevice106> remoteCgcdevice106List = this.remoteCgcdevice106DAO.selectByExample(cgcdevice106Example);
		    	    for (Cgcdevice106 remoteCgcdevice106 : remoteCgcdevice106List) {
		    	      this.localCgcdevice106DAO.insert(remoteCgcdevice106);
		    	      this.localCgcdeviceDAO.insert(remoteCgcdevice106);
		    	      processCGCDeviceData(remoteCgcdevice106);
		    	    }
		    	    processFlag = true;
		    	}
		    	
		    	if (remoteWprecordinformation.getWpdataname().equalsIgnoreCase("cgcdevice107")) {
		    		Cgcdevice107Example cgcdevice107Example = new Cgcdevice107Example();
		    		Cgcdevice107Example.Criteria cgcdevice107Criteria = cgcdevice107Example.createCriteria();
		    		cgcdevice107Criteria.andWprecordvalueEqualTo(remoteWprecordinformation.getWprecordcode().longValue());
		    	    List<Cgcdevice107> remoteCgcdevice107List = this.remoteCgcdevice107DAO.selectByExample(cgcdevice107Example);
		    	    for (Cgcdevice107 remoteCgcdevice107 : remoteCgcdevice107List) {
		    	      this.localCgcdevice107DAO.insert(remoteCgcdevice107);
		    	      this.localCgcdeviceDAO.insert(remoteCgcdevice107);
		    	      processCGCDeviceData(remoteCgcdevice107);
		    	    }
		    	    processFlag = true;
		    	}

		    	if (remoteWprecordinformation.getWpdataname().equalsIgnoreCase("transferdevice101")) {
		    		Transferdevice101Example transferdevice101Example = new Transferdevice101Example();
		    		Transferdevice101Example.Criteria transferdevice101Criteria = transferdevice101Example.createCriteria();
		    		transferdevice101Criteria.andWprecordvalueEqualTo(remoteWprecordinformation.getWprecordcode().longValue());
		    	    List<Transferdevice101> remoteTransferdevice101List = this.remoteTransferdevice101DAO.selectByExample(transferdevice101Example);
		    	    for (Transferdevice101 remoteTransferdevice101 : remoteTransferdevice101List) {
		    	      this.localTransferdevice101DAO.insert(remoteTransferdevice101);
		    	      localTransferdeviceDAO.insert(remoteTransferdevice101);
		    	      processTransferDeviceData(remoteTransferdevice101);
		    	    }
		    	    processFlag = true;
		    	}
		    	
		    	//备份原始记录，删除远程记录
		    	if (processFlag) {
		    		processCount++;
		    		//不备份原始记录，否则主键报错。 此处需要再设计。
			    	//this.localWprecordinformationDAO.insert(remoteWprecordinformation);
		    		remoteWprecordinformation.setRecordstatus(false);
		    		//this.remoteWprecordinformationDAO.updateByPrimaryKey(remoteWprecordinformation);
		    		this.remoteWprecordinformationDAO.deleteByPrimaryKey(remoteWprecordinformation);
		    	}
			}
		    if (remoteWprecordinformationList.size()>0) {
		    	logger.info("finish sync data : total " + processCount +" records.");
		    }
		} catch (Exception e) {
			logger.error("sync data error: " + e.toString());
		}
		return null;
	}

	@Override
	public void pullData() {
		
	}

	@Override
	public String processPlcData() {
		/*
		//从同步记录表里获取未处理记录
		WprecordinformationExample wprecordinformationExample = new WprecordinformationExample();
		WprecordinformationExample.Criteria wprecordCriteria = wprecordinformationExample.createCriteria();
		wprecordCriteria.andRecordstatusEqualTo(true);
	    List<Wprecordinformation> localWprecordinformationList = this.localWprecordinformationDAO.selectByExample(wprecordinformationExample);
	    if (localWprecordinformationList.size()>0) {
			logger.info("begin process data.");
	    }
	    int processCount = 0 ;
	    for (Wprecordinformation localWprecordinformation : localWprecordinformationList) {
	    	boolean processFlag = false;
	    	if (localWprecordinformation.getWpdataname().equalsIgnoreCase("robotdevice103")) {
	    		Robotdevice103Example robotdevice103Example = new Robotdevice103Example();
	    		Robotdevice103Example.Criteria robotdevice103Criteria = robotdevice103Example.createCriteria();
	    		robotdevice103Criteria.andWprecordvalueEqualTo(localWprecordinformation.getWprecordcode().longValue());
	    	    List<Robotdevice103> localRobotdevice103List = this.localRobotdevice103DAO.selectByExample(robotdevice103Example);
	    	    for (Robotdevice103 localRobotdevice103 : localRobotdevice103List) {
	    	    	try {
						processRobotDeviceData(localRobotdevice103);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	    }
	    	
	    	    processFlag = true;
	    	}
	    	//修改同步记录状态为已处理
	    	if (processFlag) {
	    		processCount++;
	    		localWprecordinformation.setRecordstatus(false);
	    		//this.remoteWprecordinformationDAO.updateByPrimaryKey(remoteWprecordinformation);
	    		this.localWprecordinformationDAO.deleteByPrimaryKey(localWprecordinformation);
	    	}
		}
	    if (localWprecordinformationList.size()>0) {
	    	logger.info("finish process data : total " + processCount +" records.");
	    }
	    
		processShockSandDeviceData();
		processMachineDeviceData();
		processCGCDeviceData();
		*/
		return null;
	}
	
	//解析取件机器人数据
	private void processRobotDeviceData(Robotdevice localRobotdevice) throws Exception {
		processBasicDeviceData(localRobotdevice, EntityDefine.RobotDeviceMetaId, EntityDefine.RobotDevice_ID_CN, EntityDefine.RobotDevice_Name_CN,
				EntityDefine.RobotDevice_StandBy, EntityDefine.RobotDevice_Run, EntityDefine.RobotDevice_Stop, EntityDefine.RobotDevice_Error, -1L);
	}

	//解析震砂机数据
	private void processShockSandDeviceData(Shocksanddevice localShocksanddevice)  throws Exception {
		processBasicDeviceData(localShocksanddevice, EntityDefine.ShockSandDeviceMetaId, EntityDefine.ShockSandDevice_ID_CN, EntityDefine.ShockSandDevice_Name_CN,
				EntityDefine.ShockSandDevice_StandBy, EntityDefine.ShockSandDevice_Run, EntityDefine.ShockSandDevice_Stop, EntityDefine.ShockSandDevice_Error, -1L);
	}
	
	//解析BROTHER机床数据
	private void processMachineDeviceData(Machinedevice localMachinedevice) throws Exception {
		processBasicDeviceData(localMachinedevice, EntityDefine.MachineDeviceMetaId, EntityDefine.MachineDevice_ID_CN, EntityDefine.MachineDevice_Name_CN,
				EntityDefine.MachineDevice_StandBy, EntityDefine.MachineDevice_Run, EntityDefine.MachineDevice_Stop, EntityDefine.MachineDevice_Error,
				EntityDefine.MachineDevice_ChangeCutter);
	}
	
	//解析CGC数据
	private void processCGCDeviceData(Cgcdevice localCgcdevice) throws Exception {
		processBasicDeviceData(localCgcdevice, EntityDefine.CgcDeviceMetaId, EntityDefine.CgcDevice_ID_CN, EntityDefine.CgcDevice_Name_CN,
				EntityDefine.CgcDevice_StandBy, EntityDefine.CgcDevice_Run, EntityDefine.CgcDevice_Stop, EntityDefine.CgcDevice_Error, -1L);
	}

	//解析中央物流数据
	private void processTransferDeviceData(Transferdevice localTransferdevice) throws Exception {
		processBasicDeviceData(localTransferdevice, EntityDefine.TransferDeviceMetaId, EntityDefine.TransferDevice_ID_CN, EntityDefine.TransferDevice_Name_CN,
				EntityDefine.TransferDevice_StandBy, EntityDefine.TransferDevice_Run, EntityDefine.TransferDevice_Stop, EntityDefine.TransferDevice_Error, -1L);
	}
	
	//解析机器数据
	private String processBasicDeviceData(Basicdevice basicdevice, int deviceMetaId, String device_ID_CN, String device_Name_CN,
			Long Device_StandBy, Long Device_Run, Long Device_Stop, Long Device_Error, Long Change_Cutter) throws Exception {
		//判断设备是否记录基本信息，如果没有，则记录 此处需要考虑性能
		Map<String, Object> mainValuesMap = new HashMap<String, Object>();
		Map<String, Object> standbyValuesMap = new HashMap<String, Object>();
		Map<String, Object> runValuesMap = new HashMap<String, Object>();
		Map<String, Object> errorValuesMap = new HashMap<String, Object>();
		List deviceBasicInfoList =  entityService.dynSelect("id", 
				"t_entity_"+String.valueOf(deviceMetaId), 
				device_ID_CN + "='" + basicdevice.getDeviceid() + "' ");
		String deviceBasicId  ="";
		if ((deviceBasicInfoList==null) || (deviceBasicInfoList.size()==0)) {
			mainValuesMap.put(device_ID_CN, basicdevice.getDeviceid());
			mainValuesMap.put(device_Name_CN, basicdevice.getDevicename());
			mainValuesMap.put("update_user", "admin");
			mainValuesMap.put("create_user", "admin");
			deviceBasicId = entityService.createEntity(deviceMetaId, mainValuesMap);
		} else {
			mainValuesMap = (Map<String, Object>)deviceBasicInfoList.get(0);
			deviceBasicId = (String) mainValuesMap.get("id");
		}
		
		//记录工件清理信息
		if ((basicdevice.getWorkpiecename()!=null ) &&(basicdevice.getWorkpiecename()!="")) {
			//寻找相同工件名称是否已有记录，这里无法判断是否重上线，重上线不能记录
			List workpieceRecList =  entityService.dynSelect(" id ", " t_entity_" + String.valueOf(EntityDefine.WorkpieceCleanMetaId) ,
					String.valueOf(EntityDefine.WorkpieceClean_PieceName_CN) + "='" + basicdevice.getWorkpiecename() + "' ");
			if ((workpieceRecList!=null) && (workpieceRecList.size()==0)) {
				//生成工件清理信息
				Map<String, Object> workpieceValuesMap = new HashMap<String, Object>();
				workpieceValuesMap.put(EntityDefine.WorkpieceClean_Date_CN, (new SimpleDateFormat("yyyy-MM-dd")).format(basicdevice.getPlctime()));
				workpieceValuesMap.put(EntityDefine.WorkpieceClean_PieceName_CN, basicdevice.getWorkpiecename());
				workpieceValuesMap.put(EntityDefine.WorkpieceClean_DeviceID_CN, basicdevice.getDeviceid());
				//构造关联属性，关联到设备主记录上
				String[][] wpEntityArr = {{String.valueOf(deviceMetaId),deviceBasicId}};
				workpieceValuesMap.put("entityArr",wpEntityArr);
				String[] wpEntityLabel = {basicdevice.getDevicename()};
				workpieceValuesMap.put("entityLabel", wpEntityLabel);
				workpieceValuesMap.put("update_user", "admin");
				workpieceValuesMap.put("create_user", "admin");
				entityService.createEntity(EntityDefine.WorkpieceCleanMetaId, workpieceValuesMap);
				
				//判断是否有工具信息 
				if ((basicdevice.getToolcode()!=null) && (basicdevice.getToolcode().intValue()>0)) {
					//记录刀具寿命
					List toolRecList =  entityService.dynSelect(" id, " + EntityDefine.Cutter_Code_CN + ", " + EntityDefine.Cutter_BeginTime_CN 
							+ ", " + EntityDefine.Cutter_DesignLife_CN + ", " + EntityDefine.Cutter_ProcessCount_CN, 
							" t_entity_" + String.valueOf(EntityDefine.CutterMetaId) ,
							String.valueOf(EntityDefine.Cutter_Code_CN) + "='" + basicdevice.getToolcode().intValue() + "' ");
					Map<String, Object> toolValuesMap = new HashMap<String, Object>();
					Map<String, Object> toolHistoryValuesMap = new HashMap<String, Object>();
					if  ((toolRecList!=null) && (toolRecList.size()==0)) {
						createInitCutterRec(basicdevice, deviceMetaId, deviceBasicId);						
						/*
						//创建刀具历史记录
						//创建一条初始记录
						toolHistoryValuesMap.put(EntityDefine.ChangeCutter_BeginTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(basicdevice.getPlctime()));
						toolHistoryValuesMap.put(EntityDefine.ChangeCutter_Code_CN, basicdevice.getToolcode().intValue());
						toolHistoryValuesMap.put(EntityDefine.ChangeCutter_ProcessCount_CN, 1);
						//构造关联属性，关联到刀具主记录上
						String[][] toolHistoryEntityArr = {{String.valueOf(EntityDefine.CutterMetaId),cutterId}};
						toolHistoryValuesMap.put("entityArr",toolHistoryEntityArr);
						String[] toolHistoryEntityLabel = {basicdevice.getToolcode()+"号刀具"};
						toolHistoryValuesMap.put("entityLabel", toolHistoryEntityLabel);
						toolHistoryValuesMap.put("update_user", "admin");
						toolHistoryValuesMap.put("create_user", "admin");
						entityService.createEntity(EntityDefine.ChangeCutterMetaId, toolHistoryValuesMap);
						*/
					} else {
						//刀具已存在
						if ((basicdevice.getProcesscode()!=null) && (basicdevice.getProcesscode().intValue()==Change_Cutter)) {
							//发生换刀 
							//新建一条刀具记录
							String cutterId = createInitCutterRec(basicdevice, deviceMetaId, deviceBasicId);
							//创建历史(换刀)记录
							toolValuesMap = (Map<String, Object>)toolRecList.get(0);
							
							toolHistoryValuesMap.put(EntityDefine.ChangeCutter_BeginTime_CN, toolValuesMap.get(EntityDefine.Cutter_BeginTime_CN));
							toolHistoryValuesMap.put(EntityDefine.ChangeCutter_ChangeTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(basicdevice.getPlctime()));
							toolHistoryValuesMap.put(EntityDefine.ChangeCutter_Code_CN, basicdevice.getToolcode().intValue());
							toolHistoryValuesMap.put(EntityDefine.ChangeCutter_ProcessCount_CN, toolValuesMap.get(EntityDefine.Cutter_ProcessCount_CN));
							toolHistoryValuesMap.put(EntityDefine.ChangeCutter_DesignLife_CN, toolValuesMap.get(EntityDefine.Cutter_DesignLife_CN));
							//构造关联属性，关联到刀具主记录上
							String[][] toolHistoryEntityArr = {{String.valueOf(EntityDefine.CutterMetaId), cutterId}};
							toolHistoryValuesMap.put("entityArr",toolHistoryEntityArr);
							String[] toolHistoryEntityLabel = {basicdevice.getToolcode()+"号刀具"};
							toolHistoryValuesMap.put("entityLabel", toolHistoryEntityLabel);
							toolHistoryValuesMap.put("update_user", "admin");
							toolHistoryValuesMap.put("create_user", "admin");
							entityService.createEntity(EntityDefine.ChangeCutterMetaId, toolHistoryValuesMap);

							//原刀具记录删除
							entityService.delEntity(EntityDefine.CutterMetaId, (String) toolValuesMap.get("id"));
							
						} else {
							//没有换刀 更新加工次数
							toolValuesMap = (Map<String, Object>)toolRecList.get(0);
							if (toolValuesMap.get(EntityDefine.Cutter_DesignLife_CN).toString().equalsIgnoreCase("null")) {
								toolValuesMap.put(EntityDefine.Cutter_DesignLife_CN,"");
							}
							toolValuesMap.put(EntityDefine.Cutter_ProcessCount_CN, Integer.parseInt(toolValuesMap.get(EntityDefine.Cutter_ProcessCount_CN).toString()) + 1);
							//构造关联属性，关联到设备主记录上
							//构造关联属性，关联到设备主记录上
							String[][] toolEntityArr = {{String.valueOf(deviceMetaId),deviceBasicId}};
							toolValuesMap.put("entityArr",toolEntityArr);
							String[] toolEntityLabel = {basicdevice.getDevicename()};
							toolValuesMap.put("entityLabel", toolEntityLabel);
							//id从map里移走，否则更新slor报错。
							String tempId = (String) toolValuesMap.get("id");
							toolValuesMap.remove("id");
							toolValuesMap.put("update_user", "admin");
							toolValuesMap.put("create_user", "admin");
							entityService.updateEntity(EntityDefine.CutterMetaId, tempId, toolValuesMap);
						}
					}
				}
			}
		}
		
		
		//根据不同状态，记录对应明细表
		//待机
		if (basicdevice.getDevicestatus().equals(Device_StandBy)) {
			//寻找最后一条未结束的故障记录 按时间倒序排列
			List errorRecList =  entityService.dynSelect("b.id,b." + EntityDefine.Error_StartTime_CN + ",b." + EntityDefine.Error_EndTime_CN, 
					"t_entity_" + String.valueOf(deviceMetaId) +" a, t_entity_" + String.valueOf(EntityDefine.ErrorMetaId) +" b, r_entity c",
					"a.id='" + deviceBasicId +"' and a.id=c.ENTITY2_ID and c.META2_ID=" + String.valueOf(deviceMetaId)
						+ " and c.META1_ID=" + String.valueOf(EntityDefine.ErrorMetaId) + " and c.ENTITY1_ID=b.id "
						+ " and b." + EntityDefine.Error_StartTime_CN + " is not null and b." + EntityDefine.Error_EndTime_CN + " is null "
						+ " order by b." + EntityDefine.Error_StartTime_CN + "  desc");
			
			if ((errorRecList!=null) && (errorRecList.size()>0)) {
				//找到最后一条未结束的故障记录
				errorValuesMap = (Map<String, Object>)errorRecList.get(0);
				//更新结束时间
				errorValuesMap.put(EntityDefine.Error_EndTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(basicdevice.getPlctime()));
				//构造关联属性，关联到设备主记录上
				String[][] errorEntityArr = {{String.valueOf(deviceMetaId),deviceBasicId}};
				errorValuesMap.put("entityArr",errorEntityArr);
				String[] errorEntityLabel = {basicdevice.getDevicename()};
				errorValuesMap.put("entityLabel",errorEntityLabel);
				//id从map里移走，否则更新slor报错。
				String tempId = (String) errorValuesMap.get("id");
				errorValuesMap.remove("id");
				errorValuesMap.put("update_user", "admin");
				errorValuesMap.put("create_user", "admin");
				entityService.updateEntity(EntityDefine.ErrorMetaId, tempId, errorValuesMap);
			}

			//寻找最后一条未结束的运行记录 按时间倒序排列
			List runRecList =  entityService.dynSelect("b.id,b." + EntityDefine.Run_StartTime_CN + ",b." + EntityDefine.Run_EndTime_CN, 
					"t_entity_" + String.valueOf(deviceMetaId) +" a, t_entity_" + String.valueOf(EntityDefine.RunMetaId) +" b, r_entity c",
					"a.id='" + deviceBasicId +"' and a.id=c.ENTITY2_ID and c.META2_ID=" + String.valueOf(deviceMetaId)
						+ " and c.META1_ID=" + String.valueOf(EntityDefine.RunMetaId) + " and c.ENTITY1_ID=b.id "
						+ " and b." + EntityDefine.Run_StartTime_CN + " is not null and b." + EntityDefine.Run_EndTime_CN + " is null "
						+ " order by b." + EntityDefine.Run_StartTime_CN + "  desc");
			
			if ((runRecList!=null) && (runRecList.size()>0)) {
				//找到最后一条未结束的运行记录
				runValuesMap = (Map<String, Object>)runRecList.get(0);
				//更新结束时间
				runValuesMap.put(EntityDefine.Run_EndTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(basicdevice.getPlctime()));
				//构造关联属性，关联到设备主记录上
				String[][] runEntityArr = {{String.valueOf(deviceMetaId),deviceBasicId}};
				runValuesMap.put("entityArr",runEntityArr);
				String[] runEntityLabel = {basicdevice.getDevicename()};
				runValuesMap.put("entityLabel",runEntityLabel);
				//id从map里移走，否则更新slor报错。
				String tempId = (String) runValuesMap.get("id");
				runValuesMap.remove("id");
				runValuesMap.put("update_user", "admin");
				runValuesMap.put("create_user", "admin");
				entityService.updateEntity(EntityDefine.RunMetaId, tempId, runValuesMap);
			}
			
			//判断是否有未结束待机记录
			List standByRecList =  entityService.dynSelect("b.id,b." + EntityDefine.Standby_StartTime_CN + ",b." + EntityDefine.Standby_EndTime_CN, 
					"t_entity_" + String.valueOf(deviceMetaId) +" a, t_entity_" + String.valueOf(EntityDefine.StandbyMetaId) +" b, r_entity c",
					"a.id='" + deviceBasicId +"' and a.id=c.ENTITY2_ID and c.META2_ID=" + String.valueOf(deviceMetaId)
						+ " and c.META1_ID=" + String.valueOf(EntityDefine.StandbyMetaId) + " and c.ENTITY1_ID=b.id "
						+ " and b." + EntityDefine.Standby_StartTime_CN + " is not null and b." + EntityDefine.Standby_EndTime_CN + " is null ");
			if ((standByRecList!=null) && (standByRecList.size()==0)) {
				//如果没有，创建待机记录
				standbyValuesMap.put(EntityDefine.Standby_StartTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(basicdevice.getPlctime()));
				//构造关联属性，关联到设备主记录上
				String[][] entityArr = {{String.valueOf(deviceMetaId),deviceBasicId}};
				standbyValuesMap.put("entityArr",entityArr);
				String[] standbyEntityLabel = {basicdevice.getDevicename()};
				standbyValuesMap.put("entityLabel",standbyEntityLabel);
				standbyValuesMap.put("update_user", "admin");
				standbyValuesMap.put("create_user", "admin");
				String standbyRecId = entityService.createEntity(EntityDefine.StandbyMetaId, standbyValuesMap);
			}
		}
		
		//运行
		if (basicdevice.getDevicestatus().equals(Device_Run)) {
			//寻找最后一条未结束的待机记录 按时间倒序排列
			/*select b.id,b.d_27,b.d_28 from 
			t_entity_2 a, t_entity_9 b, r_entity c
			where a.id='@id'
			and a.id=c.ENTITY2_ID
			and c.META2_ID=2
			and c.META1_ID=9
			and c.ENTITY1_ID=b.id
			and b.d_27 is not null and  b.d_28 is null
			order by b.d_27 desc */
			
			List standByRecList =  entityService.dynSelect("b.id,b." + EntityDefine.Standby_StartTime_CN + ",b." + EntityDefine.Standby_EndTime_CN, 
					"t_entity_" + String.valueOf(deviceMetaId) +" a, t_entity_" + String.valueOf(EntityDefine.StandbyMetaId) +" b, r_entity c",
					"a.id='" + deviceBasicId +"' and a.id=c.ENTITY2_ID and c.META2_ID=" + String.valueOf(deviceMetaId)
						+ " and c.META1_ID=" + String.valueOf(EntityDefine.StandbyMetaId) + " and c.ENTITY1_ID=b.id "
						+ " and b." + EntityDefine.Standby_StartTime_CN + " is not null and b." + EntityDefine.Standby_EndTime_CN + " is null "
						+ " order by b." + EntityDefine.Standby_StartTime_CN + "  desc");
			
			if ((standByRecList!=null) && (standByRecList.size()>0)) {
				//找到最后一条未结束的待机记录
				standbyValuesMap = (Map<String, Object>)standByRecList.get(0);
				//更新结束时间
				standbyValuesMap.put(EntityDefine.Standby_EndTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(basicdevice.getPlctime()));
				//构造关联属性，关联到设备主记录上
				String[][] standbyEntityArr = {{String.valueOf(deviceMetaId),deviceBasicId}};
				standbyValuesMap.put("entityArr",standbyEntityArr);
				String[] standbyEntityLabel = {basicdevice.getDevicename()};
				standbyValuesMap.put("entityLabel",standbyEntityLabel);
				//id从map里移走，否则更新slor报错。
				String tempId = (String) standbyValuesMap.get("id");
				standbyValuesMap.remove("id");
				standbyValuesMap.put("update_user", "admin");
				standbyValuesMap.put("create_user", "admin");
				entityService.updateEntity(EntityDefine.StandbyMetaId, tempId, standbyValuesMap);
			}
			//寻找最后一条未结束的故障记录 按时间倒序排列
			List errorRecList =  entityService.dynSelect("b.id,b." + EntityDefine.Error_StartTime_CN + ",b." + EntityDefine.Error_EndTime_CN, 
					"t_entity_" + String.valueOf(deviceMetaId) +" a, t_entity_" + String.valueOf(EntityDefine.ErrorMetaId) +" b, r_entity c",
					"a.id='" + deviceBasicId +"' and a.id=c.ENTITY2_ID and c.META2_ID=" + String.valueOf(deviceMetaId)
						+ " and c.META1_ID=" + String.valueOf(EntityDefine.ErrorMetaId) + " and c.ENTITY1_ID=b.id "
						+ " and b." + EntityDefine.Error_StartTime_CN + " is not null and b." + EntityDefine.Error_EndTime_CN + " is null "
						+ " order by b." + EntityDefine.Error_StartTime_CN + "  desc");
			
			if ((errorRecList!=null) && (errorRecList.size()>0)) {
				//找到最后一条未结束的故障记录
				errorValuesMap = (Map<String, Object>)errorRecList.get(0);
				//更新结束时间
				errorValuesMap.put(EntityDefine.Error_EndTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(basicdevice.getPlctime()));
				//构造关联属性，关联到设备主记录上
				String[][] errorEntityArr = {{String.valueOf(deviceMetaId),deviceBasicId}};
				errorValuesMap.put("entityArr",errorEntityArr);
				String[] errorEntityLabel = {basicdevice.getDevicename()};
				errorValuesMap.put("entityLabel",errorEntityLabel);
				//id从map里移走，否则更新slor报错。
				String tempId = (String) errorValuesMap.get("id");
				errorValuesMap.remove("id");
				errorValuesMap.put("update_user", "admin");
				errorValuesMap.put("create_user", "admin");
				entityService.updateEntity(EntityDefine.ErrorMetaId, tempId, errorValuesMap);
			}
			
			//判断是否有未结束
			List runRecList =  entityService.dynSelect("b.id,b." + EntityDefine.Run_StartTime_CN + ",b." + EntityDefine.Run_EndTime_CN, 
					"t_entity_" + String.valueOf(deviceMetaId) +" a, t_entity_" + String.valueOf(EntityDefine.RunMetaId) +" b, r_entity c",
					"a.id='" + deviceBasicId +"' and a.id=c.ENTITY2_ID and c.META2_ID=" + String.valueOf(deviceMetaId)
						+ " and c.META1_ID=" + String.valueOf(EntityDefine.RunMetaId) + " and c.ENTITY1_ID=b.id "
						+ " and b." + EntityDefine.Run_StartTime_CN + " is not null and b." + EntityDefine.Run_EndTime_CN + " is null ");
			
			if ((runRecList!=null) && (runRecList.size()==0)) {
				//没有未结束的，则创建运行记录
				runValuesMap.put(EntityDefine.Run_StartTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(basicdevice.getPlctime()));
				//构造关联属性，关联到设备主记录上
				String[][] runEntityArr = {{String.valueOf(deviceMetaId),deviceBasicId}};
				runValuesMap.put("entityArr",runEntityArr);
				String[] runEntityLabel = {basicdevice.getDevicename()};
				runValuesMap.put("entityLabel",runEntityLabel);
				runValuesMap.put("update_user", "admin");
				runValuesMap.put("create_user", "admin");
				String runRecId = entityService.createEntity(EntityDefine.RunMetaId, runValuesMap);
			}
		}
		
		//停止
		if (basicdevice.getDevicestatus().equals(Device_Stop)) {
			//寻找最后一条未结束的运行记录 按时间倒序排列
			List runRecList =  entityService.dynSelect("b.id,b." + EntityDefine.Run_StartTime_CN + ",b." + EntityDefine.Run_EndTime_CN, 
					"t_entity_" + String.valueOf(deviceMetaId) +" a, t_entity_" + String.valueOf(EntityDefine.RunMetaId) +" b, r_entity c",
					"a.id='" + deviceBasicId +"' and a.id=c.ENTITY2_ID and c.META2_ID=" + String.valueOf(deviceMetaId)
						+ " and c.META1_ID=" + String.valueOf(EntityDefine.RunMetaId) + " and c.ENTITY1_ID=b.id "
						+ " and b." + EntityDefine.Run_StartTime_CN + " is not null and b." + EntityDefine.Run_EndTime_CN + " is null "
						+ " order by b." + EntityDefine.Run_StartTime_CN + "  desc");
			
			if ((runRecList!=null) && (runRecList.size()>0)) {
				//找到最后一条未结束的运行记录
				runValuesMap = (Map<String, Object>)runRecList.get(0);
				//更新结束时间
				runValuesMap.put(EntityDefine.Run_EndTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(basicdevice.getPlctime()));
				//构造关联属性，关联到设备主记录上
				String[][] runEntityArr = {{String.valueOf(deviceMetaId),deviceBasicId}};
				runValuesMap.put("entityArr",runEntityArr);
				String[] runEntityLabel = {basicdevice.getDevicename()};
				runValuesMap.put("entityLabel",runEntityLabel);
				//id从map里移走，否则更新slor报错。
				String tempId = (String) runValuesMap.get("id");
				runValuesMap.remove("id");
				runValuesMap.put("update_user", "admin");
				runValuesMap.put("create_user", "admin");
				entityService.updateEntity(EntityDefine.RunMetaId, tempId, runValuesMap);
			}
		}
		
		//故障
		if (basicdevice.getDevicestatus().equals(Device_Error)) {
			//寻找最后一条未结束的运行记录 按时间倒序排列
			List runRecList =  entityService.dynSelect("b.id,b." + EntityDefine.Run_StartTime_CN + ",b." + EntityDefine.Run_EndTime_CN, 
					"t_entity_" + String.valueOf(deviceMetaId) +" a, t_entity_" + String.valueOf(EntityDefine.RunMetaId) +" b, r_entity c",
					"a.id='" + deviceBasicId +"' and a.id=c.ENTITY2_ID and c.META2_ID=" + String.valueOf(deviceMetaId)
						+ " and c.META1_ID=" + String.valueOf(EntityDefine.RunMetaId) + " and c.ENTITY1_ID=b.id "
						+ " and b." + EntityDefine.Run_StartTime_CN + " is not null and b." + EntityDefine.Run_EndTime_CN + " is null "
						+ " order by b." + EntityDefine.Run_StartTime_CN + "  desc");
			
			if ((runRecList!=null) && (runRecList.size()>0)) {
				//找到最后一条未结束的运行记录
				runValuesMap = (Map<String, Object>)runRecList.get(0);
				//更新结束时间
				runValuesMap.put(EntityDefine.Run_EndTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(basicdevice.getPlctime()));
				//构造关联属性，关联到设备主记录上
				String[][] runEntityArr = {{String.valueOf(deviceMetaId),deviceBasicId}};
				runValuesMap.put("entityArr",runEntityArr);
				String[] runEntityLabel = {basicdevice.getDevicename()};
				runValuesMap.put("entityLabel",runEntityLabel);
				//id从map里移走，否则更新slor报错。
				String tempId = (String) runValuesMap.get("id");
				runValuesMap.remove("id");
				runValuesMap.put("update_user", "admin");
				runValuesMap.put("create_user", "admin");
				entityService.updateEntity(EntityDefine.RunMetaId, tempId, runValuesMap);
			}
			//判断是否有未结束的故障记录
			List errorRecList =  entityService.dynSelect("b.id,b." + EntityDefine.Error_StartTime_CN + ",b." + EntityDefine.Error_EndTime_CN, 
					"t_entity_" + String.valueOf(deviceMetaId) +" a, t_entity_" + String.valueOf(EntityDefine.ErrorMetaId) +" b, r_entity c",
					"a.id='" + deviceBasicId +"' and a.id=c.ENTITY2_ID and c.META2_ID=" + String.valueOf(deviceMetaId)
						+ " and c.META1_ID=" + String.valueOf(EntityDefine.ErrorMetaId) + " and c.ENTITY1_ID=b.id "
						+ " and b." + EntityDefine.Error_StartTime_CN + " is not null and b." + EntityDefine.Error_EndTime_CN + " is null ");
			
			if ((errorRecList!=null) && (errorRecList.size()==0)) {
				//没找到，则创建故障记录
				errorValuesMap.put(EntityDefine.Error_StartTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(basicdevice.getPlctime()));
				errorValuesMap.put(EntityDefine.Error_Code_CN, String.valueOf(basicdevice.getFaultcode()));
				//构造关联属性，关联到设备主记录上
				String[][] errorEntityArr = {{String.valueOf(deviceMetaId),deviceBasicId}};
				errorValuesMap.put("entityArr",errorEntityArr);
				String[] errorEntityLabel = {basicdevice.getDevicename()};
				errorValuesMap.put("entityLabel",errorEntityLabel);
				errorValuesMap.put("update_user", "admin");
				errorValuesMap.put("create_user", "admin");
				String errorRecId = entityService.createEntity(EntityDefine.ErrorMetaId, errorValuesMap);
			}
		}
		return deviceBasicId;
	}

	
	private String createInitCutterRec(Basicdevice basicdevice, int deviceMetaId, String deviceBasicId) throws Exception{
		Map<String, Object> toolValuesMap = new HashMap<String, Object>();
		toolValuesMap.put(EntityDefine.Cutter_BeginTime_CN, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(basicdevice.getPlctime()));
		toolValuesMap.put(EntityDefine.Cutter_DesignLife_CN, "");
		toolValuesMap.put(EntityDefine.Cutter_Code_CN, basicdevice.getToolcode().intValue());
		toolValuesMap.put(EntityDefine.Cutter_ProcessCount_CN, 1);
		//构造关联属性，关联到设备主记录上
		String[][] toolEntityArr = {{String.valueOf(deviceMetaId),deviceBasicId}};
		toolValuesMap.put("entityArr",toolEntityArr);
		String[] toolEntityLabel = {basicdevice.getDevicename()};
		toolValuesMap.put("entityLabel", toolEntityLabel);
		toolValuesMap.put("update_user", "admin");
		toolValuesMap.put("create_user", "admin");
		return entityService.createEntity(EntityDefine.CutterMetaId, toolValuesMap);
	}
}
