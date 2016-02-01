package com.wgsoft.system.action;

import java.util.List;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.system.iservice.IMenuService;
import com.wgsoft.system.model.SysMenu;

/**
 * @title： IndexAction.java
 * @desc： 首页
 * @author： Willowgao
 * @date： 2015-9-10 下午12:37:18
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class IndexAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8979957589408583893L;
	private IMenuService menuService = (IMenuService) getService("menuService");


	/**
	 * @desc:查询横向树
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-10 下午12:37:37
	 */
	public String showMenu() throws Exception {
		if (getUserInfo() == null) {
			return LOGIN;
		}
		List<SysMenu> menuList = menuService.getMenu(getUserInfo().getUserid());
		String[] menus = new String[menuList.size()];
		int i = 0;
		for (SysMenu menu : menuList) {
			menus[i] = menu.getMenuname() + "_" + menu.getMenuid();
			i++;
		}
		renderText(response, JSONUtil.serialize(menus));
		return null;
	}

	/**
	 * @desc:查询模块下的树
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-10 下午12:37:53
	 */
	public String showTree() throws Exception {
		if (getUserInfo() == null) {
			return LOGIN;
		}
		String menuId = request.getParameter("menuId");
		List<SysMenu> treeList = menuService.getMenuForTree(getUserInfo().getUserid(), menuId);
		renderText(response, JSONUtil.serialize(treeList));
		return null;
	}
}
