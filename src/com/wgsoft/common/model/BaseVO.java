package com.wgsoft.common.model;

/**
 * 
 * @title： BaseVO.java
 * @desc：
 * @author： Willowgao
 * @date： 2016-1-27 下午03:06:25
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class BaseVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6420656281716828558L;

	private String datagrid;

	public BaseVO() {
	}

	public String getDatagrid() {
		return datagrid;
	}

	public void setDatagrid(String datagrid) {
		this.datagrid = datagrid;
	}

}
