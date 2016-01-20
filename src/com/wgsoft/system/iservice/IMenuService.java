package com.wgsoft.system.iservice;

import java.util.List;

import com.wgsoft.system.model.SysMenu;
import com.wgsoft.system.model.UserMenu;

/**
 * 
 * @title： IMenuService.java
 * @desc： 菜单管理
 * @author： Willowgao
 * @date： 2015-9-7 下午01:40:45
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IMenuService {

	/**
	 * @desc:查询菜单树
	 * @param userId
	 * @param menuId
	 * @return
	 * @return List<SysMenu>
	 * @date： 2015-9-7 下午01:41:41
	 */
	List<SysMenu> getMenuForTree(String userId, String menuId);

	/**
	 * @desc:根据用户查询用户可用的菜单
	 * @param userId
	 * @return
	 * @return List<SysMenu>
	 * @date： 2015-9-7 下午01:41:01
	 */
	List<SysMenu> getMenu(String userId);

	/**
	 * @desc:查询所有菜单
	 * @return
	 * @return List<SysMenu>
	 * @date： 2015-9-7 下午01:41:17
	 */
	List<SysMenu> getAllMenu();

	/**
	 * @desc:菜单修改
	 * @param sysMenu
	 * @param type
	 * @return
	 * @return int
	 * @date： 2015-9-7 下午01:41:24
	 */
	int saveOrUpdateMenu(SysMenu sysMenu, String type);

	/**
	 * @desc:根据用户查询用户已经拥有的菜单
	 * @param userId
	 * @return
	 * @return List<SysMenu>
	 * @date： 2015-9-7 下午01:41:01
	 */
	List<SysMenu> showTreeByUserId(String userId);

	/**
	 * @desc:给用户增加菜单
	 * @return
	 * @return int
	 * @date： 2015-9-8 下午03:34:15
	 */
	int addMenuForUser(UserMenu uMenu);

}
