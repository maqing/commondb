package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterDataCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public CharacterDataCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected CharacterDataCriteria(CharacterDataCriteria example)
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
    
    public Criteria andDataIdIsNull()
    {
      addCriterion("data_id is null");
      return this;
    }
    
    public Criteria andDataIdIsNotNull()
    {
      addCriterion("data_id is not null");
      return this;
    }
    
    public Criteria andDataIdEqualTo(Integer value)
    {
      addCriterion("data_id =", value, "dataId");
      return this;
    }
    
    public Criteria andDataIdNotEqualTo(Integer value)
    {
      addCriterion("data_id <>", value, "dataId");
      return this;
    }
    
    public Criteria andDataIdGreaterThan(Integer value)
    {
      addCriterion("data_id >", value, "dataId");
      return this;
    }
    
    public Criteria andDataIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("data_id >=", value, "dataId");
      return this;
    }
    
    public Criteria andDataIdLessThan(Integer value)
    {
      addCriterion("data_id <", value, "dataId");
      return this;
    }
    
    public Criteria andDataIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("data_id <=", value, "dataId");
      return this;
    }
    
    public Criteria andDataIdIn(List values)
    {
      addCriterion("data_id in", values, "dataId");
      return this;
    }
    
    public Criteria andDataIdNotIn(List values)
    {
      addCriterion("data_id not in", values, "dataId");
      return this;
    }
    
    public Criteria andDataIdBetween(Integer value1, Integer value2)
    {
      addCriterion("data_id between", value1, value2, "dataId");
      return this;
    }
    
    public Criteria andDataIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("data_id not between", value1, value2, "dataId");
      return this;
    }
    
    public Criteria andDataNameIsNull()
    {
      addCriterion("data_name is null");
      return this;
    }
    
    public Criteria andDataNameIsNotNull()
    {
      addCriterion("data_name is not null");
      return this;
    }
    
    public Criteria andDataNameEqualTo(String value)
    {
      addCriterion("data_name =", value, "dataName");
      return this;
    }
    
    public Criteria andDataNameNotEqualTo(String value)
    {
      addCriterion("data_name <>", value, "dataName");
      return this;
    }
    
    public Criteria andDataNameGreaterThan(String value)
    {
      addCriterion("data_name >", value, "dataName");
      return this;
    }
    
    public Criteria andDataNameGreaterThanOrEqualTo(String value)
    {
      addCriterion("data_name >=", value, "dataName");
      return this;
    }
    
    public Criteria andDataNameLessThan(String value)
    {
      addCriterion("data_name <", value, "dataName");
      return this;
    }
    
    public Criteria andDataNameLessThanOrEqualTo(String value)
    {
      addCriterion("data_name <=", value, "dataName");
      return this;
    }
    
    public Criteria andDataNameLike(String value)
    {
      addCriterion("data_name like", value, "dataName");
      return this;
    }
    
    public Criteria andDataNameNotLike(String value)
    {
      addCriterion("data_name not like", value, "dataName");
      return this;
    }
    
    public Criteria andDataNameIn(List values)
    {
      addCriterion("data_name in", values, "dataName");
      return this;
    }
    
    public Criteria andDataNameNotIn(List values)
    {
      addCriterion("data_name not in", values, "dataName");
      return this;
    }
    
    public Criteria andDataNameBetween(String value1, String value2)
    {
      addCriterion("data_name between", value1, value2, "dataName");
      return this;
    }
    
    public Criteria andDataNameNotBetween(String value1, String value2)
    {
      addCriterion("data_name not between", value1, value2, "dataName");
      return this;
    }
    
    public Criteria andDataDescIsNull()
    {
      addCriterion("data_desc is null");
      return this;
    }
    
    public Criteria andDataDescIsNotNull()
    {
      addCriterion("data_desc is not null");
      return this;
    }
    
    public Criteria andDataDescEqualTo(String value)
    {
      addCriterion("data_desc =", value, "dataDesc");
      return this;
    }
    
    public Criteria andDataDescNotEqualTo(String value)
    {
      addCriterion("data_desc <>", value, "dataDesc");
      return this;
    }
    
    public Criteria andDataDescGreaterThan(String value)
    {
      addCriterion("data_desc >", value, "dataDesc");
      return this;
    }
    
    public Criteria andDataDescGreaterThanOrEqualTo(String value)
    {
      addCriterion("data_desc >=", value, "dataDesc");
      return this;
    }
    
    public Criteria andDataDescLessThan(String value)
    {
      addCriterion("data_desc <", value, "dataDesc");
      return this;
    }
    
    public Criteria andDataDescLessThanOrEqualTo(String value)
    {
      addCriterion("data_desc <=", value, "dataDesc");
      return this;
    }
    
    public Criteria andDataDescLike(String value)
    {
      addCriterion("data_desc like", value, "dataDesc");
      return this;
    }
    
    public Criteria andDataDescNotLike(String value)
    {
      addCriterion("data_desc not like", value, "dataDesc");
      return this;
    }
    
    public Criteria andDataDescIn(List values)
    {
      addCriterion("data_desc in", values, "dataDesc");
      return this;
    }
    
    public Criteria andDataDescNotIn(List values)
    {
      addCriterion("data_desc not in", values, "dataDesc");
      return this;
    }
    
    public Criteria andDataDescBetween(String value1, String value2)
    {
      addCriterion("data_desc between", value1, value2, "dataDesc");
      return this;
    }
    
    public Criteria andDataDescNotBetween(String value1, String value2)
    {
      addCriterion("data_desc not between", value1, value2, "dataDesc");
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
    
    public Criteria andIssharedIsNull()
    {
      addCriterion("isShared is null");
      return this;
    }
    
    public Criteria andIssharedIsNotNull()
    {
      addCriterion("isShared is not null");
      return this;
    }
    
    public Criteria andIssharedEqualTo(Integer value)
    {
      addCriterion("isShared =", value, "isshared");
      return this;
    }
    
    public Criteria andIssharedNotEqualTo(Integer value)
    {
      addCriterion("isShared <>", value, "isshared");
      return this;
    }
    
    public Criteria andIssharedGreaterThan(Integer value)
    {
      addCriterion("isShared >", value, "isshared");
      return this;
    }
    
    public Criteria andIssharedGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("isShared >=", value, "isshared");
      return this;
    }
    
    public Criteria andIssharedLessThan(Integer value)
    {
      addCriterion("isShared <", value, "isshared");
      return this;
    }
    
    public Criteria andIssharedLessThanOrEqualTo(Integer value)
    {
      addCriterion("isShared <=", value, "isshared");
      return this;
    }
    
    public Criteria andIssharedIn(List values)
    {
      addCriterion("isShared in", values, "isshared");
      return this;
    }
    
    public Criteria andIssharedNotIn(List values)
    {
      addCriterion("isShared not in", values, "isshared");
      return this;
    }
    
    public Criteria andIssharedBetween(Integer value1, Integer value2)
    {
      addCriterion("isShared between", value1, value2, "isshared");
      return this;
    }
    
    public Criteria andIssharedNotBetween(Integer value1, Integer value2)
    {
      addCriterion("isShared not between", value1, value2, "isshared");
      return this;
    }
    
    public Criteria andSortIsNull()
    {
      addCriterion("sort is null");
      return this;
    }
    
    public Criteria andSortIsNotNull()
    {
      addCriterion("sort is not null");
      return this;
    }
    
    public Criteria andSortEqualTo(Integer value)
    {
      addCriterion("sort =", value, "sort");
      return this;
    }
    
    public Criteria andSortNotEqualTo(Integer value)
    {
      addCriterion("sort <>", value, "sort");
      return this;
    }
    
    public Criteria andSortGreaterThan(Integer value)
    {
      addCriterion("sort >", value, "sort");
      return this;
    }
    
    public Criteria andSortGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("sort >=", value, "sort");
      return this;
    }
    
    public Criteria andSortLessThan(Integer value)
    {
      addCriterion("sort <", value, "sort");
      return this;
    }
    
    public Criteria andSortLessThanOrEqualTo(Integer value)
    {
      addCriterion("sort <=", value, "sort");
      return this;
    }
    
    public Criteria andSortIn(List values)
    {
      addCriterion("sort in", values, "sort");
      return this;
    }
    
    public Criteria andSortNotIn(List values)
    {
      addCriterion("sort not in", values, "sort");
      return this;
    }
    
    public Criteria andSortBetween(Integer value1, Integer value2)
    {
      addCriterion("sort between", value1, value2, "sort");
      return this;
    }
    
    public Criteria andSortNotBetween(Integer value1, Integer value2)
    {
      addCriterion("sort not between", value1, value2, "sort");
      return this;
    }
    
    public Criteria andParIdIsNull()
    {
      addCriterion("par_id is null");
      return this;
    }
    
    public Criteria andParIdIsNotNull()
    {
      addCriterion("par_id is not null");
      return this;
    }
    
    public Criteria andParIdEqualTo(Integer value)
    {
      addCriterion("par_id =", value, "parId");
      return this;
    }
    
    public Criteria andParIdNotEqualTo(Integer value)
    {
      addCriterion("par_id <>", value, "parId");
      return this;
    }
    
    public Criteria andParIdGreaterThan(Integer value)
    {
      addCriterion("par_id >", value, "parId");
      return this;
    }
    
    public Criteria andParIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("par_id >=", value, "parId");
      return this;
    }
    
    public Criteria andParIdLessThan(Integer value)
    {
      addCriterion("par_id <", value, "parId");
      return this;
    }
    
    public Criteria andParIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("par_id <=", value, "parId");
      return this;
    }
    
    public Criteria andParIdIn(List values)
    {
      addCriterion("par_id in", values, "parId");
      return this;
    }
    
    public Criteria andParIdNotIn(List values)
    {
      addCriterion("par_id not in", values, "parId");
      return this;
    }
    
    public Criteria andParIdBetween(Integer value1, Integer value2)
    {
      addCriterion("par_id between", value1, value2, "parId");
      return this;
    }
    
    public Criteria andParIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("par_id not between", value1, value2, "parId");
      return this;
    }
  }
}
