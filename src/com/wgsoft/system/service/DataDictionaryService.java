package com.wgsoft.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.wgsoft.attendance.clock.model.LeaveAppSet;
import com.wgsoft.system.idao.IDataDictionaryDao;
import com.wgsoft.system.iservice.IDataDictionaryService;
import com.wgsoft.system.model.DataDictionary;
import com.wgsoft.system.model.Deptment;

/**
 * @title： DataDictionaryService.java
 * @desc： 数据字典服务类
 * @author： Willowgao
 * @date： 2015-10-27 下午01:29:46
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class DataDictionaryService implements IDataDictionaryService {

	private static Log log = LogFactory.getLog(IDataDictionaryService.class);

	private IDataDictionaryDao dataDictionaryDao;

	/**
	 * 内存变量
	 */
	private static Map<String, Map<String, String>> dataDictMap = new HashMap<String, Map<String, String>>();
	/**
	 * 数据字典描述
	 */
	private static Map<String, List<DataDictionary>> dataDictMapForDesc = new HashMap<String, List<DataDictionary>>();
	/**
	 * 请假审核等级配置表数据
	 */
	public static Map<String, String> approverSet = new HashMap<String, String>();

	private final static String DESC = "DESC";

	/**
	 * 初始化数据字典
	 */
	public void initDataDictionary() {
		// 清空所有map值
		dataDictMap.clear();
		dataDictMapForDesc.clear();
		// 初始化请假审核等级配置表数据
		initLeavesAppSet();
		// 重新加载数据
		List<DataDictionary> dataDictionarys = dataDictionaryDao.getAllDataDictionary();
		String pkDataDict = null;
		Map<String, String> chMap = new HashMap<String, String>();
		List<DataDictionary> chList = new ArrayList<DataDictionary>();
		for (DataDictionary dataDict : dataDictionarys) {
			if (pkDataDict != null && !pkDataDict.equals(dataDict.getDatatype())) {
				chMap = new HashMap<String, String>();
				chList = new ArrayList<DataDictionary>();
			}
			chList.add(dataDict);
			chMap.put(dataDict.getDataid(), dataDict.getDatacomment());
			dataDictMap.put(dataDict.getDatatype(), chMap);
			dataDictMapForDesc.put(dataDict.getDatatype() + DESC, chList);
			pkDataDict = dataDict.getDatatype();
		}
		if (log.isInfoEnabled()) {
			log.info("****************************DataDictionary Load Success******************************");
		}
	}

	/**
	 * 请假审核等级配置表
	 * 
	 * @desc:
	 * @return void
	 * @date： 2015-11-2 下午02:30:40
	 */
	public void initLeavesAppSet() {
		approverSet.clear();
		List<LeaveAppSet> leaveAppSets = dataDictionaryDao.getAllLeaveAppSet();
		for (LeaveAppSet set : leaveAppSets) {
			approverSet.put(set.getLeavetype(), set.getApprover());
		}
	}

	public Map<String, String> getDataDictionarysByKey(String key) {
		return dataDictMap.get(key);
	}

	public String getJsonStrByKey(String key) {
		String jsonStr = null;
		try {
			jsonStr = JSONUtil.serialize(dataDictMap.get(key));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	public String getListJsonStrByKey(String key) {
		String jsonStr = null;
		List<DataDictionary> dataDictionarys = dataDictMapForDesc.get(key + DESC);
		List<Map<String, String>> descList = new ArrayList<Map<String, String>>();
		try {
			for (DataDictionary data : dataDictionarys) {
				Map<String, String> dataMap = new HashMap<String, String>();
				dataMap.put("id", data.getDataid());
				dataMap.put("text", data.getDatacomment());
				descList.add(dataMap);
			}

			jsonStr = JSONUtil.serialize(descList);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	public String getDeptByOrg(String org) {
		String jsonStr = null;
		List<Deptment> depts = dataDictionaryDao.getDeptByOrg(org);
		List<Map<String, String>> descList = new ArrayList<Map<String, String>>();
		try {
			for (Deptment dept : depts) {
				Map<String, String> dataMap = new HashMap<String, String>();
				dataMap.put("id", dept.getDeptid());
				dataMap.put("text", dept.getDeptname());
				descList.add(dataMap);
			}

			jsonStr = JSONUtil.serialize(descList);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	public String getDeptByUser(String userid) {
		String jsonStr = null;
		List<Deptment> depts = dataDictionaryDao.getDeptByUser(userid);
		List<Map<String, String>> descList = new ArrayList<Map<String, String>>();
		try {
			for (Deptment dept : depts) {
				Map<String, String> dataMap = new HashMap<String, String>();
				dataMap.put("id", dept.getDeptid());
				dataMap.put("text", dept.getDeptname());
				descList.add(dataMap);
			}
			jsonStr = JSONUtil.serialize(descList);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	public IDataDictionaryDao getDataDictionaryDao() {
		return dataDictionaryDao;
	}

	public void setDataDictionaryDao(IDataDictionaryDao dataDictionaryDao) {
		this.dataDictionaryDao = dataDictionaryDao;
	}

}
