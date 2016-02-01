package com.wgsoft.system.dao;

import java.util.Arrays;
import java.util.List;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.system.idao.IMenuDao;
import com.wgsoft.system.model.SysMenu;
import com.wgsoft.system.model.UserMenu;

/**
 * @title： MenuDao.java
 * @desc： 菜单管理
 * @author： Willowgao
 * @date： 2015-9-7 下午02:00:03
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
@SuppressWarnings("unchecked")
public class MenuDao extends BaseDao implements IMenuDao {

	// 在查询菜单时 masoft,sysadmin 的菜单做控制
	private static final List SYSADMIN = Arrays.asList("0000000", "9999999");

	/**
	 * 查询菜单树
	 */
	public List<SysMenu> getMenuForTree(final String userId, final String menuId) {
		final StringBuffer sql = new StringBuffer(
				"SELECT MENUID,PARENTID,ACTION,MENUNAME FROM MENU WHERE  ISDISABLE='0'");
		if (!SYSADMIN.contains(userId)) {
			sql.append("  AND MENUID IN (SELECT MENUID FROM USER_MENU WHERE USERID = '").append(userId).append("')");
			sql.append("  START WITH  menuid = '").append(menuId);
			sql.append("' CONNECT BY PRIOR");
			sql.append(" MENUID = PARENTID ORDER BY XH ");
		} else {
			sql.append("  START WITH  menuid = '").append(menuId);
			sql.append("' CONNECT BY PRIOR");
			sql.append(" MENUID = PARENTID ORDER BY XH ");
		}
		final List<SysMenu> list = getSqlList_(sql.toString(), SysMenu.class);
		return list;
	}

	/**
	 * 查询菜单
	 */
	public List<SysMenu> getMenu(final String userId) {
		final StringBuffer sql = new StringBuffer("SELECT MENUNAME,MENUID ");
		if (!SYSADMIN.contains(userId)) {
			sql.append(" FROM MENU  WHERE MENUID IN (SELECT MENUID FROM USER_MENU WHERE USERID = '").append(userId)
					.append("') AND UPPER(PARENTID) ='ROOT' AND ISDISABLE='0'");
			sql.append("  ORDER BY XH ");
		} else {
			sql.append(" FROM MENU  WHERE UPPER(PARENTID) ='ROOT' AND ISDISABLE='0'");
			sql.append("  ORDER BY XH ");
		}
		final List<SysMenu> list = getSqlList_(sql.toString(), SysMenu.class);
		return list;
	}

	/**
	 * 查询菜单
	 */
	public List<SysMenu> getMenuByMenuID(final SysMenu sysMenu) {
		final StringBuffer sql = new StringBuffer("SELECT MENUNAME,MENUID  FROM MENU  where 1=1 ");
		if (RunUtil.isNotEmpty(sysMenu.getMenuid())) {
			sql.append(" AND MENUID = '").append(sysMenu.getMenuid()).append("'");
		}
		final List<SysMenu> list = getSqlList_(sql.toString(), SysMenu.class);
		return list;
	}

	/**
	 * 查询菜单
	 */
	public List<UserMenu> getMenuByUserID(final String userId) {
		final StringBuffer sql = new StringBuffer("SELECT MENUID  FROM USER_MENU  where 1=1 ");
		if (RunUtil.isNotEmpty(userId)) {
			sql.append(" AND userId = '").append(userId).append("'");
		}
		final List<UserMenu> list = getSqlList_(sql.toString(), UserMenu.class);
		return list;
	}

	/**
	 * 查询所有菜单
	 */
	public List<SysMenu> getAllMenu() {
		final StringBuffer sql = new StringBuffer(
				"SELECT *  FROM MENU  START WITH PARENTID IS NULL CONNECT BY PRIOR MENUID = PARENTID ORDER BY XH ");
		final List<SysMenu> list = getSqlList_(sql.toString(), SysMenu.class);
		return list;
	}

	public int deleteUserMenu(String userId) {
		try {
			String sql = "DELETE USER_MENU WHERE userId = '" + userId + "'";
			getSqlUpdate(sql);
		} catch (Exception e) {
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

}
