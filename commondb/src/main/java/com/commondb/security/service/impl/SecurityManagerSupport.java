package com.commondb.security.service.impl;

import com.commondb.db.bo.Resc;
import com.commondb.db.bo.RescCriteria;
import com.commondb.db.bo.RescCriteria.Criteria;
import com.commondb.db.bo.User;
import com.commondb.db.dao.MenuDAO;
import com.commondb.db.dao.MetaDAO;
import com.commondb.db.dao.RescDAO;
import com.commondb.db.dao.RoleDAO;
import com.commondb.db.dao.RoleMenuDAO;
import com.commondb.db.dao.RoleMetaDAO;
import com.commondb.db.dao.RoleRescDAO;
import com.commondb.db.dao.RoleUserDAO;
import com.commondb.db.dao.UserDAO;
import com.commondb.security.service.SecurityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

public class SecurityManagerSupport
  implements UserDetailsService, SecurityManager
{
  private RoleDAO roleDAO;
  private MetaDAO metaDAO;
  private UserDAO userDAO;
  private RescDAO rescDAO;
  private MenuDAO menuDAO;
  private RoleMetaDAO roleMetaDAO;
  private RoleUserDAO roleUserDAO;
  private RoleMenuDAO roleMenuDAO;
  private RoleRescDAO roleRescDAO;
  
  public MenuDAO getMenuDAO()
  {
    return this.menuDAO;
  }
  
  public void setMenuDAO(MenuDAO menuDAO)
  {
    this.menuDAO = menuDAO;
  }
  
  public RoleMenuDAO getRoleMenuDAO()
  {
    return this.roleMenuDAO;
  }
  
  public void setRoleMenuDAO(RoleMenuDAO roleMenuDAO)
  {
    this.roleMenuDAO = roleMenuDAO;
  }
  
  public RoleRescDAO getRoleRescDAO()
  {
    return this.roleRescDAO;
  }
  
  public void setRoleRescDAO(RoleRescDAO roleRescDAO)
  {
    this.roleRescDAO = roleRescDAO;
  }
  
  public RescDAO getRescDAO()
  {
    return this.rescDAO;
  }
  
  public void setRescDAO(RescDAO rescDAO)
  {
    this.rescDAO = rescDAO;
  }
  
  public RoleDAO getRoleDAO()
  {
    return this.roleDAO;
  }
  
  public void setRoleDAO(RoleDAO roleDAO)
  {
    this.roleDAO = roleDAO;
  }
  
  public MetaDAO getMetaDAO()
  {
    return this.metaDAO;
  }
  
  public void setMetaDAO(MetaDAO metaDAO)
  {
    this.metaDAO = metaDAO;
  }
  
  public UserDAO getUserDAO()
  {
    return this.userDAO;
  }
  
  public void setUserDAO(UserDAO userDAO)
  {
    this.userDAO = userDAO;
  }
  
  public RoleMetaDAO getRoleMetaDAO()
  {
    return this.roleMetaDAO;
  }
  
  public void setRoleMetaDAO(RoleMetaDAO roleMetaDAO)
  {
    this.roleMetaDAO = roleMetaDAO;
  }
  
  public RoleUserDAO getRoleUserDAO()
  {
    return this.roleUserDAO;
  }
  
  public void setRoleUserDAO(RoleUserDAO roleUserDAO)
  {
    this.roleUserDAO = roleUserDAO;
  }
  
  public UserDetails loadUserByUsername(String userName)
    throws UsernameNotFoundException, DataAccessException
  {
    List<User> users = this.userDAO.getUserByNameWithCashRole(userName);
    if (users.isEmpty()) {
      throw new UsernameNotFoundException("User " + userName + " has no GrantedAuthority");
    }
    return (UserDetails)users.get(0);
  }
  
  public Map<String, String> loadUrlAuthorities()
  {
    Map<String, String> urlAuthorities = new HashMap();
    




    RescCriteria rescCriteria = new RescCriteria();
    RescCriteria.Criteria c = rescCriteria.createCriteria();
    c.andResTypeEqualTo("URL");
    List<Resc> urlResources = this.rescDAO.selectByExample(rescCriteria);
    for (Resc resource : urlResources) {
      urlAuthorities.put(resource.getResString(), resource.getRoleAuthorities());
    }
    return urlAuthorities;
  }
}
