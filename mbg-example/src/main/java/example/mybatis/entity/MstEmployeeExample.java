package example.mybatis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MstEmployeeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    public MstEmployeeExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCidIsNull() {
            addCriterion("cid is null");
            return (Criteria) this;
        }

        public Criteria andCidIsNotNull() {
            addCriterion("cid is not null");
            return (Criteria) this;
        }

        public Criteria andCidEqualTo(String value) {
            addCriterion("cid =", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotEqualTo(String value) {
            addCriterion("cid <>", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThan(String value) {
            addCriterion("cid >", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThanOrEqualTo(String value) {
            addCriterion("cid >=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThan(String value) {
            addCriterion("cid <", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThanOrEqualTo(String value) {
            addCriterion("cid <=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLike(String value) {
            addCriterion("cid like", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotLike(String value) {
            addCriterion("cid not like", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidIn(List<String> values) {
            addCriterion("cid in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotIn(List<String> values) {
            addCriterion("cid not in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidBetween(String value1, String value2) {
            addCriterion("cid between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotBetween(String value1, String value2) {
            addCriterion("cid not between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andEidIsNull() {
            addCriterion("eid is null");
            return (Criteria) this;
        }

        public Criteria andEidIsNotNull() {
            addCriterion("eid is not null");
            return (Criteria) this;
        }

        public Criteria andEidEqualTo(String value) {
            addCriterion("eid =", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotEqualTo(String value) {
            addCriterion("eid <>", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThan(String value) {
            addCriterion("eid >", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThanOrEqualTo(String value) {
            addCriterion("eid >=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThan(String value) {
            addCriterion("eid <", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThanOrEqualTo(String value) {
            addCriterion("eid <=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLike(String value) {
            addCriterion("eid like", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotLike(String value) {
            addCriterion("eid not like", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidIn(List<String> values) {
            addCriterion("eid in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotIn(List<String> values) {
            addCriterion("eid not in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidBetween(String value1, String value2) {
            addCriterion("eid between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotBetween(String value1, String value2) {
            addCriterion("eid not between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAddrIsNull() {
            addCriterion("addr is null");
            return (Criteria) this;
        }

        public Criteria andAddrIsNotNull() {
            addCriterion("addr is not null");
            return (Criteria) this;
        }

        public Criteria andAddrEqualTo(String value) {
            addCriterion("addr =", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrNotEqualTo(String value) {
            addCriterion("addr <>", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrGreaterThan(String value) {
            addCriterion("addr >", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrGreaterThanOrEqualTo(String value) {
            addCriterion("addr >=", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrLessThan(String value) {
            addCriterion("addr <", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrLessThanOrEqualTo(String value) {
            addCriterion("addr <=", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrLike(String value) {
            addCriterion("addr like", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrNotLike(String value) {
            addCriterion("addr not like", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrIn(List<String> values) {
            addCriterion("addr in", values, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrNotIn(List<String> values) {
            addCriterion("addr not in", values, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrBetween(String value1, String value2) {
            addCriterion("addr between", value1, value2, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrNotBetween(String value1, String value2) {
            addCriterion("addr not between", value1, value2, "addr");
            return (Criteria) this;
        }

        public Criteria andAgeIsNull() {
            addCriterion("age is null");
            return (Criteria) this;
        }

        public Criteria andAgeIsNotNull() {
            addCriterion("age is not null");
            return (Criteria) this;
        }

        public Criteria andAgeEqualTo(Integer value) {
            addCriterion("age =", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotEqualTo(Integer value) {
            addCriterion("age <>", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThan(Integer value) {
            addCriterion("age >", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("age >=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThan(Integer value) {
            addCriterion("age <", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThanOrEqualTo(Integer value) {
            addCriterion("age <=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeIn(List<Integer> values) {
            addCriterion("age in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotIn(List<Integer> values) {
            addCriterion("age not in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeBetween(Integer value1, Integer value2) {
            addCriterion("age between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("age not between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampIsNull() {
            addCriterion("created_timestamp is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampIsNotNull() {
            addCriterion("created_timestamp is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampEqualTo(Date value) {
            addCriterion("created_timestamp =", value, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampNotEqualTo(Date value) {
            addCriterion("created_timestamp <>", value, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampGreaterThan(Date value) {
            addCriterion("created_timestamp >", value, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("created_timestamp >=", value, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampLessThan(Date value) {
            addCriterion("created_timestamp <", value, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampLessThanOrEqualTo(Date value) {
            addCriterion("created_timestamp <=", value, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampIn(List<Date> values) {
            addCriterion("created_timestamp in", values, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampNotIn(List<Date> values) {
            addCriterion("created_timestamp not in", values, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampBetween(Date value1, Date value2) {
            addCriterion("created_timestamp between", value1, value2, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedTimestampNotBetween(Date value1, Date value2) {
            addCriterion("created_timestamp not between", value1, value2, "createdTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridIsNull() {
            addCriterion("created_userid is null");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridIsNotNull() {
            addCriterion("created_userid is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridEqualTo(String value) {
            addCriterion("created_userid =", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridNotEqualTo(String value) {
            addCriterion("created_userid <>", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridGreaterThan(String value) {
            addCriterion("created_userid >", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridGreaterThanOrEqualTo(String value) {
            addCriterion("created_userid >=", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridLessThan(String value) {
            addCriterion("created_userid <", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridLessThanOrEqualTo(String value) {
            addCriterion("created_userid <=", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridLike(String value) {
            addCriterion("created_userid like", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridNotLike(String value) {
            addCriterion("created_userid not like", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridIn(List<String> values) {
            addCriterion("created_userid in", values, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridNotIn(List<String> values) {
            addCriterion("created_userid not in", values, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridBetween(String value1, String value2) {
            addCriterion("created_userid between", value1, value2, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridNotBetween(String value1, String value2) {
            addCriterion("created_userid not between", value1, value2, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimestampIsNull() {
            addCriterion("updated_timestamp is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimestampIsNotNull() {
            addCriterion("updated_timestamp is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimestampEqualTo(Date value) {
            addCriterion("updated_timestamp =", value, "updatedTimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimestampNotEqualTo(Date value) {
            addCriterion("updated_timestamp <>", value, "updatedTimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimestampGreaterThan(Date value) {
            addCriterion("updated_timestamp >", value, "updatedTimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_timestamp >=", value, "updatedTimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimestampLessThan(Date value) {
            addCriterion("updated_timestamp <", value, "updatedTimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimestampLessThanOrEqualTo(Date value) {
            addCriterion("updated_timestamp <=", value, "updatedTimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimestampIn(List<Date> values) {
            addCriterion("updated_timestamp in", values, "updatedTimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimestampNotIn(List<Date> values) {
            addCriterion("updated_timestamp not in", values, "updatedTimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimestampBetween(Date value1, Date value2) {
            addCriterion("updated_timestamp between", value1, value2, "updatedTimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimestampNotBetween(Date value1, Date value2) {
            addCriterion("updated_timestamp not between", value1, value2, "updatedTimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridIsNull() {
            addCriterion("updated_userid is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridIsNotNull() {
            addCriterion("updated_userid is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridEqualTo(String value) {
            addCriterion("updated_userid =", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridNotEqualTo(String value) {
            addCriterion("updated_userid <>", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridGreaterThan(String value) {
            addCriterion("updated_userid >", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridGreaterThanOrEqualTo(String value) {
            addCriterion("updated_userid >=", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridLessThan(String value) {
            addCriterion("updated_userid <", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridLessThanOrEqualTo(String value) {
            addCriterion("updated_userid <=", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridLike(String value) {
            addCriterion("updated_userid like", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridNotLike(String value) {
            addCriterion("updated_userid not like", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridIn(List<String> values) {
            addCriterion("updated_userid in", values, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridNotIn(List<String> values) {
            addCriterion("updated_userid not in", values, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridBetween(String value1, String value2) {
            addCriterion("updated_userid between", value1, value2, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridNotBetween(String value1, String value2) {
            addCriterion("updated_userid not between", value1, value2, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2IsNull() {
            addCriterion("test_dummy_col2 is null");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2IsNotNull() {
            addCriterion("test_dummy_col2 is not null");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2EqualTo(String value) {
            addCriterion("test_dummy_col2 =", value, "testDummyCol2");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2NotEqualTo(String value) {
            addCriterion("test_dummy_col2 <>", value, "testDummyCol2");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2GreaterThan(String value) {
            addCriterion("test_dummy_col2 >", value, "testDummyCol2");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2GreaterThanOrEqualTo(String value) {
            addCriterion("test_dummy_col2 >=", value, "testDummyCol2");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2LessThan(String value) {
            addCriterion("test_dummy_col2 <", value, "testDummyCol2");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2LessThanOrEqualTo(String value) {
            addCriterion("test_dummy_col2 <=", value, "testDummyCol2");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2Like(String value) {
            addCriterion("test_dummy_col2 like", value, "testDummyCol2");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2NotLike(String value) {
            addCriterion("test_dummy_col2 not like", value, "testDummyCol2");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2In(List<String> values) {
            addCriterion("test_dummy_col2 in", values, "testDummyCol2");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2NotIn(List<String> values) {
            addCriterion("test_dummy_col2 not in", values, "testDummyCol2");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2Between(String value1, String value2) {
            addCriterion("test_dummy_col2 between", value1, value2, "testDummyCol2");
            return (Criteria) this;
        }

        public Criteria andTestDummyCol2NotBetween(String value1, String value2) {
            addCriterion("test_dummy_col2 not between", value1, value2, "testDummyCol2");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table mst_employee
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table mst_employee
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}