package com.commondb.security.service.impl;

import com.commondb.common.MD5;
import com.commondb.db.bo.RoleUser;
import com.commondb.db.bo.RoleUserCriteria;
import com.commondb.db.bo.RoleUserCriteria.Criteria;
import com.commondb.db.bo.User;
import com.commondb.db.bo.UserCriteria;
import com.commondb.db.dao.RoleUserDAO;
import com.commondb.db.dao.UserDAO;
import com.commondb.security.service.UserService;
import java.util.List;

public class UserServiceImpl
  implements UserService
{
  private UserDAO userDAO;
  private RoleUserDAO roleUserDAO;

  public UserDAO getUserDAO()
  {
    return this.userDAO;
  }

  public void setUserDAO(UserDAO userDAO)
  {
    this.userDAO = userDAO;
  }

  public RoleUserDAO getRoleUserDAO()
  {
    return this.roleUserDAO;
  }

  public void setRoleUserDAO(RoleUserDAO roleUserDAO)
  {
    this.roleUserDAO = roleUserDAO;
  }

  public User findUserById(Integer userId)
  {
    User user = this.userDAO.selectByPrimaryKey(userId);
    return user;
  }

  public void updateUser(User user)
  {
    this.userDAO.updateByPrimaryKeySelective(user);
  }

  public List<User> getAllUser(User user)
  {
    UserCriteria uc = new UserCriteria();
    UserCriteria.Criteria criteria = uc.createCriteria();
    if ((user != null) &&
      (user.getUserName() != null) && (!"".equals(user.getUserName()))) {
      criteria.andUserNameLike("%" + user.getUserName() + "%");
    }
    return this.userDAO.selectByExample(uc);
  }

  public List findUsers(Integer userId)
  {
    return this.userDAO.getUserByIdWithCashRole(userId);
  }

  public List findUsersByName(String userName)
  {
    return this.userDAO.getUserByNameWithCashRole(userName);
  }
  
  public Integer createUser(String userName, String pwd, String userDesc, Boolean disabled, Integer[] roleIdArr)
  {
    String pwdMD5 = MD5.getMD5(pwd.getBytes());

    User user = new User();
    user.setUserName(userName);
    user.setUserDesc(userDesc);
    user.setDisabled(disabled);
    user.setPwd(pwdMD5);

    Integer userId = this.userDAO.insert(user);
    if (roleIdArr != null) {
      for (int i = 0; i < roleIdArr.length; i++)
      {
        RoleUser roleUser = new RoleUser();
        roleUser.setUserId(userId);
        roleUser.setRoleId(roleIdArr[i]);

        this.roleUserDAO.insert(roleUser);
      }
    }
    return userId;
  }

  public void updateUser(Integer userId, String userName, String pwd, String userDesc, Boolean disabled, Integer[] roleIdArr)
  {
    User user = new User();
    user.setUserId(userId);
    user.setUserName(userName);
    user.setUserDesc(userDesc);
    user.setDisabled(disabled);


    this.userDAO.updateByPrimaryKeySelective(user);

    RoleUserCriteria roleUserCriteria = new RoleUserCriteria();
    RoleUserCriteria.Criteria c = roleUserCriteria.createCriteria();
    c.andUserIdEqualTo(userId);
    this.roleUserDAO.deleteByExample(roleUserCriteria);
    if (roleIdArr != null) {
      for (int i = 0; i < roleIdArr.length; i++)
      {
        RoleUser roleUser = new RoleUser();
        roleUser.setUserId(userId);
        roleUser.setRoleId(roleIdArr[i]);

        this.roleUserDAO.insert(roleUser);
      }
    }
  }

  public void delUser(Integer userId)
  {
    RoleUserCriteria roleUserCriteria = new RoleUserCriteria();
    RoleUserCriteria.Criteria c = roleUserCriteria.createCriteria();
    c.andUserIdEqualTo(userId);
    this.roleUserDAO.deleteByExample(roleUserCriteria);
    this.userDAO.deleteByPrimaryKey(userId);
  }

  public void stopUser(Integer userId)
  {
    User user = this.userDAO.selectByPrimaryKey(userId);
    if (user != null) {
      this.userDAO.stopUser(user);
    }
  }

  public int modifyUserInfo(String curUserName, String userName, String userDesc)
  {
    User user = new User();
    user.setUserName(userName);
    user.setUserDesc(userDesc);

    UserCriteria userCriteria = new UserCriteria();
    UserCriteria.Criteria c = userCriteria.createCriteria();
    c.andUserNameEqualTo(curUserName);

    return this.userDAO.updateByExampleSelective(user, userCriteria);
  }

  public int changePassword(String curUserName, String curPwd, String pwd)
  {
    String curPwdMD5 = MD5.getMD5(curPwd.getBytes());
    String pwdMD5 = MD5.getMD5(pwd.getBytes());

    UserCriteria userCriteria = new UserCriteria();
    UserCriteria.Criteria c = userCriteria.createCriteria();
    c.andUserNameEqualTo(curUserName);
    c.andPwdEqualTo(curPwdMD5);

    User user = new User();
    user.setPwd(pwdMD5);

    return this.userDAO.updateByExampleSelective(user, userCriteria);
  }

  public int resetPassword(Integer userId, String pwd)
  {
    String pwdMD5 = MD5.getMD5(pwd.getBytes());

    User user = new User();
    user.setUserId(userId);
    user.setPwd(pwdMD5);

    return this.userDAO.updateByPrimaryKeySelective(user);
  }
}
