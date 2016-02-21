package com.wgsoft.performance.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.performance.model.PerformanceAssessScore;

/**
 * 
 * @author Administrator
 * 
 */
public interface IPerformanceAssessScoreService {

	/**
	 * @param queryMap
	 * @return
	 */
	List<PerformanceAssessScore> queryScores(Map<String, Object> queryMap);

	/**
	 * @param saveMap
	 * @return
	 */
	int saveAssessScore(Map<String, Object> saveMap);
}
