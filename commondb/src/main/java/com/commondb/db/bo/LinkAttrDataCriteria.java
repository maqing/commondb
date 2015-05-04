package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkAttrDataCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  private Integer limit;
  private Integer offset;
  
  public LinkAttrDataCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected LinkAttrDataCriteria(LinkAttrDataCriteria example)
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
    
    public Criteria andFromEntityIdIsNull()
    {
      addCriterion("from_entity_id is null");
      return this;
    }
    
    public Criteria andFromEntityIdIsNotNull()
    {
      addCriterion("from_entity_id is not null");
      return this;
    }
    
    public Criteria andFromEntityIdEqualTo(Integer value)
    {
      addCriterion("from_entity_id =", value, "fromEntityId");
      return this;
    }
    
    public Criteria andFromEntityIdNotEqualTo(Integer value)
    {
      addCriterion("from_entity_id <>", value, "fromEntityId");
      return this;
    }
    
    public Criteria andFromEntityIdGreaterThan(Integer value)
    {
      addCriterion("from_entity_id >", value, "fromEntityId");
      return this;
    }
    
    public Criteria andFromEntityIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("from_entity_id >=", value, "fromEntityId");
      return this;
    }
    
    public Criteria andFromEntityIdLessThan(Integer value)
    {
      addCriterion("from_entity_id <", value, "fromEntityId");
      return this;
    }
    
    public Criteria andFromEntityIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("from_entity_id <=", value, "fromEntityId");
      return this;
    }
    
    public Criteria andFromEntityIdIn(List values)
    {
      addCriterion("from_entity_id in", values, "fromEntityId");
      return this;
    }
    
    public Criteria andFromEntityIdNotIn(List values)
    {
      addCriterion("from_entity_id not in", values, "fromEntityId");
      return this;
    }
    
    public Criteria andFromEntityIdBetween(Integer value1, Integer value2)
    {
      addCriterion("from_entity_id between", value1, value2, "fromEntityId");
      return this;
    }
    
    public Criteria andFromEntityIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("from_entity_id not between", value1, value2, "fromEntityId");
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
    
    public Criteria andLabelIsNull()
    {
      addCriterion("label is null");
      return this;
    }
    
    public Criteria andLabelIsNotNull()
    {
      addCriterion("label is not null");
      return this;
    }
    
    public Criteria andLabelEqualTo(String value)
    {
      addCriterion("label =", value, "label");
      return this;
    }
    
    public Criteria andLabelNotEqualTo(String value)
    {
      addCriterion("label <>", value, "label");
      return this;
    }
    
    public Criteria andLabelGreaterThan(String value)
    {
      addCriterion("label >", value, "label");
      return this;
    }
    
    public Criteria andLabelGreaterThanOrEqualTo(String value)
    {
      addCriterion("label >=", value, "label");
      return this;
    }
    
    public Criteria andLabelLessThan(String value)
    {
      addCriterion("label <", value, "label");
      return this;
    }
    
    public Criteria andLabelLessThanOrEqualTo(String value)
    {
      addCriterion("label <=", value, "label");
      return this;
    }
    
    public Criteria andLabelLike(String value)
    {
      addCriterion("label like", value, "label");
      return this;
    }
    
    public Criteria andLabelNotLike(String value)
    {
      addCriterion("label not like", value, "label");
      return this;
    }
    
    public Criteria andLabelIn(List values)
    {
      addCriterion("label in", values, "label");
      return this;
    }
    
    public Criteria andLabelNotIn(List values)
    {
      addCriterion("label not in", values, "label");
      return this;
    }
    
    public Criteria andLabelBetween(String value1, String value2)
    {
      addCriterion("label between", value1, value2, "label");
      return this;
    }
    
    public Criteria andLabelNotBetween(String value1, String value2)
    {
      addCriterion("label not between", value1, value2, "label");
      return this;
    }
    
    public Criteria andUrlIsNull()
    {
      addCriterion("url is null");
      return this;
    }
    
    public Criteria andUrlIsNotNull()
    {
      addCriterion("url is not null");
      return this;
    }
    
    public Criteria andUrlEqualTo(String value)
    {
      addCriterion("url =", value, "url");
      return this;
    }
    
    public Criteria andUrlNotEqualTo(String value)
    {
      addCriterion("url <>", value, "url");
      return this;
    }
    
    public Criteria andUrlGreaterThan(String value)
    {
      addCriterion("url >", value, "url");
      return this;
    }
    
    public Criteria andUrlGreaterThanOrEqualTo(String value)
    {
      addCriterion("url >=", value, "url");
      return this;
    }
    
    public Criteria andUrlLessThan(String value)
    {
      addCriterion("url <", value, "url");
      return this;
    }
    
    public Criteria andUrlLessThanOrEqualTo(String value)
    {
      addCriterion("url <=", value, "url");
      return this;
    }
    
    public Criteria andUrlLike(String value)
    {
      addCriterion("url like", value, "url");
      return this;
    }
    
    public Criteria andUrlNotLike(String value)
    {
      addCriterion("url not like", value, "url");
      return this;
    }
    
    public Criteria andUrlIn(List values)
    {
      addCriterion("url in", values, "url");
      return this;
    }
    
    public Criteria andUrlNotIn(List values)
    {
      addCriterion("url not in", values, "url");
      return this;
    }
    
    public Criteria andUrlBetween(String value1, String value2)
    {
      addCriterion("url between", value1, value2, "url");
      return this;
    }
    
    public Criteria andUrlNotBetween(String value1, String value2)
    {
      addCriterion("url not between", value1, value2, "url");
      return this;
    }
    
    public Criteria andToEntityIdIsNull()
    {
      addCriterion("to_entity_id is null");
      return this;
    }
    
    public Criteria andToEntityIdIsNotNull()
    {
      addCriterion("to_entity_id is not null");
      return this;
    }
    
    public Criteria andToEntityIdEqualTo(Integer value)
    {
      addCriterion("to_entity_id =", value, "toEntityId");
      return this;
    }
    
    public Criteria andToEntityIdNotEqualTo(Integer value)
    {
      addCriterion("to_entity_id <>", value, "toEntityId");
      return this;
    }
    
    public Criteria andToEntityIdGreaterThan(Integer value)
    {
      addCriterion("to_entity_id >", value, "toEntityId");
      return this;
    }
    
    public Criteria andToEntityIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("to_entity_id >=", value, "toEntityId");
      return this;
    }
    
    public Criteria andToEntityIdLessThan(Integer value)
    {
      addCriterion("to_entity_id <", value, "toEntityId");
      return this;
    }
    
    public Criteria andToEntityIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("to_entity_id <=", value, "toEntityId");
      return this;
    }
    
    public Criteria andToEntityIdIn(List values)
    {
      addCriterion("to_entity_id in", values, "toEntityId");
      return this;
    }
    
    public Criteria andToEntityIdNotIn(List values)
    {
      addCriterion("to_entity_id not in", values, "toEntityId");
      return this;
    }
    
    public Criteria andToEntityIdBetween(Integer value1, Integer value2)
    {
      addCriterion("to_entity_id between", value1, value2, "toEntityId");
      return this;
    }
    
    public Criteria andToEntityIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("to_entity_id not between", value1, value2, "toEntityId");
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
