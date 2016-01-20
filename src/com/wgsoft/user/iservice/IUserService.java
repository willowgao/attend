package com.wgsoft.user.iservice;

import java.util.List;

import com.wgsoft.system.model.RoleInfo;
import com.wgsoft.user.model.BacklogWork;
import com.wgsoft.user.model.UserInfo;

public interface IUserService {

	/**
	 * @desc:获取用户信息
	 * @return
	 * @return List<UserInfo>
	 * @date�� 2015-9-6 ����01:56:42
	 */
	List<UserInfo> getUserInfo();

	/**
	 * @desc: 通过登录用户名密码，检查登录信息
	 * @param userName
	 *            用户名
	 * @param pwd
	 *            密码
	 * @param host
	 *            机器名
	 * @param ipPort
	 *            电脑名
	 * @return
	 * @return List<UserInfo>
	 * @date 2015-9-6 01:56:51
	 */
	List<UserInfo> getUserInfoByUName(String userName, String pwd, String host, String ipPort);

	/**
	 * @desc: 用户对象，查询用户信息
	 * @param user
	 * @return
	 * @return List<UserInfo>
	 * @date�� 2015-9-7 ����09:00:30
	 */
	List<UserInfo> getUserInfoByUserInfo(UserInfo user);

	/**
	 * @desc: 检查用户名是否已经被占用
	 * @param userCode
	 * @return
	 * @return boolean
	 * @date�� 2015-9-7 ����08:32:18
	 */
	boolean checkUserCode(String userCode);

	/**
	 * @desc: 保存用户信息
	 * @param user
	 * @return void
	 * @date�� 2015-9-6 ����01:57:40
	 */
	void saveUser(UserInfo user);

	/**
	 * @desc: 根据sql更新
	 * @param user
	 * @return void
	 * @date： 2015-11-16 下午01:03:55
	 */
	void updateUserBySql(UserInfo user);

	void updateUser(UserInfo user);

	void deleteUser(UserInfo user);

	List<RoleInfo> getRoleInfoByOrg(UserInfo user);

	/**
	 * 通过角色类型\用户信息查询 查询可以用于核审的数据
	 * 
	 * @param roleType
	 *            角色类型
	 * @param user
	 *            用户信息
	 * @return
	 */
	String getUserForApprove(String roleType, UserInfo user);

	/**
	 * @desc::查询同部门人员设置为直接上级
	 * @param user
	 * @return
	 * @return String
	 * @date： 2015-11-11 上午10:37:53
	 */
	String getUserForHigher(UserInfo user);

	/**
	 * @desc:查询待办事项
	 * @param user
	 * @return
	 * @return List<BacklogWork>
	 * @date： 2015-11-26 下午04:11:11
	 */
	List<BacklogWork> getWorks(UserInfo user);
}
