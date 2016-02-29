package com.wgsoft.performance.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.diary.model.EchartsOfBar;
import com.wgsoft.performance.idao.IQueryAssessInfoDao;
import com.wgsoft.performance.model.PerformanceAssessScore;

/**
 * @title： QueryAssessInfoDao.java
 * @desc： 考核情况查询
 * @author： Willowgao
 * @date： 2016-2-25 下午02:53:58
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryAssessInfoDao extends BaseDao implements IQueryAssessInfoDao {
	/**
	 * @see com.wgsoft.performance.idao.IQueryAssessInfoDao#queryAssessForList(Map)
	 */
	public List<PerformanceAssessScore> queryAssessForList(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT * FROM PERFORMANCE_ASSESS_SCORE A WHERE 1=1");
		
		if (RunUtil.isNotEmpty(queryMap.get("starttime"))) {
			sql.append("  AND TO_CHAR(A.STARTTIME,'YYYY-MM-DD') >='").append(queryMap.get("starttime")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("endtime"))) {
			sql.append("  AND TO_CHAR(A.ENDTIME,'YYYY-MM-DD') <='").append(queryMap.get("endtime")).append("'");
		}

		if (RunUtil.isNotEmpty(queryMap.get("deptid"))) {
			sql.append("  AND A.DEPTID = '").append(queryMap.get("deptid")).append("'");
		}
		
		return getSqlList_(sql.toString(), PerformanceAssessScore.class);
	}
	/**
	 * @see com.wgsoft.performance.idao.IQueryAssessInfoDao#queryOrgRanking(Map)
	 */
	public List<EchartsOfBar> queryOrgRanking(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT '考核成绩排名分析' TITLE, '考核得分成绩排名' CHILDTITLE, '得分' DATANAME,");
		sql.append(" '分' DATAUNIT,  A.FINALSCORE XDATA,FN_GETCODEDESC('DEPT', A.DEPTID) || ' ' || FN_GETCODEDESC('USER', A.USERID)  XCOMMENTS");
		sql.append("   FROM PERFORMANCE_ASSESS_SCORE A WHERE ROWNUM <= 10  ORDER BY A.FINALSCORE DESC");

		return getSqlList_(sql.toString(), EchartsOfBar.class);
	}

}
