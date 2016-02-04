package com.wgsoft.performance.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.performance.idao.IQueryJobUploadDao;
import com.wgsoft.performance.model.JobAssignment;
import com.wgsoft.user.model.UserInfo;

/**
 * @title： QueryJobUploadDao.java
 * @desc： 工作完成情况查询
 * @author： Willowgao
 * @date： 2016-2-4 上午08:38:00
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryJobUploadDao extends BaseDao implements IQueryJobUploadDao {

	/**
	 * @see com.wgsoft.performance.idao.IQueryJobUploadDao#queryWorks(Map)
	 */
	public List<JobAssignment> queryWorks(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM (SELECT b.jobid id,A.JOBNAME,A.APPROVEID  pid,B.JOBTYPE,B.EXECUTIVER,B.COMMENTS,B.UPLOADTIME,B.FILLTIME,");
		// 工作任务明细
		sql.append(" B.STARTTIME,B.ENDTIME, b.worktime,b.plantime,b.progress, b.userid, a.approver,b.status,");
		sql.append("b.confirmtime FROM JOBASSIGNMENT_APPROVE A, JOBASSIGNMENT B");
		sql.append(" WHERE A.APPROVEID = B.APPROVEID and b.userid in (select userid from userinfo where ");
		sql.append("userorg IN (SELECT orgid FROM wg_organization START WITH orgid = '");
		sql.append(((UserInfo) queryMap.get("user")).getUserorg()).append("' CONNECT BY PRIOR orgid = parentid))   ");
		
		if(RunUtil.isNotEmpty(queryMap.get("starttime"))){
			sql.append(" AND A.STARTTIME <= TO_DATE('").append(queryMap.get("starttime")).append("','YYYY-MM-DD')");
		}
		if(RunUtil.isNotEmpty(queryMap.get("starttime"))){
			sql.append(" AND  A.ENDTIME >= TO_DATE('").append(queryMap.get("endtime")).append("','YYYY-MM-DD')");
		}

		// 审核记录
		sql.append(" UNION SELECT A.APPROVEID ID,A.JOBNAME, NULL PID, NULL, NULL, NULL, NULL, NULL, A.STARTTIME,");
		sql.append(" A.ENDTIME,NULL,NULL,NULL,NULL,a.approver,a.status,a.confirmtime FROM JOBASSIGNMENT_APPROVE A WHERE 1=1");
		
		if(RunUtil.isNotEmpty(queryMap.get("starttime"))){
			sql.append(" AND A.STARTTIME <= TO_DATE('").append(queryMap.get("starttime")).append("','YYYY-MM-DD')");
		}
		if(RunUtil.isNotEmpty(queryMap.get("starttime"))){
			sql.append(" AND  A.ENDTIME >= TO_DATE('").append(queryMap.get("endtime")).append("','YYYY-MM-DD')");
		}
		
		sql.append(" )WHERE APPROVER in (select userid from userinfo where userorg IN (SELECT orgid FROM");
		sql.append(" wg_organization START WITH orgid = '");
		sql.append(((UserInfo) queryMap.get("user")).getUserorg()).append("'  CONNECT BY PRIOR orgid = parentid)");
	
		
		sql.append(" ) START WITH pid IS NULL CONNECT BY PRIOR id = pid");
		return getSqlList_(sql.toString(), JobAssignment.class);
	}
}
