package com.wgsoft.performance.idao;

import java.util.List;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.performance.model.PerformanceIndex;

/**
 * @title： IPerformanceIndexManageDao.java
 * @desc： 考核指标管理
 * @author： Willowgao
 * @date： 2016-1-25 上午09:16:14
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IPerformanceIndexManageDao extends IBaseDao {
	/**
	 * @desc:查询考核明细信息
	 * @return
	 * @return List<PerformanceIndex>
	 * @date： 2016-1-25 上午09:14:00
	 */
	List<PerformanceIndex> queryIndex();
}
