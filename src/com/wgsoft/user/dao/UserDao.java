package com.wgsoft.user.dao;

import java.util.List;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SecurityUtils;
import com.wgsoft.system.model.Deptment;
import com.wgsoft.system.model.RoleInfo;
import com.wgsoft.user.idao.IUserDao;
import com.wgsoft.user.model.BacklogWork;
import com.wgsoft.user.model.UserInfo;

/**
 * @title： UserDao.java
 * @desc： 用户信息数据服务DAO
 * @author： Willowgao
 * @date： 2015-11-2 上午09:46:03
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
@SuppressWarnings("unchecked")
public class UserDao extends BaseDao implements IUserDao {

	/**
	 * @see com.wgsoft.user.idao.IUserDao#getUsers()
	 */
	public List<UserInfo> getUsers() {
		List<UserInfo> users = getSqlList_("select * from userInfo", UserInfo.class);
		return users;
	}

	/**
	 * @see com.wgsoft.user.idao.IUserDao#getUserDeptments(UserInfo)
	 */
	public List<Deptment> getUserDeptments(UserInfo user) {
		StringBuffer str = new StringBuffer("select * from deptment where 1=1 ");
		if (user != null) {
			if (RunUtil.isNotEmpty(user.getUserorg())) {
				str.append(" and orgid ='").append(user.getUserorg()).append("'");
			}
		}
		List<Deptment> depts = getSqlList_(str.toString(), Deptment.class);
		return depts;
	}

	/**
	 * @see com.wgsoft.user.idao.IUserDao#getUserInfoByUName(String, String)
	 */
	public List<UserInfo> getUserInfoByUName(String userName, String pwd) {
		List<UserInfo> users = getSqlList_(
				"select a.*,b.roletype from userInfo a,roleinfo b WHERE a.roleid = b.roleid and  a.usercode='"
						+ userName + "' and a.userpwd='" + SecurityUtils.getPwdForDb(SecurityUtils.MD5(pwd)) + "'",
				UserInfo.class);
		return users;
	}

	/**
	 * @see com.wgsoft.user.idao.IUserDao#getUserInfoByUserInfo(UserInfo)
	 */
	public List<UserInfo> getUserInfoByUserInfo(UserInfo user) {
		StringBuffer str = new StringBuffer("select * from userInfo where 1=1 ");
		if (!RunUtil.isEmpty(user.getUserorg())) {
			str.append(" and userorg='" + user.getUserorg() + "' ");
		}
		if (!RunUtil.isEmpty(user.getUserid())) {
			str.append(" and userid='" + user.getUserid() + "' ");
		}
		if (!RunUtil.isEmpty(user.getUsercode())) {
			str.append(" and usercode='" + user.getUsercode() + "' ");
		}
		if (!RunUtil.isEmpty(user.getUserdeptid())) {
			str.append(" and userdeptId='" + user.getUserdeptid() + "' ");
		}
		if (!RunUtil.isEmpty(user.getRoleid())) {
			str.append(" and roleId='" + user.getRoleid() + "' ");
		}
		List<UserInfo> users = getSqlList_(str.toString(), UserInfo.class);
		return users;
	}

	/**
	 * @see com.wgsoft.user.idao.IUserDao#checkUserCode(String)
	 */
	public boolean checkUserCode(String userCode) {
		boolean bool = false;
		List<UserInfo> users = getSqlList_("select * from userInfo where usercode='" + userCode + "'", UserInfo.class);
		if (users != null && users.size() > 0) {
			bool = true;
		}
		return bool;
	}

	/**
	 * @see com.wgsoft.user.idao.IUserDao#getRoleInfoByOrg(String, UserInfo)
	 */
	public List<RoleInfo> getRoleInfoByOrg(UserInfo user) {
		StringBuffer str = new StringBuffer("select * from roleinfo where 1=1 ");
		if (!RunUtil.isEmpty(user.getUserorg())) {
			str.append(" and roleorg='" + user.getUserorg() + "' ");
		}
		if (!RunUtil.isEmpty(user.getUserdeptid())) {
			str.append(" and (roledeptid='" + user.getUserdeptid() + "' OR roledeptid is null)");
		}
		if (!RunUtil.isEmpty(user.getRoleid())) {
			str.append(" and roleId='" + user.getRoleid() + "' ");
		}
		List<RoleInfo> roles = getSqlList_(str.toString(), RoleInfo.class);
		return roles;
	}

	/**
	 * @see com.wgsoft.user.idao.IUserDao#getUserForApprove(String, UserInfo)
	 */
	public List<UserInfo> getUserForApprove(String roleType, UserInfo user) {
		StringBuffer str = new StringBuffer("SELECT * FROM USERINFO  WHERE ROLEID IN");
		str.append(" (SELECT ROLEID FROM ROLEINFO WHERE ROLETYPE = '").append(roleType).append("')");
		str.append(" AND USERDEPTID = '").append(user.getUserdeptid()).append("'");
		List<UserInfo> users = getSqlList_(str.toString(), UserInfo.class);
		return users;
	}

	/**
	 * @see com.wgsoft.user.idao.IUserDao#getUserForHigher(String, UserInfo)
	 */
	public List<UserInfo> getUserForHigher(UserInfo user) {
		StringBuffer str = new StringBuffer("SELECT * FROM USERINFO  WHERE USERDEPTID = '");
		str.append(user.getUserdeptid()).append("'");
		str.append(" and userid !='").append(user.getUserid()).append("'");
		List<UserInfo> users = getSqlList_(str.toString(), UserInfo.class);
		return users;
	}

	public void updateUserBySql(UserInfo user) {
		StringBuffer sql = new StringBuffer("UPDATE USERINFO SET USERPWD = decode('");
		sql.append(user.getUserpwd()).append("','null',userpwd,'").append(user.getUserpwd()).append("') ");
		sql.append(", DEFAULTSTYLE = '").append(user.getDefaultstyle()).append("'");
		sql.append(" WHERE userid ='").append(user.getUserid()).append("'");
		;
		getSqlUpdate(sql.toString());
	}

	public List<BacklogWork> getWorks(UserInfo user) {
		StringBuffer sql = new StringBuffer(
				" select * from (SELECT '' url,COMMENTS, STARTTIME, ENDTIME, ENDTIME settime  FROM JOBASSIGNMENT WHERE WORKTIME IS NULL AND STATUS = '2' ");
		sql.append(" and EXECUTIVER ='").append(user.getUserid()).append("'");
		// 周报
		sql
				.append(" UNION SELECT '/diary/diaryDaily!weekly.action?type=2','工作周报', TRUNC(SYSDATE, 'DD') - TO_CHAR(SYSDATE, 'D') - 5, TRUNC(SYSDATE, 'DD') - TO_CHAR(SYSDATE, 'D') - 1,");
		sql.append(" TRUNC(SYSDATE, 'DD') - TO_CHAR(SYSDATE, 'D') + 3 ENDTIME   FROM DUAL A  WHERE NOT EXISTS");
		sql
				.append(" (SELECT * FROM DIARY_DAILY B  WHERE B.DIARYTYPE = '2'  AND B.STARTTIME = TRUNC(SYSDATE, 'DD') - TO_CHAR(SYSDATE, 'D') - 5");
		sql.append(" AND B.ENDTIME = TRUNC(SYSDATE, 'DD') - TO_CHAR(SYSDATE, 'D') - 1");
		sql.append("  AND B.USERID  ='").append(user.getUserid()).append("')");
		sql.append("  AND SYSDATE >= TRUNC(SYSDATE, 'DD') - TO_CHAR(SYSDATE, 'D') + 3");
		// 月报
		sql
				.append(" UNION SELECT '/diary/diaryDaily!weekly.action?type=3','工作月报',ADD_MONTHS(TO_DATE(TO_CHAR(SYSDATE, 'yyyy-mm') || '-01', 'yyyy-mm-dd'),-1),");
		sql
				.append(" TO_DATE(TO_CHAR(SYSDATE, 'yyyy-mm') || '-01', 'yyyy-mm-dd') - 1, TO_DATE(TO_CHAR(SYSDATE, 'yyyy-mm') || '-03', 'yyyy-mm-dd') ENDTIME");
		sql.append("  FROM DUAL A  WHERE NOT EXISTS  (SELECT 1  FROM DIARY_DAILY B WHERE B.STARTTIME =");
		sql.append("  ADD_MONTHS(TO_DATE(TO_CHAR(SYSDATE, 'yyyy-mm') || '-01',  'yyyy-mm-dd'), -1)");
		sql.append(" AND B.ENDTIME = TO_DATE(TO_CHAR(SYSDATE, 'yyyy-mm') || '-01', 'yyyy-mm-dd') - 1");
		sql.append("  AND B.USERID  ='").append(user.getUserid()).append("'  AND B.DIARYTYPE = '3')");
		sql.append(" AND SYSDATE >= TO_DATE(TO_CHAR(SYSDATE, 'yyyy-mm') || '-03', 'yyyy-mm-dd')");
		// 季报
		sql
				.append(" UNION SELECT '/diary/diaryDaily!weekly.action?type=4','工作季报', ADD_MONTHS(TRUNC(SYSDATE, 'q'), -3),TRUNC(SYSDATE, 'q') - 1, TRUNC(SYSDATE, 'q') + 1 ENDTIME");
		sql
				.append("  FROM DUAL A  WHERE NOT EXISTS (SELECT *  FROM DIARY_DAILY B   WHERE B.STARTTIME = ADD_MONTHS(TRUNC(SYSDATE, 'q'), -3)");
		sql.append("  AND B.ENDTIME = TRUNC(SYSDATE, 'q') - 1");
		sql.append("  AND B.USERID  ='").append(user.getUserid()).append("'  AND B.DIARYTYPE = '4' )");
		sql.append("    AND SYSDATE >= TRUNC(SYSDATE, 'q') + 1 ");

		sql.append(") order by settime ");
		return getSqlList_(sql.toString(), BacklogWork.class);
	}
}
