package com.wgsoft.performance.model;
// default package

import java.util.Date;


/**
 * JobApprove entity. @author MyEclipse Persistence Tools
 */

public class JobApprove  implements java.io.Serializable {


    // Fields    

     private String approveid;
     private String approver;
     private String jobname;
     private Date starttime;
     private Date endtime;
     private Date appdate;
     private String appcomments;
     private String status;


    // Constructors

    /** default constructor */
    public JobApprove() {
    }

	/** minimal constructor */
    public JobApprove(String approveid) {
        this.approveid = approveid;
    }
    
    /** full constructor */
    public JobApprove(String approveid, String approver, String jobname, Date starttime, Date endtime, Date appdate, String appcomments, String status) {
        this.approveid = approveid;
        this.approver = approver;
        this.jobname = jobname;
        this.starttime = starttime;
        this.endtime = endtime;
        this.appdate = appdate;
        this.appcomments = appcomments;
        this.status = status;
    }

   
    // Property accessors

    public String getApproveid() {
        return this.approveid;
    }
    
    public void setApproveid(String approveid) {
        this.approveid = approveid;
    }

    public String getApprover() {
        return this.approver;
    }
    
    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getJobname() {
        return this.jobname;
    }
    
    public void setJobname(String jobname) {
        this.jobname = jobname;
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

    public Date getAppdate() {
        return this.appdate;
    }
    
    public void setAppdate(Date appdate) {
        this.appdate = appdate;
    }

    public String getAppcomments() {
        return this.appcomments;
    }
    
    public void setAppcomments(String appcomments) {
        this.appcomments = appcomments;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
   








}