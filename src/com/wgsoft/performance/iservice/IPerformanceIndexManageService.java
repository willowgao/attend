package com.wgsoft.performance.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.performance.model.PerformanceIndex;

/**
 * @title： IPerformanceIndexManageService.java
 * @desc：考核指标管理
 * @author： Willowgao
 * @date： 2016-1-25 上午09:13:17
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IPerformanceIndexManageService {

	/**
	 * @desc:查询考核明细信息
	 * @return
	 * @return List<PerformanceIndex>
	 * @date： 2016-1-25 上午09:14:00
	 */
	List<PerformanceIndex> queryIndex();

	/**
	 * @desc:保存明细信息
	 * @param jsonMap
	 *            Map<String, List<PerformanceIndex>>
	 * @return void
	 * @date： 2016-1-25 上午09:15:01
	 */
	int saveIndex(Map<String, List<PerformanceIndex>> jsonMap);

}
