package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetaDescAttrStatCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  private Integer limit;
  private Integer offset;
  
  public MetaDescAttrStatCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected MetaDescAttrStatCriteria(MetaDescAttrStatCriteria example)
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
    
    public Criteria andStatIdIsNull()
    {
      addCriterion("stat_id is null");
      return this;
    }
    
    public Criteria andStatIdIsNotNull()
    {
      addCriterion("stat_id is not null");
      return this;
    }
    
    public Criteria andStatIdEqualTo(Integer value)
    {
      addCriterion("stat_id =", value, "statId");
      return this;
    }
    
    public Criteria andStatIdNotEqualTo(Integer value)
    {
      addCriterion("stat_id <>", value, "statId");
      return this;
    }
    
    public Criteria andStatIdGreaterThan(Integer value)
    {
      addCriterion("stat_id >", value, "statId");
      return this;
    }
    
    public Criteria andStatIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("stat_id >=", value, "statId");
      return this;
    }
    
    public Criteria andStatIdLessThan(Integer value)
    {
      addCriterion("stat_id <", value, "statId");
      return this;
    }
    
    public Criteria andStatIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("stat_id <=", value, "statId");
      return this;
    }
    
    public Criteria andStatIdIn(List values)
    {
      addCriterion("stat_id in", values, "statId");
      return this;
    }
    
    public Criteria andStatIdNotIn(List values)
    {
      addCriterion("stat_id not in", values, "statId");
      return this;
    }
    
    public Criteria andStatIdBetween(Integer value1, Integer value2)
    {
      addCriterion("stat_id between", value1, value2, "statId");
      return this;
    }
    
    public Criteria andStatIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("stat_id not between", value1, value2, "statId");
      return this;
    }
    
    public Criteria andAttrIdIsNull()
    {
      addCriterion("attr_id is null");
      return this;
    }
    
    public Criteria andAttrIdIsNotNull()
    {
      addCriterion("attr_id is not null");
      return this;
    }
    
    public Criteria andAttrIdEqualTo(Integer value)
    {
      addCriterion("attr_id =", value, "attrId");
      return this;
    }
    
    public Criteria andAttrIdNotEqualTo(Integer value)
    {
      addCriterion("attr_id <>", value, "attrId");
      return this;
    }
    
    public Criteria andAttrIdGreaterThan(Integer value)
    {
      addCriterion("attr_id >", value, "attrId");
      return this;
    }
    
    public Criteria andAttrIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("attr_id >=", value, "attrId");
      return this;
    }
    
    public Criteria andAttrIdLessThan(Integer value)
    {
      addCriterion("attr_id <", value, "attrId");
      return this;
    }
    
    public Criteria andAttrIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("attr_id <=", value, "attrId");
      return this;
    }
    
    public Criteria andAttrIdIn(List values)
    {
      addCriterion("attr_id in", values, "attrId");
      return this;
    }
    
    public Criteria andAttrIdNotIn(List values)
    {
      addCriterion("attr_id not in", values, "attrId");
      return this;
    }
    
    public Criteria andAttrIdBetween(Integer value1, Integer value2)
    {
      addCriterion("attr_id between", value1, value2, "attrId");
      return this;
    }
    
    public Criteria andAttrIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("attr_id not between", value1, value2, "attrId");
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
    
    public Criteria andContentIsNull()
    {
      addCriterion("content is null");
      return this;
    }
    
    public Criteria andContentIsNotNull()
    {
      addCriterion("content is not null");
      return this;
    }
    
    public Criteria andContentEqualTo(String value)
    {
      addCriterion("content =", value, "content");
      return this;
    }
    
    public Criteria andContentNotEqualTo(String value)
    {
      addCriterion("content <>", value, "content");
      return this;
    }
    
    public Criteria andContentGreaterThan(String value)
    {
      addCriterion("content >", value, "content");
      return this;
    }
    
    public Criteria andContentGreaterThanOrEqualTo(String value)
    {
      addCriterion("content >=", value, "content");
      return this;
    }
    
    public Criteria andContentLessThan(String value)
    {
      addCriterion("content <", value, "content");
      return this;
    }
    
    public Criteria andContentLessThanOrEqualTo(String value)
    {
      addCriterion("content <=", value, "content");
      return this;
    }
    
    public Criteria andContentLike(String value)
    {
      addCriterion("content like", value, "content");
      return this;
    }
    
    public Criteria andContentNotLike(String value)
    {
      addCriterion("content not like", value, "content");
      return this;
    }
    
    public Criteria andContentIn(List values)
    {
      addCriterion("content in", values, "content");
      return this;
    }
    
    public Criteria andContentNotIn(List values)
    {
      addCriterion("content not in", values, "content");
      return this;
    }
    
    public Criteria andContentBetween(String value1, String value2)
    {
      addCriterion("content between", value1, value2, "content");
      return this;
    }
    
    public Criteria andContentNotBetween(String value1, String value2)
    {
      addCriterion("content not between", value1, value2, "content");
      return this;
    }
  }
}
