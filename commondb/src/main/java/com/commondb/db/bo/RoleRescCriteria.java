package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleRescCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public RoleRescCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected RoleRescCriteria(RoleRescCriteria example)
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
    
    public Criteria andRescIdIsNull()
    {
      addCriterion("RESC_ID is null");
      return this;
    }
    
    public Criteria andRescIdIsNotNull()
    {
      addCriterion("RESC_ID is not null");
      return this;
    }
    
    public Criteria andRescIdEqualTo(Integer value)
    {
      addCriterion("RESC_ID =", value, "rescId");
      return this;
    }
    
    public Criteria andRescIdNotEqualTo(Integer value)
    {
      addCriterion("RESC_ID <>", value, "rescId");
      return this;
    }
    
    public Criteria andRescIdGreaterThan(Integer value)
    {
      addCriterion("RESC_ID >", value, "rescId");
      return this;
    }
    
    public Criteria andRescIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("RESC_ID >=", value, "rescId");
      return this;
    }
    
    public Criteria andRescIdLessThan(Integer value)
    {
      addCriterion("RESC_ID <", value, "rescId");
      return this;
    }
    
    public Criteria andRescIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("RESC_ID <=", value, "rescId");
      return this;
    }
    
    public Criteria andRescIdIn(List values)
    {
      addCriterion("RESC_ID in", values, "rescId");
      return this;
    }
    
    public Criteria andRescIdNotIn(List values)
    {
      addCriterion("RESC_ID not in", values, "rescId");
      return this;
    }
    
    public Criteria andRescIdBetween(Integer value1, Integer value2)
    {
      addCriterion("RESC_ID between", value1, value2, "rescId");
      return this;
    }
    
    public Criteria andRescIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("RESC_ID not between", value1, value2, "rescId");
      return this;
    }
    
    public Criteria andRoleIdIsNull()
    {
      addCriterion("ROLE_ID is null");
      return this;
    }
    
    public Criteria andRoleIdIsNotNull()
    {
      addCriterion("ROLE_ID is not null");
      return this;
    }
    
    public Criteria andRoleIdEqualTo(Integer value)
    {
      addCriterion("ROLE_ID =", value, "roleId");
      return this;
    }
    
    public Criteria andRoleIdNotEqualTo(Integer value)
    {
      addCriterion("ROLE_ID <>", value, "roleId");
      return this;
    }
    
    public Criteria andRoleIdGreaterThan(Integer value)
    {
      addCriterion("ROLE_ID >", value, "roleId");
      return this;
    }
    
    public Criteria andRoleIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("ROLE_ID >=", value, "roleId");
      return this;
    }
    
    public Criteria andRoleIdLessThan(Integer value)
    {
      addCriterion("ROLE_ID <", value, "roleId");
      return this;
    }
    
    public Criteria andRoleIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("ROLE_ID <=", value, "roleId");
      return this;
    }
    
    public Criteria andRoleIdIn(List values)
    {
      addCriterion("ROLE_ID in", values, "roleId");
      return this;
    }
    
    public Criteria andRoleIdNotIn(List values)
    {
      addCriterion("ROLE_ID not in", values, "roleId");
      return this;
    }
    
    public Criteria andRoleIdBetween(Integer value1, Integer value2)
    {
      addCriterion("ROLE_ID between", value1, value2, "roleId");
      return this;
    }
    
    public Criteria andRoleIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("ROLE_ID not between", value1, value2, "roleId");
      return this;
    }
  }
}
