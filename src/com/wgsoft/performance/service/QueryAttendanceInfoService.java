package com.wgsoft.performance.service;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.diary.model.EchartsOfBar;
import com.wgsoft.diary.model.EchartsOfPie;
import com.wgsoft.performance.idao.IQueryAttendanceInfoDao;
import com.wgsoft.performance.iservice.IQueryAttendanceInfoService;

/**
 * @title： QueryAttendanceInfoService.java
 * @desc：查询出勤情况
 * @author： Willowgao
 * @date： 2016-2-2 上午09:31:29
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryAttendanceInfoService implements IQueryAttendanceInfoService {

	private IQueryAttendanceInfoDao queryAttendanceInfoDao;

	/**
	 * @see com.wgsoft.performance.iservice.IQueryAttendanceInfoService#queryAttend(Map)
	 */
	public List<ClockRecords> queryAttend(Map<String, Object> queryMap) {
		return queryAttendanceInfoDao.queryAttend(queryMap);
	}

	/**
	 * @see com.wgsoft.performance.iservice.IQueryAttendanceInfoService#queryOrgRanking(Map)
	 */
	public List<EchartsOfBar> queryOrgRanking(Map<String, Object> queryMap) {
		return queryAttendanceInfoDao.queryOrgRanking(queryMap);
	}
	
	/**
	 * @see com.wgsoft.performance.iservice.IQueryAttendanceInfoService#queryDeptRanking(Map)
	 */
	public List<EchartsOfPie> queryDeptRanking(Map<String, Object> queryMap) {
		return queryAttendanceInfoDao.queryDeptRanking(queryMap);
	}

	public IQueryAttendanceInfoDao getQueryAttendanceInfoDao() {
		return queryAttendanceInfoDao;
	}

	public void setQueryAttendanceInfoDao(IQueryAttendanceInfoDao queryAttendanceInfoDao) {
		this.queryAttendanceInfoDao = queryAttendanceInfoDao;
	}

}
