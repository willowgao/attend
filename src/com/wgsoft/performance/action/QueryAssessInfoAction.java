package com.wgsoft.performance.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.BeanUtil;
import com.wgsoft.common.utils.EchartsUtils;
import com.wgsoft.common.utils.FileUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.diary.model.EchartsOfBar;
import com.wgsoft.performance.iservice.IQueryAssessInfoService;
import com.wgsoft.performance.model.PerformanceAssessScore;

/**
 * 
 * @title： QueryAttendanceInfoAction.java
 * @desc：考核情况查询
 * @author： Willowgao
 * @date： 2016-2-2 上午09:23:08
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryAssessInfoAction extends BaseAction {

	/** 导出文件的列头和属性名称 */
	private static final String[] expColumnNames = new String[] { "考核年度", "部门", "被考核人", "开始日期", "终止日期", "领导打分", "同级打分",
			"考勤扣分", "最后得分" };
	private static final String[] expfieldNames = new String[] { "assessyear", "deptid", "userid", "starttime",
			"endtime", "higherscore", "peerscore", "reductionscore", "finalscore" };
	private static final String fileName = "/考核评分汇总表.xls";
	/**
	 * 
	 */
	private static final long serialVersionUID = -1324230464685885561L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc:查询出勤情况
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-2-2 上午09:24:58
	 */
	public String queryAssess() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Map<String, Object> requestMap = request.getParameterMap();
		if (RunUtil.isNotEmpty(((String[]) requestMap.get("assess.starttime"))[0])) {
			queryMap.put("starttime", ((String[]) requestMap.get("assess.starttime"))[0]);
		}
		if (RunUtil.isNotEmpty(((String[]) requestMap.get("assess.endtime"))[0])) {
			queryMap.put("endtime", ((String[]) requestMap.get("assess.endtime"))[0]);
		}
		if (RunUtil.isNotEmpty(((String[]) requestMap.get("assess.deptid"))[0])) {
			queryMap.put("deptid", ((String[]) requestMap.get("assess.deptid"))[0]);
		}
		queryMap.put("user", getUserInfo());
		List<PerformanceAssessScore> list = getQueryAssessInfoService().queryAssessForList(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-2-25 下午03:12:39
	 */
	public String exportExcel() throws Exception {
		// 列表信息
		String jsonStr = ((String[]) request.getParameterMap().get("assess.datagrid"))[0];
		List<Map<String, Object>> scoreMaps = (List<Map<String, Object>>) JSONUtil.deserialize(jsonStr);
		// 得到的行信息，转成javabena list
		List<PerformanceAssessScore> scores = new ArrayList<PerformanceAssessScore>();
		for (Map<String, Object> score : scoreMaps) {
			PerformanceAssessScore assessScore = new PerformanceAssessScore();
			try {
				BeanUtil.applyIf(assessScore, score);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scores.add(assessScore);
		}
		// 初始化文件生成的路径
		File file = new File(SysConstants.getPath(SysConstants.EXPORT_PATH) + fileName);
		OutputStream os = new FileOutputStream(file);
		try {
			// 组装excel
			HSSFWorkbook workbook = FileUtil.bean2Excel(expColumnNames, expfieldNames, scores,
					PerformanceAssessScore.class, null);
			// 写文件
			workbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		os.close();
		renderText(response, JSONUtil.serialize(fileName));
		return null;
	}

	/**
	 * 页面初始化
	 */
	public String showCharts() throws Exception {
		return "showCharts";
	}
	
	/**
	 * @desc: 考核得分排名
	 * @return
	 * @return String
	 * @date： 2016-2-2 下午01:37:43
	 */
	public String queryOrgRanking() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		List<EchartsOfBar> list = getQueryAssessInfoService().queryOrgRanking(queryMap);
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
 

	private IQueryAssessInfoService getQueryAssessInfoService() {
		return (IQueryAssessInfoService) getService("queryAssessInfoService");
	}

	private PerformanceAssessScore assess;

	public PerformanceAssessScore getAssess() {
		return assess;
	}

	public void setAssess(PerformanceAssessScore assess) {
		this.assess = assess;
	}

}
