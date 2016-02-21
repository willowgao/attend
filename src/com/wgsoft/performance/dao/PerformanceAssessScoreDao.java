package com.wgsoft.performance.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.performance.idao.IPerformanceAssessScoreDao;
import com.wgsoft.performance.model.PerformanceAssessScore;

public class PerformanceAssessScoreDao extends BaseDao implements
		IPerformanceAssessScoreDao {

	public List<PerformanceAssessScore> queryScores(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT  USERID,SUM(HIGHERSCORE / NUM) * .7 HIGHERSCORE,");
		sql.append(" SUM(PEERSCORE / NUM) * .3 PEERSCORE,  SUM(HIGHERSCORE / NUM) * .7 + SUM(PEERSCORE / NUM) * .3 FINALSCORE");
		sql.append(" FROM (SELECT A.USERID,  DECODE(A.ASSESSTYPE, '1', SUM(NVL(INDEXSCORE, 0)), 0) HIGHERSCORE,");
		sql.append(" DECODE(A.ASSESSTYPE, '2', SUM(NVL(INDEXSCORE, 0)), 0) PEERSCORE,  COUNT(DISTINCT A.ASSESSER) NUM");
		sql.append(" FROM PERFORMANCE_ASSESS A GROUP BY A.USERID, A.ASSESSTYPE)  GROUP BY USERID");
		return getSqlList_(sql.toString(), PerformanceAssessScore.class);
	}

}
