package com.wgsoft.diary.dao;

import java.util.List;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.diary.idao.IDiaryDailyDao;
import com.wgsoft.diary.model.DiaryDaily;

/**
 * @title： DiaryDailyDao.java
 * @desc：日报填写提交审核
 * @author： Willowgao
 * @date： 2015-11-3 下午03:21:29
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
@SuppressWarnings("unchecked")
public class DiaryDailyDao extends BaseDao implements IDiaryDailyDao {

	/**
	 * @see com.wgsoft.diary.management.idao.IDiaryDailyDao#getDiarys(boolean,
	 *      DiaryDaily)
	 */
	public List<DiaryDaily> getDiarys(boolean ischeck, DiaryDaily diary) {
		StringBuffer sql = new StringBuffer("SELECT * FROM DIARY_DAILY WHERE 1=1");
		if (!ischeck) {
			if (RunUtil.isNotEmpty(diary.getStarttime())) {
				sql.append(" and (to_char(diarydate,'yyyy-mm-dd') >= '").append(
						DateUtil.date2String(diary.getStarttime(), DateUtil.YMD)).append("'");
			}
			if (RunUtil.isNotEmpty(diary.getEndtime())) {
				sql.append(" and to_char(diarydate,'yyyy-mm-dd') <= '").append(
						DateUtil.date2String(diary.getEndtime(), DateUtil.YMD)).append("')");
			}
		} else {
			if (RunUtil.isNotEmpty(diary.getStarttime()) || RunUtil.isNotEmpty(diary.getEndtime())) {
				sql.append(" and (to_char(starttime,'yyyy-mm-dd') <= '").append(
						DateUtil.getNowDateByFormat(DateUtil.YMD)).append("'");
				sql.append(" and to_char(endTime,'yyyy-mm-dd') >= '").append(
						DateUtil.getNowDateByFormat(DateUtil.YMD)).append("')");
			}
		}
		if (RunUtil.isNotEmpty(diary.getDiarytype())) {
			sql.append(" and  diarytype = '").append(diary.getDiarytype()).append("'");
		}
		if (RunUtil.isNotEmpty(diary.getUserid())) {
			sql.append(" and  userid = '").append(diary.getUserid()).append("'");
		}
		if (RunUtil.isNotEmpty(diary.getDiarydate())) {
			sql.append(" and  to_char(diarydate,'yyyy-mm-dd') = '").append(
					DateUtil.date2String(diary.getDiarydate(), DateUtil.YMD)).append("'");
		}
		sql.append(" order by diarydate desc");
		return getSqlList_(sql.toString(), DiaryDaily.class);
	}

}
