package com.wgsoft.system.idao;

import java.util.List;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.system.model.RoleInfo;
import com.wgsoft.system.model.RoleMenu;

public interface ISystemAuthorityManageDao extends IBaseDao{

	List<RoleInfo> queryRoleByOrgOrDeptId(String id, String type);

 
	/**
	 * @desc:删除角色信息,同时需要删除用户下的菜单信息
	 * @param roleId
	 * @return
	 * @return int
	 * @date： 2015-9-7 下午02:45:56
	 */
	int deleteRole(String roleId);
	

	/**
	 * @desc:删除角色信息,同时需要删除角色下的菜单信息
	 * @param roleId
	 * @return
	 * @return int
	 * @date： 2015-9-7 下午02:45:56
	 */
	int deleteRoleMenu(String roleId);

	/**
	 * @desc: 将角色菜单信息同步到用户
	 * @param roleId 
	 * @return
	 * @return int
	 * @date： 2015-9-7 下午02:47:26
	 */
	int synchronizedMenuToUsers(String roleId);
	

	/**
	 * @desc:通过角色id查询菜单数据
	 * @return
	 * @return List<Organization>
	 * @date： 2015-9-7 下午02:45:00
	 */
	List<RoleMenu> queryMenusByRoleId(String roleId);
}
