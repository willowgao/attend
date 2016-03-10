package com.wgsoft.performance.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.diary.model.DiaryDaily;

/**
 * @title： IQueryDiarysDao.java
 * @desc：
 * @author： Willowgao
 * @date： 2016-3-9 下午04:15:50
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IQueryDiarysDao extends IBaseDao {
	/**
	 * @desc:日志信息查询
	 * @param queryMap
	 * @return
	 * @return List<DiaryDaily>
	 * @date： 2016-3-9 下午04:14:23
	 */
	List<DiaryDaily> queryDiarys(Map<String, Object> queryMap);
}
