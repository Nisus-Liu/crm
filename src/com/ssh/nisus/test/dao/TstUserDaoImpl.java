package com.ssh.nisus.test.dao;

import com.ssh.nisus.domain.User;
import com.ssh.nisus.utils.Log;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 继承的HibernateDaoSupport需要setSessionFactory, 用于createHibernateTemplate().
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-11-30-10:51
 */
@Repository("tstUserDao")

public class TstUserDaoImpl extends HibernateDaoSupport implements TstUserDao {
	@Resource(name = "sessionFactory")
	public void setSessionFactoryBySubclass(SessionFactory sessionFactory) {
		Log.trace(sessionFactory);
		setSessionFactory(sessionFactory);
	}
	
	/**
	 *
	 * HQL查询
	 * @param user_id
	 */
	@Override
//	public void getUserById(final Long user_id) {
//
//		// 测试HQL查询方式
//		User user = getHibernateTemplate().execute(new HibernateCallback<User>() {
//			@Override
//			public User doInHibernate(Session session) throws HibernateException {
//				// ----我们只管写查询语句即可-------
//				String sql = "from User where user_id=?";
//				Query query = session.createQuery(sql);
//				query.setParameter(0, user_id);
//				User user = (User) query.uniqueResult();
//
//				return user;
//			}
//
//
//		});
//
//		Log.trace(user);
//	}
	
	
	/**
	 * criteria查询
	 */
	public void getUserById(final Long user_id) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("user_id", user_id));
		List<User> res = (List<User>) getHibernateTemplate().findByCriteria(dc);// 需要一个离线criteria
		
		Log.trace(res);
	}
	
	
	/**
	 * 测试hibernateTransaction
	 */
	
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUser(User user) {
		
		getHibernateTemplate().save(user);
		
	}
	
	
	
}
