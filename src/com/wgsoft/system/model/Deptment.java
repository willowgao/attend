package com.wgsoft.system.model;
// default package

import java.util.Date;


/**
 * Deptment entity. @author MyEclipse Persistence Tools
 */

public class Deptment  implements java.io.Serializable {


    // Fields    

     private String deptid;
     private String deptname;
     private String orgid;
     private String orgType;
     private Date createdate;
     private String createor;


    // Constructors

    public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/** default constructor */
    public Deptment() {
    }

	/** minimal constructor */
    public Deptment(String deptid) {
        this.deptid = deptid;
    }
    
    /** full constructor */
    public Deptment(String deptid, String deptname, String orgid, Date createdate, String createor) {
        this.deptid = deptid;
        this.deptname = deptname;
        this.orgid = orgid;
        this.createdate = createdate;
        this.createor = createor;
    }

   
    // Property accessors

    public String getDeptid() {
        return this.deptid;
    }
    
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getDeptname() {
        return this.deptname;
    }
    
    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getOrgid() {
        return this.orgid;
    }
    
    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public Date getCreatedate() {
        return this.createdate;
    }
    
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreateor() {
        return this.createor;
    }
    
    public void setCreateor(String createor) {
        this.createor = createor;
    }
   








}