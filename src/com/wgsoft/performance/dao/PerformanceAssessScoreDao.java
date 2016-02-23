package com.wgsoft.performance.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.performance.idao.IPerformanceAssessScoreDao;
import com.wgsoft.performance.model.PerformanceAssessScore;
import com.wgsoft.user.model.UserInfo;

/**
 * @author Administrator
 *
 */
public class PerformanceAssessScoreDao extends BaseDao implements
		IPerformanceAssessScoreDao {

	/**
	 * @see com.wgsoft.performance.idao.IPerformanceAssessScoreDao#queryScores(Map)
	 */
	public List<PerformanceAssessScore> queryScores(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT  USERID,DEPTID,ASSESSYEAR,STARTTIME,ENDTIME,SUM(HIGHERSCORE / NUM) * .7 HIGHERSCORE,");
		sql.append(" SUM(PEERSCORE / NUM) * .3 PEERSCORE,  SUM(HIGHERSCORE / NUM) * .7 + SUM(PEERSCORE / NUM) * .3 FINALSCORE, (SELECT COUNT(1) ");
		sql.append(" FROM CLOCKEXCEPTION B WHERE A.USERID = B.USERID AND A.STARTTIME <= CLOCKDATE AND A.ENDTIME >= CLOCKDATE  ");
		sql.append(" AND B.ISENABLE = '0') * 1 + ((A.ENDTIME - A.STARTTIME + 1) - (SELECT COUNT(1) FROM CLOCKREOCRDS C WHERE A.USERID = C.USERID");
		sql.append(" AND A.STARTTIME <= CLOCKDATE  AND A.ENDTIME >= CLOCKDATE)) * 5 REDUCTIONSCORE");
		sql.append(" FROM (SELECT A.USERID,A.DEPTID,A.ASSESSYEAR,A.STARTTIME,A.ENDTIME,  DECODE(A.ASSESSTYPE, '1', SUM(NVL(INDEXSCORE, 0)), 0) HIGHERSCORE,");
		sql.append(" DECODE(A.ASSESSTYPE, '2', SUM(NVL(INDEXSCORE, 0)), 0) PEERSCORE,COUNT(DISTINCT A.ASSESSER) NUM");
		sql.append(" FROM PERFORMANCE_ASSESS A WHERE 1=1"); 
		if(RunUtil.isNotEmpty(queryMap.get("starttime"))){
			sql.append("  AND TO_CHAR(A.STARTTIME,'YYYY-MM-DD') >='").append(queryMap.get("starttime")).append("'"); 
		}
		if(RunUtil.isNotEmpty(queryMap.get("endtime"))){
			sql.append("  AND TO_CHAR(A.ENDTIME,'YYYY-MM-DD') >='").append(queryMap.get("endtime")).append("'"); 
		}
		sql.append("  AND A.DEPTID = '").append(((UserInfo)queryMap.get("user")).getUserdeptid()).append("' ");
		sql.append("  GROUP BY A.USERID, A.ASSESSTYPE,A.DEPTID,A.ASSESSYEAR,A.STARTTIME,A.ENDTIME) A");
		sql.append("  GROUP BY DEPTID, USERID, STARTTIME, ENDTIME, ASSESSYEAR");
		return getSqlList_(sql.toString(), PerformanceAssessScore.class);
	}

}
