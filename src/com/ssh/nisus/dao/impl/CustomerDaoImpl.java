package com.ssh.nisus.dao.impl;

import com.ssh.nisus.dao.CustomerDao;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.utils.HibUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@136.com
 * @date: 2017-11-20-22:28
 */
public class CustomerDaoImpl implements CustomerDao {
    @Override
    public List<Customer> findAll() {

        Criteria criteria = HibUtil.getCurrentSession().createCriteria(Customer.class);
        List list = criteria.list();

        return list;
    }

    public void dao() {
        System.out.println("dao层方法可行");
    }
    
    @Override
    public Customer selectById(Long cust_id) {
        Customer c = null;
        Session session = HibUtil.getCurrentSession();
        c = session.get(Customer.class, cust_id);
    
        return c;
    }
    
    @Override
    public boolean deleteOneById(Customer c) {
        Session session = HibUtil.getCurrentSession();
        session.delete(c);
        return true;
    }
    
    @Override
    public boolean add(Customer model) {
        Session session = HibUtil.getCurrentSession();
        Long save = (Long) session.save(model);
        System.out.println("保存返回结果==" + save);
        if (save <= 0) {
            throw new RuntimeException("保存用户失败:" + model);
        }
        return true;
    }
}
