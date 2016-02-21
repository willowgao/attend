package com.wgsoft.performance.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.performance.model.PerformanceAssessScore;

public interface IPerformanceAssessScoreDao extends IBaseDao {

	/**
	 * @param queryMap
	 * @return
	 */
	public List<PerformanceAssessScore> queryScores(Map<String, Object> queryMap);
}
