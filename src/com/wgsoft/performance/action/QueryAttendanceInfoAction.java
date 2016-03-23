package com.wgsoft.performance.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.wgsoft.attendance.clock.iservice.IAttendanceExcepService;
import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.EchartsUtils;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.diary.model.DiaryDaily;
import com.wgsoft.diary.model.EchartsOfBar;
import com.wgsoft.diary.model.EchartsOfPie;
import com.wgsoft.performance.iservice.IQueryAttendanceInfoService;
import com.wgsoft.performance.model.PositionStatement;

/**
 * 
 * @title： QueryAttendanceInfoAction.java
 * @desc：查询出勤情况
 * @author： Willowgao
 * @date： 2016-2-2 上午09:23:08
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryAttendanceInfoAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1324230464685885561L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 页面初始化
	 */
	public String queryAttends() throws Exception {
		return "queryAttends";
	}
	
	
	public void initForm() throws Exception {
		if (clockRecords == null) {
			clockRecords = new ClockRecords();
		} 
		clockRecords.setUserid(getUserInfo().getUserid());
		clockRecords.setFlag(getUserInfo().getRoletype());
		renderText(response, transferVoToForm("clockRecordsForm", clockRecords, ClockRecords.class));
	}
	

	/**
	 * @desc:查询出勤情况
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-2-2 上午09:24:58
	 */
	public String queryAttend() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("clockdate", request.getParameter("clockdate"));
		queryMap.put("dept", request.getParameter("dept"));
		queryMap.put("user", getUserInfo());
		List<ClockRecords> list = getQueryAttendanceInfoService().queryAttend(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:查询出勤情况
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-2-2 上午09:24:58
	 */
	public String queryAttendsList() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (RunUtil.isEmpty(request.getParameter("userid"))) {
			queryMap.put("userId", getUserInfo().getUserid());
		}else{
			queryMap.put("userId", request.getParameter("userid"));
		}
		queryMap.put("startTime", clockRecords == null ? request.getParameter("startTime") : DateUtil.date2String(
				clockRecords.getStartTime(), DateUtil.YMD));
		queryMap.put("endTime", clockRecords == null ? request.getParameter("endTime") : DateUtil.date2String(
				clockRecords.getEndTime(), DateUtil.YMD));
		if (clockRecords != null) {
			queryMap.put("clockdate", clockRecords.getClockdate());
		}
		List<ClockRecords> list = getAttendanceExcepService().getExcepClockRecords(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc: 查询部门出勤率排名
	 * @return
	 * @return String
	 * @date： 2016-2-2 下午01:37:43
	 */
	public String queryOrgRanking() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("clockdate", request.getParameter("clockdate"));
		queryMap.put("dept", request.getParameter("dept"));
		queryMap.put("user", getUserInfo());
		List<EchartsOfBar> list = getQueryAttendanceInfoService().queryOrgRanking(queryMap);
		List<EchartsOfBar> jsonList = new ArrayList<EchartsOfBar>();
		if (list != null && list.size() > 0) {
			String[] xComments = new String[list.size()];
			String[] xData = new String[list.size()];
			String[] groupName = new String[list.size()];
			int i = 0;
			EchartsOfBar jsonBar = new EchartsOfBar();
			BeanUtils.copyProperties(jsonBar, list.get(0));

			for (EchartsOfBar bar : list) {
				xComments[i] = bar.getXcomments();
				xData[i] = bar.getXdata().toString();
				groupName[i] = bar.getDataname();
				i++;
			}
			jsonBar.setGroupName(groupName);
			jsonBar.setxAxis(xComments);
			jsonBar.setData(xData);
			jsonList.add(jsonBar);
		}
		renderText(response, EchartsUtils.getBarCompare(jsonList).toString());
		return null;
	}

	/**
	 * @desc: 查询部门出勤率排名
	 * @return
	 * @return String
	 * @date： 2016-2-2 下午01:37:43
	 */
	public String queryDeptRanking() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("clockdate", request.getParameter("clockdate"));
		queryMap.put("dept", request.getParameter("dept"));
		queryMap.put("user", getUserInfo());
		List<EchartsOfPie> list = getQueryAttendanceInfoService().queryDeptRanking(queryMap);
		List<EchartsOfPie> jsonList = new ArrayList<EchartsOfPie>();
		if (list != null && list.size() > 0) {
			String[] value = new String[list.size()];
			String[] name = new String[list.size()];
			EchartsOfPie jsonBar = new EchartsOfPie();
			try {
				BeanUtils.copyProperties(jsonBar, list.get(0));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int i = 0;
			for (EchartsOfPie bar : list) {
				value[i] = bar.getDataValue().toString();
				name[i] = bar.getDataName();
				i++;
			}
			jsonBar.setGroupName(name);
			jsonBar.setData(value);
			jsonList.add(jsonBar);

		}
		renderText(response, EchartsUtils.setPieToLeft(EchartsUtils.getPieNormal(jsonList)));
		return null;

	}

	public String exportExcel() throws Exception {

		return null;
	}

	private IQueryAttendanceInfoService getQueryAttendanceInfoService() {
		return (IQueryAttendanceInfoService) getService("queryAttendanceInfoService");
	}

	private IAttendanceExcepService getAttendanceExcepService() {
		return (IAttendanceExcepService) getService("attendanceExcepService");
	}

	private ClockRecords clockRecords;

	public ClockRecords getClockRecords() {
		return clockRecords;
	}

	public void setClockRecords(ClockRecords clockRecords) {
		this.clockRecords = clockRecords;
	}

}
