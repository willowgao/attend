package com.wgsoft.common.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
 * @versioninfo： 远光软件股份有限公司<br>
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
	public String transferListToJsonMapForTabel(List list) {
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
