package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

public class User
  implements UserDetails
{
  private Integer userId;
  private String userName;
  private String pwd;
  private String userDesc;
  private Boolean disabled;
  private List<Role> roleList = new ArrayList();
  private Map<String, List<Resc>> roleResources;
  private Map<String, List> metas;

  public String toString()
  {
    return




      "User{id=" + this.userId + ", username='" + this.userName + '\'' + ", userDesc='" + this.userDesc + '\'' + ", roleList='" + this.roleList.size() + '\'' + '}';
  }

  public String out()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("User{id=" +
      this.userId +
      ", username='" + this.userName + '\'' +
      ", userDesc='" + this.userDesc + '\'' +
      ", roleList='" + this.roleList.size() + '\'');
    for (Role role : this.roleList) {
      sb.append("\n\t").append(role.toString());
    }
    return sb.toString();
  }

  public GrantedAuthority[] getAuthorities()
  {
    List<GrantedAuthority> grantedAuthorities = new ArrayList(this.roleList.size() + 1);
    grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_AUTHENTICATEDCOMMON"));
    for (Role role : this.roleList) {
      grantedAuthorities.add(new GrantedAuthorityImpl(role.getRoleName()));
    }
    return (GrantedAuthority[])grantedAuthorities.toArray(new GrantedAuthority[this.roleList.size() + 1]);
  }

  public String getAuthoritiesString()
  {
    List<String> authorities = new ArrayList();
    for (GrantedAuthority authority : getAuthorities()) {
      authorities.add(authority.getAuthority());
    }
    return StringUtils.join(authorities, ",");
  }

  public String getAuthoritiesDescString()
  {
    List<String> authorities = new ArrayList();
    for (Role role : this.roleList) {
      authorities.add(role.getRoleDesc());
    }
    return StringUtils.join(authorities, ",");
  }

  public Map<String, List<Resc>> getRoleResources()
  {

      // init roleResources for the first time
      if(this.roleResources == null) {
          this.roleResources = new HashMap<String, List<Resc>>();

          for(Role role : this.roleList) {
              String roleName = role.getRoleName();
              List<Resc> resources = role.getRescList();
              for(Resc resource : resources) {
                  String key = roleName + "_" + resource.getResType();
                  if(!this.roleResources.containsKey(key)) {
                          this.roleResources.put(key, new ArrayList<Resc>());
                  }
                  this.roleResources.get(key).add(resource);
              }
          }

      }
      return this.roleResources;

	/*
  if (this.roleResources == null)
    {
      this.roleResources = new HashMap();
      Iterator localIterator2;
      for (Iterator localIterator1 = this.roleList.iterator(); localIterator1.hasNext(); localIterator2.hasNext())
      {
        Role role = (Role)localIterator1.next();
        String roleName = role.getRoleName();
        List<Resc> resources = role.getRescList();
        localIterator2 = resources.iterator(); continue;Resc resource = (Resc)localIterator2.next();
        String key = roleName + "_" + resource.getResType();
        if (!this.roleResources.containsKey(key)) {
          this.roleResources.put(key, new ArrayList());
        }
        ((List)this.roleResources.get(key)).add(resource);
      }
    }

    return this.roleResources;
    */
  }

  public Map<String, List> getMetas()
  {

    if (this.metas == null)
	    {
	      this.metas = new HashMap();
	      List metaIdList = new ArrayList();
	      List metaIdAndOperList = new ArrayList();
          for(Role role : this.roleList) {
	        List<Meta> metalist = role.getMetaList();
            for(Meta meta : metalist) {

	        metaIdList.add(meta.getMetaId());
	        metaIdAndOperList.add(meta.getRoleOper());
            }
	      }
	      HashSet h = new HashSet(metaIdList);
	      metaIdList.clear();
	      metaIdList.addAll(h);
	      this.metas.put("metaIdList", metaIdList);
	      this.metas.put("metaIdAndOperList", metaIdAndOperList);
	    }
	 return this.metas;

	 /*
   if (this.metas == null)
    {
      this.metas = new HashMap();
      List metaIdList = new ArrayList();
      List metaIdAndOperList = new ArrayList();
      Iterator localIterator2;
      for (Iterator localIterator1 = this.roleList.iterator(); localIterator1.hasNext(); localIterator2.hasNext())
      {
        Role role = (Role)localIterator1.next();
        List<Meta> metalist = role.getMetaList();
        localIterator2 = metalist.iterator(); continue;Meta meta = (Meta)localIterator2.next();
        metaIdList.add(meta.getMetaId());
        metaIdAndOperList.add(meta.getRoleOper());
      }
      HashSet h = new HashSet(metaIdList);
      metaIdList.clear();
      metaIdList.addAll(h);
      this.metas.put("metaIdList", metaIdList);
      this.metas.put("metaIdAndOperList", metaIdAndOperList);
    }
    return this.metas;
    */
  }

  public String getPassword()
  {
    return this.pwd;
  }

  public String getUsername()
  {
    return this.userName;
  }

  public boolean isAccountNonExpired()
  {
    return true;
  }

  public boolean isAccountNonLocked()
  {
    return true;
  }

  public boolean isCredentialsNonExpired()
  {
    return true;
  }

  public boolean isEnabled()
  {
    return !this.disabled.booleanValue();
  }

  public List<Role> getRoleList()
  {
    return this.roleList;
  }

  public void setRoleList(List<Role> roleList)
  {
    this.roleList = roleList;
  }

  public Integer getUserId()
  {
    return this.userId;
  }

  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getPwd()
  {
    return this.pwd;
  }

  public void setPwd(String pwd)
  {
    this.pwd = pwd;
  }

  public String getUserDesc()
  {
    return this.userDesc;
  }

  public void setUserDesc(String userDesc)
  {
    this.userDesc = userDesc;
  }

  public Boolean getDisabled()
  {
    return this.disabled;
  }

  public void setDisabled(Boolean disabled)
  {
    this.disabled = disabled;
  }
}
