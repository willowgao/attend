package com.wgsoft.common.idao;

import java.util.List;

/**
 * @type IBaseDao
 * @title IBaseDao.java
 * @desc
 * @author gaochengliu
 * @date 2015-7-17
 * @version V1.0
 * @param <T>
 */
public interface IBaseDao {

	public void insert(Object entity);

	public void update(Object entity);

	public void delete(Object entity);

	public List<Object> getList(Object entity);

	public List<Object> getList(String hql);

	public List<Object> getSqlList(String sql);

	public List<Object> getList(String hql, int curPage, int pageSize);

	public int insertBatch(List entityList);

	public int updateBatch(List entityList);

	public void flush();

	public int deleteBatch(List entityList);

	public List getSqlList_(String sql, Class entityClass);
	
	/**
	 * 描述:得到sql的更新<br>
	 * 参数: SQL 普通的SQL(dlete ,update)<br>
	 */
	public int getSqlUpdate(String sql);
	

	public int updateBySql(String sql);
}
