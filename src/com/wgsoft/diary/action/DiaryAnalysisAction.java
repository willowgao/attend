package com.wgsoft.diary.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.EchartsUtils;
import com.wgsoft.diary.iservice.IDiaryAnalysisService;
import com.wgsoft.diary.model.EchartsOfBar;

/**
 * @title： DiaryAnalysisAction.java
 * @desc： 日志统计分析
 * @author： Willowgao
 * @date： 2015-11-13 下午01:05:43
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class DiaryAnalysisAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7942445267267320307L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc: 查询评论数最多的日志分析
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-16 上午10:32:03
	 */

	public String getQueryMaxDiss() throws Exception {
		List<EchartsOfBar> list = getDiaryAnalysisService().getQueryMaxDiss();
		List<EchartsOfBar> jsonList = new ArrayList<EchartsOfBar>();
		if (list != null && list.size() > 0) {
			String[] xComments = new String[list.size()];
			String[] xData = new String[list.size()];
			int i = 0;
			EchartsOfBar jsonBar = new EchartsOfBar();
			BeanUtils.copyProperties(jsonBar, list.get(0));
			for (EchartsOfBar bar : list) {
				xComments[i] = bar.getXcomments();
				xData[i] = bar.getXdata().toString();
				i++;
			}
			jsonBar.setxAxis(xComments);
			jsonBar.setData(xData);
			jsonList.add(jsonBar);
		}
		renderText(response, EchartsUtils.getBar(jsonList).toString());
		return null;
	}

	private IDiaryAnalysisService getDiaryAnalysisService() {
		return (IDiaryAnalysisService) getService("diaryAnalysisService");
	}

}
