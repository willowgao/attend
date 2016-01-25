package com.wgsoft.performance.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.performance.iservice.IPerformanceIndexManageService;
import com.wgsoft.performance.model.PerformanceIndex;

/**
 * @title： PerformanceIndexManageAction.java
 * @desc： 考核指标管理
 * @author： Willowgao
 * @date： 2016-1-25 上午09:12:02
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PerformanceIndexManageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	public String queryIndex() throws Exception {
		List<PerformanceIndex> list = getPerformanceIndexManageService().queryIndex();
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-1-25 上午11:50:21
	 */
	public String save() throws Exception {
		String jsonStr = request.getParameter(DATAGRID_JSON_DATA);
		Map<String, List<PerformanceIndex>> jsonMap = getListFromMap(jsonStr, new PerformanceIndex());
		int rel = getPerformanceIndexManageService().saveIndex(jsonMap);
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}
 

	public IPerformanceIndexManageService getPerformanceIndexManageService() {
		return (IPerformanceIndexManageService) getService("performanceIndexManageService");
	}
	
	 
}