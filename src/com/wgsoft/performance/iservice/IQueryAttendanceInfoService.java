package com.wgsoft.performance.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.diary.model.EchartsOfBar;
import com.wgsoft.diary.model.EchartsOfPie;

/**
 * 
 * @title： IQueryAttendanceInfoService.java
 * @desc：查询出勤情况
 * @author： Willowgao
 * @date： 2016-2-2 上午09:25:22
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IQueryAttendanceInfoService {

	/**
	 * 
	 * @desc:查询出勤情况
	 * @param queryMap
	 * @return
	 * @return List<ClockRecords>
	 * @date： 2016-2-2 上午09:27:12
	 */
	List<ClockRecords> queryAttend(Map<String, Object> queryMap);

	/**
	 * @desc:查询出勤排名
	 * @param queryMap
	 * @return
	 * @return List<ClockRecords>
	 * @date： 2016-2-2 上午09:27:12
	 */
	List<EchartsOfBar> queryOrgRanking(Map<String, Object> queryMap);
	

	/**
	 * @desc:查询出勤情况本部门
	 * @param queryMap
	 * @return
	 * @return List<ClockRecords>
	 * @date： 2016-2-2 上午09:27:12
	 */
	List<EchartsOfPie> queryDeptRanking(Map<String, Object> queryMap);
}
