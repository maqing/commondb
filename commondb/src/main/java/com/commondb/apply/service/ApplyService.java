package com.commondb.apply.service;

import com.commondb.apply.bo.Apply;
import com.commondb.apply.bo.ApplyCriteria;
import com.commondb.apply.bo.ApplyMenu;
import com.commondb.apply.bo.ApplyMenuCriteria;
import com.commondb.apply.bo.ApplyUser;
import com.commondb.apply.bo.MetaUser;
import com.commondb.common.PageInfo;
import com.commondb.db.bo.Meta;
import java.util.List;
import java.util.Map;

public abstract interface ApplyService
{
  public abstract List<Apply> getApplyAllList();
  
  public abstract List<Apply> getApplyList(ApplyCriteria paramApplyCriteria);
  
  public abstract PageInfo getApplyList(ApplyCriteria paramApplyCriteria, PageInfo paramPageInfo);
  
  public abstract Apply getApplyById(Integer paramInteger);
  
  public abstract void updateApply(Apply paramApply);
  
  public abstract void insertApply(Apply paramApply);
  
  public abstract void deleteApply(Integer paramInteger);
  
  public abstract List<Apply> headerTop();
  
  public abstract PageInfo getApplyMenuList(ApplyMenuCriteria paramApplyMenuCriteria, PageInfo paramPageInfo);
  
  public abstract ApplyMenu getApplyMenuById(Integer paramInteger);
  
  public abstract void updateApplyMenu(ApplyMenu paramApplyMenu);
  
  public abstract void insertApplyMenu(ApplyMenu paramApplyMenu);
  
  public abstract void deleteApplyMenu(Integer paramInteger);
  
  public abstract List<ApplyMenu> getApplyMenuByApplyId(Integer paramInteger);
  
  public abstract void addApplyUser(ApplyUser paramApplyUser);
  
  public abstract List<ApplyUser> getApplyUserByUserId(Integer paramInteger);
  
  public abstract List<ApplyUser> getApplyUserByUserIdAndApplyId(Integer paramInteger1, Integer paramInteger2);
  
  public abstract void deleteApplyUser(Integer paramInteger);
  
  public abstract List<Meta> getMetaAllList();
  
  public abstract List<MetaUser> getMetaUserByUserId(Integer paramInteger);
  
  public abstract void deleteMetaUser(Integer paramInteger);
  
  public abstract void addMetaUser(MetaUser paramMetaUser);
  
  public abstract Map headerUser();
}
