package com.wgsoft.performance.iservice;

import java.util.Map;

/**
 * 
 * @title： IPerformanceAssessService.java
 * @desc：考核评分
 * @author： Willowgao
 * @date： 2016-1-27 下午02:14:07
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
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

}
