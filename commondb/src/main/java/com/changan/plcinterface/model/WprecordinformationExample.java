package com.changan.plcinterface.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WprecordinformationExample {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table wprecordinformation
     *
     * @abatorgenerated Sun Mar 27 10:49:37 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table wprecordinformation
     *
     * @abatorgenerated Sun Mar 27 10:49:37 CST 2016
     */
    protected List oredCriteria;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table wprecordinformation
     *
     * @abatorgenerated Sun Mar 27 10:49:37 CST 2016
     */
    public WprecordinformationExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table wprecordinformation
     *
     * @abatorgenerated Sun Mar 27 10:49:37 CST 2016
     */
    protected WprecordinformationExample(WprecordinformationExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table wprecordinformation
     *
     * @abatorgenerated Sun Mar 27 10:49:37 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table wprecordinformation
     *
     * @abatorgenerated Sun Mar 27 10:49:37 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table wprecordinformation
     *
     * @abatorgenerated Sun Mar 27 10:49:37 CST 2016
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table wprecordinformation
     *
     * @abatorgenerated Sun Mar 27 10:49:37 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table wprecordinformation
     *
     * @abatorgenerated Sun Mar 27 10:49:37 CST 2016
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table wprecordinformation
     *
     * @abatorgenerated Sun Mar 27 10:49:37 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table wprecordinformation
     *
     * @abatorgenerated Sun Mar 27 10:49:37 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table wprecordinformation
     *
     * @abatorgenerated Sun Mar 27 10:49:37 CST 2016
     */
    public static class Criteria {
        protected List criteriaWithoutValue;

        protected List criteriaWithSingleValue;

        protected List criteriaWithListValue;

        protected List criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList();
            criteriaWithSingleValue = new ArrayList();
            criteriaWithListValue = new ArrayList();
            criteriaWithBetweenValue = new ArrayList();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List list = new ArrayList();
            list.add(value1);
            list.add(value2);
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andWpdatanameIsNull() {
            addCriterion("Wpdataname is null");
            return this;
        }

        public Criteria andWpdatanameIsNotNull() {
            addCriterion("Wpdataname is not null");
            return this;
        }

        public Criteria andWpdatanameEqualTo(String value) {
            addCriterion("Wpdataname =", value, "wpdataname");
            return this;
        }

        public Criteria andWpdatanameNotEqualTo(String value) {
            addCriterion("Wpdataname <>", value, "wpdataname");
            return this;
        }

        public Criteria andWpdatanameGreaterThan(String value) {
            addCriterion("Wpdataname >", value, "wpdataname");
            return this;
        }

        public Criteria andWpdatanameGreaterThanOrEqualTo(String value) {
            addCriterion("Wpdataname >=", value, "wpdataname");
            return this;
        }

        public Criteria andWpdatanameLessThan(String value) {
            addCriterion("Wpdataname <", value, "wpdataname");
            return this;
        }

        public Criteria andWpdatanameLessThanOrEqualTo(String value) {
            addCriterion("Wpdataname <=", value, "wpdataname");
            return this;
        }

        public Criteria andWpdatanameLike(String value) {
            addCriterion("Wpdataname like", value, "wpdataname");
            return this;
        }

        public Criteria andWpdatanameNotLike(String value) {
            addCriterion("Wpdataname not like", value, "wpdataname");
            return this;
        }

        public Criteria andWpdatanameIn(List values) {
            addCriterion("Wpdataname in", values, "wpdataname");
            return this;
        }

        public Criteria andWpdatanameNotIn(List values) {
            addCriterion("Wpdataname not in", values, "wpdataname");
            return this;
        }

        public Criteria andWpdatanameBetween(String value1, String value2) {
            addCriterion("Wpdataname between", value1, value2, "wpdataname");
            return this;
        }

        public Criteria andWpdatanameNotBetween(String value1, String value2) {
            addCriterion("Wpdataname not between", value1, value2, "wpdataname");
            return this;
        }

        public Criteria andWprecordcodeIsNull() {
            addCriterion("Wprecordcode is null");
            return this;
        }

        public Criteria andWprecordcodeIsNotNull() {
            addCriterion("Wprecordcode is not null");
            return this;
        }

        public Criteria andWprecordcodeEqualTo(Integer value) {
            addCriterion("Wprecordcode =", value, "wprecordcode");
            return this;
        }

        public Criteria andWprecordcodeNotEqualTo(Integer value) {
            addCriterion("Wprecordcode <>", value, "wprecordcode");
            return this;
        }

        public Criteria andWprecordcodeGreaterThan(Integer value) {
            addCriterion("Wprecordcode >", value, "wprecordcode");
            return this;
        }

        public Criteria andWprecordcodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("Wprecordcode >=", value, "wprecordcode");
            return this;
        }

        public Criteria andWprecordcodeLessThan(Integer value) {
            addCriterion("Wprecordcode <", value, "wprecordcode");
            return this;
        }

        public Criteria andWprecordcodeLessThanOrEqualTo(Integer value) {
            addCriterion("Wprecordcode <=", value, "wprecordcode");
            return this;
        }

        public Criteria andWprecordcodeIn(List values) {
            addCriterion("Wprecordcode in", values, "wprecordcode");
            return this;
        }

        public Criteria andWprecordcodeNotIn(List values) {
            addCriterion("Wprecordcode not in", values, "wprecordcode");
            return this;
        }

        public Criteria andWprecordcodeBetween(Integer value1, Integer value2) {
            addCriterion("Wprecordcode between", value1, value2, "wprecordcode");
            return this;
        }

        public Criteria andWprecordcodeNotBetween(Integer value1, Integer value2) {
            addCriterion("Wprecordcode not between", value1, value2, "wprecordcode");
            return this;
        }

        public Criteria andDatatimeIsNull() {
            addCriterion("Datatime is null");
            return this;
        }

        public Criteria andDatatimeIsNotNull() {
            addCriterion("Datatime is not null");
            return this;
        }

        public Criteria andDatatimeEqualTo(Date value) {
            addCriterion("Datatime =", value, "datatime");
            return this;
        }

        public Criteria andDatatimeNotEqualTo(Date value) {
            addCriterion("Datatime <>", value, "datatime");
            return this;
        }

        public Criteria andDatatimeGreaterThan(Date value) {
            addCriterion("Datatime >", value, "datatime");
            return this;
        }

        public Criteria andDatatimeGreaterThanOrEqualTo(Date value) {
            addCriterion("Datatime >=", value, "datatime");
            return this;
        }

        public Criteria andDatatimeLessThan(Date value) {
            addCriterion("Datatime <", value, "datatime");
            return this;
        }

        public Criteria andDatatimeLessThanOrEqualTo(Date value) {
            addCriterion("Datatime <=", value, "datatime");
            return this;
        }

        public Criteria andDatatimeIn(List values) {
            addCriterion("Datatime in", values, "datatime");
            return this;
        }

        public Criteria andDatatimeNotIn(List values) {
            addCriterion("Datatime not in", values, "datatime");
            return this;
        }

        public Criteria andDatatimeBetween(Date value1, Date value2) {
            addCriterion("Datatime between", value1, value2, "datatime");
            return this;
        }

        public Criteria andDatatimeNotBetween(Date value1, Date value2) {
            addCriterion("Datatime not between", value1, value2, "datatime");
            return this;
        }

        public Criteria andRecordstatusIsNull() {
            addCriterion("Recordstatus is null");
            return this;
        }

        public Criteria andRecordstatusIsNotNull() {
            addCriterion("Recordstatus is not null");
            return this;
        }

        public Criteria andRecordstatusEqualTo(Boolean value) {
            addCriterion("Recordstatus =", value, "recordstatus");
            return this;
        }

        public Criteria andRecordstatusNotEqualTo(Boolean value) {
            addCriterion("Recordstatus <>", value, "recordstatus");
            return this;
        }

        public Criteria andRecordstatusGreaterThan(Boolean value) {
            addCriterion("Recordstatus >", value, "recordstatus");
            return this;
        }

        public Criteria andRecordstatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("Recordstatus >=", value, "recordstatus");
            return this;
        }

        public Criteria andRecordstatusLessThan(Boolean value) {
            addCriterion("Recordstatus <", value, "recordstatus");
            return this;
        }

        public Criteria andRecordstatusLessThanOrEqualTo(Boolean value) {
            addCriterion("Recordstatus <=", value, "recordstatus");
            return this;
        }

        public Criteria andRecordstatusIn(List values) {
            addCriterion("Recordstatus in", values, "recordstatus");
            return this;
        }

        public Criteria andRecordstatusNotIn(List values) {
            addCriterion("Recordstatus not in", values, "recordstatus");
            return this;
        }

        public Criteria andRecordstatusBetween(Boolean value1, Boolean value2) {
            addCriterion("Recordstatus between", value1, value2, "recordstatus");
            return this;
        }

        public Criteria andRecordstatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("Recordstatus not between", value1, value2, "recordstatus");
            return this;
        }
    }
}