package com.wgsoft.attendance.clock.model;

// default package

/**
 * LeaveAppSet entity. @author MyEclipse Persistence Tools
 */

public class LeaveAppSet extends BaseVO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1021191233502038407L;
	private String settingid;
	private String leavetype;
	private String approver;
	private String leavename;
	private String approvername;
	private String approvetype;

	// Constructors

	/** default constructor */
	public LeaveAppSet() {
	}

	/** full constructor */
	public LeaveAppSet(String leavetype, String approver, String leavename, String approvername) {
		this.leavetype = leavetype;
		this.approver = approver;
		this.leavename = leavename;
		this.approvername = approvername;
	}

	// Property accessors

	public String getLeavetype() {
		return this.leavetype;
	}
 

	public String getSettingid() {
		return settingid;
	}

	public void setSettingid(String settingid) {
		this.settingid = settingid;
	}

	public String getApprovetype() {
		return approvetype;
	}

	public void setApprovetype(String approvetype) {
		this.approvetype = approvetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	public String getApprover() {
		return this.approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getLeavename() {
		return this.leavename;
	}

	public void setLeavename(String leavename) {
		this.leavename = leavename;
	}

	public String getApprovername() {
		return this.approvername;
	}

	public void setApprovername(String approvername) {
		this.approvername = approvername;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LeaveAppSet))
			return false;
		LeaveAppSet castOther = (LeaveAppSet) other;

		return ((this.getLeavetype() == castOther.getLeavetype()) || (this.getLeavetype() != null
				&& castOther.getLeavetype() != null && this.getLeavetype().equals(castOther.getLeavetype())))
				&& ((this.getApprover() == castOther.getApprover()) || (this.getApprover() != null
						&& castOther.getApprover() != null && this.getApprover().equals(castOther.getApprover())))
				&& ((this.getLeavename() == castOther.getLeavename()) || (this.getLeavename() != null
						&& castOther.getLeavename() != null && this.getLeavename().equals(castOther.getLeavename())))
				&& ((this.getApprovername() == castOther.getApprovername()) || (this.getApprovername() != null
						&& castOther.getApprovername() != null && this.getApprovername().equals(
						castOther.getApprovername())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getLeavetype() == null ? 0 : this.getLeavetype().hashCode());
		result = 37 * result + (getApprover() == null ? 0 : this.getApprover().hashCode());
		result = 37 * result + (getLeavename() == null ? 0 : this.getLeavename().hashCode());
		result = 37 * result + (getApprovername() == null ? 0 : this.getApprovername().hashCode());
		return result;
	}

}