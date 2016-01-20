package com.wgsoft.performance.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.performance.idao.IJobAssignmentDao;
import com.wgsoft.performance.iservice.IJobAssignmentService;
import com.wgsoft.performance.model.JobApprove;
import com.wgsoft.performance.model.JobAssignment;
import com.wgsoft.user.model.UserInfo;

/**
 * @title： JobAssignmentService.java
 * @desc： 工作任务分派
 * @author： Willowgao
 * @date： 2015-11-23 下午02:13:04
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class JobAssignmentService implements IJobAssignmentService {

	private IJobAssignmentDao jobAssignmentDao;

	/**
	 * @see com.wgsoft.performance.iservice.IJobAssignmentService#getJobs(Map)
	 */
	public List<JobAssignment> getJobs(Map<String, Object> queryMap) {
		return jobAssignmentDao.getJobs(queryMap);
	}

	/**
	 * @see com.wgsoft.performance.iservice.IJobAssignmentService#getAppJobs(Map)
	 */
	public List<JobAssignment> getJobApps(Map<String, Object> queryMap) {
		return jobAssignmentDao.getJobApps(queryMap);
	}

	/**
	 * @see com.wgsoft.performance.iservice.IJobAssignmentService#saveJobs(Map)
	 */
	public int saveJobs(Map<String, List<JobAssignment>> map, UserInfo user) {
		// 新增list
		List<JobAssignment> insertList = map.get(SysConstants.DataGridData.DATAGRID_LIST_INSERTLIST);
		// 删除list
		List<JobAssignment> deleteList = map.get(SysConstants.DataGridData.DATAGRID_LIST_DELETELIST);
		// 更新list
		List<JobAssignment> updataList = map.get(SysConstants.DataGridData.DATAGRID_LIST_UPDATELIST);
		int i = 0;
		if (updataList != null && updataList.size() > 0) {
			try {
				jobAssignmentDao.updateBatch(updataList);
			} catch (Exception e) {
				i++;
			}
		}

		if (deleteList != null && deleteList.size() > 0) {
			try {
				jobAssignmentDao.deleteBatch(deleteList);
			} catch (Exception e) {
				i++;
			}
		}

		if (insertList != null && insertList.size() > 0) {
			try {
				//
				Date date = new Date();
				for (JobAssignment job : insertList) {
					job.setStatus(SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE);
					job.setUserid(user.getUserid());
					job.setFilltime(date);
				}
				jobAssignmentDao.insertBatch(insertList);
			} catch (Exception e) {
				i++;
			}
		}
		return i;
	}

	/**
	 * @see com.wgsoft.performance.iservice.IJobAssignmentService#saveAppJobs(List)
	 */
	public int saveAppJobs(List<JobAssignment> jobs) {
		int rel = 0;
		if (jobs != null) {
			JobAssignment job = jobs.get(0);
			JobApprove jobApp = new JobApprove();
			try {
				BeanUtils.copyProperties(jobApp, job);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jobApp.setAppdate(new Date());
			jobAssignmentDao.update(jobApp);

			try {
				rel = jobAssignmentDao.updateJobs(jobApp.getApproveid(), job.getApproveid(), jobApp.getStatus());
			} catch (Exception e) {
				return SysConstants.ERROR;
			}
		} else {
			return SysConstants.ERROR;
		}
		if (rel >= 0) {
			return SysConstants.SUCCESS;
		} else {
			return SysConstants.ERROR;
		}
	}

	private static final String TB = "填报";

	/**
	 * @see com.wgsoft.performance.iservice.IJobAssignmentService#transfer(JobAssignment)
	 */
	public int transfer(JobAssignment job, String ids) {

		JobApprove jobApp = new JobApprove();
		try {
			BeanUtils.copyProperties(jobApp, job);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jobApp.setAppdate(new Date());
		jobApp.setStatus(SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE);
		jobApp.setAppcomments(TB);
		try {
			jobAssignmentDao.insert(jobApp);
		} catch (Exception e) {
			e.printStackTrace();
			return SysConstants.ERROR;
		}
		String[] idArr = ids.split(",");
		int rel = 0;
		try {
			rel = jobAssignmentDao.updateJobs(jobApp.getApproveid(), RunUtil.transObjAsSqlInStr(idArr), null);
		} catch (Exception e) {
			return SysConstants.ERROR;
		}
		if (rel >= 0) {
			return SysConstants.SUCCESS;
		} else {
			return SysConstants.ERROR;
		}
	}

	public IJobAssignmentDao getJobAssignmentDao() {
		return jobAssignmentDao;
	}

	public void setJobAssignmentDao(IJobAssignmentDao jobAssignmentDao) {
		this.jobAssignmentDao = jobAssignmentDao;
	}

}