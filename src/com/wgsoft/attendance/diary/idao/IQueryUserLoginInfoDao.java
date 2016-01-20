package com.wgsoft.attendance.diary.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.diary.model.UserLoginInfo;

public interface IQueryUserLoginInfoDao {

	public List<UserLoginInfo> getLoginInfos(Map<String, String> queryMap); 
}
