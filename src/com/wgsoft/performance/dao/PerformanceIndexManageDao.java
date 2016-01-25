package com.wgsoft.performance.dao;

import java.util.List;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.performance.idao.IPerformanceIndexManageDao;
import com.wgsoft.performance.model.PerformanceIndex;

/**
 * @title： IPerformanceIndexManageDao.java
 * @desc： 考核指标管理
 * @author： Willowgao
 * @date： 2016-1-25 上午09:16:14
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PerformanceIndexManageDao extends BaseDao implements IPerformanceIndexManageDao {

	/**
	 * @see com.wgsoft.performance.idao.IPerformanceIndexManageDao#queryIndex()
	 */
	public List<PerformanceIndex> queryIndex() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM performance_index order by to_number(indexid)");
		return getSqlList_(sql.toString(), PerformanceIndex.class);
	}

}
