package com.wgsoft.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.wgsoft.attendance.diary.model.UserLoginInfo;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.system.model.Deptment;
import com.wgsoft.system.model.RoleInfo;
import com.wgsoft.user.idao.IUserDao;
import com.wgsoft.user.iservice.IUserService;
import com.wgsoft.user.model.BacklogWork;
import com.wgsoft.user.model.UserInfo;

/**
 * 
 * @title： UserService.java
 * @desc：用户管理信息服务Service
 * @author： Willowgao
 * @date： 2015-9-8 下午01:53:40
 * @version： V1.0<br>
 * @versioninfo：慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class UserService implements IUserService {

	private IUserDao userDao;

	/**
	 * 查询所有用户
	 */
	public List<UserInfo> getUserInfo() {
		List<UserInfo> users = userDao.getUsers();
		return users;
	}

	/**
	 * 查询用户信息，并进行记录
	 */
	public List<UserInfo> getUserInfoByUName(String userName, String pwd, String host, String ipPort) {
		List<UserInfo> returnList = userDao.getUserInfoByUName(userName, pwd);
		if (returnList != null && returnList.size() > 0) {
			UserInfo user = returnList.get(0);
			// 查询人员同组织下的部门
			List<Map<String, String>> deptDatas = new ArrayList<Map<String, String>>();
			List<Deptment> depts = userDao.getUserDeptments(user);
			for (Deptment dept : depts) {

				Map<String, String> deptMap = new HashMap<String, String>();
				deptMap.put("id", dept.getDeptid());
				deptMap.put("text", dept.getDeptname());
				deptDatas.add(deptMap);
			}
			// 查询人员同部门下的人员
			List<Map<String, String>> userDatas = new ArrayList<Map<String, String>>();
			UserInfo queryUser = new UserInfo();
			queryUser.setUserdeptid(user.getUserdeptid());
			List<UserInfo> users = userDao.getUserInfoByUserInfo(queryUser);
			for (UserInfo userSingle : users) {
				Map<String, String> userMap = new HashMap<String, String>();
				userMap.put("id", userSingle.getUserid());
				userMap.put("text", userSingle.getUsername());
				userDatas.add(userMap);
			}

			user.setDepts(deptDatas);
			user.setUsers(userDatas);
			insertLoginInfo(user, host, ipPort);
		}
		return returnList;
	}

	/**
	 * @desc:保存用户信息
	 * @param user
	 * @param host
	 * @param ipPort
	 * @return void
	 * @date： 2015-9-8 下午01:57:50
	 */
	public void insertLoginInfo(UserInfo user, String host, String ipPort) {
		UserLoginInfo userLoginInfo = new UserLoginInfo();
		userLoginInfo.setLoginid(RunUtil.getUUID());
		userLoginInfo.setUsername(user.getUsername());
		userLoginInfo.setLogincode(user.getUsercode());
		userLoginInfo.setLogintime(new Date());
		userLoginInfo.setLogintmachine(host);
		userLoginInfo.setLoginip(ipPort);
		userDao.insert(userLoginInfo);
	}

	/**
	 * 删除用户
	 */
	public void deleteUser(UserInfo user) {
		userDao.delete(user);
	}

	/**
	 * 保存或修改用户
	 */
	public void saveUser(UserInfo user) {
		String roleId = user.getRoleid();

		user.setRoleid(null);
		List<UserInfo> users = userDao.getUserInfoByUserInfo(user);
		user.setRoleid(roleId);
		try {
			if (users != null && users.size() > 0) {
				user.setUserid(users.get(0).getUserid());
				if (RunUtil.isEmpty(user.getUserpwd())) {
					user.setUserpwd(users.get(0).getUserpwd());
				}
				updateUser(user);
			} else {
				userDao.insert(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<RoleInfo> getRoleInfoByOrg(UserInfo user) {
		return userDao.getRoleInfoByOrg(user);
	}

	/**
	 * 修改用户
	 */
	public void updateUser(UserInfo user) {
		userDao.update(user);
	}

	/**
	 * 检查用户编码是否已经存在 未判断组织
	 */
	public boolean checkUserCode(String userCode) {
		return userDao.checkUserCode(userCode);
	}

	public List<UserInfo> getUserInfoByUserInfo(UserInfo user) {
		return userDao.getUserInfoByUserInfo(user);
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 *得到审核人员
	 */
	public String getUserForApprove(String roleType, UserInfo user) {
		List<UserInfo> users = userDao.getUserForApprove(roleType, user);
		List<Map<String, String>> jsu = new ArrayList<Map<String, String>>();
		for (UserInfo u : users) {
			Map<String, String> userMap = new HashMap<String, String>();
			userMap.put("id", u.getUserid());
			userMap.put("text", u.getUsername());
			jsu.add(userMap);
		}
		String jsonStr = "";
		try {
			jsonStr = JSONUtil.serialize(jsu);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 得到上级人员
	 */
	public String getUserForHigher(UserInfo user) {
		List<UserInfo> users = userDao.getUserForHigher(user);
		List<Map<String, String>> jsu = new ArrayList<Map<String, String>>();
		for (UserInfo u : users) {
			Map<String, String> userMap = new HashMap<String, String>();
			userMap.put("id", u.getUserid());
			userMap.put("text", u.getUsername());
			jsu.add(userMap);
		}
		String jsonStr = "";
		try {
			jsonStr = JSONUtil.serialize(jsu);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 更新用户信息
	 */
	public void updateUserBySql(UserInfo user) {
		userDao.updateUserBySql(user);
	}

	/**
	 * 得到工作待办信息
	 */
	public List<BacklogWork> getWorks(UserInfo user) {
		return userDao.getWorks(user);
	}

}
