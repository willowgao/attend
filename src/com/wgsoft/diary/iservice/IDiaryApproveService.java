package com.wgsoft.diary.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.diary.model.DiaryDaily;

/**
 * @title： IDiaryApproveService.java
 * @desc： 日志审核
 * @author： Willowgao
 * @date： 2015-11-11 下午08:38:34
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IDiaryApproveService {

	/**
	 * @desc: 查询需要审核的日志记录
	 * @param queryMap
	 * @return
	 * @return List<DiaryDaily>
	 * @date： 2015-11-11 下午08:33:30
	 */
	List<DiaryDaily> getDiarysForApprove(Map<String, Object> queryMap);

	/**
	 * @desc: 核审日志
	 * @param diaryDaily
	 * @return int
	 * @date： 2015-11-11 下午08:34:41
	 */
	int approveDiary(DiaryDaily diaryDaily);

}
