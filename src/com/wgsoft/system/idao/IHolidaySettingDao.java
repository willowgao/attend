package com.wgsoft.system.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.system.model.ClockDateSetting;

/**
 * @title： IHolidaySettingDao.java
 * @desc： 节假日时间设置
 * @author： Willowgao
 * @date： 2015-11-20 下午01:15:20
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IHolidaySettingDao extends IBaseDao {

	/**
	 * @desc: 查询考勤时间
	 * @param queryMap
	 * @return
	 * @return List<ClockDateSetting>
	 * @date： 2015-11-20 下午01:16:45
	 */
	List<ClockDateSetting> getDates(Map<String, Object> queryMap);
}
