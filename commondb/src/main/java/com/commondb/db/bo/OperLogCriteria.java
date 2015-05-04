package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperLogCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public OperLogCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected OperLogCriteria(OperLogCriteria example)
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
    
    public Criteria andObjIdIsNull()
    {
      addCriterion("obj_id is null");
      return this;
    }
    
    public Criteria andObjIdIsNotNull()
    {
      addCriterion("obj_id is not null");
      return this;
    }
    
    public Criteria andObjIdEqualTo(String value)
    {
      addCriterion("obj_id =", value, "objId");
      return this;
    }
    
    public Criteria andObjIdNotEqualTo(String value)
    {
      addCriterion("obj_id <>", value, "objId");
      return this;
    }
    
    public Criteria andObjIdGreaterThan(String value)
    {
      addCriterion("obj_id >", value, "objId");
      return this;
    }
    
    public Criteria andObjIdGreaterThanOrEqualTo(String value)
    {
      addCriterion("obj_id >=", value, "objId");
      return this;
    }
    
    public Criteria andObjIdLessThan(String value)
    {
      addCriterion("obj_id <", value, "objId");
      return this;
    }
    
    public Criteria andObjIdLessThanOrEqualTo(String value)
    {
      addCriterion("obj_id <=", value, "objId");
      return this;
    }
    
    public Criteria andObjIdLike(String value)
    {
      addCriterion("obj_id like", value, "objId");
      return this;
    }
    
    public Criteria andObjIdNotLike(String value)
    {
      addCriterion("obj_id not like", value, "objId");
      return this;
    }
    
    public Criteria andObjIdIn(List values)
    {
      addCriterion("obj_id in", values, "objId");
      return this;
    }
    
    public Criteria andObjIdNotIn(List values)
    {
      addCriterion("obj_id not in", values, "objId");
      return this;
    }
    
    public Criteria andObjIdBetween(String value1, String value2)
    {
      addCriterion("obj_id between", value1, value2, "objId");
      return this;
    }
    
    public Criteria andObjIdNotBetween(String value1, String value2)
    {
      addCriterion("obj_id not between", value1, value2, "objId");
      return this;
    }
    
    public Criteria andOperTypeIsNull()
    {
      addCriterion("oper_type is null");
      return this;
    }
    
    public Criteria andOperTypeIsNotNull()
    {
      addCriterion("oper_type is not null");
      return this;
    }
    
    public Criteria andOperTypeEqualTo(String value)
    {
      addCriterion("oper_type =", value, "operType");
      return this;
    }
    
    public Criteria andOperTypeNotEqualTo(String value)
    {
      addCriterion("oper_type <>", value, "operType");
      return this;
    }
    
    public Criteria andOperTypeGreaterThan(String value)
    {
      addCriterion("oper_type >", value, "operType");
      return this;
    }
    
    public Criteria andOperTypeGreaterThanOrEqualTo(String value)
    {
      addCriterion("oper_type >=", value, "operType");
      return this;
    }
    
    public Criteria andOperTypeLessThan(String value)
    {
      addCriterion("oper_type <", value, "operType");
      return this;
    }
    
    public Criteria andOperTypeLessThanOrEqualTo(String value)
    {
      addCriterion("oper_type <=", value, "operType");
      return this;
    }
    
    public Criteria andOperTypeLike(String value)
    {
      addCriterion("oper_type like", value, "operType");
      return this;
    }
    
    public Criteria andOperTypeNotLike(String value)
    {
      addCriterion("oper_type not like", value, "operType");
      return this;
    }
    
    public Criteria andOperTypeIn(List values)
    {
      addCriterion("oper_type in", values, "operType");
      return this;
    }
    
    public Criteria andOperTypeNotIn(List values)
    {
      addCriterion("oper_type not in", values, "operType");
      return this;
    }
    
    public Criteria andOperTypeBetween(String value1, String value2)
    {
      addCriterion("oper_type between", value1, value2, "operType");
      return this;
    }
    
    public Criteria andOperTypeNotBetween(String value1, String value2)
    {
      addCriterion("oper_type not between", value1, value2, "operType");
      return this;
    }
    
    public Criteria andOperTimeIsNull()
    {
      addCriterion("oper_time is null");
      return this;
    }
    
    public Criteria andOperTimeIsNotNull()
    {
      addCriterion("oper_time is not null");
      return this;
    }
    
    public Criteria andOperTimeEqualTo(Date value)
    {
      addCriterion("oper_time =", value, "operTime");
      return this;
    }
    
    public Criteria andOperTimeNotEqualTo(Date value)
    {
      addCriterion("oper_time <>", value, "operTime");
      return this;
    }
    
    public Criteria andOperTimeGreaterThan(Date value)
    {
      addCriterion("oper_time >", value, "operTime");
      return this;
    }
    
    public Criteria andOperTimeGreaterThanOrEqualTo(Date value)
    {
      addCriterion("oper_time >=", value, "operTime");
      return this;
    }
    
    public Criteria andOperTimeLessThan(Date value)
    {
      addCriterion("oper_time <", value, "operTime");
      return this;
    }
    
    public Criteria andOperTimeLessThanOrEqualTo(Date value)
    {
      addCriterion("oper_time <=", value, "operTime");
      return this;
    }
    
    public Criteria andOperTimeIn(List values)
    {
      addCriterion("oper_time in", values, "operTime");
      return this;
    }
    
    public Criteria andOperTimeNotIn(List values)
    {
      addCriterion("oper_time not in", values, "operTime");
      return this;
    }
    
    public Criteria andOperTimeBetween(Date value1, Date value2)
    {
      addCriterion("oper_time between", value1, value2, "operTime");
      return this;
    }
    
    public Criteria andOperTimeNotBetween(Date value1, Date value2)
    {
      addCriterion("oper_time not between", value1, value2, "operTime");
      return this;
    }
  }
}
