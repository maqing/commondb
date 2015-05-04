package com.commondb.security.service.impl;

import com.commondb.db.bo.Meta;
import com.commondb.db.bo.Role;
import com.commondb.db.bo.RoleMeta;
import com.commondb.db.bo.RoleMetaCriteria;
import com.commondb.db.bo.RoleMetaCriteria.Criteria;
import com.commondb.db.bo.RoleUserCriteria;
import com.commondb.db.dao.RoleDAO;
import com.commondb.db.dao.RoleMetaDAO;
import com.commondb.db.dao.RoleUserDAO;
import com.commondb.db.service.MetaService;
import com.commondb.security.service.RoleService;
import java.util.List;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.sid.GrantedAuthoritySid;
import org.springframework.security.acls.sid.Sid;

public class RoleServiceImpl
  implements RoleService
{
  private RoleDAO roleDAO;
  private RoleMetaDAO roleMetaDAO;
  private MetaService metaService;
  private RoleUserDAO roleUserDAO;

  public RoleUserDAO getRoleUserDAO()
  {
    return this.roleUserDAO;
  }

  public void setRoleUserDAO(RoleUserDAO roleUserDAO)
  {
    this.roleUserDAO = roleUserDAO;
  }

  public MetaService getMetaService()
  {
    return this.metaService;
  }

  public void setMetaService(MetaService metaService)
  {
    this.metaService = metaService;
  }

  public RoleMetaDAO getRoleMetaDAO()
  {
    return this.roleMetaDAO;
  }

  public void setRoleMetaDAO(RoleMetaDAO roleMetaDAO)
  {
    this.roleMetaDAO = roleMetaDAO;
  }

  public RoleDAO getRoleDAO()
  {
    return this.roleDAO;
  }

  public void setRoleDAO(RoleDAO roleDAO)
  {
    this.roleDAO = roleDAO;
  }

  public List findRolesWhithR(Integer roleId)
  {
    return this.roleDAO.getRoleByIdWithR(roleId);
  }

  public Integer createRole(String roleName, String roleDesc, Integer[] metaIdArr, Integer[] operArr)
  {
    int maxid = this.roleDAO.getMaxId();
    roleName = "ROLE_R" + (maxid + 1);
    Role role = new Role();

    role.setRoleName(roleName);
    role.setRoleDesc(roleDesc);

    Integer roleId = this.roleDAO.insert(role);
    if ((metaIdArr != null) && (operArr != null)) {
      for (int i = 0; i < metaIdArr.length; i++)
      {
        RoleMeta roleMeta = new RoleMeta();
        roleMeta.setMetaId(metaIdArr[i]);
        roleMeta.setRoleId(roleId);
        roleMeta.setOperation(operArr[i]);
        this.roleMetaDAO.insert(roleMeta);

        Sid sid = new GrantedAuthoritySid(roleName);
        Meta meta = (Meta)this.metaService.findMeta(metaIdArr[i]).get(0);
        Permission permission = BasePermission.buildFromMask(operArr[i].intValue());
        this.metaService.addPermission(meta, sid, permission);
      }
    }
    return roleId;
  }

  public void updateRole(Integer roleId, String roleName, String roleDesc, Integer[] metaIdArr, Integer[] operArr)
  {
    Role role = this.roleDAO.selectByPrimaryKey(roleId);
    roleName = role.getRoleName();
    role.setRoleDesc(roleDesc);
    this.roleDAO.updateByPrimaryKey(role);

    List<Meta> metalist = ((Role)this.roleDAO.getRoleByIdWithR(roleId).get(0)).getMetaList();
    if (!metalist.isEmpty()) {
      for (Meta meta : metalist)
      {
        Sid sidObject = new GrantedAuthoritySid(roleName);
        Permission permissionpre = BasePermission.buildFromMask(meta.getRoleOper().intValue());
        this.metaService.deletePermission(meta, sidObject, permissionpre);
      }
    }
    RoleMetaCriteria roleMetaCriteria = new RoleMetaCriteria();
    RoleMetaCriteria.Criteria c = roleMetaCriteria.createCriteria();
    c.andRoleIdEqualTo(roleId);
    this.roleMetaDAO.deleteByExample(roleMetaCriteria);
    if ((metaIdArr != null) && (operArr != null)) {
      for (int i = 0; i < metaIdArr.length; i++)
      {
        RoleMeta roleMeta = new RoleMeta();
        roleMeta.setMetaId(metaIdArr[i]);
        roleMeta.setRoleId(roleId);
        roleMeta.setOperation(operArr[i]);
        this.roleMetaDAO.insert(roleMeta);

        Sid sid = new GrantedAuthoritySid(roleName);
        Meta meta = (Meta)this.metaService.findMeta(metaIdArr[i]).get(0);
        Permission permission = BasePermission.buildFromMask(operArr[i].intValue());
        this.metaService.addPermission(meta, sid, permission);
      }
    }
  }

  public void delRole(Integer roleId)
  {
    Role role = (Role)this.roleDAO.getRoleByIdWithR(roleId).get(0);
    List<Meta> metalist = role.getMetaList();
    if (!metalist.isEmpty()) {
      for (Meta meta : metalist)
      {
        Sid sidObject = new GrantedAuthoritySid(role.getRoleName());
        Permission permissionpre = BasePermission.buildFromMask(meta.getRoleOper().intValue());
        this.metaService.deletePermission(meta, sidObject, permissionpre);
      }
    }
    RoleUserCriteria roleUserCriteria = new RoleUserCriteria();
    RoleUserCriteria.Criteria cr = roleUserCriteria.createCriteria();
    cr.andRoleIdEqualTo(roleId);
    this.roleUserDAO.deleteByExample(roleUserCriteria);

    RoleMetaCriteria roleMetaCriteria = new RoleMetaCriteria();
    RoleMetaCriteria.Criteria c = roleMetaCriteria.createCriteria();
    c.andRoleIdEqualTo(roleId);
    this.roleMetaDAO.deleteByExample(roleMetaCriteria);
    this.roleDAO.deleteByPrimaryKey(roleId);
  }
}
