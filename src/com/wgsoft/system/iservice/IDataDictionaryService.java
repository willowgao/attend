package com.wgsoft.system.iservice;

import java.util.Map;

/**
 * @title： IDataDictionaryService.java
 * @desc： 数据字典
 * @author： Willowgao
 * @date： 2015-10-27 下午01:17:06
 * @version： V1.0<br>
 * @versioninfo：慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IDataDictionaryService {

	/**
	 * @desc: 初始化业务字典
	 * @return void
	 * @date： 2015-10-27 下午01:28:49
	 */
	public void initDataDictionary();

	/**
	 * @desc:查询数据字典类型
	 * @param key
	 * @return
	 * @return Map<String,String>
	 * @date： 2015-10-27 下午02:02:15
	 */
	public Map<String, String> getDataDictionarysByKey(String key);

	/**
	 * @desc:查询数据字典返回JSON格式
	 * @param key
	 * @return
	 * @return String
	 * @date： 2015-10-27 下午02:04:36
	 */
	public String getJsonStrByKey(String key);

	/**
	 * @desc:查询数据字典返回JSON格式
	 * @param key
	 * @return
	 * @return String
	 * @date： 2015-10-27 下午02:04:36
	 */
	public String getListJsonStrByKey(String key);

	/**
	 * @desc:获取组织内部所有的部门
	 * @param org
	 * @return
	 * @return String
	 * @date： 2016-2-2 上午10:50:55
	 */
	public String getDeptByOrg(String org);

	/**
	 * @desc:获取用户所属部门和下属部门
	 * @param userid
	 * @return
	 * @return String
	 * @date： 2016-2-2 上午10:51:32
	 */
	public String getDeptByUser(String userid);

}
