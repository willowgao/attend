package com.wgsoft.performance.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.performance.iservice.IPerformanceAssessScoreService;
import com.wgsoft.performance.model.PerformanceAssess;
import com.wgsoft.performance.model.PerformanceAssessScore;

/**
 * @title： PerformanceIndexManageAction.java
 * @desc： 考核评分汇总
 * @author： Willowgao
 * @date： 2016-1-25 上午09:12:02
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PerformanceAssessScoreAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6756113475070331197L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc:查询考核指标明细信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-23 下午02:22:44
	 */
	public String queryScore() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (assess == null) {
			assess = new PerformanceAssess();
		}
		Map<String, Object> requestMap = request.getParameterMap(); 
		if(RunUtil.isNotEmpty(((String[]) requestMap.get("assess.starttime"))[0])){
			queryMap.put("starttime", DateUtil.string2Date(((String[]) requestMap.get("assess.starttime"))[0], DateUtil.YMD));
		}
		if(RunUtil.isNotEmpty(((String[]) requestMap.get("assess.endtime"))[0])){
			queryMap.put("endtime", DateUtil.string2Date(((String[]) requestMap.get("assess.endtime"))[0], DateUtil.YMD));
		}
		queryMap.put("user", getUserInfo());
		List<PerformanceAssessScore> list = getPerformanceAssessScoreService().queryScores(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:保存考核打分
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-1-27 下午02:09:46
	 */
	@SuppressWarnings("unchecked")
	public String save() throws Exception { 
		Map<String, Object> saveMap = new HashMap<String, Object>();
		// 用户信息
		saveMap.put("user", getUserInfo());
		//列表信息
		String jsonStr = ((String[]) request.getParameterMap().get("assess.datagrid"))[0];
		List<PerformanceAssessScore> assessScore =(List<PerformanceAssessScore>)JSONUtil.deserialize(jsonStr);
		saveMap.put("assessScore", assessScore);
		int rel = getPerformanceAssessScoreService().saveAssessScore(saveMap);
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	private PerformanceAssess assess;

	public PerformanceAssess getAssess() {
		return assess;
	}

	public void setAssess(PerformanceAssess assess) {
		this.assess = assess;
	}
	public IPerformanceAssessScoreService getPerformanceAssessScoreService() {
		return (IPerformanceAssessScoreService) getService("performanceAssessScoreService");
	}

}
