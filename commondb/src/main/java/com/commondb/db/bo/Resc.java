package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class Resc
{
  private Integer rescId;
  private String descn;
  private String name;
  private String resString;
  private String resType;
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
  
  public Integer getRescId()
  {
    return this.rescId;
  }
  
  public void setRescId(Integer rescId)
  {
    this.rescId = rescId;
  }
  
  public String getDescn()
  {
    return this.descn;
  }
  
  public void setDescn(String descn)
  {
    this.descn = descn;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getResString()
  {
    return this.resString;
  }
  
  public void setResString(String resString)
  {
    this.resString = resString;
  }
  
  public String getResType()
  {
    return this.resType;
  }
  
  public void setResType(String resType)
  {
    this.resType = resType;
  }
}
