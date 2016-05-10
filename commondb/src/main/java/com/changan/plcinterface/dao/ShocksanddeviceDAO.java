package com.changan.plcinterface.dao;

import com.changan.plcinterface.model.Shocksanddevice;
import com.changan.plcinterface.model.ShocksanddeviceExample;
import com.changan.plcinterface.model.ShocksanddeviceKey;
import java.util.List;

public interface ShocksanddeviceDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table shocksanddevice
	 * @abatorgenerated  Sat May 07 23:28:29 CST 2016
	 */
	void insert(Shocksanddevice record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table shocksanddevice
	 * @abatorgenerated  Sat May 07 23:28:29 CST 2016
	 */
	int updateByPrimaryKey(Shocksanddevice record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table shocksanddevice
	 * @abatorgenerated  Sat May 07 23:28:29 CST 2016
	 */
	int updateByPrimaryKeySelective(Shocksanddevice record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table shocksanddevice
	 * @abatorgenerated  Sat May 07 23:28:29 CST 2016
	 */
	List selectByExample(ShocksanddeviceExample example);

	List selectByExamplePage(ShocksanddeviceExample example);
	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table shocksanddevice
	 * @abatorgenerated  Sat May 07 23:28:29 CST 2016
	 */
	Shocksanddevice selectByPrimaryKey(ShocksanddeviceKey key);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table shocksanddevice
	 * @abatorgenerated  Sat May 07 23:28:29 CST 2016
	 */
	int deleteByExample(ShocksanddeviceExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table shocksanddevice
	 * @abatorgenerated  Sat May 07 23:28:29 CST 2016
	 */
	int deleteByPrimaryKey(ShocksanddeviceKey key);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table shocksanddevice
	 * @abatorgenerated  Sat May 07 23:28:29 CST 2016
	 */
	int countByExample(ShocksanddeviceExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table shocksanddevice
	 * @abatorgenerated  Sat May 07 23:28:29 CST 2016
	 */
	int updateByExampleSelective(Shocksanddevice record,
			ShocksanddeviceExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table shocksanddevice
	 * @abatorgenerated  Sat May 07 23:28:29 CST 2016
	 */
	int updateByExample(Shocksanddevice record, ShocksanddeviceExample example);
}