package com.wgsoft.system.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.LeaveAppSet;
import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.system.idao.ISystemApproveAuthorityDao;

/**
 * @title： SystemApproveAuthorityDao.java
 * @desc： 系统审核权限修改
 * @author： Willowgao
 * @date： 2015-11-18 上午08:46:46
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class SystemApproveAuthorityDao extends BaseDao implements ISystemApproveAuthorityDao {

	/**
	 * @see com.wgsoft.system.idao.ISystemApproveAuthorityDao#queryApproves(Map)
	 */
	public List<LeaveAppSet> queryApproves(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT * FROM leave_approve_setting");
		return getSqlList_(sql.toString(), LeaveAppSet.class);
	}

}
