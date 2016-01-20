package com.wgsoft.common.service;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.common.iservice.IBaseService;

/**
 * @type BaseService
 * @title BaseService.java
 * @desc
 * @author gaochengliu
 * @date 2015-7-17
 * @version V1.0
 */
public class BaseService implements IBaseService {

	private IBaseDao baseDao;

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
