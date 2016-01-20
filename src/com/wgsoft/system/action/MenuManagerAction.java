package com.wgsoft.system.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.system.iservice.IMenuService;
import com.wgsoft.system.model.SysMenu;
import com.wgsoft.system.model.UserMenu;

/**
 * 
 * @title： MenuManagerAction.java
 * @desc： 菜单管理
 * @author： Willowgao
 * @date： 2015-9-7 下午01:39:39
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class MenuManagerAction extends BaseAction {

	private IMenuService menuService = (IMenuService) getService("menuService");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1492788832215445162L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String getSysDate()throws Exception{
		renderText(response, DateUtil.date2String(new Date(),DateUtil.HMS));
		return null;
	}
	/**
	 * @desc:查询菜单树
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-7 下午01:39:56
	 */
	public String showTree() throws Exception {
		List<SysMenu> treeList = menuService.getAllMenu();
		renderText(response, JSONUtil.serialize(treeList));
		return null;
	}

	/**
	 * @desc:系统菜单和用户菜单的匹配项查询
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-8 下午03:31:04
	 */
	public String showTreeByUserId() throws Exception {
		String userId = request.getParameter("userId");
		List<SysMenu> list = menuService.showTreeByUserId(userId);
		renderText(response, JSONUtil.serialize(list));
		return null;
	}

	public String saveMenuForUser() throws Exception {

		String menuIds = request.getParameter("treeIds");
		String userId = request.getParameter("userId");
		String org = request.getParameter("org");
		UserMenu uMenu = new UserMenu();
		uMenu.setMenuid(menuIds);
		uMenu.setUserid(userId);
		uMenu.setUserorg(org);
		int rel = menuService.addMenuForUser(uMenu);
		if (rel == 0) {
			renderText(response, String.valueOf(rel));
		}
		return null;
	}

	/**
	 * @desc:保存菜单信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-7 下午01:40:06
	 */
	public String saveMenu() throws Exception {
		SysMenu sysMenu = new SysMenu();
		sysMenu.setAction(action);
		sysMenu.setMenuname(menuname);
		sysMenu.setIsdisable(isdisable);
		sysMenu.setParentid(request.getParameter("parentId"));
		sysMenu.setXh(xh);

		String type = request.getParameter("type");
		if (!type.equals("add")) {
			sysMenu.setMenuid(request.getParameter("menuId"));
		} else {
			// 如果为新增时，当前选择的菜单则为上级菜单
			sysMenu.setParentid(request.getParameter("menuId"));
		}
		renderText(response, menuService.saveOrUpdateMenu(sysMenu, type) + "");
		return null;
	}

	private String menuname;
	private String action;
	private String xh;
	private String isdisable;

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getIsdisable() {
		return isdisable;
	}

	public void setIsdisable(String isdisable) {
		this.isdisable = isdisable;
	}

}
