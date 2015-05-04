package org.springframework.security.acls.objectidentity;

import java.io.Serializable;
import java.lang.reflect.Method;
import org.springframework.security.acls.IdentityUnavailableException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

public class ObjectIdentityImpl
  implements ObjectIdentity
{
  private Class javaType;
  private Serializable identifier;

  public ObjectIdentityImpl(String javaType, Serializable identifier)
  {
    Assert.hasText(javaType, "Java Type required");
    Assert.notNull(identifier, "identifier required");
    try
    {
      this.javaType = Class.forName(javaType);
    }
    catch (Exception ex)
    {
      ReflectionUtils.handleReflectionException(ex);
    }
    this.identifier = identifier;
  }

  public ObjectIdentityImpl(Class javaType, Serializable identifier)
  {
    Assert.notNull(javaType, "Java Type required");
    Assert.notNull(identifier, "identifier required");
    this.javaType = javaType;
    this.identifier = identifier;
  }

  public ObjectIdentityImpl(Object object)
    throws IdentityUnavailableException
  {
    Assert.notNull(object, "object cannot be null");

    this.javaType = ClassUtils.getUserClass(object.getClass());
    Object result;
    try
    {
      Method method = this.javaType.getMethod("getId", new Class[0]);
      result = method.invoke(object, new Object[0]);
    }
    catch (Exception e)
    {
      throw new IdentityUnavailableException("Could not extract identity from object " + object, e);
    }
    Assert.notNull(result, "getId() is required to return a non-null value");
    Assert.isInstanceOf(Serializable.class, result, "Getter must provide a return value of type Serializable");
    this.identifier = ((Serializable)result);
  }

  public boolean equals(Object arg0)
  {
    if (arg0 == null) {
      return false;
    }
    if (!(arg0 instanceof ObjectIdentityImpl)) {
      return false;
    }
    ObjectIdentityImpl other = (ObjectIdentityImpl)arg0;
    if ((getIdentifier().toString().equals(other.getIdentifier().toString())) && (getJavaType().equals(other.getJavaType()))) {
      return true;
    }
    return false;
  }

  public Serializable getIdentifier()
  {
    return this.identifier;
  }

  public Class getJavaType()
  {
    return this.javaType;
  }

  public int hashCode()
  {
    int code = 31;
    code ^= this.javaType.hashCode();
    code ^= this.identifier.hashCode();

    return code;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append(getClass().getName()).append("[");
    sb.append("Java Type: ").append(this.javaType.getName());
    sb.append("; Identifier: ").append(this.identifier).append("]");

    return sb.toString();
  }
}
