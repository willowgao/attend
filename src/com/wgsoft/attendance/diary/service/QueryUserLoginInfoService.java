package com.wgsoft.attendance.diary.service;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.diary.idao.IQueryUserLoginInfoDao;
import com.wgsoft.attendance.diary.iservice.IQueryUserLoginInfoService;
import com.wgsoft.attendance.diary.model.UserLoginInfo;

/**
 * @title： QueryUserLoginInfoService.java
 * @desc：
 * @author： Willowgao
 * @date： 2015-11-17 上午09:10:54
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryUserLoginInfoService implements IQueryUserLoginInfoService {

	private IQueryUserLoginInfoDao queryDao;

	public List<UserLoginInfo> getLoginInfos(Map<String, String> queryMap) {
		return queryDao.getLoginInfos(queryMap);
	}

	public IQueryUserLoginInfoDao getQueryDao() {
		return queryDao;
	}

	public void setQueryDao(IQueryUserLoginInfoDao queryDao) {
		this.queryDao = queryDao;
	}

}
