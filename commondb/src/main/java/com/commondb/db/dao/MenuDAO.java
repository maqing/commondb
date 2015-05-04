package com.commondb.db.dao;

import com.commondb.db.bo.Menu;
import com.commondb.db.bo.MenuCriteria;
import java.util.List;

public abstract interface MenuDAO
{
  public abstract int countByExample(MenuCriteria paramMenuCriteria);
  
  public abstract int deleteByExample(MenuCriteria paramMenuCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(Menu paramMenu);
  
  public abstract void insertSelective(Menu paramMenu);
  
  public abstract List selectByExample(MenuCriteria paramMenuCriteria);
  
  public abstract Menu selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(Menu paramMenu, MenuCriteria paramMenuCriteria);
  
  public abstract int updateByExample(Menu paramMenu, MenuCriteria paramMenuCriteria);
  
  public abstract int updateByPrimaryKeySelective(Menu paramMenu);
  
  public abstract int updateByPrimaryKey(Menu paramMenu);
  
  public abstract List<Menu> getMenuByIdWithCashRole(Integer paramInteger);
  
  public abstract List<Menu> getByRoleId(Integer paramInteger);
}
