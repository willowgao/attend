package com.wgsoft.user.idao;

import java.util.List;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.system.model.Deptment;
import com.wgsoft.system.model.RoleInfo;
import com.wgsoft.user.model.BacklogWork;
import com.wgsoft.user.model.UserInfo;

/**
 * 
 * @title： IUserDao.java
 * @desc： 用户管理
 * @author： Willowgao
 * @date： 2015-9-8 下午05:35:21
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IUserDao extends IBaseDao {

	List<UserInfo> getUsers();

	/**
	 * @desc: 查询用户秘属组织的部门
	 * @param user
	 * @return
	 * @return List<Deptment>
	 * @date： 2015-12-5 上午11:14:29
	 */
	List<Deptment> getUserDeptments(UserInfo user);

	/**
	 * @desc:用户名、密码校验
	 * @param userName
	 * @param pwd
	 * @return
	 * @return List<UserInfo>
	 * @date： 2015-9-8 下午05:35:45
	 */
	List<UserInfo> getUserInfoByUName(String userName, String pwd);

	/**
	 * @desc: 用户对象查询
	 * @param info
	 *            UserInfo
	 * @return
	 * @return List<UserInfo>
	 * @date： 2015-9-8 下午05:35:32
	 */
	List<UserInfo> getUserInfoByUserInfo(UserInfo info);

	/**
	 * @desc:检查用户编码是否存在重复
	 * @param userCode
	 * @return
	 * @return boolean
	 * @date： 2015-9-8 下午05:35:56
	 */
	boolean checkUserCode(String userCode);

	/**
	 * @desc: 用户对象查询角色
	 * @param user
	 * @return
	 * @return List<RoleInfo>
	 * @date： 2015-9-8 下午05:36:30
	 */
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
	List<UserInfo> getUserForApprove(String roleType, UserInfo user);

	/**
	 * @desc:查询同部门人员设置为直接上级
	 * @param user
	 * @return
	 * @return List<UserInfo>
	 * @date： 2015-11-11 上午10:36:57
	 */
	List<UserInfo> getUserForHigher(UserInfo user);

	/**
	 * @desc: 根据sql更新
	 * @param user
	 * @return void
	 * @date： 2015-11-16 下午01:03:55
	 */
	void updateUserBySql(UserInfo user);

	/**
	 * @desc:查询待办事项
	 * @param user
	 * @return
	 * @return List<BacklogWork>
	 * @date： 2015-11-26 下午04:11:11
	 */
	List<BacklogWork> getWorks(UserInfo user);
}
