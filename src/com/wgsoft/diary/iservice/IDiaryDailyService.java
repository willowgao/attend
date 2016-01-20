package com.wgsoft.diary.iservice;

import java.util.List;

import com.wgsoft.diary.model.DiaryComments;
import com.wgsoft.diary.model.DiaryDaily;

/**
 * 
 * @title： IDiaryDailyService.java
 * @desc：日报填写提交审核
 * @author： Willowgao
 * @date： 2015-11-3 下午02:43:17
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IDiaryDailyService {

	/**
	 * @desc: 保存日志信息
	 * @param diary
	 * @return
	 * @return int
	 * @date： 2015-11-3 下午02:44:18
	 */
	int saveDiaryDailServier(DiaryDaily diary);

	/**
	 * @desc:保存日志评论信息
	 * @param comments
	 * @return
	 * @return int
	 * @date： 2015-11-3 下午02:44:46
	 */
	int saveDiaryComments(DiaryComments comments);

	/**
	 * @desc:查询日志信息 </br> ischeck:true 则为校验是否存在,</br> false:只是查询历史记录
	 * @param ischeck
	 *            boolean
	 * @param diary
	 *            DiaryDaily
	 * @return
	 * @return List<DiaryDaily>
	 * @date： 2015-11-11 下午01:00:56
	 */
	List<DiaryDaily> getDiarys(boolean ischeck, DiaryDaily diary);

	/**
	 * @desc: 更新日志信息
	 * @param diary
	 * @return
	 * @return int
	 * @date： 2015-11-9 下午07:36:41
	 */
	int updateDiary(DiaryDaily diary);

}
