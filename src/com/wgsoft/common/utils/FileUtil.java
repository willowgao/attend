package com.wgsoft.common.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.wgsoft.common.model.BaseVO;

/**
 * 
 * @title： FileUtil.java
 * @desc：plupload 文件上传工具类型
 * @author： Willowgao
 * @date： 2016-2-16 上午09:53:09
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class FileUtil {

	static String uploadPath;

	/**
	 * @desc: 初始化路径
	 * @return void
	 * @date： 2016-2-16 上午09:52:59
	 */
	public static void init() {
		uploadPath = SysConstants.getPath(SysConstants.UPLOAD_PATH);
		File up = new File(uploadPath);
		if (!up.exists()) {
			up.mkdir();
		}

	}

	public static void writeFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 初始化目录
		init();
		response.setCharacterEncoding("UTF-8");
		Integer chunk = null;// 分割块数
		Integer chunks = null;// 总分割数
		String tempFileName = null;// 临时文件名
		String newFileName = null;// 最后合并后的新文件名
		BufferedOutputStream outputStream = null;
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1024);
				// factory.setRepository(new File(repositoryPath));// 设置临时目录
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				// upload.setSizeMax(5 * 1024 * 1024);// 设置附件最大大小，超过这个大小上传会不成功
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) {// 是文本域
						if (item.getFieldName().equals("name")) {
							tempFileName = item.getString();
							// System.out.println("临时文件名：" + tempFileName);
						} else if (item.getFieldName().equals("chunk")) {
							chunk = Integer.parseInt(item.getString());
							// System.out.println("当前文件块：" + (chunk + 1));
						} else if (item.getFieldName().equals("chunks")) {
							chunks = Integer.parseInt(item.getString());
							// System.out.println("文件总分块：" + chunks);
						}
					} else {// 如果是文件类型
						if (tempFileName != null) {
							String chunkName = tempFileName;
							if (chunk != null) {
								chunkName = chunk + "_" + tempFileName;
							}
							File savedFile = new File(uploadPath, chunkName);
							item.write(savedFile);
						}
					}
				}

				newFileName = UUID.randomUUID().toString().replace("-", "").concat(".").concat(
						FilenameUtils.getExtension(tempFileName));
				if (chunk != null && chunk + 1 == chunks) {
					outputStream = new BufferedOutputStream(new FileOutputStream(new File(uploadPath, newFileName)));
					// 遍历文件合并
					for (int i = 0; i < chunks; i++) {
						File tempFile = new File(uploadPath, i + "_" + tempFileName);
						byte[] bytes = FileUtils.readFileToByteArray(tempFile);
						outputStream.write(bytes);
						outputStream.flush();
						tempFile.delete();
					}
					outputStream.flush();
				}
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("status", true);
				m.put("fileUrl", SysConstants.UPLOAD_PATH + "/" + newFileName);
				response.getWriter().write(JSON.toJSONString(m));
			} catch (FileUploadException e) {
				e.printStackTrace();
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("status", false);
				response.getWriter().write(JSON.toJSONString(m));
			} catch (Exception e) {
				e.printStackTrace();
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("status", false);
				response.getWriter().write(JSON.toJSONString(m));
			} finally {
				try {
					if (outputStream != null)
						outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static Log log = LogFactory.getLog(FileUtil.class);
	/** 缺省表格数据开始行 */
	private static int DEFAULT_BEGIN_ROW = 1;

	/**
	 * excel文档转换为bean，暂只支持单个sheet，只支持基本数据类型和String类型
	 * 
	 * @param <T>
	 * @param startRowNum
	 *            文档起始行(大于1)
	 * @param clazz
	 *            转换为bean类的class
	 * @param file
	 *            文件
	 * @param fieldNameIndex
	 *            excel列下标对应bean实体属性名称
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> excel2Bean(File file, int startRowNum, Class<T> clazz, Map<Integer, String> fieldNameIndex)
			throws Exception {
		if (file == null) {
			return null;
		}
		startRowNum = startRowNum < 0 ? DEFAULT_BEGIN_ROW : startRowNum;
		InputStream is = null;
		HSSFWorkbook hssfWorkbook = null;
		try {
			is = new FileInputStream(file);
			hssfWorkbook = new HSSFWorkbook(is);
		} catch (FileNotFoundException e) {
			throw new Exception("指定路径文件不存在！", e);
		} catch (IOException e) {
			throw new Exception("读取文件异常！", e);
		}

		// 获取第一个sheet页
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		HSSFRow hssfRow = null;
		List<T> list = new ArrayList<T>();
		for (int i = startRowNum; i <= hssfSheet.getLastRowNum(); i++) {
			hssfRow = hssfSheet.getRow(i);
			T bean = clazz.newInstance();
			for (Integer index : fieldNameIndex.keySet()) {
				String fieldName = fieldNameIndex.get(index);
				// 调用设置值方法
				Field field = clazz.getDeclaredField(fieldName);
				Method setMethod = BeanUtil.getSetMethodByFieldName(fieldName, clazz);
				Object value = null;
				if (field.getType().equals("java.lang.Integer")) {
					value = hssfRow.getCell(index).getNumericCellValue();
				} else {
					value = hssfRow.getCell(index).toString();
				}
				setMethod.invoke(bean, value);
			}
			list.add(bean);
		}
		return list;
	}

	/**
	 * excel文档转换为bean，暂只支持单个sheet，只支持基本数据类型和String类型
	 * 
	 * @param <T>
	 * @param startRowNum
	 *            文档起始行(大于1)
	 * @param clazz
	 *            转换为bean类的class
	 * @param filePath
	 *            文件路径
	 * @param fieldNameIndex
	 *            excel列下标对应bean实体属性名称
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> excel2Bean(String filePath, int startRowNum, Class<T> clazz,
			Map<Integer, String> fieldNameIndex) throws Exception {
		return excel2Bean(new File(filePath), startRowNum, clazz, fieldNameIndex);
	}

	/**
	 * 从文本文件中读取字符串
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String txtFile2Str(File file) throws IOException {
		if (!file.exists()) {
			log.error("本地环境不存在指定的文件：" + file.getName());
			return null;
		}

		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuffer fileContent = new StringBuffer();

		String a = null;
		do {
			try {
				a = br.readLine();
			} catch (IOException e) {
				log.warn("读取文件过程中出错", e);
				throw e;
			}
			if (a != null) {
				fileContent.append(a);
			}
		} while (a != null);

		br.close();

		return fileContent.toString();
	}

	/**
	 * 从实体对象集合导出excel表格（暂只支持一个sheet页）
	 * 
	 * @param headNames
	 *            表格标题行单元格名称
	 * @param fieldNames
	 *            表格列对应bean实体属性名称（顺序和headNames一致）
	 * @param beanList
	 *            实体集合
	 * @param cellStyle
	 *            个性表格样式，可以为null
	 * @param clazz
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static <T> HSSFWorkbook bean2Excel(String[] headNames, String[] fieldNames, List<T> beanList,
			Class<T> clazz, HSSFCellStyle cellStyle) throws NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 设置默认工作表和样式
		if (cellStyle == null) {
			// 设置单元格格式
			cellStyle = workbook.createCellStyle();
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setWrapText(true);
		}
		HSSFSheet sheet = workbook.createSheet();
		int rowNum = 0;
		// 设置标题行
		if (headNames != null && headNames.length > 0) {
			HSSFRow row = sheet.createRow(rowNum++);
			for (int i = 0; i < headNames.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(new HSSFRichTextString(headNames[i]));
				cell.setCellStyle(cellStyle);
			}
		}
		// 设置数据行
		if (beanList != null && fieldNames != null && fieldNames.length > 0)
			for (T t : beanList) {
				HSSFRow row = sheet.createRow(rowNum++);
				for (int i = 0; i < fieldNames.length; i++) {
					Method method = BeanUtil.getGetMethodByFieldName(fieldNames[i], clazz);
					HSSFCell cell = row.createCell(i);
					cell.setCellValue(new HSSFRichTextString(method.invoke(t, null).toString()));
					cell.setCellStyle(cellStyle);
				}
			}
		return workbook;
	}

	public static void main(String[] args) {
		/** 导出文件的列头和属性名称 */
		String[] expColumnNames = new String[] { "数据", "ID", "父ID" };
		String[] expfieldNames = new String[] { "datagrid", "id", "pid" };
		List<BaseVO> baseVOs = new ArrayList<BaseVO>();
		for (int i = 0; i < 10; i++) {
			BaseVO vo = new BaseVO();
			vo.setDatagrid("数据" + i);
			vo.setId(String.valueOf(i));
			vo.setPid(String.valueOf(i).concat("父"));
			baseVOs.add(vo);
		}
		File file = new File("D:/tmp/测试.xls");
		try {
			OutputStream os = new FileOutputStream(file);
			HSSFWorkbook workbook = FileUtil.bean2Excel(expColumnNames, expfieldNames, baseVOs, BaseVO.class, null);
			workbook.write(os);
			os.close();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
