package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterAttrValueCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  private Integer limit;
  private Integer offset;
  
  public CharacterAttrValueCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected CharacterAttrValueCriteria(CharacterAttrValueCriteria example)
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
    
    public Criteria andValueIdEqualTo(Integer value)
    {
      addCriterion("value_id =", value, "valueId");
      return this;
    }
    
    public Criteria andValueIdNotEqualTo(Integer value)
    {
      addCriterion("value_id <>", value, "valueId");
      return this;
    }
    
    public Criteria andValueIdGreaterThan(Integer value)
    {
      addCriterion("value_id >", value, "valueId");
      return this;
    }
    
    public Criteria andValueIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("value_id >=", value, "valueId");
      return this;
    }
    
    public Criteria andValueIdLessThan(Integer value)
    {
      addCriterion("value_id <", value, "valueId");
      return this;
    }
    
    public Criteria andValueIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("value_id <=", value, "valueId");
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
    
    public Criteria andValueIdBetween(Integer value1, Integer value2)
    {
      addCriterion("value_id between", value1, value2, "valueId");
      return this;
    }
    
    public Criteria andValueIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("value_id not between", value1, value2, "valueId");
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
    
    public Criteria andAttrValueIsNull()
    {
      addCriterion("attr_value is null");
      return this;
    }
    
    public Criteria andAttrValueIsNotNull()
    {
      addCriterion("attr_value is not null");
      return this;
    }
    
    public Criteria andAttrValueEqualTo(String value)
    {
      addCriterion("attr_value =", value, "attrValue");
      return this;
    }
    
    public Criteria andAttrValueNotEqualTo(String value)
    {
      addCriterion("attr_value <>", value, "attrValue");
      return this;
    }
    
    public Criteria andAttrValueGreaterThan(String value)
    {
      addCriterion("attr_value >", value, "attrValue");
      return this;
    }
    
    public Criteria andAttrValueGreaterThanOrEqualTo(String value)
    {
      addCriterion("attr_value >=", value, "attrValue");
      return this;
    }
    
    public Criteria andAttrValueLessThan(String value)
    {
      addCriterion("attr_value <", value, "attrValue");
      return this;
    }
    
    public Criteria andAttrValueLessThanOrEqualTo(String value)
    {
      addCriterion("attr_value <=", value, "attrValue");
      return this;
    }
    
    public Criteria andAttrValueLike(String value)
    {
      addCriterion("attr_value like", value, "attrValue");
      return this;
    }
    
    public Criteria andAttrValueNotLike(String value)
    {
      addCriterion("attr_value not like", value, "attrValue");
      return this;
    }
    
    public Criteria andAttrValueIn(List values)
    {
      addCriterion("attr_value in", values, "attrValue");
      return this;
    }
    
    public Criteria andAttrValueNotIn(List values)
    {
      addCriterion("attr_value not in", values, "attrValue");
      return this;
    }
    
    public Criteria andAttrValueBetween(String value1, String value2)
    {
      addCriterion("attr_value between", value1, value2, "attrValue");
      return this;
    }
    
    public Criteria andAttrValueNotBetween(String value1, String value2)
    {
      addCriterion("attr_value not between", value1, value2, "attrValue");
      return this;
    }
    
    public Criteria andValidatedIsNull()
    {
      addCriterion("validated is null");
      return this;
    }
    
    public Criteria andValidatedIsNotNull()
    {
      addCriterion("validated is not null");
      return this;
    }
    
    public Criteria andValidatedEqualTo(String value)
    {
      addCriterion("validated =", value, "validated");
      return this;
    }
    
    public Criteria andValidatedNotEqualTo(String value)
    {
      addCriterion("validated <>", value, "validated");
      return this;
    }
    
    public Criteria andValidatedGreaterThan(String value)
    {
      addCriterion("validated >", value, "validated");
      return this;
    }
    
    public Criteria andValidatedGreaterThanOrEqualTo(String value)
    {
      addCriterion("validated >=", value, "validated");
      return this;
    }
    
    public Criteria andValidatedLessThan(String value)
    {
      addCriterion("validated <", value, "validated");
      return this;
    }
    
    public Criteria andValidatedLessThanOrEqualTo(String value)
    {
      addCriterion("validated <=", value, "validated");
      return this;
    }
    
    public Criteria andValidatedLike(String value)
    {
      addCriterion("validated like", value, "validated");
      return this;
    }
    
    public Criteria andValidatedNotLike(String value)
    {
      addCriterion("validated not like", value, "validated");
      return this;
    }
    
    public Criteria andValidatedIn(List values)
    {
      addCriterion("validated in", values, "validated");
      return this;
    }
    
    public Criteria andValidatedNotIn(List values)
    {
      addCriterion("validated not in", values, "validated");
      return this;
    }
    
    public Criteria andValidatedBetween(String value1, String value2)
    {
      addCriterion("validated between", value1, value2, "validated");
      return this;
    }
    
    public Criteria andValidatedNotBetween(String value1, String value2)
    {
      addCriterion("validated not between", value1, value2, "validated");
      return this;
    }
    
    public Criteria andCreateTimeIsNull()
    {
      addCriterion("create_time is null");
      return this;
    }
    
    public Criteria andCreateTimeIsNotNull()
    {
      addCriterion("create_time is not null");
      return this;
    }
    
    public Criteria andCreateTimeEqualTo(Date value)
    {
      addCriterion("create_time =", value, "createTime");
      return this;
    }
    
    public Criteria andCreateTimeNotEqualTo(Date value)
    {
      addCriterion("create_time <>", value, "createTime");
      return this;
    }
    
    public Criteria andCreateTimeGreaterThan(Date value)
    {
      addCriterion("create_time >", value, "createTime");
      return this;
    }
    
    public Criteria andCreateTimeGreaterThanOrEqualTo(Date value)
    {
      addCriterion("create_time >=", value, "createTime");
      return this;
    }
    
    public Criteria andCreateTimeLessThan(Date value)
    {
      addCriterion("create_time <", value, "createTime");
      return this;
    }
    
    public Criteria andCreateTimeLessThanOrEqualTo(Date value)
    {
      addCriterion("create_time <=", value, "createTime");
      return this;
    }
    
    public Criteria andCreateTimeIn(List values)
    {
      addCriterion("create_time in", values, "createTime");
      return this;
    }
    
    public Criteria andCreateTimeNotIn(List values)
    {
      addCriterion("create_time not in", values, "createTime");
      return this;
    }
    
    public Criteria andCreateTimeBetween(Date value1, Date value2)
    {
      addCriterion("create_time between", value1, value2, "createTime");
      return this;
    }
    
    public Criteria andCreateTimeNotBetween(Date value1, Date value2)
    {
      addCriterion("create_time not between", value1, value2, "createTime");
      return this;
    }
    
    public Criteria andLastUpdateTimeIsNull()
    {
      addCriterion("last_update_time is null");
      return this;
    }
    
    public Criteria andLastUpdateTimeIsNotNull()
    {
      addCriterion("last_update_time is not null");
      return this;
    }
    
    public Criteria andLastUpdateTimeEqualTo(Date value)
    {
      addCriterion("last_update_time =", value, "lastUpdateTime");
      return this;
    }
    
    public Criteria andLastUpdateTimeNotEqualTo(Date value)
    {
      addCriterion("last_update_time <>", value, "lastUpdateTime");
      return this;
    }
    
    public Criteria andLastUpdateTimeGreaterThan(Date value)
    {
      addCriterion("last_update_time >", value, "lastUpdateTime");
      return this;
    }
    
    public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value)
    {
      addCriterion("last_update_time >=", value, "lastUpdateTime");
      return this;
    }
    
    public Criteria andLastUpdateTimeLessThan(Date value)
    {
      addCriterion("last_update_time <", value, "lastUpdateTime");
      return this;
    }
    
    public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value)
    {
      addCriterion("last_update_time <=", value, "lastUpdateTime");
      return this;
    }
    
    public Criteria andLastUpdateTimeIn(List values)
    {
      addCriterion("last_update_time in", values, "lastUpdateTime");
      return this;
    }
    
    public Criteria andLastUpdateTimeNotIn(List values)
    {
      addCriterion("last_update_time not in", values, "lastUpdateTime");
      return this;
    }
    
    public Criteria andLastUpdateTimeBetween(Date value1, Date value2)
    {
      addCriterion("last_update_time between", value1, value2, "lastUpdateTime");
      return this;
    }
    
    public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2)
    {
      addCriterion("last_update_time not between", value1, value2, "lastUpdateTime");
      return this;
    }
    
    public Criteria andCreateUserIsNull()
    {
      addCriterion("create_user is null");
      return this;
    }
    
    public Criteria andCreateUserIsNotNull()
    {
      addCriterion("create_user is not null");
      return this;
    }
    
    public Criteria andCreateUserEqualTo(Integer value)
    {
      addCriterion("create_user =", value, "createUser");
      return this;
    }
    
    public Criteria andCreateUserNotEqualTo(Integer value)
    {
      addCriterion("create_user <>", value, "createUser");
      return this;
    }
    
    public Criteria andCreateUserGreaterThan(Integer value)
    {
      addCriterion("create_user >", value, "createUser");
      return this;
    }
    
    public Criteria andCreateUserGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("create_user >=", value, "createUser");
      return this;
    }
    
    public Criteria andCreateUserLessThan(Integer value)
    {
      addCriterion("create_user <", value, "createUser");
      return this;
    }
    
    public Criteria andCreateUserLessThanOrEqualTo(Integer value)
    {
      addCriterion("create_user <=", value, "createUser");
      return this;
    }
    
    public Criteria andCreateUserIn(List values)
    {
      addCriterion("create_user in", values, "createUser");
      return this;
    }
    
    public Criteria andCreateUserNotIn(List values)
    {
      addCriterion("create_user not in", values, "createUser");
      return this;
    }
    
    public Criteria andCreateUserBetween(Integer value1, Integer value2)
    {
      addCriterion("create_user between", value1, value2, "createUser");
      return this;
    }
    
    public Criteria andCreateUserNotBetween(Integer value1, Integer value2)
    {
      addCriterion("create_user not between", value1, value2, "createUser");
      return this;
    }
    
    public Criteria andUpdateUserIsNull()
    {
      addCriterion("update_user is null");
      return this;
    }
    
    public Criteria andUpdateUserIsNotNull()
    {
      addCriterion("update_user is not null");
      return this;
    }
    
    public Criteria andUpdateUserEqualTo(Integer value)
    {
      addCriterion("update_user =", value, "updateUser");
      return this;
    }
    
    public Criteria andUpdateUserNotEqualTo(Integer value)
    {
      addCriterion("update_user <>", value, "updateUser");
      return this;
    }
    
    public Criteria andUpdateUserGreaterThan(Integer value)
    {
      addCriterion("update_user >", value, "updateUser");
      return this;
    }
    
    public Criteria andUpdateUserGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("update_user >=", value, "updateUser");
      return this;
    }
    
    public Criteria andUpdateUserLessThan(Integer value)
    {
      addCriterion("update_user <", value, "updateUser");
      return this;
    }
    
    public Criteria andUpdateUserLessThanOrEqualTo(Integer value)
    {
      addCriterion("update_user <=", value, "updateUser");
      return this;
    }
    
    public Criteria andUpdateUserIn(List values)
    {
      addCriterion("update_user in", values, "updateUser");
      return this;
    }
    
    public Criteria andUpdateUserNotIn(List values)
    {
      addCriterion("update_user not in", values, "updateUser");
      return this;
    }
    
    public Criteria andUpdateUserBetween(Integer value1, Integer value2)
    {
      addCriterion("update_user between", value1, value2, "updateUser");
      return this;
    }
    
    public Criteria andUpdateUserNotBetween(Integer value1, Integer value2)
    {
      addCriterion("update_user not between", value1, value2, "updateUser");
      return this;
    }
  }
}
