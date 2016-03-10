package com.wgsoft.diary.model;

// default package

import java.lang.reflect.Field;
import java.util.Date;

/**
 * DiaryDaily entity. @author MyEclipse Persistence Tools
 */

public class DiaryDaily implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8793500846194013989L;
	private String diaryid;
	private Date diarydate;
	private Date starttime;
	private Date endtime;
	private String diarytype;
	private String userid;
	private String content;
	private String status;
	private String approverid;
	private String comments;
	private String nextcontent;
	private Integer viewcount;
	private Integer commentcount;
	private String onsubmit;
	
	private String deptid;

	// Constructors

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getOnsubmit() {
		return onsubmit;
	}

	public void setOnsubmit(String onsubmit) {
		this.onsubmit = onsubmit;
	}

	/** default constructor */
	public DiaryDaily() {
	}

	/** minimal constructor */
	public DiaryDaily(String diaryid) {
		this.diaryid = diaryid;
	}

	/** full constructor */
	public DiaryDaily(String diaryid, Date diarydate, String userid, String content, String status, String approverid,
			String comments, Integer viewcount) {
		this.diaryid = diaryid;
		this.diarydate = diarydate;
		this.userid = userid;
		this.content = content;
		this.status = status;
		this.approverid = approverid;
		this.comments = comments;
		this.viewcount = viewcount;
	}

	
	// Property accessors

	public Integer getCommentcount() {
		return commentcount;
	}

	public void setCommentcount(Integer commentcount) {
		this.commentcount = commentcount;
	}

	public String getNextcontent() {
		return nextcontent;
	}

	public void setNextcontent(String nextcontent) {
		this.nextcontent = nextcontent;
	}

	public String getDiaryid() {
		return this.diaryid;
	}

	public void setDiaryid(String diaryid) {
		this.diaryid = diaryid;
	}

	public Date getDiarydate() {
		return this.diarydate;
	}

	public void setDiarydate(Date diarydate) {
		this.diarydate = diarydate;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getViewcount() {
		return this.viewcount;
	}

	public void setViewcount(Integer viewcount) {
		this.viewcount = viewcount;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getDiarytype() {
		return diarytype;
	}

	public void setDiarytype(String diarytype) {
		this.diarytype = diarytype;
	}
	
	

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Class cls = DiaryDaily.class;
        Field[] fields = cls.getDeclaredFields();  
        for(int i=0; i<fields.length; i++){  
            Field f = fields[i];  
            f.setAccessible(true);  
			System.out.println("属性名:" + f.getName()+ " 属性值:" + f.get(new DiaryDaily()));
        }   
	}

}