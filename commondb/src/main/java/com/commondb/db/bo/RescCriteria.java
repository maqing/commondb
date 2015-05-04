package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RescCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public RescCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected RescCriteria(RescCriteria example)
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
    
    public Criteria andDescnIsNull()
    {
      addCriterion("DESCN is null");
      return this;
    }
    
    public Criteria andDescnIsNotNull()
    {
      addCriterion("DESCN is not null");
      return this;
    }
    
    public Criteria andDescnEqualTo(String value)
    {
      addCriterion("DESCN =", value, "descn");
      return this;
    }
    
    public Criteria andDescnNotEqualTo(String value)
    {
      addCriterion("DESCN <>", value, "descn");
      return this;
    }
    
    public Criteria andDescnGreaterThan(String value)
    {
      addCriterion("DESCN >", value, "descn");
      return this;
    }
    
    public Criteria andDescnGreaterThanOrEqualTo(String value)
    {
      addCriterion("DESCN >=", value, "descn");
      return this;
    }
    
    public Criteria andDescnLessThan(String value)
    {
      addCriterion("DESCN <", value, "descn");
      return this;
    }
    
    public Criteria andDescnLessThanOrEqualTo(String value)
    {
      addCriterion("DESCN <=", value, "descn");
      return this;
    }
    
    public Criteria andDescnLike(String value)
    {
      addCriterion("DESCN like", value, "descn");
      return this;
    }
    
    public Criteria andDescnNotLike(String value)
    {
      addCriterion("DESCN not like", value, "descn");
      return this;
    }
    
    public Criteria andDescnIn(List values)
    {
      addCriterion("DESCN in", values, "descn");
      return this;
    }
    
    public Criteria andDescnNotIn(List values)
    {
      addCriterion("DESCN not in", values, "descn");
      return this;
    }
    
    public Criteria andDescnBetween(String value1, String value2)
    {
      addCriterion("DESCN between", value1, value2, "descn");
      return this;
    }
    
    public Criteria andDescnNotBetween(String value1, String value2)
    {
      addCriterion("DESCN not between", value1, value2, "descn");
      return this;
    }
    
    public Criteria andNameIsNull()
    {
      addCriterion("NAME is null");
      return this;
    }
    
    public Criteria andNameIsNotNull()
    {
      addCriterion("NAME is not null");
      return this;
    }
    
    public Criteria andNameEqualTo(String value)
    {
      addCriterion("NAME =", value, "name");
      return this;
    }
    
    public Criteria andNameNotEqualTo(String value)
    {
      addCriterion("NAME <>", value, "name");
      return this;
    }
    
    public Criteria andNameGreaterThan(String value)
    {
      addCriterion("NAME >", value, "name");
      return this;
    }
    
    public Criteria andNameGreaterThanOrEqualTo(String value)
    {
      addCriterion("NAME >=", value, "name");
      return this;
    }
    
    public Criteria andNameLessThan(String value)
    {
      addCriterion("NAME <", value, "name");
      return this;
    }
    
    public Criteria andNameLessThanOrEqualTo(String value)
    {
      addCriterion("NAME <=", value, "name");
      return this;
    }
    
    public Criteria andNameLike(String value)
    {
      addCriterion("NAME like", value, "name");
      return this;
    }
    
    public Criteria andNameNotLike(String value)
    {
      addCriterion("NAME not like", value, "name");
      return this;
    }
    
    public Criteria andNameIn(List values)
    {
      addCriterion("NAME in", values, "name");
      return this;
    }
    
    public Criteria andNameNotIn(List values)
    {
      addCriterion("NAME not in", values, "name");
      return this;
    }
    
    public Criteria andNameBetween(String value1, String value2)
    {
      addCriterion("NAME between", value1, value2, "name");
      return this;
    }
    
    public Criteria andNameNotBetween(String value1, String value2)
    {
      addCriterion("NAME not between", value1, value2, "name");
      return this;
    }
    
    public Criteria andResStringIsNull()
    {
      addCriterion("RES_STRING is null");
      return this;
    }
    
    public Criteria andResStringIsNotNull()
    {
      addCriterion("RES_STRING is not null");
      return this;
    }
    
    public Criteria andResStringEqualTo(String value)
    {
      addCriterion("RES_STRING =", value, "resString");
      return this;
    }
    
    public Criteria andResStringNotEqualTo(String value)
    {
      addCriterion("RES_STRING <>", value, "resString");
      return this;
    }
    
    public Criteria andResStringGreaterThan(String value)
    {
      addCriterion("RES_STRING >", value, "resString");
      return this;
    }
    
    public Criteria andResStringGreaterThanOrEqualTo(String value)
    {
      addCriterion("RES_STRING >=", value, "resString");
      return this;
    }
    
    public Criteria andResStringLessThan(String value)
    {
      addCriterion("RES_STRING <", value, "resString");
      return this;
    }
    
    public Criteria andResStringLessThanOrEqualTo(String value)
    {
      addCriterion("RES_STRING <=", value, "resString");
      return this;
    }
    
    public Criteria andResStringLike(String value)
    {
      addCriterion("RES_STRING like", value, "resString");
      return this;
    }
    
    public Criteria andResStringNotLike(String value)
    {
      addCriterion("RES_STRING not like", value, "resString");
      return this;
    }
    
    public Criteria andResStringIn(List values)
    {
      addCriterion("RES_STRING in", values, "resString");
      return this;
    }
    
    public Criteria andResStringNotIn(List values)
    {
      addCriterion("RES_STRING not in", values, "resString");
      return this;
    }
    
    public Criteria andResStringBetween(String value1, String value2)
    {
      addCriterion("RES_STRING between", value1, value2, "resString");
      return this;
    }
    
    public Criteria andResStringNotBetween(String value1, String value2)
    {
      addCriterion("RES_STRING not between", value1, value2, "resString");
      return this;
    }
    
    public Criteria andResTypeIsNull()
    {
      addCriterion("RES_TYPE is null");
      return this;
    }
    
    public Criteria andResTypeIsNotNull()
    {
      addCriterion("RES_TYPE is not null");
      return this;
    }
    
    public Criteria andResTypeEqualTo(String value)
    {
      addCriterion("RES_TYPE =", value, "resType");
      return this;
    }
    
    public Criteria andResTypeNotEqualTo(String value)
    {
      addCriterion("RES_TYPE <>", value, "resType");
      return this;
    }
    
    public Criteria andResTypeGreaterThan(String value)
    {
      addCriterion("RES_TYPE >", value, "resType");
      return this;
    }
    
    public Criteria andResTypeGreaterThanOrEqualTo(String value)
    {
      addCriterion("RES_TYPE >=", value, "resType");
      return this;
    }
    
    public Criteria andResTypeLessThan(String value)
    {
      addCriterion("RES_TYPE <", value, "resType");
      return this;
    }
    
    public Criteria andResTypeLessThanOrEqualTo(String value)
    {
      addCriterion("RES_TYPE <=", value, "resType");
      return this;
    }
    
    public Criteria andResTypeLike(String value)
    {
      addCriterion("RES_TYPE like", value, "resType");
      return this;
    }
    
    public Criteria andResTypeNotLike(String value)
    {
      addCriterion("RES_TYPE not like", value, "resType");
      return this;
    }
    
    public Criteria andResTypeIn(List values)
    {
      addCriterion("RES_TYPE in", values, "resType");
      return this;
    }
    
    public Criteria andResTypeNotIn(List values)
    {
      addCriterion("RES_TYPE not in", values, "resType");
      return this;
    }
    
    public Criteria andResTypeBetween(String value1, String value2)
    {
      addCriterion("RES_TYPE between", value1, value2, "resType");
      return this;
    }
    
    public Criteria andResTypeNotBetween(String value1, String value2)
    {
      addCriterion("RES_TYPE not between", value1, value2, "resType");
      return this;
    }
  }
}
