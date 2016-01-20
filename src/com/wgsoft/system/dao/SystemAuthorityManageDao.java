package com.wgsoft.system.dao;

import java.util.List;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.system.idao.ISystemAuthorityManageDao;
import com.wgsoft.system.model.RoleInfo;
import com.wgsoft.system.model.RoleMenu;

/**
 * @title： SystemAuthorityManageDao.java
 * @desc： 系统权限管理
 * @author： Willowgao
 * @date： 2015-9-7 下午04:28:52
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
@SuppressWarnings("unchecked")
public class SystemAuthorityManageDao extends BaseDao implements ISystemAuthorityManageDao {

	public int deleteRole(final String roleId) {
		try {
			String sql = "DELETE ROLEINFO WHERE ROLEID = '" + roleId + "'";
			getSqlUpdate(sql);
		} catch (Exception e) {
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

	public List<RoleInfo> queryRoleByOrgOrDeptId(final String id, final String type) {
		StringBuffer sql = new StringBuffer("SELECT * FROM ROLEINFO WHERE 1=1 ");
		if (type != null && type.equals(SysConstants.ORGTYPE_ORG)) {
			sql.append(" AND ROLEORG = '").append(id).append("'");
		} else if (type != null && type.equals(SysConstants.ORGTYPE_DEPT)) {
			sql.append(" AND ROLEDEPTID = '").append(id).append("'");
		}
		List<RoleInfo> roles = getSqlList_(sql.toString(), RoleInfo.class);
		return roles;
	}

	public int synchronizedMenuToUsers(final String roleId) {
		try {
			callPrepareCall("{CALL PKG_SYNCHRONIZED_MENU.PRC_SYNCHRONIZEDMENUTOUSER(?,?,?)}", new String[] { roleId,
					null, null });
		} catch (Exception e) {
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

	public List<RoleMenu> queryMenusByRoleId(String roleId) {
		StringBuffer sql = new StringBuffer("SELECT * FROM ROLE_MENU WHERE 1=1 ");
		if (RunUtil.isNotEmpty(roleId)) {
			sql.append(" AND roleId = '").append(roleId).append("'");
		}
		List<RoleMenu> meuns = getSqlList_(sql.toString(), RoleMenu.class);
		return meuns;
	}

	public int deleteRoleMenu(String roleId) {
		try {
			String sql = "DELETE ROLE_MENU WHERE ROLEID = '" + roleId + "'";
			getSqlUpdate(sql);
		} catch (Exception e) {
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

}
