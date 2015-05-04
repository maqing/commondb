package com.commondb.security.web;

import com.commondb.common.JsonResult;
import com.commondb.security.service.RoleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class RoleAction
  extends ActionSupport
  implements Preparable
{
  private JsonResult result = new JsonResult();
  private RoleService roleService;
  private Integer roleId;
  private String roleName;
  private String roleDesc;
  private Integer[] metaIdArr;
  private Integer[] operArr;
  
  public Integer getRoleId()
  {
    return this.roleId;
  }
  
  public void setRoleId(Integer roleId)
  {
    this.roleId = roleId;
  }
  
  public Integer[] getMetaIdArr()
  {
    return this.metaIdArr;
  }
  
  public void setMetaIdArr(Integer[] metaIdArr)
  {
    this.metaIdArr = metaIdArr;
  }
  
  public Integer[] getOperArr()
  {
    return this.operArr;
  }
  
  public void setOperArr(Integer[] operArr)
  {
    this.operArr = operArr;
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
  
  public RoleService getRoleService()
  {
    return this.roleService;
  }
  
  public void setRoleService(RoleService roleService)
  {
    this.roleService = roleService;
  }
  
  public void prepare()
    throws Exception
  {}
  
  public JsonResult getResult()
  {
    return this.result;
  }
  
  public void setResult(JsonResult result)
  {
    this.result = result;
  }
  
  public String findRole()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.roleService.findRolesWhithR(this.roleId));
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String createRole()
  {
    this.result = new JsonResult();
    try
    {
      this.roleService.createRole(this.roleName, this.roleDesc, this.metaIdArr, this.operArr);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String updateRole()
  {
    this.result = new JsonResult();
    try
    {
      this.roleService.updateRole(this.roleId, this.roleName, this.roleDesc, this.metaIdArr, this.operArr);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String delRole()
  {
    this.result = new JsonResult();
    try
    {
      this.roleService.delRole(this.roleId);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
}
