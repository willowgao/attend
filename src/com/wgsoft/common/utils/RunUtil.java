package com.wgsoft.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class RunUtil {

	/**
	 * 格式化时间格式 转化为日期
	 * 
	 * @param time
	 *            日期
	 * @return fmTime
	 */
	public static String Format(String time) {
		String fmTime = "";
		if (time == null || time.length() == 0) { // 传入的是空字符

		} else if (time.length() == 4) {// 年
			fmTime = time + "-12-31";
		} else if (time.length() == 6) {// 季
			String str = time.substring(5, 6);
			fmTime = time.substring(0, 4) + "-"
					+ (str.equals("4") ? "10" : "0" + String.valueOf((Integer.parseInt(str) - 1) * 3 + 1)) + "-01";
		} else if (time.length() == 7) {// 月
			fmTime = time + "-01";
		} else if (time.length() == 10) {
			fmTime = time;
		}

		return fmTime;
	}

	/**
	 * 将集合转换成Sql中得In语句
	 * 
	 * @param o
	 *            集合对象 可为List,Object []
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String transObjAsSqlInStr(Object o) {
		Object[] elements = null;
		if (o instanceof java.util.List) {
			elements = ((java.util.List) o).toArray();
		} else {
			elements = (Object[]) o;
		}
		int len = elements.length;
		if (len == 1) {
			return "'" + elements[0] + "'";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			if (StringUtils.isEmpty((String) elements[i])) {
				continue;
			}
			String element = elements[i].toString();
			sb.append("'").append(element).append("',");
		}
		// 去掉最后一个字符返回
		return truncLastChar(sb.toString());
	}

	/**
	 * 去掉最后一个字符
	 * 
	 * @param str
	 * @return
	 */
	public static String truncLastChar(String str) {
		return str.substring(0, str.length() - 1);
	}

	/**
	 * 将map的key存入List中
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List mapKeyTransAsList(Map map) {
		// map中没有元素则返回空集合
		if (null == map || map.size() == 0)
			return null;
		return Arrays.asList(map.keySet().toArray());
	}

	/**
	 * @desc:为空判断
	 * @param o
	 * @return
	 * @return boolean
	 * @date： 2015-9-10 下午01:00:51
	 */
	public static boolean isEmpty(Object o) {
		if (null == o || "null".equals(o) || "".equals(o) || o.toString().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @desc: 不为空判断
	 * @param o
	 * @return
	 * @return boolean
	 * @date： 2015-9-10 下午01:00:42
	 */
	public static boolean isNotEmpty(Object o) {
		if (null == o || "null".equals(o) || "".equals(o) || o.toString().length() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 数据库转换为对象属性
	 * 
	 * @param voName
	 * @return
	 */
	public static String transDbAsField(String voName) {
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		for (int i = 0; i < voName.length(); i++) {
			char cur = voName.charAt(i);
			if (cur == '_') {
				flag = true;
			} else {
				if (flag) {
					sb.append(Character.toUpperCase(cur));
					flag = false;
				} else {
					sb.append(Character.toLowerCase(cur));
				}
			}
		}
		// System.out.println(sb);
		return sb.toString();
	}

	/**
	 * 对象属性转换为数据库中对应列
	 * 
	 * @param voName
	 *            对象属性
	 * @return
	 */
	public static String transFieldAsDb(String voName) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < voName.length(); i++) {
			char cur = voName.charAt(i);
			if (Character.isUpperCase(cur) && i >= 1) {
				sb.append("_");
				sb.append(cur);
			} else {
				sb.append(cur);
			}
		}
		return sb.toString().toUpperCase();
	}

	@SuppressWarnings("unchecked")
	public static List getPropertyList(Class clazz) {
		List zballList = new ArrayList();
		Field[] fields = clazz.getDeclaredFields();
		for (int len = 0; len < fields.length; len++) {
			Field field = fields[len];
			int n = field.getModifiers();
			// 判断是FINAL字段
			if (Modifier.isFinal(n)) {
				continue;
			}
			field.setAccessible(true);
			// 指标名
			String propName = field.getName();
			zballList.add(propName);
		}
		return zballList;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
