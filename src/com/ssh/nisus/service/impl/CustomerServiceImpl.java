package com.ssh.nisus.service.impl;

import com.ssh.nisus.dao.CustomerDao;
import com.ssh.nisus.dao.impl.CustomerDaoImpl;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.factory.BeanFactory;
import com.ssh.nisus.service.BaseService;
import com.ssh.nisus.service.CustomerService;
import com.ssh.nisus.utils.HibUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@136.com
 * @date: 2017-11-20-22:30
 */
public class CustomerServiceImpl extends BaseService implements CustomerService{
    
    public CustomerDao getCustomerDao() {
        return customerDao;
    }
    
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    
    //    private CustomerDao factDaoField = (CustomerDao) BeanFactory.getBean("CustomerDao");
    private CustomerDao customerDao;

    @Override
    public List<Customer> findAll() {
        List<Customer> list = null;

        try {
            Session session = HibUtil.start();

            list = customerDao.findAll();

            HibUtil.commit();
        } catch (Exception e) {
            e.printStackTrace();
            HibUtil.rollback();
        }

        return list;
    }

    @Override
    public void service() {
//        System.out.println("调用Service方法");
//
//        // 测试成员变量中获取是否成功
//        System.out.println("----开始使用成员变量形式的工厂dao对象:");
//        factDaoField.dao();
//        System.out.println("//结束使用成员变量形式的工厂dao对象----");
//
//        // 测试方法体重中是否可以正常获取
//        System.out.println("----即将使用Service的工厂dao对象:");
//        CustomerDao factDao = (CustomerDao) BeanFactory.getBean("CustomerDao");
//        factDao.dao();
//        System.out.println("//Service的工厂dao对象使用结束----");
    }
    
    @Override
    public boolean deleteOneById(Customer c) {
        // 删除之前查询有没有这个客户
        boolean flag = false;
        Session session = HibUtil.start();
    
        try {
            c = customerDao.selectById(c.getCust_id());
            // 有: 删除; 无: 等同于删除
            if (c != null) {
                // 删除
                flag = customerDao.deleteOneById(c);
            } else {
                flag = true;        // 无, 等同于删除了
            }
            HibUtil.commit();
        } catch (Exception e) {
            e.printStackTrace();
            HibUtil.rollback();
        }
    
        return  flag;
    }
    
    @Override
    public boolean add(Customer model) {
    
        boolean flag=false;
        Session session = HibUtil.start();
        try {
            flag = customerDao.add(model);
            HibUtil.commit();
    
        } catch (Exception e) {
            e.printStackTrace();
            HibUtil.rollback();
        }
        return flag;
    }
}
