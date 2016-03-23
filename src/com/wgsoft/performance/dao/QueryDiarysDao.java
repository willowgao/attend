package com.wgsoft.performance.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.diary.model.DiaryDaily;
import com.wgsoft.performance.idao.IQueryDiarysDao;
import com.wgsoft.user.model.UserInfo;

/**
 * @title： QueryDiarysDao.java
 * @desc：
 * @author： Willowgao
 * @date： 2016-3-9 下午04:16:19
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryDiarysDao extends BaseDao implements IQueryDiarysDao {
	/**
	 * @see com.wgsoft.performance.idao.IQueryDiarysDao#queryDiarys(Map)
	 */
	public List<DiaryDaily> queryDiarys(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT * FROM DIARY_DAILY A ,USERINFO B WHERE A.USERID = B.USERID ");
		UserInfo user = (UserInfo) queryMap.get("user");
		if (RunUtil.isNotEmpty(queryMap.get("userid"))) {
			sql.append(" AND A.userid ='").append(queryMap.get("userid")).append("' ");
		}
		if (RunUtil.isNotEmpty(queryMap.get("deptid"))) {
			sql.append(" AND b.userdeptid ='").append(queryMap.get("deptid")).append("' ");
		}
		if (RunUtil.isNotEmpty(queryMap.get("approverid"))) {
			sql.append(" AND approverid ='").append(queryMap.get("approverid")).append("' ");
		}
		if (RunUtil.isNotEmpty(queryMap.get("status"))) {
			sql.append(" AND status ='").append(queryMap.get("status")).append("' ");
		}
		if (RunUtil.isNotEmpty(queryMap.get("diarytype"))) {
			sql.append(" AND diarytype ='").append(queryMap.get("diarytype")).append("' ");
		}
		if (RunUtil.isNotEmpty(queryMap.get("starttime"))) {
			sql.append(" AND diarydate >= to_date('").append(queryMap.get("starttime")).append("','yyyy-mm-dd') ");
		}
		if (RunUtil.isNotEmpty(queryMap.get("endtime"))) {
			sql.append(" AND diarydate <= to_date('").append(queryMap.get("endtime")).append("','yyyy-mm-dd') ");
		}
		sql.append("  and rownum < 31");
		return getSqlList_(sql.toString(), DiaryDaily.class);
	}

}
