package com.wgsoft.common.service;

import java.util.List;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.common.iservice.IUserInfoService;
import com.wgsoft.user.model.UserInfo;

public class UserInfoService implements IUserInfoService {

	private IBaseDao baseDao;

	public List<Object> getUserInfo() {
		List<Object> list = baseDao.getList(UserInfo.class);
		return list;

	}

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
