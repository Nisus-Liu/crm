package com.ssh.nisus.dao.impl;

import com.ssh.nisus.dao.BaseDao;
import com.ssh.nisus.utils.Log;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-12-04-17:18
 */
@Repository("baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class clazz;
	public BaseDaoImpl() {
//		Log.trace(getHibernateTemplate());  // 这里肯定是null, 因为走构造时, 还没有走set方法
		// 获取泛型的Class
		// 获取带泛型的父类(此构造方法受子类继承使用, 则运行时this是子类.
		// Type对象是顶级接口, Class就是继承这个.
		// When a parameterized type p is created, the generic type declaration that p instantiates is resolved, and all type arguments of p are created recursively.
		// 也就是说, ParameterizedType一旦实例化, 其包含的泛型会被递归实例化. --> 所以通过这个可以获取其上的泛型.
		// 当本类自己实例化时, getGenericSuperclass()得到的是HibernateDaoSupport的泛型类型, 然而父类根本就没有泛型所以得到的是Class类型
		// 故无法强转成ParameterizedType类型
		// 本方法是让子类继承使用的, 目的是为了获取本类的泛型运行时实际类型
		try {
			ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();//
			clazz = (Class) genericSuperclass.getActualTypeArguments()[0];
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
	}
	
	/**
	 * 子类必须继承, 用以给HibernateDaoSupport注入SessionFactory实例
	 * 内容如下: super.setSessionFactory(sessionFactory);
	 * 并加上注解, 让spring可以注入.
	 * @param sessionFactory
	 */
	// 给HibernateDaoSupport注入sessionFactory |
	@Autowired
	public void setSessionFactoryToHibernateDaoSupport(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	
	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}
	
	@Override
	public void deleteAll(T t) {
		getHibernateTemplate().delete(t);
	}
	
	@Override
	public void deleteById(Serializable id) {
		// 先取后删
		T t = this.getById(id);
		if (t == null) {
			throw new RuntimeException("用户ID为" + id + "的用户不存在, 无法执行删除操作!");
		}
		getHibernateTemplate().delete(t);
	}
	
	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
		
	}
	
	
	@Override
	public T getById(Serializable id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}
	
	@Override
	public List<T> getByPage(DetachedCriteria dc, Integer start, Integer pageSize) {
		List<T> list = (List<T>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
		
		
		return list;
	}
	
	@Override
	public Integer getRows(DetachedCriteria dc) {
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
		// 清除聚合条件
		dc.setProjection(null);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		} else {
			return null;
		}
	
	}
	
	@Override
	public List<T> getByCriteria(DetachedCriteria dc) {
		return (List<T>) getHibernateTemplate().findByCriteria(dc);
	}
	
	
}
