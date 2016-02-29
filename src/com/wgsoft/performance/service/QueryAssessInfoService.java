package com.wgsoft.performance.service;

import java.util.List;
import java.util.Map;

import com.wgsoft.diary.model.EchartsOfBar;
import com.wgsoft.performance.idao.IQueryAssessInfoDao;
import com.wgsoft.performance.iservice.IQueryAssessInfoService;
import com.wgsoft.performance.model.PerformanceAssessScore;

/**
 * @title： QueryAssessInfoService.java
 * @desc： 考核情况查询
 * @author： Willowgao
 * @date： 2016-2-25 下午02:52:45
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryAssessInfoService implements IQueryAssessInfoService {

	private IQueryAssessInfoDao queryAssessInfoDao;

	/**
	 * @see com.wgsoft.performance.iservice.IQueryAssessInfoService#queryAssessForList(Map)
	 */
	public List<PerformanceAssessScore> queryAssessForList(Map<String, Object> queryMap) {
		return queryAssessInfoDao.queryAssessForList(queryMap);
	}

	/**
	 * @see com.wgsoft.performance.iservice.IQueryAssessInfoService#queryOrgRanking(Map)
	 */
	public List<EchartsOfBar> queryOrgRanking(Map<String, Object> queryMap) {
		return queryAssessInfoDao.queryOrgRanking(queryMap);
	}

	public IQueryAssessInfoDao getQueryAssessInfoDao() {
		return queryAssessInfoDao;
	}

	public void setQueryAssessInfoDao(IQueryAssessInfoDao queryAssessInfoDao) {
		this.queryAssessInfoDao = queryAssessInfoDao;
	}

}
