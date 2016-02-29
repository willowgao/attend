package com.wgsoft.performance.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.performance.model.PerformanceAssessScore;

/**
 * @title： IPerformanceAssessScoreDao.java
 * @desc： 考核评分汇总确认
 * @author： Willowgao
 * @date： 2016-2-25 上午09:04:34
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IPerformanceAssessScoreDao extends IBaseDao {

	/**
	 * @desc:考核评分统计汇总计算
	 * @param queryMap
	 * @return
	 * @return List<PerformanceAssessScore>
	 * @date： 2016-2-25 上午09:09:11
	 */
	public List<PerformanceAssessScore> queryScores(Map<String, Object> queryMap);

	/**
	 * @desc: 评分明细查询
	 * @param queryMap
	 * @return
	 * @return List<PerformanceAssessScore>
	 * @date： 2016-2-25 上午09:09:37
	 */
	public List<PerformanceAssessScore> queryScoresDetail(Map<String, Object> queryMap);

	/**
	 * @desc: 查询扣分明细
	 * @param queryMap
	 * @return
	 * @return List<PerformanceAssessScore>
	 * @date： 2016-2-25 上午09:09:37
	 */
	public List<PerformanceAssessScore> queryReductions(Map<String, Object> queryMap);
}
