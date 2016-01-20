package com.wgsoft.system.idao;

import java.util.List;

import com.wgsoft.attendance.clock.model.LeaveAppSet;
import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.system.model.DataDictionary;

/**
 * @title： IDataDictionaryDao.java
 * @desc： 数据字典服务
 * @author： Willowgao
 * @date： 2015-10-27 下午01:23:08
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IDataDictionaryDao extends IBaseDao {

	/**
	 * @desc: 查询所有字典类型
	 * @return
	 * @return List<DataDictionary>
	 * @date： 2015-10-27 下午01:26:21
	 */
	List<DataDictionary> getAllDataDictionary();
	
	/**
	 * @desc: 查询所有审核与角色类型对应的设置
	 * @return 
	 * @return List<LeaveAppSet>
	 * @date： 2015-11-2 下午02:23:49
	 */
	List<LeaveAppSet> getAllLeaveAppSet();
}
