package com.ssh.nisus.test.test;

import com.ssh.nisus.domain.BaseDict;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.utils.HibUtil;
import com.ssh.nisus.utils.Log;
import org.hibernate.Session;
import org.junit.Test;

/**
 * 单独用hibernate
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-12-06-14:17
 */
public class Demo05 {
	
	
	@Test
	public void fun03() {
		
		
		Session session = HibUtil.start();
		
		// 主表放弃外键
		// 都用默认级联
		Customer c = new Customer();
		c.setCust_name("风塔集团");
		
		BaseDict dict = new BaseDict();
		dict.setDictId("6");
		dict.setDictEnable("1");
		dict.setDictItemName("来自人间");
		dict.setDictItemCode("007");
		dict.setDictTypeCode("5346");
		dict.setDictTypeName("哪里来");
		
//		c.setCust_source(dict);
		
//		dict.getCust_source().add(c);

		
//		session.save(c);
		session.save(dict);
		
		HibUtil.commit();
		HibUtil.closeSessionFactory();
		
	}
}
