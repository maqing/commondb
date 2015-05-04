package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationBoxCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public OperationBoxCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected OperationBoxCriteria(OperationBoxCriteria example)
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
    
    public Criteria andIdEqualTo(String value)
    {
      addCriterion("id =", value, "id");
      return this;
    }
    
    public Criteria andIdNotEqualTo(String value)
    {
      addCriterion("id <>", value, "id");
      return this;
    }
    
    public Criteria andIdGreaterThan(String value)
    {
      addCriterion("id >", value, "id");
      return this;
    }
    
    public Criteria andIdGreaterThanOrEqualTo(String value)
    {
      addCriterion("id >=", value, "id");
      return this;
    }
    
    public Criteria andIdLessThan(String value)
    {
      addCriterion("id <", value, "id");
      return this;
    }
    
    public Criteria andIdLessThanOrEqualTo(String value)
    {
      addCriterion("id <=", value, "id");
      return this;
    }
    
    public Criteria andIdLike(String value)
    {
      addCriterion("id like", value, "id");
      return this;
    }
    
    public Criteria andIdNotLike(String value)
    {
      addCriterion("id not like", value, "id");
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
    
    public Criteria andIdBetween(String value1, String value2)
    {
      addCriterion("id between", value1, value2, "id");
      return this;
    }
    
    public Criteria andIdNotBetween(String value1, String value2)
    {
      addCriterion("id not between", value1, value2, "id");
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
    
    public Criteria andEntityIdIsNull()
    {
      addCriterion("entity_id is null");
      return this;
    }
    
    public Criteria andEntityIdIsNotNull()
    {
      addCriterion("entity_id is not null");
      return this;
    }
    
    public Criteria andEntityIdEqualTo(String value)
    {
      addCriterion("entity_id =", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdNotEqualTo(String value)
    {
      addCriterion("entity_id <>", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdGreaterThan(String value)
    {
      addCriterion("entity_id >", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdGreaterThanOrEqualTo(String value)
    {
      addCriterion("entity_id >=", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdLessThan(String value)
    {
      addCriterion("entity_id <", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdLessThanOrEqualTo(String value)
    {
      addCriterion("entity_id <=", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdLike(String value)
    {
      addCriterion("entity_id like", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdNotLike(String value)
    {
      addCriterion("entity_id not like", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdIn(List values)
    {
      addCriterion("entity_id in", values, "entityId");
      return this;
    }
    
    public Criteria andEntityIdNotIn(List values)
    {
      addCriterion("entity_id not in", values, "entityId");
      return this;
    }
    
    public Criteria andEntityIdBetween(String value1, String value2)
    {
      addCriterion("entity_id between", value1, value2, "entityId");
      return this;
    }
    
    public Criteria andEntityIdNotBetween(String value1, String value2)
    {
      addCriterion("entity_id not between", value1, value2, "entityId");
      return this;
    }
    
    public Criteria andOperationTypeIsNull()
    {
      addCriterion("operation_type is null");
      return this;
    }
    
    public Criteria andOperationTypeIsNotNull()
    {
      addCriterion("operation_type is not null");
      return this;
    }
    
    public Criteria andOperationTypeEqualTo(String value)
    {
      addCriterion("operation_type =", value, "operationType");
      return this;
    }
    
    public Criteria andOperationTypeNotEqualTo(String value)
    {
      addCriterion("operation_type <>", value, "operationType");
      return this;
    }
    
    public Criteria andOperationTypeGreaterThan(String value)
    {
      addCriterion("operation_type >", value, "operationType");
      return this;
    }
    
    public Criteria andOperationTypeGreaterThanOrEqualTo(String value)
    {
      addCriterion("operation_type >=", value, "operationType");
      return this;
    }
    
    public Criteria andOperationTypeLessThan(String value)
    {
      addCriterion("operation_type <", value, "operationType");
      return this;
    }
    
    public Criteria andOperationTypeLessThanOrEqualTo(String value)
    {
      addCriterion("operation_type <=", value, "operationType");
      return this;
    }
    
    public Criteria andOperationTypeLike(String value)
    {
      addCriterion("operation_type like", value, "operationType");
      return this;
    }
    
    public Criteria andOperationTypeNotLike(String value)
    {
      addCriterion("operation_type not like", value, "operationType");
      return this;
    }
    
    public Criteria andOperationTypeIn(List values)
    {
      addCriterion("operation_type in", values, "operationType");
      return this;
    }
    
    public Criteria andOperationTypeNotIn(List values)
    {
      addCriterion("operation_type not in", values, "operationType");
      return this;
    }
    
    public Criteria andOperationTypeBetween(String value1, String value2)
    {
      addCriterion("operation_type between", value1, value2, "operationType");
      return this;
    }
    
    public Criteria andOperationTypeNotBetween(String value1, String value2)
    {
      addCriterion("operation_type not between", value1, value2, "operationType");
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
    
    public Criteria andCreateUserEqualTo(String value)
    {
      addCriterion("create_user =", value, "createUser");
      return this;
    }
    
    public Criteria andCreateUserNotEqualTo(String value)
    {
      addCriterion("create_user <>", value, "createUser");
      return this;
    }
    
    public Criteria andCreateUserGreaterThan(String value)
    {
      addCriterion("create_user >", value, "createUser");
      return this;
    }
    
    public Criteria andCreateUserGreaterThanOrEqualTo(String value)
    {
      addCriterion("create_user >=", value, "createUser");
      return this;
    }
    
    public Criteria andCreateUserLessThan(String value)
    {
      addCriterion("create_user <", value, "createUser");
      return this;
    }
    
    public Criteria andCreateUserLessThanOrEqualTo(String value)
    {
      addCriterion("create_user <=", value, "createUser");
      return this;
    }
    
    public Criteria andCreateUserLike(String value)
    {
      addCriterion("create_user like", value, "createUser");
      return this;
    }
    
    public Criteria andCreateUserNotLike(String value)
    {
      addCriterion("create_user not like", value, "createUser");
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
    
    public Criteria andCreateUserBetween(String value1, String value2)
    {
      addCriterion("create_user between", value1, value2, "createUser");
      return this;
    }
    
    public Criteria andCreateUserNotBetween(String value1, String value2)
    {
      addCriterion("create_user not between", value1, value2, "createUser");
      return this;
    }
  }
}
