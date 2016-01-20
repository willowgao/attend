package com.wgsoft.attendance.clock.model;

import java.util.Date;

public class ClockExcepApprove {

	private String approveid;
	private String expid;
	private String userid;
	private String comments;
	private String approverid;
	private String status;
	private String exptype;
	private Date appdate;
	private Date clockdate;

	
	public Date getClockdate() {
		return clockdate;
	}

	public void setClockdate(Date clockdate) {
		this.clockdate = clockdate;
	}

	public String getApproveid() {
		return approveid;
	}

	public void setApproveid(String approveid) {
		this.approveid = approveid;
	}

	public String getExpid() {
		return expid;
	}

	public void setExpid(String expid) {
		this.expid = expid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getApproverid() {
		return approverid;
	}

	public void setApproverid(String approverid) {
		this.approverid = approverid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExptype() {
		return exptype;
	}

	public void setExptype(String exptype) {
		this.exptype = exptype;
	}

	public Date getAppdate() {
		return appdate;
	}

	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}
	

}
