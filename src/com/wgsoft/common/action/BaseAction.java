package com.wgsoft.common.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wgsoft.common.utils.BeanUtil;
import com.wgsoft.common.utils.SysConstants;
import com.wgsoft.performance.model.JobAssignment;
import com.wgsoft.performance.model.PerformanceIndex;
import com.wgsoft.system.iservice.IDataDictionaryService;
import com.wgsoft.user.iservice.IUserService;
import com.wgsoft.user.model.UserInfo;

/**
 * 
 * @title： BaseAction.java
 * @desc： the public Action
 * @author： Willowgao
 * @date： 2015-9-7 下午04:57:24
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 * @param <T>
 */
public class BaseAction extends ActionSupport implements Action {

	public static final String DATAGRID_JSON_DATA = "datagrid";

	public static final String USERINFO = "userInfo";

	public int total;

	public int page;

	/**
	 * action
	 */
	public ActionContext actionContext = ActionContext.getContext();
	/**
	 * request
	 */
	public HttpServletRequest request = ServletActionContext.getRequest();
	/**
	 * response
	 */
	public HttpServletResponse response = ServletActionContext.getResponse();
	/**
	 * session
	 */
	public HttpSession session = request.getSession();
	/**
	 * userInfo
	 */
	public static Map<String, UserInfo> userMap = new HashMap<String, UserInfo>();
	/**
	 * 
	 */
	private static final long serialVersionUID = -6063725847696756227L;
	/**
	 * log
	 */
	private static Log log = LogFactory.getLog(BaseAction.class);

	/**
	 * application init
	 */
	public static ApplicationContext ctx = null;

	/**
	 * the method of init user,applicationContext
	 */
	{
		if (ctx == null) {
			ctx = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
			if (log.isInfoEnabled()) {
				log.info("****************************SpringContext Load Success******************************");
			}
			initUserInfo();
		}

		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @desc:List For easyUI
	 * @param list
	 * @return
	 * @return String
	 * @date： 2015-9-9 下午01:46:15
	 */

	@SuppressWarnings("unchecked")
	public static String transferListToJsonMapForTabel(List list) {
		String jsonStr = null;
		Map<String, Object> dataGridMap = new HashMap<String, Object>();

		dataGridMap.put("total", list.size());
		dataGridMap.put("rows", list);
		try {
			jsonStr = JSONUtil.serialize(dataGridMap);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 创建一颗树，以json字符串形式返回
	 * 
	 * @param list
	 *            原始数据列表
	 * @return 树
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public static String createTreeJson(List list, Object obj) throws ClassNotFoundException, IllegalArgumentException,
			SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		JSONArray rootArray = new JSONArray();
		// 类型初始化
		Class cls = (Class) Class.forName(obj.getClass().getName());
		for (int i = 0; i < list.size(); i++) {
			Object object = (Object) list.get(i);
			if (cls.getMethod("getPid").invoke(object) == null) {
				JSONObject rootObj = createBranch(list, object);
				rootArray.add(rootObj);
			}
		}

		String jsonStr = null;
		Map<String, Object> dataGridMap = new HashMap<String, Object>();
		dataGridMap.put("total", rootArray.size());
		dataGridMap.put("rows", rootArray);
		jsonStr = JSONSerializer.toJSON(dataGridMap).toString();
		return jsonStr;
	}

	/**
	 * 递归创建分支节点Json对象
	 * 
	 * @param list
	 *            创建树的原始数据
	 * @param currentNode
	 *            当前节点
	 * @return 当前节点与该节点的子节点json对象
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	private static JSONObject createBranch(List list, Object currentNode) throws ClassNotFoundException,
			IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		/*
		 * 将javabean对象解析成为JSON对象
		 */
		JSONObject currentObj = JSONObject.fromObject(currentNode);
		JSONArray childArray = new JSONArray();
		/*
		 * 循环遍历原始数据列表，判断列表中某对象的父id值是否等于当前节点的id值，
		 * 如果相等，进入递归创建新节点的子节点，直至无子节点时，返回节点，并将该 节点放入当前节点的子节点列表中
		 */
		Class cls = (Class) Class.forName(currentNode.getClass().getName());

		for (int i = 0; i < list.size(); i++) {
			Object object = (Object) list.get(i);
			if (cls.getMethod("getPid").invoke(object) != null
					&& cls.getMethod("getPid").invoke(object).equals(cls.getMethod("getId").invoke(currentNode))) {
				JSONObject childObj = createBranch(list, object);
				childArray.add(childObj);
			}
		}

		/*
		 * 判断当前子节点数组是否为空，不为空将子节点数组加入children字段中
		 */
		if (!childArray.isEmpty()) {
			currentObj.put("children", childArray);
		}
		return currentObj;
	}

	/**
	 * @desc:List For easyUI
	 * @param list
	 * @return
	 * @return String
	 * @date： 2015-9-9 下午01:46:15
	 */

	@SuppressWarnings("unchecked")
	public static String tranferTreeGridJson(List list) throws JSONException {
		String jsonStr = null;
		Map<String, Object> dataGridMap = new HashMap<String, Object>();
		dataGridMap.put("total", list.size());
		dataGridMap.put("rows", list);
		jsonStr = JSONSerializer.toJSON(dataGridMap).toString();
		return jsonStr;
	}

	/**
	 * @desc: 将list转化为json对像，用于在easyui datagrid显示（带合计）
	 *        目前只有一种合计(sum)，以后可增加(avg,max,min 等)
	 * @see com.wgsoft.performance.action.PerformanceIndexManageAction#queryIndex
	 *      ()
	 * @param list
	 *            数据集合
	 * @param field
	 *            需要合计的列名(同jsp中field 列名相同)
	 * @param object
	 *            对象实例
	 * @param setNullField
	 *            需要在脚行设置为空的列名(同jsp中field 列名相同)
	 * @param setSumDesc
	 *            需要设置“合计”字样描述的列名 (同jsp中field 列名相同)
	 * @return
	 * @return String
	 * @date： 2016-1-28 下午01:53:56
	 */
	@SuppressWarnings("unchecked")
	public static String transferListToJsonMapForTabel(List list, String[] field, Object object, String[] setNullField,
			String[] setSumDesc) {
		String jsonStr = null;
		Map<String, Object> dataGridMap = new HashMap<String, Object>();
		dataGridMap.put("total", list.size());
		dataGridMap.put("rows", list);
		List<Object> footer = new ArrayList<Object>();

		Constructor con = null;
		Class cls = null;
		try {
			// 类型初始化
			cls = (Class) Class.forName(object.getClass().getName());
			con = cls.getConstructor();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		// 得到需要合计的列
		String totalField = field[0];
		totalField = (totalField.charAt(0) + "").toUpperCase() + totalField.substring(1);
		// 合计值
		Double total = new Double(0);
		try {
			object = con.newInstance();
			// 合计
			for (int i = 0; i < list.size(); i++) {
				BeanUtil.applyIf(object, list.get(i));
				// 执行get方法得到数据
				Double dou = (Double) cls.getMethod("get" + totalField).invoke(object);
				// 累计
				total = total.doubleValue() + dou.doubleValue();
			}
			// 将合计值放到初始化的属性中
			cls.getMethod("set" + totalField, Double.class).invoke(object, total);
			// 设置最后一行，需要赋值为空的列
			for (String nullField : setNullField) {
				nullField = (nullField.charAt(0) + "").toUpperCase() + nullField.substring(1);
				cls.getMethod("set" + nullField, String.class).invoke(object, "");
			}
			// 设置最后一行，需要赋值为“合计”字样的列
			for (String descField : setSumDesc) {
				descField = (descField.charAt(0) + "").toUpperCase() + descField.substring(1);
				cls.getMethod("set" + descField, String.class).invoke(object, "合计");
			}
			// 添加到合计list中
			footer.add(object);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 放到datagrid集合
		dataGridMap.put("footer", footer);
		try {
			jsonStr = JSONUtil.serialize(dataGridMap);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}

	public static void main(String[] args) {
		PerformanceIndex assess = new PerformanceIndex();
		assess.setIndexScore(Double.valueOf(23));

		PerformanceIndex assess1 = new PerformanceIndex();
		assess1.setIndexScore(Double.valueOf(43));

		List list = Arrays.asList(assess, assess1);
		String[] sumCol = new String[] { "indexScore" };
		String[] setNull = new String[] { "item", "itemDetail" };
		String[] setSumDesc = new String[] { "indexContent" };
		System.out.println(transferListToJsonMapForTabel(list, sumCol, new PerformanceIndex(), setNull, setSumDesc));
	}

	/**
	 * @desc: 初始化页面form
	 * @param domainName
	 *            String Action中定义的对应名称 (最好将form的名称和此名称定义成一样的)
	 * @param object
	 *            Object Form对象（JavaBean）
	 * @param classes
	 *            Class JavaBean的类名
	 * @return String Map Json对象 格式为{domainName.列名:值,domainName.列名1:值2....}
	 * @date： 2015-11-11 下午07:51:24
	 */
	@SuppressWarnings("unchecked")
	public static String transferVoToForm(String domainName, Object object, Class classes) {
		String jsonStr = null;
		Field[] fields = classes.getDeclaredFields();
		Map<String, Object> formData = new HashMap<String, Object>();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			f.setAccessible(true);
			try {

				formData.put(domainName.concat(".").concat(f.getName()), f.get(object));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			jsonStr = JSONUtil.serialize(formData);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * @desc: 转换Json字符为 Map
	 * @param jsonStr
	 *            String 前端传入JsonMap字符串,见 easyui_custom.js getDataFromDatagrid
	 *            方法
	 * @param obj
	 *            Object 需要转型的类实例
	 * @return Map<String,List<T>> key 分别为 </br> insertRow
	 *         SysConstants.DataGridData#DATAGRID_LIST_INSERTLIST </br>
	 *         updateRow SysConstants.DataGridData# DATAGRID_LIST_UPDATELIST
	 *         </br> deleteRow SysConstants
	 *         .DataGridData#DATAGRID_LIST_DELETELIST </br>
	 * @throws JSONException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @date： 2015-11-20 上午08:52:11
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public static final <T> Map<String, List<T>> getListFromMap(String jsonStr, Object obj) {
		// Json格式的Map
		Map<String, Map<String, List<Map>>> map = null;
		try {
			map = (Map) JSONUtil.deserialize(jsonStr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 取到数据
		Map<String, List<Map>> dataGridList = (Map) map.get("data");
		// 取到新增的list
		List<Map> insertList = dataGridList.get(SysConstants.DataGridData.DATAGRID_LIST_INSERTLIST);
		// 取到删除的list
		List<Map> deleteList = dataGridList.get(SysConstants.DataGridData.DATAGRID_LIST_DELETELIST);
		// 取到更新的list
		List<Map> updataList = dataGridList.get(SysConstants.DataGridData.DATAGRID_LIST_UPDATELIST);
		Map<String, List<T>> redataGridList = new HashMap<String, List<T>>();

		Constructor<T> con = null;
		try {
			Class<T> cls = (Class<T>) Class.forName(obj.getClass().getName());
			con = cls.getConstructor();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		List<T> reInsertList = new ArrayList<T>();//
		List<T> reUpdateList = new ArrayList<T>();
		List<T> reDeleteList = new ArrayList<T>();
		try {
			// 实例化list
			for (int i = 0; i < insertList.size(); i++) {
				obj = con.newInstance();
				BeanUtil.applyIf(obj, insertList.get(i));
				reInsertList.add((T) obj);
			}
			redataGridList.put(SysConstants.DataGridData.DATAGRID_LIST_INSERTLIST, reInsertList);

			for (int i = 0; i < deleteList.size(); i++) {
				obj = con.newInstance();
				BeanUtil.applyIf(obj, deleteList.get(i));
				reDeleteList.add((T) obj);
			}
			redataGridList.put(SysConstants.DataGridData.DATAGRID_LIST_DELETELIST, reDeleteList);

			for (int i = 0; i < updataList.size(); i++) {
				obj = con.newInstance();
				BeanUtil.applyIf(obj, updataList.get(i));
				reUpdateList.add((T) obj);
			}
			redataGridList.put(SysConstants.DataGridData.DATAGRID_LIST_UPDATELIST, reUpdateList);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return redataGridList;
	}

	public String execute() throws Exception {
		if (getUserInfo() == null) {
			return LOGIN;
		}
		return SUCCESS;
	}

	/**
	 * @desc: init userInfo to userMap
	 * @return void
	 * @date： 2015-9-7 下午04:57:03
	 */
	public static void initUserInfo() {
		IUserService userService = (IUserService) getService("userService");
		List<UserInfo> list = userService.getUserInfo();
		for (UserInfo user : list) {
			userMap.put(user.getUserid(), user);
		}

		if (log.isInfoEnabled()) {
			log.info("****************************System UserInfo Load Success******************************");
		}
	}

	/**
	 * @desc:通过serviceid取到对应的spring注入
	 * @param serviceName
	 * @return
	 * @return Object
	 * @date： 2015-9-7 下午04:56:44
	 */
	public static Object getService(String serviceName) {
		Object obj = ctx.getBean(serviceName);
		return obj;
	}

	/**
	 * @desc:ajax写入页面数据
	 * @param response
	 * @param text
	 * @return void
	 * @date： 2015-9-7 下午04:56:32
	 */
	public void renderText(HttpServletResponse response, String text) {
		PrintWriter out = null;
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
			out.write(text);
		} catch (IOException e) {
			log.error(e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
				out = null;
			}
		}
	}

	/**
	 * @desc: 查询用户部门下所有的人员，包括自己
	 * @return void
	 * @date： 2015-12-5 上午11:39:12
	 */
	public void getUsersFormUser() {
		try {
			renderText(response, JSONUtil.serialize(getUserInfo().getUsers()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @desc: 查询用户组织下所有部门
	 * @return void
	 * @date： 2015-12-5 上午11:39:34
	 */
	public void getDeptsFormUser() {
		try {
			renderText(response, JSONUtil.serialize(getUserInfo().getDepts()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @desc:设置用户信息
	 * @param user
	 * @return void
	 * @date： 2015-9-10 下午12:48:15
	 */
	public void setUserInfo(UserInfo user) {
		session.setAttribute(USERINFO, user);
	}

	/**
	 * @desc:获取用户信息
	 * @return
	 * @return UserInfo
	 * @date： 2015-9-10 下午12:48:27
	 */
	public UserInfo getUserInfo() {
		return (UserInfo) session.getAttribute(USERINFO);
	}

	/**
	 * @desc:获取静态数据字典服务
	 * @return
	 * @return IDataDictionaryService
	 * @date： 2015-11-12 上午09:31:35
	 */
	public IDataDictionaryService getDataDictionaryService() {
		return (IDataDictionaryService) getService("dataDictionaryService");
	}

}
