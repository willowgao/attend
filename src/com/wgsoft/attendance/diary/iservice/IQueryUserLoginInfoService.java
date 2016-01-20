package com.wgsoft.attendance.diary.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.diary.model.UserLoginInfo;

public interface IQueryUserLoginInfoService {

	public List<UserLoginInfo> getLoginInfos(Map<String, String> queryMap);
}
