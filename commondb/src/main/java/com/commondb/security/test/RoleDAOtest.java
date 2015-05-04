package com.commondb.security.test;

import com.commondb.db.bo.Role;
import com.commondb.db.bo.RoleCriteria;
import com.commondb.db.bo.RoleCriteria.Criteria;
import com.commondb.db.dao.RoleDAO;
import java.io.PrintStream;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class RoleDAOtest
{
  String[] locations = { "/WebRoot/WEB-INF/applicationContext.xml", "/WebRoot/WEB-INF/applicationContext-security.xml" };
  ApplicationContext ctx = new FileSystemXmlApplicationContext(this.locations);
  private RoleDAO roleDAO = (RoleDAO)this.ctx.getBean("roleDAO");
  
  public void testInsert()
  {
    System.out.println("------insert(Role role)-----");
    Role role = new Role();
    role.setRoleName("admin");
    role.setRoleDesc("管理员");
    Integer pk = this.roleDAO.insert(role);
    System.out.println("所插入数据ID=" + pk);
  }
  
  public void testGetById()
  {
    RoleCriteria roleCriteria = new RoleCriteria();
    RoleCriteria.Criteria c = roleCriteria.createCriteria();
    c.andRoleIdEqualTo(Integer.valueOf(1));
    System.out.println("------getById(Long id)-----");
    Object obj = this.roleDAO.selectByExample(roleCriteria);
    System.out.println("查询结果" + obj);
  }
  
  public void testGetRoleByIdWithCashUser()
  {
    System.out.println("------getRoleByIdWithCashUser(Long id)-----");
    Role obj = (Role)this.roleDAO.getRoleByIdWithR(Integer.valueOf(1)).get(0);
    System.out.println("查询结果" + obj.out());
  }
  
  public void testGetByUserId()
  {
    System.out.println("------getByUserId(Long userId)-----");
    List<Role> roleList = this.roleDAO.getByUserId(Integer.valueOf(1));
    for (Role r : roleList) {
      System.out.println(r);
    }
  }
  
  public static void main(String[] args)
  {
    System.out.println("正在测试RoleDAO");
    RoleDAOtest roleDAOTest = new RoleDAOtest();
    roleDAOTest.testInsert();
    roleDAOTest.testGetById();
    roleDAOTest.testGetRoleByIdWithCashUser();
    roleDAOTest.testGetByUserId();
  }
}
