package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public RoleCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected RoleCriteria(RoleCriteria example)
  {
    this.orderByClause = example.orderByClause;
    this.oredCriteria = example.oredCriteria;
  }
  
  public void setOrderByClause(String orderByClause)
  {
    this.orderByClause = orderByClause;
  }
  
  public String getOrderByClause()
  {
    return this.orderByClause;
  }
  
  public List getOredCriteria()
  {
    return this.oredCriteria;
  }
  
  public void or(Criteria criteria)
  {
    this.oredCriteria.add(criteria);
  }
  
  public Criteria createCriteria()
  {
    Criteria criteria = createCriteriaInternal();
    if (this.oredCriteria.size() == 0) {
      this.oredCriteria.add(criteria);
    }
    return criteria;
  }
  
  protected Criteria createCriteriaInternal()
  {
    Criteria criteria = new Criteria();
    return criteria;
  }
  
  public void clear()
  {
    this.oredCriteria.clear();
  }
  
  public static class Criteria
  {
    protected List criteriaWithoutValue;
    protected List criteriaWithSingleValue;
    protected List criteriaWithListValue;
    protected List criteriaWithBetweenValue;
    
    protected Criteria()
    {
      this.criteriaWithoutValue = new ArrayList();
      this.criteriaWithSingleValue = new ArrayList();
      this.criteriaWithListValue = new ArrayList();
      this.criteriaWithBetweenValue = new ArrayList();
    }
    
    public boolean isValid()
    {
      return (this.criteriaWithoutValue.size() > 0) || (this.criteriaWithSingleValue.size() > 0) || (this.criteriaWithListValue.size() > 0) || (this.criteriaWithBetweenValue.size() > 0);
    }
    
    public List getCriteriaWithoutValue()
    {
      return this.criteriaWithoutValue;
    }
    
    public List getCriteriaWithSingleValue()
    {
      return this.criteriaWithSingleValue;
    }
    
    public List getCriteriaWithListValue()
    {
      return this.criteriaWithListValue;
    }
    
    public List getCriteriaWithBetweenValue()
    {
      return this.criteriaWithBetweenValue;
    }
    
    protected void addCriterion(String condition)
    {
      if (condition == null) {
        throw new RuntimeException("Value for condition cannot be null");
      }
      this.criteriaWithoutValue.add(condition);
    }
    
    protected void addCriterion(String condition, Object value, String property)
    {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      Map map = new HashMap();
      map.put("condition", condition);
      map.put("value", value);
      this.criteriaWithSingleValue.add(map);
    }
    
    protected void addCriterion(String condition, List values, String property)
    {
      if ((values == null) || (values.size() == 0)) {
        throw new RuntimeException("Value list for " + property + " cannot be null or empty");
      }
      Map map = new HashMap();
      map.put("condition", condition);
      map.put("values", values);
      this.criteriaWithListValue.add(map);
    }
    
    protected void addCriterion(String condition, Object value1, Object value2, String property)
    {
      if ((value1 == null) || (value2 == null)) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      List list = new ArrayList();
      list.add(value1);
      list.add(value2);
      Map map = new HashMap();
      map.put("condition", condition);
      map.put("values", list);
      this.criteriaWithBetweenValue.add(map);
    }
    
    public Criteria andRoleIdIsNull()
    {
      addCriterion("role_id is null");
      return this;
    }
    
    public Criteria andRoleIdIsNotNull()
    {
      addCriterion("role_id is not null");
      return this;
    }
    
    public Criteria andRoleIdEqualTo(Integer value)
    {
      addCriterion("role_id =", value, "roleId");
      return this;
    }
    
    public Criteria andRoleIdNotEqualTo(Integer value)
    {
      addCriterion("role_id <>", value, "roleId");
      return this;
    }
    
    public Criteria andRoleIdGreaterThan(Integer value)
    {
      addCriterion("role_id >", value, "roleId");
      return this;
    }
    
    public Criteria andRoleIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("role_id >=", value, "roleId");
      return this;
    }
    
    public Criteria andRoleIdLessThan(Integer value)
    {
      addCriterion("role_id <", value, "roleId");
      return this;
    }
    
    public Criteria andRoleIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("role_id <=", value, "roleId");
      return this;
    }
    
    public Criteria andRoleIdIn(List values)
    {
      addCriterion("role_id in", values, "roleId");
      return this;
    }
    
    public Criteria andRoleIdNotIn(List values)
    {
      addCriterion("role_id not in", values, "roleId");
      return this;
    }
    
    public Criteria andRoleIdBetween(Integer value1, Integer value2)
    {
      addCriterion("role_id between", value1, value2, "roleId");
      return this;
    }
    
    public Criteria andRoleIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("role_id not between", value1, value2, "roleId");
      return this;
    }
    
    public Criteria andRoleNameIsNull()
    {
      addCriterion("role_name is null");
      return this;
    }
    
    public Criteria andRoleNameIsNotNull()
    {
      addCriterion("role_name is not null");
      return this;
    }
    
    public Criteria andRoleNameEqualTo(String value)
    {
      addCriterion("role_name =", value, "roleName");
      return this;
    }
    
    public Criteria andRoleNameNotEqualTo(String value)
    {
      addCriterion("role_name <>", value, "roleName");
      return this;
    }
    
    public Criteria andRoleNameGreaterThan(String value)
    {
      addCriterion("role_name >", value, "roleName");
      return this;
    }
    
    public Criteria andRoleNameGreaterThanOrEqualTo(String value)
    {
      addCriterion("role_name >=", value, "roleName");
      return this;
    }
    
    public Criteria andRoleNameLessThan(String value)
    {
      addCriterion("role_name <", value, "roleName");
      return this;
    }
    
    public Criteria andRoleNameLessThanOrEqualTo(String value)
    {
      addCriterion("role_name <=", value, "roleName");
      return this;
    }
    
    public Criteria andRoleNameLike(String value)
    {
      addCriterion("role_name like", value, "roleName");
      return this;
    }
    
    public Criteria andRoleNameNotLike(String value)
    {
      addCriterion("role_name not like", value, "roleName");
      return this;
    }
    
    public Criteria andRoleNameIn(List values)
    {
      addCriterion("role_name in", values, "roleName");
      return this;
    }
    
    public Criteria andRoleNameNotIn(List values)
    {
      addCriterion("role_name not in", values, "roleName");
      return this;
    }
    
    public Criteria andRoleNameBetween(String value1, String value2)
    {
      addCriterion("role_name between", value1, value2, "roleName");
      return this;
    }
    
    public Criteria andRoleNameNotBetween(String value1, String value2)
    {
      addCriterion("role_name not between", value1, value2, "roleName");
      return this;
    }
    
    public Criteria andRoleDescIsNull()
    {
      addCriterion("role_desc is null");
      return this;
    }
    
    public Criteria andRoleDescIsNotNull()
    {
      addCriterion("role_desc is not null");
      return this;
    }
    
    public Criteria andRoleDescEqualTo(String value)
    {
      addCriterion("role_desc =", value, "roleDesc");
      return this;
    }
    
    public Criteria andRoleDescNotEqualTo(String value)
    {
      addCriterion("role_desc <>", value, "roleDesc");
      return this;
    }
    
    public Criteria andRoleDescGreaterThan(String value)
    {
      addCriterion("role_desc >", value, "roleDesc");
      return this;
    }
    
    public Criteria andRoleDescGreaterThanOrEqualTo(String value)
    {
      addCriterion("role_desc >=", value, "roleDesc");
      return this;
    }
    
    public Criteria andRoleDescLessThan(String value)
    {
      addCriterion("role_desc <", value, "roleDesc");
      return this;
    }
    
    public Criteria andRoleDescLessThanOrEqualTo(String value)
    {
      addCriterion("role_desc <=", value, "roleDesc");
      return this;
    }
    
    public Criteria andRoleDescLike(String value)
    {
      addCriterion("role_desc like", value, "roleDesc");
      return this;
    }
    
    public Criteria andRoleDescNotLike(String value)
    {
      addCriterion("role_desc not like", value, "roleDesc");
      return this;
    }
    
    public Criteria andRoleDescIn(List values)
    {
      addCriterion("role_desc in", values, "roleDesc");
      return this;
    }
    
    public Criteria andRoleDescNotIn(List values)
    {
      addCriterion("role_desc not in", values, "roleDesc");
      return this;
    }
    
    public Criteria andRoleDescBetween(String value1, String value2)
    {
      addCriterion("role_desc between", value1, value2, "roleDesc");
      return this;
    }
    
    public Criteria andRoleDescNotBetween(String value1, String value2)
    {
      addCriterion("role_desc not between", value1, value2, "roleDesc");
      return this;
    }
  }
}
