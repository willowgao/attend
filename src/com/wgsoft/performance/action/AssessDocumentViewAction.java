package com.wgsoft.performance.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


import org.apache.struts2.json.JSONUtil;

import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.Doc2HtmlUtil;
import com.wgsoft.common.utils.FileUtil;

/**
 * @title： AssessDocmentViewAction.java
 * @desc：考核资料查阅
 * @author： Willowgao
 * @date： 2016-1-26 下午03:45:33
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class AssessDocumentViewAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -439816025456033593L;

	/**
	 * 页面初始化
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * @desc:考核资料审阅
	 * @return
	 * @throws Exception
	 * @return String
	 * @date： 2016-1-26 上午09:52:56
	 */
	public String viewDocument() throws Exception {
		// 输入文件路径以及文件名
		File file = new File("F:\\code\\open\\咸宁市人社局平时考核系统工作计划安排_V1.0.xlsx");
		InputStream input = new FileInputStream(file);
		String fileName1 = "咸宁市人社局平时考核系统工作计划安排_V1.0.xlsx";
		String fileName = Doc2HtmlUtil.offic2Html(input, fileName1);
		renderText(response, JSONUtil.serialize(fileName));
		// Doc2HtmlUtil.deleteDirFile();

		return null;
	}

	/**
	 * @desc:文件写入
	 * @return
	 * @throws Exception 
	 * @return String
	 * @date： 2016-2-16 上午10:23:06
	 */
	public String upload() throws Exception {
		FileUtil.writeFile(request, response);
		return null;
	}

	/**
	 * @desc:文件保存到数据库中
	 * @return
	 * @throws Exception 
	 * @return String
	 * @date： 2016-2-16 上午10:23:16
	 */
	public String save() throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String msg = "您上传了：<br/>";
		String[] fileUrls = request.getParameterValues("fileUrl");
		String[] fileNames = request.getParameterValues("fileName");
		for (int i = 0; i < fileUrls.length; i++) {
			msg += "文件路径：" + fileUrls[i] + " 文件名称：" + fileNames[i] + "<br/>";
			System.out.println(msg);
		} 
		return null;
	}

}
