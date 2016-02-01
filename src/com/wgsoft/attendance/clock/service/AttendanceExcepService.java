package com.wgsoft.attendance.clock.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wgsoft.attendance.clock.idao.IAttendanceExcepDao;
import com.wgsoft.attendance.clock.iservice.IAttendanceExcepService;
import com.wgsoft.attendance.clock.model.ClockExcepApprove;
import com.wgsoft.attendance.clock.model.ClockException;
import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.common.utils.SysConstants;

/**
 * @title： AttendanceExcepService.java
 * @desc： 考勤异常处理 【未打卡、年假、事假、出差等】
 * @author： Willowgao
 * @date： 2015-10-28 下午01:08:57
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class AttendanceExcepService implements IAttendanceExcepService {

	private static Log log = LogFactory.getLog(AttendanceExcepService.class);

	/**
	 * 实例
	 */
	private IAttendanceExcepDao attendanceExcepDao;

	/**
	 * @see com.wgsoft.attendance.clock.iservice.IAttendanceExcepService#getExcepClockRecords()
	 */
	public List<ClockRecords> getExcepClockRecords(Map<String, Object> queryMap) {
		return attendanceExcepDao.getExcepClockRecords(queryMap);
	}

	/**
	 * @see com.wgsoft.attendance.clock.iservice.IAttendanceExcepService#saveExcep(Map)
	 */
	public int saveExcep(Map<String, Object> saveMap) {
		ClockException excep = (ClockException) saveMap.get("clockExp");
		ClockExcepApprove expApp = new ClockExcepApprove();
		expApp.setApproverid((String) saveMap.get("approver"));
		expApp.setComments(excep.getComments());
		try {
			attendanceExcepDao.insert(excep);
		} catch (RuntimeException e) {
			if (log.isErrorEnabled()) {
				log.error("#saveExcep.insert(excep) 异常");
			}
			return SysConstants.ERROR;
		}
		expApp.setExpid(excep.getExpid());
		expApp.setExptype(excep.getExptype());
		// 申报状态
		expApp.setStatus(SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE);
		expApp.setUserid(excep.getUserid());
		expApp.setAppdate(new Date());
		try {
			attendanceExcepDao.insert(expApp);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("#saveExcep.insert(expApp)常");
			}
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

	public IAttendanceExcepDao getAttendanceExcepDao() {
		return attendanceExcepDao;
	}

	public void setAttendanceExcepDao(IAttendanceExcepDao attendanceExcepDao) {
		this.attendanceExcepDao = attendanceExcepDao;
	}

	public List<ClockExcepApprove> getExpApproves(Map<String, Object> queryMap) {
		return attendanceExcepDao.getExpApproves(queryMap);
	}

	public int saveApprove(Map<String, Object> saveMap) {

		// 查询写审核记录
		ClockExcepApprove excepApp = (ClockExcepApprove) saveMap.get("excepApp");
		try {
			// 如果存在，则需要更新的
			List<ClockRecords> clocks = attendanceExcepDao.getClockRecords(saveMap);
			if (clocks != null && clocks.size() > 0) {
				saveMap.put("exists", "exists");
			}
			// 写入正常的打卡记录
			attendanceExcepDao.insertNormalRecords(saveMap);
			// 修改异常记录未正常记录
			attendanceExcepDao.updateStatus(saveMap);
			excepApp.setAppdate(new Date());
			attendanceExcepDao.insert(excepApp);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("IAttendanceExcepService#saveApprove.insert(excep) 异常");
			}
			
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

}
