package com.wgsoft.performance.model;

// default package

import java.util.Date;

import com.wgsoft.common.model.BaseVO;

/**
 * AbstractPerformanceAssessScore entity provides the base persistence
 * definition of the PerformanceAssessScore entity. @author MyEclipse
 * Persistence Tools
 */

public class PerformanceAssessScore extends BaseVO implements java.io.Serializable {

	// Fields

	private String scoreid;
	private String userid;
	private String assessyear;
	private Date starttime;
	private Date endtime;
	private Double higherscore;
	private Double peerscore;
	private Double attednscore;
	private Double finalscore;
	private String uploder;
	private Date uplodetime;
	private Double reductionscore;
	
	private String deptid;
	private String assesstype;
	private String assesser;

	// Constructors

	/** default constructor */
	public PerformanceAssessScore() {
	}

	/** minimal constructor */
	public PerformanceAssessScore(String scoreid) {
		this.scoreid = scoreid;
	}

	/** full constructor */
	public PerformanceAssessScore(String scoreid, String userid,
			String assessyear, Date starttime, Date endtime, Double higherscore,
			Double peerscore, Double attednscore,Double finalscore, String uploder, Date uplodetime) {
		this.scoreid = scoreid;
		this.userid = userid;
		this.assessyear = assessyear;
		this.starttime = starttime;
		this.endtime = endtime;
		this.higherscore = higherscore;
		this.peerscore = peerscore;
		this.attednscore = attednscore;
		this.finalscore = finalscore;
		this.uploder = uploder;
		this.uplodetime = uplodetime;
	}

	
	// Property accessors

	public Double getReductionscore() {
		return reductionscore;
	}

	public String getAssesstype() {
		return assesstype;
	}

	public String getAssesser() {
		return assesser;
	}

	public void setAssesser(String assesser) {
		this.assesser = assesser;
	}

	public void setAssesstype(String assesstype) {
		this.assesstype = assesstype;
	}

	public void setReductionscore(Double reductionscore) {
		this.reductionscore = reductionscore;
	}

	public String getScoreid() {
		return this.scoreid;
	}

	
	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public void setScoreid(String scoreid) {
		this.scoreid = scoreid;
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

	public Double getHigherscore() {
		return this.higherscore;
	}

	public void setHigherscore(Double higherscore) {
		this.higherscore = higherscore;
	}

	public Double getPeerscore() {
		return this.peerscore;
	}

	public void setPeerscore(Double peerscore) {
		this.peerscore = peerscore;
	}

	public Double getAttednscore() {
		return this.attednscore;
	}

	public void setAttednscore(Double attednscore) {
		this.attednscore = attednscore;
	}
	
	

	public Double getFinalscore() {
		return finalscore;
	}

	public void setFinalscore(Double finalscore) {
		this.finalscore = finalscore;
	}

	public String getUploder() {
		return this.uploder;
	}

	public void setUploder(String uploder) {
		this.uploder = uploder;
	}

	public Date getUplodetime() {
		return this.uplodetime;
	}

	public void setUplodetime(Date uplodetime) {
		this.uplodetime = uplodetime;
	}

}