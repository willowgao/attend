package com.wgsoft.system.model;

public class SysMenu implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -495627262328416493L;
	String menuid;
	String parentid;
	String action;
	String isdisable;
	String xh;
	String menuname;
	private boolean isCheck = false;//是否选中
	

	String orgType;

	
	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getIsdisable() {
		return isdisable;
	}

	
	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public void setIsdisable(String isdisable) {
		this.isdisable = isdisable;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

}
