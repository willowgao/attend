package com.wgsoft.performance.dao;

import java.util.List;
import java.util.Map;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.common.utils.RunUtil;
import com.wgsoft.performance.idao.IPositionStatementDao;
import com.wgsoft.performance.model.PositionStatement;

/**
 * @title： PositionStatementDao.java
 * @desc：岗位职责申报管理
 * @author： Willowgao
 * @date： 2015-12-2 上午11:13:57
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class PositionStatementDao extends BaseDao implements IPositionStatementDao {

	public List<PositionStatement> queryPositionStatements(Map<String, Object> queryMap) {
		StringBuffer sql = new StringBuffer(
				"SELECT A.PSID,b.ssid,deptid,roleid,starttime,status,b.standards,b.statements,b.comments ");
		sql.append(" FROM position_statement  a, position_statement_detail b WHERE a.psid = b.psid");
		if (RunUtil.isNotEmpty(queryMap.get("deptid"))) {
			sql.append(" and deptid ='").append(queryMap.get("deptid")).append("'");
		}
		if (RunUtil.isNotEmpty(queryMap.get("roleid"))) {
			sql.append(" and roleid ='").append(queryMap.get("roleid")).append("'");
		}

		return getSqlList_(sql.toString(), PositionStatement.class);
	}

	public int updateStatus(PositionStatement positionStatement) {
		// TODO Auto-generated method stub
		return 0;
	}

}
