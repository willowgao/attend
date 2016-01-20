package com.wgsoft.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.SingleTableEntityPersister;

@SuppressWarnings("unchecked")
public class SqlUtil {
	// 缓存数据键值对；
	private static Map<String, String> map = new HashMap<String, String>();

	/**
	 * 由一个类得到连接的SQL中的FROM前的字段
	 * 
	 * @param cls
	 * @return
	 */
	public static String getClassFieldString(Class cls) {

		StringBuffer sb = new StringBuffer();
		Field[] f = cls.getDeclaredFields();
		for (int i = 0; i < f.length; i++) {
			if (i == f.length - 1) {
				sb.append("t1." + f[i].getName());
			} else {
				sb.append("t1." + f[i].getName() + ",");
			}
		}
		return sb.toString();

	}

	/**
	 * 由一个类得到一个类名称
	 * 
	 * @param cls
	 * @return
	 */
	public static String getClassName(Class cls) {
		int pos = cls.getName().lastIndexOf(".");
		return cls.getName().substring(0, pos);
	}

	/**
	 * 将属性名称为‘fdl_name_code_id’的字符串转换为标准的JAVABEAN属性名称
	 * 
	 * @param str
	 * @return fdlNameCodeId
	 */
	public static String getPropNameForDb(String str) {
		// 从MAP中获取，如果获取到直接返回
		String tempS = map.get(str);
		if (tempS != null) {
			return tempS;
		}
		// 如果没有下划线，则将其直接返回
		if (str.indexOf("_") == -1) {
			String temp = str.toLowerCase();
			map.put(str, temp);
			return temp;
		}
		// 拆分字符串;
		StringTokenizer st = new StringTokenizer(str, "_");
		int i = 0;
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreElements()) {
			String strTemp = st.nextToken();
			if (i == 0) {
				sb.append(strTemp);
			} else {
				sb.append(strTemp.substring(0, 1).toUpperCase() + strTemp.substring(1));
			}
			i++;
		}
		map.put(str, sb.toString());
		return sb.toString();
	}

	/**
	 * 由类名通过sessionFactory得到对应的表名
	 * 
	 * @param className
	 * @param sessionFactory
	 *            hibernateSessionFactory
	 * @return
	 */
	public static String getTableNameForClassName(String className, SessionFactory sessionFactory) {
		Map classMap = sessionFactory.getAllClassMetadata();
		SingleTableEntityPersister table = (SingleTableEntityPersister) classMap.get(className);
		if (table != null) {
			return table.getTableName();
		}
		return null;
	}

	/**
	 * 将集合转换成Sql中得In语句
	 * 
	 * @param o
	 *            集合对象 可为List,Object []
	 * @return
	 */
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
			if (RunUtil.isEmpty((String) elements[i])) {
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
}
