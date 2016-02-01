package com.wgsoft.system.iservice;

import java.util.List;

import com.wgsoft.system.model.RoleInfo;
import com.wgsoft.system.model.RoleMenu;
import com.wgsoft.system.model.SysMenu;
import com.wgsoft.user.model.UserInfo;

/**
 * 
 * @title： ISystemAuthorityManageService.java
 * @desc： 系统权限管理
 * @author： Willowgao
 * @date： 2015-9-7 下午02:44:36
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface ISystemAuthorityManageService {

	/**
	 * @desc:通过ID查询角色信息
	 * @param id
	 * @param type
	 * @return
	 * @return List<RoleInfo>
	 * @date： 2015-9-7 下午04:26:17
	 */
	List<RoleInfo> queryRoleByOrgOrDeptId(String id, String type);

	/**
	 * @desc:查询部门或组织下所有的用户信息
	 * @return
	 * @return List<Organization>
	 * @date： 2015-9-7 下午02:45:00
	 */
	List<UserInfo> queryUsersByRoleId();

	/**
	 * @desc:通过角色id查询菜单数据
	 * @return
	 * @return List<Organization>
	 * @date： 2015-9-7 下午02:45:00
	 */
	List<SysMenu> queryMenusByRoleId(String roleId);

	/**
	 * @desc:增加角色信息
	 * @param rInfo
	 * @return
	 * @return int
	 * @date： 2015-9-7 下午02:45:29
	 */
	int addRole(RoleInfo rInfo);

	/**
	 * @desc:给角色信息增加菜单
	 * @param roleId
	 * @param menus
	 * @return
	 * @return int
	 * @date： 2015-9-7 下午02:45:38
	 */
	int addMenuForRole(RoleMenu rMenu);

	/**
	 * @desc:删除角色信息,同时需要删除用户下的菜单信息
	 * @param roleId
	 * @return
	 * @return int
	 * @date： 2015-9-7 下午02:45:56
	 */
	int deleteRole(String roleId);

	/**
	 * @desc: 将角色菜单信息同步到用户
	 * @param roleId
	 * @return
	 * @return int
	 * @date： 2015-9-7 下午02:47:26
	 */
	int synchronizedMenuToUsers(String roleId);
}
