package com.wgsoft.system.service;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.system.idao.IHolidaySettingDao;
import com.wgsoft.system.iservice.IHolidaySettingService;
import com.wgsoft.system.model.ClockDateSetting;

/**
 * @title： HolidaySettingSerivce.java
 * @desc： 节假日时间设置
 * @author： Willowgao
 * @date： 2015-11-20 下午01:24:09
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class HolidaySettingService implements IHolidaySettingService {

	private IHolidaySettingDao holidaySettingDao;

	/**
	 * @see com.wgsoft.system.iservice.IHolidaySettingService#saveChange(Map)
	 */
	public int saveChange(Map<String, List<ClockDateSetting>> map) {
		// 更新list
		List<ClockDateSetting> updataList = map.get(SysConstants.DataGridData.DATAGRID_LIST_UPDATELIST);
		int i = 0;
		if (updataList != null && updataList.size() > 0) {
			try {
				holidaySettingDao.updateBatch(updataList);
			} catch (Exception e) {
				i++;
			}
		}
		return i;
	}

	/**
	 * @see com.wgsoft.system.iservice.IHolidaySettingService#getDates(Map)
	 */
	public List<ClockDateSetting> getDates(Map<String, Object> queryMap) {
		return holidaySettingDao.getDates(queryMap);
	}

	public IHolidaySettingDao getHolidaySettingDao() {
		return holidaySettingDao;
	}

	public void setHolidaySettingDao(IHolidaySettingDao holidaySettingDao) {
		this.holidaySettingDao = holidaySettingDao;
	}

}
