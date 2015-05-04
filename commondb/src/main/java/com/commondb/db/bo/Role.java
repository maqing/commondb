package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.List;

public class Role
{
  private Integer roleId;
  private String roleName;
  private String roleDesc;
  private List<User> userList = new ArrayList();
  private List<Meta> metaList = new ArrayList();
  private List<Resc> rescList = new ArrayList();
  private List<Menu> menuList = new ArrayList();
  
  public List<Resc> getRescList()
  {
    return this.rescList;
  }
  
  public void setRescList(List<Resc> rescList)
  {
    this.rescList = rescList;
  }
  
  public List<Menu> getMenuList()
  {
    return this.menuList;
  }
  
  public void setMenuList(List<Menu> menuList)
  {
    this.menuList = menuList;
  }
  
  public String toString()
  {
    return 
    



      "Role{id=" + this.roleId + ", rolename='" + this.roleName + '\'' + ", descp='" + this.roleDesc + '\'' + ", userList=" + this.userList.size() + '}';
  }
  
  public String out()
  {
    StringBuffer sb = new StringBuffer();
    if (this.userList.size() > 0)
    {
      sb.append("Role{id=" + 
        this.roleId + 
        ", rolename='" + this.roleName + '\'' + 
        ", descp='" + this.roleDesc + '\'' + 
        ", userList=" + this.userList.size());
      for (User u : this.userList) {
        sb.append("\n\t").append(u.toString());
      }
      sb.append("\n}");
    }
    return sb.toString();
  }
  
  public List<Meta> getMetaList()
  {
    return this.metaList;
  }
  
  public void setMetaList(List<Meta> metaList)
  {
    this.metaList = metaList;
  }
  
  public List<User> getUserList()
  {
    return this.userList;
  }
  
  public void setUserList(List<User> userList)
  {
    this.userList = userList;
  }
  
  public Integer getRoleId()
  {
    return this.roleId;
  }
  
  public void setRoleId(Integer roleId)
  {
    this.roleId = roleId;
  }
  
  public String getRoleName()
  {
    return this.roleName;
  }
  
  public void setRoleName(String roleName)
  {
    this.roleName = roleName;
  }
  
  public String getRoleDesc()
  {
    return this.roleDesc;
  }
  
  public void setRoleDesc(String roleDesc)
  {
    this.roleDesc = roleDesc;
  }
}
