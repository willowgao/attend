package com.wgsoft.diary.action;

import java.util.List;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.diary.iservice.IDiaryDailyService;
import com.wgsoft.diary.model.DiaryDaily;

/**
 * @title： DiaryDailyAction.java
 * @desc： 日报填写提交
 * @author： Willowgao
 * @date： 2015-11-3 下午01:09:59
 * @version： V1.0<br>
 * @versioninfo：慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class DiaryDailyAction extends BaseAction {

	private IDiaryDailyService diaryDailyService = (IDiaryDailyService) getService("diaryDailyService");
	/**
	 * 
	 */
	private static final long serialVersionUID = -6345518597967748847L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 周报页面
	 */
	public String weekly() throws Exception {
		return "weekly";
	}

	/**
	 * @desc: 初始化form表单数据
	 * @throws Exception
	 * @return void
	 * @date： 2015-11-11 下午08:20:45
	 */
	public void initDiary() throws Exception {
		if (diaryDaily == null) {
			diaryDaily = new DiaryDaily();
		}
		diaryDaily.setUserid(getUserInfo().getUserid());
		// 查询是否已经存在申报过的记录
		List<DiaryDaily> list = diaryDailyService.getDiarys(true, diaryDaily);
		if (list != null && list.size() > 0) {
			diaryDaily = list.get(0);
			renderText(response, transferVoToForm("diaryDaily", diaryDaily, DiaryDaily.class));
		}
	}

	/**
	 * 月报页面
	 */
	public String monthly() throws Exception {
		return "monthly";
	}

	/**
	 *季报页面
	 */
	public String quarterly() throws Exception {
		return "quarterly";
	}

	/**
	 * @desc:查询历史数据
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-11 上午11:42:59
	 */
	public String queryDiarys() throws Exception {
		if (diaryDaily != null) {
			diaryDaily.setDiarydate(null);
			diaryDaily.setStarttime(null);
			diaryDaily.setEndtime(null);
		} else {
			diaryDaily = new DiaryDaily();
			diaryDaily.setDiarytype(request.getParameter("diarytype"));
			diaryDaily.setStarttime(DateUtil.string2Date(request.getParameter("starttime").toString(), DateUtil.YMD));
			diaryDaily.setEndtime(DateUtil.string2Date(request.getParameter("endtime").toString(), DateUtil.YMD));
		}
		diaryDaily.setUserid(getUserInfo().getUserid());
		List<DiaryDaily> list = diaryDailyService.getDiarys(false, diaryDaily);
		renderText(response, super.transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:保存日志信息
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-9 下午01:24:36
	 */
	public String save() throws Exception {
		diaryDaily.setUserid(getUserInfo().getUserid());

		// 查询是否已经存在申报过的记录
		List<DiaryDaily> list = diaryDailyService.getDiarys(true, diaryDaily);
		int rel = SysConstants.SUCCESS;
		if (list != null && list.size() > 0) {
			DiaryDaily diary = list.get(0);
			// 如果已经存在的状态不为申报状态、也不为审核不通过状态
			if (!diary.getStatus().equals(SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE)
					&& !diary.getStatus().equals(SysConstants.ApproverStatus.APPROVER_STATUS_NOTPASS)) {
				// 说明状态已经被审核，不能修改
				rel = SysConstants.ERROR;
			} else {
				diaryDaily.setDiaryid(diary.getDiaryid());
				diaryDaily.setComments(diary.getComments());
				diaryDaily.setViewcount(diary.getViewcount());
				diaryDaily.setStatus(diary.getStatus());
				diaryDaily.setDiarydate(diary.getDiarydate());
				diaryDaily.setStarttime(diary.getStarttime());
				diaryDaily.setEndtime(diary.getEndtime());
				rel = diaryDailyService.updateDiary(diaryDaily);
			}
		} else {
			if (diaryDaily.getDiarytype().equals(SysConstants.DiaryType.DIARY_TYPE_DAILY)) {
				diaryDaily.setStarttime(diaryDaily.getDiarydate());
				diaryDaily.setEndtime(diaryDaily.getDiarydate());
			}
			rel = diaryDailyService.saveDiaryDailServier(diaryDaily);
		}
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	private DiaryDaily diaryDaily;

	public DiaryDaily getDiaryDaily() {
		return diaryDaily;
	}

	public void setDiaryDaily(DiaryDaily diaryDaily) {
		this.diaryDaily = diaryDaily;
	}

}
