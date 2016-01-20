package com.wgsoft.system.model;

// default package

/**
 * RoleInfo entity. @author MyEclipse Persistence Tools
 */

public class RoleInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4961184048055229965L;
	private String roleid;
	private String rolecode;
	private String rolename;
	private String roledeptid;
	private String roleorg;
	private String roletype;

	// Constructors

	/** default constructor */
	public RoleInfo() {
	}

	/** minimal constructor */
	public RoleInfo(String roleid) {
		this.roleid = roleid;
	}

	/** full constructor */
	public RoleInfo(String roleid, String rolecode, String rolename, String roledeptid, String roleorg) {
		this.roleid = roleid;
		this.rolecode = rolecode;
		this.rolename = rolename;
		this.roledeptid = roledeptid;
		this.roleorg = roleorg;
	}

	// Property accessors

	public String getRoleid() {
		return this.roleid;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolecode() {
		return this.rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoledeptid() {
		return this.roledeptid;
	}

	public void setRoledeptid(String roledeptid) {
		this.roledeptid = roledeptid;
	}

	public String getRoleorg() {
		return this.roleorg;
	}

	public void setRoleorg(String roleorg) {
		this.roleorg = roleorg;
	}

}