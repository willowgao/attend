package com.wgsoft.common.dao;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.common.utils.CnDate;
import com.wgsoft.common.utils.SqlUtil;

/**
 * @title： BaseDao.java
 * @desc： 数据加操作类
 * @author： Willowgao
 * @date： 2016-2-14 上午10:46:07
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class BaseDao extends HibernateDaoSupport implements IBaseDao {

	private static Log log = LogFactory.getLog(BaseDao.class);

	public void delete(Object entity) {
		getSession().delete(entity);
	}

	/**
	 * 描述:得到sql的更新<br>
	 * 参数: SQL 普通的SQL(dlete ,update)<br>
	 */
	public int getSqlUpdate(String sql) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			ps = conn.prepareStatement(sql);
			int i = ps.executeUpdate();
			ps.close();
			return i;
		} catch (SQLException e) {

			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量删除HIBERANTE实体对象记录；第50条更新一次；注意如果需要同一事务提交则需自已重写批量方法。
	 * 
	 * @param entity
	 *            //实体列表数据；
	 * @return 0表示删除有错误，1表示删除成功；
	 */
	public int deleteBatch(List entityList) {
		int count = 1;
		int flag = 1;
		// 该中事务只有公共代码可以，其它不行；
		try {
			for (int i = 0; i < entityList.size(); i++) {
				Object object = entityList.get(i);
				this.delete(object);
				// 每50条添加一次;
				if (count % 50 == 0) {
					this.getSession().flush(); // 修改后使用FLUSH进行清除缓存，并执行相应的SQL语句!
				}
				count++;
			}
			if (count > 1) {
				this.getSession().flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
			flag = 0;
		} finally {

		}
		return flag;
	}

	/**
	 * 
	 flush()和缓存相关。执行时会清除session缓存并向数据库发送SQL语句并执行，但此时如果数据库当前存在一个事务，
	 * 数据库会先将这些SQL语句缓存起来
	 * ，那么此时在数据库中是无法看到SQL语句执行结果的。除非执行commit提交了事务。只要没有执行commit()方法
	 * ，就能通过rollback()方法进行回滚。 1、session在什么情况下清理缓存：
	 * 默认情况下，当应用程序提交事务，如：Transaction.commit; 当我们显示调用flush的时候
	 * 在执行某些查询的时候，如：iterate 2、session.flush()主要完成两件事情： 清理缓存 执行sql
	 * 3、flush执行的顺序：hibernate按照save,update,delete顺序提交相关操作
	 */
	public void flush() {
		this.getSession().flush();
	}

	/**
	 * 
	 * 描述: 从数据库得到一个业务对象List集合 <br>
	 * 
	 * 参数：entityClass, 一个业务对象实体类<br>
	 * 
	 */
	public List getList(Class entityClass) {
		return getList(" from " + entityClass.getName());
	}

	/**
	 * 描述: 从数据库得到一个业务对象List集合 <br>
	 * 
	 * 参数：hql, 查询的HQL语句<br>
	 * 
	 */
	public List getList(String hql) {
		return getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public List<Object> getList(String hql, int curPage, int pageSize) {
		Query query = getSession().createQuery(hql);
		query.setFirstResult((curPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Object> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getSqlList(String sql) {
		if (log.isInfoEnabled()) {
			log.info("执行过程【" + sql.toUpperCase() + "】");
		}
		return getSession().createSQLQuery(sql).list();
	}

	public void insert(Object entity) {
		getSession().save(entity);
	}

	/**
	 * 批量插入HIBERANTE实体对象记录；50条提交一次事务；注意如果需要同一事务提交则需自已重写批量方法。
	 * 
	 * @param entity
	 *            //实体列表数据；
	 * @return 0表示插入有错误，1表示插入成功；
	 */
	public int insertBatch(List entityList) {
		int count = 1;
		int flag = 1;

		try {
			for (int i = 0; i < entityList.size(); i++) {
				Object object = entityList.get(i);
				this.insert(object);
				// 每50条添加一次;
				if (count % 50 == 0) {
					this.getSession().flush(); // 修改后使用FLUSH进行清除缓存，并执行相应的SQL语句!
				}
				count++;
			}
			if (count > 1) {
				this.getSession().flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = 0;
		} finally {

		}
		return flag;
	}

	/**
	 * 批量更新HIBERANTE实体对象记录；第50条更新一次；注意如果需要同一事务提交则需自已重写批量方法。
	 * 
	 * @param entity
	 *            //实体列表数据；
	 * @return 0表示更新有错误，1表示更新成功；
	 */
	public int updateBatch(List entityList) {
		int count = 1;
		int flag = 1;
		// 该中事务只有公共代码可以，其它不行；
		try {
			for (int i = 0; i < entityList.size(); i++) {
				Object object = entityList.get(i);
				this.update(object);
				// 每50条添加一次;
				if (count % 50 == 0) {
					this.getSession().flush(); // 修改后使用FLUSH进行清除缓存，并执行相应的SQL语句!
				}
				count++;
			}
			if (count > 1) {
				this.getSession().flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
			flag = 0;
		} finally {
		}
		return flag;
	}

	public List<Object> getList(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 描述:得到SQL的值 <br>
	 * 参数:SQL 普通的查询SQL语句 <br>
	 * 返回值:对象数组
	 * 
	 * @throws SQLException
	 * @throws IllegalStateException
	 * @throws HibernateException
	 * @throws DataAccessResourceFailureException
	 */
	public List getSqlList_(String sql, Class entityClass) {
		List list = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			if (log.isInfoEnabled()) {
				log.info("执行SQL【" + sql .toUpperCase()+ "】");
			}
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			list = null;
			list = populate(rs, entityClass);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 
	 * 将rs结果转换成对象列表
	 * 
	 * @param rs
	 *            jdbc结果集
	 * 
	 * @param clazz
	 *            对象的映射类 return 封装了对象的结果列表
	 */
	public List populate(ResultSet rs, Class clazz) {
		// 结果集的元素对象
		ResultSetMetaData rsmd;
		List list = null;
		try {
			rsmd = rs.getMetaData();
			// 获取结果集的元素个数
			int colCount = rsmd.getColumnCount();
			// 返回结果的列表集合
			list = new ArrayList();
			// 业务对象的属性数组
			Field[] fields = clazz.getDeclaredFields();
			Field[] fields1 = clazz.getSuperclass().getDeclaredFields();
			while (rs.next()) {
				// 对每一条记录进行操作
				Object obj = clazz.newInstance();// 构造业务对象实体
				// 将每一个字段取出进行赋值
				for (int i = 1; i <= colCount; i++) {
					Object value = rs.getObject(i);
					String columnName = rsmd.getColumnName(i);
					// 寻找该列对应的对象属性
					for (int j = 0; j < fields.length; j++) {
						Field f = fields[j];
						// 如果匹配进行赋值
						if (f.getName().equalsIgnoreCase(SqlUtil.getPropNameForDb(columnName))) {
							boolean flag = f.isAccessible();

							f.setAccessible(true);
							String typeName = f.getType().getName();
							String value_ = rs.getString(i);
							boolean flag_ = false;
							flag_ = (value_ == null);
							if (typeName.equals("java.sql.Timestamp")) {
								if (rs.getTimestamp(i) != null)
									value = new CnDate(rs.getTimestamp(i));
							} else if (typeName.equals("java.lang.Long")) {
								if (flag_)
									value = null;
								else
									value = new Long(rs.getLong(i));
							} else if (typeName.equals("java.lang.Integer")) {
								if (flag_)
									value = null;
								else
									value = new Integer(rs.getInt(i));
							} else if (typeName.equals("java.util.Date")) {
								if (rs.getTimestamp(i) != null)
									value = new CnDate(rs.getTimestamp(i));
							} else if (typeName.equals("java.lang.Double")) {
								if (flag_)
									value = null;
								else
									value = new Double(rs.getDouble(i));
								// rs.getbi
							} else if (typeName.equals("java.math.BigDecimal")) {
								if (flag_)
									value = null;
								else
									value = rs.getBigDecimal(i);
								// rs.getbi
							}
							f.set(obj, value);
							f.setAccessible(flag);
						}
					}
					// 寻找该列对应的对象属性
					for (int j = 0; j < fields1.length; j++) {
						Field f = fields1[j];

						// 如果匹配进行赋值
						if (f.getName().equalsIgnoreCase(SqlUtil.getPropNameForDb(columnName))) {
							boolean flag = f.isAccessible();
							f.setAccessible(true);
							String typeName = f.getType().getName();
							boolean flag_ = false;
							String value_ = rs.getString(i);
							flag_ = (value_ == null);
							if (typeName.equals("java.sql.Timestamp")) {
								if (rs.getTimestamp(i) != null)
									value = new CnDate(rs.getTimestamp(i));
							} else if (typeName.equals("java.lang.Long")) {
								if (flag_)
									value = null;
								else
									value = new Long(rs.getLong(i));
							} else if (typeName.equals("java.lang.Integer"))

							{
								if (rs.getTimestamp(i) != null)
									value = new CnDate(rs.getTimestamp(i));
							} else if (typeName.equals("java.lang.Double")) {
								if (flag_)
									value = null;
								else
									value = new Double(rs.getDouble(i));
							} else if (typeName.equals("java.math.BigDecimal")) {
								if (flag_)
									value = null;
								else
									value = rs.getBigDecimal(i);
								// rs.getbi
							}
							f.set(obj, value);
							f.setAccessible(flag);
						}
					}

				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * 描述: 保存一个修改的业务对象到数据库 <br>
	 * 
	 * 参数：entity, 一个业务对象实体 <br>
	 * 
	 */
	public void update(Object entity) {
		getSession().update(entity);
	}

	public void callPrepareCall(String callStr, String[] arg) {
		if (log.isInfoEnabled()) {
			log.info("执行过程【" + callStr + "】");
		}
		Connection con = null;
		try {
			con = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			CallableStatement proc = con.prepareCall(callStr);
			for (int i = 0; i < arg.length; i++) {
				proc.setString(i + 1, arg[i]);
			}
			proc.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public int updateBySql(String sql) {
		if (log.isInfoEnabled()) {
			log.info("执行过程【" + sql + "】");
		}
		Session session = getSession();
		int re = session.createSQLQuery(sql.toString()).executeUpdate();
		session.flush();
		return re;
	}

}
