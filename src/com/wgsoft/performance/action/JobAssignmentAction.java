package com.wgsoft.performance.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.performance.iservice.IJobAssignmentService;
import com.wgsoft.performance.model.JobAssignment;

/**
 * @title： JobAssignmentAction.java
 * @desc：工作任务分派
 * @author： Willowgao
 * @date： 2015-11-23 下午01:14:15
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class JobAssignmentAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2256715600512102505L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 工作审核页面初始化
	 */
	public String approve() throws Exception {
		return "approve";
	}

	/**
	 * @desc:查询工作任务分派记录
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-23 下午02:22:44
	 */
	@SuppressWarnings("unchecked")
	public String queryJobs() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap = (Map<String, Object>) request.getParameterMap();
		List<JobAssignment> list = getJobAssignmentService().getJobs(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:查询工单申报信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-26 上午11:53:32
	 */
	public String queryApproves() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("starttime", request.getParameter("starttime"));
		queryMap.put("endtime", request.getParameter("endtime"));
		queryMap.put("userid", getUserInfo().getUserid());
		List<JobAssignment> list = getJobAssignmentService().getJobApps(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:工作任务审核
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-26 上午11:54:05
	 */
	@SuppressWarnings("unchecked")
	public String saveAppJobs() throws Exception {
		if (jobAssignment == null) {
			jobAssignment = new JobAssignment();
		}
		Map<String, Object> saveMap = request.getParameterMap();
		jobAssignment.setAppcomments(((String[]) saveMap.get("comments"))[0]);
		jobAssignment.setStatus(((String[]) saveMap.get("status"))[0]);
		jobAssignment.setApproveid(((String[]) saveMap.get("approveid"))[0]);
		jobAssignment.setStarttime(DateUtil.string2Date(((String[]) saveMap.get("starttime"))[0], DateUtil.YMD));
		jobAssignment.setEndtime(DateUtil.string2Date(((String[]) saveMap.get("endtime"))[0], DateUtil.YMD));
		jobAssignment.setJobname(((String[]) saveMap.get("jobname"))[0]);
		jobAssignment.setApprover(getUserInfo().getUserid());
		int rel = getJobAssignmentService().saveAppJobs(Arrays.asList(jobAssignment));
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	/**
	 * @desc:保存工作任务分派信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-23 下午05:05:30
	 */
	public String saveJobs() throws Exception {
		String jsonStr = ((String[]) request.getParameterMap().get("jobAssignment.datagrid"))[0];
		Map<String, List<JobAssignment>> jobs = getListFromMap(jsonStr, new JobAssignment());
		int rel = getJobAssignmentService().saveJobs(jobs, getUserInfo());
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	/**
	 * @desc:传递审核单据
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-26 上午10:29:08
	 */
	public String transfer() throws Exception {
		String ids = request.getParameter("ids");
		int rel = getJobAssignmentService().transfer(jobAssignment, ids);
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	public IJobAssignmentService getJobAssignmentService() {
		return (IJobAssignmentService) getService("jobAssignmentService");
	}

	private JobAssignment jobAssignment;

	public JobAssignment getJobAssignment() {
		return jobAssignment;
	}

	public void setJobAssignment(JobAssignment jobAssignment) {
		this.jobAssignment = jobAssignment;
	}

}
