package com.wgsoft.system.action;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.system.iservice.ISystemAuthorityManageService;
import com.wgsoft.system.model.RoleInfo;
import com.wgsoft.system.model.RoleMenu;
import com.wgsoft.system.model.SysMenu;
import com.wgsoft.user.iservice.IUserService;
import com.wgsoft.user.model.UserInfo;

/**
 * 
 * @title： SystemAuthorityManageAction.java
 * @desc： 权限管理
 * @author： Willowgao
 * @date： 2015-9-7 下午02:01:46
 * @version： V1.0<br>
 * @versioninfo：慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class SystemAuthorityManageAction extends BaseAction {

	private ISystemAuthorityManageService authorityManageService = (ISystemAuthorityManageService) super
			.getService("authorityManageService");

	private IUserService userService = (IUserService) super.getService("userService");
	/**
	 * 
	 */
	private static final long serialVersionUID = -811133961490995077L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String getRoles() throws Exception {
		String orgType = request.getParameter("orgType");
		String orgId = request.getParameter("orgId");
		List<RoleInfo> list = authorityManageService.queryRoleByOrgOrDeptId(orgId, orgType);
		this.renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:新增角色
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-7 下午02:22:50
	 */
	public String addRole() throws Exception {
		// 组织id
		String org = request.getParameter("org");
		// 部门id,在选择部门时,组织id为org,否的话，组织id为org
		String deptId = request.getParameter("deptId");
		RoleInfo rInfo = new RoleInfo();
		rInfo.setRolecode(roleCode);
		rInfo.setRoledeptid(deptId);
		rInfo.setRoleorg(org);
		rInfo.setRoletype(roleType);
		rInfo.setRolename(roleName);
		int rel = authorityManageService.addRole(rInfo);
		if (rel == 0) {
			renderText(response, SUCCESS);
		}
		return null;
	}

	/**
	 * @desc:删除角色信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-11 下午03:22:17
	 */
	public String deleteRole() throws Exception {
		String roleId = request.getParameter("roleId");
		int rel = authorityManageService.deleteRole(roleId);
		if (rel == 0) {
			renderText(response, String.valueOf(rel));
		}
		return null;
	}

	/**
	 * @desc:角色受权
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-7 下午02:22:50
	 */
	public String roleAuthority() throws Exception {
		String menuIds = request.getParameter("treeIds");
		String roleId = request.getParameter("roleId");
		String org = request.getParameter("org");
		String synchronizeType = request.getParameter("synchronizeType");
		RoleMenu rMenu = new RoleMenu();
		rMenu.setMenuid(menuIds);
		rMenu.setRoleid(roleId);
		rMenu.setRoleorg(org);

		int rel = authorityManageService.addMenuForRole(rMenu);
		if (rel == 0) {
			renderText(response, String.valueOf(rel));
		}
		// 同步用户菜单数据
		if (synchronizeType.equals("1")) {
			synchronizedMenu(rMenu);
		}
		return null;
	}

	/**
	 * @desc:受权同步
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-7 下午02:22:50
	 */
	public void synchronizedMenu(RoleMenu rMenu) throws Exception {
		authorityManageService.synchronizedMenuToUsers(rMenu.getRoleid());
	}

	/**
	 * @desc:通过角色id查询菜单数据
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-8 上午10:35:00
	 */
	public String queryMenusByRoleId() throws Exception {
		String roleId = request.getParameter("roleId");
		List<SysMenu> menus = authorityManageService.queryMenusByRoleId(roleId);
		renderText(response, JSONUtil.serialize(menus));
		return null;
	}

	/**
	 * @desc:通过角色id查询用户数据
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-8 上午10:35:00
	 */
	public String queryUsersByRoleId() throws Exception {
		String roleId = request.getParameter("roleId");
		UserInfo user = new UserInfo();
		user.setRoleid(roleId);
		List<UserInfo> users = userService.getUserInfoByUserInfo(user);
		this.renderText(response, JSONUtil.serialize(users));
		return null;
	}

	/**
	 * @desc: 查询同部门的核审人员
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-11 上午10:35:08
	 */
	public String getUserForApprove() throws Exception {
		String roleType = request.getParameter("roleType");
		String jsonData = userService.getUserForApprove(roleType, getUserInfo());
		this.renderText(response, jsonData);
		return null;
	}

	/**
	 * @desc: 查询同部门的人员
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-11 上午10:35:08
	 */
	public String getUserForHigher() throws Exception {
		String deptid = request.getParameter("deptid");
		String userid = request.getParameter("userid");
		UserInfo user = new UserInfo();
		BeanUtils.copyProperties(user, getUserInfo());
		if (RunUtil.isNotEmpty(deptid)) {
			user.setUserdeptid(deptid);
		}
		if (RunUtil.isNotEmpty(userid)) {
			user.setUserid(userid);
		}
		String jsonData = userService.getUserForHigher(user);
		this.renderText(response, jsonData);
		return null;
	}

	// 角色名称
	private String roleName;
	// 角色编码
	private String roleCode;
	// 角色类型
	private String roleType;

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}
