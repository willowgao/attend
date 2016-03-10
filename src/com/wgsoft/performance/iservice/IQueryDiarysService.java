package com.wgsoft.performance.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.diary.model.DiaryDaily;

/**
 * @title： IQueryDiarysService.java
 * @desc： 日志信息查询
 * @author： Willowgao
 * @date： 2016-3-9 下午04:13:01
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IQueryDiarysService {

	/**
	 * @desc:日志信息查询
	 * @param queryMap
	 * @return
	 * @return List<DiaryDaily>
	 * @date： 2016-3-9 下午04:14:23
	 */
	List<DiaryDaily> queryDiarys(Map<String, Object> queryMap);
}
