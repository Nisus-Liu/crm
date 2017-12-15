package com.ssh.nisus.dao;

import com.ssh.nisus.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 负责客户相关数据
 */
public interface CustomerDao extends BaseDao<Customer>{
//    List<Customer> findAll();

    void dao();
	
	Customer selectById(Long cust_id);
	
	boolean deleteOneById(Customer c);
	
	boolean add(Customer model);
	

	
	List<Customer> getCustomerByPage(DetachedCriteria dc, Integer start, Integer pageSize);
}
