package com.commondb.apply.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplyUserCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  private Integer limit;
  private Integer offset;
  
  public ApplyUserCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected ApplyUserCriteria(ApplyUserCriteria example)
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
  
  public void setLimit(Integer limit)
  {
    this.limit = limit;
  }
  
  public Integer getLimit()
  {
    return this.limit;
  }
  
  public void setOffset(Integer offset)
  {
    this.offset = offset;
  }
  
  public Integer getOffset()
  {
    return this.offset;
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
        throw new RuntimeException("Value for " + property + 
          " cannot be null");
      }
      Map map = new HashMap();
      map.put("condition", condition);
      map.put("value", value);
      this.criteriaWithSingleValue.add(map);
    }
    
    protected void addCriterion(String condition, List values, String property)
    {
      if ((values == null) || (values.size() == 0)) {
        throw new RuntimeException("Value list for " + property + 
          " cannot be null or empty");
      }
      Map map = new HashMap();
      map.put("condition", condition);
      map.put("values", values);
      this.criteriaWithListValue.add(map);
    }
    
    protected void addCriterion(String condition, Object value1, Object value2, String property)
    {
      if ((value1 == null) || (value2 == null)) {
        throw new RuntimeException("Between values for " + property + 
          " cannot be null");
      }
      List list = new ArrayList();
      list.add(value1);
      list.add(value2);
      Map map = new HashMap();
      map.put("condition", condition);
      map.put("values", list);
      this.criteriaWithBetweenValue.add(map);
    }
    
    public Criteria andIdIsNull()
    {
      addCriterion("id is null");
      return this;
    }
    
    public Criteria andIdIsNotNull()
    {
      addCriterion("id is not null");
      return this;
    }
    
    public Criteria andIdEqualTo(Integer value)
    {
      addCriterion("id =", value, "id");
      return this;
    }
    
    public Criteria andIdNotEqualTo(Integer value)
    {
      addCriterion("id <>", value, "id");
      return this;
    }
    
    public Criteria andIdGreaterThan(Integer value)
    {
      addCriterion("id >", value, "id");
      return this;
    }
    
    public Criteria andIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("id >=", value, "id");
      return this;
    }
    
    public Criteria andIdLessThan(Integer value)
    {
      addCriterion("id <", value, "id");
      return this;
    }
    
    public Criteria andIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("id <=", value, "id");
      return this;
    }
    
    public Criteria andIdIn(List values)
    {
      addCriterion("id in", values, "id");
      return this;
    }
    
    public Criteria andIdNotIn(List values)
    {
      addCriterion("id not in", values, "id");
      return this;
    }
    
    public Criteria andIdBetween(Integer value1, Integer value2)
    {
      addCriterion("id between", value1, value2, "id");
      return this;
    }
    
    public Criteria andIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("id not between", value1, value2, "id");
      return this;
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
    
    public Criteria andApplyIdIsNull()
    {
      addCriterion("apply_id is null");
      return this;
    }
    
    public Criteria andApplyIdIsNotNull()
    {
      addCriterion("apply_id is not null");
      return this;
    }
    
    public Criteria andApplyIdEqualTo(Integer value)
    {
      addCriterion("apply_id =", value, "applyId");
      return this;
    }
    
    public Criteria andApplyIdNotEqualTo(Integer value)
    {
      addCriterion("apply_id <>", value, "applyId");
      return this;
    }
    
    public Criteria andApplyIdGreaterThan(Integer value)
    {
      addCriterion("apply_id >", value, "applyId");
      return this;
    }
    
    public Criteria andApplyIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("apply_id >=", value, "applyId");
      return this;
    }
    
    public Criteria andApplyIdLessThan(Integer value)
    {
      addCriterion("apply_id <", value, "applyId");
      return this;
    }
    
    public Criteria andApplyIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("apply_id <=", value, "applyId");
      return this;
    }
    
    public Criteria andApplyIdIn(List values)
    {
      addCriterion("apply_id in", values, "applyId");
      return this;
    }
    
    public Criteria andApplyIdNotIn(List values)
    {
      addCriterion("apply_id not in", values, "applyId");
      return this;
    }
    
    public Criteria andApplyIdBetween(Integer value1, Integer value2)
    {
      addCriterion("apply_id between", value1, value2, "applyId");
      return this;
    }
    
    public Criteria andApplyIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("apply_id not between", value1, value2, "applyId");
      return this;
    }
    
    public Criteria andApplyMenuIdIsNull()
    {
      addCriterion("apply_menu_id is null");
      return this;
    }
    
    public Criteria andApplyMenuIdIsNotNull()
    {
      addCriterion("apply_menu_id is not null");
      return this;
    }
    
    public Criteria andApplyMenuIdEqualTo(Integer value)
    {
      addCriterion("apply_menu_id =", value, "applyMenuId");
      return this;
    }
    
    public Criteria andApplyMenuIdNotEqualTo(Integer value)
    {
      addCriterion("apply_menu_id <>", value, "applyMenuId");
      return this;
    }
    
    public Criteria andApplyMenuIdGreaterThan(Integer value)
    {
      addCriterion("apply_menu_id >", value, "applyMenuId");
      return this;
    }
    
    public Criteria andApplyMenuIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("apply_menu_id >=", value, "applyMenuId");
      return this;
    }
    
    public Criteria andApplyMenuIdLessThan(Integer value)
    {
      addCriterion("apply_menu_id <", value, "applyMenuId");
      return this;
    }
    
    public Criteria andApplyMenuIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("apply_menu_id <=", value, "applyMenuId");
      return this;
    }
    
    public Criteria andApplyMenuIdIn(List values)
    {
      addCriterion("apply_menu_id in", values, "applyMenuId");
      return this;
    }
    
    public Criteria andApplyMenuIdNotIn(List values)
    {
      addCriterion("apply_menu_id not in", values, "applyMenuId");
      return this;
    }
    
    public Criteria andApplyMenuIdBetween(Integer value1, Integer value2)
    {
      addCriterion("apply_menu_id between", value1, value2, "applyMenuId");
      return this;
    }
    
    public Criteria andApplyMenuIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("apply_menu_id not between", value1, value2, 
        "applyMenuId");
      return this;
    }
    
    public Criteria andTypeIsNull()
    {
      addCriterion("type is null");
      return this;
    }
    
    public Criteria andTypeIsNotNull()
    {
      addCriterion("type is not null");
      return this;
    }
    
    public Criteria andTypeEqualTo(Integer value)
    {
      addCriterion("type =", value, "type");
      return this;
    }
    
    public Criteria andTypeNotEqualTo(Integer value)
    {
      addCriterion("type <>", value, "type");
      return this;
    }
    
    public Criteria andTypeGreaterThan(Integer value)
    {
      addCriterion("type >", value, "type");
      return this;
    }
    
    public Criteria andTypeGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("type >=", value, "type");
      return this;
    }
    
    public Criteria andTypeLessThan(Integer value)
    {
      addCriterion("type <", value, "type");
      return this;
    }
    
    public Criteria andTypeLessThanOrEqualTo(Integer value)
    {
      addCriterion("type <=", value, "type");
      return this;
    }
    
    public Criteria andTypeIn(List values)
    {
      addCriterion("type in", values, "type");
      return this;
    }
    
    public Criteria andTypeNotIn(List values)
    {
      addCriterion("type not in", values, "type");
      return this;
    }
    
    public Criteria andTypeBetween(Integer value1, Integer value2)
    {
      addCriterion("type between", value1, value2, "type");
      return this;
    }
    
    public Criteria andTypeNotBetween(Integer value1, Integer value2)
    {
      addCriterion("type not between", value1, value2, "type");
      return this;
    }
  }
}
