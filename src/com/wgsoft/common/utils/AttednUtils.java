package com.wgsoft.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.Leaves;
import com.wgsoft.system.service.DataDictionaryService;
import com.wgsoft.user.model.UserInfo;

/**
 * @title： AttednUtils
 * @desc：考勤工具类
 * @author： Willowgao
 * @date： 2015-11-2 下午02:17:46
 * @version： V1.0<br>
 * @versioninfo：慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class AttednUtils {

	/**
	 * 
	 * @desc:通过用户类型，设置可以查询的审批信息
	 * @param leaves
	 *            Leaves
	 * @param user
	 *            UserInfo
	 * @return void
	 * @date： 2015-11-2 下午01:38:46
	 */
	public static void setApproverStatus(Leaves leaves, UserInfo user) {
		// 查询审核人员为自己的信息
		leaves.setApproverid(user.getUserid());
		// 查询审核状态和自己审核等级匹配的信息
		if (RunUtil.isNotEmpty(user.getRoletype()) && user.getRoletype().equals(SysConstants.RoleType.ROLETYPE_FIRST)) {
			// 如果为一级审核、则查询审核状态为初审通过的，依次查询
			leaves.setStatus(SysConstants.ApproverStatus.APPROVER_STATUS_PASS);
		} else if (RunUtil.isNotEmpty(user.getRoletype()) && user.getRoletype().equals(SysConstants.RoleType.ROLETYPE_SECOND)) {
			// 如果为二级审核、则查询审核状态为一级审核通过的，依次查询
			leaves.setStatus(SysConstants.ApproverStatus.APPROVER_STATUS_FIRSTPASS);
		} else if (RunUtil.isNotEmpty(user.getRoletype()) && user.getRoletype().equals(SysConstants.RoleType.ROLETYPE_THIRD)) {
			// 如果为三级审核、则查询审核状态为二级审核通过的，依次查询
			leaves.setStatus(SysConstants.ApproverStatus.APPROVER_STATUS_SECONDPASS);
		} else {
			// 都不是，则查询填报状态
			leaves.setStatus(SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE);
		}
	}

	/**
	 * @desc:通过请假类型和当前状态,设置审核通过后的下一业务状态
	 * @param leaveType
	 *            String 请假类型
	 * @param status
	 *            String 当前状态
	 * @param dataMap
	 * @return
	 * @return List<Map<String,String>>
	 * @date： 2015-11-2 下午05:33:23
	 */
	public static List<Map<String, String>> getStatusByLeaveType(String leaveType, String status,
			Map<String, String> dataMap) {
		List<Map<String, String>> reApproveStatus = new ArrayList<Map<String, String>>();
		String approverType = DataDictionaryService.approverSet.get(leaveType);
		// 不管是任何角色，可以选择的下一步操作是，审核通过、审核不通过
		Map<String, String> data = new HashMap<String, String>();
		// 如果业务类型需要初审,可以选择的业务只能是初始审通过、和不通过
		if (approverType.equals(SysConstants.RoleType.ROLETYPE_TRIAL)
				&& status.equals(SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE)) {
			data.put("id", SysConstants.ApproverStatus.APPROVER_STATUS_PASS);
			data.put("text", dataMap.get(SysConstants.ApproverStatus.APPROVER_STATUS_PASS));
			reApproveStatus.add(data);
			// 如果业务类型为一级审核
		} else if (approverType.equals(SysConstants.RoleType.ROLETYPE_FIRST)) {
			// 当前状态填报,可以选择的业务可以状态初审通过、和不通过
			if (status.equals(SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE)) {
				data.put("id", SysConstants.ApproverStatus.APPROVER_STATUS_PASS);
				data.put("text", dataMap.get(SysConstants.ApproverStatus.APPROVER_STATUS_PASS));
				// 当前状态初审通过,可以选择的业务可以一审通过通过、和不通过
			} else if (status.equals(SysConstants.ApproverStatus.APPROVER_STATUS_PASS)) {
				data.put("id", SysConstants.ApproverStatus.APPROVER_STATUS_FIRSTPASS);
				data.put("text", dataMap.get(SysConstants.ApproverStatus.APPROVER_STATUS_FIRSTPASS));
			}

			reApproveStatus.add(data);
			// 如果业务类型为二级审核
		} else if (approverType.equals(SysConstants.RoleType.ROLETYPE_SECOND)) {
			// 当前状态填报,可以选择的业务可以状态初审通过、和不通过
			if (status.equals(SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE)) {
				data.put("id", SysConstants.ApproverStatus.APPROVER_STATUS_PASS);
				data.put("text", dataMap.get(SysConstants.ApproverStatus.APPROVER_STATUS_PASS));
				// 当前初审通过,可以选择的业务可以状态一审通过通过、和不通过
			} else if (status.equals(SysConstants.ApproverStatus.APPROVER_STATUS_PASS)) {
				data.put("id", SysConstants.ApproverStatus.APPROVER_STATUS_FIRSTPASS);
				data.put("text", dataMap.get(SysConstants.ApproverStatus.APPROVER_STATUS_FIRSTPASS));
				// 当前一审通过通过,可以选择的业务可以状态二审通过通过、和不通过
			} else if (status.equals(SysConstants.ApproverStatus.APPROVER_STATUS_FIRSTPASS)) {
				data.put("id", SysConstants.ApproverStatus.APPROVER_STATUS_SECONDPASS);
				data.put("text", dataMap.get(SysConstants.ApproverStatus.APPROVER_STATUS_SECONDPASS));
			}

			reApproveStatus.add(data);
			// 如果业务类型为三级审核
		} else if (approverType.equals(SysConstants.RoleType.ROLETYPE_THIRD)) {
			// 当前状态填报,可以选择的业务可以状态初审通过、和不通过
			if (status.equals(SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE)) {
				data.put("id", SysConstants.ApproverStatus.APPROVER_STATUS_PASS);
				data.put("text", dataMap.get(SysConstants.ApproverStatus.APPROVER_STATUS_PASS));
				// 当前初审通过,可以选择的业务可以状态一审通过通过、和不通过
			} else if (status.equals(SysConstants.ApproverStatus.APPROVER_STATUS_PASS)) {
				data.put("id", SysConstants.ApproverStatus.APPROVER_STATUS_FIRSTPASS);
				data.put("text", dataMap.get(SysConstants.ApproverStatus.APPROVER_STATUS_FIRSTPASS));
				// 当前一审通过通过,可以选择的业务可以状态二审通过通过、和不通过
			} else if (status.equals(SysConstants.ApproverStatus.APPROVER_STATUS_FIRSTPASS)) {
				data.put("id", SysConstants.ApproverStatus.APPROVER_STATUS_SECONDPASS);
				data.put("text", dataMap.get(SysConstants.ApproverStatus.APPROVER_STATUS_SECONDPASS));
				// 当前二审通过通过,可以选择的业务可以状态二审通过通过、和不通过
			} else if (status.equals(SysConstants.ApproverStatus.APPROVER_STATUS_SECONDPASS)) {
				data.put("id", SysConstants.ApproverStatus.APPROVER_STATUS_THIRDPASS);
				data.put("text", dataMap.get(SysConstants.ApproverStatus.APPROVER_STATUS_THIRDPASS));
			}
			reApproveStatus.add(data);
		}
		data = new HashMap<String, String>();
		data.put("id", SysConstants.ApproverStatus.APPROVER_STATUS_NOTPASS);
		data.put("text", dataMap.get(SysConstants.ApproverStatus.APPROVER_STATUS_NOTPASS));
		reApproveStatus.add(data);
		return reApproveStatus;
	}
}
