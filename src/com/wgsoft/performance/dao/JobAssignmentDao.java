package com.wgsoft.performance.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.performance.idao.IJobAssignmentDao;
import com.wgsoft.performance.model.JobApprove;
import com.wgsoft.performance.model.JobAssignment;
import com.wgsoft.user.model.UserInfo;

/**
 * 
 * @title： JobAssignmentDao.java
 * @desc：工作任务分派
 * @author： Willowgao
 * @date： 2015-11-23 下午02:15:21
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
@SuppressWarnings("unchecked")
public class JobAssignmentDao extends BaseDao implements IJobAssignmentDao {

	/**
	 * @see com.wgsoft.performance.idao.IJobAssignmentDao#getJobs(Map)
	 */
	public List<JobAssignment> getJobs(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer(" SELECT A.* FROM JOBASSIGNMENT A,USERINFO B,DEPTMENT C ");
		sql.append(" WHERE A.USERID = B.USERID AND USERDEPTID = C.DEPTID");

		if (RunUtil.isNotEmpty(queryMap.get("starttime"))) {
			sql.append(" AND TO_CHAR(A.FILLTIME,'YYYY-MM-DD') >='").append(((String[]) queryMap.get("starttime"))[0])
					.append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("endtime"))) {
			sql.append(" AND TO_CHAR(A.FILLTIME,'YYYY-MM-DD') <='").append(((String[]) queryMap.get("endtime"))[0])
					.append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("isapp"))) {
			if (RunUtil.isNotEmpty(queryMap.get("approveid"))) {
				sql.append(" AND  A.approveid ='").append(((String[]) queryMap.get("approveid"))[0]).append("'");
			}
		} else {
			sql.append(" and approveid is null");
		}
		return getSqlList_(sql.toString(), JobAssignment.class);
	}

	/**
	 * @see com.wgsoft.performance.idao.IJobAssignmentDao#updateJobs(String,
	 *      String)
	 */
	public int updateJobs(String appid, String ids, String status) {
		StringBuffer sql = new StringBuffer(" UPDATE JOBASSIGNMENT ");
		int rel = 0;
		if (RunUtil.isEmpty(status)) {
			sql.append(" SET APPROVEID ='");
			sql.append(appid).append("'");
			sql.append(" where jobid in (").append(ids).append(")");
			rel = getSqlUpdate(sql.toString());
		} else {
			int count = 0;
			sql.append(" SET status ='");
			sql.append(status).append("'");
			sql.append(" where APPROVEID ='").append(appid).append("'");
			count = getSqlUpdate(sql.toString());
			rel = rel + count;
			sql = new StringBuffer(" UPDATE JOBASSIGNMENT_APPROVE ");
			sql.append(" SET status ='");
			sql.append(status).append("'");
			sql.append(" where APPROVEID ='").append(appid).append("'");
			rel = getSqlUpdate(sql.toString());
			rel = rel + count;
		}
		return rel;
	}

	/**
	 * @see com.wgsoft.performance.idao.IJobAssignmentDao#getJobApps(Map)
	 */
	public List<JobAssignment> getJobApps(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer(
				" SELECT a.approveid,A.JOBNAME, A.STARTTIME, A.ENDTIME, B.STATUS, B.USERID, A.APPDATE FROM JOBASSIGNMENT_APPROVE A, JOBASSIGNMENT B ");
		sql.append(" WHERE A.APPROVEID = B.APPROVEID ");
		if (RunUtil.isNotEmpty(queryMap.get("userid"))) {
			sql.append(" AND a.approver ='").append(queryMap.get("userid")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("status"))) {
			sql.append(" AND a.status ='").append(queryMap.get("status")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("starttime"))) {
			sql.append(" AND TO_CHAR(A.APPDATE,'YYYY-MM-DD') >='").append(queryMap.get("starttime")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("endtime"))) {
			sql.append(" AND TO_CHAR(A.APPDATE,'YYYY-MM-DD') <='").append(queryMap.get("endtime")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("jobid"))) {
			sql.append(" AND b.jobid in (").append(queryMap.get("jobid")).append(")");
		}

		sql.append(" GROUP BY a.approveid,A.JOBNAME, A.STARTTIME, A.ENDTIME, B.STATUS, B.USERID, A.APPDATE");
		return getSqlList_(sql.toString(), JobAssignment.class);
	}

	/**
	 * @see com.wgsoft.performance.idao.IJobAssignmentDao#getJobUpload(Map)
	 */
	public List<JobAssignment> getJobUpload(Map<String, Object> queryMap) {
		UserInfo user = (UserInfo) queryMap.get("user");
		StringBuffer sql = new StringBuffer(" SELECT * FROM JOBASSIGNMENT a WHERE UPLOADTIME IS NULL ");
		sql.append(" AND STATUS = '").append(SysConstants.ApproverStatus.APPROVER_STATUS_PASS).append("' ");
		sql.append(" AND EXECUTIVER = '").append(user.getUserid()).append("' ");
		if (RunUtil.isNotEmpty(queryMap.get("starttime"))) {
			sql.append(" AND TO_CHAR(A.FILLTIME,'YYYY-MM-DD') >='").append(queryMap.get("starttime")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("endtime"))) {
			sql.append(" AND TO_CHAR(A.FILLTIME,'YYYY-MM-DD') <='").append(queryMap.get("starttime")).append("'");
		}

		return getSqlList_(sql.toString(), JobAssignment.class);
	}

	/**
	 * @see com.wgsoft.performance.idao.IJobAssignmentDao#getJobUploadApp(Map)
	 */
	public List<JobAssignment> getJobUploadApp(Map<String, Object> queryMap) {
		UserInfo user = (UserInfo) queryMap.get("user");
		StringBuffer sql = new StringBuffer(" SELECT * FROM JOBASSIGNMENT a WHERE  UPLOADTIME IS NOT  NULL AND CONFIRMTIME IS NULL");
		sql.append(" AND STATUS = '").append(SysConstants.ApproverStatus.APPROVER_STATUS_PASS).append("' ");
		sql.append(" AND USERID = '").append(user.getUserid()).append("' ");
		if (RunUtil.isNotEmpty(queryMap.get("starttime"))) {
			sql.append(" AND TO_CHAR(A.FILLTIME,'YYYY-MM-DD') >='").append(queryMap.get("starttime")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("endtime"))) {
			sql.append(" AND TO_CHAR(A.FILLTIME,'YYYY-MM-DD') <='").append(queryMap.get("starttime")).append("'");
		}
		return getSqlList_(sql.toString(), JobAssignment.class);
	}

	/**
	 * @see com.wgsoft.performance.idao.IJobAssignmentDao#confrim(JobAssignment,
	 *      String)
	 */
	public int confrim(String ids) {
		StringBuffer sql = new StringBuffer();
		sql.append("BEGIN  UPDATE JOBASSIGNMENT SET confirmtime = sysdate").append(" WHERE JOBID IN (").append(ids).append("); COMMIT; END ; ");
		return getSqlUpdate(sql.toString());
	}

	/**
	 * @see com.wgsoft.performance.idao.IJobAssignmentDao#rollback(String)
	 */
	public int rollback(String ids) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE JOBASSIGNMENT  SET UPLOADTIME = NULL").append(" WHERE JOBID IN (").append(
				RunUtil.transObjAsSqlInStr(ids.split("'"))).append(") ");
		return getSqlUpdate(sql.toString());
	}

	/**
	 * @see com.wgsoft.performance.idao.IJobAssignmentDao#getJobHasNotDone(String)
	 */
	public List<JobApprove> getJobHasNotDone(String ids) {
		StringBuffer sql = new StringBuffer(" SELECT * FROM JOBASSIGNMENT_APPROVE a WHERE 1=1");
		sql.append(" AND APPROVEID IN (").append(ids).append(") AND  EXISTS ");
		sql.append(" (SELECT 1 FROM JOBASSIGNMENT B WHERE A.APPROVEID = B.APPROVEID AND B.CONFIRMTIME IS  NULL)");
		return getSqlList_(sql.toString(), JobApprove.class);
	}

	/**
	 * @see com.wgsoft.performance.idao.IJobAssignmentDao#confrimApp(String)
	 */
	public int confrimApp(String ids) {
		int rel = 0;
		int count = 0;
		StringBuffer sql = new StringBuffer("  UPDATE JOBASSIGNMENT_APPROVE ");
		// 更新审核表
		sql.append(" SET CONFIRMTIME = SYSDATE , STATUS = '").append(
				SysConstants.ApproverStatus.APPROVER_STATUS_FIRSTPASS).append("' WHERE APPROVEID IN (").append(
				ids).append(") ");
		count = getSqlUpdate(sql.toString());
		rel = rel + count;
		sql = new StringBuffer("  UPDATE JOBASSIGNMENT ");
		// 更新填报表
		sql.append(" SET STATUS = '").append(
				SysConstants.ApproverStatus.APPROVER_STATUS_FIRSTPASS).append("' WHERE APPROVEID IN (").append(
						ids).append(") ");
		count = getSqlUpdate(sql.toString());
		rel = rel + count;
		return rel;
	}
}
