package com.wgsoft.performance.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.performance.idao.IPerformanceAssessDao;
import com.wgsoft.performance.iservice.IPerformanceAssessService;
import com.wgsoft.performance.model.PerformanceAssess;
import com.wgsoft.user.model.UserInfo;

/**
 * @title： PerformanceAssessService.java
 * @desc： 考核评分
 * @author： Willowgao
 * @date： 2016-1-27 下午02:15:49
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PerformanceAssessService implements IPerformanceAssessService {

	private IPerformanceAssessDao performanceAssessDao;

	@SuppressWarnings("unchecked")
	public int saveAssess(Map<String, Object> saveMap) {
		UserInfo user = (UserInfo) saveMap.get("user");
		PerformanceAssess assess = (PerformanceAssess) saveMap.get("assess");
		Map<String, List<PerformanceAssess>> assessIndexs = (Map<String, List<PerformanceAssess>>) saveMap
				.get("assessIndexs");

		// 被考核人
		Integer roleType = Integer.valueOf(assess.getRoletype());
		// 考核人
		Integer login_roleType = Integer.valueOf(user.getRoletype());

		int i = 0;
		String assessType = null;
		// 同级
		if (login_roleType.compareTo(roleType) == 0) {
			assessType = SysConstants.ASSESS_TYPE_PEER_LEVEL;
		} else if (login_roleType.compareTo(roleType) > 0) {
			assessType = SysConstants.ASSESS_TYPE_HIGHER_LEVEL;
			// 不允许下级给上级考评
		} else {
			i++;
			return i;
		}

		// 新增list
		List<PerformanceAssess> updateList = assessIndexs.get(SysConstants.DataGridData.DATAGRID_LIST_UPDATELIST);

		Date assessdate = new Date();
		if (updateList != null && updateList.size() > 0) {
			for (PerformanceAssess assessIndex : updateList) {
				assessIndex.setAssessdate(assessdate);
				assessIndex.setAssesser(user.getUserid());
				assessIndex.setAssessyear(DateUtil.getNowDateByFormat(DateUtil.YMD).substring(0, 4));
				assessIndex.setDeptid(user.getUserdeptid());
				assessIndex.setEndtime(assess.getEndtime());
				assessIndex.setStarttime(assess.getStarttime());
				assessIndex.setUserid(assess.getUserid());
				assessIndex.setAssesstype(assessType);
			}
			try {
				performanceAssessDao.insertBatch(updateList);
			} catch (Exception e) {
				i++;
			}
		}

		return i;
	}

	public IPerformanceAssessDao getPerformanceAssessDao() {
		return performanceAssessDao;
	}

	public void setPerformanceAssessDao(IPerformanceAssessDao performanceAssessDao) {
		this.performanceAssessDao = performanceAssessDao;
	}

}
