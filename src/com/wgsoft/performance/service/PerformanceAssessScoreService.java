package com.wgsoft.performance.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wgsoft.common.utils.BeanUtil;
import com.wgsoft.performance.idao.IPerformanceAssessScoreDao;
import com.wgsoft.performance.iservice.IPerformanceAssessScoreService;
import com.wgsoft.performance.model.PerformanceAssessScore;
import com.wgsoft.user.model.UserInfo;

/**
 * 
 * @title： PerformanceAssessScoreService.java
 * @desc： 考核评分汇总确认
 * @author： Willowgao
 * @date： 2016-2-25 上午09:16:41
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PerformanceAssessScoreService implements IPerformanceAssessScoreService {

	private IPerformanceAssessScoreDao performanceAssessScoreDao;

	/**
	 * @see com.wgsoft.performance.iservice.IPerformanceAssessScoreService#queryScores(Map)
	 */
	public List<PerformanceAssessScore> queryScores(Map<String, Object> queryMap) {
		List<PerformanceAssessScore> scores = performanceAssessScoreDao.queryScores(queryMap);
		for (PerformanceAssessScore score : scores) {
			
			DecimalFormat df = new DecimalFormat("0.00");
			// 最后得分减扣分项
			score.setFinalscore(Double.valueOf(df.format(score.getFinalscore() - score.getReductionscore())));
			score.setAttednscore(Double.valueOf(df.format(Double.valueOf("20") - score.getReductionscore())));
		}
		return scores;
	}

	/**
	 * @see com.wgsoft.performance.iservice.IPerformanceAssessScoreService#queryScoresDetail(Map)
	 */
	public List<PerformanceAssessScore> queryScoresDetail(Map<String, Object> queryMap) {
		return performanceAssessScoreDao.queryScoresDetail(queryMap);
	}

	/**
	 * @see com.wgsoft.performance.iservice.IPerformanceAssessScoreService#queryReductions(Map)
	 */
	public List<PerformanceAssessScore> queryReductions(Map<String, Object> queryMap) {
		return performanceAssessScoreDao.queryReductions(queryMap);
	}

	/**
	 * @see com.wgsoft.performance.iservice.IPerformanceAssessScoreService#saveAssessScore(Map)
	 */
	public int saveAssessScore(Map<String, Object> saveMap) {
		UserInfo user = (UserInfo) saveMap.get("user");
		List<Map<String, Object>> scoreMaps = (List<Map<String, Object>>) saveMap.get("assessScore");
		Date date = new Date();
		List<PerformanceAssessScore> scores = new ArrayList<PerformanceAssessScore>();
		for (Map<String, Object> score : scoreMaps) {
			PerformanceAssessScore assessScore = new PerformanceAssessScore();
			try {
				BeanUtil.applyIf(assessScore, score);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assessScore.setUploder(user.getUserid());
			assessScore.setUplodetime(date);
			// 最后得分减扣分项
			assessScore.setAttednscore(Double.valueOf("20") - assessScore.getReductionscore());
			scores.add(assessScore);
		}

		return performanceAssessScoreDao.insertBatch(scores);
	}

	public IPerformanceAssessScoreDao getPerformanceAssessScoreDao() {
		return performanceAssessScoreDao;
	}

	public void setPerformanceAssessScoreDao(IPerformanceAssessScoreDao performanceAssessScoreDao) {
		this.performanceAssessScoreDao = performanceAssessScoreDao;
	}

}
