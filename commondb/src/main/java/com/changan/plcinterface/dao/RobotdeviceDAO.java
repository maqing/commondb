package com.changan.plcinterface.dao;

import com.changan.plcinterface.model.Robotdevice;
import com.changan.plcinterface.model.RobotdeviceExample;
import com.changan.plcinterface.model.RobotdeviceKey;
import java.util.List;

public interface RobotdeviceDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table robotdevice
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    void insert(Robotdevice record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table robotdevice
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    int updateByPrimaryKey(Robotdevice record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table robotdevice
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    int updateByPrimaryKeySelective(Robotdevice record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table robotdevice
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    List selectByExample(RobotdeviceExample example);

    List selectByExamplePage(RobotdeviceExample example);
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table robotdevice
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    Robotdevice selectByPrimaryKey(RobotdeviceKey key);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table robotdevice
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    int deleteByExample(RobotdeviceExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table robotdevice
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    int deleteByPrimaryKey(RobotdeviceKey key);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table robotdevice
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    int countByExample(RobotdeviceExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table robotdevice
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    int updateByExampleSelective(Robotdevice record, RobotdeviceExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table robotdevice
     *
     * @abatorgenerated Sat May 07 22:35:29 CST 2016
     */
    int updateByExample(Robotdevice record, RobotdeviceExample example);
}