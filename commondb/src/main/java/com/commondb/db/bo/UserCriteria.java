package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public UserCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected UserCriteria(UserCriteria example)
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
    
    public Criteria andUserIdIsNull()
    {
      addCriterion("user_id is null");
      return this;
    }
    
    public Criteria andUserIdIsNotNull()
    {
      addCriterion("user_id is not null");
      return this;
    }
    
    public Criteria andUserIdEqualTo(Integer value)
    {
      addCriterion("user_id =", value, "userId");
      return this;
    }
    
    public Criteria andUserIdNotEqualTo(Integer value)
    {
      addCriterion("user_id <>", value, "userId");
      return this;
    }
    
    public Criteria andUserIdGreaterThan(Integer value)
    {
      addCriterion("user_id >", value, "userId");
      return this;
    }
    
    public Criteria andUserIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("user_id >=", value, "userId");
      return this;
    }
    
    public Criteria andUserIdLessThan(Integer value)
    {
      addCriterion("user_id <", value, "userId");
      return this;
    }
    
    public Criteria andUserIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("user_id <=", value, "userId");
      return this;
    }
    
    public Criteria andUserIdIn(List values)
    {
      addCriterion("user_id in", values, "userId");
      return this;
    }
    
    public Criteria andUserIdNotIn(List values)
    {
      addCriterion("user_id not in", values, "userId");
      return this;
    }
    
    public Criteria andUserIdBetween(Integer value1, Integer value2)
    {
      addCriterion("user_id between", value1, value2, "userId");
      return this;
    }
    
    public Criteria andUserIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("user_id not between", value1, value2, "userId");
      return this;
    }
    
    public Criteria andUserNameIsNull()
    {
      addCriterion("user_name is null");
      return this;
    }
    
    public Criteria andUserNameIsNotNull()
    {
      addCriterion("user_name is not null");
      return this;
    }
    
    public Criteria andUserNameEqualTo(String value)
    {
      addCriterion("user_name =", value, "userName");
      return this;
    }
    
    public Criteria andUserNameNotEqualTo(String value)
    {
      addCriterion("user_name <>", value, "userName");
      return this;
    }
    
    public Criteria andUserNameGreaterThan(String value)
    {
      addCriterion("user_name >", value, "userName");
      return this;
    }
    
    public Criteria andUserNameGreaterThanOrEqualTo(String value)
    {
      addCriterion("user_name >=", value, "userName");
      return this;
    }
    
    public Criteria andUserNameLessThan(String value)
    {
      addCriterion("user_name <", value, "userName");
      return this;
    }
    
    public Criteria andUserNameLessThanOrEqualTo(String value)
    {
      addCriterion("user_name <=", value, "userName");
      return this;
    }
    
    public Criteria andUserNameLike(String value)
    {
      addCriterion("user_name like", value, "userName");
      return this;
    }
    
    public Criteria andUserNameNotLike(String value)
    {
      addCriterion("user_name not like", value, "userName");
      return this;
    }
    
    public Criteria andUserNameIn(List values)
    {
      addCriterion("user_name in", values, "userName");
      return this;
    }
    
    public Criteria andUserNameNotIn(List values)
    {
      addCriterion("user_name not in", values, "userName");
      return this;
    }
    
    public Criteria andUserNameBetween(String value1, String value2)
    {
      addCriterion("user_name between", value1, value2, "userName");
      return this;
    }
    
    public Criteria andUserNameNotBetween(String value1, String value2)
    {
      addCriterion("user_name not between", value1, value2, "userName");
      return this;
    }
    
    public Criteria andPwdIsNull()
    {
      addCriterion("pwd is null");
      return this;
    }
    
    public Criteria andPwdIsNotNull()
    {
      addCriterion("pwd is not null");
      return this;
    }
    
    public Criteria andPwdEqualTo(String value)
    {
      addCriterion("pwd =", value, "pwd");
      return this;
    }
    
    public Criteria andPwdNotEqualTo(String value)
    {
      addCriterion("pwd <>", value, "pwd");
      return this;
    }
    
    public Criteria andPwdGreaterThan(String value)
    {
      addCriterion("pwd >", value, "pwd");
      return this;
    }
    
    public Criteria andPwdGreaterThanOrEqualTo(String value)
    {
      addCriterion("pwd >=", value, "pwd");
      return this;
    }
    
    public Criteria andPwdLessThan(String value)
    {
      addCriterion("pwd <", value, "pwd");
      return this;
    }
    
    public Criteria andPwdLessThanOrEqualTo(String value)
    {
      addCriterion("pwd <=", value, "pwd");
      return this;
    }
    
    public Criteria andPwdLike(String value)
    {
      addCriterion("pwd like", value, "pwd");
      return this;
    }
    
    public Criteria andPwdNotLike(String value)
    {
      addCriterion("pwd not like", value, "pwd");
      return this;
    }
    
    public Criteria andPwdIn(List values)
    {
      addCriterion("pwd in", values, "pwd");
      return this;
    }
    
    public Criteria andPwdNotIn(List values)
    {
      addCriterion("pwd not in", values, "pwd");
      return this;
    }
    
    public Criteria andPwdBetween(String value1, String value2)
    {
      addCriterion("pwd between", value1, value2, "pwd");
      return this;
    }
    
    public Criteria andPwdNotBetween(String value1, String value2)
    {
      addCriterion("pwd not between", value1, value2, "pwd");
      return this;
    }
    
    public Criteria andUserDescIsNull()
    {
      addCriterion("user_desc is null");
      return this;
    }
    
    public Criteria andUserDescIsNotNull()
    {
      addCriterion("user_desc is not null");
      return this;
    }
    
    public Criteria andUserDescEqualTo(String value)
    {
      addCriterion("user_desc =", value, "userDesc");
      return this;
    }
    
    public Criteria andUserDescNotEqualTo(String value)
    {
      addCriterion("user_desc <>", value, "userDesc");
      return this;
    }
    
    public Criteria andUserDescGreaterThan(String value)
    {
      addCriterion("user_desc >", value, "userDesc");
      return this;
    }
    
    public Criteria andUserDescGreaterThanOrEqualTo(String value)
    {
      addCriterion("user_desc >=", value, "userDesc");
      return this;
    }
    
    public Criteria andUserDescLessThan(String value)
    {
      addCriterion("user_desc <", value, "userDesc");
      return this;
    }
    
    public Criteria andUserDescLessThanOrEqualTo(String value)
    {
      addCriterion("user_desc <=", value, "userDesc");
      return this;
    }
    
    public Criteria andUserDescLike(String value)
    {
      addCriterion("user_desc like", value, "userDesc");
      return this;
    }
    
    public Criteria andUserDescNotLike(String value)
    {
      addCriterion("user_desc not like", value, "userDesc");
      return this;
    }
    
    public Criteria andUserDescIn(List values)
    {
      addCriterion("user_desc in", values, "userDesc");
      return this;
    }
    
    public Criteria andUserDescNotIn(List values)
    {
      addCriterion("user_desc not in", values, "userDesc");
      return this;
    }
    
    public Criteria andUserDescBetween(String value1, String value2)
    {
      addCriterion("user_desc between", value1, value2, "userDesc");
      return this;
    }
    
    public Criteria andUserDescNotBetween(String value1, String value2)
    {
      addCriterion("user_desc not between", value1, value2, "userDesc");
      return this;
    }
    
    public Criteria andDisabledIsNull()
    {
      addCriterion("disabled is null");
      return this;
    }
    
    public Criteria andDisabledIsNotNull()
    {
      addCriterion("disabled is not null");
      return this;
    }
    
    public Criteria andDisabledEqualTo(Integer value)
    {
      addCriterion("disabled =", value, "disabled");
      return this;
    }
    
    public Criteria andDisabledNotEqualTo(Integer value)
    {
      addCriterion("disabled <>", value, "disabled");
      return this;
    }
    
    public Criteria andDisabledGreaterThan(Integer value)
    {
      addCriterion("disabled >", value, "disabled");
      return this;
    }
    
    public Criteria andDisabledGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("disabled >=", value, "disabled");
      return this;
    }
    
    public Criteria andDisabledLessThan(Integer value)
    {
      addCriterion("disabled <", value, "disabled");
      return this;
    }
    
    public Criteria andDisabledLessThanOrEqualTo(Integer value)
    {
      addCriterion("disabled <=", value, "disabled");
      return this;
    }
    
    public Criteria andDisabledIn(List values)
    {
      addCriterion("disabled in", values, "disabled");
      return this;
    }
    
    public Criteria andDisabledNotIn(List values)
    {
      addCriterion("disabled not in", values, "disabled");
      return this;
    }
    
    public Criteria andDisabledBetween(Integer value1, Integer value2)
    {
      addCriterion("disabled between", value1, value2, "disabled");
      return this;
    }
    
    public Criteria andDisabledNotBetween(Integer value1, Integer value2)
    {
      addCriterion("disabled not between", value1, value2, "disabled");
      return this;
    }
  }
}
