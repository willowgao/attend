package com.wgsoft.performance.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.performance.model.JobAssignment;

/**
 * @title： IQueryJobUploadDao.java
 * @desc：工作完成情况查询
 * @author： Willowgao
 * @date： 2016-2-4 上午08:37:33
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IQueryJobUploadDao extends IBaseDao {
	/**
	 * @desc:查询工作完成情况
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-2-4 上午08:33:44
	 */
	public List<JobAssignment> queryWorks(Map<String, Object> queryMap);
	 

}
