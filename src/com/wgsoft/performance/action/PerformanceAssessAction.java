package com.wgsoft.performance.action;

import java.util.List;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.user.iservice.IUserService;
import com.wgsoft.user.model.UserInfo;

/**
 * @title： PerformanceIndexManageAction.java
 * @desc： 考核评分
 * @author： Willowgao
 * @date： 2016-1-25 上午09:12:02
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PerformanceAssessAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6756113475070331197L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc:查询考核指标明细信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-23 下午02:22:44
	 */
	public String queryUsers() throws Exception {
		UserInfo user = new UserInfo();
		// 查询本部门的人员
		user.setUserdeptid(getUserInfo().getUserdeptid());
		List<UserInfo> list = getUserService().getUserInfoByUserInfo(user);
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			UserInfo me = list.get(i);
			if (me.getUserid().equals(getUserInfo().getUserid())) {
				// 不包括自己
				j = i;
			}
			i++;
		}
		// 移除自己
		list.remove(j);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	public IUserService getUserService() {
		return (IUserService) getService("userService");
	}

}
