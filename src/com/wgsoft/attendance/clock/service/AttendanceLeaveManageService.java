package com.wgsoft.attendance.clock.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wgsoft.attendance.clock.idao.IAttendanceLeaveManageDao;
import com.wgsoft.attendance.clock.iservice.IAttendanceLeaveManageService;
import com.wgsoft.attendance.clock.model.Leaves;
import com.wgsoft.attendance.clock.model.LeavesApprove;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.user.model.UserInfo;

/**
 * 
 * @title： AttendanceLeaveManageService.java
 * @desc： 请假管理
 * @author： Willowgao
 * @date： 2015-10-30 下午01:21:21
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class AttendanceLeaveManageService implements IAttendanceLeaveManageService {

	private static Log log = LogFactory.getLog(AttendanceLeaveManageService.class);

	private IAttendanceLeaveManageDao attendanceLeaveManageDao;

	/**
	 * @see com.wgsoft.attendance.clock.iservice.IAttendanceLeaveManageService#delLeaves(String)
	 */
	public int delLeaves(String ids) {
		String[] id = ids.split(",");
		String delIds = RunUtil.transObjAsSqlInStr(id);
		return attendanceLeaveManageDao.delLeaves(delIds);
	}

	/**
	 * @see com.wgsoft.attendance.clock.iservice.IAttendanceLeaveManageService#getLeaves(Map)
	 */
	public List<Leaves> getLeaves(Map<String, Object> queryMap) {
		return attendanceLeaveManageDao.getLeaves(queryMap);
	}

	/**
	 * @see com.wgsoft.attendance.clock.iservice.IAttendanceLeaveManageService#getLeavesApprover(Map)
	 */
	public List<LeavesApprove> getLeavesApprover(Map<String, Object> queryMap) {
		return attendanceLeaveManageDao.getLeavesApprover(queryMap);
	}

	/**
	 * @see com.wgsoft.attendance.clock.iservice.IAttendanceLeaveManageService#saveLeaves(Leaves)
	 */
	public int saveLeaves(Leaves leave) {
		try {
			leave.setAppdate(new Date());
			leave.setStatus(SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE);
			attendanceLeaveManageDao.insert(leave);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("#saveLeaves.insert(leave)常");
			}
			return SysConstants.ERROR;
		}
		LeavesApprove approve = new LeavesApprove();
		// 赋值
		try {
			BeanUtils.copyProperties(approve, leave);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			approve.setAppdate(new Date());
			attendanceLeaveManageDao.insert(approve);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("#saveLeaves.insert(approve)常");
			}
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

	/**
	 * @see com.wgsoft.attendance.clock.iservice.IAttendanceLeaveManageService#getUserForApprover(Map)
	 */
	public List<Map<String, String>> getUserForApprover(Map<String, Object> queryMap) {
		List<Map<String, String>> users = new ArrayList<Map<String, String>>();
		List<UserInfo> list = attendanceLeaveManageDao.getUserForApprover(queryMap);
		Map<String, String> userMap = null;
		for (UserInfo userinfo : list) {
			userMap = new HashMap<String, String>();
			userMap.put("id", userinfo.getUserid());
			userMap.put("text", userinfo.getUsername());
			users.add(userMap);
		}
		return users;
	}

	/**
	 * @see com.wgsoft.attendance.clock.iservice.IAttendanceLeaveManageService#saveApprove(LeavesApprove)
	 */
	public int saveApprove(Map<String, Object> saveMap) {
		String nowstatus = (String) saveMap.get("nowstatus");
		String leaveid = (String) saveMap.get("leaveid");
		String approverid = (String) saveMap.get("approverid");
		String comments = (String) saveMap.get("comments");
		String nowleavetype = (String) saveMap.get("nowleavetype");
		// 更新主表的核定人员、状态等信息
		try {
			attendanceLeaveManageDao.updateStatus(leaveid, nowstatus, approverid);
		} catch (Exception e1) {
			if (log.isErrorEnabled()) {
				log.error("#saveApprove.updateStatus(leaveid, nowstatus, approverid)异常");
			}
			return SysConstants.ERROR;
		}
		// 写入审核记录表记录
		try {
			LeavesApprove approve = (LeavesApprove) saveMap.get("approve");
			approve.setComments(comments);
			approve.setLeavetype(nowleavetype);
			approve.setLeaveid(leaveid);
			approve.setStatus(nowstatus);
			approve.setAppdate(new Date());
			// 下级审核人员
			approve.setApproverid(approverid);
			attendanceLeaveManageDao.insert(approve);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("#saveApprove.insert(approve)异常");
			}
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

	public IAttendanceLeaveManageDao getAttendanceLeaveManageDao() {
		return attendanceLeaveManageDao;
	}

	public void setAttendanceLeaveManageDao(IAttendanceLeaveManageDao attendanceLeaveManageDao) {
		this.attendanceLeaveManageDao = attendanceLeaveManageDao;
	}

}
