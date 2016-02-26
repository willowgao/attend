package com.wgsoft.user.model;

import java.util.List;
import java.util.Map;

// default package

/**
 * UserInfoId entity. @author MyEclipse Persistence Tools
 */

public class UserInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7947382898733047673L;
	private String userid;
	private String usercode;
	private String username;
	private String userpwd;
	private String userdeptid;
	private String userorg;
	private String roleid;
	private String roletype;
	private String higherid;
	private String defaultstyle;
	private String usersex;
	private String fontsize;

	public String getFontsize() {
		return fontsize;
	}

	public void setFontsize(String fontsize) {
		this.fontsize = fontsize;
	}

	private List<Map<String, String>> depts;
	private List<Map<String, String>> users;

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** full constructor */
	public UserInfo(String userid, String usercode, String username, String userpwd, String userdeptid, String userorg,
			String roleid) {
		this.userid = userid;
		this.usercode = usercode;
		this.username = username;
		this.userpwd = userpwd;
		this.userdeptid = userdeptid;
		this.userorg = userorg;
		this.roleid = roleid;
	}

	// Property accessors

	public String getHigherid() {
		return higherid;
	}

	public List<Map<String, String>> getUsers() {
		return users;
	}

	public void setUsers(List<Map<String, String>> users) {
		this.users = users;
	}

	public List<Map<String, String>> getDepts() {
		return depts;
	}

	public void setDepts(List<Map<String, String>> depts) {
		this.depts = depts;
	}

	public String getUsersex() {
		return usersex;
	}

	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}

	public String getDefaultstyle() {
		return defaultstyle;
	}

	public void setDefaultstyle(String defaultstyle) {
		this.defaultstyle = defaultstyle;
	}

	public void setHigherid(String higherid) {
		this.higherid = higherid;
	}

	public String getUserid() {
		return this.userid;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getUsercode() {
		return this.usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return this.userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUserdeptid() {
		return this.userdeptid;
	}

	public void setUserdeptid(String userdeptid) {
		this.userdeptid = userdeptid;
	}

	public String getUserorg() {
		return this.userorg;
	}

	public void setUserorg(String userorg) {
		this.userorg = userorg;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserInfo))
			return false;
		UserInfo castOther = (UserInfo) other;

		return ((this.getUserid() == castOther.getUserid()) || (this.getUserid() != null
				&& castOther.getUserid() != null && this.getUserid().equals(castOther.getUserid())))
				&& ((this.getUsercode() == castOther.getUsercode()) || (this.getUsercode() != null
						&& castOther.getUsercode() != null && this.getUsercode().equals(castOther.getUsercode())))
				&& ((this.getUsername() == castOther.getUsername()) || (this.getUsername() != null
						&& castOther.getUsername() != null && this.getUsername().equals(castOther.getUsername())))
				&& ((this.getUserpwd() == castOther.getUserpwd()) || (this.getUserpwd() != null
						&& castOther.getUserpwd() != null && this.getUserpwd().equals(castOther.getUserpwd())))
				&& ((this.getUserdeptid() == castOther.getUserdeptid()) || (this.getUserdeptid() != null
						&& castOther.getUserdeptid() != null && this.getUserdeptid().equals(castOther.getUserdeptid())))
				&& ((this.getUserorg() == castOther.getUserorg()) || (this.getUserorg() != null
						&& castOther.getUserorg() != null && this.getUserorg().equals(castOther.getUserorg())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result + (getUsercode() == null ? 0 : this.getUsercode().hashCode());
		result = 37 * result + (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result + (getUserpwd() == null ? 0 : this.getUserpwd().hashCode());
		result = 37 * result + (getUserdeptid() == null ? 0 : this.getUserdeptid().hashCode());
		result = 37 * result + (getUserorg() == null ? 0 : this.getUserorg().hashCode());
		return result;
	}

}