package com.wgsoft.performance.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.diary.model.DiaryDaily;
import com.wgsoft.performance.iservice.IQueryDiarysService;
import com.wgsoft.user.model.UserInfo;

/**
 * @title： QueryDiarysAction.java
 * @desc：查询日志信息
 * @author： Willowgao
 * @date： 2016-3-9 下午04:11:01
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryDiarysAction extends BaseAction {

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc:日志信息查询
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-3-9 下午04:12:28
	 */
	public String queryDiarys() throws Exception {
		UserInfo user = getUserInfo();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (diaryDaily == null) {
			diaryDaily = new DiaryDaily();
			if (RunUtil.isNotEmpty(request.getParameter("starttime"))) {
				diaryDaily.setStarttime(DateUtil.string2Date(request.getParameter("starttime"), DateUtil.YMD));
			}
			if (RunUtil.isNotEmpty(request.getParameter("endtime"))) {
				diaryDaily.setEndtime(DateUtil.string2Date(request.getParameter("endtime"), DateUtil.YMD));
			}
			if (RunUtil.isNotEmpty(request.getParameter("userid"))) {
				diaryDaily.setUserid((request.getParameter("userid")));
			}
			if (RunUtil.isNotEmpty(request.getParameter("deptid"))) {
				diaryDaily.setDeptid((request.getParameter("deptid")));
			}
			diaryDaily.setDiarytype(request.getParameter("diarytype"));
		}
		queryMap.put("org", user.getUserorg());
		queryMap.put("deptid", user.getUserdeptid());
		queryMap.put("diaryDaily", diaryDaily);

		List<DiaryDaily> diarys = getQueryDiarysService().queryDiarys(queryMap);
		renderText(response, transferListToJsonMapForTabel(diarys));
		return null;
	}

	private DiaryDaily diaryDaily;

	public DiaryDaily getDiaryDaily() {
		return diaryDaily;
	}

	public void setDiaryDaily(DiaryDaily diaryDaily) {
		this.diaryDaily = diaryDaily;
	}

	private IQueryDiarysService getQueryDiarysService() {
		return (IQueryDiarysService) getService("queryDiarysService");
	}
}
