package com.wgsoft.performance.action;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.performance.iservice.IPositionStatementService;
import com.wgsoft.performance.model.PositionStatement;

/**
 * @title： PositionStatementApproveAction.java
 * @desc：岗位职责申报
 * @author： Willowgao
 * @date： 2015-12-2 上午10:41:07
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PositionStatementApproveAction extends BaseAction {

	private static final String FORMNAME = "positionForm";
	/**
	 * 
	 */
	private static final long serialVersionUID = -2943909438323072801L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void initForm() throws Exception {
		if (position == null) {
			position = new PositionStatement();
		}
		position.setApprover(getUserInfo().getUserid());
		position.setDeptid(getUserInfo().getUserdeptid());
		position.setRoleid(getUserInfo().getRoleid());
		position.setStarttime(new Date());
		renderText(response, transferVoToForm(FORMNAME, position, PositionStatement.class));
	}

	/**
	 * @desc:查询岗位职责信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-12-2 上午10:56:48
	 */
	public String queryPositionStatements() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("position", position);
		List<PositionStatement> list = getPositionStatementService().queryPositionStatements(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * 
	 * @desc: 保存岗位职责申报
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-12-2 上午10:51:53
	 */
	public String savePositionStatement() throws Exception {

		String jsonStr = ((String[]) request.getParameterMap().get("position.datagrid"))[0];
		Map<String, List<PositionStatement>> jsonMap = getListFromMap(jsonStr, new PositionStatement());
		jsonMap.put("formData", Arrays.asList(position));
		int rel = getPositionStatementService().savePositionStatement(jsonMap);
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	/**
	 * 
	 * @desc:保存岗位职责审核
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-12-2 上午10:51:53
	 */
	public String approvePositionStatement() throws Exception {
		return null;
	}

	private PositionStatement position;

	public PositionStatement getPosition() {
		return position;
	}

	public void setPosition(PositionStatement position) {
		this.position = position;
	}

	private IPositionStatementService getPositionStatementService() {
		return (IPositionStatementService) getService("positionStatementService");
	}
}
