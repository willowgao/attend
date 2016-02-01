package com.wgsoft.diary.service;

import java.util.List;

import com.wgsoft.diary.idao.IDiaryAnalysisDao;
import com.wgsoft.diary.iservice.IDiaryAnalysisService;
import com.wgsoft.diary.model.EchartsOfBar;

/**
 * 
 * @title： DiaryAnalysisService.java
 * @desc：
 * @author： Willowgao
 * @date： 2015-11-16 上午10:49:39
 * @version： V1.0<br>
 * @versioninfo：慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class DiaryAnalysisService implements IDiaryAnalysisService {
	
	private IDiaryAnalysisDao diaryAnalysisDao;

	/**
	 * @see com.wgsoft.diary.iservice.IDiaryAnalysisService#getQueryMaxDiss()
	 */
	public List<EchartsOfBar> getQueryMaxDiss() {
		return diaryAnalysisDao.getQueryMaxDiss();
	}

	public IDiaryAnalysisDao getDiaryAnalysisDao() {
		return diaryAnalysisDao;
	}

	public void setDiaryAnalysisDao(IDiaryAnalysisDao diaryAnalysisDao) {
		this.diaryAnalysisDao = diaryAnalysisDao;
	}
	
	

}
