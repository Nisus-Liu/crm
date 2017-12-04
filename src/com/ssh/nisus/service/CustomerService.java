package com.ssh.nisus.service;

import com.ssh.nisus.dao.CustomerDao;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.domain.beankit.PageBean;
import com.ssh.nisus.factory.BeanFactory;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerService {

    /**
     * 查找所有客户
     * @return
     */
    List<Customer> findAll();

    void service();
	
	boolean deleteOneById(Customer c);
	
	boolean add(Customer model);
	
	PageBean getPageBean(Integer currentPage, Integer pageSize, DetachedCriteria dc);
}
