package com.wgsoft.system.service;

import java.util.ArrayList;
import java.util.List;

import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.system.idao.IMenuDao;
import com.wgsoft.system.iservice.IMenuService;
import com.wgsoft.system.model.RoleMenu;
import com.wgsoft.system.model.SysMenu;
import com.wgsoft.system.model.UserMenu;
import com.wgsoft.system.model.UserMenuId;

public class MenuService implements IMenuService {

	private IMenuDao menuDao;

	public List<SysMenu> getMenuForTree(String userId, String menuId) {
		List<SysMenu> list = menuDao.getMenuForTree(userId, menuId);
		return list;
	}

	public List<SysMenu> getMenu(String userId) {
		List<SysMenu> list = menuDao.getMenu(userId);
		return list;
	}

	public List<SysMenu> getAllMenu() {
		List<SysMenu> list = menuDao.getAllMenu();
		return list;
	}

	public List<SysMenu> showTreeByUserId(String userId) {
		List<SysMenu> menus = getAllMenu();
		List<UserMenu> userMenus = menuDao.getMenuByUserID(userId);
		for (SysMenu sysMenu : menus) {
			for (UserMenu userMenu : userMenus) {
				if (sysMenu.getMenuid().equals(userMenu.getMenuid())) {
					sysMenu.setCheck(true);
				}
			}
		}
		return menus;
	}

	public int saveOrUpdateMenu(SysMenu sysMenu, String type) {
		if (type.equals("add")) {
			try {
				menuDao.insert(sysMenu);
			} catch (Exception e) {
				return SysConstants.ERROR;
			}
		} else {
			try {
				menuDao.update(sysMenu);
			} catch (Exception e) {
				return SysConstants.ERROR;
			}
		}
		return SysConstants.SUCCESS;

	}

	public IMenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public int addMenuForUser(UserMenu uMenu) {
		String[] menusArray = uMenu.getMenuid().split(",");
		List<UserMenu> userMenus = new ArrayList<UserMenu>();
		for (int i = 0; i < menusArray.length; i++) {
			UserMenu menu = new UserMenu();
			UserMenuId rId = new UserMenuId();
			String menuId = menusArray[i];
			rId.setUserid(uMenu.getUserid());
			rId.setMenuid(menuId);
			menu.setId(rId);
			menu.setMenuid(menuId);
			menu.setUserid(uMenu.getUserid());
			menu.setUserorg(uMenu.getUserorg());
			userMenus.add(menu);
		}
		if (userMenus.size() > 0) {
			menuDao.deleteUserMenu(uMenu.getUserid());
		}
		try {
			menuDao.insertBatch(userMenus);
		} catch (Exception e) {
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

}
