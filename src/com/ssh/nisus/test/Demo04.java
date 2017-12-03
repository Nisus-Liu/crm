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
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * ssh整合测试
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-11-29-22:51
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo04 {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@Test
	public void fun01() {
		Log.trace(sessionFactory);
	}
	
	@Test
	public void fun02() {
		
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Object sessionFactory = ac.getBean("sessionFactory");
		
		Log.trace(this.sessionFactory);
	}
	
	
	
	
}
