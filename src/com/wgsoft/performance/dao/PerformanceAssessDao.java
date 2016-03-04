package com.wgsoft.performance.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.performance.idao.IPerformanceAssessDao;
import com.wgsoft.performance.model.PerformanceAssess;

/**
 * 
 * @title： PerformanceAssessDao.java
 * @desc： 考核评分
 * @author： Willowgao
 * @date： 2016-1-27 下午02:17:10
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PerformanceAssessDao extends BaseDao implements IPerformanceAssessDao {


	/**
	 * @see com.wgsoft.performance.idao.IPerformanceAssessDao#queryAssess(Map)
	 */
	public List<PerformanceAssess> queryAssess(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT * FROM PERFORMANCE_ASSESS WHERE 1=1 ");
		if(RunUtil.isNotEmpty(queryMap.get("userid"))){
			sql.append(" AND USERID ='").append(queryMap.get("userid")).append("'");
		}
		if(RunUtil.isNotEmpty(queryMap.get("assesser"))){
			sql.append(" AND ASSESSER ='").append(queryMap.get("assesser")).append("'");
		}
		if(RunUtil.isNotEmpty(queryMap.get("starttime"))){
			sql.append(" AND TO_CHAR(STARTTIME,'YYYY-MM-DD') <='").append(queryMap.get("starttime")).append("'");
		}
		if(RunUtil.isNotEmpty(queryMap.get("endtime"))){
			sql.append(" AND TO_CHAR(ENDTIME,'YYYY-MM-DD') >='").append(queryMap.get("endtime")).append("'");
		}
		return getSqlList_(sql.toString(), PerformanceAssess.class);
	}

}
