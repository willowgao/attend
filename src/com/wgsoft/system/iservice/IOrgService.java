package com.wgsoft.system.iservice;

import java.util.List;

import com.wgsoft.common.model.Organization;
import com.wgsoft.system.model.Deptment;

public interface IOrgService {

	/**
	 * @desc:
	 * @param orgId
	 * @return
	 * @return List<Organization>
	 * @date： 2015-9-9 下午01:23:29
	 */
	List<Organization> getOrgs(String orgId);

	/**
	 * @desc:
	 * @param orgId
	 * @param orgType
	 * @return
	 * @return List<Organization>
	 * @date： 2015-9-9 下午01:27:54
	 */
	List<Deptment> queryOrgsForList(String orgId, String orgType);

	/**
	 * @desc:
	 * @param dept
	 * @param orgType
	 * @return 
	 * @return int
	 * @date： 2015-9-9 下午02:29:44
	 */
	int saveOrUpdateDepement(Deptment dept, String orgType, String deptId);
}
