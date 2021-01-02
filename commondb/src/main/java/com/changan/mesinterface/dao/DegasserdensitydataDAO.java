package com.changan.mesinterface.dao;

import com.changan.mesinterface.model.Degasserdensitydata;
import com.changan.mesinterface.model.DegasserdensitydataExample;
import java.util.List;

public interface DegasserdensitydataDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table degasserdensitydata
     *
     * @abatorgenerated Thu Dec 17 20:09:51 CST 2020
     */
    void insert(Degasserdensitydata record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table degasserdensitydata
     *
     * @abatorgenerated Thu Dec 17 20:09:51 CST 2020
     */
    int updateByPrimaryKey(Degasserdensitydata record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table degasserdensitydata
     *
     * @abatorgenerated Thu Dec 17 20:09:51 CST 2020
     */
    int updateByPrimaryKeySelective(Degasserdensitydata record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table degasserdensitydata
     *
     * @abatorgenerated Thu Dec 17 20:09:51 CST 2020
     */
    List selectByExample(DegasserdensitydataExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table degasserdensitydata
     *
     * @abatorgenerated Thu Dec 17 20:09:51 CST 2020
     */
    Degasserdensitydata selectByPrimaryKey(Integer fId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table degasserdensitydata
     *
     * @abatorgenerated Thu Dec 17 20:09:51 CST 2020
     */
    int deleteByExample(DegasserdensitydataExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table degasserdensitydata
     *
     * @abatorgenerated Thu Dec 17 20:09:51 CST 2020
     */
    int deleteByPrimaryKey(Integer fId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table degasserdensitydata
     *
     * @abatorgenerated Thu Dec 17 20:09:51 CST 2020
     */
    int countByExample(DegasserdensitydataExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table degasserdensitydata
     *
     * @abatorgenerated Thu Dec 17 20:09:51 CST 2020
     */
    int updateByExampleSelective(Degasserdensitydata record, DegasserdensitydataExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table degasserdensitydata
     *
     * @abatorgenerated Thu Dec 17 20:09:51 CST 2020
     */
    int updateByExample(Degasserdensitydata record, DegasserdensitydataExample example);
}