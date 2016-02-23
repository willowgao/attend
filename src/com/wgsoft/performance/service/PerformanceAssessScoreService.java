package com.wgsoft.performance.service;

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
 * @author Administrator
 * 
 */
public class PerformanceAssessScoreService implements
		IPerformanceAssessScoreService {

	private IPerformanceAssessScoreDao performanceAssessScoreDao;

	/**
	 * @see com.wgsoft.performance.iservice.IPerformanceAssessScoreService#queryScores(Map)
	 */
	public List<PerformanceAssessScore> queryScores(Map<String, Object> queryMap) {
		return performanceAssessScoreDao.queryScores(queryMap);
	}
	/**
	 * @see com.wgsoft.performance.iservice.IPerformanceAssessScoreService#saveAssessScore(Map)
	 */
	public int saveAssessScore(Map<String, Object> saveMap) {
		UserInfo user = (UserInfo) saveMap.get("user");
		List<Map<String,Object>> scoreMaps = (List<Map<String,Object>>) saveMap.get("assessScore");
		Date date = new Date();
		List<PerformanceAssessScore> scores = new ArrayList<PerformanceAssessScore>();
		for (Map<String,Object> score : scoreMaps) {
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
			assessScore.setFinalscore(assessScore.getFinalscore()
					- assessScore.getReductionscore());
			assessScore.setAttednscore(Double.valueOf("20")- assessScore.getReductionscore());
			scores.add(assessScore);

		}
		return performanceAssessScoreDao.insertBatch(scores);
	}

	public IPerformanceAssessScoreDao getPerformanceAssessScoreDao() {
		return performanceAssessScoreDao;
	}

	public void setPerformanceAssessScoreDao(
			IPerformanceAssessScoreDao performanceAssessScoreDao) {
		this.performanceAssessScoreDao = performanceAssessScoreDao;
	}

}
