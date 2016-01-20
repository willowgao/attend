package com.wgsoft.attendance.diary.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.diary.idao.IQueryUserLoginInfoDao;
import com.wgsoft.attendance.diary.model.UserLoginInfo;
import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;

public class QueryUserLoginInfoDao extends BaseDao implements IQueryUserLoginInfoDao {

	@SuppressWarnings("unchecked")
	public List<UserLoginInfo> getLoginInfos(Map<String, String> queryMap) {
		String startTime = queryMap.get("startTime");
		String endTime = queryMap.get("endTime");
		StringBuffer sql = new StringBuffer(" select * from user_login_info where 1=1");
		if (RunUtil.isNotEmpty(startTime)) {
			sql.append(" and to_char(logintime,'yyyy-mm-dd') >= '").append(startTime).append("'");
		}
		if (RunUtil.isNotEmpty(endTime)) {
			sql.append(" and to_char(logintime,'yyyy-mm-dd') <= '").append(endTime).append("'");
		}
		sql.append("order by logintime desc Nulls Last");
		return getSqlList_(sql.toString(), UserLoginInfo.class);
	}

}
