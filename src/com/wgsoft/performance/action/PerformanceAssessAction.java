package com.wgsoft.performance.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.performance.iservice.IPerformanceAssessService;
import com.wgsoft.performance.model.PerformanceAssess;
import com.wgsoft.user.iservice.IUserService;
import com.wgsoft.user.model.UserInfo;

/**
 * @title： PerformanceIndexManageAction.java
 * @desc： 考核评分
 * @author： Willowgao
 * @date： 2016-1-25 上午09:12:02
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PerformanceAssessAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6756113475070331197L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc:查询考核指标明细信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-23 下午02:22:44
	 */
	public String queryUsers() throws Exception {
		UserInfo user = new UserInfo();
		// 查询本部门的人员
		user.setUserdeptid(getUserInfo().getUserdeptid());
		Map<String, Object> requestMap = request.getParameterMap();
		if (RunUtil.isNotEmpty(((String[]) requestMap.get("assess.deptid"))[0])) {
			user.setUserdeptid(((String[]) requestMap.get("assess.deptid"))[0]);
		} else {
			user.setUserdeptid(getUserInfo().getUserdeptid());
		}
		List<UserInfo> list = getUserService().getUserInfoByUserInfo(user);
		int j = 99999;
		for (int i = 0; i < list.size(); i++) {
			UserInfo me = list.get(i);
			if (me.getUserid().equals(getUserInfo().getUserid())) {
				// 不包括自己
				j = i;
				break;
			}
		}
		if (j != 99999) {
			// 移除自己
			list.remove(j);
		}
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:查询是否存在此人的被打过的考核记录
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-3-2 下午03:33:58
	 */
	public String queryAssess() throws Exception {
		Map<String, Object> requestMap = request.getParameterMap();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (RunUtil.isNotEmpty(((String[]) requestMap.get("assess.starttime"))[0])) {
			queryMap.put("starttime", ((String[]) requestMap.get("assess.starttime"))[0]);
		}
		if (RunUtil.isNotEmpty(((String[]) requestMap.get("assess.endtime"))[0])) {
			queryMap.put("endtime", ((String[]) requestMap.get("assess.endtime"))[0]);
		}
		if (RunUtil.isNotEmpty(((String[]) requestMap.get("assess.userid"))[0])) {
			queryMap.put("userid", ((String[]) requestMap.get("assess.userid"))[0]);
		}
		queryMap.put("assesser", getUserInfo().getUserid());
		List<PerformanceAssess> list = getPerformanceAssessService().queryAssess(queryMap);
		renderText(response, JSONUtil.serialize(list.size()));
		return null;
	}

	/**
	 * @desc:保存考核打分
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-1-27 下午02:09:46
	 */
	@SuppressWarnings("unchecked")
	public String save() throws Exception {
		if (assess == null) {
			assess = new PerformanceAssess();
		}
		Map<String, Object> requestMap = request.getParameterMap();
		assess.setStarttime(DateUtil.string2Date(((String[]) requestMap.get("assess.starttime"))[0], DateUtil.YMD));
		assess.setEndtime(DateUtil.string2Date(((String[]) requestMap.get("assess.endtime"))[0], DateUtil.YMD));
		assess.setUserid(((String[]) requestMap.get("assess.userid"))[0]);
		assess.setRoletype(((String[]) requestMap.get("assess.roletype"))[0]);

		if (RunUtil.isNotEmpty(((String[]) requestMap.get("assess.deptid"))[0])) {
			assess.setDeptid(((String[]) requestMap.get("assess.deptid"))[0]);
		} else {
			assess.setDeptid(getUserInfo().getUserdeptid());
		}

		Map<String, Object> saveMap = new HashMap<String, Object>();
		saveMap.put("assess", assess);
		// 用户信息
		saveMap.put("user", getUserInfo());
		// 列表信息
		String jsonStr = ((String[]) request.getParameterMap().get("assess.datagrid"))[0];
		Map<String, List<PerformanceAssess>> assessIndexs = getListFromMap(jsonStr, new PerformanceAssess());
		saveMap.put("assessIndexs", assessIndexs);
		int rel = getPerformanceAssessService().saveAssess(saveMap);
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	private PerformanceAssess assess;

	public PerformanceAssess getAssess() {
		return assess;
	}

	public void setAssess(PerformanceAssess assess) {
		this.assess = assess;
	}

	public IUserService getUserService() {
		return (IUserService) getService("userService");
	}

	public IPerformanceAssessService getPerformanceAssessService() {
		return (IPerformanceAssessService) getService("performanceAssessService");
	}

}
