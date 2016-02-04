package com.wgsoft.performance.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.performance.iservice.IQueryJobUploadService;
import com.wgsoft.performance.model.JobAssignment;

/**
 * 
 * @title： QueryJobUploadAction.java
 * @desc： 工作完成情况查询
 * @author： Willowgao
 * @date： 2016-2-4 上午08:32:51
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryJobUploadAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8166401080743270249L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc:查询工作完成情况
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-2-4 上午08:33:44
	 */
	public String queryWorks() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("starttime", request.getParameter("starttime"));
		queryMap.put("endtime", request.getParameter("endtime"));
		queryMap.put("user", getUserInfo());
		List<JobAssignment> list = getQueryJobUploadService().queryWorks(queryMap);
		renderText(response, createTreeJson(list,new JobAssignment()));
		return null;
	}

	private JobAssignment job;

	private IQueryJobUploadService getQueryJobUploadService() {
		return (IQueryJobUploadService) getService("queryJobUploadService");
	}

	public JobAssignment getJob() {
		return job;
	}

	public void setJob(JobAssignment job) {
		this.job = job;
	}

}
