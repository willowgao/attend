package com.wgsoft.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

	private static String soffice_host;
	private static String soffice_port;

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
		String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		String htmFileName = uuid + ".html";
		String docFileName = uuid + "." + fileSuffix;

		// 根据不同的中间件，获取不同html的地址
		File outfile = new File(SysConstants.getPath(SysConstants.HTML_PATH));
		File htmlOutputFile = new File(outfile.toString() + File.separatorChar + htmFileName);
		File docInputFile = new File(outfile.toString() + File.separatorChar + docFileName);
		// 写文件
		writeFile(fromFileInputStream, docInputFile);
		// 通过openOffice读取写入的文件
		readFileFromOpenOffic(docInputFile, htmlOutputFile);
		// 转换完之后删除word文件
		docInputFile.delete();
		log.debug("删除上传文件：" + docInputFile.getName());
		return htmFileName;
	}

	/**
	 * @desc:通过openOffice读取写入的文件
	 * @param docInputFile
	 * @param htmlOutputFile
	 * @return void
	 * @date： 2016-2-1 下午03:16:35
	 */
	public static void readFileFromOpenOffic(File docInputFile, File htmlOutputFile) {
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(soffice_host, Integer.parseInt(soffice_port));
		try {
			connection.connect();
		} catch (Exception e) {
			log.error("文件转换出错，请检查OpenOffice服务是否启动。openOffice地址 :" + soffice_host + "端口 :" + soffice_port);
			log.error(e.getMessage(), e);
		}
		// convert
		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
		converter.convert(docInputFile, htmlOutputFile);
		connection.disconnect();
	}

	/**
	 * @desc:写文件
	 * @param fromFileInputStream
	 * @param docInputFile
	 * @return void
	 * @date： 2016-2-1 下午03:16:46
	 */
	public static void writeFile(InputStream fromFileInputStream, File docInputFile) {

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

	}

	/**
	 * @desc: 清空目录
	 * @return void
	 * @date： 2016-1-28 上午09:59:23
	 */
	public static synchronized boolean deleteFile(File file) {
		boolean bool = file.delete();
		if (bool) {
			log.info("Successfully deleted empty directory: " + file);
		} else {
			log.info("Failed to delete empty directory: " + file);
		}
		return bool;
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir
	 *            将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 */
	public static synchronized boolean deleteDirFile() {
		File file = new File(SysConstants.getPath(SysConstants.HTML_PATH));
		if (file.isDirectory()) {
			String[] children = file.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteFile(new File(file, children[i]));
				if (!success) {
					return false;
				}
			}
		} else {

		}
		// 目录此时为空，可以删除
		return true;
	}

	/**
	 * @desc: 清空系统无效文件
	 * @return void
	 * @date： 2016-3-1 上午08:58:03
	 */
	public static void clearFiles() {
		deleteExportFile();
		deleteDirFile();
	}

	/**
	 * @desc:清空导出的临时文件
	 * @return
	 * @return boolean
	 * @date： 2016-3-1 上午10:01:13
	 */
	public static synchronized boolean deleteExportFile() {
		File file = new File(SysConstants.getPath(SysConstants.EXPORT_PATH));
		if (file.isDirectory()) {
			String[] children = file.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteFile(new File(file, children[i]));
				if (!success) {
					return false;
				}
			}
		} else {

		}
		// 目录此时为空，可以删除
		return true;
	}

	public static void main(String[] args) {
		deleteExportFile();
	}

	public static String getSoffice_host() {
		return soffice_host;
	}

	public static void setSoffice_host(String sofficeHost) {
		soffice_host = sofficeHost;
	}

	public static String getSoffice_port() {
		return soffice_port;
	}

	public static void setSoffice_port(String sofficePort) {
		soffice_port = sofficePort;
	}

}