package com.wgsoft.system.idao;

import java.util.List;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.system.model.SysMenu;
import com.wgsoft.system.model.UserMenu;

/**
 * 
 * @title： IMenuDao.java
 * @desc： 菜单管理
 * @author： Willowgao
 * @date： 2015-9-7 下午01:41:56
 * @version： V1.0<br>
 * @versioninfo：慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IMenuDao extends IBaseDao {

	List<SysMenu> getMenuForTree(String userId, String menuId);

	List<SysMenu> getMenu(String userId);

	/**
	 * @desc:查询的所有菜单
	 * @return
	 * @return List<SysMenu>
	 * @date： 2015-9-7 下午01:43:03
	 */
	List<SysMenu> getAllMenu();

	/**
	 * @desc:菜单id得菜单信息
	 * @param sysMenu
	 * @return
	 * @return List<SysMenu>
	 * @date： 2015-9-7 下午01:43:16
	 */
	List<SysMenu> getMenuByMenuID(SysMenu sysMenu);

	/**
	 * @desc:菜单id得菜单信息
	 * @param sysMenu
	 * @return
	 * @return List<SysMenu>
	 * @date： 2015-9-7 下午01:43:16
	 */
	List<UserMenu> getMenuByUserID(String userId);

	/**
	 * @desc:删除用户所属菜单
	 * @param userId
	 * @return
	 * @return int
	 * @date： 2015-9-8 下午03:42:41
	 */
	int deleteUserMenu(String userId);
}
