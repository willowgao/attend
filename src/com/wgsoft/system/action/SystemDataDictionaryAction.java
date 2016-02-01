package com.wgsoft.system.action;


import com.wgsoft.common.action.BaseAction;
import com.wgsoft.system.iservice.IDataDictionaryService;
/**
 * @title： SystemDataDictionaryAction.java
 * @desc：  数据字典服务
 * @author： Willowgao
 * @date： 2015-10-29 下午04:49:05
 * @version： V1.0<br>
 * @versioninfo：慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class SystemDataDictionaryAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2941206327505726383L;
	
	private IDataDictionaryService dataDictionaryService = (IDataDictionaryService)getService("dataDictionaryService");
	
	public String getDictionary() throws Exception {
		String key = request.getParameter("key");
		String deptid = request.getParameter("deptid");
		String strDic = dataDictionaryService.getListJsonStrByKey(key);
		this.renderText(response,strDic);
		return null;
	}
}
