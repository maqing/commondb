package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RMetaCharaCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public RMetaCharaCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected RMetaCharaCriteria(RMetaCharaCriteria example)
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
    
    public Criteria andCharaIdIsNull()
    {
      addCriterion("chara_id is null");
      return this;
    }
    
    public Criteria andCharaIdIsNotNull()
    {
      addCriterion("chara_id is not null");
      return this;
    }
    
    public Criteria andCharaIdEqualTo(Integer value)
    {
      addCriterion("chara_id =", value, "charaId");
      return this;
    }
    
    public Criteria andCharaIdNotEqualTo(Integer value)
    {
      addCriterion("chara_id <>", value, "charaId");
      return this;
    }
    
    public Criteria andCharaIdGreaterThan(Integer value)
    {
      addCriterion("chara_id >", value, "charaId");
      return this;
    }
    
    public Criteria andCharaIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("chara_id >=", value, "charaId");
      return this;
    }
    
    public Criteria andCharaIdLessThan(Integer value)
    {
      addCriterion("chara_id <", value, "charaId");
      return this;
    }
    
    public Criteria andCharaIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("chara_id <=", value, "charaId");
      return this;
    }
    
    public Criteria andCharaIdIn(List values)
    {
      addCriterion("chara_id in", values, "charaId");
      return this;
    }
    
    public Criteria andCharaIdNotIn(List values)
    {
      addCriterion("chara_id not in", values, "charaId");
      return this;
    }
    
    public Criteria andCharaIdBetween(Integer value1, Integer value2)
    {
      addCriterion("chara_id between", value1, value2, "charaId");
      return this;
    }
    
    public Criteria andCharaIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("chara_id not between", value1, value2, "charaId");
      return this;
    }
    
    public Criteria andMetaIdIsNull()
    {
      addCriterion("meta_id is null");
      return this;
    }
    
    public Criteria andMetaIdIsNotNull()
    {
      addCriterion("meta_id is not null");
      return this;
    }
    
    public Criteria andMetaIdEqualTo(Integer value)
    {
      addCriterion("meta_id =", value, "metaId");
      return this;
    }
    
    public Criteria andMetaIdNotEqualTo(Integer value)
    {
      addCriterion("meta_id <>", value, "metaId");
      return this;
    }
    
    public Criteria andMetaIdGreaterThan(Integer value)
    {
      addCriterion("meta_id >", value, "metaId");
      return this;
    }
    
    public Criteria andMetaIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("meta_id >=", value, "metaId");
      return this;
    }
    
    public Criteria andMetaIdLessThan(Integer value)
    {
      addCriterion("meta_id <", value, "metaId");
      return this;
    }
    
    public Criteria andMetaIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("meta_id <=", value, "metaId");
      return this;
    }
    
    public Criteria andMetaIdIn(List values)
    {
      addCriterion("meta_id in", values, "metaId");
      return this;
    }
    
    public Criteria andMetaIdNotIn(List values)
    {
      addCriterion("meta_id not in", values, "metaId");
      return this;
    }
    
    public Criteria andMetaIdBetween(Integer value1, Integer value2)
    {
      addCriterion("meta_id between", value1, value2, "metaId");
      return this;
    }
    
    public Criteria andMetaIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("meta_id not between", value1, value2, "metaId");
      return this;
    }
  }
}
