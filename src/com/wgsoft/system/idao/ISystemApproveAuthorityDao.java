package com.wgsoft.system.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.LeaveAppSet;
import com.wgsoft.common.idao.IBaseDao;

/**
 * @title： ISystemApproveAuthorityDao.java
 * @desc： 系统审核权限修改
 * @author： Willowgao
 * @date： 2015-11-18 上午08:43:37
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface ISystemApproveAuthorityDao extends IBaseDao {
	/**
	 * @desc: 查询系统设置的权限设置
	 * @param queryMap
	 * @return
	 * @return List<LeaveAppSet>
	 * @date： 2015-11-18 上午11:14:53
	 */
	List<LeaveAppSet> queryApproves(Map<String, Object> queryMap);
}
