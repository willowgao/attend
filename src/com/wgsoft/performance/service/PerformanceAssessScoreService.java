package com.wgsoft.performance.service;

import java.util.List;
import java.util.Map;

import com.wgsoft.performance.idao.IPerformanceAssessScoreDao;
import com.wgsoft.performance.iservice.IPerformanceAssessScoreService;
import com.wgsoft.performance.model.PerformanceAssessScore;

public class PerformanceAssessScoreService implements
		IPerformanceAssessScoreService {

	private IPerformanceAssessScoreDao performanceAssessScoreDao;

	public List<PerformanceAssessScore> queryScores(Map<String, Object> queryMap) {
		return performanceAssessScoreDao.queryScores(queryMap);
	}

	public int saveAssessScore(Map<String, Object> saveMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	public IPerformanceAssessScoreDao getPerformanceAssessScoreDao() {
		return performanceAssessScoreDao;
	}

	public void setPerformanceAssessScoreDao(
			IPerformanceAssessScoreDao performanceAssessScoreDao) {
		this.performanceAssessScoreDao = performanceAssessScoreDao;
	}

}
