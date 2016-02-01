package com.wgsoft.diary.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.diary.model.DiaryDaily;

/**
 * @title： IDiaryApproveDao.java
 * @desc：日志审核
 * @author： Willowgao
 * @date： 2015-11-11 下午08:31:58
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IDiaryApproveDao extends IBaseDao {

	/**
	 * @desc: 查询需要审核的日志记录
	 * @param queryMap
	 * @return
	 * @return List<DiaryDaily>
	 * @date： 2015-11-11 下午08:33:30
	 */
	List<DiaryDaily> getDiarysForApprove(Map<String, Object> queryMap);

	/**
	 * @desc: 更新待核审的记录状态
	 * @param diaryDaily
	 * @return int
	 * @date： 2015-11-11 下午08:34:41
	 */
	int updateStatus(DiaryDaily diaryDaily);
}
