package com.wgsoft.attendance.clock.model;
// default package

import java.util.Date;


/**
 * ClockRecordsId entity. @author MyEclipse Persistence Tools
 */

public class ClockRecordsId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -6271125768923301277L;
	private String userid;
     private Date clockdate;


    // Constructors

    /** default constructor */
    public ClockRecordsId() {
    }

    
    /** full constructor */
    public ClockRecordsId(String userid, Date clockdate) {
        this.userid = userid;
        this.clockdate = clockdate;
    }

   
    // Property accessors

    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getClockdate() {
        return this.clockdate;
    }
    
    public void setClockdate(Date clockdate) {
        this.clockdate = clockdate;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ClockRecordsId) ) return false;
		 ClockRecordsId castOther = ( ClockRecordsId ) other; 
         
		 return ( (this.getUserid()==castOther.getUserid()) || ( this.getUserid()!=null && castOther.getUserid()!=null && this.getUserid().equals(castOther.getUserid()) ) )
 && ( (this.getClockdate()==castOther.getClockdate()) || ( this.getClockdate()!=null && castOther.getClockdate()!=null && this.getClockdate().equals(castOther.getClockdate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserid() == null ? 0 : this.getUserid().hashCode() );
         result = 37 * result + ( getClockdate() == null ? 0 : this.getClockdate().hashCode() );
         return result;
   }   





}