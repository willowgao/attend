package com.wgsoft.performance.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.performance.model.PositionStatement;

/**
 * @title： IPositionStatementDao.java
 * @desc：岗位职责申报管理
 * @author： Willowgao
 * @date： 2015-12-2 上午11:12:27
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IPositionStatementDao extends IBaseDao {

	/**
	 * @desc: 查询岗位职位信息
	 * @param queryMap
	 * @return
	 * @return List<PositionStatement>
	 * @date： 2015-12-2 上午11:10:04
	 */
	List<PositionStatement> queryPositionStatements(Map<String, Object> queryMap);

	/**
	 * @desc:审核
	 * @param positionStatement
	 * @return
	 * @return int
	 * @date： 2015-12-2 上午11:10:45
	 */
	int updateStatus(PositionStatement positionStatement);
}
