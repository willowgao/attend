package com.wgsoft.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 利用jodconverter(基于OpenOffice服务)将word文件(*.doc)转化为html格式，
 * 使用前请检查OpenOffice服务是否已经开启, OpenOffice进程名称：soffice.exe | soffice.bin
 * 
 * @author linshutao
 * */
public class Doc2HtmlUtil {
	private static Log log = LogFactory.getLog(Doc2HtmlUtil.class);

	private static Doc2HtmlUtil doc2HtmlUtil;

	private static String PROPERTIES_FILE = "/option.properties";
	private static String soffice_host = null;
	private static String soffice_port = null;
	// 获取html存放的位置
	private static String str = Doc2HtmlUtil.class.getClassLoader().getResource("").getPath();
	static {

		Properties option = new Properties();
		try {
			str = java.net.URLDecoder.decode(str, "utf-8");
			InputStream is = new FileInputStream(str + PROPERTIES_FILE);
			option.load(is);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		soffice_host = option.getProperty(SysConstants.OPENOFFIC_IP);
		soffice_port = option.getProperty(SysConstants.OPENOFFIC_PORT);
		

	}

	/**
	 * 获取Doc2HtmlUtil实例
	 * */
	public static synchronized Doc2HtmlUtil getDoc2HtmlUtilInstance() {
		if (doc2HtmlUtil == null) {
			doc2HtmlUtil = new Doc2HtmlUtil();
		}
		return doc2HtmlUtil;
	}

	/**
	 * 转换文件
	 * 
	 * @param fromFileInputStream
	 * */
	public static String offic2Html(InputStream fromFileInputStream, String fileName) {

		String uuid = UUID.randomUUID().toString().replace("-", "");
		String fileSuffix = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		String htmFileName = uuid + ".html";
		String docFileName = uuid + "." + fileSuffix;

		String htmlStr = str.substring(1, str.indexOf("WEB-INF")) + "web/html";
		try {
			// 获取的时候存在空格，需要进行字符级转译
			htmlStr = java.net.URLDecoder.decode(htmlStr, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File outfile = new File(htmlStr);

		File htmlOutputFile = new File(outfile.toString() + File.separatorChar + htmFileName);
		File docInputFile = new File(outfile.toString() + File.separatorChar + docFileName); 
		
		/**
		 * 由fromFileInputStream构建输入文件
		 * */
		try {
			OutputStream os = new FileOutputStream(docInputFile);
			int bytesRead = 0;
			byte[] buffer = new byte[1024 * 8];
			while ((bytesRead = fromFileInputStream.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}

			os.close();
			fromFileInputStream.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

		OpenOfficeConnection connection = new SocketOpenOfficeConnection(soffice_host, Integer.parseInt(soffice_port));
		try {
			connection.connect();
		} catch (Exception e) {
			System.err.println("文件转换出错，请检查OpenOffice服务是否启动。");
			log.error(e.getMessage(), e);
		}
		// convert
		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
		converter.convert(docInputFile, htmlOutputFile);
		connection.disconnect();

		// 转换完之后删除word文件
		docInputFile.delete();
		log.debug("删除上传文件：" + docInputFile.getName());
		return htmFileName;
	}

}