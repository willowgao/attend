package com.wgsoft.performance.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.performance.model.PerformanceAssessScore;

/**
 * 
 * @title： IPerformanceAssessScoreService.java
 * @desc： 考核评分汇总确认
 * @author： Willowgao
 * @date： 2016-2-25 上午09:15:57
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IPerformanceAssessScoreService {

	/**
	 * @desc:考核评分统计汇总计算
	 * @param queryMap
	 * @return
	 * @return List<PerformanceAssessScore>
	 * @date： 2016-2-25 上午09:09:11
	 */
	List<PerformanceAssessScore> queryScores(Map<String, Object> queryMap);

	/**
	 * @desc: 评分明细查询
	 * @param queryMap
	 * @return
	 * @return List<PerformanceAssessScore>
	 * @date： 2016-2-25 上午09:09:37
	 */
	public List<PerformanceAssessScore> queryScoresDetail(Map<String, Object> queryMap);

	/**
	 * @desc:保存考核汇总信息
	 * @param saveMap
	 * @return
	 * @return int
	 * @date： 2016-2-25 上午09:16:15
	 */
	int saveAssessScore(Map<String, Object> saveMap);
}
