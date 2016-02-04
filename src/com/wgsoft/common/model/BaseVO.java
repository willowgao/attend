package com.wgsoft.common.model;

/**
 * 
 * @title： BaseVO.java
 * @desc：
 * @author： Willowgao
 * @date： 2016-1-27 下午03:06:25
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class BaseVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6420656281716828558L;

	private String datagrid;

	private String id;
	private String pid;

	public BaseVO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getDatagrid() {
		return datagrid;
	}

	public void setDatagrid(String datagrid) {
		this.datagrid = datagrid;
	}

}
