package com.ssh.nisus.test;

import com.ssh.nisus.dao.CustomerDao;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.domain.Linkman;
import com.ssh.nisus.factory.BeanFactory;
import com.ssh.nisus.utils.HibUtil;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.hibernate.Session;
import org.junit.Test;

/**
 * 测试
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@136.com
 * @date: 2017-11-20-20:16
 */
public class Demo01 {


    /**
     * 测试客户和联系人注解是否生效
     */
    @Test
    public void fun01() {
        Session s = HibUtil.start();
        Customer c = new Customer();
        c.setCust_name("京东商城");
        c.setCust_level("尊贵铂金");

        Linkman l = new Linkman();
        l.setLk_name("奶茶妹妹");
        c.getLinkMen().add(l);

        l.setCustomer(c);

        s.save(c);
        HibUtil.commit();
        HibUtil.closeSessionFactory();

    }

    @Test
    public void fun02() {
//        StrutsPrepareAndExecuteFilter
    }


}
