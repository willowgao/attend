package com.wgsoft.diary.model;

import java.math.BigDecimal;

public class EchartsOfBar implements java.io.Serializable {

	/**
	 * 图表标题
	 */
	private String title;
	/**
	 * 图表副标题
	 */
	private String childtitle;
	/**
	 * 数据分组名称
	 */
	private String dataname;

	/**
	 * 数据单位
	 */
	private String dataunit;
	/**
	 * x轴数据
	 */
	private Object[] xAxis;
	/**
	 * y轴数据
	 */
	private Object[] yAxis;

	/**
	 * 
	 */
	private Object[] groupName;

	private BigDecimal xdata;

	private String xcomments;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getXdata() {
		return xdata;
	}

	public void setXdata(BigDecimal xdata) {
		this.xdata = xdata;
	}

	public String getXcomments() {
		return xcomments;
	}

	public void setXcomments(String xcomments) {
		this.xcomments = xcomments;
	}

	public Object[] getGroupName() {
		return groupName;
	}

	public void setGroupName(Object[] groupName) {
		this.groupName = groupName;
	}

	/**
	 * 数据分组
	 */
	private Object[] data;

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public String getDataunit() {
		return dataunit;
	}

	public void setDataunit(String dataunit) {
		this.dataunit = dataunit;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChildtitle() {
		return childtitle;
	}

	public void setChildtitle(String childtitle) {
		this.childtitle = childtitle;
	}

	public String getDataname() {
		return dataname;
	}

	public void setDataname(String dataname) {
		this.dataname = dataname;
	}

	public Object[] getxAxis() {
		return xAxis;
	}

	public void setxAxis(Object[] xAxis) {
		this.xAxis = xAxis;
	}

	public Object[] getyAxis() {
		return yAxis;
	}

	public void setyAxis(Object[] yAxis) {
		this.yAxis = yAxis;
	}

}
