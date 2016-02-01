package com.wgsoft.system.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.LeaveAppSet;

/**
 * 
 * @title： ISystemApproveAuthorityService.java
 * @desc：系统审核权限修改
 * @author： Willowgao
 * @date： 2015-11-18 上午11:14:02
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface ISystemApproveAuthorityService {
	/**
	 * @desc: 查询系统设置的权限设置
	 * @param queryMap
	 * @return
	 * @return List<LeaveAppSet>
	 * @date： 2015-11-18 上午11:14:53
	 */
	List<LeaveAppSet> queryApproves(Map<String, Object> queryMap);

	/**
	 * @desc:保存权限设置信息
	 * @param map
	 *            Map<String, List<LeaveAppSet>>
	 * @return
	 * @return int
	 * @date： 2015-11-20 上午09:00:11
	 */
	int saveSetting(Map<String, List<LeaveAppSet>> map);
}
