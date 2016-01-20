package com.wgsoft.attendance.diary.model;

import java.util.Date;

public class UserLoginInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6833127530055692350L;
	private String loginid;
	private String logincode;
	private String username;

	private Date logintime;
	private String loginip;
	private String logintmachine;

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getLogincode() {
		return logincode;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public String getLogintmachine() {
		return logintmachine;
	}

	public void setLogintmachine(String logintmachine) {
		this.logintmachine = logintmachine;
	}

}
