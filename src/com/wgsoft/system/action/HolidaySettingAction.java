package com.wgsoft.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.system.iservice.IHolidaySettingService;
import com.wgsoft.system.model.ClockDateSetting;

/**
 * @title： HolidaySettingAction.java
 * @desc： 节假日时间设置
 * @author： Willowgao
 * @date： 2015-11-20 下午01:07:07
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class HolidaySettingAction extends BaseAction {

	private String clockdate;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1346784881252575486L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc: 查询日期
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-20 下午01:08:17
	 */
	public String query() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (RunUtil.isEmpty(clockdate)) {
			clockdate = request.getParameter("clockDate");
		}
		queryMap.put("clockdate", clockdate);
		List<ClockDateSetting> list = getHolidaySettingSerivce().getDates(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:保存修改信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-20 下午02:30:42
	 */
	public String save() throws Exception {
		String jsonStr = request.getParameter(DATAGRID_JSON_DATA);
		Map<String, List<ClockDateSetting>> map = getListFromMap(jsonStr, new ClockDateSetting());
		int rel = getHolidaySettingSerivce().saveChange(map);
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	public String getClockdate() {
		return clockdate;
	}

	public IHolidaySettingService getHolidaySettingSerivce() {
		return (IHolidaySettingService) getService("holidaySettingService");
	}

	public void setClockdate(String clockdate) {
		this.clockdate = clockdate;
	}

}
