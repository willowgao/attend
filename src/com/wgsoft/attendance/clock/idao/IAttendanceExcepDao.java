package com.wgsoft.attendance.clock.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.ClockExcepApprove;
import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.common.idao.IBaseDao;

/**
 * @title： IAttendanceExcepDao.java
 * @desc： 考勤异常处理 【未打卡、年假、事假、出差等】
 * @author： Willowgao
 * @date： 2015-10-28 下午01:10:35
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IAttendanceExcepDao extends IBaseDao {

	/**
	 * @desc:查询打卡异常记录
	 * @param queryMap
	 * @return
	 * @return List<ClockRecords>
	 * @date： 2015-10-28 下午01:13:45
	 */
	List<ClockRecords> getExcepClockRecords(Map<String, Object> queryMap);

	/**
	 * @desc: 查询异常打卡审核记录
	 * @param queryMap
	 * @return
	 * @return List<ClockExcepApprove>
	 * @date： 2015-11-2 下午08:37:38
	 */
	List<ClockExcepApprove> getExpApproves(Map<String, Object> queryMap);

	/**
	 * @desc: 更新状态
	 * @param saveMap
	 * @return
	 * @return int
	 * @date： 2015-11-2 下午08:39:38
	 */
	int updateStatus(Map<String, Object> saveMap);
	/**
	 * @desc:写入正常的记录
	 * @param savemap
	 * @return 
	 * @return int
	 * @date： 2015-11-2 下午10:05:03
	 */
	int insertNormalRecords(Map <String, Object>savemap);
	/**
	 * @desc: 
	 * @param queryMap
	 * @return 
	 * @return List<ClockRecords>
	 * @date： 2015-11-10 上午09:25:34
	 */
	List<ClockRecords> getClockRecords(Map<String, Object> queryMap);
	
	
}
