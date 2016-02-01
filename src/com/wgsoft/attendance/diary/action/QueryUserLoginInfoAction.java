package com.wgsoft.attendance.diary.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.diary.iservice.IQueryUserLoginInfoService;
import com.wgsoft.attendance.diary.model.UserLoginInfo;
import com.wgsoft.common.action.BaseAction;

/**
 * 
 * @title： QueryUserLoginInfoAction.java
 * @desc：
 * @author： Willowgao
 * @date： 2015-9-9 下午01:48:12
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryUserLoginInfoAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6771049015363446373L;

	private IQueryUserLoginInfoService queryUserLoginInfoService = (IQueryUserLoginInfoService) getService("queryUserLoginInfoService");

	public String queryLogins() throws Exception {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("startTime", startTime);
		queryMap.put("endTime", endTime);
		List<UserLoginInfo> loginRecords = queryUserLoginInfoService.getLoginInfos(queryMap);
		this.renderText(response, transferListToJsonMapForTabel(loginRecords));
		return null;
	}
}
