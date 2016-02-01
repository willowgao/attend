package com.wgsoft.diary.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.diary.iservice.IDiaryApproveService;
import com.wgsoft.diary.model.DiaryDaily;

/**
 * 
 * @title： DiaryApproveAction.java
 * @desc： 日志审核
 * @author： Willowgao
 * @date： 2015-11-11 下午08:30:34
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class DiaryApproveAction extends BaseAction {

	private static final String FORMNAME = "diaryDaily";

	private IDiaryApproveService diaryApproveService = (IDiaryApproveService) getService("diaryApproveService");
	/**
	 * 
	 */
	private static final long serialVersionUID = 8040899060290599726L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void initForm() throws Exception {
		renderText(response, transferVoToForm(FORMNAME, diaryDaily, DiaryDaily.class));
	}

	/**
	 * @desc: 查询审核记录
	 * @throws Exception
	 * @return void
	 * @date： 2015-11-11 下午08:57:51
	 */
	public void queryDiarys() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (diaryDaily == null) {
			diaryDaily = new DiaryDaily();
			if (RunUtil.isNotEmpty(request.getParameter("starttime"))) {
				diaryDaily.setStarttime(DateUtil.string2Date(request.getParameter("starttime"), DateUtil.YMD));
			}
			if (RunUtil.isNotEmpty(request.getParameter("endtime"))) {
				diaryDaily.setEndtime(DateUtil.string2Date(request.getParameter("endtime"), DateUtil.YMD));
			}
			diaryDaily.setDiarytype(request.getParameter("diarytype"));
		}
		
		diaryDaily.setUserid(getUserInfo().getUserid());
		queryMap.put("diaryid", diaryDaily.getDiaryid());
		queryMap.put("startTime", diaryDaily.getStarttime());
		queryMap.put("endTime", diaryDaily.getEndtime());
		// queryMap.put("userid", diaryDaily.getUserid());
		queryMap.put("approverid", diaryDaily.getUserid());
		queryMap.put("diarytype", diaryDaily.getDiarytype());
		queryMap.put("status", SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE);
		List<DiaryDaily> diarys = diaryApproveService.getDiarysForApprove(queryMap);
		renderText(response, transferListToJsonMapForTabel(diarys));
	}

	/***
	 * @desc:日志核审
	 * @throws Exception
	 * @return void
	 * @date： 2015-11-12 上午09:10:46
	 */
	public void appDiarys() throws Exception {
		String diaryUserId = request.getParameter("userid");
		String diarytype = request.getParameter("diarytype");
		String comments = request.getParameter("comments");
		String status = request.getParameter("status");

		diaryDaily.setUserid(diaryUserId);
		diaryDaily.setComments(comments);
		diaryDaily.setDiarytype(diarytype);
		diaryDaily.setApproverid(getUserInfo().getUserid());
		diaryDaily.setStatus(status);
		int rel = diaryApproveService.approveDiary(diaryDaily);
		renderText(response, JSONUtil.serialize(rel));
	}

	/**
	 * @desc:
	 * @throws Exception
	 * @return void
	 * @date： 2015-11-12 上午09:17:00
	 */
	public void initAppStatus() throws Exception {
		Map<String, String> dataMap = getDataDictionaryService().getDataDictionarysByKey(
				SysConstants.ApproverStatus.DICTIONARY_KEY);
		Map<String, String> appMap = null;
		List<Map<String, String>> appStatus = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 2; i++) {
			appMap = new HashMap<String, String>();
			if (i == 0) {
				appMap.put("id", SysConstants.ApproverStatus.APPROVER_STATUS_NOTPASS);
				appMap.put("text", dataMap.get(SysConstants.ApproverStatus.APPROVER_STATUS_NOTPASS));
			} else {
				appMap.put("id", SysConstants.ApproverStatus.APPROVER_STATUS_PASS);
				appMap.put("text", dataMap.get(SysConstants.ApproverStatus.APPROVER_STATUS_PASS));
			}
			appStatus.add(appMap);
		}
		renderText(response, JSONUtil.serialize(appStatus));
	}

	private DiaryDaily diaryDaily;

	public DiaryDaily getDiaryDaily() {
		return diaryDaily;
	}

	public void setDiaryDaily(DiaryDaily diaryDaily) {
		this.diaryDaily = diaryDaily;
	}

}
