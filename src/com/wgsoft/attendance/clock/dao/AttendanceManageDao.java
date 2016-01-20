package com.wgsoft.attendance.clock.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.idao.IAttendanceManageDao;
import com.wgsoft.attendance.clock.model.ClockException;
import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.attendance.clock.model.ClockRecordsId;
import com.wgsoft.attendance.clock.model.ClockSetting;
import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;

/**
 * @title： AttendanceManageDao.java
 * @desc： 考勤管理
 * @author： Willowgao
 * @date： 2015-9-10 下午01:43:48
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
@SuppressWarnings("unchecked")
public class AttendanceManageDao extends BaseDao implements IAttendanceManageDao {

	public ClockRecords queryClockRecords(String userId) {
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM CLOCKREOCRDS WHERE 1=1 AND TO_CHAR(CLOCKDATE,'YYYY-MM-DD') = TO_CHAR(SYSDATE,'YYYY-MM-DD')");
		if (RunUtil.isNotEmpty(userId)) {
			sql.append(" AND USERID ='").append(userId).append("'");
		}
		List<ClockRecords> list = getSqlList_(sql.toString(), ClockRecords.class);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public int saveClockRecord(ClockRecords clock) {
		ClockRecordsId cId = new ClockRecordsId();
		clock.setClockdate(DateUtil.date2Format(new Date(), DateUtil.YMD));
		cId.setClockdate(clock.getClockdate());
		cId.setUserid(clock.getUserid());
		clock.setId(cId);
		try {
			if (queryClockRecords(clock.getUserid()) != null) {
				update(clock);
			} else {
				insert(clock);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

	/**
	 * 查询是否存在记录
	 */
	public List<ClockSetting> queryClockSettings(ClockSetting cs) {
		StringBuffer sql = new StringBuffer("select * from CLOCK_SETTING where 1=1 ");
		if (RunUtil.isNotEmpty(cs) && RunUtil.isNotEmpty(cs.getStartTime())) {
			sql.append(" and to_char(starttime,'yyyy-mm-dd') <= '").append(
					DateUtil.date2String(cs.getStartTime(), DateUtil.YMD)).append("'");
		}
		if (RunUtil.isNotEmpty(cs) && RunUtil.isNotEmpty(cs.getEndTime())) {
			sql.append(" and to_char(endtime,'yyyy-mm-dd') >= '").append(
					DateUtil.date2String(cs.getEndTime(), DateUtil.YMD)).append("'");
		}
		sql.append(" order by starttime");
		List<ClockSetting> list = getSqlList_(sql.toString(), ClockSetting.class);
		return list;
	}

	/**
	 * 保存修改的打卡时间
	 */
	public int saveClockSetting(ClockSetting cs) {
		StringBuffer sql = new StringBuffer("UPDATE CLOCK_SETTING SET isenable ='" + SysConstants.ISENABLE_NO
				+ "' WHERE STARTTIME BETWEEN");
		sql.append("  to_date('" + DateUtil.date2String(cs.getStartTime(), DateUtil.YMD) + "','yyyy-mm-dd') ");
		sql.append("  AND to_date('" + DateUtil.date2String(cs.getEndTime(), DateUtil.YMD) + "','yyyy-mm-dd')");
		try {
			// 更新存在在此区间的数据
			updateBySql(sql.toString());
			// 保存最新的数据
			cs.setIsEnable(SysConstants.ISENABLE_YES);
			insert(cs);
		} catch (Exception e) {
			e.printStackTrace();
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

	/**
	 * 查询当前时间所属的打卡时间
	 */
	public ClockSetting queryTimesForNow() {
		ClockSetting cs = new ClockSetting();
		StringBuffer sql = new StringBuffer(
				" SELECT *  FROM CLOCK_SETTING WHERE ISENABLE = '0' AND SYSDATE BETWEEN STARTTIME AND ENDTIME ");
		List<ClockSetting> list = getSqlList_(sql.toString(), ClockSetting.class);
		if (list != null && list.size() > 0) {
			cs = list.get(0);
		}
		return cs;
	}

	public int saveClockException(ClockException ce) {
		try {
			// 更新存在在此区间的数据
			insert(ce);
		} catch (Exception e) {
			e.printStackTrace();
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

	public List<ClockRecords> queryClocks(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT a.clockdate ,b.amsb,b.amxb,b.pmsb,b.pmxb FROM clockdate_setting a,");
		sql.append(" clockreocrds b WHERE a.clockdate = b.clockdate(+) AND a.clockyear = to_char(SYSDATE,'yyyy')");
		sql.append(" AND a.isneed = '0'");
		if(RunUtil.isNotEmpty(queryMap.get("userId"))){
			sql.append(" and '").append(queryMap.get("userId")).append("'  = b.userid(+) ");
		}
		if(RunUtil.isNotEmpty(queryMap.get("date"))){
			sql.append(" and to_char(a.clockdate,'yyyy-mm') = to_char(sysdate,'yyyy-mm') ");
		}
		
		sql.append("ORDER BY a.clockdate");
		List<ClockRecords> list = getSqlList_(sql.toString(), ClockRecords.class);
		return list;
	}

}
