package com.wgsoft.system.dao;

import java.util.List;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.model.Organization;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.system.idao.IOrgDao;
import com.wgsoft.system.model.Deptment;

@SuppressWarnings("unchecked")
public class OrgDao extends BaseDao implements IOrgDao {

	public List<Organization> getOrgsForTree(String orgId) {
		StringBuffer strSQL = new StringBuffer(
				"SELECT *  FROM (SELECT ORGID, PARENTID, ORGNAME, '1' ORGTYPE, NULL DEPTID, ORGID ORG  FROM WG_ORGANIZATION");
		strSQL.append("  WHERE ORGID   IN (SELECT ORGID FROM WG_ORGANIZATION )");
		strSQL
				.append(" UNION SELECT DEPTID, ORGID, DEPTNAME, '2' ORGTYPE, DEPTID DEPTID,ORGID ORG FROM DEPTMENT WHERE ORGID  IN");
		strSQL.append(" (SELECT ORGID FROM WG_ORGANIZATION )) WHERE 1=1 START WITH ORGID = '").append(orgId);
		strSQL.append("' CONNECT BY PRIOR ORGID = PARENTID");
		List<Organization> list = getSqlList_(strSQL.toString(), Organization.class);
		return list;
	}

	public List<Deptment> queryOrgsForList(String orgId, String orgType) {
		StringBuffer strSQL = new StringBuffer(
				"SELECT * FROM (SELECT ORGID DEPTID,ORGNAME deptname,ORGID, '1' ORGTYPE FROM WG_ORGANIZATION");
		strSQL.append(" UNION SELECT  DEPTID,DEPTNAME,ORGID,'2' ORGTYPE FROM DEPTMENT) WHERE 1=1 ");
		if (RunUtil.isNotEmpty(orgType)) {
			if (RunUtil.isNotEmpty(orgType) && orgType.equals(SysConstants.ORGTYPE_ORG)) {
				strSQL.append(" AND ORGID = '").append(orgId).append("'");
			}
			if (RunUtil.isNotEmpty(orgType) && orgType.equals(SysConstants.ORGTYPE_DEPT)) {
				strSQL.append(" AND DEPTID = '").append(orgId).append("'");
			}
		}
		List<Deptment> list = getSqlList_(strSQL.toString(), Deptment.class);
		return list;
	}

	public int saveOrUpdateDepement(Deptment dept, String orgType, String deptId) {
		try {
			if (RunUtil.isNotEmpty(deptId)) {
				if (orgType.equals(SysConstants.ORGTYPE_ORG)) {
					Organization org = new Organization();
					org.setOrgid(dept.getOrgid());
					org.setOrgname(dept.getDeptname());
					update(org);
				} else {
					update(dept);
				}
			} else {// 不做组织的新增
				insert(dept);
			}
		} catch (Exception e) {
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

}
