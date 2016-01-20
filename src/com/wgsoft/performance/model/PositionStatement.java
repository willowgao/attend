package com.wgsoft.performance.model;
// default package

import java.util.Date;


/**
 * PositionStatement entity. @author MyEclipse Persistence Tools
 */

public class PositionStatement  implements java.io.Serializable {


    // Fields    

     private String psid;
     private String deptid;
     private String roleid;
     private Date starttime;
     private String status;
     private String statements;
     private String standards;
     private String comments;
     private String approver;

    // custom fields
     private String ssid;
     
    // Constructors

    public String getSsid() {
		return ssid;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	/** default constructor */
    public PositionStatement() {
    }

	/** minimal constructor */
    public PositionStatement(String psid) {
        this.psid = psid;
    }
    
    /** full constructor */
    public PositionStatement(String psid, String deptid, String roleid, Date starttime, String status, String statements, String standards, String comments) {
        this.psid = psid;
        this.deptid = deptid;
        this.roleid = roleid;
        this.starttime = starttime;
        this.status = status;
        this.statements = statements;
        this.standards = standards;
        this.comments = comments;
    }

   
    // Property accessors

    public String getPsid() {
        return this.psid;
    }
    
    public void setPsid(String psid) {
        this.psid = psid;
    }

    public String getDeptid() {
        return this.deptid;
    }
    
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public Date getStarttime() {
        return this.starttime;
    }
    
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatements() {
        return this.statements;
    }
    
    public void setStatements(String statements) {
        this.statements = statements;
    }

    public String getStandards() {
        return this.standards;
    }
    
    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
   








}