package com.wgsoft.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.directwebremoting.extend.ConvertUtil;
import org.springframework.beans.BeanUtils;

public class BeanUtil {

	/**
	 * 根据属性名字获取相应bean对应的get方法
	 * 
	 * @param fieldName
	 * @return
	 * @throws NoSuchMethodException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static Method getGetMethodByFieldName(String fieldName, Class<?> clazz) throws NoSuchMethodException {

		String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		try {
			return clazz.getDeclaredMethod(getMethodName, null);
		} catch (NoSuchMethodException e) {
			if (clazz != Object.class) {
				return getGetMethodByFieldName(fieldName, clazz.getSuperclass());
			} else {
				throw new NoSuchMethodException();
			}
		}

	}

	/**
	 * 根据属性名字获取相应bean对应的set方法
	 * 
	 * @param fieldName
	 * @param clazz
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 */
	public static Method getSetMethodByFieldName(String fieldName, Class<?> clazz) throws SecurityException,
			NoSuchFieldException, NoSuchMethodException {

		String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		Field field = clazz.getDeclaredField(fieldName);
		return clazz.getDeclaredMethod(setMethodName, field.getType());

	}

	/**
	 * 根据属性名获取set方法放置值。 <br/>
	 * 支持成员的内部属性的放置，格式 如opcServerConnectConfig.serverProgID
	 * 
	 * @param targetObject
	 *            用来放置值的目标对象，如{@code OPCPointConfig}的对象
	 * @param fieldName
	 *            属性名称，如{@code OPCPointConfig}
	 *            中属性pointCode，或者属性opcServerConnectConfig中的serverProgID成员
	 * @param value
	 *            放入field的对应值
	 * @throws Exception
	 */
	public static <T> void pojoSetValueByFieldName(T targetObject, String fieldName, Object value) throws Exception {

		if (fieldName == null || targetObject == null) {
			return;
		}

		boolean innerSet = false;

		String temp = "";
		if (fieldName.indexOf(".") != -1) {
			temp = fieldName.substring(0, fieldName.indexOf("."));

			innerSet = true;

		} else {

			temp = fieldName;

		}

		Class<?> targetClass = targetObject.getClass();

		// 非设置成员的内部属性
		if (innerSet == false) {
			String setMethodName = "set" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method[] methods = targetClass.getMethods();

			for (Method method : methods) {
				if (setMethodName.equals(method.getName())) {

					method.invoke(targetObject, value);
					break;
				}
			}
		} else {
			// 获取成员
			String getMethodName = "get" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method getMethod = targetClass.getMethod(getMethodName, null);

			Object member = getMethod.invoke(targetObject, null);

			if (member == null) {
				return;
			}

			// 设置成员的内部属性
			Class innerClass = member.getClass();

			temp = fieldName.substring(fieldName.indexOf(".") + 1);

			String setInnerMethodName = "set" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method[] methods = innerClass.getMethods();

			for (Method method : methods) {
				if (setInnerMethodName.equals(method.getName())) {

					method.invoke(member, value);
					break;
				}
			}
		}
	}

	/**
	 * 根据属性名获取get方法放置值。 <br/>
	 * 支持成员的内部属性的放置，格式 如opcServerConnectConfig.serverProgID
	 * 
	 * @param targetObject
	 *            用来放置值的目标对象，如{@code OPCPointConfig}的对象
	 * @param fieldName
	 *            属性名称，如{@code OPCPointConfig}
	 *            中属性pointCode，或者属性opcServerConnectConfig中的serverProgID成员
	 * @throws Exception
	 */
	public static <T> Object pojoGetValueByFieldName(T targetObject, String fieldName) throws Exception {

		if (fieldName == null || targetObject == null) {
			return null;
		}

		boolean innerSet = false;

		String temp = "";
		if (fieldName.indexOf(".") != -1) {
			temp = fieldName.substring(0, fieldName.indexOf("."));

			innerSet = true;

		} else {
			temp = fieldName;

		}

		Class targetClass = targetObject.getClass();

		// 非设置成员的内部属性
		if (innerSet == false) {
			String getMethodName = "get" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method method = targetClass.getMethod(getMethodName, null);

			return method.invoke(targetObject, null);

		} else {
			// 获取成员
			String getMethodName = "get" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method method = targetClass.getMethod(getMethodName, null);

			Object member = method.invoke(targetObject, null);

			if (member == null) {
				return null;
			}

			// 设置成员的内部属性
			Class innerClass = member.getClass();

			temp = fieldName.substring(fieldName.indexOf(".") + 1);

			String getInnerMethodName = "get" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method innerMethod = innerClass.getMethod(getInnerMethodName, null);

			return innerMethod.invoke(member, null);

		}

	}

	/**
	 * 合并两个List集合中实体类，如果id字段重复则覆盖
	 * 
	 * @param <S>
	 * @param <T>
	 * @param sourceBeanList
	 *            源集合
	 * @param targetBeanList
	 *            目标集合
	 * @param targetClazz
	 *            目标集合实体类
	 * @param idFieldName
	 *            合并依据字段（相同则覆盖）
	 * @return 合并后的目标集合
	 * @throws Exception
	 */
	public static <S, T> List<T> combineList(List<S> sourceBeanList, List<T> targetBeanList, Class<T> targetClazz,
			String idFieldName, String[] ignoreProperties) throws Exception {
		Map<Object, T> map = list2MapByField(targetBeanList, targetClazz, idFieldName);
		// 利用反射获取bean标识字段值
		Method getIdMethod = getGetMethodByFieldName(idFieldName, targetClazz);
		// 合并操作

		if (sourceBeanList != null) {
			for (S s : sourceBeanList) {
				Object id = getIdMethod.invoke(s, null);
				T t = map.containsKey(id) ? map.get(id) : targetClazz.newInstance();
				BeanUtils.copyProperties(s, t, ignoreProperties);
				map.put(id, t);
			}
		}

		return new ArrayList<T>(map.values());
	}

	/**
	 * 把list转换为以实体对象的某个属性为key的map
	 * 
	 * @param <T>
	 * @param targetBeanList
	 * @param targetClazz
	 * @param idFieldName
	 * @return
	 * @throws Exception
	 */
	public static <T> Map<Object, T> list2MapByField(List<T> beanList, Class<T> clazz, String idFieldName)
			throws Exception {
		// 利用map的key值保证唯一性
		Map<Object, T> map = new HashMap<Object, T>();
		// 利用反射获取bean标识字段值
		Method getIdMethod = getGetMethodByFieldName(idFieldName, clazz);

		if (beanList != null) {

			for (T t : beanList) {
				map.put(getIdMethod.invoke(t, null), t);
			}
		}

		return map;
	}

	/**
	 * 
	 * @desc: 将源对象中的值覆盖到目标对象中，仅覆盖源对象中不为NULL值的属性
	 * @param dest
	 *            目标对象，标准的JavaBean
	 * @param orig
	 *            源对象，可为Map、标准的JavaBean
	 * @throws Exception
	 * @return void
	 * @date： 2015-11-24 上午11:45:19
	 */

	@SuppressWarnings("rawtypes")
	public static void applyIf(Object dest, Object orig) throws Exception {
		try {
			if (orig instanceof Map) {
				Iterator names = ((Map) orig).keySet().iterator();
				while (names.hasNext()) {
					String name = (String) names.next();
					if (PropertyUtils.isWriteable(dest, name)) {
						Object value = ((Map) orig).get(name);
						if (value != null && !value.equals("")) {
							// 取到所有java的属性
							Field[] tarFields = dest.getClass().getDeclaredFields();
							for (int i = 0; i < tarFields.length; i++) {
								// 字段类型
								String tarType = tarFields[i].getType().toString();
								// 字段名称
								String tarTame = tarFields[i].getName();
								if (tarTame.equals(name)) {
									// 如果为时间，做特殊处理
									if ((tarType.indexOf("Date") != -1 || (tarType.indexOf("Timestamp") != -1))) {
										String dateStr = "yyyy-MM-dd HH:mm:ss";
										if (value.toString().trim().length() < 11) {
											dateStr = "yyyy-MM-dd";
										}
										SimpleDateFormat myFmt2 = new SimpleDateFormat(dateStr);
										Date date = new Date();
										if (value.toString().indexOf("T") != -1) {
											value = value.toString().replace("T", " ");
										}
										date = myFmt2.parse(value.toString());
										PropertyUtils.setSimpleProperty(dest, name, date);
									} else if (tarType.indexOf("Integer") != -1) {
										value = Integer.valueOf(value.toString());
										PropertyUtils.setSimpleProperty(dest, name, value);
									} else if (tarType.indexOf("Double") != -1) {
										value = Double.valueOf(value.toString());
										PropertyUtils.setSimpleProperty(dest, name, value);
									} else if (tarType.indexOf("BigDecimal") != -1) {
										value = new BigDecimal(value.toString());
										PropertyUtils.setSimpleProperty(dest, name, value);
									} else if (tarType.indexOf("Long") != -1) {
										value = Long.valueOf(value.toString());
										PropertyUtils.setSimpleProperty(dest, name, value);
									} else {
										PropertyUtils.setSimpleProperty(dest, name, value);
									}
								}
							}
						}
					}
				}
			} else {
				java.lang.reflect.Field[] fields = orig.getClass().getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					String name = fields[i].getName();
					if (PropertyUtils.isReadable(orig, name) && PropertyUtils.isWriteable(dest, name)) {
						Object value = PropertyUtils.getSimpleProperty(orig, name);
						if (value != null) {
							PropertyUtils.setSimpleProperty(dest, name, value);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new Exception("将源对象中的值覆盖到目标对象中，仅覆盖源对象中不为NULL值的属性", e);
		}
	}
 
}
