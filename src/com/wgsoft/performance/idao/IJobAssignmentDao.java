package com.wgsoft.performance.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.performance.model.JobApprove;
import com.wgsoft.performance.model.JobAssignment;

/**
 * @title： IJobAssignmentDao.java
 * @desc：工作任务分派
 * @author： Willowgao
 * @date： 2015-11-23 下午02:14:30
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IJobAssignmentDao extends IBaseDao {

	/**
	 * @desc:查询工作任务分派记录
	 * @param queryMap
	 * @return
	 * @return List<JobAssignment>
	 * @date： 2015-11-23 下午02:14:54
	 */
	List<JobAssignment> getJobs(Map<String, Object> queryMap);

	/**
	 * @desc:查询工已审核未上报信息
	 * @param queryMap
	 * @return
	 * @return List<JobAssignment>
	 * @date： 2016-1-28 上午11:16:42
	 */
	List<JobAssignment> getJobUpload(Map<String, Object> queryMap);

	/**
	 * @desc:查询工已审核已上报信息
	 * @param queryMap
	 * @return
	 * @return List<JobAssignment>
	 * @date： 2016-1-28 上午11:16:42
	 */
	List<JobAssignment> getJobUploadApp(Map<String, Object> queryMap);

	/**
	 * @desc:更新填报信息
	 * @param appid
	 *            审核ID
	 * @param ids
	 *            填报id
	 * @param status
	 *            String 审核状态
	 * @return
	 * @return int
	 * @date： 2015-11-26 上午10:52:53
	 */
	int updateJobs(String appid, String ids, String status);

	/**
	 * @desc:查询工单申报信息
	 * @param queryMap
	 * @return
	 * @return List<JobAssignment>
	 * @date： 2015-11-23 下午02:14:54
	 */
	List<JobAssignment> getJobApps(Map<String, Object> queryMap);

	/**
	 * @desc:工作单据确认 只要工作单据下面有一个工作项，没有完成，这一个工单，将不会被填上确认时间，直到最后一个工作项完成之后，再填写上确认时间
	 * @param job
	 *            JobAssignment
	 * @param ids
	 *            String 选择需要传递的id
	 * @return
	 * @return int
	 * @date： 2015-11-26 上午10:44:44
	 */
	int confrim(String ids);

	/**
	 * @desc:回退已经上报的工作单
	 * @param ids
	 *            String 选择需要传递的id
	 * @return
	 * @return int
	 * @date： 2015-11-26 上午10:44:44
	 */
	int rollback(String ids);

	/**
	 * @desc: 未结束的工作单据
	 * @param ids
	 * @return
	 * @return List<JobApprove>
	 * @date： 2016-1-28 下午04:13:55
	 */
	List<JobApprove> getJobHasNotDone(String ids);
	

	/**
	 * @desc:工作单据确认 只要工作单据下面有一个工作项，没有完成，这一个工单，将不会被填上确认时间，直到最后一个工作项完成之后，再填写上确认时间
	 * @param job
	 *            JobAssignment
	 * @param ids
	 *            String 选择需要传递的id
	 * @return
	 * @return int
	 * @date： 2015-11-26 上午10:44:44
	 */
	int confrimApp(String ids);
	
	
}
