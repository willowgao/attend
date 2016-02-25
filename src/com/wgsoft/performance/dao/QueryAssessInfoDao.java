package com.wgsoft.performance.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
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
		StringBuffer sql = new StringBuffer("SELECT * FROM PERFORMANCE_ASSESS_SCORE WHERE 1=1");
		return getSqlList_(sql.toString(), PerformanceAssessScore.class);
	}

}
