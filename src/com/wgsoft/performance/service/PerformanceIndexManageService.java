package com.wgsoft.performance.service;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.performance.idao.IPerformanceIndexManageDao;
import com.wgsoft.performance.iservice.IPerformanceIndexManageService;
import com.wgsoft.performance.model.PerformanceIndex;
import com.wgsoft.performance.model.PositionStatement;

/**
 * 
 * @title： PerformanceIndexManageService.java
 * @desc： 考核指标管理
 * @author： Willowgao
 * @date： 2016-1-25 上午09:15:34
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PerformanceIndexManageService implements IPerformanceIndexManageService {

	private IPerformanceIndexManageDao performanceIndexManageDao;

	/**
	 * @see com.wgsoft.performance.iservice.IPerformanceIndexManageService#queryIndex()
	 */
	public List<PerformanceIndex> queryIndex() {
		return performanceIndexManageDao.queryIndex();
	}

	/**
	 * @see com.wgsoft.performance.iservice.IPerformanceIndexManageService#saveIndex(Map)
	 */
	public int saveIndex(Map<String, List<PerformanceIndex>> jsonMap) {
		// 新增list
		List<PerformanceIndex> insertList = jsonMap.get(SysConstants.DataGridData.DATAGRID_LIST_INSERTLIST);
		// 删除list
		List<PerformanceIndex> deleteList = jsonMap.get(SysConstants.DataGridData.DATAGRID_LIST_DELETELIST);
		// 更新list
		List<PerformanceIndex> updataList = jsonMap.get(SysConstants.DataGridData.DATAGRID_LIST_UPDATELIST);
		int i = 0;

		if (updataList != null && updataList.size() > 0) {
			try {
				performanceIndexManageDao.updateBatch(updataList);
			} catch (Exception e) {
				i++;
			}
		}
		return i;
	}

	public IPerformanceIndexManageDao getPerformanceIndexManageDao() {
		return performanceIndexManageDao;
	}

	public void setPerformanceIndexManageDao(IPerformanceIndexManageDao performanceIndexManageDao) {
		this.performanceIndexManageDao = performanceIndexManageDao;
	}

}
