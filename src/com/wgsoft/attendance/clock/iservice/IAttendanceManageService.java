package com.wgsoft.attendance.clock.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.attendance.clock.model.ClockSetting;

public interface IAttendanceManageService {
	/**
	 * @desc:查询打卡记录
	 * @param userId
	 * @return
	 * @return ClockRecords
	 * @date： 2015-9-10 下午01:19:53
	 */
	ClockRecords queryClockRecords(String userId);

	/**
	 * @desc:打卡
	 * @param clock
	 * @return
	 * @return int
	 * @date： 2015-9-10 下午01:22:09
	 */
	int saveClockRecord(ClockRecords clock);

	/**
	 * @desc: 查询设置记录
	 * @return
	 * @return List<ClockSetting>
	 * @date： 2015-10-25 下午02:24:53
	 */
	List<ClockSetting> queryClockSettings(ClockSetting cs);

	/**
	 * 保存打卡记录
	 * 
	 * @param cs
	 * @return
	 */
	int saveClockSetting(ClockSetting cs);

	/**
	 * 根据系统当前时间查询所属的打卡区间
	 * 
	 * @return
	 */
	ClockSetting queryTimesForNow();

	/**
	 * 查询打卡记录
	 * 
	 * @param queryMap
	 * @return
	 */
	List<ClockRecords> queryClocks(Map<String, Object> queryMap);
}
