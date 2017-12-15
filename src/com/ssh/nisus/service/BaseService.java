package com.ssh.nisus.service;

import com.ssh.nisus.dao.CustomerDao;
import com.ssh.nisus.dao.impl.CustomerDaoImpl;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.domain.beankit.PageBean;
import com.ssh.nisus.factory.BeanFactory;
import com.ssh.nisus.utils.Log;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Service层基类
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@136.com
 * @date: 2017-11-20-23:05
 */
public interface BaseService {

	public PageBean getPageBean(Integer currentPage, Integer pageSize, DetachedCriteria dc);

}
