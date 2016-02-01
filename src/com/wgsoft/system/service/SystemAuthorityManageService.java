package com.wgsoft.system.service;

import java.util.ArrayList;
import java.util.List;

import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.system.idao.ISystemAuthorityManageDao;
import com.wgsoft.system.iservice.IMenuService;
import com.wgsoft.system.iservice.ISystemAuthorityManageService;
import com.wgsoft.system.model.RoleInfo;
import com.wgsoft.system.model.RoleMenu;
import com.wgsoft.system.model.RoleMenuId;
import com.wgsoft.system.model.SysMenu;
import com.wgsoft.user.model.UserInfo;

/**
 * @title： SystemAuthorityManageService.java
 * @desc：权限管理<包括用户、菜单、角色>
 * @author： Willowgao
 * @date： 2015-9-7 下午02:48:53
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class SystemAuthorityManageService implements ISystemAuthorityManageService {

	private ISystemAuthorityManageDao systemAuthorityManageDao;

	private IMenuService menuService;

	public List<RoleInfo> queryRoleByOrgOrDeptId(String id, String type) {
		return systemAuthorityManageDao.queryRoleByOrgOrDeptId(id, type);
	}

	public int addMenuForRole(RoleMenu rMenu) {
		String[] menusArray = rMenu.getMenuid().split(",");
		List<RoleMenu> roleMenus = new ArrayList<RoleMenu>();
		for (int i = 0; i < menusArray.length; i++) {
			RoleMenu menu = new RoleMenu();
			RoleMenuId rId = new RoleMenuId();
			String menuId = menusArray[i];
			rId.setRoleid(rMenu.getRoleid());
			rId.setMenuid(menuId);
			menu.setId(rId);
			menu.setMenuid(menuId);
			menu.setRoleid(rMenu.getRoleid());
			menu.setRoleorg(rMenu.getRoleorg());
			roleMenus.add(menu);
		}
		// 修改菜单信息之前，先清楚此角色所属的菜单
		if (roleMenus.size() > 0) {
			systemAuthorityManageDao.deleteRoleMenu(rMenu.getRoleid());
		}
		try {
			systemAuthorityManageDao.insertBatch(roleMenus);
		} catch (Exception e) {
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

	public int addRole(RoleInfo rInfo) {
		try {
			systemAuthorityManageDao.insert(rInfo);
		} catch (Exception e) {
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

	public List<SysMenu> queryMenusByRoleId(String roleId) {
		List<SysMenu> menuList = menuService.getAllMenu();
		List<RoleMenu> roleMenuList = systemAuthorityManageDao.queryMenusByRoleId(roleId);
		for (SysMenu sysMenu : menuList) {
			for (RoleMenu roleMenu : roleMenuList) {
				if (roleMenu.getMenuid().equals(sysMenu.getMenuid())) {
					sysMenu.setCheck(true);
				}
			}
		}
		return menuList;
	}

	public int deleteRole(String roleId) {
		int relRole = systemAuthorityManageDao.deleteRole(roleId);
		int relRoleMenu = systemAuthorityManageDao.deleteRoleMenu(roleId);
		return relRole + relRoleMenu;
	}

	public List<UserInfo> queryUsersByRoleId() {
		// TODO Auto-generated method stub
		return null;
	}

	public int synchronizedMenuToUsers(String roleId) {
		return systemAuthorityManageDao.synchronizedMenuToUsers(roleId);
	}

	public ISystemAuthorityManageDao getSystemAuthorityManageDao() {
		return systemAuthorityManageDao;
	}

	public void setSystemAuthorityManageDao(ISystemAuthorityManageDao systemAuthorityManageDao) {
		this.systemAuthorityManageDao = systemAuthorityManageDao;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

}
