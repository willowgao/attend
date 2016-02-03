package com.wgsoft.attendance.clock.model;

import java.util.Date;

// default package

/**
 * ClockRecords entity. @author MyEclipse Persistence Tools
 */

public class ClockRecords implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -770294197327037094L;

	private ClockRecordsId id;

	private String userid;
	private Date clockdate;
	private String amsb;
	private String amxb;
	private String pmsb;
	private String pmxb;
	private String checkTime;
	private String type ;
 
	private String isneed;
	private String comments;
	// 开始时间
	private Date startTime;
	// 终止时间
	private Date endTime;
	
	private String amsbtime;
	private String amxbtime;
	private String pmsbtime;
	private String pmxbtime;
	
	private String flag;
	
	private String dept;
	
	
	
	

	// Constructors
	

	public String getAmsbtime() {
		return amsbtime;
	}

	public String getFlag() {
		return flag;
	}

	
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setAmsbtime(String amsbtime) {
		this.amsbtime = amsbtime;
	}

	public String getAmxbtime() {
		return amxbtime;
	}

	public void setAmxbtime(String amxbtime) {
		this.amxbtime = amxbtime;
	}

	public String getPmsbtime() {
		return pmsbtime;
	}

	public void setPmsbtime(String pmsbtime) {
		this.pmsbtime = pmsbtime;
	}

	public String getPmxbtime() {
		return pmxbtime;
	}

	public void setPmxbtime(String pmxbtime) {
		this.pmxbtime = pmxbtime;
	}

	public String getIsneed() {
		return isneed;
	}

	public void setIsneed(String isneed) {
		this.isneed = isneed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	/** default constructor */
	public ClockRecords() {
	}

	/** minimal constructor */
	public ClockRecords(ClockRecordsId id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getClockdate() {
		return clockdate;
	}

	public void setClockdate(Date clockdate) {
		this.clockdate = clockdate;
	}

	/** full constructor */
	public ClockRecords(ClockRecordsId id, String amsb, String amxb, String pmsb, String pmxb) {
		this.id = id;
		this.amsb = amsb;
		this.amxb = amxb;
		this.pmsb = pmsb;
		this.pmxb = pmxb;
	}
	
	

	// Property accessors

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public ClockRecordsId getId() {
		return this.id;
	}

	public void setId(ClockRecordsId id) {
		this.id = id;
	}

	public String getAmsb() {
		return this.amsb;
	}

	public void setAmsb(String amsb) {
		this.amsb = amsb;
	}

	public String getAmxb() {
		return this.amxb;
	}

	public void setAmxb(String amxb) {
		this.amxb = amxb;
	}

	public String getPmsb() {
		return this.pmsb;
	}

	public void setPmsb(String pmsb) {
		this.pmsb = pmsb;
	}

	public String getPmxb() {
		return this.pmxb;
	}

	public void setPmxb(String pmxb) {
		this.pmxb = pmxb;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}