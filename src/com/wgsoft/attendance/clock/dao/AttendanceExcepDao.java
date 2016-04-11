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
		StringBuffer sql = new StringBuffer(
				"SELECT F.CLOCKDATE,NVL(F.USERID,'"
						+ queryMap.get("userId")
						+ "') USERID,AMSB,AMXB,PMSB,PMXB,ISNEED,G.AMSBTIME,G.AMXBTIME,G.PMSBTIME,G.PMXBTIME FROM ");
		sql
				.append(" (SELECT USERID, CLOCKDATE,MIN(ISNEED) ISNEED, MAX(AMSB) AMSB,MAX(AMXB) AMXB,MAX(PMSB) PMSB,MAX(PMXB) PMXB");
		sql
				.append("  FROM (SELECT '9999' ISNEED,USERID,CLOCKDATE,AMSB,AMXB,PMSB,PMXB FROM CLOCKREOCRDS B ");
		sql.append("  WHERE USERID ='" + queryMap.get("userId")
				+ "'  UNION SELECT ISNEED,'" + queryMap.get("userId")
				+ "' userid,");
		sql
				.append(" CLOCKDATE CLOCKDATE, NULL,NULL,NULL,NULL FROM CLOCKDATE_SETTING A) GROUP BY USERID, CLOCKDATE) F,CLOCK_SETTING G ");
		sql
				.append(" WHERE F.CLOCKDATE BETWEEN G.STARTTIME AND G.ENDTIME AND G.ISENABLE = '0' ");

		if (RunUtil.isNotEmpty(queryMap.get("isExcep"))) {
			sql
					.append("  AND ((F.AMSB IS NULL OR F.AMXB IS NULL OR F.PMSB IS NULL OR   F.PMXB IS NULL) OR  (EXISTS  (SELECT 1");
			sql
					.append("   FROM CLOCKEXCEPTION H  WHERE F.CLOCKDATE = TO_DATE(TO_CHAR(H.CLOCKDATE, 'yyyy-mm-dd'), 'yyyy-mm-dd')");
			sql.append("  AND H.USERID = F.USERID))) ");
		}

		if (RunUtil.isNotEmpty(queryMap.get("clockdate"))) {
			sql.append(" AND  F.clockdate =to_date('").append(
					DateUtil.date2String((Date) queryMap.get("clockdate"),
							DateUtil.YMD)).append("','yyyy-mm-dd')");
		}

		if (RunUtil.isEmpty(queryMap.get("startTime"))
				&& RunUtil.isEmpty(queryMap.get("endTime"))) {
			sql
					.append(" AND F.CLOCKDATE BETWEEN (SYSDATE - 15) AND (SYSDATE + 15) ");
		}

		if (RunUtil.isNotEmpty(queryMap.get("startTime"))) {
			sql.append(" AND  to_char(F.clockdate,'yyyy-mm-dd') >='").append(
					queryMap.get("startTime")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("endTime"))) {
			sql.append(" AND  to_char(F.clockdate,'yyyy-mm-dd') <='").append(
					queryMap.get("endTime")).append("'");
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
						DateUtil.date2String(records.getStartTime(),
								DateUtil.YMD)).append("'");
			}
			if (RunUtil.isNotEmpty(records.getEndTime())) {
				sql.append(" and to_char(appdate,'yyyy=-mm-dd') <= '")
						.append(
								DateUtil.date2String(records.getEndTime(),
										DateUtil.YMD)).append("'");
			}
		}
		if (RunUtil.isNotEmpty(expid)) {
			sql.append(" and b.expid  = '").append(expid).append("'");
		}
		if (RunUtil.isNotEmpty(approverid)) {
			sql.append(" and approverid  = '").append(approverid).append("'");
		}
		sql.append(" and status ='").append(
				SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE)
				.append("'");
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
		StringBuffer updateSql = new StringBuffer(
				" update clockexception set isenable = '");
		updateSql.append(status).append("' where expid ='").append(expId)
				.append("'");
		return getSqlUpdate(updateSql.toString());

	}

	/**
	 * @see com.wgsoft.attendance.clock.idao.IAttendanceExcepDao#insertNormalRecords(Map)
	 */
	public int insertNormalRecords(Map<String, Object> saveMap) {
		StringBuffer updateSql = new StringBuffer("  ");
		String expId = (String) saveMap.get("expid");
		if (RunUtil.isNotEmpty(saveMap.get("exists"))) {
			try {

				callPrepareCall(
						"{CALL PKG_SYNCHRONIZED_MENU.PRC_SETCLOCKEXCEPTIONTONOMARL(?,?,?,?)}",
						new String[] { saveMap.get("userid").toString(),
								saveMap.get("clockdate").toString(), null, null });

			} catch (Exception e) {
				System.out.println(e.getMessage());
				return SysConstants.ERROR;
			}
			return SysConstants.SUCCESS;

		} else {
			updateSql
					.append(" INSERT INTO CLOCKREOCRDS SELECT a.userid,a.clockdate,b.amsbtime,b.amxbtime,b.pmsbtime,");
			updateSql
					.append(" b.pmxbtime FROM clockexception a ,clock_setting b WHERE  b.starttime <= a.clockdate ");
			updateSql
					.append(" AND b.endtime >= a.clockdate   AND b.isenable ='0'");
			updateSql.append(" and a.expid ='").append(expId).append("'");
		}
		return getSqlUpdate(updateSql.toString());
	}

	/**
	 * @see com.wgsoft.attendance.clock.idao.IAttendanceExcepDao#getClockRecords(Map)
	 */
	public List<ClockRecords> getClockRecords(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer(
				" SELECT * FROM CLOCKREOCRDS WHERE  1=1 ");
		String userid = (String) queryMap.get("userid");
		String clockdate = (String) queryMap.get("clockdate");
		if (RunUtil.isNotEmpty(userid)) {
			sql.append(" and userid  = '").append(userid).append("'");
		}
		if (RunUtil.isNotEmpty(clockdate)) {
			sql.append(" and to_char(clockdate,'yyyy-mm-dd')  = substr('")
					.append(clockdate).append("',0,10)");
		}
		return getSqlList_(sql.toString(), ClockRecords.class);
	}

}
