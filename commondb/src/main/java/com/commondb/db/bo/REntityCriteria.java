package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class REntityCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public REntityCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected REntityCriteria(REntityCriteria example)
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
      addCriterion("ID is null");
      return this;
    }
    
    public Criteria andIdIsNotNull()
    {
      addCriterion("ID is not null");
      return this;
    }
    
    public Criteria andIdEqualTo(String value)
    {
      addCriterion("ID =", value, "id");
      return this;
    }
    
    public Criteria andIdNotEqualTo(String value)
    {
      addCriterion("ID <>", value, "id");
      return this;
    }
    
    public Criteria andIdGreaterThan(String value)
    {
      addCriterion("ID >", value, "id");
      return this;
    }
    
    public Criteria andIdGreaterThanOrEqualTo(String value)
    {
      addCriterion("ID >=", value, "id");
      return this;
    }
    
    public Criteria andIdLessThan(String value)
    {
      addCriterion("ID <", value, "id");
      return this;
    }
    
    public Criteria andIdLessThanOrEqualTo(String value)
    {
      addCriterion("ID <=", value, "id");
      return this;
    }
    
    public Criteria andIdLike(String value)
    {
      addCriterion("ID like", value, "id");
      return this;
    }
    
    public Criteria andIdNotLike(String value)
    {
      addCriterion("ID not like", value, "id");
      return this;
    }
    
    public Criteria andIdIn(List values)
    {
      addCriterion("ID in", values, "id");
      return this;
    }
    
    public Criteria andIdNotIn(List values)
    {
      addCriterion("ID not in", values, "id");
      return this;
    }
    
    public Criteria andIdBetween(String value1, String value2)
    {
      addCriterion("ID between", value1, value2, "id");
      return this;
    }
    
    public Criteria andIdNotBetween(String value1, String value2)
    {
      addCriterion("ID not between", value1, value2, "id");
      return this;
    }
    
    public Criteria andMeta1IdIsNull()
    {
      addCriterion("META1_ID is null");
      return this;
    }
    
    public Criteria andMeta1IdIsNotNull()
    {
      addCriterion("META1_ID is not null");
      return this;
    }
    
    public Criteria andMeta1IdEqualTo(Integer value)
    {
      addCriterion("META1_ID =", value, "meta1Id");
      return this;
    }
    
    public Criteria andMeta1IdNotEqualTo(Integer value)
    {
      addCriterion("META1_ID <>", value, "meta1Id");
      return this;
    }
    
    public Criteria andMeta1IdGreaterThan(Integer value)
    {
      addCriterion("META1_ID >", value, "meta1Id");
      return this;
    }
    
    public Criteria andMeta1IdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("META1_ID >=", value, "meta1Id");
      return this;
    }
    
    public Criteria andMeta1IdLessThan(Integer value)
    {
      addCriterion("META1_ID <", value, "meta1Id");
      return this;
    }
    
    public Criteria andMeta1IdLessThanOrEqualTo(Integer value)
    {
      addCriterion("META1_ID <=", value, "meta1Id");
      return this;
    }
    
    public Criteria andMeta1IdIn(List values)
    {
      addCriterion("META1_ID in", values, "meta1Id");
      return this;
    }
    
    public Criteria andMeta1IdNotIn(List values)
    {
      addCriterion("META1_ID not in", values, "meta1Id");
      return this;
    }
    
    public Criteria andMeta1IdBetween(Integer value1, Integer value2)
    {
      addCriterion("META1_ID between", value1, value2, "meta1Id");
      return this;
    }
    
    public Criteria andMeta1IdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("META1_ID not between", value1, value2, "meta1Id");
      return this;
    }
    
    public Criteria andEntity1IdIsNull()
    {
      addCriterion("ENTITY1_ID is null");
      return this;
    }
    
    public Criteria andEntity1IdIsNotNull()
    {
      addCriterion("ENTITY1_ID is not null");
      return this;
    }
    
    public Criteria andEntity1IdEqualTo(String value)
    {
      addCriterion("ENTITY1_ID =", value, "entity1Id");
      return this;
    }
    
    public Criteria andEntity1IdNotEqualTo(String value)
    {
      addCriterion("ENTITY1_ID <>", value, "entity1Id");
      return this;
    }
    
    public Criteria andEntity1IdGreaterThan(String value)
    {
      addCriterion("ENTITY1_ID >", value, "entity1Id");
      return this;
    }
    
    public Criteria andEntity1IdGreaterThanOrEqualTo(String value)
    {
      addCriterion("ENTITY1_ID >=", value, "entity1Id");
      return this;
    }
    
    public Criteria andEntity1IdLessThan(String value)
    {
      addCriterion("ENTITY1_ID <", value, "entity1Id");
      return this;
    }
    
    public Criteria andEntity1IdLessThanOrEqualTo(String value)
    {
      addCriterion("ENTITY1_ID <=", value, "entity1Id");
      return this;
    }
    
    public Criteria andEntity1IdLike(String value)
    {
      addCriterion("ENTITY1_ID like", value, "entity1Id");
      return this;
    }
    
    public Criteria andEntity1IdNotLike(String value)
    {
      addCriterion("ENTITY1_ID not like", value, "entity1Id");
      return this;
    }
    
    public Criteria andEntity1IdIn(List values)
    {
      addCriterion("ENTITY1_ID in", values, "entity1Id");
      return this;
    }
    
    public Criteria andEntity1IdNotIn(List values)
    {
      addCriterion("ENTITY1_ID not in", values, "entity1Id");
      return this;
    }
    
    public Criteria andEntity1IdBetween(String value1, String value2)
    {
      addCriterion("ENTITY1_ID between", value1, value2, "entity1Id");
      return this;
    }
    
    public Criteria andEntity1IdNotBetween(String value1, String value2)
    {
      addCriterion("ENTITY1_ID not between", value1, value2, "entity1Id");
      return this;
    }
    
    public Criteria andMeta2IdIsNull()
    {
      addCriterion("META2_ID is null");
      return this;
    }
    
    public Criteria andMeta2IdIsNotNull()
    {
      addCriterion("META2_ID is not null");
      return this;
    }
    
    public Criteria andMeta2IdEqualTo(Integer value)
    {
      addCriterion("META2_ID =", value, "meta2Id");
      return this;
    }
    
    public Criteria andMeta2IdNotEqualTo(Integer value)
    {
      addCriterion("META2_ID <>", value, "meta2Id");
      return this;
    }
    
    public Criteria andMeta2IdGreaterThan(Integer value)
    {
      addCriterion("META2_ID >", value, "meta2Id");
      return this;
    }
    
    public Criteria andMeta2IdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("META2_ID >=", value, "meta2Id");
      return this;
    }
    
    public Criteria andMeta2IdLessThan(Integer value)
    {
      addCriterion("META2_ID <", value, "meta2Id");
      return this;
    }
    
    public Criteria andMeta2IdLessThanOrEqualTo(Integer value)
    {
      addCriterion("META2_ID <=", value, "meta2Id");
      return this;
    }
    
    public Criteria andMeta2IdIn(List values)
    {
      addCriterion("META2_ID in", values, "meta2Id");
      return this;
    }
    
    public Criteria andMeta2IdNotIn(List values)
    {
      addCriterion("META2_ID not in", values, "meta2Id");
      return this;
    }
    
    public Criteria andMeta2IdBetween(Integer value1, Integer value2)
    {
      addCriterion("META2_ID between", value1, value2, "meta2Id");
      return this;
    }
    
    public Criteria andMeta2IdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("META2_ID not between", value1, value2, "meta2Id");
      return this;
    }
    
    public Criteria andEntity2IdIsNull()
    {
      addCriterion("ENTITY2_ID is null");
      return this;
    }
    
    public Criteria andEntity2IdIsNotNull()
    {
      addCriterion("ENTITY2_ID is not null");
      return this;
    }
    
    public Criteria andEntity2IdEqualTo(String value)
    {
      addCriterion("ENTITY2_ID =", value, "entity2Id");
      return this;
    }
    
    public Criteria andEntity2IdNotEqualTo(String value)
    {
      addCriterion("ENTITY2_ID <>", value, "entity2Id");
      return this;
    }
    
    public Criteria andEntity2IdGreaterThan(String value)
    {
      addCriterion("ENTITY2_ID >", value, "entity2Id");
      return this;
    }
    
    public Criteria andEntity2IdGreaterThanOrEqualTo(String value)
    {
      addCriterion("ENTITY2_ID >=", value, "entity2Id");
      return this;
    }
    
    public Criteria andEntity2IdLessThan(String value)
    {
      addCriterion("ENTITY2_ID <", value, "entity2Id");
      return this;
    }
    
    public Criteria andEntity2IdLessThanOrEqualTo(String value)
    {
      addCriterion("ENTITY2_ID <=", value, "entity2Id");
      return this;
    }
    
    public Criteria andEntity2IdLike(String value)
    {
      addCriterion("ENTITY2_ID like", value, "entity2Id");
      return this;
    }
    
    public Criteria andEntity2IdNotLike(String value)
    {
      addCriterion("ENTITY2_ID not like", value, "entity2Id");
      return this;
    }
    
    public Criteria andEntity2IdIn(List values)
    {
      addCriterion("ENTITY2_ID in", values, "entity2Id");
      return this;
    }
    
    public Criteria andEntity2IdNotIn(List values)
    {
      addCriterion("ENTITY2_ID not in", values, "entity2Id");
      return this;
    }
    
    public Criteria andEntity2IdBetween(String value1, String value2)
    {
      addCriterion("ENTITY2_ID between", value1, value2, "entity2Id");
      return this;
    }
    
    public Criteria andEntity2IdNotBetween(String value1, String value2)
    {
      addCriterion("ENTITY2_ID not between", value1, value2, "entity2Id");
      return this;
    }
  }
}
