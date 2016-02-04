package com.wgsoft.performance.service;

import java.util.List;
import java.util.Map;

import com.wgsoft.performance.idao.IQueryJobUploadDao;
import com.wgsoft.performance.iservice.IQueryJobUploadService;
import com.wgsoft.performance.model.JobAssignment;

/**
 * @title： QueryJobUploadService.java
 * @desc： 工作完成情况查询
 * @author： Willowgao
 * @date： 2016-2-4 上午08:35:37
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryJobUploadService implements IQueryJobUploadService {

	private IQueryJobUploadDao queryJobUploadDao;

	/**
	 * @see com.wgsoft.performance.iservice.IQueryJobUploadService#
	 */
	public List<JobAssignment> queryWorks(Map<String, Object> queryMap) {
		List<JobAssignment> list = queryJobUploadDao.queryWorks(queryMap);
		 
		return list;
	}

	public IQueryJobUploadDao getQueryJobUploadDao() {
		return queryJobUploadDao;
	}

	public void setQueryJobUploadDao(IQueryJobUploadDao queryJobUploadDao) {
		this.queryJobUploadDao = queryJobUploadDao;
	}

}
