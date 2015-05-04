package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetaPropertyCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public MetaPropertyCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected MetaPropertyCriteria(MetaPropertyCriteria example)
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
    
    public Criteria andPropertyIdIsNull()
    {
      addCriterion("property_id is null");
      return this;
    }
    
    public Criteria andPropertyIdIsNotNull()
    {
      addCriterion("property_id is not null");
      return this;
    }
    
    public Criteria andPropertyIdEqualTo(Integer value)
    {
      addCriterion("property_id =", value, "propertyId");
      return this;
    }
    
    public Criteria andPropertyIdNotEqualTo(Integer value)
    {
      addCriterion("property_id <>", value, "propertyId");
      return this;
    }
    
    public Criteria andPropertyIdGreaterThan(Integer value)
    {
      addCriterion("property_id >", value, "propertyId");
      return this;
    }
    
    public Criteria andPropertyIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("property_id >=", value, "propertyId");
      return this;
    }
    
    public Criteria andPropertyIdLessThan(Integer value)
    {
      addCriterion("property_id <", value, "propertyId");
      return this;
    }
    
    public Criteria andPropertyIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("property_id <=", value, "propertyId");
      return this;
    }
    
    public Criteria andPropertyIdIn(List values)
    {
      addCriterion("property_id in", values, "propertyId");
      return this;
    }
    
    public Criteria andPropertyIdNotIn(List values)
    {
      addCriterion("property_id not in", values, "propertyId");
      return this;
    }
    
    public Criteria andPropertyIdBetween(Integer value1, Integer value2)
    {
      addCriterion("property_id between", value1, value2, "propertyId");
      return this;
    }
    
    public Criteria andPropertyIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("property_id not between", value1, value2, "propertyId");
      return this;
    }
    
    public Criteria andPropertyNameIsNull()
    {
      addCriterion("property_name is null");
      return this;
    }
    
    public Criteria andPropertyNameIsNotNull()
    {
      addCriterion("property_name is not null");
      return this;
    }
    
    public Criteria andPropertyNameEqualTo(String value)
    {
      addCriterion("property_name =", value, "propertyName");
      return this;
    }
    
    public Criteria andPropertyNameNotEqualTo(String value)
    {
      addCriterion("property_name <>", value, "propertyName");
      return this;
    }
    
    public Criteria andPropertyNameGreaterThan(String value)
    {
      addCriterion("property_name >", value, "propertyName");
      return this;
    }
    
    public Criteria andPropertyNameGreaterThanOrEqualTo(String value)
    {
      addCriterion("property_name >=", value, "propertyName");
      return this;
    }
    
    public Criteria andPropertyNameLessThan(String value)
    {
      addCriterion("property_name <", value, "propertyName");
      return this;
    }
    
    public Criteria andPropertyNameLessThanOrEqualTo(String value)
    {
      addCriterion("property_name <=", value, "propertyName");
      return this;
    }
    
    public Criteria andPropertyNameLike(String value)
    {
      addCriterion("property_name like", value, "propertyName");
      return this;
    }
    
    public Criteria andPropertyNameNotLike(String value)
    {
      addCriterion("property_name not like", value, "propertyName");
      return this;
    }
    
    public Criteria andPropertyNameIn(List values)
    {
      addCriterion("property_name in", values, "propertyName");
      return this;
    }
    
    public Criteria andPropertyNameNotIn(List values)
    {
      addCriterion("property_name not in", values, "propertyName");
      return this;
    }
    
    public Criteria andPropertyNameBetween(String value1, String value2)
    {
      addCriterion("property_name between", value1, value2, "propertyName");
      return this;
    }
    
    public Criteria andPropertyNameNotBetween(String value1, String value2)
    {
      addCriterion("property_name not between", value1, value2, "propertyName");
      return this;
    }
    
    public Criteria andPropertyDescIsNull()
    {
      addCriterion("property_desc is null");
      return this;
    }
    
    public Criteria andPropertyDescIsNotNull()
    {
      addCriterion("property_desc is not null");
      return this;
    }
    
    public Criteria andPropertyDescEqualTo(String value)
    {
      addCriterion("property_desc =", value, "propertyDesc");
      return this;
    }
    
    public Criteria andPropertyDescNotEqualTo(String value)
    {
      addCriterion("property_desc <>", value, "propertyDesc");
      return this;
    }
    
    public Criteria andPropertyDescGreaterThan(String value)
    {
      addCriterion("property_desc >", value, "propertyDesc");
      return this;
    }
    
    public Criteria andPropertyDescGreaterThanOrEqualTo(String value)
    {
      addCriterion("property_desc >=", value, "propertyDesc");
      return this;
    }
    
    public Criteria andPropertyDescLessThan(String value)
    {
      addCriterion("property_desc <", value, "propertyDesc");
      return this;
    }
    
    public Criteria andPropertyDescLessThanOrEqualTo(String value)
    {
      addCriterion("property_desc <=", value, "propertyDesc");
      return this;
    }
    
    public Criteria andPropertyDescLike(String value)
    {
      addCriterion("property_desc like", value, "propertyDesc");
      return this;
    }
    
    public Criteria andPropertyDescNotLike(String value)
    {
      addCriterion("property_desc not like", value, "propertyDesc");
      return this;
    }
    
    public Criteria andPropertyDescIn(List values)
    {
      addCriterion("property_desc in", values, "propertyDesc");
      return this;
    }
    
    public Criteria andPropertyDescNotIn(List values)
    {
      addCriterion("property_desc not in", values, "propertyDesc");
      return this;
    }
    
    public Criteria andPropertyDescBetween(String value1, String value2)
    {
      addCriterion("property_desc between", value1, value2, "propertyDesc");
      return this;
    }
    
    public Criteria andPropertyDescNotBetween(String value1, String value2)
    {
      addCriterion("property_desc not between", value1, value2, "propertyDesc");
      return this;
    }
    
    public Criteria andPropertyLimitIsNull()
    {
      addCriterion("property_limit is null");
      return this;
    }
    
    public Criteria andPropertyLimitIsNotNull()
    {
      addCriterion("property_limit is not null");
      return this;
    }
    
    public Criteria andPropertyLimitEqualTo(Integer value)
    {
      addCriterion("property_limit =", value, "propertyLimit");
      return this;
    }
    
    public Criteria andPropertyLimitNotEqualTo(Integer value)
    {
      addCriterion("property_limit <>", value, "propertyLimit");
      return this;
    }
    
    public Criteria andPropertyLimitGreaterThan(Integer value)
    {
      addCriterion("property_limit >", value, "propertyLimit");
      return this;
    }
    
    public Criteria andPropertyLimitGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("property_limit >=", value, "propertyLimit");
      return this;
    }
    
    public Criteria andPropertyLimitLessThan(Integer value)
    {
      addCriterion("property_limit <", value, "propertyLimit");
      return this;
    }
    
    public Criteria andPropertyLimitLessThanOrEqualTo(Integer value)
    {
      addCriterion("property_limit <=", value, "propertyLimit");
      return this;
    }
    
    public Criteria andPropertyLimitIn(List values)
    {
      addCriterion("property_limit in", values, "propertyLimit");
      return this;
    }
    
    public Criteria andPropertyLimitNotIn(List values)
    {
      addCriterion("property_limit not in", values, "propertyLimit");
      return this;
    }
    
    public Criteria andPropertyLimitBetween(Integer value1, Integer value2)
    {
      addCriterion("property_limit between", value1, value2, "propertyLimit");
      return this;
    }
    
    public Criteria andPropertyLimitNotBetween(Integer value1, Integer value2)
    {
      addCriterion("property_limit not between", value1, value2, "propertyLimit");
      return this;
    }
    
    public Criteria andPropertyCodeIsNull()
    {
      addCriterion("property_code is null");
      return this;
    }
    
    public Criteria andPropertyCodeIsNotNull()
    {
      addCriterion("property_code is not null");
      return this;
    }
    
    public Criteria andPropertyCodeEqualTo(String value)
    {
      addCriterion("property_code =", value, "propertyCode");
      return this;
    }
    
    public Criteria andPropertyCodeNotEqualTo(String value)
    {
      addCriterion("property_code <>", value, "propertyCode");
      return this;
    }
    
    public Criteria andPropertyCodeGreaterThan(String value)
    {
      addCriterion("property_code >", value, "propertyCode");
      return this;
    }
    
    public Criteria andPropertyCodeGreaterThanOrEqualTo(String value)
    {
      addCriterion("property_code >=", value, "propertyCode");
      return this;
    }
    
    public Criteria andPropertyCodeLessThan(String value)
    {
      addCriterion("property_code <", value, "propertyCode");
      return this;
    }
    
    public Criteria andPropertyCodeLessThanOrEqualTo(String value)
    {
      addCriterion("property_code <=", value, "propertyCode");
      return this;
    }
    
    public Criteria andPropertyCodeLike(String value)
    {
      addCriterion("property_code like", value, "propertyCode");
      return this;
    }
    
    public Criteria andPropertyCodeNotLike(String value)
    {
      addCriterion("property_code not like", value, "propertyCode");
      return this;
    }
    
    public Criteria andPropertyCodeIn(List values)
    {
      addCriterion("property_code in", values, "propertyCode");
      return this;
    }
    
    public Criteria andPropertyCodeNotIn(List values)
    {
      addCriterion("property_code not in", values, "propertyCode");
      return this;
    }
    
    public Criteria andPropertyCodeBetween(String value1, String value2)
    {
      addCriterion("property_code between", value1, value2, "propertyCode");
      return this;
    }
    
    public Criteria andPropertyCodeNotBetween(String value1, String value2)
    {
      addCriterion("property_code not between", value1, value2, "propertyCode");
      return this;
    }
    
    public Criteria andIsenabledIsNull()
    {
      addCriterion("isEnabled is null");
      return this;
    }
    
    public Criteria andIsenabledIsNotNull()
    {
      addCriterion("isEnabled is not null");
      return this;
    }
    
    public Criteria andIsenabledEqualTo(Integer value)
    {
      addCriterion("isEnabled =", value, "isenabled");
      return this;
    }
    
    public Criteria andIsenabledNotEqualTo(Integer value)
    {
      addCriterion("isEnabled <>", value, "isenabled");
      return this;
    }
    
    public Criteria andIsenabledGreaterThan(Integer value)
    {
      addCriterion("isEnabled >", value, "isenabled");
      return this;
    }
    
    public Criteria andIsenabledGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("isEnabled >=", value, "isenabled");
      return this;
    }
    
    public Criteria andIsenabledLessThan(Integer value)
    {
      addCriterion("isEnabled <", value, "isenabled");
      return this;
    }
    
    public Criteria andIsenabledLessThanOrEqualTo(Integer value)
    {
      addCriterion("isEnabled <=", value, "isenabled");
      return this;
    }
    
    public Criteria andIsenabledIn(List values)
    {
      addCriterion("isEnabled in", values, "isenabled");
      return this;
    }
    
    public Criteria andIsenabledNotIn(List values)
    {
      addCriterion("isEnabled not in", values, "isenabled");
      return this;
    }
    
    public Criteria andIsenabledBetween(Integer value1, Integer value2)
    {
      addCriterion("isEnabled between", value1, value2, "isenabled");
      return this;
    }
    
    public Criteria andIsenabledNotBetween(Integer value1, Integer value2)
    {
      addCriterion("isEnabled not between", value1, value2, "isenabled");
      return this;
    }
  }
}
