package com.wgsoft.attendance.clock.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.attendance.clock.iservice.IAttendanceLeaveManageService;
import com.wgsoft.attendance.clock.model.Leaves;
import com.wgsoft.attendance.clock.model.LeavesApprove;
import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.AttednUtils;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;

/**
 * @title： AttendanceLeaveManageAction.java
 * @desc： 请假管理
 * @author： Willowgao
 * @date： 2015-10-30 下午12:03:17
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class AttendanceLeaveManageAction extends BaseAction {

	private IAttendanceLeaveManageService attendanceLeaveManageService = (IAttendanceLeaveManageService) getService("attendanceLeaveManageService");

	/**
	 * 
	 */
	private static final long serialVersionUID = -8751693772234775946L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc: 查询请假登录记录
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-10-30 下午02:10:20
	 */
	public String queryLeaves() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (leaves == null) {
			leaves = new Leaves();
			leaves.setApproverid(request.getParameter("approver"));
			if (RunUtil.isNotEmpty(request.getParameter("enddate"))) {
				leaves.setEnddate(DateUtil.string2Date(request.getParameter("enddate").toString(), DateUtil.YMD));
			}
			if (RunUtil.isNotEmpty(request.getParameter("startdate"))) {
				leaves.setStartdate(DateUtil.string2Date(request.getParameter("startdate").toString(), DateUtil.YMD));
			}
			leaves.setLeavetype(request.getParameter("leavetype"));
		}
		queryMap.put("leave", leaves);
		leaves.setUserid(getUserInfo().getUserid());
		List<Leaves> leaves = attendanceLeaveManageService.getLeaves(queryMap);
		this.renderText(response, transferListToJsonMapForTabel(leaves));
		return null;
	}

	/**
	 * @desc: 查询审核记录
	 * @throws Exception
	 * @return void
	 * @date： 2015-11-2 下午08:00:54
	 */
	public void queryLeavesApprover() throws Exception {
		String leaveId = request.getParameter("leaveid");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("leaveid", leaveId);
		List<LeavesApprove> leaves = attendanceLeaveManageService.getLeavesApprover(queryMap);
		this.renderText(response, transferListToJsonMapForTabel(leaves));
	}

	/**
	 * @desc: 请假管理
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-10-30 下午12:05:46
	 */
	public String leave() throws Exception {
		leaves.setUserid(getUserInfo().getUserid());
		int rel = attendanceLeaveManageService.saveLeaves(leaves);
		this.renderText(response, String.valueOf(rel));
		return null;
	}

	/**
	 * @desc: 请假管理
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-10-30 下午12:05:46
	 */
	public String delLeave() throws Exception {
		String ids = request.getParameter("ids");
		int rel = attendanceLeaveManageService.delLeaves(ids);
		this.renderText(response, String.valueOf(rel));
		return null;
	}

	/**
	 * 审核页面初始化
	 */
	public String approve() throws Exception {
		return "approve";
	}

	/**
	 * @desc:查询待审核的信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-2 下午01:29:01
	 */
	public String queryForApprover() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (leaves == null) {
			leaves = new Leaves();
			if (RunUtil.isNotEmpty(request.getParameter("enddate"))) {
				leaves.setEnddate(DateUtil.string2Date(request.getParameter("enddate").toString(), DateUtil.YMD));
			}
			if (RunUtil.isNotEmpty(request.getParameter("startdate"))) {
				leaves.setStartdate(DateUtil.string2Date(request.getParameter("startdate").toString(), DateUtil.YMD));
			}
			leaves.setLeavetype(request.getParameter("leavetype"));
		}
		// 设置查询条件
		AttednUtils.setApproverStatus(leaves, getUserInfo());
		queryMap.put("leave", leaves);
		List<Leaves> leaves = attendanceLeaveManageService.getLeaves(queryMap);
		this.renderText(response, transferListToJsonMapForTabel(leaves));
		return null;
	}

	/**
	 * 通过业务类型、业务输的当前状态、得到可以操作的状态下拉列表 (审核状态)
	 * 
	 * @desc:
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-2 下午04:27:42
	 */
	public String initAppStatus() throws Exception {
		Map<String, String> dataMap = getDataDictionaryService().getDataDictionarysByKey(SysConstants.ApproverStatus.DICTIONARY_KEY);
		String leavetype = request.getParameter("leavetype");
		String status = request.getParameter("status");
		this.renderText(response, JSONUtil.serialize(AttednUtils.getStatusByLeaveType(leavetype, status, dataMap)));
		return null;
	}

	/**
	 * @desc: 通过当前状态、业务类型，得到当前的可操作的下拉列表（审核人员）
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-2 下午05:51:10
	 */
	public String initApprover() throws Exception {
		String leavetype = request.getParameter("leavetype");
		String userid = request.getParameter("userid");
		String status = request.getParameter("status");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("leavetype", leavetype);
		queryMap.put("status", status);
		queryMap.put("user", getUserInfo());
		queryMap.put("userid", userid);
		List<Map<String, String>> users = attendanceLeaveManageService.getUserForApprover(queryMap);
		this.renderText(response, JSONUtil.serialize(users));
		return null;
	}

	/**
	 * @desc: 保存审核信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-10-30 下午12:07:51
	 */
	public String saveApprove() throws Exception {
		String nowstatus = request.getParameter("nowstatus");
		String nowleavetype = request.getParameter("nowleavetype");
		String leaveid = request.getParameter("leaveid");
		String approverid = request.getParameter("approverid");
		String comments = request.getParameter("comments");

		Map<String, Object> saveMap = new HashMap<String, Object>();
		LeavesApprove approve = new LeavesApprove();
		saveMap.put("nowstatus", nowstatus);
		saveMap.put("nowleavetype", nowleavetype);
		saveMap.put("leaveid", leaveid);
		saveMap.put("approverid", approverid);
		saveMap.put("comments", comments);
		approve.setUserid(getUserInfo().getUserid());
		approve.setApproveid(getUserInfo().getUserid());
		saveMap.put("approve", approve);
		int rel = attendanceLeaveManageService.saveApprove(saveMap);
		this.renderText(response, String.valueOf(rel));
		return null;
	}

	private Leaves leaves;

	public Leaves getLeaves() {
		return leaves;
	}

	public void setLeaves(Leaves leaves) {
		this.leaves = leaves;
	}

}
