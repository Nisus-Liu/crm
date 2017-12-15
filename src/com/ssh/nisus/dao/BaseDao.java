package com.ssh.nisus.dao;

import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * 基础dao层接口, 定义基础的增删改查方法.
 */
public interface BaseDao<T>{
	
	
	
	/**
	 * 保存某个对象
	 * @param t
	 */
	// 增
	void save(T t);
	
	/**
	 * 删除所有
	 * @param t
	 */
	// 删
	void deleteAll(T t);
	
	/**
	 * 依据ID删除某条记录
	 * @param id
	 */
	void deleteById(Serializable id);
	
	/**
	 * 更新某个记录
	 * @param t
	 */
	// 改
	void update(T t);
	
//	/**
//	 * 获取所有
//	 * @param t
//	 * @return
//	 */
//	// 查
//	List<T> getAll(T t);
	
	/**
	 * 根据ID获取某个记录
	 * @param id
	 * @return
	 */
	T getById(Serializable id);
	
	/**
	 * 获取分页数据
	 * @param dc
	 * @param start
	 * @param pageSize
	 * @return
	 */
	List<T> getByPage(DetachedCriteria dc, Integer start, Integer pageSize);
	
	/**
	 * 获取分页用的总记录数
	 * @param detachedCriteria
	 * @return
	 */
	Integer getRows(DetachedCriteria detachedCriteria);
	
	/**
	 * 查询所有
	 * @param dc
	 * @return
	 */
	List<T> getByCriteria(DetachedCriteria dc);
	
}
