package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class Meta
{
  private Integer metaId;
  private Date createTime;
  private Date lastUpdateTime;
  private Integer createUser;
  private Integer updateUser;
  private String entityName;
  private String entityDesc;
  private List<Role> roleList = new ArrayList();
  private List<CharacterAttrDef> characterAttrDefList = new ArrayList();
  private List<DescAttrDef> descAttrDefList = new ArrayList();
  private List<HierarchyAttrDef> hierarchyAttrDefList = new ArrayList();
  private List<PicAttrDef> picAttrDefList = new ArrayList();
  private Integer roleOper;
  
  public Integer getRoleOper()
  {
    return this.roleOper;
  }
  
  public void setRoleOper(Integer roleOper)
  {
    this.roleOper = roleOper;
  }
  
  public String getRoleAuthorities()
  {
    List<String> roleAuthorities = new ArrayList();
    for (Role role : this.roleList) {
      roleAuthorities.add(role.getRoleName());
    }
    return StringUtils.join(roleAuthorities, ",");
  }
  
  public List<CharacterAttrDef> getCharacterAttrDefList()
  {
    return this.characterAttrDefList;
  }
  
  public void setCharacterAttrDefList(List<CharacterAttrDef> characterAttrDefList)
  {
    this.characterAttrDefList = characterAttrDefList;
  }
  
  public List<DescAttrDef> getDescAttrDefList()
  {
    return this.descAttrDefList;
  }
  
  public void setDescAttrDefList(List<DescAttrDef> descAttrDefList)
  {
    this.descAttrDefList = descAttrDefList;
  }
  
  public List<HierarchyAttrDef> getHierarchyAttrDefList()
  {
    return this.hierarchyAttrDefList;
  }
  
  public void setHierarchyAttrDefList(List<HierarchyAttrDef> hierarchyAttrDefList)
  {
    this.hierarchyAttrDefList = hierarchyAttrDefList;
  }
  
  public List<PicAttrDef> getPicAttrDefList()
  {
    return this.picAttrDefList;
  }
  
  public void setPicAttrDefList(List<PicAttrDef> picAttrDefList)
  {
    this.picAttrDefList = picAttrDefList;
  }
  
  public List<Role> getRoleList()
  {
    return this.roleList;
  }
  
  public void setRoleList(List<Role> roleList)
  {
    this.roleList = roleList;
  }
  
  public Integer getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
  }
  
  public Date getCreateTime()
  {
    return this.createTime;
  }
  
  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }
  
  public Date getLastUpdateTime()
  {
    return this.lastUpdateTime;
  }
  
  public void setLastUpdateTime(Date lastUpdateTime)
  {
    this.lastUpdateTime = lastUpdateTime;
  }
  
  public Integer getCreateUser()
  {
    return this.createUser;
  }
  
  public void setCreateUser(Integer createUser)
  {
    this.createUser = createUser;
  }
  
  public Integer getUpdateUser()
  {
    return this.updateUser;
  }
  
  public void setUpdateUser(Integer updateUser)
  {
    this.updateUser = updateUser;
  }
  
  public String getEntityName()
  {
    return this.entityName;
  }
  
  public void setEntityName(String entityName)
  {
    this.entityName = entityName;
  }
  
  public String getEntityDesc()
  {
    return this.entityDesc;
  }
  
  public void setEntityDesc(String entityDesc)
  {
    this.entityDesc = entityDesc;
  }
  
  public Integer getId()
  {
    return this.metaId;
  }
}
