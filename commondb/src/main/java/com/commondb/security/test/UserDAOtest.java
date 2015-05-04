package com.commondb.security.test;

import com.commondb.db.bo.User;
import com.commondb.db.bo.UserCriteria;
import com.commondb.db.bo.UserCriteria.Criteria;
import com.commondb.db.dao.UserDAO;
import java.io.PrintStream;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserDAOtest
{
  String[] locations = { "/WebRoot/WEB-INF/applicationContext.xml", "/WebRoot/WEB-INF/applicationContext-security.xml" };
  ApplicationContext ctx = new FileSystemXmlApplicationContext(this.locations);
  private UserDAO userDAO = (UserDAO)this.ctx.getBean("userDAO");
  
  public void testInsert()
  {
    System.out.println("-------insert(User user)--------");
    User user = new User();
    user.setUserName("u5");
    user.setUserDesc("hou");
    Integer pk = this.userDAO.insert(user);
    System.out.println("所插入数据ID=" + pk);
  }
  
  public void testGetById()
  {
    UserCriteria userCriteria = new UserCriteria();
    UserCriteria.Criteria c = userCriteria.createCriteria();
    c.andUserIdEqualTo(Integer.valueOf(1));
    System.out.println("-------getById(Long id)-------");
    Object object = this.userDAO.selectByExample(userCriteria);
    System.out.println(object);
  }
  
  public void testGetUserByIdWithCashRole()
  {
    System.out.println("-------testGetUserByIdWithCashRole(Long id)-------");
    List<User> obj = this.userDAO.getUserByIdWithCashRole(Integer.valueOf(1));
    System.out.println("查询结果" + ((User)obj.get(0)).out());
  }
  
  public void testGetByRoleId()
  {
    System.out.println("-------testGetByRoleId()-------");
    List<User> userList = this.userDAO.getByRoleId(Integer.valueOf(1));
    for (User r : userList) {
      System.out.println(r);
    }
  }
  
  public static void main(String[] args)
  {
    System.out.println("正在测试UserDAO");
    UserDAOtest userDAOTest = new UserDAOtest();
    userDAOTest.testInsert();
    userDAOTest.testGetUserByIdWithCashRole();
    userDAOTest.testGetByRoleId();
  }
}
