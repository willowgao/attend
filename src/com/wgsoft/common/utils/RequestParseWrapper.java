package com.wgsoft.common.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;

/**
 * @title： RequestParseWrapper.java
 * @desc： 文件上传过滤器
 * @author： Willowgao
 * @date： 2016-2-16 上午09:53:46
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class RequestParseWrapper extends JakartaMultiPartRequest {

	public void parse(HttpServletRequest servletRequest, String saveDir) throws IOException {
	}
}