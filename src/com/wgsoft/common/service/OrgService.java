package com.wgsoft.common.service;

import java.util.List;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.common.iservice.IOrgService;
import com.wgsoft.common.model.Organization;

public class OrgService implements IOrgService {

	private IBaseDao baseDao;

	public List<Organization> getOrgs(String orgId) {
		List<Organization> list = baseDao.getSqlList_(
							"select orgId,parentId,orgName from wg_organization where 1=1 START WITH ORGID = '" + orgId
												+ "' CONNECT BY PRIOR orgId = parentId", Organization.class);
		return list;
	}

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
