package com.wgsoft.attendance.clock.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.ClockException;
import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.attendance.clock.model.ClockSetting;
import com.wgsoft.common.idao.IBaseDao;

/**
 * @title： IAttendanceManageDao.java
 * @desc： 考勤管理
 * @author： Willowgao
 * @date： 2015-9-10 下午01:18:45
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IAttendanceManageDao extends IBaseDao {

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
	 * 
	 * @desc: 保存打卡异常信息
	 * @param ce
	 * @return
	 * @return int
	 * @date： 2015-10-27 上午11:31:02
	 */
	int saveClockException(ClockException ce);


	/**
	 * 查询打卡记录
	 * 
	 * @param queryMap
	 * @return
	 */
	List<ClockRecords> queryClocks(Map<String, Object> queryMap);
}
