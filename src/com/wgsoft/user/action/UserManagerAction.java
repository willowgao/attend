package com.wgsoft.user.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.SecurityUtils;
import com.wgsoft.system.model.RoleInfo;
import com.wgsoft.user.iservice.IUserService;
import com.wgsoft.user.model.BacklogWork;
import com.wgsoft.user.model.UserInfo;

/**
 * 
 * @title： UserManagerAction.java
 * @desc： 用户管理
 * @author： Willowgao
 * @date： 2015-9-7 下午03:49:34
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class UserManagerAction extends BaseAction {

	private String userName;
	private String userPwd;
	private String userCode;
	private String higherid;
	private String defaultstyle;
	private String usersex;
	private String oldPwd;
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getUserName() {
		return userName;
	}

	public String getHigherid() {
		return higherid;
	}

	public void setHigherid(String higherid) {
		this.higherid = higherid;
	}

	public String getDefaultstyle() {
		return defaultstyle;
	}

	public void setDefaultstyle(String defaultstyle) {
		this.defaultstyle = defaultstyle;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUsersex() {
		return usersex;
	}

	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1461515996793463430L;
	/**
	 * 用户信息
	 */
	private IUserService userService = (IUserService) getService("userService");

	/**
	 * 检查登录、并将用户信息放入SESSION中
	 */
	public String execute() throws Exception {
		String userName = request.getParameter("username");
		String pwd = request.getParameter("password");
		String ipPort = request.getRemoteAddr() + ":" + request.getRemotePort();
		String host = request.getRemoteHost();
		
		List<UserInfo> list = userService.getUserInfoByUName(userName, pwd, host, ipPort);
		if (list.size() > 0) {
			renderText(response, "1");
			// 设置用户信息
			setUserInfo(list.get(0));
		}
		return null;
	}

	/**
	 * @desc:系统登录
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-7 下午03:49:16
	 */
	public String show() throws Exception {
		if (getUserInfo() == null) {
			return LOGIN;
		} else {
			request.setAttribute("username", getUserInfo().getUsername());
			request.setAttribute("sysstyle", getUserInfo().getDefaultstyle());
		}
		return SUCCESS;
	}

	/**
	 * @desc:页面初始化
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-7 下午03:49:08
	 */
	public String saveOrUpdateUser() throws Exception {
		return "addUser";
	}

	/**
	 * @desc:页面初始化
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-7 下午03:49:08
	 */
	public String modifyUser() throws Exception {
		return "modifyUser";
	}

	/**
	 * @desc:检查用户名是否已经存在
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-7 下午03:48:58
	 */
	public String checkUserCode() throws Exception {
		String code = request.getParameter("userCode");
		if (userService.checkUserCode(code)) {
			renderText(response, "1");
		}
		return null;
	}

	/**
	 * @desc:保存用户信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-7 下午03:48:48
	 */
	public String saveOrUpdate() throws Exception {
		UserInfo user = new UserInfo();
		user.setUsercode(userCode);
		user.setHigherid(higherid);
		user.setUsersex(usersex);
		user.setDefaultstyle(defaultstyle);
		user.setRoleid(request.getParameter("roleId"));
		if (!userPwd.equals(oldPwd)) {
			user.setUserpwd(SecurityUtils.setPwdForDb(userPwd));
		}
		user.setUsername(userName);
		user.setUserorg(request.getParameter("orgId"));
		user.setUserid(request.getParameter("userId"));
		user.setUserdeptid(request.getParameter("deptId"));
		userService.saveUser(user);
		renderText(response, "1");
		return null;
	}

	/**
	 * @desc: 获取角色信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-10 下午12:49:31
	 */
	public String getRoles() throws Exception {
		UserInfo user = new UserInfo();
		user.setUserorg(request.getParameter("orgId"));
		user.setUserdeptid(request.getParameter("deptId"));
		String roleId = request.getParameter("roleId");
		List<RoleInfo> roles = userService.getRoleInfoByOrg(user);
		List<Map<String, String>> roleList = new ArrayList<Map<String, String>>();
		for (RoleInfo role : roles) {
			Map<String, String> roleMap = new HashMap<String, String>();
			if (role.getRoleid().equals(roleId)) {
				roleMap.put("selected", "true");
			}
			roleMap.put("id", role.getRoleid());
			roleMap.put("text", role.getRolename());
			roleList.add(roleMap);
		}
		renderText(response, JSONUtil.serialize(roleList));
		return null;
	}

	/**
	 * @desc:
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-16 下午01:10:05
	 */
	public String getUser() throws Exception {
		renderText(response, JSONUtil.serialize(getUserInfo()));
		return null;
	}

	/**
	 * @desc:
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-16 下午03:07:28
	 */
	public String updateUser() throws Exception {
		UserInfo user = new UserInfo();
		user.setDefaultstyle(defaultstyle);
		if (!userPwd.equals(getUserInfo().getUserpwd())) {
			user.setUserpwd(SecurityUtils.setPwdForDb(userPwd));
		}
		user.setUserid(request.getParameter("userId"));
		userService.updateUserBySql(user);
		renderText(response, "1");
		return null;
	}

	/**
	 * @desc:查询用户信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-7 下午03:48:31
	 */
	public String queryUserListByOrg() throws Exception {
		String org = request.getParameter("orgId");
		String userid = request.getParameter("userid");
		String deptId = request.getParameter("deptId");
		UserInfo user = new UserInfo();
		user.setUserorg(org);
		user.setUserid(userid);
		user.setUserdeptid(deptId);
		List<UserInfo> list = userService.getUserInfoByUserInfo(user);
		this.renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:删除用户
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-7 下午03:48:24
	 */
	public String deleteUser() throws Exception {
		String userid = request.getParameter("userid");
		UserInfo user = new UserInfo();
		user.setUserid(userid);
		userService.deleteUser(user);
		this.renderText(response, "1");
		return null;
	}

	/**
	 * @desc:查询工作待办记录
	 * @return
	 * @return int
	 * @date： 2015-12-8 下午02:08:25
	 */
	public int getWorkCounts() {
		List<BacklogWork> list = userService.getWorks(getUserInfo());
		int rel = 0;
		if (list != null && list.size() > 0) {
			rel = list.size();
		}
		return rel;
	}

	/**
	 * @desc:查询待办记录
	 * @return 
	 * @return String
	 * @date： 2015-12-8 下午02:08:43
	 */
	public String getWorks() {
		List<BacklogWork> list = userService.getWorks(getUserInfo());
		this.renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

}
