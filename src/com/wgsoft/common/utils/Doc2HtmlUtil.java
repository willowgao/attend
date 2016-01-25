package com.wgsoft.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	 *            :
	 * */
	public static String offic2Html(InputStream fromFileInputStream, File toFileFolder) {
		String soffice_host = "127.0.0.1";
		String soffice_port = "8100";
		log.debug("soffice_host:" + soffice_host + ",soffice_port:" + soffice_port);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timesuffix = sdf.format(date);
		String htmFileName = "htmlfile" + timesuffix + ".html";
		String docFileName = "docfile" + timesuffix + ".xls";

		File htmlOutputFile = new File(toFileFolder.toString() + File.separatorChar + htmFileName);
		File docInputFile = new File(toFileFolder.toString() + File.separatorChar + docFileName);
		log.debug("########htmlOutputFile：" + toFileFolder.toString() + File.pathSeparator + htmFileName);
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

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("F:\\workspace\\workspace8\\咸宁市人社局平时考核系统项目工作量及报价v1.0.xls");
		InputStream input = new FileInputStream(file);
		File outfile = new File("F:\\workspace\\workspace8\\");
		offic2Html(input, outfile);
	}

}