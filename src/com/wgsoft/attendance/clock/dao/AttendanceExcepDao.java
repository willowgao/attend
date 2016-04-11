package com.wgsoft.attendance.clock.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.clock.idao.IAttendanceExcepDao;
import com.wgsoft.attendance.clock.model.ClockExcepApprove;
import com.wgsoft.attendance.clock.model.ClockRecords;
import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;

/**
 * @title： AttendanceExcepDao.java
 * @desc： 考勤异常处理 【未打卡、年假、事假、出差等】
 * @author： Willowgao
 * @date： 2015-10-28 下午01:11:05
 * @version： V1.0<br>
 * @versioninfo：慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
@SuppressWarnings("unchecked")
public class AttendanceExcepDao extends BaseDao implements IAttendanceExcepDao {

	/**
	 * @see com.wgsoft.attendance.clock.idao.IAttendanceExcepDao#getExcepClockRecords()
	 */
	public List<ClockRecords> getExcepClockRecords(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer("SELECT F.CLOCKDATE,NVL(F.USERID,'" + queryMap.get("userId")
				+ "') USERID,AMSB,AMXB,PMSB,PMXB,ISNEED,G.AMSBTIME,G.AMXBTIME,G.PMSBTIME,G.PMXBTIME FROM ");
		sql
				.append(" (SELECT USERID, CLOCKDATE,MIN(ISNEED) ISNEED, MAX(AMSB) AMSB,MAX(AMXB) AMXB,MAX(PMSB) PMSB,MAX(PMXB) PMXB");
		sql.append("  FROM (SELECT '9999' ISNEED,USERID,CLOCKDATE,AMSB,AMXB,PMSB,PMXB FROM CLOCKREOCRDS B ");
		sql.append("  WHERE USERID ='" + queryMap.get("userId") + "'  UNION SELECT ISNEED,'" + queryMap.get("userId")
				+ "' userid,");
		sql
				.append(" CLOCKDATE CLOCKDATE, NULL,NULL,NULL,NULL FROM CLOCKDATE_SETTING A) GROUP BY USERID, CLOCKDATE) F,CLOCK_SETTING G ");
		sql
				.append(" WHERE F.CLOCKDATE BETWEEN G.STARTTIME AND G.ENDTIME AND G.ISENABLE = '0' ");
		
		if(RunUtil.isNotEmpty(queryMap.get("isExcep"))){
			sql
					.append("  AND ((F.AMSB IS NULL OR F.AMXB IS NULL OR F.PMSB IS NULL OR   F.PMXB IS NULL) OR  (EXISTS  (SELECT 1");
			sql
					.append("   FROM CLOCKEXCEPTION H  WHERE F.CLOCKDATE = TO_DATE(TO_CHAR(H.CLOCKDATE, 'yyyy-mm-dd'), 'yyyy-mm-dd')");
			sql.append("  AND H.USERID = F.USERID))) ");
		}

		if (RunUtil.isNotEmpty(queryMap.get("clockdate"))) {
			sql.append(" AND  F.clockdate =to_date('").append(
					DateUtil.date2String((Date) queryMap.get("clockdate"), DateUtil.YMD)).append("','yyyy-mm-dd')");
		}
		
		if(RunUtil.isEmpty(queryMap.get("startTime"))&&RunUtil.isEmpty(queryMap.get("endTime"))){
			sql.append(" AND F.CLOCKDATE BETWEEN (SYSDATE - 15) AND (SYSDATE + 15) ");
		}

		if (RunUtil.isNotEmpty(queryMap.get("startTime"))) {
			sql.append(" AND  to_char(F.clockdate,'yyyy-mm-dd') >='").append(queryMap.get("startTime")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("endTime"))) {
			sql.append(" AND  to_char(F.clockdate,'yyyy-mm-dd') <='").append(queryMap.get("endTime")).append("'");
		}
		sql.append(" ORDER BY F.CLOCKDATE");
		return getSqlList_(sql.toString(), ClockRecords.class);
	}

	/**
	 * @see com.wgsoft.attendance.clock.idao.IAttendanceExcepDao#getExpApproves(Map)
	 */
	public List<ClockExcepApprove> getExpApproves(Map<String, Object> queryMap) {
		String approverid = (String) queryMap.get("approverid");
		String expid = (String) queryMap.get("expid");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM clock_excep_approve a, clockexception b  where 1=1 and a.expid = b.expid and b.isenable='0' ");
		ClockRecords records = (ClockRecords) queryMap.get("clockRecords");
		if (records != null) {
			if (RunUtil.isNotEmpty(records.getStartTime())) {
				sql.append(" and to_char(appdate,'yyyy=-mm-dd') >= '").append(
						DateUtil.date2String(records.getStartTime(), DateUtil.YMD)).append("'");
			}
			if (RunUtil.isNotEmpty(records.getEndTime())) {
				sql.append(" and to_char(appdate,'yyyy=-mm-dd') <= '").append(
						DateUtil.date2String(records.getEndTime(), DateUtil.YMD)).append("'");
			}
		}
		if (RunUtil.isNotEmpty(expid)) {
			sql.append(" and b.expid  = '").append(expid).append("'");
		}
		if (RunUtil.isNotEmpty(approverid)) {
			sql.append(" and approverid  = '").append(approverid).append("'");
		}
		sql.append(" and status ='").append(SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE).append("'");
		sql.append(" order by appdate desc");

		return getSqlList_(sql.toString(), ClockExcepApprove.class);
	}

	/**
	 * @see com.wgsoft.attendance.clock.idao.IAttendanceExcepDao#updateStatus(Map)
	 */
	public int updateStatus(Map<String, Object> saveMap) {
		String status = (String) saveMap.get("status");
		if (status.equals(SysConstants.ApproverStatus.APPROVER_STATUS_PASS)) {
			status = SysConstants.ISENABLE_NO;
		} else {
			status = SysConstants.ISENABLE_YES;
		}
		String expId = (String) saveMap.get("expid");
		StringBuffer updateSql = new StringBuffer(" update clockexception set isenable = '");
		updateSql.append(status).append("' where expid ='").append(expId).append("'");
		return getSqlUpdate(updateSql.toString());

	}

	/**
	 * @see com.wgsoft.attendance.clock.idao.IAttendanceExcepDao#insertNormalRecords(Map)
	 */
	public int insertNormalRecords(Map<String, Object> saveMap) {
		StringBuffer updateSql = new StringBuffer("  ");
		String expId = (String) saveMap.get("expid");
		if (RunUtil.isNotEmpty(saveMap.get("exists"))) {
			updateSql.append(" UPDATE CLOCKREOCRDS  SET  AMSB = CASE WHEN AMSB IS NOT NULL AND TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' ' || AMSB, ");
			updateSql.append(" 'YYYY-MM-DD HH24:Mi:SS') > (SELECT TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' ' ||  AMSBTIME, 'YYYY-MM-DD HH24:Mi:SS') ");
			updateSql.append(" FROM CLOCK_SETTING WHERE ISENABLE = '0' AND SYSDATE BETWEEN STARTTIME AND ENDTIME) THEN (SELECT AMSBTIME FROM CLOCK_SETTING ");
			updateSql.append(" WHERE ISENABLE = '0' AND SYSDATE BETWEEN STARTTIME AND ENDTIME) WHEN AMSB IS  NULL AND SYSDATE > ");
			updateSql.append(" (SELECT TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' ' || AMSBTIME, 'YYYY-MM-DD HH24:Mi:SS')  FROM CLOCK_SETTING WHERE ");
			updateSql.append(" ISENABLE = '0' AND SYSDATE BETWEEN STARTTIME AND ENDTIME) THEN (SELECT AMSBTIME FROM CLOCK_SETTING WHERE ISENABLE = '0' AND ");
			updateSql.append(" SYSDATE BETWEEN STARTTIME AND ENDTIME)  ELSE AMSB END,");
			
			updateSql.append(" AMXB =  CASE  WHEN AMXB IS NOT NULL AND TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' ' || AMXB, 'YYYY-MM-DD HH24:Mi:SS') < ");
			updateSql.append(" (SELECT TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' ' || AMXBTIME, 'YYYY-MM-DD HH24:Mi:SS') FROM CLOCK_SETTING ");
			updateSql.append("  WHERE ISENABLE = '0' AND SYSDATE BETWEEN STARTTIME AND ENDTIME) THEN (SELECT AMXBTIME FROM CLOCK_SETTING WHERE ISENABLE = '0'  ");
			updateSql.append("  AND SYSDATE BETWEEN STARTTIME AND ENDTIME) WHEN AMXB IS   NULL AND SYSDATE > (SELECT TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD')   ");
			updateSql.append("  || ' ' ||  AMXBTIME, 'YYYY-MM-DD HH24:Mi:SS') FROM CLOCK_SETTING WHERE ISENABLE = '0' AND SYSDATE BETWEEN STARTTIME AND ENDTIME) ");
			updateSql.append(" THEN (SELECT AMXBTIME FROM CLOCK_SETTING WHERE ISENABLE = '0' AND SYSDATE BETWEEN STARTTIME AND ENDTIME)  ELSE AMXB END,");
			
			updateSql.append("  PMSB =   CASE WHEN PMSB IS NOT NULL AND  TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' ' || PMSB, 'YYYY-MM-DD HH24:Mi:SS') > ");
			updateSql.append(" (SELECT TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' ' || PMSBTIME, 'YYYY-MM-DD HH24:Mi:SS') FROM CLOCK_SETTING");
			updateSql.append(" WHERE ISENABLE = '0' AND SYSDATE BETWEEN STARTTIME AND ENDTIME) THEN (SELECT PMSBTIME FROM CLOCK_SETTING WHERE ISENABLE = '0'  ");
			updateSql.append(" AND SYSDATE BETWEEN STARTTIME AND ENDTIME) WHEN PMSB IS   NULL AND SYSDATE  > (SELECT TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD')  ");
			updateSql.append(" || ' ' || PMSBTIME, 'YYYY-MM-DD HH24:Mi:SS') FROM CLOCK_SETTING WHERE ISENABLE = '0' AND SYSDATE BETWEEN STARTTIME AND ENDTIME) ");
			updateSql.append(" THEN (SELECT PMSBTIME FROM CLOCK_SETTING WHERE ISENABLE = '0' AND SYSDATE BETWEEN STARTTIME AND ENDTIME)  ELSE PMSB END, ");
			
			updateSql.append(" PMXB =  CASE WHEN PMXB IS NOT NULL AND TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' ' || PMXB, 'YYYY-MM-DD HH24:Mi:SS') < ");
			updateSql.append(" (SELECT TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' ' || PMXBTIME, 'YYYY-MM-DD HH24:Mi:SS') FROM CLOCK_SETTING ");
			updateSql.append(" WHERE ISENABLE = '0' AND SYSDATE BETWEEN STARTTIME AND ENDTIME) THEN (SELECT PMXBTIME FROM CLOCK_SETTING WHERE ISENABLE = '0' ");
			updateSql.append(" AND SYSDATE BETWEEN STARTTIME AND ENDTIME) WHEN PMXB IS NULL AND  SYSDATE  > (SELECT TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD')  ");
			updateSql.append(" || ' ' || PMXBTIME, 'YYYY-MM-DD HH24:Mi:SS') FROM CLOCK_SETTING WHERE ISENABLE = '0' AND SYSDATE BETWEEN STARTTIME AND ENDTIME) ");
			updateSql.append(" THEN  (SELECT PMXBTIME FROM CLOCK_SETTING WHERE ISENABLE = '0' AND SYSDATE BETWEEN STARTTIME AND ENDTIME)  ELSE PMXB  END ");
			
			updateSql.append(" WHERE to_char(CLOCKDATE,'yyyy-mm-dd') = substr('").append(saveMap.get("clockdate")).append("',0,10)");
			updateSql.append("  AND USERID = '").append(saveMap.get("userid")).append("'");
			 
		} else {
			updateSql.append(" INSERT INTO CLOCKREOCRDS SELECT a.userid,a.clockdate,b.amsbtime,b.amxbtime,b.pmsbtime,");
			updateSql.append(" b.pmxbtime FROM clockexception a ,clock_setting b WHERE  b.starttime <= a.clockdate ");
			updateSql.append(" AND b.endtime >= a.clockdate   AND b.isenable ='0'");
			updateSql.append(" and a.expid ='").append(expId).append("'");
		}
		return getSqlUpdate(updateSql.toString());
	}

	/**
	 * @see com.wgsoft.attendance.clock.idao.IAttendanceExcepDao#getClockRecords(Map)
	 */
	public List<ClockRecords> getClockRecords(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer(" SELECT * FROM CLOCKREOCRDS WHERE  1=1 ");
		String userid = (String) queryMap.get("userid");
		String clockdate = (String) queryMap.get("clockdate");
		if (RunUtil.isNotEmpty(userid)) {
			sql.append(" and userid  = '").append(userid).append("'");
		}
		if (RunUtil.isNotEmpty(clockdate)) {
			sql.append(" and to_char(clockdate,'yyyy-mm-dd')  = substr('").append(clockdate).append("',0,10)");
		}
		return getSqlList_(sql.toString(), ClockRecords.class);
	}

}
