package com.wgsoft.system.model;

// default package

/**
 * UserMenu entity. @author MyEclipse Persistence Tools
 */

public class UserMenu implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2731042078119630664L;
	private UserMenuId id;
	private String userorg;
	private String menuid;
    private String userid;

	// Constructors

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	/** default constructor */
	public UserMenu() {
	}

	/** minimal constructor */
	public UserMenu(UserMenuId id) {
		this.id = id;
	}

	/** full constructor */
	public UserMenu(UserMenuId id, String userorg) {
		this.id = id;
		this.userorg = userorg;
	}

	// Property accessors

	public UserMenuId getId() {
		return this.id;
	}

	public void setId(UserMenuId id) {
		this.id = id;
	}

	public String getUserorg() {
		return this.userorg;
	}

	public void setUserorg(String userorg) {
		this.userorg = userorg;
	}

}