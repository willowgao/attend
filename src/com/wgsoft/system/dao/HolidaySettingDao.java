package com.wgsoft.system.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.system.idao.IHolidaySettingDao;
import com.wgsoft.system.model.ClockDateSetting;

/**
 * @title： HolidaySettingDao.java
 * @desc： 节假日时间设置
 * @author： Willowgao
 * @date： 2015-11-20 下午01:17:38
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
@SuppressWarnings("unchecked")
public class HolidaySettingDao extends BaseDao implements IHolidaySettingDao {

	/**
	 *@see com.wgsoft.system.idao.IHolidaySettingDao#getDates(Map)
	 */
	public List<ClockDateSetting> getDates(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT * FROM clockdate_setting WHERE 1=1");
		if (RunUtil.isNotEmpty(queryMap.get("clockdate"))) {
			sql.append(" AND to_char(CLOCKDATE,'yyyy-mm')='").append(queryMap.get("clockdate")).append("'");
			sql.append(" and clockyear =SUBSTR('").append(queryMap.get("clockdate")).append("',0,4)");
		}else{
			sql.append(" AND to_char(CLOCKDATE,'yyyy-mm') = TO_CHAR(SYSDATE,'YYYY')||'-01'");
		}
		sql.append("  order by CLOCKDATE");
		return getSqlList_(sql.toString(), ClockDateSetting.class);
	}

}
