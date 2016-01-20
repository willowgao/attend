package com.wgsoft.common.model;

// default package

/**
 * OrganizationId entity. @author MyEclipse Persistence Tools
 */

public class Organization implements java.io.Serializable {

	// Fields

	private String orgid;
	private String parentid;
	private String orgname;
	private String orgType;
	private String deptid;
	private String org;

	// Constructors

	/** default constructor */
	public Organization() {
	}

	/** full constructor */
	public Organization(String orgid, String parentid, String orgname) {
		this.orgid = orgid;
		this.parentid = parentid;
		this.orgname = orgname;
	}
	

	
	// Property accessors

	
	public String getDeptid() {
		return deptid;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Organization))
			return false;
		Organization castOther = (Organization) other;

		return ((this.getOrgid() == castOther.getOrgid()) || (this.getOrgid() != null && castOther.getOrgid() != null && this
							.getOrgid().equals(castOther.getOrgid())))
							&& ((this.getParentid() == castOther.getParentid()) || (this.getParentid() != null
												&& castOther.getParentid() != null && this.getParentid().equals(
												castOther.getParentid())))
							&& ((this.getOrgname() == castOther.getOrgname()) || (this.getOrgname() != null
												&& castOther.getOrgname() != null && this.getOrgname().equals(
												castOther.getOrgname())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getOrgid() == null ? 0 : this.getOrgid().hashCode());
		result = 37 * result + (getParentid() == null ? 0 : this.getParentid().hashCode());
		result = 37 * result + (getOrgname() == null ? 0 : this.getOrgname().hashCode());
		return result;
	}

}