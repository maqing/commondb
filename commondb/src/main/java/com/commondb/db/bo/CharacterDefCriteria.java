package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterDefCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public CharacterDefCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected CharacterDefCriteria(CharacterDefCriteria example)
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
    
    public Criteria andCharaNameIsNull()
    {
      addCriterion("chara_name is null");
      return this;
    }
    
    public Criteria andCharaNameIsNotNull()
    {
      addCriterion("chara_name is not null");
      return this;
    }
    
    public Criteria andCharaNameEqualTo(String value)
    {
      addCriterion("chara_name =", value, "charaName");
      return this;
    }
    
    public Criteria andCharaNameNotEqualTo(String value)
    {
      addCriterion("chara_name <>", value, "charaName");
      return this;
    }
    
    public Criteria andCharaNameGreaterThan(String value)
    {
      addCriterion("chara_name >", value, "charaName");
      return this;
    }
    
    public Criteria andCharaNameGreaterThanOrEqualTo(String value)
    {
      addCriterion("chara_name >=", value, "charaName");
      return this;
    }
    
    public Criteria andCharaNameLessThan(String value)
    {
      addCriterion("chara_name <", value, "charaName");
      return this;
    }
    
    public Criteria andCharaNameLessThanOrEqualTo(String value)
    {
      addCriterion("chara_name <=", value, "charaName");
      return this;
    }
    
    public Criteria andCharaNameLike(String value)
    {
      addCriterion("chara_name like", value, "charaName");
      return this;
    }
    
    public Criteria andCharaNameNotLike(String value)
    {
      addCriterion("chara_name not like", value, "charaName");
      return this;
    }
    
    public Criteria andCharaNameIn(List values)
    {
      addCriterion("chara_name in", values, "charaName");
      return this;
    }
    
    public Criteria andCharaNameNotIn(List values)
    {
      addCriterion("chara_name not in", values, "charaName");
      return this;
    }
    
    public Criteria andCharaNameBetween(String value1, String value2)
    {
      addCriterion("chara_name between", value1, value2, "charaName");
      return this;
    }
    
    public Criteria andCharaNameNotBetween(String value1, String value2)
    {
      addCriterion("chara_name not between", value1, value2, "charaName");
      return this;
    }
    
    public Criteria andCharaDescIsNull()
    {
      addCriterion("chara_desc is null");
      return this;
    }
    
    public Criteria andCharaDescIsNotNull()
    {
      addCriterion("chara_desc is not null");
      return this;
    }
    
    public Criteria andCharaDescEqualTo(String value)
    {
      addCriterion("chara_desc =", value, "charaDesc");
      return this;
    }
    
    public Criteria andCharaDescNotEqualTo(String value)
    {
      addCriterion("chara_desc <>", value, "charaDesc");
      return this;
    }
    
    public Criteria andCharaDescGreaterThan(String value)
    {
      addCriterion("chara_desc >", value, "charaDesc");
      return this;
    }
    
    public Criteria andCharaDescGreaterThanOrEqualTo(String value)
    {
      addCriterion("chara_desc >=", value, "charaDesc");
      return this;
    }
    
    public Criteria andCharaDescLessThan(String value)
    {
      addCriterion("chara_desc <", value, "charaDesc");
      return this;
    }
    
    public Criteria andCharaDescLessThanOrEqualTo(String value)
    {
      addCriterion("chara_desc <=", value, "charaDesc");
      return this;
    }
    
    public Criteria andCharaDescLike(String value)
    {
      addCriterion("chara_desc like", value, "charaDesc");
      return this;
    }
    
    public Criteria andCharaDescNotLike(String value)
    {
      addCriterion("chara_desc not like", value, "charaDesc");
      return this;
    }
    
    public Criteria andCharaDescIn(List values)
    {
      addCriterion("chara_desc in", values, "charaDesc");
      return this;
    }
    
    public Criteria andCharaDescNotIn(List values)
    {
      addCriterion("chara_desc not in", values, "charaDesc");
      return this;
    }
    
    public Criteria andCharaDescBetween(String value1, String value2)
    {
      addCriterion("chara_desc between", value1, value2, "charaDesc");
      return this;
    }
    
    public Criteria andCharaDescNotBetween(String value1, String value2)
    {
      addCriterion("chara_desc not between", value1, value2, "charaDesc");
      return this;
    }
    
    public Criteria andChecklevelIsNull()
    {
      addCriterion("checkLevel is null");
      return this;
    }
    
    public Criteria andChecklevelIsNotNull()
    {
      addCriterion("checkLevel is not null");
      return this;
    }
    
    public Criteria andChecklevelEqualTo(Integer value)
    {
      addCriterion("checkLevel =", value, "checklevel");
      return this;
    }
    
    public Criteria andChecklevelNotEqualTo(Integer value)
    {
      addCriterion("checkLevel <>", value, "checklevel");
      return this;
    }
    
    public Criteria andChecklevelGreaterThan(Integer value)
    {
      addCriterion("checkLevel >", value, "checklevel");
      return this;
    }
    
    public Criteria andChecklevelGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("checkLevel >=", value, "checklevel");
      return this;
    }
    
    public Criteria andChecklevelLessThan(Integer value)
    {
      addCriterion("checkLevel <", value, "checklevel");
      return this;
    }
    
    public Criteria andChecklevelLessThanOrEqualTo(Integer value)
    {
      addCriterion("checkLevel <=", value, "checklevel");
      return this;
    }
    
    public Criteria andChecklevelIn(List values)
    {
      addCriterion("checkLevel in", values, "checklevel");
      return this;
    }
    
    public Criteria andChecklevelNotIn(List values)
    {
      addCriterion("checkLevel not in", values, "checklevel");
      return this;
    }
    
    public Criteria andChecklevelBetween(Integer value1, Integer value2)
    {
      addCriterion("checkLevel between", value1, value2, "checklevel");
      return this;
    }
    
    public Criteria andChecklevelNotBetween(Integer value1, Integer value2)
    {
      addCriterion("checkLevel not between", value1, value2, "checklevel");
      return this;
    }
    
    public Criteria andIscheckmultipleIsNull()
    {
      addCriterion("isCheckMultiple is null");
      return this;
    }
    
    public Criteria andIscheckmultipleIsNotNull()
    {
      addCriterion("isCheckMultiple is not null");
      return this;
    }
    
    public Criteria andIscheckmultipleEqualTo(Integer value)
    {
      addCriterion("isCheckMultiple =", value, "ischeckmultiple");
      return this;
    }
    
    public Criteria andIscheckmultipleNotEqualTo(Integer value)
    {
      addCriterion("isCheckMultiple <>", value, "ischeckmultiple");
      return this;
    }
    
    public Criteria andIscheckmultipleGreaterThan(Integer value)
    {
      addCriterion("isCheckMultiple >", value, "ischeckmultiple");
      return this;
    }
    
    public Criteria andIscheckmultipleGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("isCheckMultiple >=", value, "ischeckmultiple");
      return this;
    }
    
    public Criteria andIscheckmultipleLessThan(Integer value)
    {
      addCriterion("isCheckMultiple <", value, "ischeckmultiple");
      return this;
    }
    
    public Criteria andIscheckmultipleLessThanOrEqualTo(Integer value)
    {
      addCriterion("isCheckMultiple <=", value, "ischeckmultiple");
      return this;
    }
    
    public Criteria andIscheckmultipleIn(List values)
    {
      addCriterion("isCheckMultiple in", values, "ischeckmultiple");
      return this;
    }
    
    public Criteria andIscheckmultipleNotIn(List values)
    {
      addCriterion("isCheckMultiple not in", values, "ischeckmultiple");
      return this;
    }
    
    public Criteria andIscheckmultipleBetween(Integer value1, Integer value2)
    {
      addCriterion("isCheckMultiple between", value1, value2, 
        "ischeckmultiple");
      return this;
    }
    
    public Criteria andIscheckmultipleNotBetween(Integer value1, Integer value2)
    {
      addCriterion("isCheckMultiple not between", value1, value2, 
        "ischeckmultiple");
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
  }
}
