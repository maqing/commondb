package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PicCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public PicCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected PicCriteria(PicCriteria example)
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
    
    public Criteria andPicIdIsNull()
    {
      addCriterion("pic_id is null");
      return this;
    }
    
    public Criteria andPicIdIsNotNull()
    {
      addCriterion("pic_id is not null");
      return this;
    }
    
    public Criteria andPicIdEqualTo(Integer value)
    {
      addCriterion("pic_id =", value, "picId");
      return this;
    }
    
    public Criteria andPicIdNotEqualTo(Integer value)
    {
      addCriterion("pic_id <>", value, "picId");
      return this;
    }
    
    public Criteria andPicIdGreaterThan(Integer value)
    {
      addCriterion("pic_id >", value, "picId");
      return this;
    }
    
    public Criteria andPicIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("pic_id >=", value, "picId");
      return this;
    }
    
    public Criteria andPicIdLessThan(Integer value)
    {
      addCriterion("pic_id <", value, "picId");
      return this;
    }
    
    public Criteria andPicIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("pic_id <=", value, "picId");
      return this;
    }
    
    public Criteria andPicIdIn(List values)
    {
      addCriterion("pic_id in", values, "picId");
      return this;
    }
    
    public Criteria andPicIdNotIn(List values)
    {
      addCriterion("pic_id not in", values, "picId");
      return this;
    }
    
    public Criteria andPicIdBetween(Integer value1, Integer value2)
    {
      addCriterion("pic_id between", value1, value2, "picId");
      return this;
    }
    
    public Criteria andPicIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("pic_id not between", value1, value2, "picId");
      return this;
    }
    
    public Criteria andPicNameIsNull()
    {
      addCriterion("pic_name is null");
      return this;
    }
    
    public Criteria andPicNameIsNotNull()
    {
      addCriterion("pic_name is not null");
      return this;
    }
    
    public Criteria andPicNameEqualTo(String value)
    {
      addCriterion("pic_name =", value, "picName");
      return this;
    }
    
    public Criteria andPicNameNotEqualTo(String value)
    {
      addCriterion("pic_name <>", value, "picName");
      return this;
    }
    
    public Criteria andPicNameGreaterThan(String value)
    {
      addCriterion("pic_name >", value, "picName");
      return this;
    }
    
    public Criteria andPicNameGreaterThanOrEqualTo(String value)
    {
      addCriterion("pic_name >=", value, "picName");
      return this;
    }
    
    public Criteria andPicNameLessThan(String value)
    {
      addCriterion("pic_name <", value, "picName");
      return this;
    }
    
    public Criteria andPicNameLessThanOrEqualTo(String value)
    {
      addCriterion("pic_name <=", value, "picName");
      return this;
    }
    
    public Criteria andPicNameLike(String value)
    {
      addCriterion("pic_name like", value, "picName");
      return this;
    }
    
    public Criteria andPicNameNotLike(String value)
    {
      addCriterion("pic_name not like", value, "picName");
      return this;
    }
    
    public Criteria andPicNameIn(List values)
    {
      addCriterion("pic_name in", values, "picName");
      return this;
    }
    
    public Criteria andPicNameNotIn(List values)
    {
      addCriterion("pic_name not in", values, "picName");
      return this;
    }
    
    public Criteria andPicNameBetween(String value1, String value2)
    {
      addCriterion("pic_name between", value1, value2, "picName");
      return this;
    }
    
    public Criteria andPicNameNotBetween(String value1, String value2)
    {
      addCriterion("pic_name not between", value1, value2, "picName");
      return this;
    }
    
    public Criteria andPicSizeIsNull()
    {
      addCriterion("pic_size is null");
      return this;
    }
    
    public Criteria andPicSizeIsNotNull()
    {
      addCriterion("pic_size is not null");
      return this;
    }
    
    public Criteria andPicSizeEqualTo(Integer value)
    {
      addCriterion("pic_size =", value, "picSize");
      return this;
    }
    
    public Criteria andPicSizeNotEqualTo(Integer value)
    {
      addCriterion("pic_size <>", value, "picSize");
      return this;
    }
    
    public Criteria andPicSizeGreaterThan(Integer value)
    {
      addCriterion("pic_size >", value, "picSize");
      return this;
    }
    
    public Criteria andPicSizeGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("pic_size >=", value, "picSize");
      return this;
    }
    
    public Criteria andPicSizeLessThan(Integer value)
    {
      addCriterion("pic_size <", value, "picSize");
      return this;
    }
    
    public Criteria andPicSizeLessThanOrEqualTo(Integer value)
    {
      addCriterion("pic_size <=", value, "picSize");
      return this;
    }
    
    public Criteria andPicSizeIn(List values)
    {
      addCriterion("pic_size in", values, "picSize");
      return this;
    }
    
    public Criteria andPicSizeNotIn(List values)
    {
      addCriterion("pic_size not in", values, "picSize");
      return this;
    }
    
    public Criteria andPicSizeBetween(Integer value1, Integer value2)
    {
      addCriterion("pic_size between", value1, value2, "picSize");
      return this;
    }
    
    public Criteria andPicSizeNotBetween(Integer value1, Integer value2)
    {
      addCriterion("pic_size not between", value1, value2, "picSize");
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
      addCriterion("create_time not between", value1, value2, 
        "createTime");
      return this;
    }
    
    public Criteria andPicUrlIsNull()
    {
      addCriterion("pic_url is null");
      return this;
    }
    
    public Criteria andPicUrlIsNotNull()
    {
      addCriterion("pic_url is not null");
      return this;
    }
    
    public Criteria andPicUrlEqualTo(String value)
    {
      addCriterion("pic_url =", value, "picUrl");
      return this;
    }
    
    public Criteria andPicUrlNotEqualTo(String value)
    {
      addCriterion("pic_url <>", value, "picUrl");
      return this;
    }
    
    public Criteria andPicUrlGreaterThan(String value)
    {
      addCriterion("pic_url >", value, "picUrl");
      return this;
    }
    
    public Criteria andPicUrlGreaterThanOrEqualTo(String value)
    {
      addCriterion("pic_url >=", value, "picUrl");
      return this;
    }
    
    public Criteria andPicUrlLessThan(String value)
    {
      addCriterion("pic_url <", value, "picUrl");
      return this;
    }
    
    public Criteria andPicUrlLessThanOrEqualTo(String value)
    {
      addCriterion("pic_url <=", value, "picUrl");
      return this;
    }
    
    public Criteria andPicUrlLike(String value)
    {
      addCriterion("pic_url like", value, "picUrl");
      return this;
    }
    
    public Criteria andPicUrlNotLike(String value)
    {
      addCriterion("pic_url not like", value, "picUrl");
      return this;
    }
    
    public Criteria andPicUrlIn(List values)
    {
      addCriterion("pic_url in", values, "picUrl");
      return this;
    }
    
    public Criteria andPicUrlNotIn(List values)
    {
      addCriterion("pic_url not in", values, "picUrl");
      return this;
    }
    
    public Criteria andPicUrlBetween(String value1, String value2)
    {
      addCriterion("pic_url between", value1, value2, "picUrl");
      return this;
    }
    
    public Criteria andPicUrlNotBetween(String value1, String value2)
    {
      addCriterion("pic_url not between", value1, value2, "picUrl");
      return this;
    }
    
    public Criteria andPreviewUrlIsNull()
    {
      addCriterion("preview_url is null");
      return this;
    }
    
    public Criteria andPreviewUrlIsNotNull()
    {
      addCriterion("preview_url is not null");
      return this;
    }
    
    public Criteria andPreviewUrlEqualTo(String value)
    {
      addCriterion("preview_url =", value, "previewUrl");
      return this;
    }
    
    public Criteria andPreviewUrlNotEqualTo(String value)
    {
      addCriterion("preview_url <>", value, "previewUrl");
      return this;
    }
    
    public Criteria andPreviewUrlGreaterThan(String value)
    {
      addCriterion("preview_url >", value, "previewUrl");
      return this;
    }
    
    public Criteria andPreviewUrlGreaterThanOrEqualTo(String value)
    {
      addCriterion("preview_url >=", value, "previewUrl");
      return this;
    }
    
    public Criteria andPreviewUrlLessThan(String value)
    {
      addCriterion("preview_url <", value, "previewUrl");
      return this;
    }
    
    public Criteria andPreviewUrlLessThanOrEqualTo(String value)
    {
      addCriterion("preview_url <=", value, "previewUrl");
      return this;
    }
    
    public Criteria andPreviewUrlLike(String value)
    {
      addCriterion("preview_url like", value, "previewUrl");
      return this;
    }
    
    public Criteria andPreviewUrlNotLike(String value)
    {
      addCriterion("preview_url not like", value, "previewUrl");
      return this;
    }
    
    public Criteria andPreviewUrlIn(List values)
    {
      addCriterion("preview_url in", values, "previewUrl");
      return this;
    }
    
    public Criteria andPreviewUrlNotIn(List values)
    {
      addCriterion("preview_url not in", values, "previewUrl");
      return this;
    }
    
    public Criteria andPreviewUrlBetween(String value1, String value2)
    {
      addCriterion("preview_url between", value1, value2, "previewUrl");
      return this;
    }
    
    public Criteria andPreviewUrlNotBetween(String value1, String value2)
    {
      addCriterion("preview_url not between", value1, value2, 
        "previewUrl");
      return this;
    }
    
    public Criteria andRefCountIsNull()
    {
      addCriterion("ref_count is null");
      return this;
    }
    
    public Criteria andRefCountIsNotNull()
    {
      addCriterion("ref_count is not null");
      return this;
    }
    
    public Criteria andRefCountEqualTo(Integer value)
    {
      addCriterion("ref_count =", value, "refCount");
      return this;
    }
    
    public Criteria andRefCountNotEqualTo(Integer value)
    {
      addCriterion("ref_count <>", value, "refCount");
      return this;
    }
    
    public Criteria andRefCountGreaterThan(Integer value)
    {
      addCriterion("ref_count >", value, "refCount");
      return this;
    }
    
    public Criteria andRefCountGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("ref_count >=", value, "refCount");
      return this;
    }
    
    public Criteria andRefCountLessThan(Integer value)
    {
      addCriterion("ref_count <", value, "refCount");
      return this;
    }
    
    public Criteria andRefCountLessThanOrEqualTo(Integer value)
    {
      addCriterion("ref_count <=", value, "refCount");
      return this;
    }
    
    public Criteria andRefCountIn(List values)
    {
      addCriterion("ref_count in", values, "refCount");
      return this;
    }
    
    public Criteria andRefCountNotIn(List values)
    {
      addCriterion("ref_count not in", values, "refCount");
      return this;
    }
    
    public Criteria andRefCountBetween(Integer value1, Integer value2)
    {
      addCriterion("ref_count between", value1, value2, "refCount");
      return this;
    }
    
    public Criteria andRefCountNotBetween(Integer value1, Integer value2)
    {
      addCriterion("ref_count not between", value1, value2, "refCount");
      return this;
    }
  }
}
