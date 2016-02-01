package com.wgsoft.system.action;

import java.util.List;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.model.Organization;
import com.wgsoft.system.iservice.IOrgService;
import com.wgsoft.system.model.Deptment;

/**
 * 
 * @title： OrganizationTreeAction.java
 * @desc： 组织信息管理
 * @author： Willowgao
 * @date： 2015-9-7 下午04:58:35
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class OrganizationTreeAction extends BaseAction {

	private static final long serialVersionUID = 7875458494521281603L;

	private IOrgService orgService = (IOrgService) getService("orgService");

	// 机构名称
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc:查询组织树
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-9 下午12:42:27
	 */
	public String queryTree() throws Exception {
		List<Organization> treeList = orgService.getOrgs(getUserInfo().getUserorg());
		renderText(response, JSONUtil.serialize(treeList));
		return null;
	}

	/**
	 * @desc:
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-9 下午12:52:14
	 */
	public String gotoOrganziationManage() throws Exception {
		return "organizationManager";
	}

	/**
	 * @desc:
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-9-9 下午12:42:27
	 */
	public String saveOrUpdateOrganziation() throws Exception {
		String orgType = request.getParameter("orgType");
		String orgId = request.getParameter("orgId");
		String deptId = request.getParameter("deptId");
		Deptment dept = new Deptment();
		dept.setDeptid(deptId);
		dept.setDeptname(deptName);
		dept.setOrgid(orgId);
		int rel = orgService.saveOrUpdateDepement(dept, orgType, deptId);
		if (rel == 0) {
			renderText(response, SUCCESS);
		}
		return null;
	}

	public String getOrgAndDepts() throws Exception {
		String orgId = request.getParameter("orgId");
		String orgType = request.getParameter("orgType");
		List<Deptment> list = orgService.queryOrgsForList(orgId, orgType);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}
}
