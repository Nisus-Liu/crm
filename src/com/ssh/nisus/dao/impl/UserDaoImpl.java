package com.ssh.nisus.dao.impl;

import com.ssh.nisus.dao.UserDao;
import com.ssh.nisus.domain.User;
import com.ssh.nisus.utils.HibUtil;
import com.ssh.nisus.utils.Log;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-11-24-22:09
 */
@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	
	// 准备模板
	@Resource(name = "sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	
	
	@Override
	public User getByUserCode(final String user_code) {
		User user_db = null;
		
		user_db = getHibernateTemplate().execute(new HibernateCallback<User>() {
			@Override
			public User doInHibernate(Session session) throws HibernateException {
				
				Query qr = session.createQuery("from User where user_code = ?");
				qr.setParameter(0, user_code);
				
				return (User) qr.uniqueResult();
			}
		});


//		^ 改为ssh整合模式
//		Session session = HibUtil.getCurrentSession();
//		Query qr = session.createQuery("from User where user_code = ?");
//		qr.setParameter(0, user_code);
//		user = (User) qr.uniqueResult();
//		Log.trace(user);
		return user_db;
	}
}
