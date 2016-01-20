package com.wgsoft.diary.model;
// default package

import java.util.Date;


/**
 * DiaryComments entity. @author MyEclipse Persistence Tools
 */

public class DiaryComments  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -7723957635211991398L;
	private String commentid;
     private String diaryid;
     private String userid;
     private String comments;
     private Integer agree;
     private Integer disagree;
     private Integer nomarl;
     private Date commdate;
     private String username;


    // Constructors

    /** default constructor */
    public DiaryComments() {
    }

	/** minimal constructor */
    public DiaryComments(String commentid) {
        this.commentid = commentid;
    }
    
    /** full constructor */
    public DiaryComments(String commentid, String diaryid, String userid, String comments, Integer agree, Integer disagree, Integer nomarl, Date commdate) {
        this.commentid = commentid;
        this.diaryid = diaryid;
        this.userid = userid;
        this.comments = comments;
        this.agree = agree;
        this.disagree = disagree;
        this.nomarl = nomarl;
        this.commdate = commdate;
    }

   
    // Property accessors

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCommentid() {
        return this.commentid;
    }
    
    public void setCommentid(String commentid) {
        this.commentid = commentid;
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

    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getAgree() {
        return this.agree;
    }
    
    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public Integer getDisagree() {
        return this.disagree;
    }
    
    public void setDisagree(Integer disagree) {
        this.disagree = disagree;
    }

    public Integer getNomarl() {
        return this.nomarl;
    }
    
    public void setNomarl(Integer nomarl) {
        this.nomarl = nomarl;
    }

    public Date getCommdate() {
        return this.commdate;
    }
    
    public void setCommdate(Date commdate) {
        this.commdate = commdate;
    }
   








}