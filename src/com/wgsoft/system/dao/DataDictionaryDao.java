package com.wgsoft.system.dao;

import java.util.List;

import com.wgsoft.attendance.clock.model.LeaveAppSet;
import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.system.idao.IDataDictionaryDao;
import com.wgsoft.system.model.DataDictionary;
import com.wgsoft.system.model.Deptment;

/**
 * 
 * @title： DataDictionaryDao.java
 * @desc：
 * @author： Willowgao
 * @date： 2015-10-27 下午01:27:24
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
@SuppressWarnings("unchecked")
public class DataDictionaryDao extends BaseDao implements IDataDictionaryDao {

	/**
	 * @see com.wgsoft.system.idao.IDataDictionaryDao#getAllDataDictionary()
	 */
	public List<DataDictionary> getAllDataDictionary() {
		StringBuffer sql = new StringBuffer("select * from DataDictionary ");
		// 组织
		sql.append(" UNION ALL SELECT 'ORG', '经办机构', ORGID, ORGNAME   FROM WG_ORGANIZATION ");
		// 部门
		sql.append(" UNION SELECT 'DEPT', '部门', DEPTID, DEPTNAME FROM DEPTMENT ");
		// 人员
		sql.append(" UNION SELECT 'USER', '人员', USERID, USERNAME FROM USERINFO ");
		// 角色
		sql.append(" UNION SELECT 'ROLE', '角色', ROLEID, ROLENAME FROM ROLEINFO ");
		return getSqlList_(sql.toString(), DataDictionary.class);
	}

	/**
	 * @see com.wgsoft.system.idao.IDataDictionaryDao#getAllLeaveAppSet()
	 */
	public List<LeaveAppSet> getAllLeaveAppSet() {
		StringBuffer sql = new StringBuffer("select * from leave_approve_setting where approvetype='LEAVE' ");
		return getSqlList_(sql.toString(), LeaveAppSet.class);
	}

	/**
	 * @see com.wgsoft.system.idao.IDataDictionaryDao#getDeptByOrg(String)
	 */
	public List<Deptment> getDeptByOrg(String org) {
		StringBuffer sql = new StringBuffer("select deptid,deptname FROM DEPTMENT  WHERE ORGID IN (SELECT ORGID");
		sql.append("  FROM WG_ORGANIZATION A START WITH A.ORGID = '").append(org).append(
				"'  CONNECT BY PRIOR A.ORGID = A.PARENTID)");
		return getSqlList_(sql.toString(), Deptment.class);
	}

	/**
	 * @see com.wgsoft.system.idao.IDataDictionaryDao#getDeptByUser(String)
	 */
	public List<Deptment> getDeptByUser(String userid) {
		StringBuffer sql = new StringBuffer("select deptid,deptname FROM DEPTMENT  START WITH DEPTID = ");
		sql.append("(SELECT USERDEPTID FROM USERINFO WHERE USERID = '").append(userid).append(
				"' CONNECT BY PRIOR DEPTID = PARENTID ");
		return getSqlList_(sql.toString(), Deptment.class);
	}

}
