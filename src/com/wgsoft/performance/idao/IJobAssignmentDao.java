package com.wgsoft.performance.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.idao.IBaseDao;
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
}
