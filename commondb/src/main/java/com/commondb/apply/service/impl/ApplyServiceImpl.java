package com.commondb.apply.service.impl;

import com.commondb.apply.bo.Apply;
import com.commondb.apply.bo.ApplyCriteria;
import com.commondb.apply.bo.ApplyCriteria.Criteria;
import com.commondb.apply.bo.ApplyMenu;
import com.commondb.apply.bo.ApplyMenuCriteria;
//import com.commondb.apply.bo.ApplyMenuCriteria.Criteria;
import com.commondb.apply.bo.ApplyUser;
import com.commondb.apply.bo.ApplyUserCriteria;
//import com.commondb.apply.bo.ApplyUserCriteria.Criteria;
import com.commondb.apply.bo.MetaUser;
import com.commondb.apply.bo.MetaUserCriteria;
//import com.commondb.apply.bo.MetaUserCriteria.Criteria;
import com.commondb.apply.dao.ApplyDAO;
import com.commondb.apply.dao.ApplyMenuDAO;
import com.commondb.apply.dao.ApplyUserDAO;
import com.commondb.apply.dao.MetaUserDAO;
import com.commondb.apply.service.ApplyService;
import com.commondb.common.PageInfo;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.MetaCriteria;
import com.commondb.db.bo.User;
import com.commondb.db.dao.MetaDAO;
import com.commondb.security.service.SecurityUserHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplyServiceImpl
  implements ApplyService
{
  private ApplyDAO applyDAO;
  private ApplyMenuDAO applyMenuDAO;
  private ApplyUserDAO applyUserDAO;
  private MetaUserDAO metaUserDAO;
  private ApplyService applyService;
  private MetaDAO metaDAO;
  private List<ApplyUser> topList;
  private User user;

  public User getUser()
  {
    return this.user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public ApplyUserDAO getApplyUserDAO()
  {
    return this.applyUserDAO;
  }

  public void setApplyUserDAO(ApplyUserDAO applyUserDAO)
  {
    this.applyUserDAO = applyUserDAO;
  }

  public ApplyMenuDAO getApplyMenuDAO()
  {
    return this.applyMenuDAO;
  }

  public void setApplyMenuDAO(ApplyMenuDAO applyMenuDAO)
  {
    this.applyMenuDAO = applyMenuDAO;
  }

  public ApplyDAO getApplyDAO()
  {
    return this.applyDAO;
  }

  public void setApplyDAO(ApplyDAO applyDAO)
  {
    this.applyDAO = applyDAO;
  }

  public Map headerUser()
  {
    Map hMap = new HashMap();
    this.user = SecurityUserHolder.getCurrentUser();
    hMap.put("userDesc", this.user.getUserDesc());
    hMap.put("userRole", this.user.getAuthoritiesDescString());
    return hMap;
  }

  public List<Apply> headerTop()
  {
    this.user = SecurityUserHolder.getCurrentUser();

    this.topList = this.applyService.getApplyUserByUserId(this.user.getUserId());
    List<Integer> applyIdList = new ArrayList();
    List<Apply> tList = null;
    for (ApplyUser au : this.topList) {
      if (!applyIdList.contains(au.getApplyId())) {
        applyIdList.add(au.getApplyId());
      }
    }
    if (applyIdList.size() > 0)
    {
      ApplyCriteria cc = new ApplyCriteria();
      cc.createCriteria().andApplyIdIn(applyIdList);
      tList = this.applyService.getApplyList(cc);
    }
    return tList;
  }

  public List<Apply> getApplyAllList()
  {
    ApplyCriteria example = new ApplyCriteria();
    example.setOrderByClause("num asc");
    example.createCriteria();
    return this.applyDAO.selectByExample(example);
  }

  public List<Apply> getApplyList(ApplyCriteria example)
  {
    example.setOrderByClause("num asc");
    example.createCriteria();
    return this.applyDAO.selectByExample(example);
  }

  public PageInfo getApplyList(ApplyCriteria example, PageInfo page)
  {
    if (page == null) {
      page = new PageInfo();
    }
    example.setOrderByClause("num asc");
    page.setTotalRows(this.applyDAO.countByExample(example));
    if (page.getCurrentPage() <= 0) {
      page.setCurrentPage(1);
    } else if (page.getCurrentPage() > page.getTotalPages()) {
      page.setCurrentPage(page.getTotalPages());
    }
    example.setLimit(Integer.valueOf(page.getNumPerPage()));
    example.setOffset(Integer.valueOf(page.getStartIndex()));
    List<Apply> aList = this.applyDAO.selectByExample(example);
    page.setResultList(aList);
    return page;
  }

  public Apply getApplyById(Integer applyId)
  {
    return this.applyDAO.selectByPrimaryKey(applyId);
  }

  public void updateApply(Apply apply)
  {
    this.applyDAO.updateByPrimaryKeySelective(apply);
  }

  public void insertApply(Apply apply)
  {
    this.applyDAO.insert(apply);
  }

  public void deleteApply(Integer applyId)
  {
    this.applyDAO.deleteByPrimaryKey(applyId);
  }

  public PageInfo getApplyMenuList(ApplyMenuCriteria example, PageInfo page)
  {
    if (page == null) {
      page = new PageInfo();
    }
    example.setOrderByClause("apply_id asc , num asc");
    page.setTotalRows(this.applyMenuDAO.countByExample(example));
    if (page.getCurrentPage() <= 0) {
      page.setCurrentPage(1);
    } else if (page.getCurrentPage() > page.getTotalPages()) {
      page.setCurrentPage(page.getTotalPages());
    }
    example.setLimit(Integer.valueOf(page.getNumPerPage()));
    example.setOffset(Integer.valueOf(page.getStartIndex()));
    List<ApplyMenu> aList = this.applyMenuDAO.selectByExample(example);
    page.setResultList(aList);
    return page;
  }

  public ApplyMenu getApplyMenuById(Integer applyMenuId)
  {
    return this.applyMenuDAO.selectByPrimaryKey(applyMenuId);
  }

  public void updateApplyMenu(ApplyMenu applyMenu)
  {
    this.applyMenuDAO.updateByPrimaryKeySelective(applyMenu);
  }

  public void insertApplyMenu(ApplyMenu applyMenu)
  {
    this.applyMenuDAO.insert(applyMenu);
  }

  public void deleteApplyMenu(Integer applyMenuId)
  {
    this.applyMenuDAO.deleteByPrimaryKey(applyMenuId);
  }

  public List<ApplyMenu> getApplyMenuByApplyId(Integer applyId)
  {
    ApplyMenuCriteria example = new ApplyMenuCriteria();
    example.createCriteria().andApplyIdEqualTo(applyId);
    List<ApplyMenu> applyList = this.applyMenuDAO.selectByExample(example);
    return applyList;
  }

  public void addApplyUser(ApplyUser applyUser)
  {
    this.applyUserDAO.insert(applyUser);
  }

  public List<ApplyUser> getApplyUserByUserId(Integer userId)
  {
    ApplyUserCriteria example = new ApplyUserCriteria();
    example.createCriteria().andUserIdEqualTo(userId);
    List<ApplyUser> aList = this.applyUserDAO.selectByExample(example);
    return aList;
  }

  public void deleteApplyUser(Integer auId)
  {
    this.applyUserDAO.deleteByPrimaryKey(auId);
  }

  public List<ApplyUser> getApplyUserByUserIdAndApplyId(Integer userId, Integer applyId)
  {
    ApplyUserCriteria example = new ApplyUserCriteria();
    example.createCriteria().andApplyIdEqualTo(applyId).andUserIdEqualTo(userId);
    List<ApplyUser> applyList = this.applyUserDAO.selectByExample(example);
    return applyList;
  }

  public List<Meta> getMetaAllList()
  {
    MetaCriteria example = new MetaCriteria();
    example.createCriteria();
    return this.metaDAO.selectByExample(example);
  }

  public List<MetaUser> getMetaUserByUserId(Integer userId)
  {
    MetaUserCriteria example = new MetaUserCriteria();
    example.createCriteria().andUserIdEqualTo(userId);
    List<MetaUser> aList = this.metaUserDAO.selectByExample(example);
    return aList;
  }

  public void deleteMetaUser(Integer uId)
  {
    this.metaUserDAO.deleteByPrimaryKey(uId);
  }

  public void addMetaUser(MetaUser metaUser)
  {
    this.metaUserDAO.insert(metaUser);
  }

  public ApplyService getApplyService()
  {
    return this.applyService;
  }

  public void setApplyService(ApplyService applyService)
  {
    this.applyService = applyService;
  }

  public List<ApplyUser> getTopList()
  {
    return this.topList;
  }

  public void setTopList(List<ApplyUser> topList)
  {
    this.topList = topList;
  }

  public MetaDAO getMetaDAO()
  {
    return this.metaDAO;
  }

  public void setMetaDAO(MetaDAO metaDAO)
  {
    this.metaDAO = metaDAO;
  }

  public MetaUserDAO getMetaUserDAO()
  {
    return this.metaUserDAO;
  }

  public void setMetaUserDAO(MetaUserDAO metaUserDAO)
  {
    this.metaUserDAO = metaUserDAO;
  }
}
