package com.wgsoft.performance.service;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.utils.DateUtil;
import com.wgsoft.diary.idao.IDiaryApproveDao;
import com.wgsoft.diary.model.DiaryDaily;
import com.wgsoft.performance.idao.IQueryDiarysDao;
import com.wgsoft.performance.iservice.IQueryDiarysService;

/**
 * 
 * @title： QueryDiarysService.java
 * @desc： 日志信息查询
 * @author： Willowgao
 * @date： 2016-3-9 下午04:14:50
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class QueryDiarysService implements IQueryDiarysService {

	private IQueryDiarysDao queryDiarysDao;
	
	private IDiaryApproveDao diaryApproveDao;

	/**
	 * @see com.wgsoft.performance.iservice.IQueryDiarysService#queryDiarys(Map)
	 */
	public List<DiaryDaily> queryDiarys(Map<String, Object> queryMap) {
		queryMap.put("isAll", "2");
		DiaryDaily diaryDaily = (DiaryDaily) queryMap.get("diaryDaily");
		queryMap.put("diaryid", diaryDaily.getDiaryid());
		queryMap.put("startTime", DateUtil.date2String(diaryDaily.getStarttime(), DateUtil.YMD));
		queryMap.put("endTime", DateUtil.date2String(diaryDaily.getEndtime(), DateUtil.YMD));
		queryMap.put("diarytype", diaryDaily.getDiarytype());
		queryMap.put("userid", diaryDaily.getUserid());
		queryMap.put("deptid", diaryDaily.getDeptid());
		return diaryApproveDao.getDiarysForApprove(queryMap);
	}
	
	
	public List<DiaryDaily> queryHistoryDiarys(Map<String, Object> queryMap) {
		DiaryDaily diaryDaily = (DiaryDaily) queryMap.get("diaryDaily");
		queryMap.put("diaryid", diaryDaily.getDiaryid());
		queryMap.put("startTime", DateUtil.date2String(diaryDaily.getStarttime(), DateUtil.YMD));
		queryMap.put("endTime", DateUtil.date2String(diaryDaily.getEndtime(), DateUtil.YMD));
		queryMap.put("diarytype", diaryDaily.getDiarytype());
		queryMap.put("userid", diaryDaily.getUserid());
		queryMap.put("deptid", diaryDaily.getDeptid());
		return queryDiarysDao.queryDiarys(queryMap);
	}
	

	public IQueryDiarysDao getQueryDiarysDao() {
		return queryDiarysDao;
	}

	public void setQueryDiarysDao(IQueryDiarysDao queryDiarysDao) {
		this.queryDiarysDao = queryDiarysDao;
	}

	public IDiaryApproveDao getDiaryApproveDao() {
		return diaryApproveDao;
	}

	public void setDiaryApproveDao(IDiaryApproveDao diaryApproveDao) {
		this.diaryApproveDao = diaryApproveDao;
	}

	
	

}
