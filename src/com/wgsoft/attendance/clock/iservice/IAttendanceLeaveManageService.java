package com.wgsoft.attendance.clock.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.Leaves;
import com.wgsoft.attendance.clock.model.LeavesApprove;

/**
 * @title： IAttendanceLeaveManageService.java
 * @desc： 请假管理
 * @author： Willowgao
 * @date： 2015-10-30 下午12:28:29
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IAttendanceLeaveManageService {

	/**
	 * @desc:查询请假记录
	 * @param queryMap
	 * @return
	 * @return List<Leaves>
	 * @date： 2015-10-30 下午01:17:35
	 */
	List<Leaves> getLeaves(Map<String, Object> queryMap);

	/**
	 * @desc:查询审核记录
	 * @param queryMap
	 * @return
	 * @return List<LeavesApprove>
	 * @date： 2015-11-2 下午08:02:28
	 */
	List<LeavesApprove> getLeavesApprover(Map<String, Object> queryMap);

	/**
	 * @desc: 保存请假信息
	 * @param leave
	 * @return
	 * @return int
	 * @date： 2015-10-30 下午01:19:03
	 */
	int saveLeaves(Leaves leave);

	/**
	 * @desc: 删除请假申请记录
	 * @param ids
	 * @return
	 * @return int
	 * @date： 2015-10-30 下午01:20:40
	 */
	int delLeaves(String ids);

	/**
	 * @desc:根据当前用户,和当前业务状态，查询下一步可审核用户
	 * @param Map
	 *            <String, Object> queryMap
	 * @return
	 * @return List<Map<String,String>>
	 * @date： 2015-11-2 下午07:26:02
	 */
	List<Map<String, String>> getUserForApprover(Map<String, Object> queryMap);

	/**
	 * @desc: 保存审核信息
	 * @param Map
	 *            <String,Object> saveMap
	 * @return
	 * @return int
	 * @date： 2015-11-2 下午06:04:01
	 */
	int saveApprove(Map<String, Object> saveMap);

}
