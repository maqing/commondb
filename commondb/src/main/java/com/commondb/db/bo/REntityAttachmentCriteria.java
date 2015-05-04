package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class REntityAttachmentCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public REntityAttachmentCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected REntityAttachmentCriteria(REntityAttachmentCriteria example)
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
      addCriterion("ID is null");
      return this;
    }
    
    public Criteria andIdIsNotNull()
    {
      addCriterion("ID is not null");
      return this;
    }
    
    public Criteria andIdEqualTo(String value)
    {
      addCriterion("ID =", value, "id");
      return this;
    }
    
    public Criteria andIdNotEqualTo(String value)
    {
      addCriterion("ID <>", value, "id");
      return this;
    }
    
    public Criteria andIdGreaterThan(String value)
    {
      addCriterion("ID >", value, "id");
      return this;
    }
    
    public Criteria andIdGreaterThanOrEqualTo(String value)
    {
      addCriterion("ID >=", value, "id");
      return this;
    }
    
    public Criteria andIdLessThan(String value)
    {
      addCriterion("ID <", value, "id");
      return this;
    }
    
    public Criteria andIdLessThanOrEqualTo(String value)
    {
      addCriterion("ID <=", value, "id");
      return this;
    }
    
    public Criteria andIdLike(String value)
    {
      addCriterion("ID like", value, "id");
      return this;
    }
    
    public Criteria andIdNotLike(String value)
    {
      addCriterion("ID not like", value, "id");
      return this;
    }
    
    public Criteria andIdIn(List values)
    {
      addCriterion("ID in", values, "id");
      return this;
    }
    
    public Criteria andIdNotIn(List values)
    {
      addCriterion("ID not in", values, "id");
      return this;
    }
    
    public Criteria andIdBetween(String value1, String value2)
    {
      addCriterion("ID between", value1, value2, "id");
      return this;
    }
    
    public Criteria andIdNotBetween(String value1, String value2)
    {
      addCriterion("ID not between", value1, value2, "id");
      return this;
    }
    
    public Criteria andMetaIdIsNull()
    {
      addCriterion("META_ID is null");
      return this;
    }
    
    public Criteria andMetaIdIsNotNull()
    {
      addCriterion("META_ID is not null");
      return this;
    }
    
    public Criteria andMetaIdEqualTo(Integer value)
    {
      addCriterion("META_ID =", value, "metaId");
      return this;
    }
    
    public Criteria andMetaIdNotEqualTo(Integer value)
    {
      addCriterion("META_ID <>", value, "metaId");
      return this;
    }
    
    public Criteria andMetaIdGreaterThan(Integer value)
    {
      addCriterion("META_ID >", value, "metaId");
      return this;
    }
    
    public Criteria andMetaIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("META_ID >=", value, "metaId");
      return this;
    }
    
    public Criteria andMetaIdLessThan(Integer value)
    {
      addCriterion("META_ID <", value, "metaId");
      return this;
    }
    
    public Criteria andMetaIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("META_ID <=", value, "metaId");
      return this;
    }
    
    public Criteria andMetaIdIn(List values)
    {
      addCriterion("META_ID in", values, "metaId");
      return this;
    }
    
    public Criteria andMetaIdNotIn(List values)
    {
      addCriterion("META_ID not in", values, "metaId");
      return this;
    }
    
    public Criteria andMetaIdBetween(Integer value1, Integer value2)
    {
      addCriterion("META_ID between", value1, value2, "metaId");
      return this;
    }
    
    public Criteria andMetaIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("META_ID not between", value1, value2, "metaId");
      return this;
    }
    
    public Criteria andEntityIdIsNull()
    {
      addCriterion("ENTITY_ID is null");
      return this;
    }
    
    public Criteria andEntityIdIsNotNull()
    {
      addCriterion("ENTITY_ID is not null");
      return this;
    }
    
    public Criteria andEntityIdEqualTo(String value)
    {
      addCriterion("ENTITY_ID =", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdNotEqualTo(String value)
    {
      addCriterion("ENTITY_ID <>", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdGreaterThan(String value)
    {
      addCriterion("ENTITY_ID >", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdGreaterThanOrEqualTo(String value)
    {
      addCriterion("ENTITY_ID >=", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdLessThan(String value)
    {
      addCriterion("ENTITY_ID <", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdLessThanOrEqualTo(String value)
    {
      addCriterion("ENTITY_ID <=", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdLike(String value)
    {
      addCriterion("ENTITY_ID like", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdNotLike(String value)
    {
      addCriterion("ENTITY_ID not like", value, "entityId");
      return this;
    }
    
    public Criteria andEntityIdIn(List values)
    {
      addCriterion("ENTITY_ID in", values, "entityId");
      return this;
    }
    
    public Criteria andEntityIdNotIn(List values)
    {
      addCriterion("ENTITY_ID not in", values, "entityId");
      return this;
    }
    
    public Criteria andEntityIdBetween(String value1, String value2)
    {
      addCriterion("ENTITY_ID between", value1, value2, "entityId");
      return this;
    }
    
    public Criteria andEntityIdNotBetween(String value1, String value2)
    {
      addCriterion("ENTITY_ID not between", value1, value2, "entityId");
      return this;
    }
    
    public Criteria andAttachmentNameIsNull()
    {
      addCriterion("ATTACHMENT_NAME is null");
      return this;
    }
    
    public Criteria andAttachmentNameIsNotNull()
    {
      addCriterion("ATTACHMENT_NAME is not null");
      return this;
    }
    
    public Criteria andAttachmentNameEqualTo(String value)
    {
      addCriterion("ATTACHMENT_NAME =", value, "attachmentName");
      return this;
    }
    
    public Criteria andAttachmentNameNotEqualTo(String value)
    {
      addCriterion("ATTACHMENT_NAME <>", value, "attachmentName");
      return this;
    }
    
    public Criteria andAttachmentNameGreaterThan(String value)
    {
      addCriterion("ATTACHMENT_NAME >", value, "attachmentName");
      return this;
    }
    
    public Criteria andAttachmentNameGreaterThanOrEqualTo(String value)
    {
      addCriterion("ATTACHMENT_NAME >=", value, "attachmentName");
      return this;
    }
    
    public Criteria andAttachmentNameLessThan(String value)
    {
      addCriterion("ATTACHMENT_NAME <", value, "attachmentName");
      return this;
    }
    
    public Criteria andAttachmentNameLessThanOrEqualTo(String value)
    {
      addCriterion("ATTACHMENT_NAME <=", value, "attachmentName");
      return this;
    }
    
    public Criteria andAttachmentNameLike(String value)
    {
      addCriterion("ATTACHMENT_NAME like", value, "attachmentName");
      return this;
    }
    
    public Criteria andAttachmentNameNotLike(String value)
    {
      addCriterion("ATTACHMENT_NAME not like", value, "attachmentName");
      return this;
    }
    
    public Criteria andAttachmentNameIn(List values)
    {
      addCriterion("ATTACHMENT_NAME in", values, "attachmentName");
      return this;
    }
    
    public Criteria andAttachmentNameNotIn(List values)
    {
      addCriterion("ATTACHMENT_NAME not in", values, "attachmentName");
      return this;
    }
    
    public Criteria andAttachmentNameBetween(String value1, String value2)
    {
      addCriterion("ATTACHMENT_NAME between", value1, value2, "attachmentName");
      return this;
    }
    
    public Criteria andAttachmentNameNotBetween(String value1, String value2)
    {
      addCriterion("ATTACHMENT_NAME not between", value1, value2, "attachmentName");
      return this;
    }
    
    public Criteria andAttachmentPathIsNull()
    {
      addCriterion("ATTACHMENT_PATH is null");
      return this;
    }
    
    public Criteria andAttachmentPathIsNotNull()
    {
      addCriterion("ATTACHMENT_PATH is not null");
      return this;
    }
    
    public Criteria andAttachmentPathEqualTo(String value)
    {
      addCriterion("ATTACHMENT_PATH =", value, "attachmentPath");
      return this;
    }
    
    public Criteria andAttachmentPathNotEqualTo(String value)
    {
      addCriterion("ATTACHMENT_PATH <>", value, "attachmentPath");
      return this;
    }
    
    public Criteria andAttachmentPathGreaterThan(String value)
    {
      addCriterion("ATTACHMENT_PATH >", value, "attachmentPath");
      return this;
    }
    
    public Criteria andAttachmentPathGreaterThanOrEqualTo(String value)
    {
      addCriterion("ATTACHMENT_PATH >=", value, "attachmentPath");
      return this;
    }
    
    public Criteria andAttachmentPathLessThan(String value)
    {
      addCriterion("ATTACHMENT_PATH <", value, "attachmentPath");
      return this;
    }
    
    public Criteria andAttachmentPathLessThanOrEqualTo(String value)
    {
      addCriterion("ATTACHMENT_PATH <=", value, "attachmentPath");
      return this;
    }
    
    public Criteria andAttachmentPathLike(String value)
    {
      addCriterion("ATTACHMENT_PATH like", value, "attachmentPath");
      return this;
    }
    
    public Criteria andAttachmentPathNotLike(String value)
    {
      addCriterion("ATTACHMENT_PATH not like", value, "attachmentPath");
      return this;
    }
    
    public Criteria andAttachmentPathIn(List values)
    {
      addCriterion("ATTACHMENT_PATH in", values, "attachmentPath");
      return this;
    }
    
    public Criteria andAttachmentPathNotIn(List values)
    {
      addCriterion("ATTACHMENT_PATH not in", values, "attachmentPath");
      return this;
    }
    
    public Criteria andAttachmentPathBetween(String value1, String value2)
    {
      addCriterion("ATTACHMENT_PATH between", value1, value2, "attachmentPath");
      return this;
    }
    
    public Criteria andAttachmentPathNotBetween(String value1, String value2)
    {
      addCriterion("ATTACHMENT_PATH not between", value1, value2, "attachmentPath");
      return this;
    }
  }
}
