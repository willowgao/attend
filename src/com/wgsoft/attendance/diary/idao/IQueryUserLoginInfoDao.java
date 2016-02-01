package com.wgsoft.attendance.diary.idao;

import java.util.List;
import java.util.Map;

import com.wgsoft.attendance.diary.model.UserLoginInfo;

/**
 * @title： IQueryUserLoginInfoDao.java
 * @desc：登录日志查询
 * @author： Willowgao
 * @date： 2016-2-1 下午04:37:31
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IQueryUserLoginInfoDao {

	public List<UserLoginInfo> getLoginInfos(Map<String, String> queryMap);
}
