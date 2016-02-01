package com.wgsoft.attendance.clock.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @title： ClockSetting.java
 * @desc： 时间管理bean
 * @author： Willowgao
 * @date： 2015-10-25 下午01:06:55
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class ClockSetting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5091914974464026581L;
	private String id;
	// 开始时间
	private Date startTime;
	// 终止时间
	private Date endTime;
	// 是否停用(0：否，1：是)
	private String isEnable;
	// 上午上班时间
	private String amsbTime;
	// 上午下班时间
	private String amxbTime;
	// 下午上班时间
	private String pmsbTime;
	// 下午上班时间
	private String pmxbTime;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getAmsbTime() {
		return amsbTime;
	}

	public void setAmsbTime(String amsbTime) {
		this.amsbTime = amsbTime;
	}

	public String getAmxbTime() {
		return amxbTime;
	}

	public void setAmxbTime(String amxbTime) {
		this.amxbTime = amxbTime;
	}

	public String getPmsbTime() {
		return pmsbTime;
	}

	public void setPmsbTime(String pmsbTime) {
		this.pmsbTime = pmsbTime;
	}

	public String getPmxbTime() {
		return pmxbTime;
	}

	public void setPmxbTime(String pmxbTime) {
		this.pmxbTime = pmxbTime;
	}

}
