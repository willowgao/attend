package com.wgsoft.attendance.clock.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.ClockExcepApprove;
import com.wgsoft.attendance.clock.model.ClockRecords;

/**
 * @title： IAttendanceExcepService.java
 * @desc：考勤异常处理 【未打卡、年假、事假、出差等】
 * @author： Willowgao
 * @date： 2015-10-28 下午01:07:03
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IAttendanceExcepService {

	/**
	 * @desc:查询打卡异常记录
	 * @param queryMap
	 * @return
	 * @return List<ClockRecords>
	 * @date： 2015-10-28 下午01:13:11
	 */
	List<ClockRecords> getExcepClockRecords(Map<String, Object> queryMap);

	/**
	 * @desc: 保存未打卡记录
	 * @param saveMap
	 * @return
	 * @return int
	 * @date： 2015-10-30 上午08:45:25
	 */
	int saveExcep(Map<String, Object> saveMap);

	/**
	 * @desc: 查询异常打卡审核记录
	 * @param queryMap
	 * @return
	 * @return List<ClockExcepApprove>
	 * @date： 2015-11-2 下午08:37:38
	 */
	List<ClockExcepApprove> getExpApproves(Map<String, Object> queryMap);

	/**
	 * 
	 * @desc:审核异常打卡审核记录
	 * @param saveMap
	 * @return
	 * @return int
	 * @date： 2015-11-2 下午08:38:03
	 */
	int saveApprove(Map<String, Object> saveMap);

}
