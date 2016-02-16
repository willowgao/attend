package com.wgsoft.performance.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.diary.model.EchartsOfBar;
import com.wgsoft.diary.model.EchartsOfPie;
import com.wgsoft.performance.idao.IQueryAttendanceInfoDao;
import com.wgsoft.user.model.UserInfo;

/**
 * 
 * @title： QueryAttendanceInfoDao.java
 * @desc：查询出勤情况
 * @author： Willowgao
 * @date： 2016-2-2 上午09:32:22
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryAttendanceInfoDao extends BaseDao implements IQueryAttendanceInfoDao {

	/**
	 * @see com.wgsoft.performance.idao.IQueryAttendanceInfoDao#queryAttend(Map)
	 */
	public List<ClockRecords> queryAttend(Map<String, Object> queryMap) {
		String clockDate = null;
		if (RunUtil.isEmpty(queryMap.get("clockdate"))) {
			clockDate = DateUtil.getNowDateByFormat(DateUtil.YMD);
		} else {
			clockDate = (String) queryMap.get("clockdate");
		}
		StringBuffer sql = new StringBuffer(" SELECT NVL(TO_DATE('").append(clockDate).append(
				"', 'yyyy-mm-dd'),TO_DATE(TO_CHAR(SYSDATE, 'yyyy-mm-dd'), 'yyyy-mm-dd')) CLOCKDATE,");
		sql
				.append(" A.USERDEPTID DEPT, A.USERID,B.AMSB,B.AMXB,B.PMSB,B.PMXB,C.AMSBTIME,C.AMXBTIME,C.PMSBTIME,C.PMXBTIME ");
		sql.append("  FROM USERINFO A, CLOCKREOCRDS B, CLOCK_SETTING C WHERE A.USERID = B.USERID(+) AND TO_DATE('")
				.append(clockDate).append("', 'yyyy-mm-dd') = B.CLOCKDATE(+)");
		sql.append("  AND TO_DATE('").append(clockDate).append("', 'yyyy-mm-dd') BETWEEN C.STARTTIME AND C.ENDTIME");

		if (RunUtil.isNotEmpty(queryMap.get("dept"))) {
			sql.append("  AND  A.USERDEPTID = '").append(queryMap.get("dept")).append("' ");
		} else {
			sql.append("  AND  A.USERDEPTID = '").append(((UserInfo) queryMap.get("user")).getUserdeptid())
					.append("' ");
		}
		sql.append(" AND C.ISENABLE = '0' ORDER BY A.USERDEPTID");
		return getSqlList_(sql.toString(), ClockRecords.class);
	}

	/**
	 * @see com.wgsoft.performance.idao.IQueryAttendanceInfoDao#queryOrgRanking(Map)
	 */
	public List<EchartsOfBar> queryOrgRanking(Map<String, Object> queryMap) {
		String clockDate = null;
		if (RunUtil.isEmpty(queryMap.get("clockdate"))) {
			clockDate = DateUtil.getNowDateByFormat(DateUtil.YMD);
		} else {
			clockDate = (String) queryMap.get("clockdate");
		}
		String descClockDate = DateUtil.string2Format(clockDate, DateUtil.YMD, DateUtil.YNYMDR);
		StringBuffer sql = new StringBuffer(" SELECT *  FROM ( SELECT  '").append(descClockDate).append(
				"'||' 部门出勤率' title, '组织内出勤排名' childtitle,C.DEPTNAME xcomments,COUNT(1) xdata, ");
		sql.append(" '异常数' dataname,'名' dataunit");
		sql.append(" FROM CLOCKDATE_SETTING A, USERINFO B, DEPTMENT C WHERE B.USERDEPTID = C.DEPTID");
		sql.append(" AND TO_DATE('").append(clockDate).append("', 'yyyy-mm-dd') = A.CLOCKDATE");
		sql.append(" AND ORGID =  '").append(((UserInfo) queryMap.get("user")).getUserorg()).append("' ");
		sql
				.append(" AND (NOT EXISTS (SELECT 1 FROM CLOCKREOCRDS C WHERE B.USERID = C.USERID  AND C.CLOCKDATE = A.CLOCKDATE) OR EXISTS ");
		sql
				.append(" (SELECT 1  FROM CLOCKEXCEPTION D WHERE B.USERID = D.USERID  AND D.ISENABLE = '0'  AND TO_CHAR(D.CLOCKDATE, 'yyyy-mm-dd') = ");
		sql.append("  TO_CHAR(A.CLOCKDATE, 'yyyy-mm-dd'))) GROUP BY C.DEPTNAME");
		sql.append(" UNION SELECT  '").append(descClockDate).append(
				"'||' 部门出勤率' title, '组织内出勤排名' childtitle,F.DEPTNAME xcomments,COUNT(1) xdata, ");
		sql.append(" '正常数' dataname,'名' dataunit");
		sql.append("  FROM USERINFO E, DEPTMENT F WHERE E.USERDEPTID = F.DEPTID ");
		sql.append(" AND E.USERORG =  '").append(((UserInfo) queryMap.get("user")).getUserorg()).append(
				"'  AND E.USERDEPTID IS NOT NULL AND NOT EXISTS");
		sql
				.append("  (SELECT 1 FROM CLOCKDATE_SETTING A, USERINFO B, DEPTMENT C WHERE B.USERDEPTID = C.DEPTID AND E.USERID = B.USERID");
		sql.append(" AND TO_DATE('").append(clockDate).append("', 'yyyy-mm-dd') = A.CLOCKDATE");
		sql.append(" AND ORGID =  '").append(((UserInfo) queryMap.get("user")).getUserorg()).append("' ");
		sql
				.append(" AND ( NOT EXISTS (SELECT 1 FROM CLOCKREOCRDS C WHERE B.USERID = C.USERID  AND C.CLOCKDATE = A.CLOCKDATE) OR  EXISTS ");
		sql
				.append(" (SELECT 1  FROM CLOCKEXCEPTION D WHERE B.USERID = D.USERID  AND D.ISENABLE = '0'  AND TO_CHAR(D.CLOCKDATE, 'yyyy-mm-dd') = ");
		sql
				.append("  TO_CHAR(A.CLOCKDATE, 'yyyy-mm-dd'))) GROUP BY C.DEPTNAME) GROUP BY F.DEPTNAME ) A  ORDER BY xcomments desc,dataname  ");
		return getSqlList_(sql.toString(), EchartsOfBar.class);
	}

	/**
	 * @see com.wgsoft.performance.idao.IQueryAttendanceInfoDao#queryDeptRanking(Map)
	 */
	public List<EchartsOfPie> queryDeptRanking(Map<String, Object> queryMap) {

		String clockDate = null;
		if (RunUtil.isEmpty(queryMap.get("clockdate"))) {
			clockDate = DateUtil.getNowDateByFormat(DateUtil.YMD);
		} else {
			clockDate = (String) queryMap.get("clockdate");
		}
		String descClockDate = DateUtil.string2Format(clockDate, DateUtil.YMD, DateUtil.YNYMDR);
		StringBuffer sql = new StringBuffer(" SELECT  '").append(descClockDate).append(
				"'||' 部门出勤率' title, '本部门出勤情况' CHILDTITLE, COUNT(1) dataValue,'未考勤' dataName,'名' DATAUNIT");
		sql.append(" FROM CLOCKDATE_SETTING A, USERINFO B WHERE  TO_DATE('").append(clockDate).append("', 'yyyy-mm-dd') = A.CLOCKDATE");
		sql.append(" AND B.Userdeptid =  '").append(((UserInfo) queryMap.get("user")).getUserdeptid()).append("' ");
		sql.append(" AND  NOT EXISTS (SELECT 1 FROM CLOCKREOCRDS C WHERE B.USERID = C.USERID  AND C.CLOCKDATE = A.CLOCKDATE)  ");
		
		
		sql.append(" UNION SELECT  '").append(descClockDate).append(
				"'||' 部门出勤率' title, '本部门出勤情况' CHILDTITLE, COUNT(1) VALUE,'考勤异常' NAME,'名' DATAUNIT");
		sql.append(" FROM CLOCKDATE_SETTING A, USERINFO B WHERE  TO_DATE('").append(clockDate).append("', 'yyyy-mm-dd') = A.CLOCKDATE");
		sql.append(" AND B.Userdeptid  =  '").append(((UserInfo) queryMap.get("user")).getUserdeptid()).append("' ");
		sql.append(" AND EXISTS (SELECT 1  FROM CLOCKEXCEPTION D  WHERE B.USERID = D.USERID  AND D.ISENABLE = '0'");
		sql.append(" AND TO_CHAR(D.CLOCKDATE, 'yyyy-mm-dd') =  TO_CHAR(A.CLOCKDATE, 'yyyy-mm-dd'))");
		 

		sql.append(" UNION SELECT  '").append(descClockDate).append(
				"'||' 部门出勤率' title, '本部门出勤情况' CHILDTITLE, COUNT(1) VALUE,'正常考勤' NAME,'名' DATAUNIT");
		sql.append(" FROM CLOCKDATE_SETTING A, USERINFO B WHERE  TO_DATE('").append(clockDate).append("', 'yyyy-mm-dd') = A.CLOCKDATE");
		sql.append(" AND B.Userdeptid =  '").append(((UserInfo) queryMap.get("user")).getUserdeptid()).append("' ");
		sql.append(" AND NOT EXISTS (SELECT 1  FROM CLOCKEXCEPTION D  WHERE B.USERID = D.USERID  AND D.ISENABLE = '0'");
		sql.append(" AND TO_CHAR(D.CLOCKDATE, 'yyyy-mm-dd') =  TO_CHAR(A.CLOCKDATE, 'yyyy-mm-dd'))");

		sql.append(" AND EXISTS (SELECT 1 FROM CLOCKREOCRDS C WHERE B.USERID = C.USERID  AND C.CLOCKDATE = A.CLOCKDATE)  ");
		
		return getSqlList_(sql.toString(), EchartsOfPie.class);
	}
}
