package com.wgsoft.common.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title： SysConstants.java
 * @desc： 系统公共用常量
 * @author： Willowgao
 * @date： 2015-9-8 上午08:45:15
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class SysConstants {

	/**
	 * 保存成功
	 */
	public static final int SUCCESS = 0;
	/**
	 * 保存失败
	 */
	public static final int ERROR = 1;

	/**
	 * 机构
	 */
	public static final String ORGTYPE_ORG = "1";
	/**
	 * 部门
	 */
	public static final String ORGTYPE_DEPT = "2";

	/**
	 * 有效
	 */
	public static final String ISENABLE_YES = "0";
	/**
	 * 无效
	 */
	public static final String ISENABLE_NO = "1";

	/**
	 * 考勤异常类型 迟到
	 */
	public static final String CLOCK_EXCEPTION_LATER = "1";
	/**
	 * 考勤异常类型 早退
	 */
	public static final String CLOCK_EXCEPTION_EARLY = "2";
	/**
	 * 考勤异常类型 旷工
	 */
	public static final String CLOCK_EXCEPTION_QUIT = "3";
	/**
	 * 考勤异常类型 未打卡
	 */
	public static final String CLOCK_EXCEPTION_DONOT = "4";

	/**
	 * 
	 * @title： SysConstants.java
	 * @desc： 角色类型
	 * @author： Willowgao
	 * @date： 2015-11-16 下午03:55:43
	 * @version： V1.0<br>
	 * @versioninfo： 慕安软件<br>
	 * @modify： 更改时间、更改人、更改原因、更改内容<br>
	 */
	public static class RoleType {
		/**
		 * 数据字典KEY
		 */
		public static final String DICTIONARY_KEY = "ROLETYPE";
		/**
		 * 角色类型 普通人员
		 */
		public static final String ROLETYPE_NOMARL = "1";
		/**
		 * 角色类型 初审人员
		 */
		public static final String ROLETYPE_TRIAL = "2";
		/**
		 * 角色类型 一级审核
		 */
		public static final String ROLETYPE_FIRST = "3";
		/**
		 * 角色类型 二级审核
		 */
		public static final String ROLETYPE_SECOND = "4";
		/**
		 * 角色类型 三级审核
		 */
		public static final String ROLETYPE_THIRD = "5";
	}

	/**
	 * 
	 * @title： SysConstants.java
	 * @desc：核审状态
	 * @author： Willowgao
	 * @date： 2015-10-30 上午08:54:18
	 * @version： V1.0<br>
	 * @versioninfo： 慕安软件<br>
	 * @modify： 更改时间、更改人、更改原因、更改内容<br>
	 */
	public static class ApproverStatus {
		/**
		 * 数据字典KEY
		 */
		public static final String DICTIONARY_KEY = "APPROVER_STATUS";
		/**
		 * 审核状态 审核不通过
		 */
		public static final String APPROVER_STATUS_NOTPASS = "0";
		/**
		 * 审核状态 申报
		 */
		public static final String APPROVER_STATUS_DECLARE = "1";
		/**
		 * 审核状态 初审通过
		 */
		public static final String APPROVER_STATUS_PASS = "2";
		/**
		 * 审核状态 一级核审通过
		 */
		public static final String APPROVER_STATUS_FIRSTPASS = "3";
		/**
		 * 审核状态 二级核审通过
		 */
		public static final String APPROVER_STATUS_SECONDPASS = "4";
		/**
		 * 审核状态 三级核审通过
		 */
		public static final String APPROVER_STATUS_THIRDPASS = "5";

	}

	/**
	 * @title： SysConstants.java
	 * @desc： 请假类型
	 * @author： Willowgao
	 * @date： 2015-11-2 下午01:43:39
	 * @version： V1.0<br>
	 * @versioninfo： 慕安软件<br>
	 * @modify： 更改时间、更改人、更改原因、更改内容<br>
	 */
	public static class LeaveType {
		/**
		 * 数据字典KEY
		 */
		public static final String DICTIONARY_KEY = "LEAVETYPE";
		/**
		 * 假期类型 出差
		 */
		public static final String LEAVE_TYPE_BUSINESSTRIP = "1";
		/**
		 * 假期类型 年假
		 */
		public static final String LEAVE_TYPE_YEAR = "2";
		/**
		 * 假期类型 陪产假
		 */
		public static final String LEAVE_TYPE_PATERNITY = "3";
		/**
		 * 假期类型 事假
		 */
		public static final String LEAVE_TYPE_PERSONAL = "4";
		/**
		 * 假期类型 婚假
		 */
		public static final String LEAVE_TYPE_MARRIAGE = "5";
		/**
		 * 假期类型 产假
		 */
		public static final String LEAVE_TYPE_MATERNITY = "6";
		/**
		 * 假期类型 病假
		 */
		public static final String LEAVE_TYPE_SICK = "7";
		/**
		 * 未打卡
		 */
		public static final String CLOCK_EXCEPTION_DONOT = "8";

	}

	/**
	 * 
	 * @title： SysConstants.java
	 * @desc：日志类型
	 * @author： Willowgao
	 * @date： 2015-11-9 下午02:59:42
	 * @version： V1.0<br>
	 * @versioninfo： 慕安软件<br>
	 * @modify： 更改时间、更改人、更改原因、更改内容<br>
	 */
	public static class DiaryType {
		/**
		 * 数据字典KEY
		 */
		public static final String DICTIONARY_KEY = "DIARYTYPE";
		/**
		 * 日志类型 日报
		 */
		public static final String DIARY_TYPE_DAILY = "1";
		/**
		 * 日志类型 周报
		 */
		public static final String DIARY_TYPE_WEEKLY = "2";
		/**
		 * 日志类型 月报
		 */
		public static final String DIARY_TYPE_MONTHLY = "3";
		/**
		 * 日志类型 季报
		 */
		public static final String DIARY_TYPE_QUARTERLY = "4";
	}

	/**
	 * 
	 * 
	 * @title： SysConstants.java
	 * @desc： 性别
	 * @author： Willowgao
	 * @date： 2015-11-16 下午03:57:04
	 * @version： V1.0<br>
	 * @versioninfo： 慕安软件<br>
	 * @modify： 更改时间、更改人、更改原因、更改内容<br>
	 */
	public static class SexType {

		public static final String SEX_TYPE_KEY = "SEXTYPE";
		/**
		 * 性别 未知
		 */
		public static final String SEX_TYPE_NONE = "0";
		/**
		 * 性别 男
		 */
		public static final String SEX_TYPE_MAN = "1";
		/**
		 * 性别 女
		 */
		public static final String SEX_TYPE_WOMAN = "2";
	}

	/**
	 * 
	 * @title： SysConstants.java
	 * @desc： 前台传入Json字符
	 * @author： Willowgao
	 * @date： 2015-11-20 上午08:55:21
	 * @version： V1.0<br>
	 * @versioninfo： 慕安软件<br>
	 * @modify： 更改时间、更改人、更改原因、更改内容<br>
	 */
	public static class DataGridData {
		/**
		 * 新增list
		 */
		public static final String DATAGRID_LIST_INSERTLIST = "insertRow";
		/**
		 * 更新list
		 */
		public static final String DATAGRID_LIST_UPDATELIST = "updateRow";
		/**
		 * 删除list
		 */
		public static final String DATAGRID_LIST_DELETELIST = "deleteRow";
	}

	/**
	 * 
	 * @title： SysConstants.java
	 * @desc： 工作类型
	 * @author： Willowgao
	 * @date： 2015-11-23 下午04:47:18
	 * @version： V1.0<br>
	 * @versioninfo： 慕安软件<br>
	 * @modify： 更改时间、更改人、更改原因、更改内容<br>
	 */
	public static class JobType {
		/**
		 * 数据字典KEY
		 */
		public static final String JOB_TYPE_KEY = "JOBTYPE";
		/**
		 * 工作类型 - 工作调研
		 */
		public static final String JOB_TYPE_WORKRESEARCH = "1";
		/**
		 * 工作类型 - 工作计划
		 */
		public static final String JOB_TYPE_PLAN = "2";
		/**
		 * 工作类型 - 培训学习
		 */
		public static final String JOB_TYPE_STUDY = "3";
		/**
		 * 工作类型 - 其它
		 */
		public static final String JOB_TYPE_OTHER = "4";
	}

	/**
	 * openOffic IP
	 */
	public static final String OPENOFFIC_IP = "openoffic.ip";
	/**
	 * openOffic 端口
	 */
	public static final String OPENOFFIC_PORT = "openoffic.port";

	/**
	 * 上级
	 */
	public static final String ASSESS_TYPE_HIGHER_LEVEL = "1";

	/**
	 * 同级
	 */
	public static final String ASSESS_TYPE_PEER_LEVEL = "2";

	// 获取html存放的位置 tomcat和weblogic路径获取方法不同
	public static String classUrl = FileUtil.class.getClassLoader().getResource("").getPath();

	/**
	 * 导出文件目录
	 */
	public static String EXPORT_PATH = "web/files/exportFile";

	/**
	 * 生成html临时预览文件目录
	 */
	public static String HTML_PATH = "web/files/html";

	/**
	 * 上传文件目录
	 */
	public static String UPLOAD_PATH = "web/files/uploadFile";

	/**
	 * @desc:获取系统路径
	 * @param folderPath
	 * @return
	 * @return String
	 * @date： 2016-2-25 上午10:43:16
	 */
	public static String getPath(String folderPath) {
		String uploadPath;
		try {
			uploadPath = classUrl.substring(1, classUrl.indexOf("WEB-INF")) + folderPath;
		} catch (Exception e1) {
			uploadPath = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			uploadPath = uploadPath.substring(1, uploadPath.indexOf("WEB-INF")) + folderPath;
		}
		try {
			uploadPath = java.net.URLDecoder.decode(uploadPath, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File up = new File(uploadPath);
		if (!up.exists()) {
			up.mkdir();
		}
		return uploadPath;

	}

	/**
	 * 
	 * @title： SysConstants.java
	 * @desc： 查检日期天数公共类
	 * @author： Willowgao
	 * @date： 2016-3-17 下午05:55:28
	 * @version： V1.0<br>
	 * @versioninfo： 慕安软件<br>
	 * @modify： 更改时间、更改人、更改原因、更改内容<br>
	 */

	public static class DatesCheckUtils {

		/**
		 * 考核检查天数
		 */
		public static final String[] CHECK_TYPE_ASSESS = { "1", "6" };
		/**
		 *日期检查类型与具体的检查时间天数
		 */
		static Map<String, String> checkMap = null;

		public static String getDatesByType(String type) {
			if (checkMap == null) {
				checkMap = new HashMap<String, String>();
				checkMap.put(CHECK_TYPE_ASSESS[0], CHECK_TYPE_ASSESS[1]);
			}
			return checkMap.get(type);
		}
	}

}
