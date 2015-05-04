package com.commondb.apply.web;

import com.commondb.apply.bo.Apply;
import com.commondb.apply.bo.ApplyCriteria;
import com.commondb.apply.bo.ApplyMenu;
import com.commondb.apply.bo.ApplyMenuCriteria;
import com.commondb.apply.bo.ApplyUser;
import com.commondb.apply.bo.MetaUser;
import com.commondb.apply.service.ApplyService;
import com.commondb.common.DateUtil;
import com.commondb.common.MD5;
import com.commondb.common.PageInfo;
import com.commondb.db.bo.Meta;
import com.commondb.db.bo.User;
import com.commondb.security.service.SecurityUserHolder;
import com.commondb.security.service.UserService;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.struts2.ServletActionContext;

public class ApplyAction
{
  private ApplyService applyService;
  private Apply apply;
  private Integer applyId;
  private List<Apply> applyList;
  private List<ApplyUser> auList;
  private List<ApplyUser> topList;
  private List<Apply> tList;
  private List<Meta> metaList;
  private List<MetaUser> metauList;
  private ApplyMenu applyMenu;
  private Integer applyMenuId;
  private List<ApplyMenu> applyMenuList;
  private PageInfo page = new PageInfo();
  private UserService userService;
  private User user;
  private User userr;
  private Integer userId;
  private List<User> uRList;
  private String[] checkMenuId;
  private String[] checkMetaId;
  public String message;
  private String excelPath;
  
  public String applyR()
  {
    ApplyCriteria ac = new ApplyCriteria();
    this.page.setNumPerPage(15);
    this.applyService.getApplyList(ac, this.page);
    this.applyList = this.page.getResultList();
    
    return "success";
  }
  
  public String preAddApplyR()
  {
    if (this.applyId != null) {
      this.apply = this.applyService.getApplyById(this.applyId);
    }
    return "success";
  }
  
  public String addApplyR()
  {
    if (this.apply.getApplyId() != null) {
      this.applyService.updateApply(this.apply);
    } else {
      this.applyService.insertApply(this.apply);
    }
    return "success";
  }
  
  public String deleteApplyR()
  {
    if (this.applyId != null) {
      this.applyService.deleteApply(this.applyId);
    }
    return "success";
  }
  
  public String applyMenu()
  {
    ApplyMenuCriteria ac = new ApplyMenuCriteria();
    this.page.setNumPerPage(15);
    this.applyService.getApplyMenuList(ac, this.page);
    this.applyMenuList = this.page.getResultList();
    
    return "success";
  }
  
  public String preAddApplyMenu()
  {
    this.applyList = this.applyService.getApplyAllList();
    if (this.applyMenuId != null) {
      this.applyMenu = this.applyService.getApplyMenuById(this.applyMenuId);
    }
    return "success";
  }
  
  public String addApplyMenu()
  {
    if (this.applyMenu.getApplyMenuId() != null) {
      this.applyService.updateApplyMenu(this.applyMenu);
    } else {
      this.applyService.insertApplyMenu(this.applyMenu);
    }
    return "success";
  }
  
  public String deleteApplyMenu()
  {
    if (this.applyMenuId != null) {
      this.applyService.deleteApplyMenu(this.applyMenuId);
    }
    return "success";
  }
  
  public String searchUserList()
  {
    this.uRList = this.userService.getAllUser(this.user);
    
    return "success";
  }
  
  public String preUserJueSe()
  {
    this.user = this.userService.findUserById(this.userId);
    this.applyList = this.applyService.getApplyAllList();
    for (Apply app : this.applyList)
    {
      this.applyMenuList = this.applyService.getApplyMenuByApplyId(app.getApplyId());
      app.setAmenuList(this.applyMenuList);
    }
    this.auList = this.applyService.getApplyUserByUserId(this.userId);
    return "success";
  }
  
  public String setUserApplyMenu()
  {
    this.auList = this.applyService.getApplyUserByUserId(this.userId);
    if (this.auList.size() > 0) {
      for (ApplyUser u : this.auList) {
        this.applyService.deleteApplyUser(u.getId());
      }
    }
    if (this.checkMenuId != null)
    {
      for (String did : this.checkMenuId)
      {
        String[] sa = did.split(",");
        
        ApplyUser am = new ApplyUser();
        am.setUserId(this.userId);
        am.setApplyId(new Integer(sa[0]));
        am.setApplyMenuId(new Integer(sa[1]));
        this.applyService.addApplyUser(am);
      }
      this.message = (" " + this.userService.findUserById(this.userId).getUserDesc() + " " + "权限设置成功");
      return "set";
    }
    this.message = (" " + this.userService.findUserById(this.userId).getUserDesc() + " " + "没有选择任何菜单");
    return "noSet";
  }
  
  public String searchUserMetaList()
  {
    this.user = SecurityUserHolder.getCurrentUser();
    this.user = this.userService.findUserById(this.user.getUserId());
    this.metaList = this.applyService.getMetaAllList();
    
    this.metauList = this.applyService.getMetaUserByUserId(this.user.getUserId());
    
    return "success";
  }
  
  public String setUserMeta()
  {
    this.user = SecurityUserHolder.getCurrentUser();
    this.metauList = this.applyService.getMetaUserByUserId(this.user.getUserId());
    if (this.metauList.size() > 0) {
      for (MetaUser u : this.metauList) {
        this.applyService.deleteMetaUser(u.getId());
      }
    }
    if (this.checkMetaId != null)
    {
      for (String did : this.checkMetaId)
      {
        MetaUser mu = new MetaUser();
        mu.setUserId(this.user.getUserId());
        mu.setMetaId(new Integer(did));
        this.applyService.addMetaUser(mu);
      }
      this.message = "设置成功";
      return "set";
    }
    this.message = "没有选择任何项";
    return "noSet";
  }
  
  public String preEditPassword()
  {
    this.user = SecurityUserHolder.getCurrentUser();
    this.user = this.userService.findUserById(this.user.getUserId());
    
    return "success";
  }
  
  public String editPassword()
  {
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    

    this.user = SecurityUserHolder.getCurrentUser();
    String oldpass = request.getParameter("oldpass");
    String newpass = this.userr.getPwd();
    this.user = this.userService.findUserById(this.user.getUserId());
    if (this.user.getPwd().equals(MD5.getMD5(oldpass.getBytes())))
    {
      this.user.setPwd(MD5.getMD5(newpass.getBytes()));
      this.userService.updateUser(this.user);
      this.message = "密码修改成功";
      return "suc";
    }
    this.message = "您输入的旧密码有误，请重新输入";
    return "error";
  }
  
  public String exMetaFromExcel()
  {
    Date now = new Date();
    String randomPath = DateUtil.formatDate(now, "yyyy-MM-dd-HH-mm");
    String serverLocalPath = ServletActionContext.getServletContext().getRealPath("doc") + "/excel/" + randomPath + ".xls";
    
    String worksheet = "导出的库信息";
    
























































    return null;
  }
  
  public String preMetaFromExcel()
  {
    return "success";
  }
  
  public String addMetaFromExcel()
  {
    Workbook book = null;
    Sheet sheet = null;
    try
    {
      FileInputStream in = new FileInputStream(this.excelPath);
      book = Workbook.getWorkbook(in);
      sheet = book.getSheet(0);
      for (int i = 1; i < sheet.getRows(); i++) {
        try
        {
          Apply cf = new Apply();
          cf.setApplyName(sheet.getCell(0, i).getContents());
          cf.setApplyId(new Integer(sheet.getCell(1, i).getContents()));
          cf.setApplyUrl(sheet.getCell(2, i).getContents());
        }
        catch (Exception e)
        {
          this.message += " 导入失败";
          e.printStackTrace();
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      this.message += " 导入失败";
    }
    finally
    {
      if (sheet != null) {
        sheet = null;
      }
      if (book != null) {
        book = null;
      }
    }
    return "success";
  }
  
  public ApplyService getApplyService()
  {
    return this.applyService;
  }
  
  public void setApplyService(ApplyService applyService)
  {
    this.applyService = applyService;
  }
  
  public Apply getApply()
  {
    return this.apply;
  }
  
  public void setApply(Apply apply)
  {
    this.apply = apply;
  }
  
  public List<Apply> getApplyList()
  {
    return this.applyList;
  }
  
  public void setApplyList(List<Apply> applyList)
  {
    this.applyList = applyList;
  }
  
  public PageInfo getPage()
  {
    return this.page;
  }
  
  public void setPage(PageInfo page)
  {
    this.page = page;
  }
  
  public Integer getApplyId()
  {
    return this.applyId;
  }
  
  public void setApplyId(Integer applyId)
  {
    this.applyId = applyId;
  }
  
  public ApplyMenu getApplyMenu()
  {
    return this.applyMenu;
  }
  
  public void setApplyMenu(ApplyMenu applyMenu)
  {
    this.applyMenu = applyMenu;
  }
  
  public Integer getApplyMenuId()
  {
    return this.applyMenuId;
  }
  
  public void setApplyMenuId(Integer applyMenuId)
  {
    this.applyMenuId = applyMenuId;
  }
  
  public List<ApplyMenu> getApplyMenuList()
  {
    return this.applyMenuList;
  }
  
  public void setApplyMenuList(List<ApplyMenu> applyMenuList)
  {
    this.applyMenuList = applyMenuList;
  }
  
  public UserService getUserService()
  {
    return this.userService;
  }
  
  public void setUserService(UserService userService)
  {
    this.userService = userService;
  }
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public Integer getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
  
  public List<User> getURList()
  {
    return this.uRList;
  }
  
  public void setURList(List<User> list)
  {
    this.uRList = list;
  }
  
  public String[] getCheckMenuId()
  {
    return this.checkMenuId;
  }
  
  public void setCheckMenuId(String[] checkMenuId)
  {
    this.checkMenuId = checkMenuId;
  }
  
  public List<ApplyUser> getAuList()
  {
    return this.auList;
  }
  
  public void setAuList(List<ApplyUser> auList)
  {
    this.auList = auList;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
  }
  
  public List<ApplyUser> getTopList()
  {
    return this.topList;
  }
  
  public void setTopList(List<ApplyUser> topList)
  {
    this.topList = topList;
  }
  
  public List<Apply> getTList()
  {
    return this.tList;
  }
  
  public void setTList(List<Apply> list)
  {
    this.tList = list;
  }
  
  public String getExcelPath()
  {
    return this.excelPath;
  }
  
  public void setExcelPath(String excelPath)
  {
    this.excelPath = excelPath;
  }
  
  public User getUserr()
  {
    return this.userr;
  }
  
  public void setUserr(User userr)
  {
    this.userr = userr;
  }
  
  public List<Meta> getMetaList()
  {
    return this.metaList;
  }
  
  public void setMetaList(List<Meta> metaList)
  {
    this.metaList = metaList;
  }
  
  public String[] getCheckMetaId()
  {
    return this.checkMetaId;
  }
  
  public void setCheckMetaId(String[] checkMetaId)
  {
    this.checkMetaId = checkMetaId;
  }
  
  public List<MetaUser> getMetauList()
  {
    return this.metauList;
  }
  
  public void setMetauList(List<MetaUser> metauList)
  {
    this.metauList = metauList;
  }
}
