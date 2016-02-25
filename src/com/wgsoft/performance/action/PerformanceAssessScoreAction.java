package com.wgsoft.performance.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.BeanUtil;
import com.wgsoft.common.utils.FileUtil;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.performance.iservice.IPerformanceAssessScoreService;
import com.wgsoft.performance.model.PerformanceAssess;
import com.wgsoft.performance.model.PerformanceAssessScore;

/**
 * @title： PerformanceIndexManageAction.java
 * @desc： 考核评分汇总
 * @author： Willowgao
 * @date： 2016-1-25 上午09:12:02
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PerformanceAssessScoreAction extends BaseAction {

	/** 导出文件的列头和属性名称 */
	private static final String[] expColumnNames = new String[] { "考核年度", "部门", "被考核人", "开始日期", "终止日期", "领导打分", "同级打分",
			"考勤扣分", "最后得分" };
	private static final String[] expfieldNames = new String[] { "assessyear", "deptid", "userid", "starttime",
			"endtime", "higherscore", "peerscore", "reductionscore", "finalscore" };
	private static final String fileName = "/考核评分汇总表.xls";
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
	public String queryScore() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Map<String, Object> requestMap = request.getParameterMap();
		if (RunUtil.isNotEmpty(((String[]) requestMap.get("assess.starttime"))[0])) {
			queryMap.put("starttime", ((String[]) requestMap.get("assess.starttime"))[0]);
		}
		if (RunUtil.isNotEmpty(((String[]) requestMap.get("assess.endtime"))[0])) {
			queryMap.put("endtime", ((String[]) requestMap.get("assess.endtime"))[0]);
		}
		queryMap.put("user", getUserInfo());
		List<PerformanceAssessScore> list = getPerformanceAssessScoreService().queryScores(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:查询考核指标明细信息
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2015-11-23 下午02:22:44
	 */
	public String queryDetail() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();

		Map<String, Object> requestMap = request.getParameterMap();
		if (RunUtil.isNotEmpty(((String[]) requestMap.get("starttime"))[0])) {
			queryMap.put("starttime", ((String[]) requestMap.get("starttime"))[0]);
		}
		if (RunUtil.isNotEmpty(((String[]) requestMap.get("endtime"))[0])) {
			queryMap.put("endtime", ((String[]) requestMap.get("endtime"))[0]);
		}
		queryMap.put("userid", ((String[]) requestMap.get("userid"))[0]);
		List<PerformanceAssessScore> list = getPerformanceAssessScoreService().queryScoresDetail(queryMap);
		renderText(response, transferListToJsonMapForTabel(list));
		return null;
	}

	/**
	 * @desc:保存考核打分
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-1-27 下午02:09:46
	 */
	@SuppressWarnings("unchecked")
	public String save() throws Exception {
		Map<String, Object> saveMap = new HashMap<String, Object>();
		// 用户信息
		saveMap.put("user", getUserInfo());
		// 列表信息
		String jsonStr = ((String[]) request.getParameterMap().get("assess.datagrid"))[0];
		List<PerformanceAssessScore> assessScore = (List<PerformanceAssessScore>) JSONUtil.deserialize(jsonStr);
		saveMap.put("assessScore", assessScore);
		int rel = getPerformanceAssessScoreService().saveAssessScore(saveMap);
		renderText(response, JSONUtil.serialize(rel));
		return null;
	}

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
			//写文件
			workbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		os.close();
		renderText(response, JSONUtil.serialize(fileName));
		return null;
	}

	private PerformanceAssess assess;

	public PerformanceAssess getAssess() {
		return assess;
	}

	public void setAssess(PerformanceAssess assess) {
		this.assess = assess;
	}

	public IPerformanceAssessScoreService getPerformanceAssessScoreService() {
		return (IPerformanceAssessScoreService) getService("performanceAssessScoreService");
	}

}
