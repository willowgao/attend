package com.wgsoft.diary.service;

import java.util.Date;
import java.util.List;

import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.diary.idao.IDiaryDailyDao;
import com.wgsoft.diary.iservice.IDiaryDailyService;
import com.wgsoft.diary.model.DiaryComments;
import com.wgsoft.diary.model.DiaryDaily;

/**
 * @title： DiaryDailyService.java
 * @desc：日报填写提交审核
 * @author： Willowgao
 * @date： 2015-11-3 下午03:20:58
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class DiaryDailyService implements IDiaryDailyService {
	private IDiaryDailyDao diaryDailyDao;

	/**
	 * @see com.wgsoft.diary.management.iservice.IDiaryDailyService#saveDiaryComments(DiaryComments)
	 */
	public int saveDiaryComments(DiaryComments comments) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see com.wgsoft.diary.management.iservice.IDiaryDailyService#saveDiaryDailServier(DiaryDaily)
	 */
	public int saveDiaryDailServier(DiaryDaily diary) {
		try {
			diary.setStatus(SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE);
			if (RunUtil.isEmpty(diary.getDiarydate())) {
				diary.setDiarydate(new Date());
			}
			diaryDailyDao.insert(diary);
		} catch (Exception e) {
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

	/**
	 * @see com.wgsoft.diary.management.iservice.IDiaryDailyService#updateDiary(DiaryDaily)
	 */
	public int updateDiary(DiaryDaily diary) {
		try {
			diaryDailyDao.update(diary);
		} catch (Exception e) {
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

	/**
	 * @see com.wgsoft.diary.management.iservice.IDiaryDailyService#getDiarys(boolean,
	 *      DiaryDaily)
	 */
	public List<DiaryDaily> getDiarys(boolean ischeck, DiaryDaily diary) {
		return diaryDailyDao.getDiarys(ischeck, diary);
	}

	public IDiaryDailyDao getDiaryDailyDao() {
		return diaryDailyDao;
	}

	public void setDiaryDailyDao(IDiaryDailyDao diaryDailyDao) {
		this.diaryDailyDao = diaryDailyDao;
	}

}
