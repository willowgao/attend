package com.wgsoft.performance.model;

// default package

import java.util.Date;

/**
 * AbstractPerformanceAssessScore entity provides the base persistence
 * definition of the PerformanceAssessScore entity. @author MyEclipse
 * Persistence Tools
 */

public class PerformanceAssessScore implements java.io.Serializable {

	// Fields

	private String scoreid;
	private Long userid;
	private Short assessyear;
	private Date starttime;
	private Date endtime;
	private Double higherscore;
	private Double peerscore;
	private Double attednscore;
	private Double finalscore;
	private Long uploder;
	private Date uplodetime;

	// Constructors

	/** default constructor */
	public PerformanceAssessScore() {
	}

	/** minimal constructor */
	public PerformanceAssessScore(String scoreid) {
		this.scoreid = scoreid;
	}

	/** full constructor */
	public PerformanceAssessScore(String scoreid, Long userid,
			Short assessyear, Date starttime, Date endtime, Double higherscore,
			Double peerscore, Double attednscore,Double finalscore, Long uploder, Date uplodetime) {
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

	public String getScoreid() {
		return this.scoreid;
	}

	public void setScoreid(String scoreid) {
		this.scoreid = scoreid;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Short getAssessyear() {
		return this.assessyear;
	}

	public void setAssessyear(Short assessyear) {
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

	public Long getUploder() {
		return this.uploder;
	}

	public void setUploder(Long uploder) {
		this.uploder = uploder;
	}

	public Date getUplodetime() {
		return this.uplodetime;
	}

	public void setUplodetime(Date uplodetime) {
		this.uplodetime = uplodetime;
	}

}