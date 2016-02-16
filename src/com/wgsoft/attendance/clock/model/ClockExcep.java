package com.wgsoft.attendance.clock.model;
// default package

import java.util.Date;


/**
 * ClockException entity. @author MyEclipse Persistence Tools
 */

public class ClockExcep  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1023757232474933080L;
	private String expid;
     private Date clockdate;
     private String exptype;
     private String clocktime;
     private String userid;
     private String isenable;
     private String comments;


    // Constructors

    public String getIsenable() {
		return isenable;
	}

	public void setIsenable(String isenable) {
		this.isenable = isenable;
	}

	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	/** default constructor */
    public ClockExcep() {
    }

	/** minimal constructor */
    public ClockExcep(String expid) {
        this.expid = expid;
    }
    
    /** full constructor */
    public ClockExcep(String expid, Date clockdate, String exptype, String clocktime, String userid) {
        this.expid = expid;
        this.clockdate = clockdate;
        this.exptype = exptype;
        this.clocktime = clocktime;
        this.userid = userid;
    }

   
    // Property accessors

    public String getExpid() {
        return this.expid;
    }
    
    public void setExpid(String expid) {
        this.expid = expid;
    }

    public Date getClockdate() {
        return this.clockdate;
    }
    
    public void setClockdate(Date clockdate) {
        this.clockdate = clockdate;
    }

    public String getExptype() {
        return this.exptype;
    }
    
    public void setExptype(String exptype) {
        this.exptype = exptype;
    }

    public String getClocktime() {
        return this.clocktime;
    }
    
    public void setClocktime(String clocktime) {
        this.clocktime = clocktime;
    }

    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
   








}