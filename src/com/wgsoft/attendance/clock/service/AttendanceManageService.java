package com.wgsoft.attendance.clock.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.idao.IAttendanceManageDao;
import com.wgsoft.attendance.clock.iservice.IAttendanceManageService;
import com.wgsoft.attendance.clock.model.ClockExcep;
import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.attendance.clock.model.ClockSetting;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;

/**
 * @title： AttendanceManageService.java
 * @desc：
 * @author： Willowgao
 * @date： 2015-9-10 下午01:40:12
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class AttendanceManageService implements IAttendanceManageService {

	private IAttendanceManageDao attendanceManageDao;

	public ClockRecords queryClockRecords(String userId) {
		return attendanceManageDao.queryClockRecords(userId);
	}

	public int saveClockRecord(ClockRecords clock) {
		/**
		 * 需要对打卡的时间与标准时间进行判断，用以区分是迟到、早退、旷工等情况
		 */
		ClockExcep clockExp = new ClockExcep();
		ClockSetting setting = queryTimesForNow();
		Date checkTime = DateUtil.string2Date(clock.getCheckTime(), DateUtil.HMS);
		boolean bool = false;
		// 上午上班、下午上班
		if ((RunUtil.isNotEmpty(clock.getAmsb()) && clock.getType().equals("1"))
				|| (RunUtil.isNotEmpty(clock.getPmsb()) && clock.getType().equals("3"))) {
			if (RunUtil.isNotEmpty(clock.getAmsb())) {
				Date amsb = DateUtil.string2Date(clock.getAmsb(), DateUtil.HMS);
				bool = amsb.after(checkTime);
			}
			if (RunUtil.isNotEmpty(clock.getPmsb())) {
				Date pmsb = DateUtil.string2Date(clock.getPmsb(), DateUtil.HMS);
				bool = pmsb.after(checkTime);
			}
			// 上班打卡，如果打卡时间晚于设置时间，则属于迟到
			if (bool) {
				clockExp.setClockdate(new Date());
				if (clock.getType().equals("1")) {
					clockExp.setClocktime(clock.getAmsb());
				} else {
					clockExp.setClocktime(clock.getPmsb());
				}
				clockExp.setExptype(SysConstants.CLOCK_EXCEPTION_LATER);
			}
		} else if ((RunUtil.isNotEmpty(clock.getAmxb()) && clock.getType().equals("2"))
				|| (RunUtil.isNotEmpty(clock.getPmxb()) && clock.getType().equals("4"))) {
			if (RunUtil.isNotEmpty(clock.getAmxb())) {
				Date amxb = DateUtil.string2Date(clock.getAmxb(), DateUtil.HMS);
				bool = amxb.before(checkTime);
			}
			if (RunUtil.isNotEmpty(clock.getPmxb())) {
				Date pmxb = DateUtil.string2Date(clock.getPmxb(), DateUtil.HMS);
				bool = pmxb.before(checkTime);
			}
			// 上午下班时间，晚于下午上班时间，也属于异常打卡 异常的类型？
			if (RunUtil.isNotEmpty(clock.getAmxb()) && RunUtil.isNotEmpty(setting.getPmsbTime())) {
				Date pmsb = DateUtil.string2Date(setting.getPmsbTime(), DateUtil.HMS);
				bool = pmsb.after(checkTime);
			}

			// 下班打卡，如果打卡时间早于设置时间，则属于早退
			if (bool) {
				clockExp.setClockdate(new Date());
				if (clock.getType().equals("2")) {
					clockExp.setClocktime(clock.getAmxb());
				} else {
					clockExp.setClocktime(clock.getPmxb());
				}
				clockExp.setExptype(SysConstants.CLOCK_EXCEPTION_EARLY);
			}
		}
		if (bool) {
			clockExp.setUserid(clock.getUserid());
			clockExp.setIsenable(SysConstants.ISENABLE_YES);
			attendanceManageDao.saveClockException(clockExp);
		}
		return attendanceManageDao.saveClockRecord(clock);
	}

	/**
	 * 查询时间设置记录
	 */
	public List<ClockSetting> queryClockSettings(ClockSetting cs) {
		return attendanceManageDao.queryClockSettings(cs);
	}

	/**
	 * 保存时间设置信息
	 */
	public int saveClockSetting(ClockSetting cs) {
		return attendanceManageDao.saveClockSetting(cs);
	}

	/**
	 * 查询系统当前时间所属的区间
	 */

	public ClockSetting queryTimesForNow() {
		return attendanceManageDao.queryTimesForNow();
	}

	public IAttendanceManageDao getAttendanceManageDao() {
		return attendanceManageDao;
	}

	public void setAttendanceManageDao(IAttendanceManageDao attendanceManageDao) {
		this.attendanceManageDao = attendanceManageDao;
	}

	public List<ClockRecords> queryClocks(Map<String, Object> queryMap) {
		return attendanceManageDao.queryClocks(queryMap);
	}

}
