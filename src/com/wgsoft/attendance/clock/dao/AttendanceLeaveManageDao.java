package com.wgsoft.attendance.clock.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.idao.IAttendanceLeaveManageDao;
import com.wgsoft.attendance.clock.model.Leaves;
import com.wgsoft.attendance.clock.model.LeavesApprove;
import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.user.model.UserInfo;

/**
 * @title： AttendanceLeaveManageDao.java
 * @desc：请假管理
 * @author： Willowgao
 * @date： 2015-10-30 下午01:23:48
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
@SuppressWarnings("unchecked")
public class AttendanceLeaveManageDao extends BaseDao implements IAttendanceLeaveManageDao {

	/**
	 * @see com.wgsoft.attendance.clock.idao.IAttendanceLeaveManageDao#getLeaves(Map)
	 */
	public List<Leaves> getLeaves(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT * FROM leaves where 1=1");
		Leaves leave = (Leaves) queryMap.get("leave");
		if (leave != null) {
			if (RunUtil.isNotEmpty(leave.getUserid())) {
				sql.append(" and userid = '").append(leave.getUserid()).append("'");
			}
			if (RunUtil.isNotEmpty(leave.getStatus())) {
				sql.append(" and status = '").append(leave.getStatus()).append("'");
			}
			if (RunUtil.isNotEmpty(leave.getLeavetype())) {
				sql.append(" and leavetype = '").append(leave.getLeavetype()).append("'");
			}
			if (RunUtil.isNotEmpty(leave.getApproverid())) {
				sql.append(" and approverid = '").append(leave.getApproverid()).append("'");
			}
			if (RunUtil.isNotEmpty(leave.getStartdate())) {
				sql.append(" and to_char(appdate,'yyyy-mm-dd') >= '").append(
						DateUtil.date2String(leave.getStartdate(), DateUtil.YMD)).append("'");
			}
			if (RunUtil.isNotEmpty(leave.getEnddate())) {
				sql.append(" and to_char(appdate,'yyyy-mm-dd') <= '").append(
						DateUtil.date2String(leave.getEnddate(), DateUtil.YMD)).append("'");
			}
		}

		return getSqlList_(sql.toString(), Leaves.class);
	}

	/**
	 * @see com.wgsoft.attendance.clock.idao.IAttendanceLeaveManageDao#delLeaves(String)
	 */
	public int delLeaves(String ids) {
		StringBuffer sql = new StringBuffer(" DELETE FROM leaves ");
		sql.append(" WHERE LEAVEID IN (").append(ids).append(") and status in ('1','3')");
		return getSqlUpdate(sql.toString());
	}

	/**
	 * @see com.wgsoft.attendance.clock.idao.IAttendanceLeaveManageDao#getUserForApprover(Map)
	 */
	public List<UserInfo> getUserForApprover(Map<String, Object> queryMap) {
		String status = (String) queryMap.get("status");
		String leaveType = (String) queryMap.get("leavetype");
		UserInfo user = (UserInfo) queryMap.get("user");
		StringBuffer sql = new StringBuffer(
				" SELECT userid,username FROM USERINFO A, ROLEINFO B , leave_approve_setting c WHERE A.ROLEID = B.ROLEID  ");
		sql.append("  AND b.roletype <= c.approver AND c.approvetype ='LEAVE' AND ROLETYPE = '").append(
				Integer.valueOf(status).intValue() + 2);
		sql.append("' AND a.userdeptid ='").append(user.getUserdeptid()).append("'");
		// 根据类型获取需要处理人员
		sql.append(" AND c.leavetype ='").append(leaveType).append("'");
		// 核审人员，不能是申报人员本人
		sql.append(" AND a.userid !='").append(queryMap.get("userid")).append("'");
		return getSqlList_(sql.toString(), UserInfo.class);
	}

	/**
	 * @see com.wgsoft.attendance.clock.idao.IAttendanceLeaveManageDao#updateStatus(String,
	 *      String, String)
	 */
	public int updateStatus(String leaveId, String status, String approver) {
		StringBuffer sql = new StringBuffer(" UPDATE leaves set status ='").append(status).append("',");
		sql.append("   approverid ='").append(approver).append("'");
		sql.append(" WHERE LEAVEID ='").append(leaveId).append("'");
		return getSqlUpdate(sql.toString());
	}

	/**
	 * @see com.wgsoft.attendance.clock.idao.IAttendanceLeaveManageDao#getLeavesApprover(Map)
	 */
	public List<LeavesApprove> getLeavesApprover(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT * FROM leaves_approve where 1=1");
		sql.append(" and  LEAVEID ='").append(queryMap.get("leaveid")).append("' order by appdate ");
		return getSqlList_(sql.toString(), LeavesApprove.class);
	}

}
