package com.commondb.apply.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplyMenuCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  private Integer limit;
  private Integer offset;
  
  public ApplyMenuCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected ApplyMenuCriteria(ApplyMenuCriteria example)
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
      addCriterion("apply_menu_id not between", value1, value2, "applyMenuId");
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
    
    public Criteria andMenuNameIsNull()
    {
      addCriterion("menu_name is null");
      return this;
    }
    
    public Criteria andMenuNameIsNotNull()
    {
      addCriterion("menu_name is not null");
      return this;
    }
    
    public Criteria andMenuNameEqualTo(String value)
    {
      addCriterion("menu_name =", value, "menuName");
      return this;
    }
    
    public Criteria andMenuNameNotEqualTo(String value)
    {
      addCriterion("menu_name <>", value, "menuName");
      return this;
    }
    
    public Criteria andMenuNameGreaterThan(String value)
    {
      addCriterion("menu_name >", value, "menuName");
      return this;
    }
    
    public Criteria andMenuNameGreaterThanOrEqualTo(String value)
    {
      addCriterion("menu_name >=", value, "menuName");
      return this;
    }
    
    public Criteria andMenuNameLessThan(String value)
    {
      addCriterion("menu_name <", value, "menuName");
      return this;
    }
    
    public Criteria andMenuNameLessThanOrEqualTo(String value)
    {
      addCriterion("menu_name <=", value, "menuName");
      return this;
    }
    
    public Criteria andMenuNameLike(String value)
    {
      addCriterion("menu_name like", value, "menuName");
      return this;
    }
    
    public Criteria andMenuNameNotLike(String value)
    {
      addCriterion("menu_name not like", value, "menuName");
      return this;
    }
    
    public Criteria andMenuNameIn(List values)
    {
      addCriterion("menu_name in", values, "menuName");
      return this;
    }
    
    public Criteria andMenuNameNotIn(List values)
    {
      addCriterion("menu_name not in", values, "menuName");
      return this;
    }
    
    public Criteria andMenuNameBetween(String value1, String value2)
    {
      addCriterion("menu_name between", value1, value2, "menuName");
      return this;
    }
    
    public Criteria andMenuNameNotBetween(String value1, String value2)
    {
      addCriterion("menu_name not between", value1, value2, "menuName");
      return this;
    }
    
    public Criteria andMenuUrlIsNull()
    {
      addCriterion("menu_url is null");
      return this;
    }
    
    public Criteria andMenuUrlIsNotNull()
    {
      addCriterion("menu_url is not null");
      return this;
    }
    
    public Criteria andMenuUrlEqualTo(String value)
    {
      addCriterion("menu_url =", value, "menuUrl");
      return this;
    }
    
    public Criteria andMenuUrlNotEqualTo(String value)
    {
      addCriterion("menu_url <>", value, "menuUrl");
      return this;
    }
    
    public Criteria andMenuUrlGreaterThan(String value)
    {
      addCriterion("menu_url >", value, "menuUrl");
      return this;
    }
    
    public Criteria andMenuUrlGreaterThanOrEqualTo(String value)
    {
      addCriterion("menu_url >=", value, "menuUrl");
      return this;
    }
    
    public Criteria andMenuUrlLessThan(String value)
    {
      addCriterion("menu_url <", value, "menuUrl");
      return this;
    }
    
    public Criteria andMenuUrlLessThanOrEqualTo(String value)
    {
      addCriterion("menu_url <=", value, "menuUrl");
      return this;
    }
    
    public Criteria andMenuUrlLike(String value)
    {
      addCriterion("menu_url like", value, "menuUrl");
      return this;
    }
    
    public Criteria andMenuUrlNotLike(String value)
    {
      addCriterion("menu_url not like", value, "menuUrl");
      return this;
    }
    
    public Criteria andMenuUrlIn(List values)
    {
      addCriterion("menu_url in", values, "menuUrl");
      return this;
    }
    
    public Criteria andMenuUrlNotIn(List values)
    {
      addCriterion("menu_url not in", values, "menuUrl");
      return this;
    }
    
    public Criteria andMenuUrlBetween(String value1, String value2)
    {
      addCriterion("menu_url between", value1, value2, "menuUrl");
      return this;
    }
    
    public Criteria andMenuUrlNotBetween(String value1, String value2)
    {
      addCriterion("menu_url not between", value1, value2, "menuUrl");
      return this;
    }
    
    public Criteria andNumIsNull()
    {
      addCriterion("num is null");
      return this;
    }
    
    public Criteria andNumIsNotNull()
    {
      addCriterion("num is not null");
      return this;
    }
    
    public Criteria andNumEqualTo(Integer value)
    {
      addCriterion("num =", value, "num");
      return this;
    }
    
    public Criteria andNumNotEqualTo(Integer value)
    {
      addCriterion("num <>", value, "num");
      return this;
    }
    
    public Criteria andNumGreaterThan(Integer value)
    {
      addCriterion("num >", value, "num");
      return this;
    }
    
    public Criteria andNumGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("num >=", value, "num");
      return this;
    }
    
    public Criteria andNumLessThan(Integer value)
    {
      addCriterion("num <", value, "num");
      return this;
    }
    
    public Criteria andNumLessThanOrEqualTo(Integer value)
    {
      addCriterion("num <=", value, "num");
      return this;
    }
    
    public Criteria andNumIn(List values)
    {
      addCriterion("num in", values, "num");
      return this;
    }
    
    public Criteria andNumNotIn(List values)
    {
      addCriterion("num not in", values, "num");
      return this;
    }
    
    public Criteria andNumBetween(Integer value1, Integer value2)
    {
      addCriterion("num between", value1, value2, "num");
      return this;
    }
    
    public Criteria andNumNotBetween(Integer value1, Integer value2)
    {
      addCriterion("num not between", value1, value2, "num");
      return this;
    }
  }
}
