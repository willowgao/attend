package com.wgsoft.system.iservice;

import java.util.Map;

/**
 * @title： IDataDictionaryService.java
 * @desc： 数据字典
 * @author： Willowgao
 * @date： 2015-10-27 下午01:17:06
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
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
}
