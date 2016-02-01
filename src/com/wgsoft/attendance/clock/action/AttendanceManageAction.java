package com.wgsoft.attendance.clock.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.attendance.clock.iservice.IAttendanceManageService;
import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.attendance.clock.model.ClockSetting;
import com.wgsoft.common.action.BaseAction;

/**
 * @title： AttendanceManagerAction.java
 * @desc：考勤管理
 * @author： Willowgao
 * @date： 2015-9-10 下午01:17:21
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class AttendanceManageAction extends BaseAction {

	private IAttendanceManageService attendanceManageService = (IAttendanceManageService) getService("attendanceManageService");

	/**
	 * 
	 */
	private static final long serialVersionUID = 8411493806342383696L;

	/**
	 * @desc:查询打卡记录
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-10 下午01:31:39
	 */
	public String getClockRecords() throws Exception {
		ClockRecords clock = attendanceManageService.queryClockRecords(getUserInfo().getUserid());
		renderText(response, JSONUtil.serialize(clock));
		return null;
	}

	/**
	 * @desc:保存打卡记录
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-10 下午03:27:27
	 */
	public String saveClock() throws Exception {
		JSONObject params = null;
		try {
			params = JSONObject.fromObject(request.getParameter("params"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClockRecords clock = (ClockRecords) JSONObject.toBean(params, ClockRecords.class);
		clock.setUserid(getUserInfo().getUserid());
		attendanceManageService.saveClockRecord(clock);
		renderText(response, "1");
		return null;
	}

	/**
	 * @desc: 初始化页面 考勤时间管理
	 * @return String
	 * @throws Exception
	 * @return String
	 * @date： 2015-10-25 下午03:38:15
	 */
	public String timeManage() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc: 保存考勤时间管理录入数据
	 * @return String
	 * @throws Exception
	 * @return String
	 * @date： 2015-10-25 下午03:38:21
	 */
	public String saveTime() throws Exception {
		attendanceManageService.saveClockSetting(clockSetting);
		renderText(response, "1");
		return null;
	}

	/**
	 * @desc: 查询考勤时间管理录入数据
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-10-25 下午03:38:51
	 */
	public String querySettings() throws Exception {
		List<ClockSetting> list = attendanceManageService.queryClockSettings(clockSetting);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * 通过系统当前时间检查,目前的所属的打开时间
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryTimesForNow() throws Exception {
		ClockSetting clockSet = attendanceManageService.queryTimesForNow();
		renderText(response, JSONUtil.serialize(clockSet));
		return null;
	}
	
	
	

	/**
	 * 查询打卡记录
	 * @return
	 * @throws Exception
	 */
	public String queryClocks() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userId", getUserInfo().getUserid());
		queryMap.put("date", getUserInfo().getUserid());
		List<ClockRecords> list = attendanceManageService.queryClocks(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	private ClockSetting clockSetting;

	public ClockSetting getClockSetting() {
		return clockSetting;
	}

	public void setClockSetting(ClockSetting clockSetting) {
		this.clockSetting = clockSetting;
	}

}
