package com.wgsoft.performance.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.Doc2HtmlUtil;
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
		// 需要合计的列名
		String[] sumCol = new String[] { "indexScore" };
		// 需要在脚行设置为空的列名
		String[] setNull = new String[] { "item", "itemDetail" };
		// 需要设置“合计”字样描述的列名
		String[] setSumDesc = new String[] { "indexContent" };
		renderText(response, transferListToJsonMapForTabel(list, sumCol, new PerformanceIndex(), setNull, setSumDesc));
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
		String jsonStr = ((String[]) request.getParameterMap().get("index.datagrid"))[0];
		Map<String, List<PerformanceIndex>> jsonMap = getListFromMap(jsonStr, new PerformanceIndex());
		int rel = getPerformanceIndexManageService().saveIndex(jsonMap);
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

	/**
	 * @desc:考核资料审阅
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-1-26 上午09:52:56
	 */
	@SuppressWarnings("static-access")
	public String viewDocument() throws Exception {
		// 输入文件路径以及文件名
		File file = new File("F:\\code\\open\\咸宁市人社局平时考核系统工作计划安排_V1.0.xlsx");
		InputStream input = new FileInputStream(file);
		String fileName1 = "咸宁市人社局平时考核系统工作计划安排_V1.0.xlsx";
		String fileName = Doc2HtmlUtil.getDoc2HtmlUtilInstance().offic2Html(input, fileName1);
		renderText(response, JSONUtil.serialize(fileName));
		return null;
	}

	public IPerformanceIndexManageService getPerformanceIndexManageService() {
		return (IPerformanceIndexManageService) getService("performanceIndexManageService");
	}

	private PerformanceIndex index;

	public PerformanceIndex getIndex() {
		return index;
	}

	public void setIndex(PerformanceIndex index) {
		this.index = index;
	}

}
