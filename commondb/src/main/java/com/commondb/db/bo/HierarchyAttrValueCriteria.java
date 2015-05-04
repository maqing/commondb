package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HierarchyAttrValueCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  private Integer limit;
  private Integer offset;
  
  public HierarchyAttrValueCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected HierarchyAttrValueCriteria(HierarchyAttrValueCriteria example)
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
    
    public Criteria andValueIdIsNull()
    {
      addCriterion("value_id is null");
      return this;
    }
    
    public Criteria andValueIdIsNotNull()
    {
      addCriterion("value_id is not null");
      return this;
    }
    
    public Criteria andValueIdEqualTo(String value)
    {
      addCriterion("value_id =", value, "valueId");
      return this;
    }
    
    public Criteria andValueIdNotEqualTo(String value)
    {
      addCriterion("value_id <>", value, "valueId");
      return this;
    }
    
    public Criteria andValueIdGreaterThan(String value)
    {
      addCriterion("value_id >", value, "valueId");
      return this;
    }
    
    public Criteria andValueIdGreaterThanOrEqualTo(String value)
    {
      addCriterion("value_id >=", value, "valueId");
      return this;
    }
    
    public Criteria andValueIdLessThan(String value)
    {
      addCriterion("value_id <", value, "valueId");
      return this;
    }
    
    public Criteria andValueIdLessThanOrEqualTo(String value)
    {
      addCriterion("value_id <=", value, "valueId");
      return this;
    }
    
    public Criteria andValueIdLike(String value)
    {
      addCriterion("value_id like", value, "valueId");
      return this;
    }
    
    public Criteria andValueIdNotLike(String value)
    {
      addCriterion("value_id not like", value, "valueId");
      return this;
    }
    
    public Criteria andValueIdIn(List values)
    {
      addCriterion("value_id in", values, "valueId");
      return this;
    }
    
    public Criteria andValueIdNotIn(List values)
    {
      addCriterion("value_id not in", values, "valueId");
      return this;
    }
    
    public Criteria andValueIdBetween(String value1, String value2)
    {
      addCriterion("value_id between", value1, value2, "valueId");
      return this;
    }
    
    public Criteria andValueIdNotBetween(String value1, String value2)
    {
      addCriterion("value_id not between", value1, value2, "valueId");
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
    
    public Criteria andParentIdIsNull()
    {
      addCriterion("parent_id is null");
      return this;
    }
    
    public Criteria andParentIdIsNotNull()
    {
      addCriterion("parent_id is not null");
      return this;
    }
    
    public Criteria andParentIdEqualTo(String value)
    {
      addCriterion("parent_id =", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdNotEqualTo(String value)
    {
      addCriterion("parent_id <>", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdGreaterThan(String value)
    {
      addCriterion("parent_id >", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdGreaterThanOrEqualTo(String value)
    {
      addCriterion("parent_id >=", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdLessThan(String value)
    {
      addCriterion("parent_id <", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdLessThanOrEqualTo(String value)
    {
      addCriterion("parent_id <=", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdLike(String value)
    {
      addCriterion("parent_id like", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdNotLike(String value)
    {
      addCriterion("parent_id not like", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdIn(List values)
    {
      addCriterion("parent_id in", values, "parentId");
      return this;
    }
    
    public Criteria andParentIdNotIn(List values)
    {
      addCriterion("parent_id not in", values, "parentId");
      return this;
    }
    
    public Criteria andParentIdBetween(String value1, String value2)
    {
      addCriterion("parent_id between", value1, value2, "parentId");
      return this;
    }
    
    public Criteria andParentIdNotBetween(String value1, String value2)
    {
      addCriterion("parent_id not between", value1, value2, "parentId");
      return this;
    }
    
    public Criteria andLevelNumIsNull()
    {
      addCriterion("level_num is null");
      return this;
    }
    
    public Criteria andLevelNumIsNotNull()
    {
      addCriterion("level_num is not null");
      return this;
    }
    
    public Criteria andLevelNumEqualTo(Integer value)
    {
      addCriterion("level_num =", value, "levelNum");
      return this;
    }
    
    public Criteria andLevelNumNotEqualTo(Integer value)
    {
      addCriterion("level_num <>", value, "levelNum");
      return this;
    }
    
    public Criteria andLevelNumGreaterThan(Integer value)
    {
      addCriterion("level_num >", value, "levelNum");
      return this;
    }
    
    public Criteria andLevelNumGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("level_num >=", value, "levelNum");
      return this;
    }
    
    public Criteria andLevelNumLessThan(Integer value)
    {
      addCriterion("level_num <", value, "levelNum");
      return this;
    }
    
    public Criteria andLevelNumLessThanOrEqualTo(Integer value)
    {
      addCriterion("level_num <=", value, "levelNum");
      return this;
    }
    
    public Criteria andLevelNumIn(List values)
    {
      addCriterion("level_num in", values, "levelNum");
      return this;
    }
    
    public Criteria andLevelNumNotIn(List values)
    {
      addCriterion("level_num not in", values, "levelNum");
      return this;
    }
    
    public Criteria andLevelNumBetween(Integer value1, Integer value2)
    {
      addCriterion("level_num between", value1, value2, "levelNum");
      return this;
    }
    
    public Criteria andLevelNumNotBetween(Integer value1, Integer value2)
    {
      addCriterion("level_num not between", value1, value2, "levelNum");
      return this;
    }
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
}
