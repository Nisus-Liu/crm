package com.ssh.nisus.test;

import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.domain.User;
import com.ssh.nisus.test.dao.TstUserDao;
import com.ssh.nisus.utils.Log;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 测试hibernate和spring整合
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-11-29-22:51
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml.note")
public class Demo03 {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * 单独测试hibernate
	 */
	@Test
	public void fun01() {
		
		Configuration conf = new Configuration().configure();
		SessionFactory fact = conf.buildSessionFactory();
		Session session = fact.openSession();
		Transaction tx = session.beginTransaction();
		Query qr = session.createQuery("from Customer where cust_id=6");
		Customer o = (Customer) qr.uniqueResult();
		System.out.println(o);
		tx.commit();
		session.close();
		fact.close();
		
	}
	
	
	/**
	 * 整合方式一: spring读取hibernate的配置文件
	 */
	@Test
	public void fun02() {
		Log.trace(sessionFactory);
		SessionFactory fact = sessionFactory;
		Session session = fact.openSession();
		Transaction tx = session.beginTransaction();
		Query qr = session.createQuery("from Customer where cust_id=6");
		Customer o = (Customer) qr.uniqueResult();
		System.out.println(o);
		tx.commit();
		session.close();
		fact.close();
		
	}
	/**
	 * 整合方式二: hibernate的配置文件融入spring中
	 */
	@Test
	public void fun03() {
		Log.trace(sessionFactory);
		SessionFactory fact = sessionFactory;
		Session session = fact.openSession();
		Transaction tx = session.beginTransaction();
		Query qr = session.createQuery("from Customer where cust_id=6");
		Customer o = (Customer) qr.uniqueResult();
		System.out.println(o);
		tx.commit();
		session.close();
		fact.close();
		
	}
	
	@Autowired
	private TstUserDao tstUserDao;
	
	/**
	 * 测试hibernateTemplate
	 */
	@Test
	public void fun04() {
		tstUserDao.getUserById(2L);
		
	}
	/**
	 * 测试hibernateTransactionManager事务管理
	 */
	@Test
	public void fun05() {
		User user = new User();
		user.setUser_code("x03");
		user.setUser_name("李世民");
		tstUserDao.saveUser(user);
	}
	
	
	
	
	
}
