package com.wgsoft.diary.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.diary.iservice.IDiaryDiscussEachOtherService;
import com.wgsoft.diary.model.DiaryComments;
import com.wgsoft.diary.model.DiaryDaily;
import com.wgsoft.user.model.UserInfo;

/**
 * @title： DiaryDiscussEachOtherAction.java
 * @desc： 日志互评
 * @author： Willowgao
 * @date： 2015-11-12 上午11:55:02
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class DiaryDiscussEachOtherAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7436302980900559413L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc: 查询所有日志
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-12 上午11:56:12
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
			diaryDaily.setDiarytype(request.getParameter("diarytype"));
		}
		diaryDaily.setUserid(user.getUserid());
		queryMap.put("org", user.getUserorg());
		queryMap.put("deptid", user.getUserdeptid());
		queryMap.put("diaryDaily", diaryDaily);
		List<DiaryDaily> diarys = getDiaryDiscussService().getDiarysForDiscuss(queryMap);
		renderText(response, transferListToJsonMapForTabel(diarys));
		return null;
	}

	/**
	 * @desc:页面跳转
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-12 下午03:54:12
	 */
	public String goDetail() throws Exception {
		request.setAttribute("content", request.getParameter("diaryDaily.content"));
		request.setAttribute("nextcontent", request.getParameter("diaryDaily.nextcontent"));
		return "detail";
	}

	/**
	 * @desc:跳转到评论页面
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-12 下午03:52:26
	 */
	public String goDiscussDetail() throws Exception {
		String diaryId = request.getParameter("diaryDaily.diaryid");
		List<DiaryComments> comments = getDiaryDiscussService().getDiaryCommentsById(diaryId);
		request.setAttribute("diaryid", diaryId);
		request.setAttribute("username", getUserInfo().getUsername());
		request.setAttribute("comments", comments);
		return "discuss";
	}

	/**
	 * @desc: 增加评论信息
	 * @return
	 * @return String
	 * @date： 2015-11-12 上午11:58:10
	 */
	public String discussDiarys() throws Exception {
		String comment = request.getParameter("comments");
		String diaryid = request.getParameter("diaryid");
		DiaryComments comments = new DiaryComments();
		comments.setCommdate(new Date());
		comments.setComments(comment);
		comments.setDiaryid(diaryid);
		comments.setUserid(getUserInfo().getUserid());
		String rel = getDiaryDiscussService().addDiscuss(comments);
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	/**
	 * @desc:删除日志评论信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-13 上午09:06:17
	 */
	public String delDiarysComments() throws Exception {
		String commentid = request.getParameter("commentid");
		int rel = getDiaryDiscussService().delComments(commentid);
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}
	/**
	 * @desc:更新日志查阅信息
	 * @return
	 * @throws Exception 
	 * @return String
	 * @date： 2015-11-13 上午09:06:28
	 */
	public String addViewCount() throws Exception {
		String diaryid = request.getParameter("diaryid");
		int rel = getDiaryDiscussService().addViewCount(diaryid);
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

	private IDiaryDiscussEachOtherService getDiaryDiscussService() {
		return (IDiaryDiscussEachOtherService) getService("diaryDiscussEachOtherService");
	}

}
