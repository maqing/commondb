package com.commondb.db.dao.impl;

import com.commondb.db.bo.Menu;
import com.commondb.db.bo.MenuCriteria;
import com.commondb.db.dao.MenuDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class MenuDAOImpl
  extends SqlMapClientDaoSupport
  implements MenuDAO
{
  public int countByExample(MenuCriteria example)
  {
    Integer count = (Integer)getSqlMapClientTemplate().queryForObject("t_menu.ibatorgenerated_countByExample", example);
    return count.intValue();
  }
  
  public int deleteByExample(MenuCriteria example)
  {
    int rows = getSqlMapClientTemplate().delete("t_menu.ibatorgenerated_deleteByExample", example);
    return rows;
  }
  
  public int deleteByPrimaryKey(Integer menuId)
  {
    Menu key = new Menu();
    key.setMenuId(menuId);
    int rows = getSqlMapClientTemplate().delete("t_menu.ibatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }
  
  public void insert(Menu record)
  {
    getSqlMapClientTemplate().insert("t_menu.ibatorgenerated_insert", record);
  }
  
  public void insertSelective(Menu record)
  {
    getSqlMapClientTemplate().insert("t_menu.ibatorgenerated_insertSelective", record);
  }
  
  public List selectByExample(MenuCriteria example)
  {
    List list = getSqlMapClientTemplate().queryForList("t_menu.ibatorgenerated_selectByExample", example);
    return list;
  }
  
  public Menu selectByPrimaryKey(Integer menuId)
  {
    Menu key = new Menu();
    key.setMenuId(menuId);
    Menu record = (Menu)getSqlMapClientTemplate().queryForObject("t_menu.ibatorgenerated_selectByPrimaryKey", key);
    return record;
  }
  
  public int updateByExampleSelective(Menu record, MenuCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_menu.ibatorgenerated_updateByExampleSelective", parms);
    return rows;
  }
  
  public int updateByExample(Menu record, MenuCriteria example)
  {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("t_menu.ibatorgenerated_updateByExample", parms);
    return rows;
  }
  
  public int updateByPrimaryKeySelective(Menu record)
  {
    int rows = getSqlMapClientTemplate().update("t_menu.ibatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }
  
  public int updateByPrimaryKey(Menu record)
  {
    int rows = getSqlMapClientTemplate().update("t_menu.ibatorgenerated_updateByPrimaryKey", record);
    return rows;
  }
  
  private static class UpdateByExampleParms
    extends MenuCriteria
  {
    private Object record;
    
    public UpdateByExampleParms(Object record, MenuCriteria example)
    {
      super();
      this.record = record;
    }
    
    public Object getRecord()
    {
      return this.record;
    }
  }
  
  public List<Menu> getMenuByIdWithCashRole(Integer menuId)
  {
    Map map = new HashMap();
    map.put("menuId", menuId);
    return getSqlMapClientTemplate().queryForList("t_menu.getMenuByIdWithCashRole", map);
  }
  
  public List<Menu> getByRoleId(Integer roleId)
  {
    return getSqlMapClientTemplate().queryForList("t_menu.getByRoleId", roleId);
  }
}
