package com.ssh.nisus.service.impl;

import com.ssh.nisus.dao.CustomerDao;
import com.ssh.nisus.dao.impl.CustomerDaoImpl;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.domain.beankit.PageBean;
import com.ssh.nisus.factory.BeanFactory;
import com.ssh.nisus.service.BaseService;
import com.ssh.nisus.service.CustomerService;
import com.ssh.nisus.utils.HibUtil;
import com.ssh.nisus.utils.Log;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@136.com
 * @date: 2017-11-20-22:30
 */
@Service("customerService")
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerService{
    

    
    //    private CustomerDao factDaoField = (CustomerDao) BeanFactory.getBean("CustomerDao");
    @Autowired
    private CustomerDao customerDao;

//    ^ BaseDao模式替换
//    @Override
//    public List<Customer> findAll() {
//        List<Customer> list = null;
//
//        try {
//            Session session = HibUtil.start();
//
//            list = customerDao.findAll();
//
//            HibUtil.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            HibUtil.rollback();
//        }
//
//        return list;
//    }

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
//        Session session = HibUtil.start();
//
//        try {
//            c = customerDao.selectById(c.getCust_id());
//            // 有: 删除; 无: 等同于删除
//            if (c != null) {
//                // 删除
//                flag = customerDao.deleteOneById(c);
//            } else {
//                flag = true;        // 无, 等同于删除了
//            }
//            HibUtil.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            HibUtil.rollback();
//        }
    
    
        try {
            customerDao.deleteById(c.getCust_id());
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return  flag;
    }
    
    @Override
    public boolean add(Customer model) {
    
        boolean flag=false;
//        Session session = HibUtil.start();
//        try {
//            flag = customerDao.add(model);
//            HibUtil.commit();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            HibUtil.rollback();
//        }
    
        try {
            customerDao.save(model);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return flag;
    }
    
    @Override
    public PageBean getPageBean(Integer currentPage, Integer pageSize, DetachedCriteria dc) {
        // 得到总记录数
        Integer rows = customerDao.getRows(dc);
        Log.trace("总行数: "+rows);
        PageBean pb = new PageBean(currentPage, pageSize, rows);
        
        // 查询数据
//        List<Customer> customers = customerDao.getCustomerByPage(dc, pb.getStart(), pb.getPageSize());
        List<Customer> customers = customerDao.getByPage(dc, pb.getStart(), pb.getPageSize());
        // 封装数据
        pb.setData(customers);
        // 返回
        return pb;
        
    }
    
    @Override
    public Customer get(Customer customer) {
    
        return customerDao.getById(customer.getCust_id());
    }
    
    @Override
    public Boolean update(Customer customer) {
        Boolean flag = false;
        try {
            customerDao.update(customer);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
        return flag;
    }
}
