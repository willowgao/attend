package com.wgsoft.system.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.system.model.ClockDateSetting;

/**
 * @title： IHolidaySettingSerivce.java
 * @desc： 节假日时间设置
 * @author： Willowgao
 * @date： 2015-11-20 下午01:21:15
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IHolidaySettingService {

	/**
	 * @desc: 查询考勤时间
	 * @param queryMap
	 * @return
	 * @return List<ClockDateSetting>
	 * @date： 2015-11-20 下午01:16:45
	 */
	List<ClockDateSetting> getDates(Map<String, Object> queryMap);

	/**
	 * @desc: 保存修改信息
	 * @param map Map<String, List<ClockDateSetting>> 
	 * @return
	 * @return int > 0 失败
	 * @date： 2015-11-20 下午02:27:50
	 */
	int saveChange(Map<String, List<ClockDateSetting>> map);
}
