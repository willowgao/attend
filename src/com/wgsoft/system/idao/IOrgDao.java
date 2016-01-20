package com.wgsoft.system.idao;

import java.util.List;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.common.model.Organization;
import com.wgsoft.system.model.Deptment;

public interface IOrgDao extends IBaseDao {

	/**
	 * @desc: 查询机构树
	 * @param orgId
	 * @return
	 * @return List<Organization>
	 * @date： 2015-9-9 下午01:23:29
	 */
	List<Organization> getOrgsForTree(String orgId);

	/**
	 * @desc: 查询机构信息
	 * @param orgId
	 *            组织ID
	 * @param orgType
	 *            组织类型
	 * @return
	 * @return List<Organization>
	 * @date： 2015-9-9 下午01:28:17
	 */
	List<Deptment> queryOrgsForList(String orgId, String orgType);

	/**
	 * @desc: 保存机构信息
	 * @param dept
	 *            组织信息
	 * @param orgType
	 *            组织类型
	 * @param deptId
	 *            部门编号
	 * @return
	 * @return int
	 * @date： 2015-9-9 下午02:45:28
	 */
	int saveOrUpdateDepement(Deptment dept, String orgType, String deptId);
}
