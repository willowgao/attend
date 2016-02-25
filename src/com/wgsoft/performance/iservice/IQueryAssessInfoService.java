package com.wgsoft.performance.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.performance.model.PerformanceAssessScore;

/**
 * @title： IQueryAssessInfoService.java
 * @desc：考核情况查询
 * @author： Willowgao
 * @date： 2016-2-25 下午02:50:25
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IQueryAssessInfoService {

	/**
	 * @desc:查询考核汇总情况
	 * @param queryMap
	 * @return
	 * @return List<PerformanceAssessScore>
	 * @date： 2016-2-25 下午02:51:59
	 */
	List<PerformanceAssessScore> queryAssessForList(Map<String, Object> queryMap);
}
