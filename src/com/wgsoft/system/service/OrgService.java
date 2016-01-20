package com.wgsoft.system.service;

import java.util.List;

import com.wgsoft.common.model.Organization;
import com.wgsoft.system.idao.IOrgDao;
import com.wgsoft.system.iservice.IOrgService;
import com.wgsoft.system.model.Deptment;

public class OrgService implements IOrgService {

	private IOrgDao orgDao;

	public List<Organization> getOrgs(String orgId) {
		return orgDao.getOrgsForTree(orgId);
	}

	public List<Deptment> queryOrgsForList(String orgId, String orgType) {
		return orgDao.queryOrgsForList(orgId, orgType);
	}

	public IOrgDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(IOrgDao orgDao) {
		this.orgDao = orgDao;
	}

	public int saveOrUpdateDepement(Deptment dept, String orgType, String deptId) {
		return orgDao.saveOrUpdateDepement(dept, orgType, deptId);
	}

}
