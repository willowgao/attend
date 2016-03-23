package com.wgsoft.performance.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.performance.model.PerformanceAssess;
import com.wgsoft.system.model.ClockDateSetting;

/**
 * 
 * @title： IPerformanceAssessDao.java
 * @desc： 考核评分
 * @author： Willowgao
 * @date： 2016-1-27 下午02:16:32
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IPerformanceAssessDao extends IBaseDao {

	/**
	 * @desc: 查询考核信息
	 * @param queryMap
	 * @return
	 * @return List<PerformanceAssess>
	 * @date： 2016-3-2 下午03:25:50
	 */
	List<PerformanceAssess> queryAssess(Map<String, Object> queryMap);

	/**
	 * @desc: 查询考核查检的时间
	 * @param dateType
	 * @return
	 * @return ClockDateSetting
	 * @date： 2016-3-17 下午05:57:30
	 */
	ClockDateSetting getDateByType(String dateType);

}
