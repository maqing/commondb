package com.commondb.security.test;

import com.commondb.db.bo.RoleUser;
import com.commondb.db.dao.RoleUserDAO;
import java.io.PrintStream;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class RoleUsertest
{
  String[] locations = { "/WebRoot/WEB-INF/applicationContext.xml", "/WebRoot/WEB-INF/applicationContext-security.xml" };
  ApplicationContext ctx = new FileSystemXmlApplicationContext(this.locations);
  private RoleUserDAO roleUserDAO = (RoleUserDAO)this.ctx.getBean("roleUserDAO");
  
  public void testInsert()
  {
    System.out.println("------insert(RoleUser role)-----");
    RoleUser roleUser = new RoleUser();
    roleUser.setRoleId(Integer.valueOf(1));
    roleUser.setUserId(Integer.valueOf(5));
    Integer pk = this.roleUserDAO.insert(roleUser);
    System.out.println("所插入数据ID=" + pk);
  }
  
  public static void main(String[] args)
  {
    RoleUsertest tlinkDAOTest = new RoleUsertest();
    tlinkDAOTest.testInsert();
  }
}
