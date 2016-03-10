package com.wgsoft.diary.service;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.diary.idao.IDiaryApproveDao;
import com.wgsoft.diary.idao.IDiaryDiscussEachOtherDao;
import com.wgsoft.diary.iservice.IDiaryDiscussEachOtherService;
import com.wgsoft.diary.model.DiaryComments;
import com.wgsoft.diary.model.DiaryDaily;

/**
 * @title： DiaryDiscussEachOtherService.java
 * @desc： 日志互评
 * @author： Willowgao
 * @date： 2015-11-12 下午12:40:15
 * @version： V1.0<br>
 * @versioninfo：慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class DiaryDiscussEachOtherService implements IDiaryDiscussEachOtherService {

	private String isAll;
	/**
	 * @see com.wgsoft.diary.idao.IDiaryApproveDao
	 */
	private IDiaryApproveDao diaryApproveDao;
	/**
	 * @see com.wgsoft.diary.idao.IDiaryDiscussEachOtherDao
	 */
	private IDiaryDiscussEachOtherDao diaryDiscussEachOtherDao;

	/**
	 * @see com.wgsoft.diary.iservice.IDiaryDiscussEachOtherService#getDiarysForDiscuss(Map)
	 */
	public List<DiaryDaily> getDiarysForDiscuss(Map<String, Object> queryMap) {
		queryMap.put("isAll", isAll);
		DiaryDaily diaryDaily = (DiaryDaily) queryMap.get("diaryDaily");
		queryMap.put("diaryid", diaryDaily.getDiaryid());
		queryMap.put("startTime", DateUtil.date2String(diaryDaily.getStarttime(), DateUtil.YMD));
		queryMap.put("endTime", DateUtil.date2String(diaryDaily.getEndtime(), DateUtil.YMD));
		queryMap.put("diarytype", diaryDaily.getDiarytype());
		queryMap.put("userid", diaryDaily.getUserid());
		queryMap.put("onsubmit", "1");
		return diaryApproveDao.getDiarysForApprove(queryMap);
	}

	public List<DiaryComments> getDiaryCommentsById(String id) {
		return diaryDiscussEachOtherDao.getDiaryCommentsById(id);
	}

	/**
	 * @see com.wgsoft.diary.iservice.IDiaryDiscussEachOtherService#addDiscuss(DiaryComments)
	 */
	public String addDiscuss(DiaryComments comments) {
		String commentid = null;
		try {
			diaryDiscussEachOtherDao.insert(comments);
		} catch (Exception e) {
			return commentid;
		}//
		commentid = comments.getCommentid();
		return commentid;
	}

	/**
	 * @see com.wgsoft.diary.iservice.IDiaryDiscussEachOtherService#delComments(String)
	 */
	public int delComments(String id) {
		return diaryDiscussEachOtherDao.delComments(id);
	}

	/**
	 * @see com.wgsoft.diary.iservice.IDiaryDiscussEachOtherService#addViewCount(String)
	 */
	public int addViewCount(String id) {
		return diaryDiscussEachOtherDao.addViewCount(id);
	}

	public IDiaryApproveDao getDiaryApproveDao() {
		return diaryApproveDao;
	}

	public void setDiaryApproveDao(IDiaryApproveDao diaryApproveDao) {
		this.diaryApproveDao = diaryApproveDao;
	}

	public IDiaryDiscussEachOtherDao getDiaryDiscussEachOtherDao() {
		return diaryDiscussEachOtherDao;
	}

	public void setDiaryDiscussEachOtherDao(IDiaryDiscussEachOtherDao diaryDiscussEachOtherDao) {
		this.diaryDiscussEachOtherDao = diaryDiscussEachOtherDao;
	}

	public String getIsAll() {
		return isAll;
	}

	public void setIsAll(String isAll) {
		this.isAll = isAll;
	}

}
