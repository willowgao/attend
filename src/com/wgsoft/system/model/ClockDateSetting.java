package com.wgsoft.system.model;

import java.util.Date;

// default package



/**
 * ClockDateSetting entity. @author MyEclipse Persistence Tools
 */

public class ClockDateSetting  implements java.io.Serializable {


    // Fields    
	
	private String id;

     private String clockyear;
     private Date clockdate;
     private String isneed;


    // Constructors

    /** default constructor */
    public ClockDateSetting() {
    }
  
    /** full constructor */
    public ClockDateSetting(String clockyear ,Date clockdate, String isneed,String id) {
        this.clockyear = clockyear;
        this.clockdate = clockdate;
        this.id = id;
        this.isneed = isneed;
    }
    
    

    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsneed() {
        return this.isneed;
    }
    
    public String getClockyear() {
		return clockyear;
	}

	public void setClockyear(String clockyear) {
		this.clockyear = clockyear;
	}

	 

	public Date getClockdate() {
		return clockdate;
	}

	public void setClockdate(Date clockdate) {
		this.clockdate = clockdate;
	}

	public void setIsneed(String isneed) {
        this.isneed = isneed;
    }
   








}