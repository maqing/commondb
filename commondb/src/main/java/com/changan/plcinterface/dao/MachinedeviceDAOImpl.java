package com.changan.plcinterface.dao;

import com.changan.plcinterface.model.Machinedevice;
import com.changan.plcinterface.model.MachinedeviceExample;
import com.changan.plcinterface.model.MachinedeviceKey;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class MachinedeviceDAOImpl extends SqlMapClientDaoSupport implements MachinedeviceDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table machinedevice
     *
     * @abatorgenerated Sat May 07 17:10:27 CST 2016
     */
    public MachinedeviceDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table machinedevice
     *
     * @abatorgenerated Sat May 07 17:10:27 CST 2016
     */
    public void insert(Machinedevice record) {
        getSqlMapClientTemplate().insert("machinedevice.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table machinedevice
     *
     * @abatorgenerated Sat May 07 17:10:27 CST 2016
     */
    public int updateByPrimaryKey(Machinedevice record) {
        int rows = getSqlMapClientTemplate().update("machinedevice.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table machinedevice
     *
     * @abatorgenerated Sat May 07 17:10:27 CST 2016
     */
    public int updateByPrimaryKeySelective(Machinedevice record) {
        int rows = getSqlMapClientTemplate().update("machinedevice.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table machinedevice
     *
     * @abatorgenerated Sat May 07 17:10:27 CST 2016
     */
    public List selectByExample(MachinedeviceExample example) {
        List list = getSqlMapClientTemplate().queryForList("machinedevice.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table machinedevice
     *
     * @abatorgenerated Sat May 07 17:10:27 CST 2016
     */
    public List selectByExamplePage(MachinedeviceExample example) {
        List list = getSqlMapClientTemplate().queryForList("machinedevice.abatorgenerated_selectByExamplePage", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table machinedevice
     *
     * @abatorgenerated Sat May 07 17:10:27 CST 2016
     */
    public Machinedevice selectByPrimaryKey(MachinedeviceKey key) {
        Machinedevice record = (Machinedevice) getSqlMapClientTemplate().queryForObject("machinedevice.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table machinedevice
     *
     * @abatorgenerated Sat May 07 17:10:27 CST 2016
     */
    public int deleteByExample(MachinedeviceExample example) {
        int rows = getSqlMapClientTemplate().delete("machinedevice.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table machinedevice
     *
     * @abatorgenerated Sat May 07 17:10:27 CST 2016
     */
    public int deleteByPrimaryKey(MachinedeviceKey key) {
        int rows = getSqlMapClientTemplate().delete("machinedevice.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table machinedevice
     *
     * @abatorgenerated Sat May 07 17:10:27 CST 2016
     */
    public int countByExample(MachinedeviceExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("machinedevice.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table machinedevice
     *
     * @abatorgenerated Sat May 07 17:10:27 CST 2016
     */
    public int updateByExampleSelective(Machinedevice record, MachinedeviceExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("machinedevice.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table machinedevice
     *
     * @abatorgenerated Sat May 07 17:10:27 CST 2016
     */
    public int updateByExample(Machinedevice record, MachinedeviceExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("machinedevice.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table machinedevice
     *
     * @abatorgenerated Sat May 07 17:10:27 CST 2016
     */
    private static class UpdateByExampleParms extends MachinedeviceExample {
        private Object record;

        public UpdateByExampleParms(Object record, MachinedeviceExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}