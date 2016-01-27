package com.wgsoft.performance.model;

// default package

import java.util.Date;

import com.wgsoft.common.model.BaseVO;

/**
 * PerformanceAssess entity. @author MyEclipse Persistence Tools
 */

public class PerformanceAssess extends BaseVO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6467073546524680224L;
	private String assessid;
	private String deptid;
	private String userid;
	private String assessyear;
	private Date starttime;
	private Date endtime;
	private String assesser;
	private Date assessdate;
	private String assesstype;
	private String indexid;
	private Double indexscore;

	private String roletype;

	// Constructors

	/** default constructor */
	public PerformanceAssess() {
	}

	/** minimal constructor */
	public PerformanceAssess(String assessid) {
		this.assessid = assessid;
	}

	/** full constructor */
	public PerformanceAssess(String assessid, String deptid, String userid, String assessyear, Date starttime,
			Date endtime, String assesser, Date assessdate, String assesstype, String indexid, Double indexscore) {
		this.assessid = assessid;
		this.deptid = deptid;
		this.userid = userid;
		this.assessyear = assessyear;
		this.starttime = starttime;
		this.endtime = endtime;
		this.assesser = assesser;
		this.assessdate = assessdate;
		this.assesstype = assesstype;
		this.indexid = indexid;
		this.indexscore = indexscore;
	}

	// Property accessors

	public String getAssessid() {
		return this.assessid;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public void setAssessid(String assessid) {
		this.assessid = assessid;
	}

	public String getDeptid() {
		return this.deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAssessyear() {
		return this.assessyear;
	}

	public void setAssessyear(String assessyear) {
		this.assessyear = assessyear;
	}

	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getAssesser() {
		return this.assesser;
	}

	public void setAssesser(String assesser) {
		this.assesser = assesser;
	}

	public Date getAssessdate() {
		return this.assessdate;
	}

	public void setAssessdate(Date assessdate) {
		this.assessdate = assessdate;
	}

	public String getAssesstype() {
		return this.assesstype;
	}

	public void setAssesstype(String assesstype) {
		this.assesstype = assesstype;
	}

	public String getIndexid() {
		return this.indexid;
	}

	public void setIndexid(String indexid) {
		this.indexid = indexid;
	}

	public Double getIndexscore() {
		return this.indexscore;
	}

	public void setIndexscore(Double indexscore) {
		this.indexscore = indexscore;
	}

}