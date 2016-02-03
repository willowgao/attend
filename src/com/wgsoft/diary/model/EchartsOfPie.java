package com.wgsoft.diary.model;

import java.math.BigDecimal;

public class EchartsOfPie implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3909558667099489049L;

	/**
	 * 图表标题
	 */
	private String title;
	/**
	 * 图表副标题
	 */
	private String childtitle;
	
	private BigDecimal dataValue;
	private String dataName;

	 

	public BigDecimal getDataValue() {
		return dataValue;
	}

	public void setDataValue(BigDecimal dataValue) {
		this.dataValue = dataValue;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public static class Pie {
		private String value;

		private String name;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
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

	/**
	 * 
	 */
	private Object[] groupName;

	public Object[] getGroupName() {
		return groupName;
	}

	public void setGroupName(Object[] groupName) {
		this.groupName = groupName;
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

}
