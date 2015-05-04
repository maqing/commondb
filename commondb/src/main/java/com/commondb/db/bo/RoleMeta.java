package com.commondb.db.bo;

public class RoleMeta
{
  private Integer id;
  private Integer roleId;
  private Integer metaId;
  private Integer operation;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Integer getRoleId()
  {
    return this.roleId;
  }
  
  public void setRoleId(Integer roleId)
  {
    this.roleId = roleId;
  }
  
  public Integer getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
  }
  
  public Integer getOperation()
  {
    return this.operation;
  }
  
  public void setOperation(Integer operation)
  {
    this.operation = operation;
  }
}
