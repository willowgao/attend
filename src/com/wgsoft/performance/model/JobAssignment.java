package com.wgsoft.performance.model;

// default package

import java.util.Date;

import com.wgsoft.common.model.BaseVO;

/**
 * JobAssignment entity. @author MyEclipse Persistence Tools
 */

public class JobAssignment extends BaseVO implements java.io.Serializable {

	// Fields

	private String jobid;
	private String jobtype;
	private String executiver;
	private String comments;
	private Date starttime;
	private Date endtime;
	private Integer plantime;
	private Integer worktime;
	private Date filltime;
	private String jobname;
	private String status;
	private String userid;
	private String approver;
	private String appcomments;
	private Date appdate;
	private String approveid;
	private Double progress;
	
	private Date uploadtime;
	private Date confirmtime;

	// Constructors

	/** default constructor */
	public JobAssignment() {
	}

	/** minimal constructor */
	public JobAssignment(String jobid) {
		this.jobid = jobid;
	}


	/** full constructor */
	public JobAssignment(String jobid, String jobtype, String executiver, String comments, Date starttime,
			Date endtime, Integer plantime, Integer worktime, Date filltime, String status, String userid,
			String approveid) {
		this.jobid = jobid;
		this.jobtype = jobtype;
		this.executiver = executiver;
		this.comments = comments;
		this.starttime = starttime;
		this.endtime = endtime;
		this.plantime = plantime;
		this.worktime = worktime;
		this.filltime = filltime;
		this.status = status;
		this.userid = userid;
		this.approveid = approveid;
	}


	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}
	
	
	
	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public Date getConfirmtime() {
		return confirmtime;
	}

	public void setConfirmtime(Date confirmtime) {
		this.confirmtime = confirmtime;
	}

	// Property accessors
	public String getJobid() {
		return this.jobid;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getApproveid() {
		return approveid;
	}

	public void setApproveid(String approveid) {
		this.approveid = approveid;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}

	public String getJobtype() {
		return this.jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	public String getExecutiver() {
		return this.executiver;
	}

	public void setExecutiver(String executiver) {
		this.executiver = executiver;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public Integer getPlantime() {
		return this.plantime;
	}

	public void setPlantime(Integer plantime) {
		this.plantime = plantime;
	}

	public Integer getWorktime() {
		return this.worktime;
	}

	public void setWorktime(Integer worktime) {
		this.worktime = worktime;
	}

	public Date getFilltime() {
		return this.filltime;
	}

	public void setFilltime(Date filltime) {
		this.filltime = filltime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getApprover() {
		return this.approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getAppcomments() {
		return this.appcomments;
	}

	public void setAppcomments(String appcomments) {
		this.appcomments = appcomments;
	}

	public Date getAppdate() {
		return this.appdate;
	}

	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}

}