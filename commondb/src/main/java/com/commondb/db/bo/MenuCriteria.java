package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuCriteria
{
  protected String orderByClause;
  protected List oredCriteria;
  
  public MenuCriteria()
  {
    this.oredCriteria = new ArrayList();
  }
  
  protected MenuCriteria(MenuCriteria example)
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
    
    public Criteria andMenuIdIsNull()
    {
      addCriterion("MENU_ID is null");
      return this;
    }
    
    public Criteria andMenuIdIsNotNull()
    {
      addCriterion("MENU_ID is not null");
      return this;
    }
    
    public Criteria andMenuIdEqualTo(Integer value)
    {
      addCriterion("MENU_ID =", value, "menuId");
      return this;
    }
    
    public Criteria andMenuIdNotEqualTo(Integer value)
    {
      addCriterion("MENU_ID <>", value, "menuId");
      return this;
    }
    
    public Criteria andMenuIdGreaterThan(Integer value)
    {
      addCriterion("MENU_ID >", value, "menuId");
      return this;
    }
    
    public Criteria andMenuIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("MENU_ID >=", value, "menuId");
      return this;
    }
    
    public Criteria andMenuIdLessThan(Integer value)
    {
      addCriterion("MENU_ID <", value, "menuId");
      return this;
    }
    
    public Criteria andMenuIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("MENU_ID <=", value, "menuId");
      return this;
    }
    
    public Criteria andMenuIdIn(List values)
    {
      addCriterion("MENU_ID in", values, "menuId");
      return this;
    }
    
    public Criteria andMenuIdNotIn(List values)
    {
      addCriterion("MENU_ID not in", values, "menuId");
      return this;
    }
    
    public Criteria andMenuIdBetween(Integer value1, Integer value2)
    {
      addCriterion("MENU_ID between", value1, value2, "menuId");
      return this;
    }
    
    public Criteria andMenuIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("MENU_ID not between", value1, value2, "menuId");
      return this;
    }
    
    public Criteria andDescnIsNull()
    {
      addCriterion("DESCN is null");
      return this;
    }
    
    public Criteria andDescnIsNotNull()
    {
      addCriterion("DESCN is not null");
      return this;
    }
    
    public Criteria andDescnEqualTo(String value)
    {
      addCriterion("DESCN =", value, "descn");
      return this;
    }
    
    public Criteria andDescnNotEqualTo(String value)
    {
      addCriterion("DESCN <>", value, "descn");
      return this;
    }
    
    public Criteria andDescnGreaterThan(String value)
    {
      addCriterion("DESCN >", value, "descn");
      return this;
    }
    
    public Criteria andDescnGreaterThanOrEqualTo(String value)
    {
      addCriterion("DESCN >=", value, "descn");
      return this;
    }
    
    public Criteria andDescnLessThan(String value)
    {
      addCriterion("DESCN <", value, "descn");
      return this;
    }
    
    public Criteria andDescnLessThanOrEqualTo(String value)
    {
      addCriterion("DESCN <=", value, "descn");
      return this;
    }
    
    public Criteria andDescnLike(String value)
    {
      addCriterion("DESCN like", value, "descn");
      return this;
    }
    
    public Criteria andDescnNotLike(String value)
    {
      addCriterion("DESCN not like", value, "descn");
      return this;
    }
    
    public Criteria andDescnIn(List values)
    {
      addCriterion("DESCN in", values, "descn");
      return this;
    }
    
    public Criteria andDescnNotIn(List values)
    {
      addCriterion("DESCN not in", values, "descn");
      return this;
    }
    
    public Criteria andDescnBetween(String value1, String value2)
    {
      addCriterion("DESCN between", value1, value2, "descn");
      return this;
    }
    
    public Criteria andDescnNotBetween(String value1, String value2)
    {
      addCriterion("DESCN not between", value1, value2, "descn");
      return this;
    }
    
    public Criteria andIconClsIsNull()
    {
      addCriterion("ICON_CLS is null");
      return this;
    }
    
    public Criteria andIconClsIsNotNull()
    {
      addCriterion("ICON_CLS is not null");
      return this;
    }
    
    public Criteria andIconClsEqualTo(String value)
    {
      addCriterion("ICON_CLS =", value, "iconCls");
      return this;
    }
    
    public Criteria andIconClsNotEqualTo(String value)
    {
      addCriterion("ICON_CLS <>", value, "iconCls");
      return this;
    }
    
    public Criteria andIconClsGreaterThan(String value)
    {
      addCriterion("ICON_CLS >", value, "iconCls");
      return this;
    }
    
    public Criteria andIconClsGreaterThanOrEqualTo(String value)
    {
      addCriterion("ICON_CLS >=", value, "iconCls");
      return this;
    }
    
    public Criteria andIconClsLessThan(String value)
    {
      addCriterion("ICON_CLS <", value, "iconCls");
      return this;
    }
    
    public Criteria andIconClsLessThanOrEqualTo(String value)
    {
      addCriterion("ICON_CLS <=", value, "iconCls");
      return this;
    }
    
    public Criteria andIconClsLike(String value)
    {
      addCriterion("ICON_CLS like", value, "iconCls");
      return this;
    }
    
    public Criteria andIconClsNotLike(String value)
    {
      addCriterion("ICON_CLS not like", value, "iconCls");
      return this;
    }
    
    public Criteria andIconClsIn(List values)
    {
      addCriterion("ICON_CLS in", values, "iconCls");
      return this;
    }
    
    public Criteria andIconClsNotIn(List values)
    {
      addCriterion("ICON_CLS not in", values, "iconCls");
      return this;
    }
    
    public Criteria andIconClsBetween(String value1, String value2)
    {
      addCriterion("ICON_CLS between", value1, value2, "iconCls");
      return this;
    }
    
    public Criteria andIconClsNotBetween(String value1, String value2)
    {
      addCriterion("ICON_CLS not between", value1, value2, "iconCls");
      return this;
    }
    
    public Criteria andNameIsNull()
    {
      addCriterion("NAME is null");
      return this;
    }
    
    public Criteria andNameIsNotNull()
    {
      addCriterion("NAME is not null");
      return this;
    }
    
    public Criteria andNameEqualTo(String value)
    {
      addCriterion("NAME =", value, "name");
      return this;
    }
    
    public Criteria andNameNotEqualTo(String value)
    {
      addCriterion("NAME <>", value, "name");
      return this;
    }
    
    public Criteria andNameGreaterThan(String value)
    {
      addCriterion("NAME >", value, "name");
      return this;
    }
    
    public Criteria andNameGreaterThanOrEqualTo(String value)
    {
      addCriterion("NAME >=", value, "name");
      return this;
    }
    
    public Criteria andNameLessThan(String value)
    {
      addCriterion("NAME <", value, "name");
      return this;
    }
    
    public Criteria andNameLessThanOrEqualTo(String value)
    {
      addCriterion("NAME <=", value, "name");
      return this;
    }
    
    public Criteria andNameLike(String value)
    {
      addCriterion("NAME like", value, "name");
      return this;
    }
    
    public Criteria andNameNotLike(String value)
    {
      addCriterion("NAME not like", value, "name");
      return this;
    }
    
    public Criteria andNameIn(List values)
    {
      addCriterion("NAME in", values, "name");
      return this;
    }
    
    public Criteria andNameNotIn(List values)
    {
      addCriterion("NAME not in", values, "name");
      return this;
    }
    
    public Criteria andNameBetween(String value1, String value2)
    {
      addCriterion("NAME between", value1, value2, "name");
      return this;
    }
    
    public Criteria andNameNotBetween(String value1, String value2)
    {
      addCriterion("NAME not between", value1, value2, "name");
      return this;
    }
    
    public Criteria andQtipIsNull()
    {
      addCriterion("QTIP is null");
      return this;
    }
    
    public Criteria andQtipIsNotNull()
    {
      addCriterion("QTIP is not null");
      return this;
    }
    
    public Criteria andQtipEqualTo(String value)
    {
      addCriterion("QTIP =", value, "qtip");
      return this;
    }
    
    public Criteria andQtipNotEqualTo(String value)
    {
      addCriterion("QTIP <>", value, "qtip");
      return this;
    }
    
    public Criteria andQtipGreaterThan(String value)
    {
      addCriterion("QTIP >", value, "qtip");
      return this;
    }
    
    public Criteria andQtipGreaterThanOrEqualTo(String value)
    {
      addCriterion("QTIP >=", value, "qtip");
      return this;
    }
    
    public Criteria andQtipLessThan(String value)
    {
      addCriterion("QTIP <", value, "qtip");
      return this;
    }
    
    public Criteria andQtipLessThanOrEqualTo(String value)
    {
      addCriterion("QTIP <=", value, "qtip");
      return this;
    }
    
    public Criteria andQtipLike(String value)
    {
      addCriterion("QTIP like", value, "qtip");
      return this;
    }
    
    public Criteria andQtipNotLike(String value)
    {
      addCriterion("QTIP not like", value, "qtip");
      return this;
    }
    
    public Criteria andQtipIn(List values)
    {
      addCriterion("QTIP in", values, "qtip");
      return this;
    }
    
    public Criteria andQtipNotIn(List values)
    {
      addCriterion("QTIP not in", values, "qtip");
      return this;
    }
    
    public Criteria andQtipBetween(String value1, String value2)
    {
      addCriterion("QTIP between", value1, value2, "qtip");
      return this;
    }
    
    public Criteria andQtipNotBetween(String value1, String value2)
    {
      addCriterion("QTIP not between", value1, value2, "qtip");
      return this;
    }
    
    public Criteria andTheSortIsNull()
    {
      addCriterion("THE_SORT is null");
      return this;
    }
    
    public Criteria andTheSortIsNotNull()
    {
      addCriterion("THE_SORT is not null");
      return this;
    }
    
    public Criteria andTheSortEqualTo(Integer value)
    {
      addCriterion("THE_SORT =", value, "theSort");
      return this;
    }
    
    public Criteria andTheSortNotEqualTo(Integer value)
    {
      addCriterion("THE_SORT <>", value, "theSort");
      return this;
    }
    
    public Criteria andTheSortGreaterThan(Integer value)
    {
      addCriterion("THE_SORT >", value, "theSort");
      return this;
    }
    
    public Criteria andTheSortGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("THE_SORT >=", value, "theSort");
      return this;
    }
    
    public Criteria andTheSortLessThan(Integer value)
    {
      addCriterion("THE_SORT <", value, "theSort");
      return this;
    }
    
    public Criteria andTheSortLessThanOrEqualTo(Integer value)
    {
      addCriterion("THE_SORT <=", value, "theSort");
      return this;
    }
    
    public Criteria andTheSortIn(List values)
    {
      addCriterion("THE_SORT in", values, "theSort");
      return this;
    }
    
    public Criteria andTheSortNotIn(List values)
    {
      addCriterion("THE_SORT not in", values, "theSort");
      return this;
    }
    
    public Criteria andTheSortBetween(Integer value1, Integer value2)
    {
      addCriterion("THE_SORT between", value1, value2, "theSort");
      return this;
    }
    
    public Criteria andTheSortNotBetween(Integer value1, Integer value2)
    {
      addCriterion("THE_SORT not between", value1, value2, "theSort");
      return this;
    }
    
    public Criteria andUrlIsNull()
    {
      addCriterion("URL is null");
      return this;
    }
    
    public Criteria andUrlIsNotNull()
    {
      addCriterion("URL is not null");
      return this;
    }
    
    public Criteria andUrlEqualTo(String value)
    {
      addCriterion("URL =", value, "url");
      return this;
    }
    
    public Criteria andUrlNotEqualTo(String value)
    {
      addCriterion("URL <>", value, "url");
      return this;
    }
    
    public Criteria andUrlGreaterThan(String value)
    {
      addCriterion("URL >", value, "url");
      return this;
    }
    
    public Criteria andUrlGreaterThanOrEqualTo(String value)
    {
      addCriterion("URL >=", value, "url");
      return this;
    }
    
    public Criteria andUrlLessThan(String value)
    {
      addCriterion("URL <", value, "url");
      return this;
    }
    
    public Criteria andUrlLessThanOrEqualTo(String value)
    {
      addCriterion("URL <=", value, "url");
      return this;
    }
    
    public Criteria andUrlLike(String value)
    {
      addCriterion("URL like", value, "url");
      return this;
    }
    
    public Criteria andUrlNotLike(String value)
    {
      addCriterion("URL not like", value, "url");
      return this;
    }
    
    public Criteria andUrlIn(List values)
    {
      addCriterion("URL in", values, "url");
      return this;
    }
    
    public Criteria andUrlNotIn(List values)
    {
      addCriterion("URL not in", values, "url");
      return this;
    }
    
    public Criteria andUrlBetween(String value1, String value2)
    {
      addCriterion("URL between", value1, value2, "url");
      return this;
    }
    
    public Criteria andUrlNotBetween(String value1, String value2)
    {
      addCriterion("URL not between", value1, value2, "url");
      return this;
    }
    
    public Criteria andParentIdIsNull()
    {
      addCriterion("PARENT_ID is null");
      return this;
    }
    
    public Criteria andParentIdIsNotNull()
    {
      addCriterion("PARENT_ID is not null");
      return this;
    }
    
    public Criteria andParentIdEqualTo(Integer value)
    {
      addCriterion("PARENT_ID =", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdNotEqualTo(Integer value)
    {
      addCriterion("PARENT_ID <>", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdGreaterThan(Integer value)
    {
      addCriterion("PARENT_ID >", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("PARENT_ID >=", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdLessThan(Integer value)
    {
      addCriterion("PARENT_ID <", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("PARENT_ID <=", value, "parentId");
      return this;
    }
    
    public Criteria andParentIdIn(List values)
    {
      addCriterion("PARENT_ID in", values, "parentId");
      return this;
    }
    
    public Criteria andParentIdNotIn(List values)
    {
      addCriterion("PARENT_ID not in", values, "parentId");
      return this;
    }
    
    public Criteria andParentIdBetween(Integer value1, Integer value2)
    {
      addCriterion("PARENT_ID between", value1, value2, "parentId");
      return this;
    }
    
    public Criteria andParentIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("PARENT_ID not between", value1, value2, "parentId");
      return this;
    }
  }
}
