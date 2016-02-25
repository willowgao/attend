package com.wgsoft.performance.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.performance.idao.IPerformanceAssessScoreDao;
import com.wgsoft.performance.model.PerformanceAssessScore;
import com.wgsoft.user.model.UserInfo;

/**
 * 
 * @title： PerformanceAssessScoreDao.java
 * @desc： 考核评分汇总确认
 * @author： Willowgao
 * @date： 2016-2-25 上午09:04:38
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PerformanceAssessScoreDao extends BaseDao implements IPerformanceAssessScoreDao {

	/**
	 * @see com.wgsoft.performance.idao.IPerformanceAssessScoreDao#queryScores(Map)
	 */
	public List<PerformanceAssessScore> queryScores(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer(
				"SELECT  USERID,DEPTID,ASSESSYEAR,STARTTIME,ENDTIME,SUM(HIGHERSCORE / NUM) * .7 HIGHERSCORE,");
		sql
				.append(" SUM(PEERSCORE / NUM) * .3 PEERSCORE,  SUM(HIGHERSCORE / NUM) * .7 + SUM(PEERSCORE / NUM) * .3 FINALSCORE, (SELECT COUNT(1) ");
		sql
				.append(" FROM CLOCKEXCEPTION B WHERE A.USERID = B.USERID AND A.STARTTIME <= CLOCKDATE AND A.ENDTIME >= CLOCKDATE  ");
		sql
				.append(" AND B.ISENABLE = '0') * 1 + ((A.ENDTIME - A.STARTTIME + 1) - (SELECT COUNT(1) FROM CLOCKREOCRDS C WHERE A.USERID = C.USERID");
		sql.append(" AND A.STARTTIME <= CLOCKDATE  AND A.ENDTIME >= CLOCKDATE)) * 5 REDUCTIONSCORE");
		sql
				.append(" FROM (SELECT A.USERID,A.DEPTID,A.ASSESSYEAR,A.STARTTIME,A.ENDTIME,  DECODE(A.ASSESSTYPE, '1', SUM(NVL(INDEXSCORE, 0)), 0) HIGHERSCORE,");
		sql.append(" DECODE(A.ASSESSTYPE, '2', SUM(NVL(INDEXSCORE, 0)), 0) PEERSCORE,COUNT(DISTINCT A.ASSESSER) NUM");
		sql.append(" FROM PERFORMANCE_ASSESS A WHERE 1=1");
		if (RunUtil.isNotEmpty(queryMap.get("starttime"))) {
			sql.append("  AND TO_CHAR(A.STARTTIME,'YYYY-MM-DD') >='").append(queryMap.get("starttime")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("endtime"))) {
			sql.append("  AND TO_CHAR(A.ENDTIME,'YYYY-MM-DD') >='").append(queryMap.get("endtime")).append("'");
		}
		sql.append("  AND A.DEPTID = '").append(((UserInfo) queryMap.get("user")).getUserdeptid()).append("' ");
		sql.append("  GROUP BY A.USERID, A.ASSESSTYPE,A.DEPTID,A.ASSESSYEAR,A.STARTTIME,A.ENDTIME) A");
		sql
				.append("  WHERE NOT EXISTS (SELECT 1 FROM PERFORMANCE_ASSESS_SCORE B WHERE A.USERID = B.USERID AND A.ASSESSYEAR = B.ASSESSYEAR AND A.STARTTIME = B.STARTTIME AND A.ENDTIME = B.ENDTIME)");
		sql.append("  GROUP BY DEPTID, USERID, STARTTIME, ENDTIME, ASSESSYEAR");
		return getSqlList_(sql.toString(), PerformanceAssessScore.class);
	}

	public List<PerformanceAssessScore> queryScoresDetail(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer(
				"SELECT A.USERID,A.DEPTID,A.ASSESSTYPE,A.ASSESSYEAR,A.ASSESSER,A.STARTTIME,A.ENDTIME ");
		sql.append(" , SUM(NVL(INDEXSCORE, 0)) finalscore  FROM PERFORMANCE_ASSESS A WHERE 1=1");
		if (RunUtil.isNotEmpty(queryMap.get("starttime"))) {
			sql.append("  AND TO_CHAR(A.STARTTIME,'YYYY-MM-DD') >='").append(
					queryMap.get("starttime").toString()
							.substring(0, queryMap.get("starttime").toString().indexOf("T"))).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("endtime"))) {
			sql.append("  AND TO_CHAR(A.ENDTIME,'YYYY-MM-DD') >='").append(queryMap.get("endtime").toString()
					.substring(0, queryMap.get("endtime").toString().indexOf("T"))).append("'");
		}
		sql.append("  AND A.USERID = '").append(queryMap.get("userid")).append("' ");
		sql.append("  GROUP BY A.USERID, A.ASSESSTYPE,A.DEPTID,A.ASSESSYEAR,A.STARTTIME,A.ENDTIME,A.ASSESSER");
		sql.append("  ORDER BY A.ASSESSER ");
		return getSqlList_(sql.toString(), PerformanceAssessScore.class);
	}

}
