package com.wgsoft.performance.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.performance.idao.IJobAssignmentDao;
import com.wgsoft.performance.model.JobAssignment;

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
		}else{
			int count = 0;
			sql.append(" SET status ='");
			sql.append(status).append("'");
			sql.append(" where APPROVEID ='").append(appid).append("'");
			count = getSqlUpdate(sql.toString());
			rel = rel+count;
			sql = new StringBuffer(" UPDATE JOBASSIGNMENT_APPROVE ");
			sql.append(" SET status ='");
			sql.append(status).append("'");
			sql.append(" where APPROVEID ='").append(appid).append("'");
			rel = getSqlUpdate(sql.toString());
			rel = rel+count;
		}
		return rel;
	}

	public List<JobAssignment> getJobApps(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer(
				" SELECT a.approveid,A.JOBNAME, A.STARTTIME, A.ENDTIME, B.STATUS, B.USERID, A.APPDATE FROM JOBASSIGNMENT_APPROVE A, JOBASSIGNMENT B ");
		sql.append(" WHERE A.APPROVEID = B.APPROVEID  AND B.STATUS = '1'");
		if (RunUtil.isNotEmpty(queryMap.get("userid"))) {
			sql.append(" AND a.approver ='").append(queryMap.get("userid")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("starttime"))) {
			sql.append(" AND TO_CHAR(A.APPDATE,'YYYY-MM-DD') >='").append(queryMap.get("starttime")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("endtime"))) {
			sql.append(" AND TO_CHAR(A.APPDATE,'YYYY-MM-DD') <='").append(queryMap.get("endtime")).append("'");
		}

		sql.append(" GROUP BY a.approveid,A.JOBNAME, A.STARTTIME, A.ENDTIME, B.STATUS, B.USERID, A.APPDATE");
		return getSqlList_(sql.toString(), JobAssignment.class);
	}
}
