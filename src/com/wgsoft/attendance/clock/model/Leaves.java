package com.wgsoft.attendance.clock.model;

// default package

import java.util.Date;

/**
 * Leaves entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Leaves extends BaseVO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3551188040474764042L;
	private String leaveid;
	private String userid;
	private Date startdate;
	private String starttime;
	private Date enddate;
	private String endtime;
	private String destination;
	private Double total;
	private String comments;
	private Date appdate;
	private String approverid;
	private String status;
	private String leavetype;

	// Constructors

	/** default constructor */
	public Leaves() {
	}

	/** minimal constructor */
	public Leaves(String leaveid) {
		this.leaveid = leaveid;
	}

	/** full constructor */
	public Leaves(String leaveid, String userid, Date startdate, String starttime, Date enddate, String endtime,
			String destination, Double total, String comments, Date appdate, String approverid, String status) {
		this.leaveid = leaveid;
		this.userid = userid;
		this.startdate = startdate;
		this.starttime = starttime;
		this.enddate = enddate;
		this.endtime = endtime;
		this.destination = destination;
		this.total = total;
		this.comments = comments;
		this.appdate = appdate;
		this.approverid = approverid;
		this.status = status;
	}

	// Property accessors

	public String getLeavetype() {
		return leavetype;
	}

	public String getApproverid() {
		return approverid;
	}

	public void setApproverid(String approverid) {
		this.approverid = approverid;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	public String getLeaveid() {
		return this.leaveid;
	}

	public void setLeaveid(String leaveid) {
		this.leaveid = leaveid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getAppdate() {
		return this.appdate;
	}

	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}