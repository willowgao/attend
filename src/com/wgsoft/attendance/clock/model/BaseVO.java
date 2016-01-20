package com.wgsoft.attendance.clock.model;

/**
 * @title： BaseVO.java
 * @desc：公共VO
 * @author： Willowgao
 * @date： 2015-10-30 下午01:32:41
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class BaseVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8708875399318589813L;
	// 开始时间
	private String startTime;
	// 终止时间
	private String endTime;
	// 用户ID
	private String userid;
	// 角色类型
	private String roletype;
	// 状态
	private String status;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
