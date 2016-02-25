package com.wgsoft.diary.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.diary.idao.IDiaryApproveDao;
import com.wgsoft.diary.model.DiaryDaily;

/**
 * @title： DiaryApproveDao.java
 * @desc：日志审核
 * @author： Willowgao
 * @date： 2015-11-11 下午08:36:55
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
@SuppressWarnings("unchecked")
public class DiaryApproveDao extends BaseDao implements IDiaryApproveDao {

	/**
	 * @see com.wgsoft.diary.idao.IDiaryApproveDao#getDiarysForApprove(Map)
	 */
	public List<DiaryDaily> getDiarysForApprove(final Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT a.*,nvl((select count(1) from diary_comments b where a.diaryid = b.diaryid),0) commentcount FROM DIARY_DAILY a WHERE 1=1");
		if (RunUtil.isNotEmpty(queryMap.get("userid"))) {
			sql.append(" AND USERID ='").append(queryMap.get("userid")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("approverid"))) {
			sql.append(" AND approverid ='").append(queryMap.get("approverid")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("diaryid"))) {
			sql.append(" AND diaryid ='").append(queryMap.get("diaryid")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("startTime"))) {
			sql.append(" and (to_char(diarydate,'yyyy-mm-dd') >= '").append(queryMap.get("startTime")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("endTime"))) {
			sql.append(" and to_char(diarydate,'yyyy-mm-dd') <= '").append(queryMap.get("endTime")).append("')");
		}
		if (RunUtil.isNotEmpty(queryMap.get("diarytype"))) {
			sql.append(" and diarytype = '").append(queryMap.get("diarytype")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("status"))) {
			sql.append(" and status = '").append(queryMap.get("status")).append("'");
		}

		if (RunUtil.isNotEmpty(queryMap.get("onsubmit"))) {
			sql.append(" and onsubmit = '").append(queryMap.get("onsubmit")).append("'");
		}
		
		// 如果isAll为1则查询所有，如果为2则只查询本部门
		if (RunUtil.isNotEmpty(queryMap.get("isAll")) && queryMap.get("isAll").equals("2")) {
			sql.append(" AND USERID IN (SELECT USERID FROM USERINFO WHERE USERDEPT = '").append(queryMap.get("deptid"))
					.append("')");
		}
		// 如果isAll为1则查询所有，如果为2则只查询本部门
		if (RunUtil.isNotEmpty(queryMap.get("isAll")) && queryMap.get("isAll").equals("2")) {
			sql.append(" AND USERID IN (SELECT USERID FROM USERINFO WHERE userorg = '").append(queryMap.get("org"))
					.append("')");
		}
		
		sql.append(" order by diarydate desc");
		return getSqlList_(sql.toString(), DiaryDaily.class);
	}

	/**
	 * @see com.wgsoft.diary.idao.IDiaryApproveDao#updateStatus(DiaryDaily)
	 */
	public int updateStatus(final DiaryDaily diaryDaily) {
		StringBuffer sql = new StringBuffer(" UPDATE DIARY_DAILY SET STATUS ='").append(diaryDaily.getStatus()).append(
				"',");
		sql.append("   approverid ='").append(diaryDaily.getApproverid()).append("'");
		sql.append("  , comments ='").append(diaryDaily.getComments()).append("'");
		sql.append(" WHERE DIARYID ='").append(diaryDaily.getDiaryid()).append("'");
		return getSqlUpdate(sql.toString());
	}

}
