package org.springframework.security.acls.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.acls.Acl;
import org.springframework.security.acls.AclService;
import org.springframework.security.acls.NotFoundException;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.objectidentity.ObjectIdentityImpl;
import org.springframework.security.acls.sid.Sid;
import org.springframework.util.Assert;

public class JdbcAclService
  implements AclService
{
  protected static final Log log = LogFactory.getLog(JdbcAclService.class);
  private static final String selectAclObjectWithParent = "select obj.object_id_identity as obj_id, class.class as class from acl_object_identity obj, acl_object_identity parent, acl_class class where obj.parent_object = parent.id and obj.object_id_class = class.id and parent.object_id_identity = ? and parent.object_id_class = (select id FROM acl_class where acl_class.class = ?)";
  protected JdbcTemplate jdbcTemplate;
  private LookupStrategy lookupStrategy;
  
  public JdbcAclService(DataSource dataSource, LookupStrategy lookupStrategy)
  {
    Assert.notNull(dataSource, "DataSource required");
    Assert.notNull(lookupStrategy, "LookupStrategy required");
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.lookupStrategy = lookupStrategy;
  }
  
  public ObjectIdentity[] findChildren(ObjectIdentity parentIdentity)
  {
    Object[] args = { parentIdentity.getIdentifier(), parentIdentity.getJavaType().getName() };
    List objects = this.jdbcTemplate.query("select obj.object_id_identity as obj_id, class.class as class from acl_object_identity obj, acl_object_identity parent, acl_class class where obj.parent_object = parent.id and obj.object_id_class = class.id and parent.object_id_identity = ? and parent.object_id_class = (select id FROM acl_class where acl_class.class = ?)", args, 
      new RowMapper()
      {
        public Object mapRow(ResultSet rs, int rowNum)
          throws SQLException
        {
          String javaType = rs.getString("class");
          Long identifier = new Long(rs.getLong("obj_id"));
          
          return new ObjectIdentityImpl(javaType, identifier);
        }
      });
    if (objects.size() == 0) {
      return null;
    }
    return (ObjectIdentityImpl[])objects.toArray(new ObjectIdentityImpl[objects.size()]);
  }
  
  public Acl readAclById(ObjectIdentity object, Sid[] sids)
    throws NotFoundException
  {
    Map map = readAclsById(new ObjectIdentity[] { object }, sids);
    Assert.isTrue(map.containsKey(object), "There should have been an Acl entry for ObjectIdentity " + object);
    
    return (Acl)map.get(object);
  }
  
  public Acl readAclById(ObjectIdentity object)
    throws NotFoundException
  {
    return readAclById(object, null);
  }
  
  public Map readAclsById(ObjectIdentity[] objects)
    throws NotFoundException
  {
    return readAclsById(objects, null);
  }
  
  public Map readAclsById(ObjectIdentity[] objects, Sid[] sids)
    throws NotFoundException
  {
    Map result = this.lookupStrategy.readAclsById(objects, sids);
    for (int i = 0; i < objects.length; i++) {
      if (!result.containsKey(objects[i])) {
        throw new NotFoundException("Unable to find ACL information for object identity '" + 
          objects[i].toString() + "'");
      }
    }
    return result;
  }
}
