package com.wgsoft.performance.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.diary.model.EchartsOfBar;
import com.wgsoft.diary.model.EchartsOfPie;

/**
 * 
 * @title： IQueryAttendanceInfoDao.java
 * @desc：
 * @author： Willowgao
 * @date： 2016-2-2 上午09:31:52
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IQueryAttendanceInfoDao extends IBaseDao {
	/**
	 * @desc: 查询出勤情况
	 * @param queryMap
	 * @return
	 * @return List<ClockRecords>
	 * @date： 2016-2-2 上午09:27:12
	 */
	List<ClockRecords> queryAttend(Map<String, Object> queryMap);

	/**
	 * @desc: 个人一段时间内的出勤情况
	 * @param queryMap
	 * @return
	 * @return List<ClockRecords>
	 * @date： 2016-2-2 上午09:27:12
	 */
	List<ClockRecords> queryAttends(Map<String, Object> queryMap);

	/**
	 * @desc: 查询出勤情况 所有组织
	 * @param queryMap
	 * @return
	 * @return List<EchartsOfBar>
	 * @date： 2016-2-2 上午09:27:12
	 */
	List<EchartsOfBar> queryOrgRanking(Map<String, Object> queryMap);

	/**
	 * @desc: 查询出勤情况本部门
	 * @param queryMap
	 * @return
	 * @return List<EchartsOfPie>
	 * @date： 2016-2-2 上午09:27:12
	 */
	List<EchartsOfPie> queryDeptRanking(Map<String, Object> queryMap);
}
