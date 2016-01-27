package com.wgsoft.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.attendance.clock.model.LeaveAppSet;
import com.wgsoft.common.action.BaseAction;
import com.wgsoft.system.iservice.ISystemApproveAuthorityService;

/**
 * @title： SystemApproveAuthorityAction.java
 * @desc： 系统审核权限修改
 * @author： Willowgao
 * @date： 2015-11-17 下午05:29:05
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class SystemApproveAuthorityAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2928223153135180205L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc:查询权限设置信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-19 下午03:04:34
	 */
	public String queryApproves() throws Exception {
		List<LeaveAppSet> list = getSystemApproveAuthorityService().queryApproves(new HashMap<String, Object>());
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	public String saveApproves() throws Exception {
		String jsonStr = ((String[]) request.getParameterMap().get("leaveAppSet.datagrid"))[0];
		Map<String, List<LeaveAppSet>> jsonMap = getListFromMap(jsonStr, new LeaveAppSet());
		int rel = getSystemApproveAuthorityService().saveSetting(jsonMap);
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	private ISystemApproveAuthorityService getSystemApproveAuthorityService() {
		return (ISystemApproveAuthorityService) getService("systemApproveAuthorityService");
	}

	private LeaveAppSet leaveAppSet;

	public LeaveAppSet getLeaveAppSet() {
		return leaveAppSet;
	}

	public void setLeaveAppSet(LeaveAppSet leaveAppSet) {
		this.leaveAppSet = leaveAppSet;
	}

}
