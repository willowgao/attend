package com.wgsoft.performance.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.performance.model.JobAssignment;
import com.wgsoft.user.model.UserInfo;

/**
 * @title： IJobAssignmentService.java
 * @desc： 工作任务分派
 * @author： Willowgao
 * @date： 2015-11-23 下午01:20:20
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IJobAssignmentService {

	/**
	 * @desc:查询工作任务分派记录
	 * @param queryMap
	 *            Map<String, Object> queryMap
	 * @return
	 * @return List<JobAssignment>
	 * @date： 2015-11-23 下午02:05:07
	 */
	List<JobAssignment> getJobs(Map<String, Object> queryMap);

	/**
	 * @desc:保存工作任务(包括删除、更新、新增)
	 * @param map
	 *            Map<String, List<JobAssignment>>
	 * @param user
	 *            UserInfo
	 * @return
	 * @return int
	 * @date： 2015-11-24 上午10:47:34
	 */
	int saveJobs(Map<String, List<JobAssignment>> map, UserInfo user);

	/**
	 * @desc:查询工单申报信息
	 * @param queryMap
	 * @return
	 * @return List<JobAssignment>
	 * @date： 2015-11-26 上午11:47:38
	 */
	List<JobAssignment> getJobApps(Map<String, Object> queryMap);

	/**
	 * @desc: 工作任务审核
	 * @param jobs
	 *            List<JobAssignment> jobs
	 * @return
	 * @return int
	 * @date： 2015-11-23 下午02:10:04
	 */
	int saveAppJobs(List<JobAssignment> jobs);

	/**
	 * @desc:工作单据传递
	 * @param job
	 *            JobAssignment
	 * @param ids
	 *            String 选择需要传递的id
	 * @return
	 * @return int
	 * @date： 2015-11-26 上午10:44:44
	 */
	int transfer(JobAssignment job, String ids);

}
