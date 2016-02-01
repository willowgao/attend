package com.wgsoft.diary.idao;

import java.util.List;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.diary.model.DiaryDaily;

/**
 * @title： IDiaryDailyDao.java
 * @desc：日报填写提交审核
 * @author： Willowgao
 * @date： 2015-11-3 下午03:19:29
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IDiaryDailyDao extends IBaseDao {

	/**
	 * @desc:查询日志信息
	 * @param ischeck
	 *            boolean
	 * @param diary
	 *            DiaryDaily
	 * @return
	 * @return List<DiaryDaily>
	 * @date： 2015-11-11 下午01:00:56
	 */
	List<DiaryDaily> getDiarys(boolean ischeck, DiaryDaily diary);
}
