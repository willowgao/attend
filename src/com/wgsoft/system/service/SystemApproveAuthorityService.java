package com.wgsoft.system.service;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.LeaveAppSet;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.system.idao.ISystemApproveAuthorityDao;
import com.wgsoft.system.iservice.ISystemApproveAuthorityService;

/**
 * 
 * @title： SystemApproveAuthorityService.java
 * @desc： 系统审核权限修改
 * @author： Willowgao
 * @date： 2015-11-18 上午11:15:45
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class SystemApproveAuthorityService implements ISystemApproveAuthorityService {
	private ISystemApproveAuthorityDao systemApproveAuthorityDao;

	/**
	 * @see com.wgsoft.system.iservice.ISystemApproveAuthorityService#queryApproves(Map)
	 */
	public List<LeaveAppSet> queryApproves(Map<String, Object> queryMap) {
		return systemApproveAuthorityDao.queryApproves(queryMap);
	}

	/**
	 * @see com.wgsoft.system.iservice.ISystemApproveAuthorityService#save(LeaveAppSet)
	 */
	public int saveSetting(Map<String, List<LeaveAppSet>> map) {
		// 新增list
		List<LeaveAppSet> insertList = map.get(SysConstants.DataGridData.DATAGRID_LIST_INSERTLIST);
		// 删除list
		List<LeaveAppSet> deleteList = map.get(SysConstants.DataGridData.DATAGRID_LIST_DELETELIST);
		// 更新list
		List<LeaveAppSet> updataList = map.get(SysConstants.DataGridData.DATAGRID_LIST_UPDATELIST);
		int i = 0;
		if (updataList != null && updataList.size() > 0) {
			try {
				systemApproveAuthorityDao.updateBatch(updataList);
			} catch (Exception e) {
				i++;
			}
		}

		if (deleteList != null && deleteList.size() > 0) {
			try {
				systemApproveAuthorityDao.deleteBatch(deleteList);
			} catch (Exception e) {
				i++;
			}
		}

		if (insertList != null && insertList.size() > 0) {
			try {
				systemApproveAuthorityDao.insertBatch(insertList);
			} catch (Exception e) {
				i++;
			}
		}
		return i;
	}

	public ISystemApproveAuthorityDao getSystemApproveAuthorityDao() {
		return systemApproveAuthorityDao;
	}

	public void setSystemApproveAuthorityDao(ISystemApproveAuthorityDao systemApproveAuthorityDao) {
		this.systemApproveAuthorityDao = systemApproveAuthorityDao;
	}

}
