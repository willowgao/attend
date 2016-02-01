package com.wgsoft.attendance.clock.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.attendance.clock.iservice.IAttendanceExcepService;
import com.wgsoft.attendance.clock.model.ClockExcepApprove;
import com.wgsoft.attendance.clock.model.ClockException;
import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.AttednUtils;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.system.iservice.IDataDictionaryService;

/**
 * @title： AttendanceExcepAction.java
 * @desc： 考勤异常处理 【未打卡、年假、事假、出差等】
 * @author： Willowgao
 * @date： 2015-10-28 上午10:25:16
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class AttendanceExcepAction extends BaseAction {

	private IAttendanceExcepService attendanceExcepService = (IAttendanceExcepService) getService("attendanceExcepService");

	private IDataDictionaryService dataDictionaryService = (IDataDictionaryService) getService("dataDictionaryService");

	/**
	 * 
	 */
	private static final long serialVersionUID = 8422844466149043082L;

	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc:审核页面初始化
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-2 下午08:31:11
	 */

	public String approve() throws Exception {
		return "approve";
	}

	/**
	 * @desc:查询异常打卡记录
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-10 下午01:31:39
	 */
	public String getExcepRecords() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userId", getUserInfo().getUserid());
		queryMap.put("startTime", clockRecords == null ? request.getParameter("startTime") : clockRecords
				.getStartTime());
		queryMap.put("endTime", clockRecords == null ? request.getParameter("endTime") : clockRecords.getEndTime());
		if (clockRecords != null) {
			queryMap.put("clockdate", clockRecords.getClockdate());
		}
		List<ClockRecords> list = attendanceExcepService.getExcepClockRecords(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc: 查询异常打卡审核记录
	 * @return
	 * @return String
	 * @date： 2015-11-2 下午08:36:01
	 */
	public String getExpApproves() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("expid", request.getParameter("expid"));
		queryMap.put("approverid", getUserInfo().getUserid());
		queryMap.put("startTime", clockRecords == null ? request.getParameter("startTime") : clockRecords
				.getStartTime());
		queryMap.put("endTime", clockRecords == null ? request.getParameter("endTime") : clockRecords.getEndTime());
		queryMap.put("clockRecords", clockRecords);
		List<ClockExcepApprove> list = attendanceExcepService.getExpApproves(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	public String initStatus() throws Exception {
		Map<String, String> dataMap = dataDictionaryService.getDataDictionarysByKey("APPROVER_STATUS");
		String leavetype = SysConstants.LeaveType.CLOCK_EXCEPTION_DONOT;
		String status = SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE;
		this.renderText(response, JSONUtil.serialize(AttednUtils.getStatusByLeaveType(leavetype, status, dataMap)));
		return null;
	}

	/**
	 * 保存异常处理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveExcep() throws Exception {
		ClockException clockExp = new ClockException();
		clockExp.setClockdate(clockRecords.getStartTime());
		clockExp.setExptype(SysConstants.CLOCK_EXCEPTION_DONOT);
		clockExp.setIsenable(SysConstants.ISENABLE_YES);
		clockExp.setClockdate(clockRecords.getClockdate());
		clockExp.setUserid(clockRecords.getUserid());
		clockExp.setComments(request.getParameter("comments"));
		Map<String, Object> saveMap = new HashMap<String, Object>();
		saveMap.put("clockExp", clockExp);
		saveMap.put("approver", request.getParameter("approver"));
		int rel = attendanceExcepService.saveExcep(saveMap);
		renderText(response, String.valueOf(rel));
		return null;
	}

	/**
	 * 保存异常处理信息
	 * 
	 * @return
	 * @throws Exception
	 */

	public String saveExcepApprove() throws Exception {
		Map<String, Object> saveMap = new HashMap<String, Object>();
		saveMap.put("userid", request.getParameter("userid"));
		saveMap.put("status", request.getParameter("status"));
		saveMap.put("expid", request.getParameter("expid"));
		saveMap.put("clockdate", request.getParameter("clockdate"));
		ClockExcepApprove approver = new ClockExcepApprove();
		approver.setApproverid(getUserInfo().getUserid());
		approver.setComments(request.getParameter("comments"));
		approver.setExptype(SysConstants.CLOCK_EXCEPTION_DONOT);
		approver.setStatus(request.getParameter("status"));
		approver.setExpid(request.getParameter("expid"));
		approver.setUserid(request.getParameter("userid"));
		saveMap.put("excepApp", approver);
		int rel = attendanceExcepService.saveApprove(saveMap);
		renderText(response, String.valueOf(rel));
		return null;
	}

	private ClockRecords clockRecords;

	public ClockRecords getClockRecords() {
		return clockRecords;
	}

	public void setClockRecords(ClockRecords clockRecords) {
		this.clockRecords = clockRecords;
	}

}
