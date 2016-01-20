package com.wgsoft.performance.model;
// default package

import java.util.Date;


/**
 * PositionStatementApprove entity. @author MyEclipse Persistence Tools
 */

public class PositionStatementApprove  implements java.io.Serializable {


    // Fields    

     private String appid;
     private String psid;
     private String deptid;
     private String roleid;
     private Date starttime;
     private String status;
     private String statements;
     private String standards;
     private String comments;
     private String approver;
     private Date appdate;


    // Constructors

    /** default constructor */
    public PositionStatementApprove() {
    }

	/** minimal constructor */
    public PositionStatementApprove(String appid) {
        this.appid = appid;
    }
    
    /** full constructor */
    public PositionStatementApprove(String appid, String psid, String deptid, String roleid, Date starttime, String status, String statements, String standards, String comments, String approver, Date appdate) {
        this.appid = appid;
        this.psid = psid;
        this.deptid = deptid;
        this.roleid = roleid;
        this.starttime = starttime;
        this.status = status;
        this.statements = statements;
        this.standards = standards;
        this.comments = comments;
        this.approver = approver;
        this.appdate = appdate;
    }

   
    // Property accessors

    public String getAppid() {
        return this.appid;
    }
    
    public void setAppid(String appid) {
        this.appid = appid;
    }

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

    public String getApprover() {
        return this.approver;
    }
    
    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Date getAppdate() {
        return this.appdate;
    }
    
    public void setAppdate(Date appdate) {
        this.appdate = appdate;
    }
   








}