package com.ssh.nisus.test;

import com.ssh.nisus.dao.CustomerDao;
import com.ssh.nisus.factory.BeanFactory;
import org.junit.Test;

/**
 * 测试struts2+工厂
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@136.com
 * @date: 2017-11-21-15:46
 */
public class Demo02 {


    /**
     * 测试工厂类
     * @throws Exception
     */
    @Test
    public void fun01() throws Exception {
        CustomerDao customerDao = (CustomerDao) BeanFactory.getBean("CustomerDao");
        customerDao.dao();
    }

}
