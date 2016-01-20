package com.wgsoft.attendance.clock.model;

import java.util.Date;

// default package

/**
 * LeavesApprove entity. @author MyEclipse Persistence Tools
 */

public class LeavesApprove implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5159530618104736960L;
	private String approveid;
	private String leaveid;
	private String leavetype;
	private String userid;
	private String comments;
	private String approverid;
	private String status;
	private Date appdate;

	// Constructors

	/** default constructor */
	public LeavesApprove() {
	}

	/** minimal constructor */
	public LeavesApprove(String approveid, String leaveid) {
		this.approveid = approveid;
		this.leaveid = leaveid;
	}

	/** full constructor */
	public LeavesApprove(String approveid, String leaveid, String leavetype, String userid, String comments,
			String approverid, String status) {
		this.approveid = approveid;
		this.leaveid = leaveid;
		this.leavetype = leavetype;
		this.userid = userid;
		this.comments = comments;
		this.approverid = approverid;
		this.status = status;
	}

	// Property accessors

	public String getApproveid() {
		return this.approveid;
	}

	public Date getAppdate() {
		return appdate;
	}

	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}

	public void setApproveid(String approveid) {
		this.approveid = approveid;
	}

	public String getLeaveid() {
		return this.leaveid;
	}

	public void setLeaveid(String leaveid) {
		this.leaveid = leaveid;
	}

	public String getLeavetype() {
		return this.leavetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getApproverid() {
		return this.approverid;
	}

	public void setApproverid(String approverid) {
		this.approverid = approverid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}