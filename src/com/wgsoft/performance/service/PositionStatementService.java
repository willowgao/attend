package com.wgsoft.performance.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.performance.idao.IPositionStatementDao;
import com.wgsoft.performance.iservice.IPositionStatementService;
import com.wgsoft.performance.model.PositionStatement;
import com.wgsoft.performance.model.PositionStatementDetail;

/**
 * 
 * @title： PositionStatementService.java
 * @desc： 岗位职责申报管理
 * @author： Willowgao
 * @date： 2015-12-2 上午11:10:11
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PositionStatementService implements IPositionStatementService {

	private IPositionStatementDao positionStatementDao;

	/**
	 * @see com.wgsoft.performance.iservice.IPositionStatementService#approvePositionStatement(PositionStatement)
	 */
	public int approvePositionStatement(PositionStatement positionStatement) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see com.wgsoft.performance.iservice.IPositionStatementService#queryPositionStatements(Map)
	 */
	public List<PositionStatement> queryPositionStatements(Map<String, Object> queryMap) {
		return positionStatementDao.queryPositionStatements(queryMap);
	}

	/**
	 * @see com.wgsoft.performance.iservice.IPositionStatementService#savePositionStatement(Map)
	 */
	public int savePositionStatement(Map<String, List<PositionStatement>> jsonMap) {
		// 新增list
		List<PositionStatement> insertList = jsonMap.get(SysConstants.DataGridData.DATAGRID_LIST_INSERTLIST);
		// 删除list
		List<PositionStatement> deleteList = jsonMap.get(SysConstants.DataGridData.DATAGRID_LIST_DELETELIST);
		// 更新list
		List<PositionStatement> updataList = jsonMap.get(SysConstants.DataGridData.DATAGRID_LIST_UPDATELIST);

		List<PositionStatement> formData = jsonMap.get("formData");
		int i = 0;
		if (updataList != null && updataList.size() > 0) {
			List<String> ids = new ArrayList<String>();
			List<PositionStatement> psList = new ArrayList<PositionStatement>();
			// 将需要删除的id增加到列表
			for (PositionStatement ps : updataList) {
				if (!ids.contains(ps.getPsid())) {
					ids.add(ps.getPsid());
					psList.add(ps);
				}
			}
			try {
				positionStatementDao.updateBatch(psList);
			} catch (Exception e) {
				i++;
			}

			try {
				positionStatementDao.updateBatch(transferList(updataList, null));
			} catch (Exception e) {
				i++;
			}
		}

		if (deleteList != null && deleteList.size() > 0) {
			List<String> ids = new ArrayList<String>();
			List<PositionStatement> psList = new ArrayList<PositionStatement>();
			// 将需要删除的id增加到删除列表
			for (PositionStatement ps : deleteList) {
				if (!ids.contains(ps.getPsid())) {
					ids.add(ps.getPsid());
					psList.add(ps);
				}
			}
			try {
				positionStatementDao.deleteBatch(psList);
			} catch (Exception e) {
				i++;
			}

			try {
				positionStatementDao.deleteBatch(transferList(insertList, null));
			} catch (Exception e) {
				i++;
			}
		}

		if (insertList != null && insertList.size() > 0) {
			PositionStatement ps = formData.get(0);
			ps.setStatus(SysConstants.ApproverStatus.APPROVER_STATUS_DECLARE);
			positionStatementDao.insert(ps);

			try {
				positionStatementDao.insertBatch(transferList(insertList, ps));
			} catch (Exception e) {
				i++;
			}
		}
		return i;

	}

	/**
	 * @desc:
	 * @param psList
	 * @param ps
	 * @return
	 * @return List<PositionStatementDetail>
	 * @date： 2015-12-3 下午12:05:47
	 */
	private static List<PositionStatementDetail> transferList(List<PositionStatement> psList, PositionStatement ps) {
		List<PositionStatementDetail> psdList = new ArrayList<PositionStatementDetail>();
		for (PositionStatement pss : psList) {
			PositionStatementDetail psd = new PositionStatementDetail();

			try {
				BeanUtils.copyProperties(psd, pss);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (ps != null) {
				psd.setPsid(ps.getPsid());
			}
			psdList.add(psd);
		}

		return psdList;

	}

	public IPositionStatementDao getPositionStatementDao() {
		return positionStatementDao;
	}

	public void setPositionStatementDao(IPositionStatementDao positionStatementDao) {
		this.positionStatementDao = positionStatementDao;
	}

}
