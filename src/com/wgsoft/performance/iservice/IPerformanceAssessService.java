package com.wgsoft.performance.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.performance.model.PerformanceAssess;

/**
 * 
 * @title： IPerformanceAssessService.java
 * @desc：考核评分
 * @author： Willowgao
 * @date： 2016-1-27 下午02:14:07
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IPerformanceAssessService {

	/**
	 * @desc:保存打分信息
	 * @param saveMap
	 * @return
	 * @return int
	 * @date： 2016-1-27 下午02:15:12
	 */
	int saveAssess(Map<String, Object> saveMap);
	
	/**
	 * @desc: 查询考核信息
	 * @param queryMap
	 * @return 
	 * @return List<PerformanceAssess>
	 * @date： 2016-3-2 下午03:25:50
	 */
	List<PerformanceAssess> queryAssess(Map<String, Object> queryMap);
}
