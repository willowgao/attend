package com.wgsoft.diary.model;
// default package

import java.util.Date;


/**
 * DiaryApprove entity. @author MyEclipse Persistence Tools
 */

public class DiaryApprove  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 3297708795775816527L;
	private String approveid;
     private String diaryid;
     private String userid;
     private String approverid;
     private String comments;
     private Date appdate;
     private String status;
     private String diarytype;


    // Constructors

    /** default constructor */
    public DiaryApprove() {
    }

	/** minimal constructor */
    public DiaryApprove(String approveid) {
        this.approveid = approveid;
    }
    
    /** full constructor */
    public DiaryApprove(String approveid, String diaryid, String userid, String approverid, String comments, Date appdate, String status, String diarytype) {
        this.approveid = approveid;
        this.diaryid = diaryid;
        this.userid = userid;
        this.approverid = approverid;
        this.comments = comments;
        this.appdate = appdate;
        this.status = status;
        this.diarytype = diarytype;
    }

   
    // Property accessors

    public String getApproveid() {
        return this.approveid;
    }
    
    public void setApproveid(String approveid) {
        this.approveid = approveid;
    }

    public String getDiaryid() {
        return this.diaryid;
    }
    
    public void setDiaryid(String diaryid) {
        this.diaryid = diaryid;
    }

    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getApproverid() {
        return this.approverid;
    }
    
    public void setApproverid(String approverid) {
        this.approverid = approverid;
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

    public String getDiarytype() {
        return this.diarytype;
    }
    
    public void setDiarytype(String diarytype) {
        this.diarytype = diarytype;
    }
   








}