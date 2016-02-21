package com.wgsoft.performance.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.performance.idao.IPerformanceAssessScoreDao;
import com.wgsoft.performance.model.PerformanceAssessScore;

public class PerformanceAssessScoreDao extends BaseDao implements
		IPerformanceAssessScoreDao {

	public List<PerformanceAssessScore> queryScores(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT  A.USERID USERID, ROUND(SUM(DECODE(A.ASSESSTYPE, '1', SUM(NVL(INDEXSCORE, 0)), 0)) /");
		sql.append("  SUM(DECODE(A.ASSESSTYPE, '1', COUNT(DISTINCT A.ASSESSER), 0))) * .7 HIGHERSCORE,");
		sql.append("  ROUND(SUM(DECODE(A.ASSESSTYPE, '2', SUM(NVL(INDEXSCORE, 0)), 0)) /");
		sql.append("  SUM(DECODE(A.ASSESSTYPE, '2', COUNT(DISTINCT A.ASSESSER), 0))) * .3 PEERSCORE,");
		sql.append("  ROUND(SUM(DECODE(A.ASSESSTYPE, '1', SUM(NVL(INDEXSCORE, 0)), 0)) /");
		sql.append("  SUM(DECODE(A.ASSESSTYPE, '1', COUNT(DISTINCT A.ASSESSER), 0))) * .7 +");
		sql.append("  ROUND(SUM(DECODE(A.ASSESSTYPE, '2', SUM(NVL(INDEXSCORE, 0)), 0)) /");
		sql.append("  SUM(DECODE(A.ASSESSTYPE, '2', COUNT(DISTINCT A.ASSESSER), 0))) * .3 FINALSCORE");
		sql.append("  FROM PERFORMANCE_ASSESS A  GROUP BY A.USERID, A.ASSESSER, ASSESSTYPE");
		return getSqlList_(sql.toString(), PerformanceAssessScore.class);
	}

}
