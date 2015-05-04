package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DescAttrDefCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  private Integer limit;
  private Integer offset;
  
  public DescAttrDefCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected DescAttrDefCriteria(DescAttrDefCriteria example)
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
    
    public Criteria andAttrNameIsNull()
    {
      addCriterion("attr_name is null");
      return this;
    }
    
    public Criteria andAttrNameIsNotNull()
    {
      addCriterion("attr_name is not null");
      return this;
    }
    
    public Criteria andAttrNameEqualTo(String value)
    {
      addCriterion("attr_name =", value, "attrName");
      return this;
    }
    
    public Criteria andAttrNameNotEqualTo(String value)
    {
      addCriterion("attr_name <>", value, "attrName");
      return this;
    }
    
    public Criteria andAttrNameGreaterThan(String value)
    {
      addCriterion("attr_name >", value, "attrName");
      return this;
    }
    
    public Criteria andAttrNameGreaterThanOrEqualTo(String value)
    {
      addCriterion("attr_name >=", value, "attrName");
      return this;
    }
    
    public Criteria andAttrNameLessThan(String value)
    {
      addCriterion("attr_name <", value, "attrName");
      return this;
    }
    
    public Criteria andAttrNameLessThanOrEqualTo(String value)
    {
      addCriterion("attr_name <=", value, "attrName");
      return this;
    }
    
    public Criteria andAttrNameLike(String value)
    {
      addCriterion("attr_name like", value, "attrName");
      return this;
    }
    
    public Criteria andAttrNameNotLike(String value)
    {
      addCriterion("attr_name not like", value, "attrName");
      return this;
    }
    
    public Criteria andAttrNameIn(List values)
    {
      addCriterion("attr_name in", values, "attrName");
      return this;
    }
    
    public Criteria andAttrNameNotIn(List values)
    {
      addCriterion("attr_name not in", values, "attrName");
      return this;
    }
    
    public Criteria andAttrNameBetween(String value1, String value2)
    {
      addCriterion("attr_name between", value1, value2, "attrName");
      return this;
    }
    
    public Criteria andAttrNameNotBetween(String value1, String value2)
    {
      addCriterion("attr_name not between", value1, value2, "attrName");
      return this;
    }
    
    public Criteria andLengthLimitIsNull()
    {
      addCriterion("length_limit is null");
      return this;
    }
    
    public Criteria andLengthLimitIsNotNull()
    {
      addCriterion("length_limit is not null");
      return this;
    }
    
    public Criteria andLengthLimitEqualTo(Integer value)
    {
      addCriterion("length_limit =", value, "lengthLimit");
      return this;
    }
    
    public Criteria andLengthLimitNotEqualTo(Integer value)
    {
      addCriterion("length_limit <>", value, "lengthLimit");
      return this;
    }
    
    public Criteria andLengthLimitGreaterThan(Integer value)
    {
      addCriterion("length_limit >", value, "lengthLimit");
      return this;
    }
    
    public Criteria andLengthLimitGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("length_limit >=", value, "lengthLimit");
      return this;
    }
    
    public Criteria andLengthLimitLessThan(Integer value)
    {
      addCriterion("length_limit <", value, "lengthLimit");
      return this;
    }
    
    public Criteria andLengthLimitLessThanOrEqualTo(Integer value)
    {
      addCriterion("length_limit <=", value, "lengthLimit");
      return this;
    }
    
    public Criteria andLengthLimitIn(List values)
    {
      addCriterion("length_limit in", values, "lengthLimit");
      return this;
    }
    
    public Criteria andLengthLimitNotIn(List values)
    {
      addCriterion("length_limit not in", values, "lengthLimit");
      return this;
    }
    
    public Criteria andLengthLimitBetween(Integer value1, Integer value2)
    {
      addCriterion("length_limit between", value1, value2, "lengthLimit");
      return this;
    }
    
    public Criteria andLengthLimitNotBetween(Integer value1, Integer value2)
    {
      addCriterion("length_limit not between", value1, value2, "lengthLimit");
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
