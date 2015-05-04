package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class Menu
{
  private Integer menuId;
  private String descn;
  private String iconCls;
  private String name;
  private String qtip;
  private Integer theSort;
  private String url;
  private Integer parentId;
  private List<Role> roleList = new ArrayList();
  
  public List<Role> getRoleList()
  {
    return this.roleList;
  }
  
  public void setRoleList(List<Role> roleList)
  {
    this.roleList = roleList;
  }
  
  public String getRoleAuthorities()
  {
    List<String> roleAuthorities = new ArrayList();
    for (Role role : this.roleList) {
      roleAuthorities.add(role.getRoleName());
    }
    return StringUtils.join(roleAuthorities, ",");
  }
  
  public Integer getMenuId()
  {
    return this.menuId;
  }
  
  public void setMenuId(Integer menuId)
  {
    this.menuId = menuId;
  }
  
  public String getDescn()
  {
    return this.descn;
  }
  
  public void setDescn(String descn)
  {
    this.descn = descn;
  }
  
  public String getIconCls()
  {
    return this.iconCls;
  }
  
  public void setIconCls(String iconCls)
  {
    this.iconCls = iconCls;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getQtip()
  {
    return this.qtip;
  }
  
  public void setQtip(String qtip)
  {
    this.qtip = qtip;
  }
  
  public Integer getTheSort()
  {
    return this.theSort;
  }
  
  public void setTheSort(Integer theSort)
  {
    this.theSort = theSort;
  }
  
  public String getUrl()
  {
    return this.url;
  }
  
  public void setUrl(String url)
  {
    this.url = url;
  }
  
  public Integer getParentId()
  {
    return this.parentId;
  }
  
  public void setParentId(Integer parentId)
  {
    this.parentId = parentId;
  }
}
