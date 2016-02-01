package com.wgsoft.diary.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.diary.idao.IDiaryApproveDao;
import com.wgsoft.diary.iservice.IDiaryApproveService;
import com.wgsoft.diary.model.DiaryApprove;
import com.wgsoft.diary.model.DiaryDaily;

/**
 * @title： DiaryApproveService.java
 * @desc： 日志审核
 * @author： Willowgao
 * @date： 2015-11-11 下午08:39:27
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class DiaryApproveService implements IDiaryApproveService {

	private IDiaryApproveDao diaryApproveDao;

	/**
	 * @see com.wgsoft.diary.iservice.IDiaryApproveService#approveDiary(DiaryDaily)
	 */
	public int approveDiary(DiaryDaily diaryDaily) {
		DiaryApprove diaryApprove = new DiaryApprove();
		try {
			BeanUtils.copyProperties(diaryApprove, diaryDaily);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rel = 0;
		try {
			rel = diaryApproveDao.updateStatus(diaryDaily);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (rel > 0) {
				diaryApprove.setAppdate(new Date());
				diaryApproveDao.insert(diaryApprove);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return SysConstants.ERROR;
		}
		return SysConstants.SUCCESS;
	}

	/**
	 * @see com.wgsoft.diary.iservice.IDiaryApproveService#getDiarysForApprove(Map)
	 */
	public List<DiaryDaily> getDiarysForApprove(Map<String, Object> queryMap) {
		return diaryApproveDao.getDiarysForApprove(queryMap);
	}

	public IDiaryApproveDao getDiaryApproveDao() {
		return diaryApproveDao;
	}

	public void setDiaryApproveDao(IDiaryApproveDao diaryApproveDao) {
		this.diaryApproveDao = diaryApproveDao;
	}

}
