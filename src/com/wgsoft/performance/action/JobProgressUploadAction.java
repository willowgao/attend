package com.wgsoft.performance.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.performance.iservice.IJobAssignmentService;
import com.wgsoft.performance.model.JobAssignment;

/**
 * @title： JobProgressUploadAction.java
 * @desc：工作任务申报
 * @author： Willowgao
 * @date： 2016-1-28 上午10:57:52
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class JobProgressUploadAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 
	 * @desc: 页面初始化
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-1-28 上午11:48:38
	 */
	public String uploadApp() throws Exception {
		return "uploadApp";
	}

	/**
	 * @desc: 查询本人已经被审核的工作任务
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-1-28 上午11:24:44
	 */
	public String queryJobs() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("starttime", request.getParameter("starttime"));
		queryMap.put("endtime", request.getParameter("endtime"));
		queryMap.put("user", getUserInfo());
		List<JobAssignment> list = getJobAssignmentService().getJobUpload(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc: 工作任务上报
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-1-28 上午11:24:44
	 */
	public String saveJobs() throws Exception {
		String jsonStr = ((String[]) request.getParameterMap().get("jobAssignment.datagrid"))[0];
		Map<String, List<JobAssignment>> jobs = getListFromMap(jsonStr, new JobAssignment());
		List<JobAssignment> updataList = jobs.get(SysConstants.DataGridData.DATAGRID_LIST_UPDATELIST);
		Date uploadDate = new Date();
		for (JobAssignment job : updataList) {
			job.setUploadtime(uploadDate);
		}
		int rel = getJobAssignmentService().saveJobs(jobs, getUserInfo());
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	/**
	 * @desc: 查询本人已经被审核且已上报的工作任务
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-1-28 上午11:24:44
	 */
	public String getJobUploadApp() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("starttime", request.getParameter("starttime"));
		queryMap.put("endtime", request.getParameter("endtime"));
		queryMap.put("user", getUserInfo());
		List<JobAssignment> list = getJobAssignmentService().getJobUploadApp(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:完成情况确认
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-1-28 上午11:48:50
	 */
	public String uploadJobApp() throws Exception {
		String ids = request.getParameter("ids");
		Map<String, Object> saveMap = new HashMap<String, Object>();
		saveMap.put("ids", RunUtil.transObjAsSqlInStr(ids.split(",")));
		int rel = getJobAssignmentService().confrim(saveMap);
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	/**
	 * @desc: 回退已经填报上报的数据
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-1-28 上午11:48:50
	 */
	public String rollback() throws Exception {
		String ids = request.getParameter("ids");
		int rel = getJobAssignmentService().rollback(ids);
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	private JobAssignment jobAssignment;

	public JobAssignment getJobAssignment() {
		return jobAssignment;
	}

	public void setJobAssignment(JobAssignment jobAssignment) {
		this.jobAssignment = jobAssignment;
	}

	private IJobAssignmentService getJobAssignmentService() {
		return (IJobAssignmentService) getService("jobAssignmentService");
	}
}
